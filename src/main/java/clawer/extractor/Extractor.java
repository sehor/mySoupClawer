package clawer.extractor;

import java.time.LocalDate;
import java.util.List;

import org.jsoup.nodes.Element;

import clawer.util.BookType;

public interface Extractor {
	String bookListPageToolType();
	String bookPageToolType();
	String chapterPageToolType();
	
	String rootUrl();
	String startUrl();
	String weibsiteName();
	
	String etrBookName(Element infoNode);

	String etrAuthor(Element infoNode);

	String etrBrief(Element infoNode);

	String etrCoverImageUrl(Element infoNode);

	String etrChapterName(Element infoNode);

	long etrChapterNum(Element infoNode);

	String etrImageName(Element infoNode);

	LocalDate etrImagePublishDate(Element infoNode);

	public List<String> getBookUrls(String startUrl);

	public List<String> getChapterUrls(Element bookPage);

	public List<String> getChapterImageUrls(Element chapterPage);
	
	BookType bookType();
	
}
