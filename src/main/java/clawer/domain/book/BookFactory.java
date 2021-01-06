package clawer.domain.book;

import java.util.List;

import clawer.extrator.InfoExtrator;

public interface BookFactory { 
	 List<Book> booksFromWebsite(String websiteName,String statrUrl,InfoExtrator extrator);
}
