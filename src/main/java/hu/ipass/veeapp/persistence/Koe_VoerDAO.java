package hu.ipass.veeapp.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hu.ipass.veeapp.model.*;

public class Koe_VoerDAO extends BaseDAO {

	private List<Koe_Voer> selectCustomers(String query) {

		List<Koe_Voer> results = new ArrayList<Koe_Voer>();
		System.out.print("(koe)VOERDAO");
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);

			while (dbResultSet.next()) {

				int oornr = dbResultSet.getInt("oornr");
				int voer_id = dbResultSet.getInt("voer_id");
				float max = dbResultSet.getFloat("maximale_hoeveelheid");
				float gegeten = dbResultSet.getFloat("gemiddeld_gegeten");

				Koe_Voer kv = new Koe_Voer(oornr, voer_id, max, gegeten);
				results.add(kv);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		return results;
	}

	public List<Koe_Voer> getByOornr(int oornr) {
		return selectCustomers("SELECT * from koe_voer where oornr=" + oornr);
	}

	public List<Koe_Voer> getAll() {
		return selectCustomers("SELECT * from koe_voer");
	}

	public List<Koe_Voer> getAllSum() {
		String query = "select voer_id,sum(maximale_hoeveelheid) maxi,sum(gemiddeld_gegeten)geg from koe_voer group by voer_id";
		List<Koe_Voer> results = new ArrayList<Koe_Voer>();
		System.out.print("(koe)VOERDAO");
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);

			while (dbResultSet.next()) {
				int voer_id = dbResultSet.getInt("voer_id");
				float max = dbResultSet.getFloat("maxi");
				float gegeten = dbResultSet.getFloat("geg");

				Koe_Voer kv = new Koe_Voer(voer_id, max, gegeten);
				results.add(kv);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		return results;
	}

	public List<Koe_Voer> getAllOornr() {

		String query = "SELECT DISTINCT oornr from Koe_Voer order by oornr;";
		List<Koe_Voer> results = new ArrayList<Koe_Voer>();
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);

			while (dbResultSet.next()) {
				int oornr = dbResultSet.getInt("oornr");
				Koe_Voer kv = new Koe_Voer(oornr);
				results.add(kv);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		return results;
	}

}
