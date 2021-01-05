package clawer.domain.book;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {
	@Autowired
	BookRepository repository;

	@Override
	public Book addBook(Book book) {
		return repository.save(book);
	}

	@Override
	public Book getBook(String id) {
		return repository.findById(id).get();
	}

	@Override
	public Book updateBook(Book book) {
		return repository.save(book);
	}

	@Override
	public void deleteBook(Book book) {
		repository.delete(book);
	}

	@Override
	public void deleteBook(String id) {
		repository.deleteById(id);
	}

	@Override
	public List<Book> getAllBook(){
	   return repository.findAll();
	}

}

