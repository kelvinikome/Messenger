package org.kelvin.messenger.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/this")
public class This {

	@GET
	public String th() { 
		return "this";
	}
}
