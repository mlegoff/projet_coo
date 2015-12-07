package projet_coo.voyage.domaine;

public class Client {
	
	private String nom;
	private String prenom;
	private String ville;
	private int reduction;
	private int date;
	private int id;
	
	public Client(int id,String nom,String prenom, String ville,int reduction,int date){
		this.nom = nom;
		this.prenom = prenom;
		this.ville = ville;
		this.reduction = reduction;
		this.date = date;
		
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public String getVille() {
		return ville;
	}

	public int getReduction() {
		return reduction;
	}

	public int getDate() {
		return date;
	}
	
	

}
