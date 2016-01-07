package projet_coo.voyage.domaine;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import projet_coo.voyage.fabrique.FabriqueVille;

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
		@SuppressWarnings("deprecation")
		String d = new SimpleDateFormat("dd/MM/YYYY").format(date);
		String nomDep = FabriqueVille.getInstance().getVilleById(idVilleDepart).getNomVille();
		String nomArr = FabriqueVille.getInstance().getVilleById(idVilleArrivee).getNomVille();
		return nomDep+"-"+nomArr+" "+d+" "+this.heure+" "+nbPassager1ere+" places en 1ère et "+nbPassager2eme+" en 2nd classe";
	}
	public String toStringResume(){
		@SuppressWarnings("deprecation")
		String d = new SimpleDateFormat("dd/MM/YYYY").format(date);
		String nomDep = FabriqueVille.getInstance().getVilleById(idVilleDepart).getNomVille();
		String nomArr = FabriqueVille.getInstance().getVilleById(idVilleArrivee).getNomVille();
		return nomDep+"-"+nomArr+" "+d+" "+this.heure;
	}
}
