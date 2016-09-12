package com.miltos.tutorials.messenger.resources;

/**
 * This class handles requests to the path /messages
 * @author Miltos
 *
 */

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import com.miltos.tutorials.messenger.service.MessageService;
import com.miltos.tutorials.messenger.exception.DataNotFoundException;
import com.miltos.tutorials.messenger.model.Link;
import com.miltos.tutorials.messenger.model.Message;
import com.miltos.tutorials.messenger.resources.beans.MessageFilterBean;;

@Path("/messages")
public class MessageResource {
	
	//Create a service in order to manipulate the messages stored in the application database
	MessageService messageService = new MessageService();
	
	/*
	 * Link the methods of the MessageService class to the HTTP requests appropriately.
	 */

	/*
	 * A simple filter for receiving specific messages...
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getMessages(@QueryParam("year") int year,
									 @QueryParam("start") int start,
									 @QueryParam("size") int size){
		System.out.println("The year is : " + year);
		if (year > 0){
			return messageService.getAllMessagesForYear(year);
		}
		
		if (start > 0 && size != 0){
			return messageService.getAllMessagesPaginated(start, size);
		}
		return messageService.getAllMessages();
		//return "<messages><message><id>1</id><author>John</author><text>Hi There</text></message></messages>";
	}
	
	/*
	 * An alternative way to pass the query parameters. We encapsulate them in a class
	 * and we use the Bean annotation to automatically access them.
	 */
/*
	@GET
	@Produces(value = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Message> getMessages(@BeanParam MessageFilterBean filterbean){
		System.out.println("The year is : " + filterbean.getYear());
		if (filterbean.getYear() > 0){
			return messageService.getAllMessagesForYear(filterbean.getYear());
		}
		
		if (filterbean.getStart() > 0 && filterbean.getSize() != 0){
			return messageService.getAllMessagesPaginated(filterbean.getStart(), filterbean.getSize());
		}
		return messageService.getAllMessages();
		//return "<messages><message><id>1</id><author>John</author><text>Hi There</text></message></messages>";
	}
*/
	
	/*
	 * Get all messages in an XML format ...
	 */
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Message> getXmlMessages(@BeanParam MessageFilterBean filterbean){
		System.out.println("So you want an XML!!");
		return messageService.getAllMessages();
		//return "<messages><message><id>1</id><author>John</author><text>Hi There</text></message></messages>";
	}
	
	/*
	 * Get all messages in a JSON format ...
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getJsonMessages(@BeanParam MessageFilterBean filterbean) {
		System.out.println("So you want a JSON!!!");
		return messageService.getAllMessages();
		//return "<messages><message><id>1</id><author>John</author><text>Hi There</text></message></messages>";
	}
	
	/*
	 * This method demonstrates how to throw a custom exception.
	 */
	@GET
	@Path("/{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Message getMessage(@PathParam("messageId") String messageId, @Context UriInfo uri){
		
		//Invoke the appropriate method in order to retrieve the desired message.
		Message message = messageService.getMessage(Long.parseLong(messageId));	
		
		//Check if the message is null (i.e. there isn't such a message)
		if (message == null){
			
			//If the message is null throw a custom exception.
			throw new DataNotFoundException("The message with id: " + messageId + " cannot be found!");
			
		}else {
			
			//Add some custom links to the JSON file returned to the client to implement HATEOAS.
			message.getLinks().add(new Link(uri.getBaseUriBuilder().path(MessageResource.class).path(String.valueOf(message.getId())).build().toString(), "self"));
			message.getLinks().add(new Link(uri.getBaseUriBuilder().path(ProfileResource.class).path(String.valueOf(message.getAuthor())).build().toString(), "profile"));
			message.getLinks().add(new Link(uri.getBaseUriBuilder().path(CommentResource.class).build().toString(), "comments"));	
			message.getLinks().add(new Link("localhost:8081/messenger/webapi/messages/" + messageId + "/comments" , "com"));
			
			//Return the desired message in the appropriate form. 
			return message;
		}
	}
	
	/*
	 * Useless method. For testing purposes only. 
	 * TODO: To be removed.
	 */
	@GET
	@Path("/test")
	@Produces(MediaType.TEXT_PLAIN)
	public String test(){
		return "test";
	}
	
	/*
	 * Update a specific message ...
	 */
	@PUT
	@Path("/{messageId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message updateMessage(@PathParam("messageId") long messageId, Message message){
		message.setId(messageId);
		return messageService.updateMessage(message);
	}
	
	/*
	 * Add a new message ...
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addMessage(Message message, @Context UriInfo uri) throws URISyntaxException{
		Message newMessage = messageService.addMessage(message);
		//return Response.status(Status.CREATED)
		//			   .entity(newMessage)
		//			   .build();
		//return Response.created(new URI("/messanger/webapi/messages" + newMessage.getId())).entity(newMessage).build();
		URI newUri = uri.getAbsolutePathBuilder().path(String.valueOf(newMessage.getId())).build();
		return Response.created(newUri).entity(newMessage).build();
	}
	
	/*
	 * Delete an existing message ...
	 */
	@DELETE
	@Path("/{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteMessage(@PathParam("messageId") long messageId){
		messageService.removeMessage(messageId);
		
	}
	
	/*
	 * SOS: Redirect the execution to a sub-resource class when a specific uri pattern is 
	 *      invoked. Here, when the client makes a request concerning the comments of a message
	 *      the execution is transfered to the CommentResource class.
	 */
	@Path("/{messageId}/comments")
	public CommentResource testComments(@PathParam("messageId") long messageId){
		System.out.println( "The Message Id of the message that you want to retrieve all its comments is : " + messageId);
		return new CommentResource();
	}

}
