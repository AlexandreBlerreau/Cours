package pack;

import java.util.ArrayList;

public class Piocher3Cartes extends Carte{

	public Piocher3Cartes() {
		super(1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void jouer(Joueur joueur, ArrayList<Joueur> listJoueur, Paquet p) {
		joueur.addCartes(p.piocher());
		joueur.addCartes(p.piocher());
		joueur.addCartes(p.piocher());		
	}

	@Override
	public String[] afficherCarte() {
		String [] tab = new String [12];
		tab[0] = " _______________ ";
		tab[1] = "|   Coût : W    |";
		tab[2] = "|               |";
		tab[3] = "|   Piochez 3   |";
		tab[4] = "|  cartes dans  |";
		tab[5] = "|   la pioche.  |";
		tab[6] = "|               |";
		tab[7] = "|               |";
		tab[8] = "|               |";
		tab[9] = "|               |";
		tab[10] = "|               |";
		tab[11] = "|_______________|";
		
		return tab;
		

	}


}
