package clawer.domain.urlTree;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.websocket.server.PathParam;

import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import clawer.extractor.UrlExtractor;
import clawer.extractor.website.kankan.KanKanUrlExtractor;
import clawer.util.Helper;

@RestController
@RequestMapping("/urlTree")
public class UrlTreeController {
	@Autowired
	UrlTreeService service;

	@Autowired
	@Qualifier("defaultUrlTreeFactory")
	UrlTreeFactory factory;

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
	public List<UrlTree> getAll() {


		return service.getAllUrlTree();

	}

	@GetMapping("/getBookUrls")
	public List<String> getBookUrls() {
		UrlExtractor urlExtractor = new KanKanUrlExtractor();
		String startUrl = "https://www.kuaikanmanhua.com/tag/0?state=1&sort=1&page=35";

		List<String> urls = urlExtractor.getBookUrls(startUrl);
		return urls;
	}

	@GetMapping("/getChapterUrl")
	public Map<String, List<String>> getChapterUrls(@PathParam(value = "url") String url) {
		UrlExtractor urlExtractor = new KanKanUrlExtractor();
        Element el=Helper.getBodyBySelenium(url);
		Map<String, List<String>> map = new HashMap<>();

		
		map.put(url, urlExtractor.getChapterUrls(el));

		return map;
	}

	@GetMapping("/getImageUrl")
	public Map<String, List<String>> getImageUrls(@PathParam(value = "url") String url) {
		Element el=Helper.getBodyBySelenium(url);
		UrlExtractor urlExtractor = new KanKanUrlExtractor();
		Map<String, List<String>> map = new HashMap<>();
		List<String> imagesUrls = urlExtractor.getChapterImageUrls(el);
		map.put(url, imagesUrls);
		return map;
	}
}
