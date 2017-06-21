package hu.ipass.veeapp.webservices;

import javax.json.*;
import javax.ws.rs.*;

import hu.ipass.veeapp.model.*;

@Path("koeien")

public class KoeVoerResource {
	KoeService service = ServiceProvider.getKoeService();
	private JsonObjectBuilder KoeVoerToJSON(KoeVoer x){
		JsonObjectBuilder job = Json.createObjectBuilder();
		job.add("oornr", x.getOornr());
		job.add("voer_id", x.getVoer_id());
		job.add("max", x.getMax());
		job.add("gegeten", x.getGegeten());
		return job;
	}
	
	@GET
	@Path("/voer")
	@Produces("application/json")
	public String getAllVoerallKoe() {	
		JsonArrayBuilder jab = Json.createArrayBuilder();
		for (KoeVoer x : service.getKoeVoerAll()) {
			jab.add(KoeVoerToJSON(x));
		}
		JsonArray array = jab.build();
		return array.toString();
	}

	@GET
	@Path("/voer&oornr=alle")
	@Produces("application/json")
	public String getAllVoerSum() {

		JsonArrayBuilder jab = Json.createArrayBuilder();
		for (KoeVoer x : service.getKoeVoerAllSum()) {
			jab.add(KoeVoerToJSON(x));
		}

		JsonArray array = jab.build();
		return array.toString();
	}

	@GET
	@Path("/voer&oornr={oornr}")
	@Produces("application/json")
	public String getAllVoerByOornr(@PathParam("oornr") int oornr) {
		JsonArrayBuilder jab = Json.createArrayBuilder();
		for (KoeVoer x : service.getKoeVoerByOornr(oornr)) {
			jab.add(KoeVoerToJSON(x));
		}

		JsonArray array = jab.build();
		return array.toString();
	}

	@GET
	@Path("/voer/oornr")
	@Produces("application/json")
	public String getAlloornrVoer() { 	
		JsonArrayBuilder jab = Json.createArrayBuilder();
		//distinct alle oornrs
		for (KoeVoer x : service.getAlloornrVoer()) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("oornr", x.getOornr());
			jab.add(job);
		}
		JsonArray array = jab.build();
		return array.toString();
	}
}
