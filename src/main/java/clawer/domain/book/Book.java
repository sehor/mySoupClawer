package clawer.domain.book;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.mongodb.core.mapping.Document;

import clawer.domain.other.BackCover;
import clawer.domain.other.ContentPage;
import clawer.domain.other.Cover;


@Document
public class Book {
	
	private String webSiteName;

	private String id;

	private String name;
	
	private String url;

	private String author;

	private String language;
	
	private String brief;
	
	private LocalDate publishDate;
	
	private Cover cover;
	
	private BackCover backCover;
	
	private ContentPage contentPage;
	
	private List<String> chapterIds=new ArrayList<>();
	
	private Map<String,String> chapterIdToUrl=new HashMap<>();
	
	
	

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Map<String, String> getChapterIdToUrl() {
		return chapterIdToUrl;
	}

	public void setChapterIdToUrl(Map<String, String> chapterIdToUrl) {
		this.chapterIdToUrl = chapterIdToUrl;
	}

	public List<String> getChapterIds() {
		return chapterIds;
	}

	public void setChapterIds(List<String> chapterIds) {
		this.chapterIds = chapterIds;
	}

  
   	
}
