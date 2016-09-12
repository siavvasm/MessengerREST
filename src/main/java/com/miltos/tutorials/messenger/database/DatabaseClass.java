package com.miltos.tutorials.messenger.database;

import java.util.HashMap;
import java.util.Map;

import com.miltos.tutorials.messenger.model.Message;
import com.miltos.tutorials.messenger.model.Profile;

/**
 * A class that constitutes the virtual database of the messenger
 * application.
 */

public class DatabaseClass {
	
	//Map objects used to store the application data.
	//They are declared as static in order to be common to any class of the application.
	private static Map<Long, Message> messages = new HashMap<>();
	private static Map<String, Profile> profiles = new HashMap<>();
	
	//Methods to retrieve the stored data.
	public static Map<Long, Message> getMessages() {
		return messages;
	}
	
	public static Map<String, Profile> getProfiles() {
		return profiles;
	}
	
	

}
