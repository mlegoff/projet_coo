package projet_coo.voyage.domaine;

public class Hotel {
	private String nom;
	private int id;
	private int idVille;
	
	public Hotel(String nom, int id, int idVille){
		this.id = id;
		this.nom = nom;
		this.idVille = idVille;
	}

	public String getNom() {
		return nom;
	}

	public int getId() {
		return id;
	}

	public int getIdVille() {
		return idVille;
	}
	
	public String toString(){
		return nom ;
	}

}
