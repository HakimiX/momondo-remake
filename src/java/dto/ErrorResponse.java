/**
 * 
 */
package dto;


/**
 * This is the error response class
 * @author 
 *
 */
public class ErrorResponse {

	private int errorCode;
	private int httpCode;
	private String message;
	/**
	 * @return the errorCode
	 */
	public int getErrorCode() {
		return errorCode;
	}
	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	/**
	 * @return the httpCode
	 */
	public int getHttpCode() {
		return httpCode;
	}
	/**
	 * @param httpCode the httpCode to set
	 */
	public void setHttpCode(int httpCode) {
		this.httpCode = httpCode;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
}
