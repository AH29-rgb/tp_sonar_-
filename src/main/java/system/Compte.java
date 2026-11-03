package system;

import java.util.Date;

/**
 * @author zakrani
 * @version 1.0
 * @created 25-nov.-2017 21:29:54
 */
public class Compte {

	private Date dateCreation;
	private int numCompte;
	private float solde = 0;

	public Compte() {
		// Constructeur par défaut volontairement vide
	}

	public Compte(Date dateCreation, int numCompte, float solde) {
		this.dateCreation = dateCreation;
		this.numCompte = numCompte;
		this.solde = solde;
	}

	@Override
	public String toString() {
		return "Compte créé le " + dateCreation + ", NumCompte=" + numCompte + ", Solde=" + solde;
	}

	/**
	 * @param newVal montant à débiter (ignoré si le solde est insuffisant)
	 */
	public void debiter(float newVal) {
		if (solde >= newVal) {
			setSolde(solde - newVal);
		}
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public int getNumCompte() {
		return numCompte;
	}

	public float getSolde() {
		return solde;
	}

	public void setDateCreation(Date newVal) {
		dateCreation = newVal;
	}

	public void setNumCompte(int newVal) {
		numCompte = newVal;
	}

	public void setSolde(float newVal) {
		if (newVal >= 0) {
			solde = newVal;
		}
	}

	/**
	 * @param newVal montant à créditer
	 */
	public void crediter(float newVal) {
		setSolde(solde + newVal);
	}

	public void virement(Compte c, float mnt) {
		debiter(mnt);
		c.crediter(mnt);
	}
}
