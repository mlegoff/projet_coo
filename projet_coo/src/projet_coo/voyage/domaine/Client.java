package projet_coo.voyage.domaine;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Client {
	
	private String nom;
	private String prenom;
	private int ville;
	private Date date;
	private int id;
	
	public Client(int id,String nom,String prenom, int ville,Date date){
		this.nom = nom;
		this.prenom = prenom;
		this.ville = ville;
		this.date = date;
		this.id = id;
		
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public int getVille() {
		return ville;
	}


	public Date getDate() {
		return date;
	}
	public String getDateString(){
		String d = new SimpleDateFormat("dd/MM/YYYY").format(date);
		return d;
	}
	public int getId(){
		return this.id;
	}
	
	public String toString(){
		return this.nom+" "+this.prenom;
	}

}
