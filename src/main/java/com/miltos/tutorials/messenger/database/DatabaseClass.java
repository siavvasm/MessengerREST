package com.miltos.tutorials.messenger.database;

import java.util.HashMap;
import java.util.Map;

import com.miltos.tutorials.messenger.model.Message;
import com.miltos.tutorials.messenger.model.Profile;

/**
 * This class pretents to be the system database
 * @author Miltos
 *
 */

public class DatabaseClass {
	
	private static Map<Long, Message> messages = new HashMap<>();
	private static Map<String, Profile> profiles = new HashMap<>();
	
	public static Map<Long, Message> getMessages() {
		return messages;
	}
	
	public static Map<String, Profile> getProfiles() {
		return profiles;
	}
	
	

}
