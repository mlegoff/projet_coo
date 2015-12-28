package projet_coo.voyage.domaine;

import java.util.Date;

public class Vol {
	
	private int id;
	private int idVilleDepart;
	private int idVilleArrivee;
	private Date date;
	private int duree;
	private String heure;
	private int nbJoursAnnulation;
	private int nbPassager1ere;
	private int nbPassager2eme;
	
	public int getId() {
		return id;
	}
	public int getIdVilleDepart() {
		return idVilleDepart;
	}
	public int getIdVilleArrivee() {
		return idVilleArrivee;
	}
	public Date getDate() {
		return date;
	}
	public int getDuree() {
		return duree;
	}
	public String getHeure() {
		return heure;
	}
	public int getNbJoursAnnulation() {
		return nbJoursAnnulation;
	}
	public int getNbPassager1ere() {
		return nbPassager1ere;
	}
	public int getNbPassager2eme() {
		return nbPassager2eme;
	}
	public Vol(int id, int idVilleDepart, int idVilleArrivee, Date date, int duree, String heure, int nbJoursAnnulation,
			int nbPassager1ere, int nbPassager2eme) {
		super();
		this.id = id;
		this.idVilleDepart = idVilleDepart;
		this.idVilleArrivee = idVilleArrivee;
		this.date = date;
		this.duree = duree;
		this.heure = heure;
		this.nbJoursAnnulation = nbJoursAnnulation;
		this.nbPassager1ere = nbPassager1ere;
		this.nbPassager2eme = nbPassager2eme;
	}
	
	public String toString(){
		return this.idVilleDepart+"-"+this.idVilleArrivee+" à "+this.heure;
	}

}
