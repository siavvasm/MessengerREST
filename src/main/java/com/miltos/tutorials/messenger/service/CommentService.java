package com.miltos.tutorials.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.miltos.tutorials.messenger.database.DatabaseClass;
import com.miltos.tutorials.messenger.model.Message;
import com.miltos.tutorials.messenger.model.Comment;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This class is responsible for manipulating the comments stored in the application database.
 * It provides methods that allow a new comment to be added and an existing comment to be 
 * retrieved, updated and deleted. It also allows the retrieval of all the comments 
 * stored in the database. 
 */

public class CommentService {
	
	//Get the messages array from the DatabaseClass
	private Map<Long, Message> messages = DatabaseClass.getMessages();
	
	/*
	 * Methods provided by the CommentService class for manipulating the comments.
	 */
	
	public List<Comment> getAllComments(long messageId) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		return new ArrayList<Comment>(comments.values());
	}
	
	public Comment getComment(long messageId, long commentId) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		return comments.get(commentId);
	}
	
	public Comment addComment(long messageId, Comment comment) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		comment.setId(comments.size() + 1);
		comments.put(comment.getId(), comment);
		return comment;
	}
	
	public Comment updateComment(long messageId, Comment comment) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		if (comment.getId() <= 0) {
			return null;
		}
		comments.put(comment.getId(), comment);
		return comment;
	}
	
	public Comment removeComment(long messageId, long commentId) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		return comments.remove(commentId);
	}
		
}
