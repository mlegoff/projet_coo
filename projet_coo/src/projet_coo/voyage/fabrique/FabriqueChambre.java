package projet_coo.voyage.fabrique;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import projet_coo.voyage.domaine.Chambre;

public class FabriqueChambre {
	private static FabriqueChambre _instance;
	private Connection conn;

	private FabriqueChambre(){

			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://webtp.fil.univ-lille1.fr/legoff?user=legoff&password=123moi");
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			


	}

	public static FabriqueChambre getInstance(){
		if(_instance == null){
			_instance = new FabriqueChambre();
		}
		return _instance;
	}

	public Chambre createNewChambre(int capacite, int tarif, String categorie,int nbchambre, int idHotel){
		Chambre newChambre = null;
		try {
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO chambre (categorie, tarif,capacite,nbchambre,idhotel) VALUES(?,?,?,?,?)");
			stmt.clearParameters();
			stmt.setString(1, categorie);
			stmt.setInt(2, tarif);
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
	
	public void deleteChambreByHotel (int id){
		try {
			PreparedStatement stmt = conn.prepareStatement("select idchambre from chambre WHERE idhotel = ?");
			stmt.clearParameters();
			stmt.setInt(1, id);			
			ResultSet resultat = stmt.executeQuery();
			while(resultat.next()){
				deleteChambre(resultat.getInt("idchambre"));
			}
			System.out.println("suppression des hotel pour la ville ! " + id);
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
	}

	public void deleteChambre(int id){
		try {
			PreparedStatement stmt = conn.prepareStatement("DELETE from chambre WHERE idchambre = ?");
			stmt.clearParameters();
			stmt.setInt(1, id);			
			stmt.execute();			

		} catch (SQLException e) {
			e.printStackTrace();
			
		}
	}
	
	public List<Chambre> chambreParId(int id){
		List<Chambre> listChambre = new ArrayList<Chambre>();
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT idchambre,categorie,tarif,capacite,nbchambre,idhotel FROM chambre where idhotel = ?");
			stmt.clearParameters();
			stmt.setInt(1, id);			
			ResultSet resultat = stmt.executeQuery();	
			while(resultat.next()){
				
				Chambre tmp = new Chambre(resultat.getInt("nbchambre"),resultat.getInt("capacite"),resultat.getInt("tarif"),resultat.getString("categorie"),resultat.getInt("idhotel"),resultat.getInt("idchambre"));
				listChambre.add(tmp);
				
			}	
			

		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		return listChambre;
	}
	public Chambre getChambreParId(int id){
		Chambre c = null;
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT idchambre,categorie,tarif,capacite,nbchambre,idhotel FROM chambre where idchambre = ?");
			stmt.clearParameters();
			stmt.setInt(1, id);			
			ResultSet resultat = stmt.executeQuery();	
			if(resultat.next()){
				
				c = new Chambre(resultat.getInt("nbchambre"),resultat.getInt("capacite"),resultat.getInt("tarif"),resultat.getString("categorie"),resultat.getInt("idhotel"),resultat.getInt("idchambre"));
				
				
			}	
			

		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		return c;
	}
	public void putindegitdemerde(){
		String st = "NIKE TA MERE";
	}
}
