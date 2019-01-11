/**
 * 
 */
package service;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;

import com.flightapi.dto.ErrorResponse;
import com.flightapi.exception.FlightException;

/**
 * This is the class used to handle exception occured in the application
 * @author 
 *
 */
@Provider
public class ExceptionHttpStatusResolver implements
        ExceptionMapper<Throwable> {
	
	final static Logger logger = Logger.getLogger(ExceptionHttpStatusResolver.class);
 
    @Override
    public Response toResponse(Throwable exception) {
    	logger.error("Exception:"+exception);
        Response.Status httpStatus = Response.Status.INTERNAL_SERVER_ERROR;
 
        ErrorResponse errorResponse=new ErrorResponse();
        if (exception instanceof FlightException){
        	FlightException fe=(FlightException)exception;
            httpStatus = fe.getHttpCode();
            errorResponse.setHttpCode(httpStatus.getStatusCode());
            errorResponse.setErrorCode(fe.getErrorCode());
            errorResponse.setMessage(fe.getMessage());
        }else{
            errorResponse.setHttpCode(httpStatus.getStatusCode());
            errorResponse.setErrorCode(4);
            errorResponse.setMessage("Unknown error has occured ");
        }
 
        return Response.status(httpStatus).entity(errorResponse)
                .build();
    }

	
}
