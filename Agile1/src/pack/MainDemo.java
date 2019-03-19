package pack;

import java.util.ArrayList;
import java.util.Random;

public class MainDemo {
	
	
	
	public static void main (String[] args) {
		
		Aide a = new Aide();
		Partie partie = new Partie();
		System.out.println("\nAffichage de la liste des joueurs");
		System.out.println(partie.afficherJoueurs());
		
		ArrayList<Dé> listde = new ArrayList<Dé>();
		
		Random rand = new Random();
		
		System.out.println("Affichage d'un tirage de dés");
		for(int i = 0; i < 4; i ++) {
			listde.add(new Dé());
		}
		
		
		for(Dé d : listde) {
		System.out.print(" ---   ");
		}
		System.out.print("\n");
		
		for(Dé d : listde) {
			System.out.print(d.affichage(d.randomFace()) + "  ");
		}
		System.out.print("\n");
		for(Dé d : listde) {
			System.out.print(" ---   ");
		}
		
		
		System.out.println("\n\nAffichage de la main de carte d'un joueur (3 cartes)");
		Joueur j = new Joueur("bas" , 1);
		
		ChangeSens carte1 = new ChangeSens();
		/*for(int i = 0; i < 12; i++) {
			
			System.out.println(carte1.afficherCarte()[i]);
		}
		*/
		CarteSuppr1De carte2 = new CarteSuppr1De();
		/*for(int i = 0; i < 12; i++) {
			
			System.out.println(carte2.afficherCarte()[i]);
		}
		*/
		CarteSuppr2De carte3 = new CarteSuppr2De();
		/*for(int i = 0; i < 12; i++) {
			
			System.out.println(carte3.afficherCarte()[i]);
			
		}*/
		ArrayList<Carte> main = new ArrayList<Carte>();
		main.add(carte1);
		main.add(carte2);
		main.add(carte3);
		j.setMain(main);
		
		System.out.println(j.afficherMain());
		System.out.println("Exemple de pioche");
		Paquet paquet = new Paquet();
		for(int i = 0; i < 12; i++) {
		
			System.out.println(paquet.piocher().afficherCarte()[i]);
		}
		
	}

}
