package hu.ipass.veeapp.model;

public class ServiceProvider {

	private static KoeService koeService = new KoeService();

	public static KoeService getKoeService() {
		return koeService;
	}

	private static VoerService voerService = new VoerService();

	public static VoerService getVoerService() {
		return voerService;
	}

	private static MelkService melkService = new MelkService();

	public static MelkService getMelkService() {
		return melkService;
	}
}
