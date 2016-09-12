package com.miltos.tutorials.messenger.model;

import java.util.Date;

/**
 * This class represents a Comment into the system.
 *
 */
public class Comment {
	
	//Comment's attributes
	private long id;
    private String message;
    private Date created;
    private String author;
    
    /*
     * The constructors of the class...
     */
    public Comment() {
    	//Do nothing...
    }
    
    public Comment(long id, String message, String author) {
    	this.id = id;
    	this.message = message;
    	this.author = author;
    	this.created = new Date();
    }
    
    /*
     * Setters and Getters...
     */
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public Date getCreated() {
		return created;
	}
	
	public void setCreated(Date created) {
		this.created = created;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
    
    

}
