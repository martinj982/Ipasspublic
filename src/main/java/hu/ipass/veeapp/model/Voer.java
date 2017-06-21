package hu.ipass.veeapp.model;

public class Voer {
	private int id;
	private String soort;

	public Voer(int id, String soort) {
		super();
		this.id = id;
		this.soort = soort;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSoort() {
		return soort;
	}

	public void setSoort(String soort) {
		this.soort = soort;
	}

}
