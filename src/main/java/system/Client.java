package system;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {

	private String nomClient;
	private int numClient;
	private String prenomClient;
	private List<Compte> mCompte = new ArrayList<>();
	private final Logger logger = Logger.getLogger(Client.class.getName());
	private FileHandler fileHandler;

	public Client(String nomClient, int numClient, String prenomClient) {
		this.nomClient = nomClient;
		this.numClient = numClient;
		this.prenomClient = prenomClient;
	}

	public Client() {
	}

	public void initialiseFileHandler() throws SecurityException, IOException {
		fileHandler = new FileHandler("app.log");
		logger.addHandler(fileHandler);
	}

	public void closeFileHandler() {
		if (fileHandler != null) {
			logger.removeHandler(fileHandler);
			fileHandler.close();
			fileHandler = null;
		}
	}

	public void afficherClient() {
		if (logger.isLoggable(Level.INFO)) {
			logger.info("le nom du client:" + getNomClient());
			logger.info("le prenom du client:" + getPrenomClient());
			logger.info("-- Les comptes bancaires associes : ------");
			for (int i = 0; i < mCompte.size(); i++) {
				logger.info(mCompte.get(i).toString());
			}
		}
	}

	public List<Compte> getMCompte() {
		return mCompte;
	}

	public void setMCompte(List<Compte> mCompte) {
		this.mCompte = mCompte;
	}

	public String getNomClient() {
		return nomClient;
	}

	public int getNumClient() {
		return numClient;
	}

	public String getPrenomClient() {
		return prenomClient;
	}

	public void setNomClient(String newVal) {
		nomClient = newVal;
	}

	public void setNumClient(int newVal) {
		numClient = newVal;
	}

	public void setPrenomClient(String newVal) {
		prenomClient = newVal;
	}
}