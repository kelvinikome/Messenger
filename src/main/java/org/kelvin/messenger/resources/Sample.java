package org.kelvin.messenger.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/heys")
public class Sample {

	@GET
	public String sample() {
		return "Sampleaaaaaaaaa";
	}
}
