package clawer.domain.chapter;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import clawer.data.PageModel;
import clawer.domain.book.Book;
import clawer.domain.book.BookService;

@Service
public class ChapterServiceImpl implements ChapterService {
	@Autowired
	ChapterRepository repository;
    @Autowired
    BookService bookService;

    @Autowired
     MongoOperations operations;
    
	@Override
	public Chapter getChapter(String id) {
		return repository.findById(id).get();
	}

	@Override
	public Chapter updateChapter(Chapter chapter) {
		return repository.save(chapter);
	}

	@Override
	public void deleteChapter(Chapter chapter) {
		repository.delete(chapter);
	}

	@Override
	public void deleteChapter(String id) {
		repository.deleteById(id);
	}

	@Override
	public List<Chapter> getAllChapter(){
       Query query=new Query();
       query.limit(50);
       List<Chapter> chapters=operations.find(query, Chapter.class);
	   return chapters;
	}

	@Override
	public Chapter addChapter(Chapter chapter, Book book) {
		// TODO Auto-generated method stub
		Chapter saved=repository.save(chapter);
		book.getChapterIds().add(saved.getId());
		bookService.updateBook(book);
		return saved;
	}

	@Override
	public PageModel<Chapter> getChaptersInPage(String bookId,int pageIndex, int pageSize) {
		// TODO Auto-generated method stub
     Book book=bookService.getBook(bookId);
     List<String> chapterIds=book.getChapterIds();
	 PageModel<Chapter> page=new PageModel<>();
	 page.setPageIndex(pageIndex);
	 page.setPageSize(pageSize);
	 
	  Query query=new Query();
	  query.addCriteria(Criteria.where("id").in(chapterIds));
	  long count=operations.count(query, Chapter.class);
	  page.setTotal(count);
	  query.skip(page.getSkip()).limit(pageSize);
	  List<Chapter> chapters=operations.find(query, Chapter.class);
	  page.getData().addAll(chapters);
	  
		return page;
	}

}

