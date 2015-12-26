package projet_coo.voyage.fabrique;

	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.ArrayList;
	import java.util.List;

	import projet_coo.voyage.domaine.Hotel;
import projet_coo.voyage.domaine.Ville;

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

		public Ville createNewVille(String nom){
			Ville newVille = null;
			try {
				PreparedStatement stmt = conn.prepareStatement("INSERT INTO Ville (nom) VALUES(?)");
				stmt.clearParameters();
				stmt.setString(1, nom);				
				stmt.execute();			
				newVille = new Ville(nom);			
			}
			catch(SQLException e){
				e.printStackTrace();
			}
			return newVille;
		}

		
		public void deleteVille(int id){
			try {
				PreparedStatement stmt = conn.prepareStatement("DELETE from ville WHERE id = ?");
				stmt.clearParameters();
				stmt.setInt(1, id);			
				stmt.execute();			

			} catch (SQLException e) {
				e.printStackTrace();
				
			}
		}
		
	}


}
