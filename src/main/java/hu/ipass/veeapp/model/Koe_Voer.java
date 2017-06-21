package hu.ipass.veeapp.model;

public class Koe_Voer {
	
	private int oornr;
	private int voer_id;
	private float max; 
	private float gegeten;
	
	
	public Koe_Voer(int oornr, int voer_id, float max, float gegeten) {
		super();
		this.oornr = oornr;
		this.voer_id = voer_id;
		this.max = max;
		this.gegeten = gegeten;
	}
	
	
	public Koe_Voer(int oornr) {
		this.oornr = oornr;
	}

	public Koe_Voer(int voer_id, float max, float gegeten) {
		this.voer_id = voer_id;
		this.max = max;
		this.gegeten = gegeten;
	}


	public int getOornr() {
		return oornr;
	}
	public void setOornr(int oornr) {
		this.oornr = oornr;
	}
	public int getVoer_id() {
		return voer_id;
	}
	public void setVoer_id(int voer_id) {
		this.voer_id = voer_id;
	}
	public float getMax() {
		return max;
	}
	public void setMax(float max) {
		this.max = max;
	}
	public float getGegeten() {
		return gegeten;
	}
	public void setGegeten(float gegeten) {
		this.gegeten = gegeten;
	}
	
}
