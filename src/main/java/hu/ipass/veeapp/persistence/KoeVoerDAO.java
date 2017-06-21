package hu.ipass.veeapp.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hu.ipass.veeapp.model.*;

public class KoeVoerDAO extends BaseDAO {

	private List<KoeVoer> selectKoeVoer(String query) {

		List<KoeVoer> results = new ArrayList<KoeVoer>();
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);

			while (dbResultSet.next()) {

				int oornr = dbResultSet.getInt("oornr");
				int voer_id = dbResultSet.getInt("voer_id");
				float max = dbResultSet.getFloat("maximale_hoeveelheid");
				float gegeten = dbResultSet.getFloat("gemiddeld_gegeten");

				KoeVoer kv = new KoeVoer(oornr, voer_id, max, gegeten);
				results.add(kv);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		return results;
	}

	public List<KoeVoer> getByOornr(int oornr) {
		return selectKoeVoer("SELECT * from koe_voer where oornr=" + oornr);
	}

	public List<KoeVoer> getAll() {
		return selectKoeVoer("SELECT * from koe_voer");
	}

	public List<KoeVoer> getAllSum() {
		String query = "select voer_id,sum(maximale_hoeveelheid) maxi,sum(gemiddeld_gegeten)geg from koe_voer group by voer_id";
		List<KoeVoer> results = new ArrayList<KoeVoer>();
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);

			while (dbResultSet.next()) {
				int voer_id = dbResultSet.getInt("voer_id");
				float max = dbResultSet.getFloat("maxi");
				float gegeten = dbResultSet.getFloat("geg");

				KoeVoer kv = new KoeVoer(voer_id, max, gegeten);
				results.add(kv);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		return results;
	}

	public List<KoeVoer> getAllOornr() {

		String query = "SELECT DISTINCT oornr from Koe_Voer order by oornr;";
		List<KoeVoer> results = new ArrayList<KoeVoer>();
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);

			while (dbResultSet.next()) {
				int oornr = dbResultSet.getInt("oornr");
				KoeVoer kv = new KoeVoer(oornr);
				results.add(kv);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		return results;
	}

}
