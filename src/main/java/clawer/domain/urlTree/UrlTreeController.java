package clawer.domain.urlTree;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import clawer.domain.urlTree.UrlTree;

@RestController
@RequestMapping("/urlTree")
public class UrlTreeController {
	@Autowired
	UrlTreeService service;

	@PostMapping("/add")
	public UrlTree add(@RequestBody UrlTree urlTree) {

		return service.addUrlTree(urlTree);
	}

	@GetMapping("/get/{id}")
	public UrlTree getUrlTree(@PathVariable(value = "id") String id) {
		return service.getUrlTree(id);
	}

	@PutMapping("/update")
	public UrlTree update(@RequestBody UrlTree urlTree) {
		return service.updateUrlTree(urlTree);
	}

	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable("id") String id) {

		service.deleteUrlTree(id);
		return "delete UrlTree by id :" + id;
	}

	@GetMapping("/getAll")
	public List<UrlTree> getAll(){
	  return service.getAllUrlTree();
	}
}

