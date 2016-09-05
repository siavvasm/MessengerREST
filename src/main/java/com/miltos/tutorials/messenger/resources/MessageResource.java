package com.miltos.tutorials.messenger.resources;

/**
 * This class handles requests to the path /messages
 * @author Miltos
 *
 */

import java.util.List;

import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.miltos.tutorials.messenger.service.MessageService;
import com.miltos.tutorials.messenger.model.Message;

@Path("/messages")
public class MessageResource {
	
	MessageService messageService = new MessageService();
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Message> getMessages(){
		return messageService.getAllMessages();
		//return "<messages><message><id>1</id><author>John</author><text>Hi There</text></message></messages>";
	}
	

}
