package pack;

import java.util.ArrayList;

public class Garde1Carte extends Carte{

	public Garde1Carte() {
		super(1);
	}
	
	
		
	@Override
	public void jouer(Joueur joueur, ArrayList<Joueur> listJoueurs, Paquet p) {
		System.out.println(joueur.getNom() + ", Sur quelle joueur voulez vous utiliser cette carte?");
		int jou = joueur.selectionnerJoueur(listJoueurs);
		System.out.println(listJoueurs.get(jou).getNom() + ", quelle carte voulez vous garder?");
		listJoueurs.get(jou).afficherMain();
		int res = listJoueurs.get(jou).selectionnerCarte();
		int taille = listJoueurs.get(jou).getMain().size();
		ArrayList<Carte> nvMain = new ArrayList<Carte>();
		nvMain.add(listJoueurs.get(jou).getMain().get(res));
		listJoueurs.get(jou).setMain(nvMain);
	}
	




	@Override
	public String [] afficherCarte() {
		String [] tab = new String [12];
		tab[0] = " _______________ ";
		tab[1] = "|   Coût : W    |";
		tab[2] = "|               |";
		tab[3] = "|  Choisissez   |";
		tab[4] = "|  un joueur,   |";
		tab[5] = "|   celui-ci    |";
		tab[6] = "|  devra jeter  |";
		tab[7] = "|  toutes ses   |";
		tab[8] = "|  cartes sauf  |";
		tab[9] = "|   une qu il   |";
		tab[10] = "|   choisira.   |";
		tab[11] = "|_______________|";
		
		return tab;
				
	}





		

}
