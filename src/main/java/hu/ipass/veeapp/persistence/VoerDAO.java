package hu.ipass.veeapp.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hu.ipass.veeapp.model.*;

public class VoerDAO extends BaseDAO {

	private List<Voer> selectVoer(String query) {
		List<Voer> results = new ArrayList<Voer>();
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);

			while (dbResultSet.next()) {
				int voer_id = dbResultSet.getInt("voer_id");
				String soort = dbResultSet.getString("soort");

				Voer x = new Voer(voer_id, soort);
				results.add(x);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return results;
	}

	public List<Voer> findAll() {
		return selectVoer("SELECT * from voer");
	}

}
