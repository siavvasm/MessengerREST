package com.miltos.tutorials.messenger.resources;

/**
 * This class handles requests to the path /messages
 * @author Miltos
 *
 */

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
import javax.ws.rs.core.MediaType;

import com.miltos.tutorials.messenger.service.MessageService;
import com.miltos.tutorials.messenger.model.Message;

import com.miltos.tutorials.messenger.resources.beans.MessageFilterBean;;

@Path("/messages")
public class MessageResource {
	
	MessageService messageService = new MessageService();
/*	
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
*/	
	/*
	 * An alternative way to pass the query parameters. We encapsulate them in a class
	 * and we use the Bean annotation to automatically access them.
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
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
	
	@GET
	@Path("/{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Message getMessage(@PathParam("messageId") String messageId){
		System.out.println(messageId);
		return messageService.getMessage(Long.parseLong(messageId));		
	}
	
	@GET
	@Path("/test")
	@Produces(MediaType.TEXT_PLAIN)
	public String test(){
		return "test";
	}
	
	@PUT
	@Path("/{messageId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message updateMessage(@PathParam("messageId") long messageId, Message message){
		message.setId(messageId);
		return messageService.updateMessage(message);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message addMessage(Message message){
		return messageService.addMessage(message);
	}
	
	@DELETE
	@Path("/{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteMessage(@PathParam("messageId") long messageId){
		messageService.removeMessage(messageId);
		
	}

}
