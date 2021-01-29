
    package clawer.domain.image;
       import java.util.List;

import clawer.domain.chapter.Chapter;
	public interface ImageService {

	Image addImage(Image image,Chapter chapter);

	Image getImage(String id);

	Image updateImage(Image image);

	void deleteImage(Image Image);

	void deleteImage(String id);

	List<Image> getAllImage();
	
	void downloadAndSave(List<Image> images);

}

