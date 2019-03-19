package pack;

import java.util.ArrayList;
import java.util.Random;

public class Paquet {
	
	private ArrayList<Carte> paquet;
	private Random rand;
	
	public Paquet() {
		paquet = new ArrayList<Carte>();
		initPaquet();
		rand = new Random();
	}
	
	private void initPaquet() {
		
		for(int i = 0; i < 5; i ++) {
			paquet.add(new CarteSuppr2De());
		}
		for(int i = 0; i < 7; i ++) {
			paquet.add(new CarteSuppr1De());
		}
		
		/*for(int i = 0; i < 2; i ++) {
			paquet.add(new ChangeSens());
		}*/
		
		for(int i = 0; i < 4; i ++) {
			paquet.add(new Garde1Carte());
		}
		
		/*for(int i = 0; i < 4; i ++) {
			paquet.add(new Garde2Cartes());
		}*/
		
		for(int i = 0; i < 2; i ++) {
			paquet.add(new PassTour());
		}
		
		for(int i = 0; i < 3; i ++) {
			paquet.add(new Piocher3Cartes());
		}
		
		for(int i = 0; i < 3; i ++) {
			paquet.add(new PrendreCarte());
		}
		
	}
	
	public Carte piocher() {
		return paquet.get(rand.nextInt((paquet.size() -1) +1));
	}
	
}
