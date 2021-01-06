package clawer.domain.book;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import clawer.domain.other.BackCover;
import clawer.domain.other.ContentPage;
import clawer.domain.other.Cover;


@Document
public class Book {
	
	private String webSiteName;

	private String id;

	private String name;

	private String author;

	private String language;
	
	private String brief;
	
	private LocalDate publishDate;
	
	private Cover cover;
	
	private BackCover backCover;
	
	private ContentPage contentPage;
	
	private List<String> chapterId;
	
	
	

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public Cover getCover() {
		return cover;
	}

	public void setCover(Cover cover) {
		this.cover = cover;
	}

	public BackCover getBackCover() {
		return backCover;
	}

	public void setBackCover(BackCover backCover) {
		this.backCover = backCover;
	}

	public ContentPage getContentPage() {
		return contentPage;
	}

	public void setContentPage(ContentPage contentPage) {
		this.contentPage = contentPage;
	}

	public List<String> getChapterId() {
		return chapterId;
	}

	public void setChapterId(List<String> chapterId) {
		this.chapterId = chapterId;
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

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getAuthor() {
		return author;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getLanguage() {
		return language;
	}

	public LocalDate getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(LocalDate publishDate) {
		this.publishDate = publishDate;
	}

	public String getWebSiteName() {
		return webSiteName;
	}

	public void setWebSiteName(String webSiteName) {
		this.webSiteName = webSiteName;
	}
 
	
}
