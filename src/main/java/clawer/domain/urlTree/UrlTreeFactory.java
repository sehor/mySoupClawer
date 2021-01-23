package clawer.domain.urlTree;

import clawer.extractor.UrlExtractor;

public interface UrlTreeFactory {

	public UrlTree build(UrlExtractor extractor);
	public UrlTreeFactory setSiteName(String websiteName);
	public UrlTreeFactory setEnterUrl(String entUrl);
	
}
