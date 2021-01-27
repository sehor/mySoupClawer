package clawer.domain.book;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import clawer.data.DataUtils;
import clawer.data.SaveImage;
import clawer.domain.chapter.Chapter;
import clawer.domain.chapter.ChapterService;
import clawer.domain.chapter.ChapterServiceImpl;
import clawer.domain.image.Image;
import clawer.domain.image.ImageService;
import clawer.domain.image.ImageServiceImpl;
import clawer.domain.urlTree.UrlTree;
import clawer.domain.urlTree.UrlTreeService;
import clawer.domain.urlTree.UrlTreeServiceImpl;
import clawer.exception.ClawerExceptionFactory;
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
			UrlExtractor urlExtractor) {

		UrlTree urlTree =defaultBookFactory.urlTreeService.getByName(websiteName);

		List<String> bookUrls = urlExtractor.getBookUrls(startUrl);
		if(bookUrls.isEmpty()) {
			 urlTree.getExceptions().add(ClawerExceptionFactory.bookUrlsNoFound());
		}

		// 未处理的book urls
		List<String> notYetHandledBookUrls = DataUtils.differStringList(bookUrls, urlTree.getBookUrls());

		List<Book> books = new ArrayList<>();

		// outcome books
		for (String bookurl : notYetHandledBookUrls) {
			Element bookPage = Helper.getBody(bookurl);
			
			Book book = defaultBookFactory.bookService.findOneBookByUrl(bookurl);
			setBook(book, bookPage, infoExtractor, websiteName, bookurl);
			List<String> chapterUrls = urlExtractor.getChapterUrls(bookPage);
			if (chapterUrls.isEmpty()) {
				urlTree.getExceptions().add(ClawerExceptionFactory.chapterUrlsNoFound(book));
			}
			if (book.getName().isEmpty()) {
				urlTree.getExceptions().add(ClawerExceptionFactory.bookNameNoFound(book));
			}

			List<String> chaptersUlsFromDB = defaultBookFactory.dataUtils.getFielsFromDB("url",
					Criteria.where("id").in(book.getChapterIds()), Chapter.class);
			List<String> notYetHandledChapteUrls=DataUtils.differStringList(chapterUrls, chaptersUlsFromDB);
			
			System.out.println("book: " + book.getName());
			// outcome chapters
			long chapterIndex = chaptersUlsFromDB==null?0:chaptersUlsFromDB.size(); //已完成的chapter最大值
			for (String chapterUrl : notYetHandledChapteUrls) {

				Chapter chapter = new Chapter();
				Element chapterPage = Helper.getBodyBySelenium(chapterUrl);

				List<String> imageUrls = urlExtractor.getChapterImageUrls(chapterPage);
				setChapter(book, chapter, chapterPage, infoExtractor, chapterUrl, chapterIndex);

				System.out.println("chapter: " + chapter.getName());
				if (imageUrls.isEmpty()) {
					urlTree.getExceptions().add(ClawerExceptionFactory.imageUrlsNoFound(chapter));
				}
				if (chapter.getName().isEmpty()) {
					urlTree.getExceptions().add(ClawerExceptionFactory.ChapterNameNoFound(chapter));
				}
				urlTree.getChapterUrls().put(chapterUrl.replaceAll("\\.", "<->"), imageUrls);

				// outcome images
				int imageIndex = 0;
				for (String imageUrl : imageUrls) {
					Image image = new Image();
					setImage(book, chapter, image, chapterPage, infoExtractor, imageUrl, imageIndex, websiteName);
					chapter.getImageIds().add(image.getId());
					defaultBookFactory.imageService.addImage(image);
					imageIndex++;
				}

				defaultBookFactory.chapterService.addChapter(chapter);
				chapterIndex++;

				book.getChapterIds().add(chapter.getId());
				defaultBookFactory.bookService.addBook(book); // 每完成一章都存一次book
			}

			books.add(book);

			// 记录完成的books到urltree
			urlTree.getBookUrls().add(bookurl);
			defaultBookFactory.urlTreeService.addUrlTree(urlTree);
		}

		return books;
	}

	private void setBook(Book book, Element bookPage, InfoExtractor infoExtractor, String websiteName, String bookurl) {
		String bookName = infoExtractor.etrName(bookPage);
		String bookId = websiteName + "_" + bookName;
		String author = infoExtractor.etrAuthor(bookPage);
		String brief = infoExtractor.etrBrief(bookPage);

		book.setWebSiteName(websiteName);
		book.setUrl(bookurl);
		book.setId(bookId);
		book.setName(bookName);
		book.setBrief(brief);
		book.setAuthor(author);
	}

	private void setChapter(Book book, Chapter chapter, Element chapterPage, InfoExtractor infoExtractor,
			String chapterUrl, long chapterNum) {
		String chapterTitle = infoExtractor.etrChapterName(chapterPage);
		String chapterId = book.getId() + "_" + chapterTitle;
		chapter.setOrderNum(chapterNum);
		chapter.setUrl(chapterUrl);
		chapter.setBookId(book.getId());
		chapter.setBookName(book.getName());
		chapter.setId(chapterId);
		chapter.setName(chapterTitle);
	}

	private void setImage(Book book, Chapter chapter, Image image, Element chapterPage, InfoExtractor infoExtractor,
			String imageUrl, int imageIndex, String websiteName) {

		String imageName = String.valueOf(imageIndex) + "_"
				+ imageUrl.substring(imageUrl.length() - 6, imageUrl.length() - 1);
		String imageId = chapter.getId() + "_" + String.valueOf(imageIndex) + "_"
				+ imageUrl.substring(imageUrl.length() - 6, imageUrl.length() - 1);
		String savePath = Helper.Base_Save_Path + "/" + websiteName + "/" + book.getName() + "/" + chapter.getName()
				+ "/" + imageName;

		image.setImageNum(imageIndex);
		image.setName(imageName);
		image.setSavePath(savePath);
		image.setId(imageId);
		image.setBookName(book.getName());
		image.setChapterId(chapter.getId());
		image.setChapterName(chapter.getName());
		image.setUrl(imageUrl);
	}
}
