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
	private UrlTree urlTree;

	public DefaultBookFactory(UrlTree urlTree) {
		// TODO Auto-generated constructor stub
		this.urlTree = urlTree;
	}


	@Override
	public List<Book> booksFromWebsite(String websiteName,String statrUrl,InfoExtractor extractor) {
		// TODO Auto-generated method stub
		List<Book> books = new ArrayList<>();
		Helper helper=new Helper();

		// book
		for (String bookUrl : this.urlTree.getBookUrls()) {
            Element bookInfoBody=helper.getBody(bookUrl);
			Book book = new Book();
			book.setAuthor(extractor.etrAuthor(bookInfoBody));
			book.setBrief(extractor.etrBrief(bookInfoBody));
			book.setName(extractor.etrName(bookInfoBody));

			// chapter
			for (String chapterUrl : this.urlTree.getChapterUrls().get(bookUrl)) {

				Chapter chapter = new Chapter();
				Element chapterInfoBody=helper.getBody(chapterUrl);
				chapter.setTitle(extractor.etrChapterName(chapterInfoBody));

				// image
				for (String imageUrl : this.urlTree.getImageUrls().get(chapterUrl)) {

					String imageSavePath = book.getName() + "/" + chapter.getTitle() + "/";
					String imageName = extractor.etrImageName(chapterInfoBody);
					imageName = book.getName() + "_" + chapter.getTitle() + "_" + imageName;
					Image image = new Image();
					image.setOriginUrl(imageUrl);
					image.setName(imageName);
					image.setBookName(book.getName());
					image.setChapterName(chapter.getTitle());
					image.setSavePath(imageSavePath);
                    
				}
			}

		}

		return null;
	}

}
