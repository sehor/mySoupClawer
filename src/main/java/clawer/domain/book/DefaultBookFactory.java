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
import clawer.data.SaveImage;
import clawer.domain.chapter.Chapter;
import clawer.domain.chapter.ChapterService;
import clawer.domain.image.Image;
import clawer.domain.image.ImageService;
import clawer.domain.urlTree.UrlTree;
import clawer.domain.urlTree.UrlTreeService;
import clawer.extractor.InfoExtractor;
import clawer.extractor.UrlExtractor;
import clawer.util.Helper;

@Component
@Qualifier("defaultBookFactory")
public class DefaultBookFactory implements BookFactory {

	@Autowired
	ImageService imageService;

	@Autowired
	SaveImage saveImage;

	@Autowired
	BookService bookService;

	@Autowired
	ChapterService chapterService;

	@Autowired
	UrlTreeService urlTreeService;

	@Autowired
	DataUtils dataUtils;

	public static DefaultBookFactory defaultBookFactory;
	
	private List<String> errors=new ArrayList<>();
	private final int maxError=5;

	@PostConstruct
	public void init() {
		defaultBookFactory = this;
		defaultBookFactory.imageService = this.imageService;
		defaultBookFactory.saveImage = this.saveImage;
		defaultBookFactory.bookService = this.bookService;
		defaultBookFactory.chapterService = this.chapterService;
		defaultBookFactory.urlTreeService = this.urlTreeService;
		defaultBookFactory.dataUtils = this.dataUtils;
	}

	@Override
	public List<Book> booksFromWebsiteUpdate(String websiteName, String startUrl, InfoExtractor infoExtractor,
			UrlExtractor urlExtractor,boolean updateUrlTree ) {

		UrlTree urlTree = defaultBookFactory.urlTreeService.getByName(websiteName);
		if(updateUrlTree) {
			updateUrlTreeBookUrlsFromWebsite(urlTree, startUrl, urlExtractor);
		}
		List<String> bookUrls = urlTree.getAllBookUrls();
		// 未处理的book urls
		List<String> notYetHandledBookUrls = DataUtils.differStringList(bookUrls, urlTree.getBookUrls());
		
		if(updateUrlTree) {
			return null;
		}
		List<Book> books = new ArrayList<>();
		// outcome books
		top_loop:for (String bookurl : notYetHandledBookUrls) {
			Element bookPage = Helper.getBody(bookurl);

			Book book = defaultBookFactory.bookService.findOneBookByUrl(bookurl);

			List<String> chapterUrls = urlExtractor.getChapterUrls(bookPage);
			//未找到chapter urls
			if(chapterUrls.isEmpty()) {
				this.errors.add(bookurl+": "+" noChapterurlFounded!");
				continue;		
			}
			String bookeName=infoExtractor.etrName(bookPage);
			if(bookeName.isBlank()) {
				this.errors.add(bookurl+": notFoundedBookName");
				continue;
			}
	        book.setName(bookeName);
			setBook(book, bookPage, infoExtractor, websiteName, bookurl);
			defaultBookFactory.bookService.updateBook(book);
			List<String> chaptersUlsFromDB = defaultBookFactory.dataUtils.getFielsFromDB("url",
					Criteria.where("id").in(book.getChapterIds()), Chapter.class);
			List<String> notYetHandledChapteUrls = DataUtils.differStringList(chapterUrls, chaptersUlsFromDB);

			// outcome chapters
			long chapterIndex = chaptersUlsFromDB == null ? 0 : chaptersUlsFromDB.size(); // 已完成的chapter最大值
			for (String chapterUrl : notYetHandledChapteUrls) {

				Chapter chapter = new Chapter();
				Element chapterPage = Helper.getBodyBySelenium(chapterUrl);
				List<String> imageUrls = urlExtractor.getChapterImageUrls(chapterPage);
				if(imageUrls.isEmpty()) {
					this.errors.add(chapterUrl+": notFoundedImageurl");
					continue;
				}
				String chapterName=infoExtractor.etrChapterName(chapterPage);
				if(chapterName.isBlank()) {
					this.errors.add(chapterUrl+": notFoundedChapterName");
					continue;
				}
				chapter.setName(chapterName);	
				setChapter(chapter, chapterUrl, chapterIndex);
				defaultBookFactory.chapterService.updateChapter(chapter); // 因为images未处理，只更新，也就是不把id放进book；
			
				
				// outcome images
				int imageIndex = 0;
				for (String imageUrl : imageUrls) {
					Image image = new Image();
					setImage(book, chapter, image, chapterPage, infoExtractor, imageUrl, imageIndex, websiteName);
					defaultBookFactory.imageService.addImage(image, chapter);
					imageIndex++;
				}

				defaultBookFactory.chapterService.addChapter(chapter, book);
				chapterIndex++;

			}
			
			if(this.errors.size()>this.maxError) {
				 break top_loop;
			}
			books.add(book);

			// 记录完成的books到urltree
			urlTree.getBookUrls().add(bookurl);
			defaultBookFactory.urlTreeService.addUrlTree(urlTree);

		}
		System.out.println(websiteName+" done");
		System.out.println(" errors： ");
		this.errors.forEach(e->System.out.println(e));
		return books;
	}

	private void setBook(Book book, Element bookPage, InfoExtractor infoExtractor, String websiteName, String bookurl) {
		
		String author = infoExtractor.etrAuthor(bookPage);
		String brief = infoExtractor.etrBrief(bookPage);

		book.setWebSiteName(websiteName);
		book.setUrl(bookurl);
		book.setBrief(brief);
		book.setAuthor(author);
	}

	private void setChapter(Chapter chapter, String chapterUrl, long chapterNum) {
		chapter.setOrderNum(chapterNum);
		chapter.setUrl(chapterUrl);
	}

	private void setImage(Book book, Chapter chapter, Image image, Element chapterPage, InfoExtractor infoExtractor,
			String imageUrl, int imageIndex, String websiteName) {
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

		String savePath =websiteName + "/" +bookName + "/" + chapterName
				+ "/" + imageName;
		return savePath;

	}
	
	private void updateUrlTreeBookUrlsFromWebsite(UrlTree urlTree,String url,UrlExtractor urlExtractor){
		List<String> bookUrls=urlExtractor.getBookUrls(url);
		urlTree.setAllBookUrls(bookUrls);
		defaultBookFactory.urlTreeService.addUrlTree(urlTree);
	}

}
