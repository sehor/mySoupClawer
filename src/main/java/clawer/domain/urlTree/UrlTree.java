package clawer.domain.urlTree;

import java.util.List;
import java.util.Map;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class UrlTree {

	private String id;

	private String name;

	private String rootUrl;

	private List<String> bookUrls;

	private Map<String, List<String>> chapterUrls;

	private Map<String, List<String>> imageUrls;

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setRootUrl(String rootUrl) {
		this.rootUrl = rootUrl;
	}

	public String getRootUrl() {
		return rootUrl;
	}

	public List<String> getBookUrls() {
		return bookUrls;
	}

	public void setBookUrls(List<String> bookUrls) {
		this.bookUrls = bookUrls;
	}

	public Map<String, List<String>> getChapterUrls() {
		return chapterUrls;
	}

	public void setChapterUrls(Map<String, List<String>> chapterUrls) {
		this.chapterUrls = chapterUrls;
	}

	public Map<String, List<String>> getImageUrls() {
		return imageUrls;
	}

	public void setImageUrls(Map<String, List<String>> imageUrls) {
		this.imageUrls = imageUrls;
	}
	
	

}
