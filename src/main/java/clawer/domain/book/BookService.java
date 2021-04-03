
package clawer.domain.book;

import java.util.List;

import clawer.data.PageModel;

public interface BookService {

	Book addBook(Book book);

	Book getBook(String id);

	Book updateBook(Book book);

	List<Book> getBookByName(String bookName);
	void deleteBook(Book Book);

	void deleteBook(String id);

	List<Book> getAllBook();
	
	int getBookLastIndex(String webSitName);

	List<String> getBookUrlsByWebsite(String websiteName);
	
	Book findOneBookByUrl(String url);
	
	boolean existInOtherWebSite(String bookName,String websiteName);

	PageModel<Book> getBookByPage(int pageIndex, int pageSize);
}
