package pack;

import java.util.ArrayList;

public class CarteSuppr1De extends Carte {
	
	public CarteSuppr1De() {
		super(1);
	}

	public void jouer(Joueur joueur, ArrayList<Joueur> listJoueurs, Paquet p) {
		int nbDes = joueur.getDes().size();
		if(nbDes >= 1) {
			joueur.getDes().remove(0);
		}
		System.out.println("\nNombre de dés avant action : " + nbDes + "\nNombre de dés après action : " + joueur.getDes().size());
		System.out.println("Action effectuée : " + "Suppression de " + (nbDes-joueur.getDes().size()) + "dés");
	}

	public String[] afficherCarte() {
		String[] tab = new String[12];
		tab[0]  = " _______________ ";
		tab[1]  = "|   Coût : W    |";
		tab[2]  = "|               |";
		tab[3]  = "|  Supprimez 1  |";
		tab[4]  = "|  de vos dés.  |";
		tab[5]  = "| Il sera retiré|";
		tab[6]  = "| de la partie. |";
		tab[7]  = "| Impossible de |";
		tab[8]  = "| jouer cette   |";
		tab[9]  = "|  carte si 2+  |";
		tab[10] = "|   dés 'D'.    |";
		tab[11] = "|_______________|";
		return tab;
	}

}