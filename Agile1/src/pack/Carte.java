package pack;

import java.util.ArrayList;

public abstract class Carte {
	//Attributs
	private int prix;

	public Carte(int prix) {
		super();
		this.prix = prix;
	}
	
	//Methodes
	
	public boolean jouable (int nbr) {
		if (nbr == this.prix) {
			return true;
		} else {
			return false;
		}
	}

	
	public abstract void jouer(Joueur joueur, ArrayList<Joueur> listJoueurs, Paquet p);
	public abstract String[] afficherCarte();

}
