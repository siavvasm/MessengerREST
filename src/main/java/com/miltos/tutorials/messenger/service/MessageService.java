package com.miltos.tutorials.messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.client.ClientBuilder;

import com.miltos.tutorials.messenger.database.DatabaseClass;
import com.miltos.tutorials.messenger.model.Message;

/**
 * This class is responsible for manipulating the messages stored in the application database.
 * It provides methods that allow a new message to be added and an existing message to be 
 * retrieved, updated and deleted. It also allows the retrieval of all the messages 
 * stored in the database. 
 */
public class MessageService {
	
	//This messages object points to the original messages object found in the DatabaseClass
	//This object is not a copy of the original object. It is a pointer that points to the same object.
	private  Map<Long, Message> messages = DatabaseClass.getMessages();
	
	//Add some fake messages to the database for testing purposes
	//TODO: To be removed.
	public MessageService(){
		messages.put(1L, new Message(0,"Hi !!", "John Doe"));
		messages.put(2L, new Message(0,"Hi !!", "John Doe"));
	}
	
	/*
	 * A method used to retrieve messages filtered by their creation year.
	 */
	public List<Message> getAllMessagesForYear(int year){
		List<Message> messagesForYear = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		for (Message message : messages.values()){
			cal.setTime(message.getCreated());
			if (cal.get(Calendar.YEAR) == year){
				messagesForYear.add(message);
			}
		}
		return messagesForYear;
	}
	
	/*
	 * A method used to retrieve messages filtered by their index.
	 */
	public List<Message> getAllMessagesPaginated(int start, int size){
		ArrayList<Message> list = new ArrayList<>(messages.values());
		if(start + size > list.size()) return new ArrayList<Message>();
		return list.subList(start, start + size);
	}
	
	
	/*
	 * Methods provided by the MessagesService class for the manipulation of messages.
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
