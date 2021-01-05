package clawer.domain.image;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Image {

	private String id;
	
	private String chapterId;
	
	private LocalDate publishDate;
	
	private LocalDateTime saveTime;

	private String name;  // save file name, not include suffix

	private String savePath; //relative save path, not include filename
	
	private String suffix; // for example:  the baseDir + savePath + name + suffix=absolute full path
	
	

	public String getChapterId() {
		return chapterId;
	}

	public void setChapterId(String chapterId) {
		this.chapterId = chapterId;
	}

	public LocalDate getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(LocalDate publishDate) {
		this.publishDate = publishDate;
	}

	public LocalDateTime getSaveTime() {
		return saveTime;
	}

	public void setSaveTime(LocalDateTime saveTime) {
		this.saveTime = saveTime;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public String getSavePath() {
		return savePath;
	}

}
