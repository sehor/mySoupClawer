package clawer.domain.book;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import clawer.data.SaveImage;
import clawer.domain.chapter.Chapter;
import clawer.domain.chapter.ChapterService;
import clawer.domain.image.Image;
import clawer.domain.image.ImageService;
import clawer.domain.urlTree.UrlTree;
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


	
	@Override
	public List<Book> booksFromWebsite(String websiteName, String startUrl, InfoExtractor infoExtractor,
			UrlExtractor urlExtractor) {

		List<String> bookUrls = urlExtractor.getBookUrls(startUrl);
		List<Book> books = new ArrayList<>();

		// outcome books
		for (String bookurl : bookUrls) {

			Book book = new Book();
			Element bookPage = Helper.getBody(bookurl);
			List<String> chapterUrls = urlExtractor.getChapterUrls(bookPage);

			String bookName = infoExtractor.etrName(bookPage);
			String bookId = websiteName + "_" + bookName;
			String author=infoExtractor.etrAuthor(bookPage);
			String brief=infoExtractor.etrBrief(bookPage);
			
			book.setWebSiteName(websiteName);
			book.setUrl(startUrl);
			book.setId(bookId);
			book.setName(bookName);
			book.setBrief(brief);
			book.setAuthor(author);

			System.out.println("book: "+book.getName());
			// outcome chapters
			for (String chapterUrl : chapterUrls) {

				Chapter chapter = new Chapter();
				Element chapterPage = Helper.getBodyBySelenium(chapterUrl);
				List<String> imageUrls = urlExtractor.getChapterImageUrls(chapterPage);

				String chapterTitle = infoExtractor.etrChapterName(chapterPage);
				long chapterNum = infoExtractor.etrChapterNum(chapterPage);
				String chapterId = book.getId() + "_" + chapterTitle;

				chapter.setUrl(chapterUrl);
				chapter.setOrderNum(chapterNum);
				chapter.setBookId(book.getId());
				chapter.setBookName(book.getName());
				chapter.setId(chapterId);
				chapter.setName(chapterTitle);

				book.getChapterIds().add(chapter.getId());
				book.getChapterIdToUrl().put(chapter.getId(), chapter.getUrl());
				System.out.println("chapter: "+chapter.getName());
				
				// outcome images
				int imageNum = 1;
				for (String imageUrl : imageUrls) {
					Image image = new Image();
					image.setImageNum(imageNum);
					String imageName=String.valueOf(imageNum)+"_"+imageUrl.substring(imageUrl.length() - 6, imageUrl.length() - 1);
					String imageId = chapterId + "_" + String.valueOf(imageNum) + "_"
							+ imageUrl.substring(imageUrl.length() - 6, imageUrl.length() - 1);
					String savePath = Helper.Base_Save_Path + "/" + websiteName + "/" + bookName + "/" + chapterTitle
							+ "/" +imageName;
					
				
					image.setName(imageName);
                    image.setSavePath(savePath);
					image.setId(imageId);
					image.setBookName(book.getName());
					image.setChapterId(chapter.getId());
					image.setChapterName(chapter.getName());
					image.setUrl(imageUrl);
					imageNum++;

					// helper.saveImage(image,savepath).then() =>;
					chapter.getImageIds().add(imageId);
					chapter.getImageIdToUrl().put(image.getId(), image.getUrl());

					
				}
				

			}

			books.add(book);

		}

		return books;
	}


}
