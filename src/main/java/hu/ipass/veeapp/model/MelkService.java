package hu.ipass.veeapp.model;

import java.util.List;

import hu.ipass.veeapp.persistence.MelkDAO;

public class MelkService {

	private MelkDAO MelkDAO = new MelkDAO();

	public List<MelkGegevens> getMelkById(int oornr) {
		return MelkDAO.findByOornr(oornr);
	}

	public List<MelkGegevens> getMelkOornrs() {
		return MelkDAO.getAllOornr();
	}

	public List<MelkGegevens> getMelkGem() {
		return MelkDAO.getGem();
	}

}
