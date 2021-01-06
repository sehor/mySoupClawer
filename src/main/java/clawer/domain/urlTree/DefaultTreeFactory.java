package clawer.domain.urlTree;

import java.util.List;

import clawer.extractor.UrlExtractor;

public class DefaultTreeFactory implements UrlTreeFactory {

	private String startUrl;

	public DefaultTreeFactory(String startUrl) {
		this.startUrl = startUrl;
	}


	@Override
	public UrlTree getUrlTree(UrlExtractor extractor) {
		// TODO Auto-generated method stub
		UrlTree tree=new UrlTree();
		tree.setBookUrls(extractor.getBookUrls(this.startUrl));
		
		// add chapter url to bookurls
		for(String url:tree.getBookUrls()) {
			 tree.getChapterUrls().put(url, extractor.getChapterUrls(url));
		}
		
		// add img urls  to chapter urls
		for(List<String> urlList:tree.getChapterUrls().values()) {
			for(String chapterUrl:urlList) {
				 tree.getImageUrls().put(chapterUrl, extractor.getChapterImageUrls(chapterUrl));
			}
		}
		
		
		
		return tree;
	}

}
