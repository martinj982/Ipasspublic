package hu.ipass.veeapp.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hu.ipass.veeapp.model.Koe;

public class KoeDAO extends BaseDAO {

	private List<Koe> selectKoeien(String query) {
		List<Koe> results = new ArrayList<Koe>();
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);
			while (dbResultSet.next()) {
				int oornr = dbResultSet.getInt("oornr");
				String naam = dbResultSet.getString("NAAM");
				String geslacht = dbResultSet.getString("GESLACHT");
				String status = dbResultSet.getString("STATUS");
				String geboortedatum = String.valueOf(dbResultSet.getDate("GEBOORTEDATUM"));
				String stal = String.valueOf(dbResultSet.getString("stal"));
				String geplande_kd = String.valueOf(dbResultSet.getString("geplande_kd"));
				String laatste_kd = String.valueOf(dbResultSet.getString("laatste_kd"));
				String laatste_insd = String.valueOf(dbResultSet.getString("laatste_insd"));
				int moeder = dbResultSet.getInt("moeder");

				if (geplande_kd.equals("null")) {
					geplande_kd = "";
				}
				if (laatste_kd.equals("null")) {
					laatste_kd = "";
				}
				if (laatste_insd.equals("null")) {
					laatste_insd = "";
				}
				Koe k = new Koe(oornr, naam, geslacht, status, geboortedatum, stal, geplande_kd, laatste_kd,
						laatste_insd, moeder);
				results.add(k);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		return results;
	}

	public List<Koe> findAll() {
		return selectKoeien("SELECT * from koe order by oornr");
	}

	public Koe findById(int id) {
		return selectKoeien("SELECT * from koe where oornr=" + id).get(0);
	}

	public List<Koe> findInseminaties() {
		return selectKoeien("select * from koe  where (status='geen' or status='afgekalfd')"
				+ "and geslacht ='V' and geboortedatum +interval'15 month'  <current_date "
				+ "and (laatste_insd +interval'3 week' < current_date or laatste_insd is null)"
				+ "and(laatste_kd +interval'3 week' < current_date or laatste_kd is null) ORDER BY OORNR;");
	}

	public boolean save(Koe koe) {
		boolean result = false;
		String query = "LEEG";
		if (koe.getMoeder() == 0) {

			query = "insert into koe(OORNR,NAAM,GESLACHT,GEBOORTEDATUM,STAL) values(" + koe.getOornr() + ",'"
					+ koe.getNaam() + "','" + koe.getGeslacht() + "','" + koe.getGeboortedatum() + "','" + koe.getStal()
					+ "')";
		} else {
			query = "insert into koe(OORNR,NAAM,GESLACHT,GEBOORTEDATUM,STAL,MOEDER) values(" + koe.getOornr() + ",'"
					+ koe.getNaam() + "','" + koe.getGeslacht() + "','" + koe.getGeboortedatum() + "','" + koe.getStal()
					+ "'," + koe.getMoeder() + ")";
		}

		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);
			result = true;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return result;
	}

	public boolean update(Koe koe) {
		boolean result = false;
		try {
			Connection conn = super.getConnection();
			Statement stmt = conn.createStatement();
			String strQuery = "UPDATE koe SET status='" + koe.getStatus() + "'";

			if (!koe.getLaatste_kd().equals("")) {
				strQuery += ", laatste_kd= to_date('" + koe.getLaatste_kd() + "','YYYY-MM-DD')";
			}
			if (!koe.getLaatste_insd().equals("")) {
				strQuery += ", laatste_insd= to_date('" + koe.getLaatste_insd() + "','YYYY-MM-DD'),";
				strQuery += "geplande_kd = to_date('" + koe.getLaatste_insd() + "','YYYY-MM-DD') + interval'9 month' ";
			}
			strQuery += " where oornr=" + koe.getOornr();
			stmt.executeUpdate(strQuery);
			conn.close();
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;

	}

	public boolean delete(Koe koe) {

		boolean result = false;
		boolean koeExists = findById(koe.getOornr()) != null;

		if (koeExists) {
			String query = "DELETE FROM koe WHERE oornr =" + koe.getOornr();

			try (Connection con = super.getConnection()) {

				Statement stmt = con.createStatement();
				stmt.executeUpdate(query);
				result = true;

			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}

		return result;
	}
}
