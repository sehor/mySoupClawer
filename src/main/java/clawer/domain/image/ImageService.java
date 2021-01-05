
    package clawer.domain.image;
       import java.util.List;

import clawer.domain.image.Image;
	public interface ImageService {

	Image addImage(Image image);

	Image getImage(String id);

	Image updateImage(Image image);

	void deleteImage(Image Image);

	void deleteImage(String id);

	List<Image> getAllImage();

}

