package clawer.domain.book;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import clawer.data.PageModel;

@Service
public class BookServiceImpl implements BookService {
	@Autowired
	BookRepository repository;
	@Autowired
	MongoOperations options;

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

	@Override
	public int getBookLastIndex(String webSiteName) {
		// TODO Auto-generated method stub
		return repository.findByWebSiteName(webSiteName).size()-1;
	}

	@Override
	public List<String> getBookUrlsByWebsite(String websiteName) {
		// TODO Auto-generated method stub
		
		Query query=new Query();
		query.addCriteria(Criteria.where("webSiteName").is(websiteName));
		query.fields().include("url");
		List<Book> books=options.find(query, Book.class);
		List<String> urls=new ArrayList<>();
		for(Book b:books) {
			urls.add(b.getUrl());
		}
		return urls;
	}

	@Override
	public Book findOneBookByUrl(String url) {
		// TODO Auto-generated method stub
		List<Book> books=repository.findByUrl(url);
		if(books==null||books.isEmpty()) {
			Book book=new Book();
			book.setUrl(url);
			return book;
		}
		return books.get(0);
	}

	@Override
	public boolean existInOtherWebSite(String bookName,String websiteName) {
		// TODO Auto-generated method stub
		List<Book> books=repository.findByName(bookName);
		for(Book book:books) {
			if(!book.getWebSiteName().equals(websiteName)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public List<Book> getBookByName(String bookName) {
		// TODO Auto-generated method stub
		List<Book> books=repository.findByName(bookName);
		return books;
	}

	@Override
	public PageModel<Book> getBookByPage(int pageIndex, int pageSize) {
		// TODO Auto-generated method stub
		Query query=new Query();
		PageModel<Book> page=new PageModel<>();
		page.setPageIndex(pageIndex);
		page.setPageSize(pageSize);
		long count=options.count(query, Book.class);
		page.setTotal(count);
		
		query.skip(page.getSkip()).limit(pageSize);
		query.fields().include("webSiteName").include("name").include("author").include("url").include("chapterIds");
		
		List<Book> books=options.find(query, Book.class);
		System.out.println(books.get(0).getChapterIds().size());
		page.getData().addAll(books);
		
		return page;
	}

}

