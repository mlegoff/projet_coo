package projet_coo.voyage.fabrique;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import projet_coo.voyage.domaine.Client;
import projet_coo.voyage.domaine.Vol;

public class FabriqueClient {
private static FabriqueClient _instance;
private Connection conn;

private FabriqueClient(){

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://webtp.fil.univ-lille1.fr/legoff?user=legoff&password=123moi");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		


}

public static FabriqueClient getInstance(){
	if(_instance == null){
		_instance = new FabriqueClient();
	}
	return _instance;
}

public Client createNewClient(String nom,String prenom, int ville,Date date){
	Client newClient = null;
	try {
		PreparedStatement stmt = conn.prepareStatement("INSERT INTO client (nom, prenom,ville,datenaissance) VALUES(?,?,?,?)");
		stmt.clearParameters();
		stmt.setString(1, nom);
		stmt.setString(2, prenom);
		stmt.setInt(3, ville);
		java.sql.Date dateSQL = new java.sql.Date(date.getTime());
		stmt.setDate(4, dateSQL);
		stmt.execute();
		
		PreparedStatement st = conn.prepareStatement("SELECT LAST_INSERT_ID() as id FROM client");
		st.clearParameters();
		ResultSet resultat = st.executeQuery();		
		resultat.next();
		int id = resultat.getInt("id");
		newClient = new Client(id,nom,prenom,ville,date);
	
	}
	catch(SQLException e){
		e.printStackTrace();
	}
	return newClient;
}

public void deleteClient(int id){
	try {
		PreparedStatement stmt = conn.prepareStatement("DELETE from client WHERE idclient = ?");
		stmt.clearParameters();
		stmt.setInt(1, id);			
		stmt.execute();			

	} catch (SQLException e) {
		e.printStackTrace();
		
	}
}

public List<Client> getAllClients(){
	List<Client> listClient = new ArrayList<Client>();
	try {
		PreparedStatement stmt = conn.prepareStatement("SELECT idclient,nom,prenom,ville,datenaissance  FROM client ");
		stmt.clearParameters();			
		ResultSet resultat = stmt.executeQuery();	
		while(resultat.next()){
			java.sql.Date dte = resultat.getDate("datenaissance");
			Date dateTmp = new Date(dte.getTime());
			int idclient = resultat.getInt("idclient");
			Client tmp = new Client(idclient,resultat.getString("nom"),resultat.getString("prenom"),resultat.getInt("ville"),dateTmp);
			listClient.add(tmp);
			
		}	
		

	} catch (SQLException e) {
		e.printStackTrace();
		
	}
	return listClient;
}

public List<Client> searchClient(String chaine){
	List<Client> listClient = new ArrayList<Client>();
	try {
	PreparedStatement stmt = conn.prepareStatement("SELECT idclient,nom,prenom,ville,datenaissance FROM client JOIN ville on ville = idville WHERE (nom like ?) ");
		stmt.clearParameters();
		stmt.setString(1, "%"+chaine+"%");
		ResultSet resultat = stmt.executeQuery();
		while(resultat.next()){
			java.sql.Date dte = resultat.getDate("date");
			Date dateTmp = new Date(dte.getTime());
			Client tmp = new Client(resultat.getInt("idclient"),resultat.getString("nom"),resultat.getString("prenom"),resultat.getInt("ville"),dateTmp);
			listClient.add(tmp);
			
		}
		PreparedStatement stmt2 = conn.prepareStatement("SELECT idclient,nom,prenom,ville,datenaissance FROM client JOIN ville on ville = idville WHERE (prenom like ?) ");
		stmt.clearParameters();
		stmt2.setString(1,  "%"+chaine+"%");
		ResultSet resultat2 = stmt2.executeQuery();
		while(resultat2.next()){
			java.sql.Date dte = resultat.getDate("date");
			Date dateTmp = new Date(dte.getTime());
			Client tmp = new Client(resultat2.getInt("idclient"),resultat2.getString("nom"),resultat2.getString("prenom"),resultat2.getInt("ville"),dateTmp);
			listClient.add(tmp);
			
		}
			
		
	} catch (SQLException e) {
		
		// TODO Auto-generated catch block
		e.printStackTrace();
	}			
	return listClient;
}

public Client getClientById(int id){
	Client c = null;
	try{
		PreparedStatement stmt = conn.prepareStatement("SELECT idclient,nom,prenom,ville,datenaissance  FROM client where idclient = ?");
		stmt.clearParameters();	
		stmt.setInt(1,id);
		ResultSet resultat = stmt.executeQuery();	
		if(resultat.next()){
		java.sql.Date dte = resultat.getDate("datenaissance");
		Date dateTmp = new Date(dte.getTime());
		c = new Client(resultat.getInt("idclient"),resultat.getString("nom"),resultat.getString("prenom"),resultat.getInt("ville"),dateTmp);
		}
		
	}
	catch(SQLException e){
		
	}
	return c;
}
}
