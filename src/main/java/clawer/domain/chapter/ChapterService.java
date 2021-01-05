
    package clawer.domain.chapter;
       import java.util.List;

import clawer.domain.chapter.Chapter;
	public interface ChapterService {

	Chapter addChapter(Chapter chapter);

	Chapter getChapter(String id);

	Chapter updateChapter(Chapter chapter);

	void deleteChapter(Chapter Chapter);

	void deleteChapter(String id);

	List<Chapter> getAllChapter();

}

