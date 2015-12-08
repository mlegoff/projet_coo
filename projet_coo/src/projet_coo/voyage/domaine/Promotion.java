package projet_coo.voyage.domaine;

public class Promotion {
	
	private int idpromotion;
	private int idville;
	private int reduction;
	public int getIdpromotion() {
		return idpromotion;
	}
	public int getIdville() {
		return idville;
	}
	public int getReduction() {
		return reduction;
	}
	public Promotion(int idpromotion, int idville, int reduction) {
		super();
		this.idpromotion = idpromotion;
		this.idville = idville;
		this.reduction = reduction;
	}
	
	

}
