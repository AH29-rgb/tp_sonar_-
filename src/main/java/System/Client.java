package System;

import java.util.ArrayList;
import java.util.List;
/**
 * @author emsi
 * @version 1.0
 * @created 02-janvier 2023
 */
public class Client {

    //private static final Logger log=Logger.getLogger(Client.class);

	private String NomClient;
	private int NumClient;
	private String PrenomClient;
	public List<Compte> m_Compte=new ArrayList<Compte>();
	
	
	public Client(String nomClient, int numClient, String prenomClient) {
		NomClient = nomClient;
		NumClient = numClient;
		PrenomClient = prenomClient;
	}
	public Client(){

	}

	public void finalize() throws Throwable {

	}

	public void afficherClient(){
		System.out.println(" nom du client:"+getNomClient());
		System.out.println("le nom du client:"+getNomClient());
		System.out.println("le pr nom du client:"+getPrenomClient());
		System.out.println("------- Les comptes bancaires associ s : --------");
		for(int i=0;i<m_Compte.size();i++)
		System.out.println(m_Compte.get(i).toString());

	}

	public String getNomClient(){
		return NomClient;
	}

	public int getNumClient(){
		return NumClient;
	}

	public String getPrenomClient(){
		return PrenomClient;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setNomClient(String newVal){
		NomClient = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setNumClient(int newVal){
		NumClient = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setPrenomClient(String newVal){
		PrenomClient = newVal;
	}

}