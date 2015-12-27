package projet_coo.voyage.fabrique;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import projet_coo.voyage.domaine.Hotel;

public class FabriqueHotel {
	static FabriqueHotel _instance;
	private Connection conn;

	private FabriqueHotel(){

			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://webtp.fil.univ-lille1.fr/legoff?user=legoff&password=123moi");
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			


	}

	public static  FabriqueHotel getInstance(){
		if(_instance == null){
			_instance = new FabriqueHotel();
		}
		return _instance;
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
			System.out.println("ajout! " + nom);
			new Hotel(nom,idHotel,idVille);
		
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return newHotel;
	}

	public List<Hotel> hotelParId(int id){
		List<Hotel> listHotel = new ArrayList<Hotel>();
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT idhotel,nom,idville FROM hotel where idhotel = ?");
			stmt.clearParameters();
			stmt.setInt(1, id);			
			ResultSet resultat = stmt.executeQuery();	
			while(resultat.next()){
				
				Hotel tmp = new Hotel(resultat.getString("nom"),resultat.getInt("idhotel"),resultat.getInt("idville"));
				listHotel.add(tmp);
				
			}	
			

		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		return listHotel;
	}
	
	
	public List<Hotel> hotelParVille(int id){
		List<Hotel> listHotel = new ArrayList<Hotel>();
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT idhotel,nom,idville FROM hotel where idville = ?");
			stmt.clearParameters();
			stmt.setInt(1, id);			
			ResultSet resultat = stmt.executeQuery();	
			while(resultat.next()){
				
				Hotel tmp = new Hotel(resultat.getString("nom"),resultat.getInt("idhotel"),resultat.getInt("idville"));
				listHotel.add(tmp);
				
			}	
			

		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		return listHotel;
	}
	
	public void deleteHotel(int id){
		try {
			PreparedStatement stmt = conn.prepareStatement("DELETE from hotel WHERE idhotel = ?");
			stmt.clearParameters();
			stmt.setInt(1, id);			
			stmt.execute();			

		} catch (SQLException e) {
			e.printStackTrace();
			
		}
	}
	
}
