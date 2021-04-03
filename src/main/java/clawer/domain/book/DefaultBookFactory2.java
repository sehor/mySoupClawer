package clawer.domain.book;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;
import clawer.data.DataUtils;
import clawer.domain.chapter.Chapter;
import clawer.domain.chapter.ChapterService;
import clawer.domain.image.Image;
import clawer.domain.image.ImageService;
import clawer.domain.urlTree.UrlTree;
import clawer.domain.urlTree.UrlTreeService;
import clawer.extractor.Extractor;
import clawer.util.Helper;

@Component
@Qualifier("defaultBookFactory")
public class DefaultBookFactory2 {

	@Autowired
	ImageService imageService;

	@Autowired
	BookService bookService;

	@Autowired
	ChapterService chapterService;

	@Autowired
	UrlTreeService urlTreeService;

	@Autowired
	DataUtils dataUtils;

	private UrlTree urlTree;
	private Extractor extractor;
	private boolean isUpdateUrlTree=true;
	
	private List<String> errors = new ArrayList<>();
	private final int maxError = 5;

	public static DefaultBookFactory2 defaultBookFactory;

	@PostConstruct
	public void init() {
		defaultBookFactory = this;
		defaultBookFactory.imageService = this.imageService;
		defaultBookFactory.bookService = this.bookService;
		defaultBookFactory.chapterService = this.chapterService;
		defaultBookFactory.urlTreeService = this.urlTreeService;
		defaultBookFactory.dataUtils = this.dataUtils;
	}

	public DefaultBookFactory2() {
		
	}
	
	public List<Book> buildBooks() {

		initUrlTree();
		
		// 未处理的book urls
		List<String> notYetHandledBookUrls = DataUtils.differStringList(urlTree.getAllBookUrls(),
				urlTree.getBookUrls());

		List<Book> books = new ArrayList<>();
		// outcome books
		for (String bookurl : notYetHandledBookUrls) {

			// 太多错误或者已经50，终断
			if (this.errors.size() > this.maxError) {
				System.out.println("too many errors,break!");
				errors.forEach(e -> System.out.println(e));
				break;
			}
			if (books.size() >= 50) {
				System.out.println("already handled 50 books,break!");
			}

			
			Element bookPage = Helper.getBody(bookurl,this.extractor.bookPageToolType());
			List<String> chapterUrls = this.extractor.getChapterUrls(bookPage);
			
			// 未找到chapter urls
			if (chapterUrls.isEmpty()) {
				this.errors.add(bookurl + ": " + " noChapterurlFounded!");
				continue;
			}
			String bookName = this.extractor.etrBookName(bookPage);
			if (bookName.isBlank()) {
				this.errors.add(bookurl + ": notFoundedBookName");
				continue;
			}
            if(defaultBookFactory.bookService.existInOtherWebSite(bookName, this.extractor.weibsiteName())) {
            	System.out.println("already exist at other website");
            	continue;
            }
            System.out.println("book.... "+bookName);
			//新建或从数据库获取一个book
			Book book = defaultBookFactory.bookService.findOneBookByUrl(bookurl);
			book.setName(bookName);
			setBook(book, bookPage, this.extractor.weibsiteName(), bookurl);
			defaultBookFactory.bookService.updateBook(book);
			
			//确定未处理的chapters
			List<String> chaptersUlsFromDB = defaultBookFactory.dataUtils.getFielsFromDB("url",
					Criteria.where("id").in(book.getChapterIds()), Chapter.class);
			List<String> notYetHandledChapteUrls = DataUtils.differStringList(chapterUrls, chaptersUlsFromDB);

			// outcome chapters
			long chapterIndex = chaptersUlsFromDB == null ? 0 : chaptersUlsFromDB.size(); // 已完成的chapter最大值
			for (String chapterUrl : notYetHandledChapteUrls) {
				Chapter chapter = new Chapter();
				Element chapterPage = Helper.getBody(chapterUrl,this.extractor.chapterPageToolType());
				List<String> imageUrls = this.extractor.getChapterImageUrls(chapterPage);
				if (imageUrls.isEmpty()) {
					this.errors.add(chapterUrl + ": notFoundedImageurl");
					continue;
				}
				String chapterName = this.extractor.etrChapterName(chapterPage);
				if (chapterName.isBlank()) {
					this.errors.add(chapterUrl + ": notFoundedChapterName");
					continue;
				}
				System.out.println("Chapter.... "+chapterName);
				chapter.setName(chapterName);
				setChapter(chapter, chapterUrl, chapterIndex);
				defaultBookFactory.chapterService.updateChapter(chapter); // 因为images未处理，只更新，也就是不把id放进book；

				// outcome images
				int imageIndex = 0;
				for (String imageUrl : imageUrls) {
					Image image = new Image();
					setImage(book, chapter, image, chapterPage, imageUrl, imageIndex, this.extractor.weibsiteName());
					defaultBookFactory.imageService.addImage(image, chapter);
					imageIndex++;
				}

				defaultBookFactory.chapterService.addChapter(chapter, book);
				chapterIndex++;

			}

			books.add(book);

			// 记录完成的books到urltree
			urlTree.getBookUrls().add(bookurl);
			defaultBookFactory.urlTreeService.addUrlTree(urlTree);

		}
		urlTree.getErrors().addAll(this.errors);
		defaultBookFactory.urlTreeService.addUrlTree(urlTree);
		return books;
	}

