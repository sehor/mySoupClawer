package clawer.domain.chapter;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.web.bind.annotation.*;

import clawer.data.PageModel;
import clawer.domain.book.BookService;


@RestController
@RequestMapping("/chapter")
public class ChapterController {
	@Autowired
	ChapterRepository repository;
	
	@Autowired
	ChapterService service;
	@Autowired
	BookService bookservice;

	@PostMapping("/add")
	public Chapter add(@RequestBody Chapter chapter,@PathParam(value = "bookId") String bookId) {
       
		return service.addChapter(chapter,bookservice.getBook(bookId));
	}

	@GetMapping("/get/{id}")
	public Chapter getChapter(@PathVariable(value = "id") String id) {
		return service.getChapter(id);
	}

	@PutMapping("/update")
	public Chapter update(@RequestBody Chapter chapter) {
		return service.updateChapter(chapter);
	}

	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable("id") String id) {

		service.deleteChapter(id);
		return "delete Chapter by id :" + id;
	}

	@GetMapping("/getAll/page")
	public PageModel<Chapter> getAll(@PathParam(value = "bookId")String bookId,@PathParam(value = "pageIndex") int pageIndex,@PathParam(value = "pageSize") int pageSize){

		
		return service.getChaptersInPage(bookId,pageIndex, pageSize);
	  
	}
	
	
	
	
	
}

