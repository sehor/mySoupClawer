package clawer.extractor;

import java.util.List;

import org.jsoup.nodes.Element;

public interface UrlExtractor {

	public List<String> getBookUrls(String startUrl);
	public List<String> getChapterUrls(Element bookPage);
	public List<String> getChapterImageUrls(Element chapterPage);
}