	private void setBook(Book book, Element bookPage, String websiteName, String bookurl) {

		String author = this.extractor.etrAuthor(bookPage);
		String brief = this.extractor.etrBrief(bookPage);

		book.setWebSiteName(websiteName);
		book.setUrl(bookurl);
		book.setBrief(brief);
		book.setAuthor(author);
	}

	private void setChapter(Chapter chapter, String chapterUrl, long chapterNum) {
		chapter.setOrderNum(chapterNum);
		chapter.setUrl(chapterUrl);
	}

	private void setImage(Book book, Chapter chapter, Image image, Element chapterPage, String imageUrl, int imageIndex,
			String websiteName) {
		String imageName = String.valueOf(imageIndex) + extSuffix(imageUrl);
		image.setSavePath(generateSavePath(websiteName, book.getName(), chapter.getName(), imageName));
		image.setImageNum(imageIndex);
		image.setName(imageName);
		image.setUrl(imageUrl);
	}

	private String extSuffix(String input) {
		if (input.contains(".jpg") || input.contains(".JPG")) {
			return ".jpg";
		} else if (input.contains(".gif") || input.contains(".GIF")) {
			return ".gif";
		} else if (input.contains(".jpeg") || input.contains(".JPEG")) {
			return ".jpeg";
		}

		return ".nomatch";
	}

	private String generateSavePath(String websiteName, String bookName, String chapterName, String imageName) {

		String savePath = websiteName + "/" + bookName + "/" + chapterName + "/" + imageName;
		return savePath;

	}

	private void updateUrlTreeBookUrlsFromWebsite() {
		List<String> bookUrls = this.extractor.getBookUrls(this.extractor.startUrl());
		this.urlTree.getAllBookUrls().clear();
		this.urlTree.getAllBookUrls().addAll(bookUrls);
		defaultBookFactory.urlTreeService.addUrlTree(urlTree);
	}


	public DefaultBookFactory2 setExtractor(Extractor extractor) {
		this.extractor = extractor;
		return this;
	}

	public DefaultBookFactory2 setIsUpdateUrlTree(boolean isUpdateUrlTree) {
		this.isUpdateUrlTree = isUpdateUrlTree;
		return this;
	}

	private void initUrlTree() {
		this.urlTree = defaultBookFactory.urlTreeService.getByName(this.extractor.weibsiteName());
		if(isUpdateUrlTree) {
			updateUrlTreeBookUrlsFromWebsite();
		}
	}

	
}
