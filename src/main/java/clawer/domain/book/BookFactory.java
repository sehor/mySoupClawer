package clawer.domain.book;

import java.util.List;

import clawer.extractor.InfoExtractor;
import clawer.extractor.UrlExtractor;

public interface BookFactory { 
	 List<Book> booksFromWebsite(String websiteName,String statrUrl,InfoExtractor extractor,UrlExtractor urlExtractor);
}
