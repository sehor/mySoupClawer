package clawer.domain.image;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import clawer.domain.image.Image;

@Service
public class ImageServiceImpl implements ImageService {
	@Autowired
	ImageRepository repository;

	@Override
	public Image addImage(Image image) {
		return repository.save(image);
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

}

