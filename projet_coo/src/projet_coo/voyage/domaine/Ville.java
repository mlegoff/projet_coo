package projet_coo.voyage.domaine;

public class Ville {

	private String nomVille;
	private int id;
	
	public Ville(String nomVille,int id){
		this.nomVille = nomVille;
		this.id = id;
	}

	public String getNomVille() {
		return nomVille;
	}
	
	public int getid(){
		return id;
	}

	public String toString(){
		return this.nomVille;
	}
	
}
