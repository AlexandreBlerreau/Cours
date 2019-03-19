package pack;

public enum FacesDé {
	
	POUVOIR("pouvoir"),
	PIOCHE("pioche"),
	DONNERDE("donnerdé");
	
	private String nom; 
	
	FacesDé(String nom) {
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}

}
