package projet_coo.voyage.fabrique;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import projet_coo.voyage.domaine.Client;
import projet_coo.voyage.domaine.Hotel;

public class FabriqueHotel {
	private FabriqueHotel _instance;
	private Connection conn;

	private FabriqueHotel(){

			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://webtp.fil.univ-lille1.fr/legoff?user=legoff&password=123moi");
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			


	}

	public FabriqueHotel getInstance(){
		if(_instance == null){
			this._instance = new FabriqueHotel();
		}
		return this._instance;
	}

	public Hotel createNewHotel(String nom, int idVille){
		Hotel newHotel = null;
		try {
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO hotel (nom, idville) VALUES(?,?)");
			stmt.clearParameters();
			stmt.setString(1, nom);
			stmt.setInt(2, idVille);
			stmt.execute();
			
			PreparedStatement st = conn.prepareStatement("SELECT LAST_INSERT_ID() as id FROM hotel");
			st.clearParameters();
			ResultSet resultat = st.executeQuery();		
			resultat.next();
			int idHotel = resultat.getInt("id");
			new Hotel(nom,idHotel,idVille);
		
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return newHotel;
	}

	public void deleteHotel(int id){
		try {
			PreparedStatement stmt = conn.prepareStatement("DELETE from hotel WHERE id = ?");
			stmt.clearParameters();
			stmt.setInt(1, id);			
			stmt.execute();			

		} catch (SQLException e) {
			e.printStackTrace();
			
		}
	}
}
