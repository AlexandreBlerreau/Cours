package pack;

import java.util.ArrayList;

public class CarteSuppr2De extends Carte {
	
	public CarteSuppr2De() {
		super(3);
	}

	public void jouer(Joueur joueur, ArrayList<Joueur> listJoueurs, Paquet p) {
		int nbDes = joueur.getDes().size();
		if(nbDes == 1) {
			joueur.getDes().remove(0);
		}
		if(nbDes >= 2) {
			joueur.getDes().remove(0);
			joueur.getDes().remove(0);
		}
		System.out.println("\nNombre de dés avant action : " + nbDes + "\nNombre de dés après action : " + joueur.getDes().size());
		System.out.println("Action effectuée : " + "Suppression de " + (nbDes-joueur.getDes().size()) + "dés");
	}
	
	public String[] afficherCarte() {
		String[] tab = new String[12];		
		tab[0]  = " _______________ ";
		tab[1]  = "| Coût : W W W  |";
		tab[2]  = "|               |";
		tab[3]  = "|  Supprimez 2  |";
		tab[4]  = "|de vos dés. Ils|";
		tab[5]  = "| seront retirés|";
		tab[6]  = "| de la partie. |";
		tab[7]  = "|               |";
		tab[8]  = "|               |";
		tab[9]  = "|               |";
		tab[10] = "|               |";
		tab[11] = "|_______________|";
		return tab;
	}

}


