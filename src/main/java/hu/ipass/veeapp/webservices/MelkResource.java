package hu.ipass.veeapp.webservices;

import javax.json.*;
import javax.ws.rs.*;
import hu.ipass.veeapp.model.*;

@Path("melk")

public class MelkResource {
	MelkService service = ServiceProvider.getMelkService();

	@GET
	@Path("/alle")
	@Produces("application/json")
	public String getAllMelkGem() {

		JsonArrayBuilder jab = Json.createArrayBuilder();
		// per datum het gemiddelde
		for (MelkGegevens x : service.getMelkGem()) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("datum", x.getDatum());
			job.add("liter", x.getAantalLiter());
			jab.add(job);
		}

		JsonArray array = jab.build();
		return array.toString();
	}

	@GET
	@Path("{oornr}")
	@Produces("application/json")
	public String getAllMelk(@PathParam("oornr") int oornr) {

		JsonArrayBuilder jab = Json.createArrayBuilder();
		for (MelkGegevens x : service.getMelkById(oornr)) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("oornr", x.getOornr());
			job.add("datum", x.getDatum());
			job.add("liter", x.getAantalLiter());

			jab.add(job);
		}

		JsonArray array = jab.build();
		return array.toString();
	}

	@GET
	@Path("/oornr")
	@Produces("application/json")
	public String getAllMelkoornrs() {

		JsonArrayBuilder jab = Json.createArrayBuilder();
		//distinct alle oornr
		for (MelkGegevens x : service.getMelkOornrs()) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("oornr", x.getOornr());
			jab.add(job);
		}

		JsonArray array = jab.build();
		return array.toString();
	}
}
