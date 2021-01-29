package clawer.domain.image;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

import clawer.domain.chapter.ChapterService;

@RestController
@RequestMapping("/image")
public class ImageController {
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
	public String downloadAndSave() {
        Query query=new Query();
        query.limit(20);
       List<Image> images= operations.find(query, Image.class);
       service.downloadAndSave(images);
		
		return "save done";

	}
	
	@GetMapping("/setFalse")
	public String setFalse() {

        	List<Image> images=service.getAllImage();
        	images.forEach(e->e.setCompleted(false));
            repository.saveAll(images);

		return "save done";

	}

}
