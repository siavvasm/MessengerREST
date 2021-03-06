package com.miltos.tutorials.messenger.trash;

import java.io.IOException;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import javax.ws.rs.client.Entity;

import org.json.JSONException;
import org.json.JSONObject;

import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifyException;

import java.util.HashMap;

/**
 * An example of sending a POST request to the S-CASE Control Tower (CT) by using JAX-RS Client
 * API and jwt framework for OAuth authentication. 
 */
public class Demo2 
{
	private static String baseURL = "http://app.scasefp7.com:3000/api/proxy/nlpserver/question";
	private static JWTSigner signer = new JWTSigner("bEaq4DYORH0On2IpEE3ZnQcLdu8wS4N-nVcyCZSzc8iXXc4"); // scase_secret as parameter
	
    public static void main( String[] args ) throws JSONException, InvalidKeyException, NoSuchAlgorithmException, IllegalStateException, SignatureException, IOException, JWTVerifyException
    {
    	/*
    	 * Create the signature ...
    	 */
    	HashMap<String, Object> claims = new HashMap<String, Object>();
    	String scase_token = "PeQ4nOHiTQOJumK8tCfU3m-LT9JizdP81EAGhoguu3qDbzc"; // scase_token
    	claims.put("token", scase_token);
        
    	String signature = signer.sign(claims);
        
    	/*
    	 * Create the query ...
    	 */
        String query = "{"
        		+ "\"question\": \"Which services are used to create a tag cloud?\""
        		+ "}";
        
        /*
         * Create a Client object and send the request ...
         */
        Client client = ClientBuilder.newClient();
        try {
        	
        	//Send the request to the API
        	Response response = client.target(baseURL).request().header("AUTHORIZATION", "CT-AUTH " + scase_token + ":" + signature).post(Entity.json(query));
        	
        	//Retrieve the response, parse it and print it to the user's screen
        	System.out.println("***Extracting query terms from a question[POST]***");
        	
        	String resString = response.readEntity(String.class);
        	JSONObject obj = new JSONObject(resString);
        	
        	System.out.println("Created At: " + obj.getString("created_at"));
        	System.out.println("Question: " + obj.getString("question"));
        	System.out.println("Query Terms: " + obj.getJSONArray("query_terms"));
        	
        } finally {
        	client.close();
        }
    }
}

