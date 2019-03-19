/*package pack;

import java.util.ArrayList;

public class Garde2Cartes extends Carte {

	public Garde2Cartes() {
		super(2);
	}

	@Override
	public void jouer(Joueur joueur, ArrayList<Joueur>listJoueurs) {
		for (int i = 0; i < listJoueurs.size(); i++) {
			if (listJoueurs.get(i).getNumJoueur() != joueur.getNumJoueur()) {
				System.out.println(listJoueurs.get(i).getNom() + ", quelles cartes voulez vous garder?");
				//TODO listJoueurs.get(i).afficherCarte();
				int res1 = listJoueurs.get(i).selectionnerCarte();
				int res2;
				do {
					res2 = listJoueurs.get(i).selectionnerCarte();
				} while (res1 == res2);
				ArrayList<Carte> nvMain = new ArrayList<Carte>();
				nvMain.add(listJoueurs.get(i).getMain().get(res1));
				nvMain.add(listJoueurs.get(i).getMain().get(res2));
				listJoueurs.get(i).setMain(nvMain);
			}
		}
	}
	
	public String [] afficherCarte() {
		String [] tab = new String [12];
		tab[0] = " _______________ ";
		tab[1] = "|  Coût : W W   |";
		tab[2] = "|               |";
		tab[3] = "|   Tous les    |";
		tab[4] = "| joueurs sauf  |";
		tab[5] = "|  vous n'ont   |";
		tab[6] = "|  plus que 2   |";
		tab[7] = "|  cartes. Ils  |";
		tab[8] = "|  choisissent  |";
		tab[9] = "| celles qu'ils |";
		tab[10] = "|  conservent.  |";
		tab[11] = "|_______________|";
		
		return tab;

	}

	@Override
	public void jouer(Joueur joueur, Paquet p) {
		// TODO Auto-generated method stub
		
	}
}*/
