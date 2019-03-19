package pack;

import java.util.ArrayList;

public class TestCarte {

	public static void main(String[] args) {
		Paquet p = new Paquet();
		ArrayList<Joueur> liste = new ArrayList<>();
		liste.add(new Joueur("Aba", 0));
		liste.add(new Joueur("Bob", 1));
		
		Joueur j1 = liste.get(0);
		Joueur j2 = liste.get(1);
		
		j1.setMain(new ArrayList<Carte>()); 
		j2.setMain(new ArrayList<Carte>());
		
		j1.getMain().add(p.piocher());
		j1.getMain().add(p.piocher());
		j1.getMain().add(p.piocher());
		
		j2.getMain().add(p.piocher());
		j2.getMain().add(p.piocher());
		j2.getMain().add(p.piocher());
		
		j1.setPouvoir(5);
		j2.setPouvoir(5);
		
		j1.addDes(new Dé()); j1.addDes(new Dé()); j1.addDes(new Dé()); j1.addDes(new Dé());
		j2.addDes(new Dé()); j2.addDes(new Dé()); j2.addDes(new Dé()); j2.addDes(new Dé());

		j1.afficherJoueurs(liste);
		System.out.println(j1.afficherMain());
		System.out.println(j2.afficherMain());
		
		System.out.println("choix");
		int ca = j1.selectionnerCarte();
		
		j1.getMain().get(ca).jouer(j1, liste, p);
		
		j1.afficherJoueurs(liste);
		System.out.println(j1.afficherMain());
		System.out.println(j2.afficherMain());
	
	
	
	
	}

}
