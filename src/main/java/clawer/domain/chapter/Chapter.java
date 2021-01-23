package clawer.domain.chapter;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import clawer.domain.other.Cover;

@Document
public class Chapter {

	private String id;

	private String bookName;

	private String bookId;

	private String url;
	private String title;

	private long orderNum;

	private List<String> imageId;

	private Cover cover;

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

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public long getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(long orderNum) {
		this.orderNum = orderNum;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
