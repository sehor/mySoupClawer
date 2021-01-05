
    package clawer.domain.urlTree;
       import java.util.List;
       import clawer.domain.urlTree.UrlTree;
	public interface UrlTreeService {

	UrlTree addUrlTree(UrlTree urlTree);

	UrlTree getUrlTree(String id);

	UrlTree updateUrlTree(UrlTree urlTree);

	void deleteUrlTree(UrlTree UrlTree);

	void deleteUrlTree(String id);

	List<UrlTree> getAllUrlTree();

}

