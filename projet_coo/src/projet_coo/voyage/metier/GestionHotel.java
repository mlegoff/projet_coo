package projet_coo.voyage.metier;

import java.util.List;

import projet_coo.voyage.domaine.Hotel;
import projet_coo.voyage.fabrique.FabriqueHotel;

public class GestionHotel {
	
	public List<Hotel> listeHotelParVille(int id){
		return FabriqueHotel.getInstance().hotelParId(id);
		
		
	}
	public void listChambreParHotel(int id){
		
		
	}

}
