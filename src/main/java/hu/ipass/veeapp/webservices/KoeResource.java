package hu.ipass.veeapp.webservices;

import javax.json.*;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import hu.ipass.veeapp.model.*;

@Path("koeien")

public class KoeResource {
	KoeService service = ServiceProvider.getKoeService();
	private JsonObjectBuilder KoeToJSON(Koe k){
		JsonObjectBuilder job = Json.createObjectBuilder();
		job.add("oornr", k.getOornr());
		job.add("naam", k.getNaam());
		job.add("geslacht", k.getGeslacht());
		job.add("status", k.getStatus());
		job.add("geboortedatum", k.getGeboortedatum());
		job.add("stal", k.getStal());
		job.add("geplande_kd", k.getGeplande_kd());
		job.add("laatste_kd", k.getLaatste_kd());
		job.add("laatste_insd", k.getLaatste_insd());
		job.add("moeder", k.getMoeder());
		return job;
	}
	
	@GET
	@Produces("application/json")
	public String getAllkoeien() {

		JsonArrayBuilder jab = Json.createArrayBuilder();
		for (Koe k : service.getAllKoeien()) {
			jab.add(KoeToJSON(k));
		}

		JsonArray array = jab.build();
		return array.toString();
	}

	@GET
	@Path("insemineren")
	@Produces("application/json")
	public String getInserminaties() {
		JsonArrayBuilder jab = Json.createArrayBuilder();
		for (Koe k : service.getInseminaties()) {
			jab.add(KoeToJSON(k));
		}
		JsonArray array = jab.build();
		return array.toString();
	}
	


	@POST
	@Produces("application/json")
	public Response createCustomer(@FormParam("oornr") int oornr, @FormParam("naam") String naam,
			@FormParam("geslacht") String geslacht, @FormParam("geboortedatum") String geboortedatum,
			@FormParam("moeder") int moeder, @FormParam("stal") String stal) {

		if (!geboortedatum.equals("") && !naam.equals("") && oornr >= 1) {

			Koe newkoe = new Koe(oornr, naam, geslacht, geboortedatum, stal, moeder);

			if (service.saveKoe(newkoe)) {
				// koe opgeslagen = true

				return Response.ok().build();
			}
		}

		return Response.status(Response.Status.NOT_FOUND).build();

	}

	@PUT
	@Path("{oornr}")
	@Produces("application/json")
	public Response updateCustomer(@PathParam("oornr") int oornr, @FormParam("status") String status,
			@FormParam("datum") String datum) {

		for (Koe k : service.getAllKoeien()) {
			// als status niet al zo erin staat
			if (k.getOornr() == oornr && !k.getStatus().equals(status)
					&& (!status.equals("afgekalfd") || !datum.equals(""))) {

				k.setStatus(status);
				if (status.equals("afgekalfd")) { // voor afgekalfd zet de
													// laatste kalfdatum en
													// geplande kd vervalt
					k.setLaatste_kd(datum);
					k.setGeplande_kd(null);
				}

				if (service.updateKoe(k)) {
					return Response.ok().build();
				}
			}
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}

	@PUT
	@Path("insemineren/{oornr}")
	@Produces("application/json")
	public Response updateInseminatie(@PathParam("oornr") int oornr, @FormParam("datum") String datum) {

		System.out.print(datum);
		for (Koe k : service.getAllKoeien()) {

			if (k.getOornr() == oornr && !datum.equals("")) { // als datum niet
																// leeg is
				k.setLaatste_insd(datum);

				if (service.updateKoe(k)) {
					return Response.ok().build();
				}
			}
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}

	@DELETE
	@Path("{oornr}")
	public Response deleteKoe(@PathParam("oornr") int oornr) {
		Koe found = null;
		for (Koe k : service.getAllKoeien()) {

			if (k.getOornr() == oornr) {
				found = k;
				break;
			}
		}

		if (found == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} else {

			service.deleteKoe(found);
			return Response.ok().build();
		}
	}
}