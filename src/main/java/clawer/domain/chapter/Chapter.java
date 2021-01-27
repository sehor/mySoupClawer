package clawer.domain.chapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.attribute.HashAttributeSet;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import clawer.domain.other.Cover;

@Document
public class Chapter {
    @Id
	private String id;

	private String bookName;

	private String bookId;

	private String url;
	private String name;

	private long orderNum;

	private List<String> imageIds=new ArrayList<>();
	private List<String> notYetHandledImageUrls=new ArrayList<>();
	
	private Cover cover;



	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
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

 

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public List<String> getImageIds() {
		return imageIds;
	}

	public void setImageIds(List<String> imageIds) {
		this.imageIds = imageIds;
	}


	public List<String> getNotYetHandledImageUrls() {
		return notYetHandledImageUrls;
	}

	public void setNotYetHandledImageUrls(List<String> notYetHandledImageUrls) {
		this.notYetHandledImageUrls = notYetHandledImageUrls;
	}

	 
}
