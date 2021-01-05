package clawer.domain.chapter;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import clawer.domain.chapter.Chapter;

@Service
public class ChapterServiceImpl implements ChapterService {
	@Autowired
	ChapterRepository repository;

	@Override
	public Chapter addChapter(Chapter chapter) {
		return repository.save(chapter);
	}

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
	   return repository.findAll();
	}

}

