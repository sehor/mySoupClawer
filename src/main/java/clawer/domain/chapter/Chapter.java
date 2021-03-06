package clawer.domain.chapter;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import clawer.domain.other.Cover;

@Document
public class Chapter {

	private String id;
	
	private String bookId;

	private String title;
	
	private List<String> imageId;
	
    private Cover cover;
	
	
	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public List<String> getImageId() {
		return imageId;
	}

	public void setImageId(List<String> imageId) {
		this.imageId = imageId;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public Cover getCover() {
		return cover;
	}

	public void setCover(Cover cover) {
		this.cover = cover;
	}

}
