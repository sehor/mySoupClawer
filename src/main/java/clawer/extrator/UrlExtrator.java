package clawer.extrator;

import java.util.List;

public interface UrlExtrator {

	public List<String> getIntroUrls(String startUrl);
	public List<String> getChapterUrls(String startUrl);
	public List<String> getChapterImageUrls(String startUrl);
}
