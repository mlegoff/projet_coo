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
import projet_coo.voyage.domaine.Reservation;
import projet_coo.voyage.domaine.Ville;
import projet_coo.voyage.domaine.Vol;

public class FabriqueReservation {
	private static FabriqueReservation _instance;
	private Connection conn;

	private FabriqueReservation(){

			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://webtp.fil.univ-lille1.fr/legoff?user=legoff&password=123moi");
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
	}

	public static FabriqueReservation getInstance(){
		if(_instance == null){
			_instance = new FabriqueReservation();
		}
		return _instance;
	}

	public Reservation createNewReservation(int idClient, int villeDepart, int villeArrive,
			int dateAller, int dateRetour, int nbVoyageurs,int idChambre,int idVolDepart,int idVolRetour){
		Reservation newReservation = null;
		try {
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO reservation (idclient,idvilledepart,idvillearrivee,nbpersonne,idvolaller,idvolretour,idchambre) VALUES(?,?,?,?,?,?,?)");
			stmt.clearParameters();
			stmt.setInt(1, idClient);
			stmt.setInt(2, villeDepart);
			stmt.setInt(3, villeArrive);
			stmt.setInt(4, nbVoyageurs);
			stmt.setInt(5, idVolDepart);
			stmt.setInt(6, idVolRetour);
			stmt.setInt(7, idChambre);
			stmt.execute();
			
			PreparedStatement st = conn.prepareStatement("SELECT LAST_INSERT_ID() as id FROM reservation");
			st.clearParameters();
			ResultSet resultat = st.executeQuery();		
			resultat.next();
			int idResa = resultat.getInt("id");
			newReservation = new Reservation(idClient,villeDepart,villeArrive,nbVoyageurs,idChambre,idResa,idVolDepart,idVolRetour);
			
		
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return newReservation;
	}

	public void deleteReservation(int id){
		try {
			PreparedStatement stmt = conn.prepareStatement("DELETE from reservation WHERE id = ?");
			stmt.clearParameters();
			stmt.setInt(1, id);			
			stmt.execute();			

		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		
	}
	
	public List<Reservation> reservationParClient(int idClient){
		List<Reservation> listResa = new ArrayList<Reservation>();
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT idreservation,idclient,idvilledepart,nbpersonne,idvolaller,idvolretour,idchambre,idvillearrivee FROM reservation where idclient = ?");
			stmt.clearParameters();
			stmt.setInt(1, idClient);			
			ResultSet resultat = stmt.executeQuery();	
			while(resultat.next()){
				Reservation tmp = new Reservation(resultat.getInt("idclient"),resultat.getInt("idvilledepart"),resultat.getInt("idvillearrivee"),resultat.getInt("nbpersonne"),resultat.getInt("idchambre"),resultat.getInt("idreservation"),resultat.getInt("idvolaller"),resultat.getInt("idvolretour"));
				listResa.add(tmp);
				
			}	
			

		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		return listResa;
		
	}
	
	
	public List<Reservation> reservationParVilleDepart(int idVille){
		List<Reservation> listResa = new ArrayList<Reservation>();
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT idreservation,idclient,idvilledepart,nbpersonne,idvolaller,idvolretour,idchambre,idvillearrivee FROM reservation where idvilledepart = ?");
			stmt.clearParameters();
			stmt.setInt(1, idVille);			
			ResultSet resultat = stmt.executeQuery();	
			while(resultat.next()){
				Reservation tmp = new Reservation(resultat.getInt("idclient"),resultat.getInt("idvilledepart"),resultat.getInt("idvillearrivee"),
						resultat.getInt("nbpersonne"),resultat.getInt("idchambre"),resultat.getInt("idreservation"),resultat.getInt("idvolaller"),resultat.getInt("idvolretour"));
				listResa.add(tmp);
				
			}	
			

		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		return listResa;
		
	}
	
	public List<Reservation> reservationParVilleArrivee(int idVille){
		List<Reservation> listResa = new ArrayList<Reservation>();
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT idreservation,idclient,datealler,dateretour,idvilledepart,nbpersonne,idvolaller,idvolretour,idchambre,idvillearrivee FROM reservation where idvillearrivee = ?");
			stmt.clearParameters();
			stmt.setInt(1, idVille);			
			ResultSet resultat = stmt.executeQuery();	
			while(resultat.next()){
				Reservation tmp = new Reservation(resultat.getInt("idclient"),resultat.getInt("idvilledepart"),resultat.getInt("idvillearrivee")
						,resultat.getInt("nbpersonne"),resultat.getInt("idchambre"),resultat.getInt("idreservation"),resultat.getInt("idvolaller"),resultat.getInt("idvolretour"));
				listResa.add(tmp);
				
			}	
			

		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		return listResa;
		
	}
	
	public List<Reservation> reservationParVol(int idVol){
		List<Reservation> listResa = new ArrayList<Reservation>();
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT idreservation,idclient,idvilledepart,nbpersonne,idvolaller,idvolretour,idchambre,idvillearrivee FROM reservation where idvolaller = ? or idvolretour = ?");
			stmt.clearParameters();
			stmt.setInt(1, idVol);			
			stmt.setInt(2, idVol);		
			ResultSet resultat = stmt.executeQuery();	
			while(resultat.next()){
				Reservation tmp = new Reservation(resultat.getInt("idclient"),resultat.getInt("idvilledepart"),resultat.getInt("idvillearrivee"),
						resultat.getInt("nbpersonne"),resultat.getInt("idchambre"),resultat.getInt("idreservation"),resultat.getInt("idvolaller"),resultat.getInt("idvolretour"));
				listResa.add(tmp);
				
			}	
			

		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		return listResa;
		
	}
public Reservation getResaById(int id){
	Reservation r = null;
	try{
		PreparedStatement stmt = conn.prepareStatement("SELECT idreservation,idclient,idvilledepart,nbpersonne,idvolaller,idvolretour,idchambre,idvillearrivee FROM reservation where idreservation = ?");
		stmt.clearParameters();
		stmt.setInt(1, id);	
		ResultSet resultat = stmt.executeQuery();
		if(resultat.next()){
				
		r = new Reservation(resultat.getInt("idclient"),resultat.getInt("idvilledepart"),resultat.getInt("idvillearrivee"),
				resultat.getInt("nbpersonne"),resultat.getInt("idchambre"),resultat.getInt("idreservation"),resultat.getInt("idvolaller"),resultat.getInt("idvolretour"));
		}
	}
	catch(SQLException e){
		e.printStackTrace();
	}
	return r;
}
public List<Reservation> getAllResa(){
	List<Reservation> listResa = new ArrayList<Reservation>();
	try {
	PreparedStatement stmt = conn.prepareStatement("SELECT idreservation,idclient,idvilledepart,nbpersonne,idvolaller,"
			+ "idvolretour,idchambre,idvillearrivee  FROM reservation");
		stmt.clearParameters();
		ResultSet resultat = stmt.executeQuery();
		while(resultat.next()){;
			Reservation tmp = new Reservation(resultat.getInt("idclient"),resultat.getInt("idvilledepart"),resultat.getInt("idvillearrivee"),
					resultat.getInt("nbpersonne"),resultat.getInt("idchambre"),resultat.getInt("idreservation"),resultat.getInt("idvolaller"),resultat.getInt("idvolretour"));
			listResa.add(tmp);
			
		}
	} catch (SQLException e) {
		
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
	return listResa;
}
public List<Reservation> searchResa(String chaine){
	List<Reservation> listResa = new ArrayList<Reservation>();
	try {
	PreparedStatement stmt = conn.prepareStatement("SELECT idreservation,idclient,idvilledepart,nbpersonne,idvolaller,"
			+ "idvolretour,idchambre,idvillearrivee  FROM reservation JOIN ville on idvillearrivee = idville"
			+ " WHERE (nom like ?) ");
		stmt.clearParameters();
		stmt.setString(1, "%"+chaine+"%");
		ResultSet resultat = stmt.executeQuery();
		while(resultat.next()){;
			Reservation tmp = new Reservation(resultat.getInt("idclient"),resultat.getInt("idvilledepart"),resultat.getInt("idvillearrivee"),
					resultat.getInt("nbpersonne"),resultat.getInt("idchambre"),resultat.getInt("idreservation"),resultat.getInt("idvolaller"),resultat.getInt("idvolretour"));
			listResa.add(tmp);
			
		}
		PreparedStatement stmt2 = conn.prepareStatement("SELECT idreservation,idclient,idvilledepart,nbpersonne,idvolaller,"
				+ "idvolretour,idchambre,idvillearrivee  FROM reservation JOIN ville on idvilledepart = idville"
				+ " WHERE (nom like ?) ");
		stmt2.clearParameters();
		stmt2.setString(1,  "%"+chaine+"%");
		ResultSet resultat2 = stmt2.executeQuery();
		while(resultat2.next()){
			Reservation tmp = new Reservation(resultat2.getInt("idclient"),resultat2.getInt("idvilledepart"),resultat2.getInt("idvillearrivee"),
					resultat2.getInt("nbpersonne"),resultat2.getInt("idchambre"),resultat2.getInt("idreservation"),resultat2.getInt("idvolaller"),resultat2.getInt("idvolretour"));
			listResa.add(tmp);
			
		}
		PreparedStatement stmt3 = conn.prepareStatement("SELECT idreservation,idclient,idvilledepart,nbpersonne,idvolaller,"
				+ "idvolretour,idchambre,idvillearrivee  FROM reservation NATURAL JOIN client WHERE (nom like ?) OR (prenom like ?)");
		stmt3.clearParameters();
		stmt3.setString(1,  "%"+chaine+"%");
		stmt3.setString(2,  "%"+chaine+"%");
		ResultSet resultat3 = stmt2.executeQuery();
		while(resultat3.next()){
			Reservation tmp = new Reservation(resultat3.getInt("idclient"),resultat3.getInt("idvilledepart"),resultat3.getInt("idvillearrivee"),
					resultat3.getInt("nbpersonne"),resultat3.getInt("idchambre"),resultat3.getInt("idreservation"),resultat3.getInt("idvolaller"),resultat3.getInt("idvolretour"));
			listResa.add(tmp);
			
		}
			
		
	} catch (SQLException e) {
		
		// TODO Auto-generated catch block
		e.printStackTrace();
	}			
	return listResa;
}
}
