package com.miltos.tutorials.messenger.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.client.ClientBuilder;

import com.miltos.tutorials.messenger.database.DatabaseClass;
import com.miltos.tutorials.messenger.model.Message;
import com.miltos.tutorials.messenger.model.Profile;

public class ProfileService {
	
	//This messages object points to the original messages object found in the DatabaseClass
	//This object is not a copy of the original object. It is a pointer that points to the same object.
	private  Map<String, Profile> profiles = DatabaseClass.getProfiles();
	
	//Initially put some stub messages to the static Database
	public ProfileService(){
		profiles.put("siavvasm", new Profile(1,"siavvasm", "Miltos", "Siavvas"));
		profiles.put("thekkini", new Profile(0,"thekkini", "Thekla", "Kinikli"));
	}
	
	/*
	 * Returns all the messages from the DatabaseClass
	 */
	public List<Profile> getAllProfiles(){
		return new ArrayList<Profile>(profiles.values());
	}
	
	public Profile getProfile(String profileName){
		return profiles.get(profileName);
	}
	
	public Profile addProfile (Profile profile){
		profile.setId(profiles.size() + 1);
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}
	
	public Profile updateProfile(Profile profile){
		if(profile.getProfileName().isEmpty()){
			return null;
		}
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}
	
	public Profile removeProfile(String profileName){
		return profiles.remove(profileName);
	}
	
	
	

}
