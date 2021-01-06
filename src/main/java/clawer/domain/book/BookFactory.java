package clawer.domain.book;

import java.util.List;

import clawer.extractor.InfoExtractor;

public interface BookFactory { 
	 List<Book> booksFromWebsite(String websiteName,String statrUrl,InfoExtractor extractor);
}
