package id.co.bni.qris.fault;

public class QrisException extends Exception {
	
	Object faultInfo = null;
	String origin = "";
	String errorCode = "";
	String errorMessage = "";
	
	public QrisException() {
		// TODO Auto-generated constructor stub
	}

	public QrisException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public QrisException(String message, String origin, Object faultInfo, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
		this.origin = origin;
	}
	public QrisException(String message, String origin, Object faultInfo) {
		super(message);
		// TODO Auto-generated constructor stub
		this.faultInfo = faultInfo;
		this.origin = origin;
	}
	public QrisException(String message, String origin, String errorCode, String errorMessage, Object faultInfo) {
		super(message);
		// TODO Auto-generated constructor stub
		this.faultInfo = faultInfo;
		this.origin = origin;
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	public Object getFaultInfo()
	{
		return this.faultInfo;
	}

	public String getOrigin() {
		return origin;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

}
