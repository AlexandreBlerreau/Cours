package pack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Joueur {
	private ArrayList<Carte> main;
	private ArrayList<Dé> dés;
	private String nom;
	private boolean passeSonTour = false;
	private int numJoueur;
	private boolean aGagnee = false;
	private int pouvoir = 0;
	
	public Joueur(String nom, int ind) {
		this.nom = nom;
		this.main = new ArrayList<Carte>();
		this.dés = new ArrayList<Dé>();
	}
	
	
	
	
	public int getNumJoueur() {
		return numJoueur;
	}




	public String getNom() {
		return nom;
	}

	public ArrayList<Dé> getDes() {
		return dés;
	}
	
	public void addDes(Dé d) {
		dés.add(d);
	}
	
	public void addCartes(Carte c) {
		main.add(c);
		
	}


	public void lancer(Paquet p, ArrayList<Joueur> joueurs) {
		Scanner sc = new Scanner(System.in);
		String entree =" ";
		int donnerdé = 0;
		int [] indexDonnerdé = new int[100];
		
		do {
		System.out.println("Appuyez sur 'L' pour lancer les dés\n");
		entree = sc.next();
		
		System.err.println("                                               W = pouvoir \n                                               P = pioche \n                                               D = donner un dé\n");
	
		for(Dé d : dés) {
			System.out.print(" --- ");
			}
			
			//for(Dé d : dés) {
				//System.out.print(d.affichage(d.randomFace()) + "  ");
			//}
			//System.out.print("          P = pioche");
			System.out.print("\n");
			
		
		
		}while(entree.equals(" "));
		
	
		

		int nbDéDonné = 0;
		String res = "";
		for(int i = 0; i < dés.size() ; i ++) {
			String random = dés.get(i).getFace(dés.get(i).randomFace());
			if(random.equals("pioche")){
				res += dés.get(i).affichage(3);
				//System.out.println(dés.get(i).getFace(3));
				dés.get(i).DePioche(this, p);
				
				
			}
			if(random.equals("pouvoir")) {
				res +=dés.get(i).affichage(0);
				//System.out.println(dés.get(i).getFace(0));
				this.setPouvoir(this.getPouvoir() + 1);
				
				
			}
			if(random.equals("donnerdé")) {
				res +=dés.get(i).affichage(5);
				//System.out.println(dés.get(i).getFace(5));
				nbDéDonné++;
				//dés.get(i).DeDonner(this, joueurs);
				
				
			}
			
		}
		System.out.println(res);
		for(Dé d : dés) {
			System.out.print(" --- ");
		}
		System.out.println("");
		for(int i = 0; i < nbDéDonné; i++) {
			dés.get(0).DeDonner(this, joueurs);
		}
	}


		
		

				
				
		
	
	
	public int selectionnerCarte() {
		this.afficherMain();
		System.out.println("Saisissez le numéro de la carte choisie.");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String choix = "";
		int res = 0;
		//Scanner sc = new Scanner(System.in);
		do {
		try {
			choix = br.readLine();
			res = Integer.parseInt(choix);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}while ((res < 1)&&(res > 7)) ;
		
		//if(sc.hasNext()) {
		
			//String choix = sc.next();
		//}
		//sc.close();
		
		return res-1;
	}

	
	public int selectionnerJoueur(ArrayList<Joueur> joueurs) {
		this.afficherJoueurs(joueurs);
		int res = 0;
		Scanner sc = new Scanner(System.in);
		String choix = "";
		do {
			System.out.println("Saisissez le numéro du joueur choisi.");
			choix = sc.next();
			res = Integer.parseInt(choix);
		}
		while ((res < 1) && (res > 7));
		
		
		
		return res-1;
		
	}

	public void afficherJoueurs(ArrayList<Joueur> joueurs) {
		for(int i=0; i<joueurs.size();i++) {
			System.out.print(i +1 +" - " + joueurs.get(i).getNom() + " : ");
			System.out.print(joueurs.get(i).getMain().size() + " cartes & ");
			System.out.println(joueurs.get(i).getDes().size() + " dés\n");
		}
	}

	public ArrayList<Carte> getMain() {
		return main;
	}




	public void setMain(ArrayList<Carte> main) {
		this.main = main;
	}

	public boolean passeSonTour() {
		return this.passeSonTour;
	}
	
	public void setPasseSonTour(boolean value) {
		this.passeSonTour = value;
	}
	
	public String afficherMain() {
		String[] res = new String[12];
		for (int i = 0; i < 12; i++) {
			res[i] = "";
		}
		for(Carte c : this.main) {
			for(int i = 0; i < 12; i++) {
				res[i] +=  c.afficherCarte()[i];
			}
		}
		String resultat = "";
		for (int i = 0; i < 12; i++) {
			resultat += res[i] + "\n";
		}
		return resultat;
	}




	public boolean isaGagnee() {
		return aGagnee;
	}




	public void setaGagnee(boolean aGagnee) {
		this.aGagnee = aGagnee;
	}




	public int getPouvoir() {
		return pouvoir;
	}




	public void setPouvoir(int pouvoir) {
		this.pouvoir = pouvoir;
	}
	
}