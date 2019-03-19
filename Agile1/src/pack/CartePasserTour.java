package pack;

import java.util.ArrayList;
import java.util.Scanner;

public class CartePasserTour extends Carte {
	
	Scanner sc;

	public CartePasserTour() {
		super(0);
		sc = new Scanner(System.in);
		
	}
	
	

	@Override
	public void jouer(Joueur joueur, ArrayList<Joueur> listJoueur, Paquet p) {
		System.out.println("Choissiez le joueur à qui vous voulez faire passer son tour(nom): ");
		String nom = sc.next();
		
		//recherche du nom dans l'arraylist de joueur et jouer.setPasserSonTour(true);
		
		
	}



	@Override
	public String[] afficherCarte() {
		// TODO Auto-generated method stub
		return null;
	}



}
