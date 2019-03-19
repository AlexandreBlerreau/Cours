package pack;

import java.util.ArrayList;

public class CarteDonnerChoix extends Carte {

	public CarteDonnerChoix() {
		super(3);
		
	}
	CarteDonnerChoix carterose = new CarteDonnerChoix();
	Dé e = new Dé();
	@Override
	
	
	public void jouer (Joueur j1, ArrayList<Joueur> listJoueur, Paquet p) {
	//donne un dé a un joueur de votre choix
		System.out.println(j1.getNom() + ", A quel joueur voulez vous donner un Dé?");
		int jou = j1.selectionnerJoueur(listJoueur);
		listJoueur.get(jou).addDes(new Dé());
		j1.getDes().remove(0);
		
		
		
	}
	@Override
	public String [] afficherCarte() {
		String [] tab = new String [12];
		tab [0] = " _______________";
	    tab [1] = "| Cout : W W W  |"; 
		tab [2]	= "|               |" ;
		tab [3] = "| Cette carte   |" ;
	    tab [4] = "| permet de     |";
		tab [5] = "| donner l un   |" ;
		tab [6] = "| vos dés au    |"  ;
		tab [7] = "| joueur de     |" ;
		tab [8] = "| vote choix    |" ;
		tab [9] = "|               |";
		tab [10] = "|               |"; 
		tab [11] = "|_______________|";
		return tab ;
	}

	
}