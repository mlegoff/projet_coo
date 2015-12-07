package projet_coo.voyage.fabrique;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import projet_coo.voyage.domaine.Client;

public class FabriqueClient {
private FabriqueClient _instance;
private Connection conn;

private FabriqueClient(){

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://webtp.fil.univ-lille1.fr/legoff?user=legoff&password=123moi");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		


}

public FabriqueClient getInstance(){
	if(_instance == null){
		this._instance = new FabriqueClient();
	}
	return this._instance;
}

public Client createNewClient(String nom,String prenom, String ville,int reduction,int date){
	Client newClient = null;
	try {
		PreparedStatement stmt = conn.prepareStatement("INSERT INTO client (nom, prenom,ville,reduction,date) VALUES(?,?,?,?,?)");
		stmt.clearParameters();
		stmt.setString(1, nom);
		stmt.setString(2, prenom);
		stmt.setString(3, ville);
		stmt.setInt(4, reduction);
		stmt.setInt(5, date);
		stmt.execute();
		
		PreparedStatement st = conn.prepareStatement("SELECT LAST_INSERT_ID() as id FROM client");
		st.clearParameters();
		ResultSet resultat = st.executeQuery();		
		resultat.next();
		int id = resultat.getInt("id");
		new Client(id,nom,prenom,ville,reduction,date);
	
	}
	catch(SQLException e){
		e.printStackTrace();
	}
	return newClient;
}

public void deleteClient(int id){
	try {
		PreparedStatement stmt = conn.prepareStatement("DELETE from client WHERE id = ?");
		stmt.clearParameters();
		stmt.setInt(1, id);			
		stmt.execute();			

	} catch (SQLException e) {
		e.printStackTrace();
		
	}
}
}
