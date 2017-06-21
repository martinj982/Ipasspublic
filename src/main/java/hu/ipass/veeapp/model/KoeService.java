package hu.ipass.veeapp.model;

import java.util.List;

import hu.ipass.veeapp.persistence.*;

public class KoeService {

	private KoeDAO koeDAO = new KoeDAO();
	private KoeVoerDAO KoeVoerDAO = new KoeVoerDAO();

	public List<Koe> getInseminaties() {
		return koeDAO.findInseminaties();
	}

	public List<Koe> getAllKoeien() {
		return koeDAO.findAll();
	}

	public Koe getKoeById(int id) {
		return koeDAO.findById(id);
	}

	public boolean deleteKoe(Koe koe) {
		return koeDAO.delete(koe);
	}

	public boolean saveKoe(Koe koe) {
		return koeDAO.save(koe);
	}

	public boolean updateKoe(Koe koe) {
		return koeDAO.update(koe);
	}

	public List<KoeVoer> getKoeVoerByOornr(int oornr) {
		return KoeVoerDAO.getByOornr(oornr);
	}

	public List<KoeVoer> getAlloornrVoer() { // returned distinct
												// koe.oornummers die voer eten
		return KoeVoerDAO.getAllOornr();
	}

	public List<KoeVoer> getKoeVoerAll() { // alle data uit koe_voer
		return KoeVoerDAO.getAll();
	}

	public List<KoeVoer> getKoeVoerAllSum() { // alle data uit koe_voer
												// opgeteld
		return KoeVoerDAO.getAllSum();
	}
}