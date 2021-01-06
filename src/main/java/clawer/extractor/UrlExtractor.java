package clawer.extractor;

import java.util.List;

public interface UrlExtractor {

	public List<String> getBookUrls(String startUrl);
	public List<String> getChapterUrls(String startUrl);
	public List<String> getChapterImageUrls(String startUrl);
}
