package clawer.domain.urlTree;

import java.util.List;

public interface UrlTreeFactory {


	public List<String> getBookUrls();
	public List<String> getChapterUrls();
	public List<String> getChapterImageUrls();
	public UrlTree getUrlTree();
}
