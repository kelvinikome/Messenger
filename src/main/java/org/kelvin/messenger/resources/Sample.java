package org.kelvin.messenger.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/sample")
public class Sample {

	@GET
	public String sample() {
		return "Sample";
	}
}
