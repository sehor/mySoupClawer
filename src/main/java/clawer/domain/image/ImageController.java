package clawer.domain.image;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import clawer.domain.image.Image;

@RestController
@RequestMapping("/image")
public class ImageController {
	@Autowired
	ImageService service;

	@PostMapping("/add")
	public Image add(@RequestBody Image image) {

		return service.addImage(image);
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
	public List<Image> getAll(){
	  return service.getAllImage();
	}
}

