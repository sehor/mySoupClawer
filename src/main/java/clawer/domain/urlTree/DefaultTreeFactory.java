package clawer.domain.urlTree;

import java.util.List;

import clawer.extrator.UrlExtrator;

public class DefaultTreeFactory implements UrlTreeFactory {

	private String startUrl;

	public DefaultTreeFactory(String startUrl) {
		this.startUrl = startUrl;
	}


	@Override
	public UrlTree getUrlTree(UrlExtrator extrator) {
		// TODO Auto-generated method stub
		UrlTree tree=new UrlTree();
		tree.setBookUrls(extrator.getBookUrls(this.startUrl));
		
		// add chapter url to bookurls
		for(String url:tree.getBookUrls()) {
			 tree.getChapterUrls().put(url, extrator.getChapterUrls(url));
		}
		
		// add img urls  to chapter urls
		for(List<String> urlList:tree.getChapterUrls().values()) {
			for(String chapterUrl:urlList) {
				 tree.getImageUrls().put(chapterUrl, extrator.getChapterImageUrls(chapterUrl));
			}
		}
		
		
		
		return tree;
	}

}
