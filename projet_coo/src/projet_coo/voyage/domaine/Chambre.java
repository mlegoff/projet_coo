package projet_coo.voyage.domaine;

public class Chambre {
	private int capacite;
	private int tarif;
	private String categorie;
	private int idHotel;
	private int nbChambre;
	private int id;
	
	public Chambre(int nbChambre,int capacite, int tarif, String categorie, int idHotel, int id) {
		super();
		this.nbChambre =  nbChambre;
		this.capacite = capacite;
		this.tarif = tarif;
		this.categorie = categorie;
		this.idHotel = idHotel;
		this.id = id;
	}
	public int getNbChambre() {
		return nbChambre;
	}
	public int getCapacite() {
		return capacite;
	}
	public int getTarif() {
		return tarif;
	}
	public String getCategorie() {
		return categorie;
	}
	public int getIdHotel() {
		return idHotel;
	}
	public int getId() {
		return id;
	}
	
	public String toString(){
		return "Chambre de categorie " + categorie + " pour " + capacite + " personnes " + ", prix " + tarif + "€, nb chambre " + nbChambre;
	}

}
