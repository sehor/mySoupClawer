package clawer.exception;

public enum ClawerExceptionType {

	BookUrls_No_Founded("BookUrls_No_Founded"), ChapterUrls_No_Founded("ChapterUrls_No_Founded"),
	ImageUrls_No_Founded("ImageUrls_No_Founded"), BookName_No_Founded("BookName_No_Founded"),
	ChapterName_No_Founded("ChapterName_No_Founded"), Image_Data_Error("Image_Data_Error"),
	Image_Save_Error("Image_Save_Error"), Break_Error("Break_Error");

	public final String value;

	private ClawerExceptionType(String value) {
		this.value = value;
	}
}
