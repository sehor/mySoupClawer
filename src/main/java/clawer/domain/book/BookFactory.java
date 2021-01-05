package clawer.domain.book;

import java.util.List;

public interface BookFactory {
 
	 String etrName(String entyUrl);
	 String etrAuthor(String entyUrl);
	 String etrBrief(String entyUrl);
	 String etrChapterName(String entyUrl);
	 String etrImageName(String entyUrl);
	 
	 List<Book> books();
}
