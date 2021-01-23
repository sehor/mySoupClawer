package clawer.extractor;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.nodes.Element;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import clawer.extractor.website.kankan.InfoExtractor_KanKan;
import clawer.util.Helper;

@RestController
@RequestMapping("/extractor")
public class Controller_InfoExtratro {

	
	private final InfoExtractor extractor=new InfoExtractor_KanKan();
	
	@GetMapping("/bookExt")
	public Map<String,String> getBookInfo(@RequestParam(value = "url") String url){
		
		Map<String,String> map=new HashMap<>();
		Element body=Helper.getBodyBySelenium(url);
		map.put("name", extractor.etrName(body));
		map.put("author", extractor.etrAuthor(body));
		map.put("brief", extractor.etrBrief(body));
		map.put("coverImageUrl", extractor.etrCoverImageUrl(body));
	
		return map;
	}
	
	@GetMapping("/chapterExt")
	public Map<String,String> getChapterInfo(@RequestParam(value = "url") String url){
		
		Map<String,String> map=new HashMap<>();
		Element body=Helper.getBody(url);
		map.put("name", extractor.etrChapterName(body));
	
		return map;
	}
	
	@GetMapping("/imageExt")
	public Map<String,String> getImageInfo(@RequestParam(value = "url") String url){
		
		Map<String,String> map=new HashMap<>();
		Element body=Helper.getBodyBySeleniumScroll(url, 50);
        map.put("name", extractor.etrImageName(body));
        map.put("publish date", extractor.etrImagePublishDate(body).toString());
		return map;
	}
}
