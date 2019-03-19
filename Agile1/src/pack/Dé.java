package pack;

import java.util.ArrayList;
import java.util.Random;

public class Dé {
	
	
	private String[]faces;
	private Random rand;
	
	
	public Dé() {
		rand = new Random();
		faces = new String[6];
		initFaces();
	}
	
	public void initFaces() {
	faces[0] = FacesDé.POUVOIR.getNom();
	faces[1] = FacesDé.POUVOIR.getNom();
	faces[2] = FacesDé.POUVOIR.getNom();
	
	faces[3] = FacesDé.PIOCHE.getNom();
	faces[4] = FacesDé.PIOCHE.getNom();
	
	faces[5] = FacesDé.DONNERDE.getNom();
	
	}
	
	public String getFace(int nb) {
		if(nb <= 5) {
		return faces[nb];
		}
		return "error";
	}
	
	public int randomFace() {
		
		return rand.nextInt(6-1) +1;
		
	}
	
	public String affichage(int nb){
		if(faces[nb].equals(FacesDé.POUVOIR.getNom())) {
			return "| W |";
		}
		
		if(faces[nb].equals(FacesDé.PIOCHE.getNom())) {
			return "| P |";
		}
		
		if(faces[nb].equals(FacesDé.DONNERDE.getNom())) {
			return "| D |";
		}

			return "error";

	}
	
	public void DePioche (Joueur joueur, Paquet p) {
		joueur.addCartes(p.piocher());
		
	}

	public void DeDonner(Joueur joueur, ArrayList<Joueur> listJoueurs) {
		System.out.println(joueur.getNom() + ", A quel joueur voulez vous donner un Dé?");
		int jou = joueur.selectionnerJoueur(listJoueurs);
		listJoueurs.get(jou).addDes(new Dé());
		joueur.getDes().remove(0);
	}
	
	
	
}




