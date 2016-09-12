package com.miltos.tutorials.messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.miltos.tutorials.messenger.model.ErrorMessage;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

	@Override
	public Response toResponse(Throwable e) {
		ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), 500, "http://localhost:8081/messanger/docs");
		return Response.status(Status.INTERNAL_SERVER_ERROR)
				       .entity(errorMessage)
				       .build();
	}
}
