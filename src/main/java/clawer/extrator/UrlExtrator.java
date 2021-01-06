package clawer.extrator;

import java.util.List;

public interface UrlExtrator {

	public List<String> getBookUrls(String startUrl);
	public List<String> getChapterUrls(String startUrl);
	public List<String> getChapterImageUrls(String startUrl);
}
