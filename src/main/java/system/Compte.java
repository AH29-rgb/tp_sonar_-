package system;

import java.util.Date;

public class Compte {

	private Date dateCreation;
	private int numCompte;
	private float solde = 0;

	public Compte() {
	}

	public Compte(Date dateCreation, int numCompte, float solde) {
		this.dateCreation = dateCreation;
		this.numCompte = numCompte;
		this.solde = solde;
	}

	@Override
	public String toString() {
		return "Compte cree le " + dateCreation + ", numCompte=" + numCompte + ", solde=" + solde;
	}

	public void debiter(float newVal) {
		if (solde >= newVal) {
			setSolde(solde - newVal);
		}
	}

	public void crediter(float newVal) {
		setSolde(solde + newVal);
	}

	public void virement(Compte c, float mnt) {
		debiter(mnt);
		c.crediter(mnt);
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
}