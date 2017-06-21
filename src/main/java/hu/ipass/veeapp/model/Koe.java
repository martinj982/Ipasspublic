package hu.ipass.veeapp.model;

public class Koe {
	private int oornr;
	private String naam;
	private String geslacht;
	private String status;
	private String geboortedatum;
	private String stal;
	private String geplande_kd;
	private String laatste_kd;
	private String laatste_insd;
	private int moeder;

	public Koe() {
		super();
	}

	public Koe(int oornr, String naam, String geslacht, String status, String geboortedatum, String stal,
			String geplande_kd, String laatste_kd, String laatste_insd,int moeder) {
		super();
		this.oornr = oornr;
		this.naam = naam;
		this.geslacht = geslacht;
		this.status = status;
		this.geboortedatum = geboortedatum;
		this.stal = stal;
		this.geplande_kd = geplande_kd;
		this.laatste_kd = laatste_kd;
		this.laatste_insd = laatste_insd;
		this.moeder=moeder;
	}



	public Koe(int oornr, String naam, String geslacht, String geboortedatum,String stal,int moeder) {
		super();
		this.oornr = oornr;
		this.naam = naam;
		this.geslacht = geslacht;
		this.geboortedatum = geboortedatum;
		this.stal = stal;
		this.moeder=moeder;
	}



	public Koe(int oornr) {
		this.oornr = oornr;
	}



	public int getOornr() {
		return oornr;
	}

	public void setOornr(int oornr) {
		this.oornr = oornr;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public String getGeslacht() {
		return geslacht;
	}

	public void setGeslacht(String geslacht) {
		this.geslacht = geslacht;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getGeboortedatum() {
		return geboortedatum;
	}

	public void setGeboortedatum(String geboortedatum) {
		this.geboortedatum = geboortedatum;
	}

	public String getStal() {
		return stal;
	}

	public void setStal(String stal) {
		this.stal = stal;
	}

	public String getGeplande_kd() {
		return geplande_kd;
	}

	public void setGeplande_kd(String geplande_kd) {
		this.geplande_kd = geplande_kd;
	}

	public String getLaatste_kd() {
		return laatste_kd;
	}

	public void setLaatste_kd(String laatste_kd) {
		this.laatste_kd = laatste_kd;
	}

	public String getLaatste_insd() {
		return laatste_insd;
	}

	public void setLaatste_insd(String laatste_insd) {
		this.laatste_insd = laatste_insd;
	}

	public int getMoeder() {
		return moeder;
	}

	public void setMoeder(int moeder) {
		this.moeder = moeder;
	}




}
