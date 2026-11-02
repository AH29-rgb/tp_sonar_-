package system;

import java.util.Date;

/**
 * @author zakrani
 * @version 1.0
 * @created 25-nov.-2017 21:29:54
 */
public class Compte {

	private Date DateCreation;
	private int NumCompte;
	private float Solde=0;

	public Compte(){

	}
	
	

	public Compte(Date dateCreation, int numCompte, float solde) {
		super();
		DateCreation = dateCreation;
		NumCompte = numCompte;
		Solde = solde;
	}



	@Override
	public String toString() {
		return "Compte crée le " + DateCreation + ", NumCompte=" + NumCompte + ", Solde=" + Solde + "]";
	}



	public void finalize() throws Throwable {

	}

	

	/**
	 * 
	 * @param newVal
	 */
	public void debiter(float newVal){
		if (Solde>=newVal) setSolde(Solde-newVal);

	}

	public Date getDateCreation(){
		return DateCreation;
	}

	public int getNumCompte(){
		return NumCompte;
	}

	public float getSolde(){
		return Solde;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setDateCreation(Date newVal){
		DateCreation = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setNumCompte(int newVal){
		NumCompte = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setSolde(float newVal){
		if (newVal>=0) Solde = newVal;
	}
	
	/**
	 * 
	 * @param newVal
	 */
	public void crediter(float newVal){
		setSolde(Solde+newVal);

	}

	public void virement(Compte c, float mnt){
		debiter(mnt);
		c.crediter(mnt);
	}

}