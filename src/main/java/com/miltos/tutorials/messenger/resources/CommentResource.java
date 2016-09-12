package com.miltos.tutorials.messenger.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.miltos.tutorials.messenger.model.Comment;
import com.miltos.tutorials.messenger.model.ErrorMessage;
import com.miltos.tutorials.messenger.service.CommentService;

/**
 * This class is responsible for handling all the HTTP Requests that refer to the 
 * URI of the form ./messenger/webapi/messages/{messageId}/comments/{commentId}.
 * 
 * In particular, it is a sub-resource of the MessageResource. 
 */

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class CommentResource {

	//Create a CommentService object to manipulate the comments of a message
	private CommentService commentService = new CommentService();
	
	/*
	 * Link the requests to the appropriate services ... 
	 */
	
	@GET
	public List<Comment> getAllComments(@PathParam("messageId") long messageId) {
		return commentService.getAllComments(messageId);
	}
	
	@POST
	public Comment addComment(@PathParam("messageId") long messageId, Comment comment) {
		return commentService.addComment(messageId, comment);
	}
	
	@PUT
	@Path("/{commentId}")
	public Comment updateComment(@PathParam("messageId") long messageId, @PathParam("commentId") long id, Comment comment) {
		comment.setId(id);
		return commentService.updateComment(messageId, comment);
	}
	
	@DELETE
	@Path("/{commentId}")
	public void deleteComment(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId) {
		commentService.removeComment(messageId, commentId);
	}
	
/*	
	@GET
	@Path("/{commentId}")
	public Comment getMessage(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId) {
		return commentService.getComment(messageId, commentId);
	}
*/
	
	//Implementation with exception handling
	@GET
	@Path("/{commentId}")
	public Response getMessage(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId) {
		
		//Create a custom response for error message
		Response response = Response.status(Status.NOT_FOUND)
									.entity(new ErrorMessage("Nooooo!!!", 404, "localhost:8081"))
									.build();
		
		//Request the desired comment from a specific message
		Comment comment = commentService.getComment(messageId, commentId);
		if (comment == null){
			//Comment doesn't exist. Send the custom response back to the client.
			throw new WebApplicationException(response);
		}else {
			//Comment does exist. Send a Response object containing the desired Comment object.
			return Response.status(Status.FOUND).entity(comment).build();
		}

	}
	
}
