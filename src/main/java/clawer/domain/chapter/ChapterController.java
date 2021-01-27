package clawer.domain.chapter;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/chapter")
public class ChapterController {
	@Autowired
	ChapterRepository repository;
	
	@Autowired
	ChapterService service;

	@PostMapping("/add")
	public Chapter add(@RequestBody Chapter chapter) {

		return service.addChapter(chapter);
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

	@GetMapping("/getAll")
	public List<Chapter> getAll(){

		return service.getAllChapter();
	  
	}
}

