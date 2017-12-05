package org.kelvin.messenger.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/messages")
public class MessageResource {

	@GET
	public String test() {
		return "Hello World!"; 
	}
}
