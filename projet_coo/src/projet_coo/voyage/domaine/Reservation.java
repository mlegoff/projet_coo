package projet_coo.voyage.domaine;

public class Reservation {
	
	private int idClient;
	private String villeDepart;
	private String villeArrive;
	private int dateAller;
	private int dateRetour;
	private int nbVoyageurs;
	private int idResa;
		
	public int getIdResa() {
		return idResa;
	}
	public int getIdClient() {
		return idClient;
	}
	public String getVilleDepart() {
		return villeDepart;
	}
	public String getVilleArrive() {
		return villeArrive;
	}
	public int getDateAller() {
		return dateAller;
	}
	public int getDateRetour() {
		return dateRetour;
	}
	public int getNbVoyageurs() {
		return nbVoyageurs;
	}


}
