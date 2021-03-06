
package clawer.domain.book;

import java.util.List;

public interface BookService {

	Book addBook(Book book);

	Book getBook(String id);

	Book updateBook(Book book);

	void deleteBook(Book Book);

	void deleteBook(String id);

	List<Book> getAllBook();

}
