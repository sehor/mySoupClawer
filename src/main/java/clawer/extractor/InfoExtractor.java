package clawer.extractor;

import java.time.LocalDate;

import org.jsoup.nodes.Element;

public interface InfoExtractor {

	
	 
	 String etrName(Element infoNode);
	 String etrAuthor(Element infoNode);
	 String etrBrief(Element infoNode);
	 String etrCoverImageUrl(Element infoNode);
	 
	 String etrChapterName(Element infoNode);
	 long etrChapterNum(Element infoNode);
 
	 String etrImageName(Element infoNode);
	 LocalDate etrImagePublishDate(Element infoNode);
}
