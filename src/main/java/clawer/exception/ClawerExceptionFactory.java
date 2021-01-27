package clawer.exception;

import clawer.domain.book.Book;
import clawer.domain.chapter.Chapter;
import clawer.domain.image.Image;

public class ClawerExceptionFactory {
	

	public static ClawerException bookUrlsNoFound() {		
		ClawerException exception=new ClawerException();
		exception.setExceptionType(ClawerExceptionType.BookUrls_No_Founded);
		return exception;
	}
	
	
	
	
	public static ClawerException chapterUrlsNoFound(Book book) {		
		ClawerException exception=new ClawerException();
		exception.setExceptionType(ClawerExceptionType.ChapterUrls_No_Founded);
		exception.setErrorBeanId(book.getId());
		return exception;
	}
	
	public static ClawerException imageUrlsNoFound(Chapter chapter) {		
		ClawerException exception=new ClawerException();
		exception.setExceptionType(ClawerExceptionType.ImageUrls_No_Founded);
		exception.setErrorBeanId(chapter.getId());
		
		return exception;
	}
	
	
	public static ClawerException bookNameNoFound(Book book) {		
		ClawerException exception=new ClawerException();
		exception.setExceptionType(ClawerExceptionType.BookName_No_Founded);
	    exception.setErrorBeanId(book.getId());
		return exception;
	}
	
	public static ClawerException ChapterNameNoFound(Chapter chapter) {		
		ClawerException exception=new ClawerException();
		exception.setExceptionType(ClawerExceptionType.ChapterName_No_Founded);
        exception.setErrorBeanId(chapter.getId());
		return exception;
	}
	
	
	public static ClawerException ImageDataError(Image image) {		
		ClawerException exception=new ClawerException();
		exception.setExceptionType(ClawerExceptionType.Image_Data_Error);
        exception.setErrorBeanId(image.getId());
		return exception;
	}
	
	public static ClawerException ImageSaveError(Image image) {		
		ClawerException exception=new ClawerException();
		exception.setExceptionType(ClawerExceptionType.Image_Save_Error);
		exception.setErrorBeanId(image.getId());
		return exception;
	}
	
}
