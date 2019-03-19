package pack;

import java.util.ArrayList;

public class PrendreCarte extends Carte{

	public PrendreCarte() {
		super(1);
		
	}

	@Override
	public void jouer(Joueur joueur, ArrayList<Joueur> listJoueurs, Paquet p) {
		System.out.println(joueur.getNom() + ", A quel joueur voulez vous prendre une carte?");
		int jou = joueur.selectionnerJoueur(listJoueurs);
		int res = (int)(Math.random() * (listJoueurs.get(jou).getMain().size() + 1));
		joueur.getMain().add(listJoueurs.get(jou).getMain().get(res));	
		listJoueurs.get(jou).getMain().remove(res);
		
	}

	@Override
	public String[] afficherCarte() {
		String [] tab = new String [12];
		tab[0] = "  _______________ ";
		tab[1] = "|   Coût : W    |";
		tab[2] = "|               |";
		tab[3] = "|  Prenez une   |";
		tab[4] = "|  carte à un   |";
		tab[5] = "|  joueur de    |";
		tab[6] = "|  votre choix. |";
		tab[7] = "|               |";
		tab[8] = "|               |";
		tab[9] = "|               |";
		tab[10] = "|               |";
		tab[11] = "|_______________|";
		
		return tab;
	}


}

