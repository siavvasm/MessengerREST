package com.miltos.tutorials.messenger.resources;

import java.util.List;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

/**
 * Useless class. Just for testing purposes.
 */

@Path("/injectdemo")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.TEXT_PLAIN)
public class InjectDemoResource {
	
	@GET
	@Path("/annotations")
	public String getParamsUsingAnnotations(@MatrixParam("param") String matrixParam,
											@HeaderParam("customHeader") String headerParam,
											@CookieParam("cookieParam") String cookieParam){
		return "The value of the \"param\" is : " + matrixParam + ". The header param is : " + headerParam + " The cookie is : " + cookieParam;
	}
	
	@GET
	@Path("/context")
	public String getParamsUsingContext(@Context UriInfo uriInfo, @Context HttpHeaders headers){
		String path = uriInfo.getAbsolutePath().toString();
		String cookies = headers.getHeaderString("customHeader");
		
		/*MultivaluedMap<String,String> headersMap = headers.getRequestHeaders();
		List<String> list = null;
		for (int i = 0; i < headersMap.size() ; i ++ ){
			list = headersMap.;
			System.out.println(" Header Param Name : " + list.get(0) + " Header param Value : " + list.get(1));
		}*/	
		
		return "the uri is : " + path + " the custom header value is : " + cookies;

	}
}
