package projet_coo.voyage.domaine;

public class Reservation {
	
	private int idClient;
	private int villeDepart;
	private int villeArrive;
	private int nbVoyageurs;
	private int idchambre;
	private int id;
	private int idVolDepart;
	private int idVolRetour;
		
	public Reservation(int idClient, int villeDepart, int villeArrive,
			int nbVoyageurs,
			int idchambre, int id,int idVolDepart,int idVolRetour) {
		super();
		this.idClient = idClient;
		this.villeDepart = villeDepart;
		this.villeArrive = villeArrive;
		this.nbVoyageurs = nbVoyageurs;
		this.idchambre = idchambre;
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public int getIdchambre() {
		return idchambre;
	}
	public int getIdVolDepart() {
		return idVolDepart;
	}
	public int getIdVolRetour() {
		return idVolRetour;
	}
	public int getIdClient() {
		return idClient;
	}
	public int getVilleDepart() {
		return villeDepart;
	}
	public int getVilleArrive() {
		return villeArrive;
	}

	public int getNbVoyageurs() {
		return nbVoyageurs;
	}


}
