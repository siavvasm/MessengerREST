package com.miltos.tutorials.messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.miltos.tutorials.messenger.model.ErrorMessage;

/**
 * This class is responsible for mapping the DataNotFoundException to a specific response.
 * In particular, it is used for tailored exception handling. Whenever a DataNotFoundException
 * is thrown this custom response is created and sent back to the client.
 * 
 * It is a mechanism introduced by the jax-rs framework.
 */

@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException>{

	@Override
	public Response toResponse(DataNotFoundException e) {
		ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), 404, "/localhost:8081/messanger/docs");
		return Response.status(Status.NOT_FOUND)	//Set the status code of the response
				.entity(errorMessage)				//Return a custom error message to the user's screen
				.build();							//Instantiate the response class
	}

}
