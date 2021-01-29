package clawer.domain.image;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import clawer.data.SaveImage;
import clawer.domain.chapter.Chapter;
import clawer.domain.chapter.ChapterService;

@Service
public class ImageServiceImpl implements ImageService {
	@Autowired
	ImageRepository repository;
	@Autowired
	ChapterService chapterService;
	@Autowired
	SaveImage saveImage;
	@Autowired
	MongoOperations operations;

	@Override
	public Image addImage(Image image,Chapter chapter) {
		Image saved=repository.save(image);
		chapter.getImageIds().add(saved.getId());
		chapterService.updateChapter(chapter);
		return saved;
	}

	@Override
	public Image getImage(String id) {
		return repository.findById(id).get();
	}

	@Override
	public Image updateImage(Image image) {
		return repository.save(image);
	}

	@Override
	public void deleteImage(Image image) {
		repository.delete(image);
	}

	@Override
	public void deleteImage(String id) {
		repository.deleteById(id);
	}

	@Override
	public List<Image> getAllImage(){
	   return repository.findAll();
	}

	@Override
	public void downloadAndSave(List<Image> images) {
		// TODO Auto-generated method stub

		for(Image image:images) {
			 saveImage.saveImageToFile(image);
		}
	}

}

