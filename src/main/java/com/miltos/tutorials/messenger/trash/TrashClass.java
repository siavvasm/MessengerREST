package com.miltos.tutorials.messenger.trash;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.client.Entity;

import org.json.JSONException;
import org.json.JSONObject;

import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifyException;

import java.util.HashMap;

public class TrashClass 
{
	private static String baseURL = "http://app.scasefp7.com:3000/api/proxy/auth";
	//private static String baseURL = "http://app.scasefp7.com:3000/api/proxy/nlpserver/question";
	
	private static JWTSigner signer = new JWTSigner("PrBzJLdtLeJ8oxXEUcVthOS5B-ppL_SEHhwD9jyuXWFnTEo"); // A signer to create the signature of our user (scase_secret as parameter)
	private static JWTSigner signer2 = new JWTSigner("KX28G6uRP1opKBdxTPRYQ3rdL0SbITEKzLeTBnFFfrrON1A"); // A signer to create the signature of the user that we want to validate
    
	public static void main( String[] args ) throws JSONException, InvalidKeyException, NoSuchAlgorithmException, IllegalStateException, SignatureException, IOException, JWTVerifyException
    {
    	HashMap<String, Object> claims = new HashMap<String, Object>();
    	String scase_token = "YzZFdPtaHv45Qip_0Eyn2wK09KAH4iKKQRoazy88iqSDoaI"; //The scase_token of the application
        
    	claims.put("token", scase_token);
        
    	//Create the signature of the application
    	String signature = signer.sign(claims);
    	
    	System.out.println("The signature is :" + signature);
        
    	//Create a client to communicate with the API
        Client client = ClientBuilder.newClient();
        
        //Set the credentials of the user that we want to validate
        String userSecretToken = "NkOqdgaK-njHSdgd8QjKIrOXosVo2QN6xoq7T6x6EVT7HUE";
        String userSecretKey = "KX28G6uRP1opKBdxTPRYQ3rdL0SbITEKzLeTBnFFfrrON1A";
        
        //Create the signature of the user that we want to validate
        HashMap<String, Object> claims2 = new HashMap<String, Object>();
        claims.put("token", scase_token);
        String userSignature = signer2.sign(claims2);
        
        
        System.out.println("The signature of the user is :" + userSignature);
        
        try {
        	
        	//POST Request to : /auth - Invalid
        	//Response response = client.target(baseURL).request().header("AUTHORIZATION", "CT-AUTH " + scase_token + ":" + signature).post(Entity.json(query));
        	
        	//GET Request to : /auth - Valid
        	//Check if you gain authorized access ...
        	Response test_response = client.target(baseURL).request().header("AUTHORIZATION", "CT-AUTH " + scase_token + ":" + signature).get();
        	
        	//TODO: Remove the following couple of lines
        	String authHeader = test_response.getHeaderString("AUTHORIZATION");
        	System.out.println("The AUTHORIZATION header is : " + authHeader);
        	
        	//Print the response to the console (Weather our app successfully granted access)
        	String test_resString = test_response.readEntity(String.class);
        	System.out.println("Authorization status : " + test_resString);

        	//Check if the user is valid
        	Response response = client.target("http://app.scasefp7.com:3000/api/validateUser?scase_token=" + userSecretToken + "&scase_signature=" + userSignature ).request().header("AUTHORIZATION", "CT-AUTH " + scase_token + ":" + signature).get();
        	System.out.println("You send the following request: " + "http://app.scasefp7.com:3000/api/validateUser?scase_token=" + userSecretToken + "&scase_signature=" + userSignature  );
        	
        	//Print the response to the console (Whether the user is valid or not)
        	String resString = response.readEntity(String.class);
        	System.out.println("You got this response in pure text : " + resString);
        	
        	//Turn the string into JSON Object
        	JSONObject obj = new JSONObject(resString);       	
        	System.out.println("JSON Content: " + obj.getString("message"));

        	//Check with an if case whether the user is valid. If yes return SUCCESS, else return FAILURE.
        	//...
        	
        } finally {
        	client.close();
        }
    }
}

