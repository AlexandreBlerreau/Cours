package pack;

import java.util.ArrayList;

public class PassTour extends Carte{

	public PassTour() {
		super(0);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void jouer(Joueur joueur, ArrayList<Joueur> listJoueurs, Paquet p) {
		System.out.println(joueur.getNom() + ", Sur quelle joueur voulez vous utiliser cette carte?");
		int jou = joueur.selectionnerJoueur(listJoueurs);
		listJoueurs.get(jou).setPasseSonTour(true);	
	}

	@Override
	public String[] afficherCarte() {
		String [] tab = new String [12];
		tab[0] = "  _______________ ";
		tab[1] = "| Coût : Gratuit|";
		tab[2] = "|               |";
		tab[3] = "|  Choisissez   |";
		tab[4] = "|  un joueur,   |";
		tab[5] = "|   celui-ci    |";
		tab[6] = "|  devra passer |";
		tab[7] = "|   son tour.   |";
		tab[8] = "|               |";
		tab[9] = "|               |";
		tab[10] = "|               |";
		tab[11] = "|_______________|";
		
		return tab;
	}


}

