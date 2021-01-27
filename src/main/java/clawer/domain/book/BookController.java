package clawer.domain.book;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Shape;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import clawer.data.DataUtils;
import clawer.domain.chapter.Chapter;
import clawer.extractor.website.kankan.InfoExtractor_KanKan;
import clawer.extractor.website.kankan.KanKanUrlExtractor;

@RestController
@RequestMapping("/book")
public class BookController {
	@Autowired
	BookService service;
	@Autowired
	DataUtils dataUtils;

	@PostMapping("/add")
	public Book add(@RequestBody Book book) {

		return service.addBook(book);
	}

	@GetMapping("/get/{id}")
	public Book getBook(@PathVariable(value = "id") String id) {
		return service.getBook(id);
	}

	@PutMapping("/update")
	public Book update(@RequestBody Book book) {
		return service.updateBook(book);
	}

	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable("id") String id) {

		service.deleteBook(id);
		return "delete Book by id :" + id;
	}

	@GetMapping("/getAll")
	public List<Book> getAll() {

		return service.getAllBook();
	}

	@GetMapping("/bookUrls")
	public List<Object> getBookUrls(@PathParam(value = "websiteName") String websiteName) {

		List<Object> reuslt = dataUtils.getFielsFromDB("chapterIds", Criteria.where("id").regex(".*再度与你.*"),
				Book.class);
		
		List<Object> reuslt2=dataUtils.getFielsFromDB("url", Criteria.where("id").in(reuslt), Chapter.class);

		return reuslt2;
	}
	
	@GetMapping("/updateFromWebsite")
	public List<Book> updatFromWebsite(@PathParam(value="websiteName") String websiteName,@PathParam(value = "url") String url){
		BookFactory factory=new DefaultBookFactory();
		List<Book> books=factory.booksFromWebsiteUpdate(websiteName, url, new InfoExtractor_KanKan(), new KanKanUrlExtractor());
		
		return books;
	}

}
