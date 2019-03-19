package pack;

import java.util.ArrayList;
import java.util.Scanner;


public class Partie {
	private ArrayList<Joueur> joueurs;
	private Paquet paquet;
	
	public Partie(){
		this.paquet = new Paquet();
			//à remplir
		this.joueurs = new ArrayList<Joueur>();
		this.InitJoueurs();
		this.initMainDepart();
	}
	
	public void InitJoueurs() {
		System.out.println("Choisissez le nombre de joueurs entre 2 et 6");
		Scanner scanner = new Scanner(System.in);
		String phrase= "";
		int nbJoueur = 0;
		do {
			boolean entier = true;
			phrase = scanner.next();
			for(int i =0; i < phrase.length(); i++) {
				if(!phrase.substring(i, i+1).matches("[0-9]")) {
					entier = false;
				}
			}
			if(entier) {
				nbJoueur = Integer.parseInt(phrase);
			}
		}
		while(!(nbJoueur >= 2 && nbJoueur <= 6));
		//for(int i = 0; i < nbJoueur; i++) {
			
			//Scanner scan2 = new Scanner(System.in);
		int i = 1;
		String nom = "";
		do {
			System.out.println("Choisissez le nom du joueur " + (i));
			nom = scanner.next();
			Joueur j = new Joueur(nom,i);
			joueurs.add(j);
			i++;
		}
		while(i <= nbJoueur);
		
	
	}
	
	public void initMainDepart() {
		for(Joueur j : joueurs) {
			for(int i = 0; i < 4; i ++) {
				j.addDes(new Dé());
			}
			for(int i = 0; i < 3; i ++) {
				j.addCartes(paquet.piocher());
			}
		}
	}
	
	
	public boolean finJeu() {
		int nbJoueur = joueurs.size();
		
		for(Joueur j : joueurs) {
			if(j.getDes().size() == 0) {
				nbJoueur --;
			}
		}
		
		if(nbJoueur > 1) {
			return false;
		}
		
		return true;
	}
	
	public String afficherJoueurs() {
		String res = "";
		for(Joueur j : this.joueurs) {
			res += j.getNom() + "\n"; 
		}
		return res;
	}
	
	public void jeu() {
		int numJoueur= 0;
		do {
			if(!this.joueurs.get(numJoueur).passeSonTour()) {
				this.tourJoueur(numJoueur);
				this.joueurs.get(numJoueur).setPasseSonTour(false);
			}
			else {
				System.out.println("Le joueur " + this.joueurs.get(numJoueur).getNom() + " passe son tour.");
			}
			numJoueur++;
			if(numJoueur == this.joueurs.size()) {
				numJoueur=0;
			}
			if(this.joueurs.get(numJoueur).isaGagnee()) {
				numJoueur++;
			}
			
			
		}while(!finJeu());
		
	}
	
	public void tourJoueur(int numJoueur) {
		System.out.println("C'est le tour du joueur " + this.joueurs.get(numJoueur).getNom());
		this.joueurs.get(numJoueur).lancer(paquet,joueurs);
		
		
		System.out.println("voici vos cartes");
		System.out.println(this.joueurs.get(numJoueur).afficherMain());
		this.joueurs.get(numJoueur).afficherJoueurs(this.joueurs);
		//choisir une carte et appliquer effet
		this.joueurs.get(numJoueur).getMain().get(this.joueurs.get(numJoueur).selectionnerCarte()).jouer(this.joueurs.get(numJoueur), this.joueurs, this.paquet);
		if(this.joueurs.get(numJoueur).getDes().isEmpty()) {
			System.out.println("Le joueur " + this.joueurs.get(numJoueur).getNom() + " a gagné");
			int nbPasFini=0;
			for(Joueur j : this.joueurs) {
				if(!j.getDes().isEmpty()) {
					nbPasFini++;
				}
			}
			if(nbPasFini==1) {
				System.out.println("Partie Terminée");
			}
		}
	}
	
	
}
