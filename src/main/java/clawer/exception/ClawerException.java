package clawer.exception;

import java.util.ArrayList;
import java.util.List;

public class ClawerException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String websiteName;
	private int bookIndex;
	private int chapterIndex;
	private int imageIndex;
	private List<String> errType = new ArrayList<>();

	public String getWebsiteName() {
		return websiteName;
	}

	public void setWebsiteName(String websiteName) {
		this.websiteName = websiteName;
	}

	public int getBookIndex() {
		return bookIndex;
	}

	public void setBookIndex(int bookIndex) {
		this.bookIndex = bookIndex;
	}

	public int getChapterIndex() {
		return chapterIndex;
	}

	public void setChapterIndex(int chapterIndex) {
		this.chapterIndex = chapterIndex;
	}

	public int getImageIndex() {
		return imageIndex;
	}

	public void setImageIndex(int imageIndex) {
		this.imageIndex = imageIndex;
	}

	public List<String> getErrType() {
		return errType;
	}

	public void setErrType(List<String> errType) {
		this.errType = errType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
