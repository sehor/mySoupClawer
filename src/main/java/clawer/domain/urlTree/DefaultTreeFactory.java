package clawer.domain.urlTree;

import java.util.List;

import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import clawer.extractor.UrlExtractor;
import clawer.util.Helper;

@Component
@Qualifier("defaultUrlTreeFactory")
public class DefaultTreeFactory implements UrlTreeFactory {

	private String enterUrl;
	private String websiteName;

	@Override
	public UrlTree build(UrlExtractor extractor) {
		// TODO Auto-generated method stub
		UrlTree tree=new UrlTree();
		tree.setId(tree.getName()+"_urlTree");
		tree.setName(this.websiteName);
		tree.setRootUrl(this.enterUrl);
		tree.setBookUrls(extractor.getBookUrls(this.enterUrl));
		
		// add chapter url to bookurls
		for(String url:tree.getBookUrls()) {
			 Element bookPage=Helper.getBodyBySelenium(url);
			 tree.getChapterUrls().put(url, extractor.getChapterUrls(bookPage));
		}
		
		// add img urls  to chapter urls
		for(List<String> urlList:tree.getChapterUrls().values()) {
			for(String chapterUrl:urlList) {
				 Element chapterPage=Helper.getBodyBySelenium(chapterUrl);
				 tree.getImageUrls().put(chapterUrl, extractor.getChapterImageUrls(chapterPage));
			}
		}
		
		
		
		return tree;
	}



	@Override
	public UrlTreeFactory setSiteName(String websiteName) {
		// TODO Auto-generated method stub
		this.websiteName=websiteName;
		return this;
	}


	@Override
	public UrlTreeFactory setEnterUrl(String enterUrl) {
		// TODO Auto-generated method stub
		this.enterUrl=enterUrl;
		return this;
	}

}
