package hu.ipass.veeapp.model;

import java.util.List;

import hu.ipass.veeapp.model.Voer;
import hu.ipass.veeapp.persistence.VoerDAO;

public class VoerService {

	private VoerDAO voerDAO = new VoerDAO();

	public List<Voer> getAllVoer() {
		return voerDAO.findAll();
	}

}
