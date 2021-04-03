
    package clawer.domain.chapter;
       import java.util.List;

import org.springframework.data.domain.PageImpl;

import clawer.data.PageModel;
import clawer.domain.book.Book;
	public interface ChapterService {

	Chapter addChapter(Chapter chapter,Book book);

	Chapter getChapter(String id);

	Chapter updateChapter(Chapter chapter);

	void deleteChapter(Chapter Chapter);

	void deleteChapter(String id);

	List<Chapter> getAllChapter();
	
	PageModel<Chapter> getChaptersInPage(String bookId,int pageIndex,int pageSize);

}

