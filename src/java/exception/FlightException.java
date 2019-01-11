/**
 * 
 */
package exception;

import javax.ws.rs.core.Response;

/**
 * This is the custom exception class
 * @author 
 *
 */
public class FlightException extends Exception {

	
	private static final long serialVersionUID = 1L;
	private int errorCode;
	private Response.Status httpCode;
	private String message;
	
	public FlightException(Response.Status httpCode,int errorCode,String message) {
		super(message);
		this.errorCode=errorCode;
		this.httpCode=httpCode;
		this.message=message;
	}

	/**
	 * @return the errorCode
	 */
	public int getErrorCode() {
		return errorCode;
	}

	/**
	 * @return the httpCode
	 */
	public Response.Status getHttpCode() {
		return httpCode;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	
	
}
