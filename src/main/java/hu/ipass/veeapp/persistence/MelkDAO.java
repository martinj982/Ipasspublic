package hu.ipass.veeapp.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hu.ipass.veeapp.model.MelkGegevens;

public class MelkDAO extends BaseDAO {

	private List<MelkGegevens> selectMelkGegevens(String query) {
		List<MelkGegevens> results = new ArrayList<MelkGegevens>();
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);

			while (dbResultSet.next()) {

				String datum = String.valueOf(dbResultSet.getDate("DATUM"));
				float aantalLiter = dbResultSet.getFloat("aantal_liter");
				int oornr = dbResultSet.getInt("oornr");

				MelkGegevens x = new MelkGegevens(oornr, datum, aantalLiter);
				results.add(x);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		return results;
	}

	public List<MelkGegevens> findByOornr(int oornr) {
		return selectMelkGegevens("SELECT * from melkgegevens where oornr=" + oornr + "order by datum");
	}

	public List<MelkGegevens> getAllOornr() {
		String query = "SELECT DISTINCT oornr from melkgegevens order by oornr;";
		List<MelkGegevens> results = new ArrayList<MelkGegevens>();
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);

			while (dbResultSet.next()) {
				int oornr = dbResultSet.getInt("oornr");
				MelkGegevens x = new MelkGegevens(oornr);
				results.add(x);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		return results;
	}

	public List<MelkGegevens> getGem() {
		String query = "select datum ,avg(aantal_liter) liters from melkgegevens group by datum order by datum;";
		List<MelkGegevens> results = new ArrayList<MelkGegevens>();
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);

			while (dbResultSet.next()) {
				String datum = String.valueOf(dbResultSet.getDate("DATUM"));
				float aantalLiter = dbResultSet.getFloat("liters");
				MelkGegevens x = new MelkGegevens(datum, aantalLiter);
				results.add(x);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		return results;
	}

}
