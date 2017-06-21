package hu.ipass.veeapp.webservices;

import javax.json.*;
import javax.ws.rs.*;


import hu.ipass.veeapp.model.*;

@Path("voer")

public class VoerResource {
	VoerService service = ServiceProvider.getVoerService();

	@GET
	@Produces("application/json")
	public String getAllvoer() {

		JsonArrayBuilder jab = Json.createArrayBuilder();
		for (Voer x : service.getAllVoer()) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("voer_id", x.getId());
			job.add("soort", x.getSoort());
			jab.add(job);
		}

		JsonArray array = jab.build();
		return array.toString();
		}	
	}
