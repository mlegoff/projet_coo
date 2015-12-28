package projet_coo.voyage.domaine;

public class Ville {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((nomVille == null) ? 0 : nomVille.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ville other = (Ville) obj;
		if (id != other.id)
			return false;
		if (nomVille == null) {
			if (other.nomVille != null)
				return false;
		} else if (!nomVille.equals(other.nomVille))
			return false;
		return true;
	}

	private String nomVille;
	private int id;
	
	public Ville(String nomVille,int id){
		this.nomVille = nomVille;
		this.id = id;
	}

	public String getNomVille() {
		return nomVille;
	}
	
	public int getid(){
		return id;
	}

	public String toString(){
		return this.nomVille;
	}
	
}
