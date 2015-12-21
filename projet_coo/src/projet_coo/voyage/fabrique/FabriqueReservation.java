package projet_coo.voyage.fabrique;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import projet_coo.voyage.domaine.Reservation;
import projet_coo.voyage.domaine.Vol;

public class FabriqueReservation {
	private FabriqueReservation _instance;
	private Connection conn;

	private FabriqueReservation(){

			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://webtp.fil.univ-lille1.fr/legoff?user=legoff&password=123moi");
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			


	}

	public FabriqueReservation getInstance(){
		if(_instance == null){
			this._instance = new FabriqueReservation();
		}
		return this._instance;
	}

	public Reservation createNewReservation(int idClient, int villeDepart, int villeArrive,
			int dateAller, int dateRetour, int nbVoyageurs,int idChambre,int idVolDepart,int idVolRetour){
		Reservation newReservation = null;
		try {
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO reservation (idclient,datealler,dateretour,idvilledepart,idvillearrivee,nbpersonne,idvolaller,idvolretour,idchambre) VALUES(?,?,?,?,?,?,?,?,?)");
			stmt.clearParameters();
			stmt.setInt(1, idClient);
			stmt.setInt(2, dateAller);
			stmt.setInt(3, dateRetour);
			stmt.setInt(4, villeDepart);
			stmt.setInt(5, villeArrive);
			stmt.setInt(6, nbVoyageurs);
			stmt.setInt(7, idVolDepart);
			stmt.setInt(8, idVolRetour);
			stmt.setInt(9, idChambre);
			stmt.execute();
			
			PreparedStatement st = conn.prepareStatement("SELECT LAST_INSERT_ID() as id FROM reservation");
			st.clearParameters();
			ResultSet resultat = st.executeQuery();		
			resultat.next();
			int idResa = resultat.getInt("id");
			newReservation = new Reservation(idClient,villeDepart,villeArrive,dateAller,dateRetour,nbVoyageurs,idChambre,idResa,idVolDepart,idVolRetour);
			
		
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
			PreparedStatement stmt = conn.prepareStatement("SELECT idreservation,idclient,datealler,dateretour,idvilledepart,nbpersonne,idvolaller,idvolretour,idchambre,idvillearrivee FROM reservation where idclient = ?");
			stmt.clearParameters();
			stmt.setInt(1, idClient);			
			ResultSet resultat = stmt.executeQuery();	
			while(resultat.next()){
				Reservation tmp = new Reservation(resultat.getInt("idclient"),resultat.getInt("idvilledepart"),resultat.getInt("idvillearrivee"),resultat.getInt("datealler"),
						resultat.getInt("dateretour"),resultat.getInt("nbpersonne"),resultat.getInt("idchambre"),resultat.getInt("idreservation"),resultat.getInt("idvolaller"),resultat.getInt("idvolretour"));
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
			PreparedStatement stmt = conn.prepareStatement("SELECT idreservation,idclient,datealler,dateretour,idvilledepart,nbpersonne,idvolaller,idvolretour,idchambre,idvillearrivee FROM reservation where idvilledepart = ?");
			stmt.clearParameters();
			stmt.setInt(1, idVille);			
			ResultSet resultat = stmt.executeQuery();	
			while(resultat.next()){
				Reservation tmp = new Reservation(resultat.getInt("idclient"),resultat.getInt("idvilledepart"),resultat.getInt("idvillearrivee"),resultat.getInt("datealler"),
						resultat.getInt("dateretour"),resultat.getInt("nbpersonne"),resultat.getInt("idchambre"),resultat.getInt("idreservation"),resultat.getInt("idvolaller"),resultat.getInt("idvolretour"));
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
				Reservation tmp = new Reservation(resultat.getInt("idclient"),resultat.getInt("idvilledepart"),resultat.getInt("idvillearrivee"),resultat.getInt("datealler"),
						resultat.getInt("dateretour"),resultat.getInt("nbpersonne"),resultat.getInt("idchambre"),resultat.getInt("idreservation"),resultat.getInt("idvolaller"),resultat.getInt("idvolretour"));
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
			PreparedStatement stmt = conn.prepareStatement("SELECT idreservation,idclient,datealler,dateretour,idvilledepart,nbpersonne,idvolaller,idvolretour,idchambre,idvillearrivee FROM reservation where idvolaller = ? or idvolretour = ?");
			stmt.clearParameters();
			stmt.setInt(1, idVol);			
			stmt.setInt(2, idVol);		
			ResultSet resultat = stmt.executeQuery();	
			while(resultat.next()){
				Reservation tmp = new Reservation(resultat.getInt("idclient"),resultat.getInt("idvilledepart"),resultat.getInt("idvillearrivee"),resultat.getInt("datealler"),
						resultat.getInt("dateretour"),resultat.getInt("nbpersonne"),resultat.getInt("idchambre"),resultat.getInt("idreservation"),resultat.getInt("idvolaller"),resultat.getInt("idvolretour"));
				listResa.add(tmp);
				
			}	
			

		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		return listResa;
		
	}

}
