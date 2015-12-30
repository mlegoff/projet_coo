package projet_coo.voyage.fabrique;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import projet_coo.voyage.domaine.Vol;

public class FabriqueVol {
	private static FabriqueVol _instance;
	private Connection conn;

	private FabriqueVol(){

			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://webtp.fil.univ-lille1.fr/legoff?user=legoff&password=123moi");
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			


	}

	public static FabriqueVol getInstance(){
		if(_instance == null){
			_instance = new FabriqueVol();
		}
		return _instance;
	}

	public Vol createNewVol(int idVilleDepart, int idVilleArrivee, Date date, int duree, String heure, int nbJoursAnnulation,
			int nbPassager1ere, int nbPassager2eme){
		Vol newVol = null;
		try {
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO vol (idvilledepart, idvillearrivee,date,duree,heure,nbjoursannulation,nbpassager1ere,nbPassager2eme) VALUES(?,?,?,?,?,?,?,?)");
			stmt.clearParameters();
			stmt.setInt(1, idVilleDepart);
			stmt.setInt(2, idVilleArrivee);
			java.sql.Date dateSQL = new java.sql.Date(date.getTime());
			stmt.setDate(3,dateSQL);
			stmt.setInt(4, duree);
			stmt.setString(5, heure);
			stmt.setInt(6, nbJoursAnnulation);
			stmt.setInt(7, nbPassager1ere);
			stmt.setInt(8, nbPassager2eme);
			stmt.execute();
			
			PreparedStatement st = conn.prepareStatement("SELECT LAST_INSERT_ID() as id FROM vol");
			st.clearParameters();
			ResultSet resultat = st.executeQuery();		
			resultat.next();
			int id = resultat.getInt("id");
			newVol = new Vol( id,  idVilleDepart,  idVilleArrivee,  date,  duree,  heure,  nbJoursAnnulation,
					nbPassager1ere,  nbPassager2eme);
		
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return newVol;
	}

	public void deleteVol(int id){
		try {
			PreparedStatement stmt = conn.prepareStatement("DELETE from vol WHERE idvol = ?");
			stmt.clearParameters();
			stmt.setInt(1, id);			
			stmt.execute();			

		} catch (SQLException e) {
			e.printStackTrace();
			
		}
	}
	
	public List<Vol> volParVilleDepart(int idVille){
		List<Vol> listVol = new ArrayList<Vol>();
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT idvol,idvilledepart,idvillearrivee,date,duree,heure,nbjoursannulation,nbpassager1ere,nbpassager2eme FROM vol where idvilledepart = ?");
			stmt.clearParameters();
			stmt.setInt(1, idVille);			
			ResultSet resultat = stmt.executeQuery();	
			while(resultat.next()){
				java.sql.Date dte = resultat.getDate("date");
				Date dateTmp = new Date(dte.getTime());
				Vol tmp = new Vol(resultat.getInt("idvol"),resultat.getInt("idvilledepart"),resultat.getInt("idvillearrivee"),dateTmp,resultat.getInt("duree"),
						resultat.getString("heure"),resultat.getInt("nbJoursAnnulation"),resultat.getInt("nbPassager1ere"),resultat.getInt("nbPassager2eme"));
				listVol.add(tmp);
				
			}	
			

		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		return listVol;
	}
	
	public List<Vol> volParVilleArrivee(int idVille){
		List<Vol> listVol = new ArrayList<Vol>();
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT idvol,idvilledepart,idvillearrivee,date,duree,heure,nbjoursannulation,nbpassager1ere,nbpassager2eme FROM vol where idvilledepart = ?");
			stmt.clearParameters();
			stmt.setInt(1, idVille);			
			ResultSet resultat = stmt.executeQuery();	
			while(resultat.next()){
				java.sql.Date dte = resultat.getDate("date");
				Date dateTmp = new Date(dte.getTime());
				Vol tmp = new Vol(resultat.getInt("idvol"),resultat.getInt("idvilledepart"),resultat.getInt("idvillearrivee"),dateTmp,resultat.getInt("duree"),
						resultat.getString("heure"),resultat.getInt("nbJoursAnnulation"),resultat.getInt("nbPassager1ere"),resultat.getInt("nbPassager2eme"));
				listVol.add(tmp);
				
			}	
			

		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		return listVol;
	}
	
	public List<Vol> getAllVol(){
		List<Vol> listVol = new ArrayList<Vol>();
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT idvol,idvilledepart,idvillearrivee,date,duree,heure,nbjoursannulation,nbpassager1ere,nbpassager2eme FROM vol ");
			stmt.clearParameters();			
			ResultSet resultat = stmt.executeQuery();	
			while(resultat.next()){
				java.sql.Date dte = resultat.getDate("date");
				Date dateTmp = new Date(dte.getTime());
				Vol tmp = new Vol(resultat.getInt("idvol"),resultat.getInt("idvilledepart"),resultat.getInt("idvillearrivee"),dateTmp,resultat.getInt("duree"),
						resultat.getString("heure"),resultat.getInt("nbJoursAnnulation"),resultat.getInt("nbPassager1ere"),resultat.getInt("nbPassager2eme"));
				listVol.add(tmp);
				
			}	
			

		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		return listVol;

	}
	
	public Vol getVolById(int id){
		Vol v = null;
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT idvol,idvilledepart,idvillearrivee,date,duree,heure,nbjoursannulation,nbpassager1ere,nbpassager2eme FROM vol WHERE idvol = ?");
			stmt.clearParameters();	
			stmt.setInt(1, id);
			ResultSet resultat = stmt.executeQuery();	
			if(resultat.next()){
			java.sql.Date dte = resultat.getDate("date");
			Date dateTmp = new Date(dte.getTime());
			v = new Vol(resultat.getInt("idvol"),resultat.getInt("idvilledepart"),resultat.getInt("idvillearrivee"),dateTmp,resultat.getInt("duree"),
					resultat.getString("heure"),resultat.getInt("nbJoursAnnulation"),resultat.getInt("nbPassager1ere"),resultat.getInt("nbPassager2eme"));
			}
	} catch (SQLException e) {
		e.printStackTrace();
		
	}
	return v;
	}
}
