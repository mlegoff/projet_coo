package projet_coo.voyage.fabrique;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import projet_coo.voyage.domaine.Hotel;
import projet_coo.voyage.domaine.Ville;
import projet_coo.voyage.domaine.Vol;

	public class FabriqueVille {
		static FabriqueVille _instance;
		private Connection conn;

		private FabriqueVille(){

				try {
					Class.forName("com.mysql.jdbc.Driver");
					conn = DriverManager.getConnection("jdbc:mysql://webtp.fil.univ-lille1.fr/legoff?user=legoff&password=123moi");
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
				


		}

		public static  FabriqueVille getInstance(){
			if(_instance == null){
				_instance = new FabriqueVille();
			}
			return _instance;
		}
		
		public List<Ville> getVilles(){
			List<Ville> listVille = new ArrayList<Ville>();
			try {
				PreparedStatement stmt = conn.prepareStatement("SELECT nom,idville FROM ville");						
				ResultSet resultat = stmt.executeQuery();	
				while(resultat.next()){					
					Ville tmp = new Ville(resultat.getString("nom"),resultat.getInt("idville"));
					listVille.add(tmp);					
				}					

			} catch (SQLException e) {
				e.printStackTrace();
				
			}
			return listVille;
		}
		

		public Ville createNewVille(String nom){
			Ville newVille = null;
			try {				
				PreparedStatement stmt = conn.prepareStatement("INSERT INTO ville (nom) VALUES(?)");
				stmt.clearParameters();
				stmt.setString(1, nom);							
				stmt.execute();
				PreparedStatement st = conn.prepareStatement("SELECT LAST_INSERT_ID() as idville FROM ville");
				st.clearParameters();
				ResultSet resultat = st.executeQuery();		
				resultat.next();
				int id = resultat.getInt("idville");
				System.out.println("ajout! " + nom);
				newVille = new Ville(nom,id);
			}
			catch(SQLException e){
				e.printStackTrace();
			}
			return newVille;
		}

		
		public void deleteVille(int id){
			try {
				PreparedStatement stmt = conn.prepareStatement("DELETE from ville WHERE idville = ?");
				stmt.clearParameters();
				stmt.setInt(1, id);			
				stmt.execute();	
				FabriqueHotel.getInstance().deleteHotelByVille(id);
			} catch (SQLException e) {
				e.printStackTrace();
				
			}
		}
		
		public Ville getVilleByName(String ville){
			Ville v = null;
			try {
				PreparedStatement stmt = conn.prepareStatement("SELECT idville,nom FROM ville WHERE nom = ?");
				stmt.clearParameters();
				stmt.setString(1, ville);
				ResultSet resultat = stmt.executeQuery();	
				resultat.next();
				v = new Ville(resultat.getString("nom"),resultat.getInt("idville"));
				
			} catch (SQLException e) {
				
			}
			return v;
		}
		public boolean villeExists(String ville){
			boolean result = false;
			try {
				PreparedStatement stmt = conn.prepareStatement("SELECT nom FROM ville WHERE nom = ?");
				stmt.clearParameters();
				 stmt.setString(1, ville);		
				 ResultSet resultat = stmt.executeQuery();
				 return resultat.next();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			return result;
		}
		
		public Ville getVilleById(int id){
			Ville v = null;
			try {
				PreparedStatement stmt = conn.prepareStatement("SELECT idville,nom FROM ville WHERE idville = ?");
				stmt.clearParameters();	
				stmt.setInt(1, id);
				ResultSet resultat = stmt.executeQuery();
				if(resultat.next()){
				v = new Ville(resultat.getString("nom"),resultat.getInt("idville"));
				}
				
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		return v;
		}
}

