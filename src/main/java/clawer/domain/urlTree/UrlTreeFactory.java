package clawer.domain.urlTree;

import clawer.extractor.UrlExtractor;

public interface UrlTreeFactory {

	public UrlTree getUrlTree(UrlExtractor extractor);
}
