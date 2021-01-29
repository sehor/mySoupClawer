package clawer.domain.urlTree;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import clawer.extractor.UrlExtractor;

@Component
@Qualifier("defaultUrlTreeFactory")
public class DefaultTreeFactory implements UrlTreeFactory {

	@Override
	public UrlTree build(UrlExtractor extractor) {
		// TODO Auto-generated method stub
		UrlTree tree=new UrlTree();

		return tree;
	}



	@Override
	public UrlTreeFactory setSiteName(String websiteName) {
		return this;
	}


	@Override
	public UrlTreeFactory setEnterUrl(String enterUrl) {
		return this;
	}

}
