package clawer.domain.urlTree;

import java.util.List;

public interface UrlTreeFactory {


	public List<String> getBookUrls(String entryUrl);
	public List<String> getChapterUrls(String entryUrl);
	public List<String> getChapterImageUrls(String entryUrl);
	public UrlTree getUrlTree();
}
