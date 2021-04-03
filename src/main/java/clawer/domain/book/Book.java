package clawer.domain.book;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import clawer.domain.other.BackCover;
import clawer.domain.other.ContentPage;
import clawer.domain.other.Cover;


@Document
public class Book {
	
	private String webSiteName;
	
    @Id
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
	private List<String> notYetHandledChapterUrls=new ArrayList<>();

	
	private String bookType;
	

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


	public List<String> getChapterIds() {
		return chapterIds;
	}

	public void setChapterIds(List<String> chapterIds) {
		this.chapterIds = chapterIds;
	}

	public List<String> getNotYetHandledChapterUrls() {
		return notYetHandledChapterUrls;
	}

	public void setNotYetHandledChapterUrls(List<String> notYetHandledChapterUrls) {
		this.notYetHandledChapterUrls = notYetHandledChapterUrls;
	}

	public String getBookType() {
		return bookType;
	}

	public void setBookType(String bookType) {
		this.bookType = bookType;
	}

 
  
   	
}
