package projet_coo.voyage.fabrique;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import projet_coo.voyage.domaine.Chambre;
import projet_coo.voyage.domaine.Vol;

public class FabriqueVol {
	private FabriqueVol _instance;
	private Connection conn;

	private FabriqueVol(){

			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://webtp.fil.univ-lille1.fr/legoff?user=legoff&password=123moi");
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			


	}

	public FabriqueVol getInstance(){
		if(_instance == null){
			this._instance = new FabriqueVol();
		}
		return this._instance;
	}

	public Vol createNewVol(int idVilleDepart, int idVilleArrivee, Date date, int duree, int heure, int nbJoursAnnulation,
			int nbPassager1ere, int nbPassager2eme){
		Vol newVol = null;
		try {
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO vol (idvilledepart, idvillearrivee,date,duree,heure,nbjoursannulation,nbpassager1ere,nbPassager2eme) VALUES(?,?,?,?,?,?,?,?)");
			stmt.clearParameters();
			stmt.setInt(1, idVilleDepart);
			stmt.setInt(2, idVilleArrivee);
			java.sql.Date dateSQL = new java.sql.Date();
			stmt.setDate(parameterIndex, x);
			stmt.setInt(3, capacite);
			stmt.setInt(4, nbchambre);
			stmt.setInt(5, idHotel);
			stmt.execute();
			
			PreparedStatement st = conn.prepareStatement("SELECT LAST_INSERT_ID() as id FROM chambre");
			st.clearParameters();
			ResultSet resultat = st.executeQuery();		
			resultat.next();
			int id = resultat.getInt("id");
			newChambre = new Chambre(nbchambre,capacite,tarif,categorie,idHotel,id);
		
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return newChambre;
	}

	public void deleteChambre(int id){
		try {
			PreparedStatement stmt = conn.prepareStatement("DELETE from chambre WHERE id = ?");
			stmt.clearParameters();
			stmt.setInt(1, id);			
			stmt.execute();			

		} catch (SQLException e) {
			e.printStackTrace();
			
		}
	}
}
