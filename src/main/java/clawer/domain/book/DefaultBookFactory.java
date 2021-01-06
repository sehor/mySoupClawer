package clawer.domain.book;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import clawer.data.SaveImage;
import clawer.domain.chapter.Chapter;
import clawer.domain.chapter.ChapterService;
import clawer.domain.image.Image;
import clawer.domain.image.ImageService;
import clawer.domain.urlTree.UrlTree;
import clawer.extrator.InfoExtrator;

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
	public List<Book> booksFromWebsite(String websiteName,String statrUrl,InfoExtrator extrator) {
		// TODO Auto-generated method stub
		List<Book> books = new ArrayList<>();

		// book
		for (String bookUrl : this.urlTree.getBookUrls()) {

			Book book = new Book();
			book.setAuthor(extrator.etrAuthor(bookUrl));
			book.setBrief(extrator.etrBrief(bookUrl));
			book.setName(extrator.etrName(bookUrl));

			// chapter
			for (String chapterUrl : this.urlTree.getChapterUrls().get(bookUrl)) {

				Chapter chapter = new Chapter();

				chapter.setTitle(extrator.etrChapterName(chapterUrl));

				// image
				for (String imageUrl : this.urlTree.getImageUrls().get(chapterUrl)) {

					String imageSavePath = book.getName() + "/" + chapter.getTitle() + "/";
					String imageName = extrator.etrImageName(imageUrl);
					imageName = book.getName() + "_" + chapter.getTitle() + "_" + imageName;
					Image image = new Image();
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