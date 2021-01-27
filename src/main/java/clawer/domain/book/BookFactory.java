package clawer.domain.book;

import java.util.List;

import clawer.domain.urlTree.UrlTree;
import clawer.extractor.InfoExtractor;
import clawer.extractor.UrlExtractor;

public interface BookFactory { 
	
	 List<Book>booksFromWebsiteUpdate(String websiteName, String startUrl, InfoExtractor infoExtractor,
				UrlExtractor urlExtractor);
	
}
