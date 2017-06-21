package hu.ipass.veeapp.model;

public class MelkGegevens {

	private int oornr;
	private String datum;
	private float aantalLiter;

	public MelkGegevens(int oornr, String datum, float aantalLiter) {
		super();
		this.oornr = oornr;
		this.datum = datum;
		this.aantalLiter = aantalLiter;
	}

	public MelkGegevens(int oornr) {
		this.oornr = oornr;
	}

	public MelkGegevens(String datum, float aantalLiter) {
		this.datum = datum;
		this.aantalLiter = aantalLiter;
	}

	public int getOornr() {
		return oornr;
	}

	public void setOornr(int oornr) {
		this.oornr = oornr;
	}

	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}

	public float getAantalLiter() {
		return aantalLiter;
	}

	public void setAantalLiter(float aantalLiter) {
		this.aantalLiter = aantalLiter;
	}

}
