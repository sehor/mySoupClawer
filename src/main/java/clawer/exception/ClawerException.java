package clawer.exception;

public class ClawerException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String websiteName;
    private String errorBeanId;
	private ClawerExceptionType exceptionType;

	public String getWebsiteName() {
		return websiteName;
	}

	public void setWebsiteName(String websiteName) {
		this.websiteName = websiteName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public ClawerExceptionType getExceptionType() {
		return exceptionType;
	}

	public void setExceptionType(ClawerExceptionType exceptionType) {
		this.exceptionType = exceptionType;
	}

	public String getErrorBeanId() {
		return errorBeanId;
	}

	public void setErrorBeanId(String errorBeanId) {
		this.errorBeanId = errorBeanId;
	}
	
	

}
