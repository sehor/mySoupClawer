package clawer.domain.chapter;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import clawer.domain.other.Cover;

@Document
public class Chapter {
    @Id
	private String id;

	private String url;
	private String name;

	private long orderNum;

	private List<String> imageIds=new ArrayList<>();

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
	 
}
