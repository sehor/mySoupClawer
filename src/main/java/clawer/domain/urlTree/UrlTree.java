package clawer.domain.urlTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import clawer.exception.ClawerException;
import clawer.exception.ClawerExceptionType;

@Component
@Document
public class UrlTree {

	private String id;

	private String name;

	private String rootUrl;

	private List<String> bookUrls=new ArrayList<>();
	private List<String> notYetHandledBookUrls=new ArrayList<>();

	private Map<String, List<String>> chapterUrls=new HashMap<>();

	private Map<String, List<String>> imageUrls=new HashMap<>();
	
	private List<ClawerException> exceptions=new ArrayList<>();
	
	private int lastBookIndx;
	private long lastChapterIndx;
	private int lastImageIndex;

	
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

	public void setRootUrl(String rootUrl) {
		this.rootUrl = rootUrl;
	}

	public String getRootUrl() {
		return rootUrl;
	}

	public List<String> getBookUrls() {
		return bookUrls;
	}

	public void setBookUrls(List<String> bookUrls) {
		this.bookUrls = bookUrls;
	}

	public Map<String, List<String>> getChapterUrls() {
		return chapterUrls;
	}

	public void setChapterUrls(Map<String, List<String>> chapterUrls) {
		this.chapterUrls = chapterUrls;
	}

	public Map<String, List<String>> getImageUrls() {
		return imageUrls;
	}

	public void setImageUrls(Map<String, List<String>> imageUrls) {
		this.imageUrls = imageUrls;
	}

	public List<ClawerException> getExceptions() {
		return exceptions;
	}

	public void setExceptions(List<ClawerException> exceptions) {
		this.exceptions = exceptions;
	}

	public int getLastBookIndx() {
		return lastBookIndx;
	}

	public void setLastBookIndx(int lastBookIndx) {
		this.lastBookIndx = lastBookIndx;
	}



	public int getLastImageIndex() {
		return lastImageIndex;
	}

	public void setLastImageIndex(int lastImageIndex) {
		this.lastImageIndex = lastImageIndex;
	}

	public long getLastChapterIndx() {
		return lastChapterIndx;
	}

	public void setLastChapterIndx(long lastChapterIndx) {
		this.lastChapterIndx = lastChapterIndx;
	}

	public List<String> getNotYetHandledBookUrls() {
		return notYetHandledBookUrls;
	}

	public void setNotYetHandledBookUrls(List<String> notYetHandledBookUrls) {
		this.notYetHandledBookUrls = notYetHandledBookUrls;
	}



}
