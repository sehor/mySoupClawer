package clawer.domain.image;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

import clawer.data.DataUtils;
import clawer.data.SaveImage;
import clawer.domain.book.Book;
import clawer.domain.book.BookService;
import clawer.domain.chapter.Chapter;
import clawer.domain.chapter.ChapterService;

@RestController
@RequestMapping("/image")
public class ImageController {
	@Autowired
	BookService bookService;
	@Autowired
	ImageService service;
	@Autowired
	ChapterService chapterService;
	@Autowired
	ImageRepository repository;
	@Autowired
	MongoOperations operations;

	@PostMapping("/add")
	public Image add(@RequestBody Image image, @PathParam(value = "chapterId") String chapterId) {

		return service.addImage(image, chapterService.getChapter(chapterId));
	}

	@GetMapping("/get/{id}")
	public Image getImage(@PathVariable(value = "id") String id) {
		return service.getImage(id);
	}

	@PutMapping("/update")
	public Image update(@RequestBody Image image) {
		return service.updateImage(image);
	}

	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable("id") String id) {

		service.deleteImage(id);
		return "delete Image by id :" + id;
	}

	@GetMapping("/getAll")
	public List<Image> getAll() {
		return service.getAllImage();
	}

	@GetMapping("/dowloadAndSave")
	public String downloadAndSave(@PathParam(value = "bookName") String bookName) {

		List<Book> books = bookService.getBookByName(bookName);
		for (Book book : books) {
			Query query = new Query();
			query.addCriteria(Criteria.where("id").in(book.getChapterIds()));
			List<Chapter> chapters = operations.find(query, Chapter.class);

			for (Chapter chapter : chapters) {

				Query query1 = new Query();
				query1.addCriteria(Criteria.where("id").in(chapter.getImageIds()));
				List<Image> images = operations.find(query1, Image.class);

				System.out.println("image numb : " + images.size());

				service.downloadAndSave(images);

			}

		}

		return "save done";

	}

	@GetMapping("/getChapterImage")
	public List<Image> getChaImages(@PathParam(value = "chapterName") String chapterId) {

		List<Image> images = new ArrayList<>();
		Chapter chapter = chapterService.getChapter(chapterId);

		List<Image> imgs = operations.find(new Query().addCriteria(Criteria.where("id").in(chapter.getImageIds())),
				Image.class);
		images.addAll(imgs);

		return images;
	}

}
