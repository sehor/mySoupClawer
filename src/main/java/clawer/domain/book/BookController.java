package clawer.domain.book;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import clawer.extractor.InfoExtractor;
import clawer.extractor.UrlExtractor;
import clawer.extractor.website.kankan.InfoExtractor_KanKan;
import clawer.extractor.website.kankan.KanKanUrlExtractor;


@RestController
@RequestMapping("/book")
public class BookController {
	@Autowired
	BookService service;

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
	public List<Book> getAll(){
	  UrlExtractor urlExtractor=new KanKanUrlExtractor();
	  InfoExtractor infoExtractor=new InfoExtractor_KanKan();
	  
	  BookFactory factory=new DefaultBookFactory();	
	  List<Book> books=factory.booksFromWebsite("KanKan", "https://www.kuaikanmanhua.com/tag/0", infoExtractor, urlExtractor);
		
	  return books;
	}
}

