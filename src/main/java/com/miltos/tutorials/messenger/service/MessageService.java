package com.miltos.tutorials.messenger.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.client.ClientBuilder;

import com.miltos.tutorials.messenger.database.DatabaseClass;
import com.miltos.tutorials.messenger.model.Message;

public class MessageService {
	
	//This messages object points to the original messages object found in the DatabaseClass
	//This object is not a copy of the original object. It is a pointer that points to the same object.
	private  Map<Long, Message> messages = DatabaseClass.getMessages();
	
	//Initially put some stub messages to the static Database
	public MessageService(){
		messages.put(1L, new Message(0,"Hi !!", "John Doe"));
		messages.put(2L, new Message(0,"Hi !!", "John Doe"));
	}
	
	/*
	 * Returns all the messages from the DatabaseClass
	 */
	public List<Message> getAllMessages(){
		return new ArrayList<Message>(messages.values());
	}
	
	public Message getMessage(long id){
		return messages.get(id);
	}
	
	public Message addMessage (Message message){
		message.setId(messages.size() + 1);
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message updateMessage(Message message){
		if(message.getId() <= 0){
			return null;
		}
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message removeMessage(long id){
		return messages.remove(id);
	}
	
	
	

}
