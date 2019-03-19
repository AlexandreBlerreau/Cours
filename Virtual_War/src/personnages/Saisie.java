package personnages;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Saisie {

	static String message;
	private Scanner unScanner;
	static JOptionPane jop1;
	public static ImageIcon iconQ = new ImageIcon("image/iconQ.png");
	public static ImageIcon iconOk = new ImageIcon("image/iconS.png");
	public static int nbrobot = 0;
	public static int nbrobotB = 0;

	public Saisie(String message) {
		this.message = message;
		jop1 = new JOptionPane();
		unScanner = new Scanner(System.in);

	}

	public static Coord DeplacerDemande(int equipe) {

<<<<<<< HEAD
		Coord[] choixA = new Coord[Jouer.infoRobotA.size()];

		String[] typeA = new String[5];

		Coord[] choixB = new Coord[Jouer.infoRobotB.size()];
=======
		Coord[] choixA = new Coord[EssaiIa.infoRobotA.size()];

		String[] typeA = new String[5];

		Coord[] choixB = new Coord[EssaiIa.infoRobotB.size()];
>>>>>>> 01e94f70828b0814cc4ad87c39ee15c5da184990

		String[] typeB = new String[5];

		if (equipe == 1) {

<<<<<<< HEAD
			for (Robot r : Jouer.infoRobotA) {

				choixA[Jouer.infoRobotA.indexOf(r)] = r.getCoordonnee();
				typeA[Jouer.infoRobotA.indexOf(r)] = r.getType();
=======
			for (Robot r : EssaiIa.infoRobotA) {

				choixA[EssaiIa.infoRobotA.indexOf(r)] = r.getCoordonnee();
				typeA[EssaiIa.infoRobotA.indexOf(r)] = r.getType();
>>>>>>> 01e94f70828b0814cc4ad87c39ee15c5da184990

			}
			return (Coord) JOptionPane.showInputDialog(null, "Quel robot voulez vous bouger?", "Deplacement",
					JOptionPane.QUESTION_MESSAGE, iconQ, choixA, choixA[0].toString() + "hello");

		}

		if (equipe == 2) {

<<<<<<< HEAD
			for (Robot r : Jouer.infoRobotB) {

				choixB[Jouer.infoRobotB.indexOf(r)] = r.getCoordonnee();
				typeB[Jouer.infoRobotB.indexOf(r)] = r.getType();
=======
			for (Robot r : EssaiIa.infoRobotB) {

				choixB[EssaiIa.infoRobotB.indexOf(r)] = r.getCoordonnee();
				typeB[EssaiIa.infoRobotB.indexOf(r)] = r.getType();
>>>>>>> 01e94f70828b0814cc4ad87c39ee15c5da184990

			}
			return (Coord) JOptionPane.showInputDialog(null, "Quel robot voulez vous bouger?", "Deplacement",
					JOptionPane.QUESTION_MESSAGE, iconQ, choixB, choixB[0].toString());

		}
		return null;

	}
	public static Coord DeplacerDemandeIa(int equipe) {

<<<<<<< HEAD
		Coord[] choixA = new Coord[Jouer.infoRobotA.size()];

		String[] typeA = new String[5];

		Coord[] choixB = new Coord[Jouer.infoRobotB.size()];
=======
		Coord[] choixA = new Coord[EssaiIa.infoRobotA.size()];

		String[] typeA = new String[5];

		Coord[] choixB = new Coord[EssaiIa.infoRobotB.size()];
>>>>>>> 01e94f70828b0814cc4ad87c39ee15c5da184990

		String[] typeB = new String[5];

		if (equipe == 1) {

<<<<<<< HEAD
			for (Robot r : Jouer.infoRobotA) {

				choixA[Jouer.infoRobotA.indexOf(r)] = r.getCoordonnee();
				typeA[Jouer.infoRobotA.indexOf(r)] = r.getType();
=======
			for (Robot r : EssaiIa.infoRobotA) {

				choixA[EssaiIa.infoRobotA.indexOf(r)] = r.getCoordonnee();
				typeA[EssaiIa.infoRobotA.indexOf(r)] = r.getType();
>>>>>>> 01e94f70828b0814cc4ad87c39ee15c5da184990

			}
			return (Coord) JOptionPane.showInputDialog(null, "Quel robot voulez vous bouger?", "Deplacement",
					JOptionPane.QUESTION_MESSAGE, iconQ, choixA, choixA[0].toString() + "hello");

		}

		if (equipe == 2) {

<<<<<<< HEAD
			for (Robot r : Jouer.infoRobotB) {

				choixB[Jouer.infoRobotB.indexOf(r)] = r.getCoordonnee();
				typeB[Jouer.infoRobotB.indexOf(r)] = r.getType();
=======
			for (Robot r : EssaiIa.infoRobotB) {

				choixB[EssaiIa.infoRobotB.indexOf(r)] = r.getCoordonnee();
				typeB[EssaiIa.infoRobotB.indexOf(r)] = r.getType();
>>>>>>> 01e94f70828b0814cc4ad87c39ee15c5da184990

			}
			return (Coord) JOptionPane.showInputDialog(null, "Quel robot voulez vous bouger?", "Deplacement",
					JOptionPane.QUESTION_MESSAGE, iconQ, choixB, choixB[0].toString());

		}
		return null;

	}
	
	public static Coord ActionDemande(int equipe) {

<<<<<<< HEAD
		Coord[] choixA = new Coord[Jouer.infoRobotA.size()];

		String[] typeA = new String[5];

		Coord[] choixB = new Coord[Jouer.infoRobotB.size()];
=======
		Coord[] choixA = new Coord[EssaiIa.infoRobotA.size()];

		String[] typeA = new String[5];

		Coord[] choixB = new Coord[EssaiIa.infoRobotB.size()];
>>>>>>> 01e94f70828b0814cc4ad87c39ee15c5da184990

		String[] typeB = new String[5];

		if (equipe == 1) {

<<<<<<< HEAD
			for (Robot r : Jouer.infoRobotA) {

				choixA[Jouer.infoRobotA.indexOf(r)] = r.getCoordonnee();
				typeA[Jouer.infoRobotA.indexOf(r)] = r.getType();
=======
			for (Robot r : EssaiIa.infoRobotA) {

				choixA[EssaiIa.infoRobotA.indexOf(r)] = r.getCoordonnee();
				typeA[EssaiIa.infoRobotA.indexOf(r)] = r.getType();
>>>>>>> 01e94f70828b0814cc4ad87c39ee15c5da184990

			}
			return (Coord) JOptionPane.showInputDialog(null, "Quel robot voulez vous utiliser?", "Attaquer",
					JOptionPane.QUESTION_MESSAGE, iconQ, choixA, choixA[0].toString() + "hello");

		}

		if (equipe == 2) {

<<<<<<< HEAD
			for (Robot r : Jouer.infoRobotB) {

				choixB[Jouer.infoRobotB.indexOf(r)] = r.getCoordonnee();
				typeB[Jouer.infoRobotB.indexOf(r)] = r.getType();
=======
			for (Robot r : EssaiIa.infoRobotB) {

				choixB[EssaiIa.infoRobotB.indexOf(r)] = r.getCoordonnee();
				typeB[EssaiIa.infoRobotB.indexOf(r)] = r.getType();
>>>>>>> 01e94f70828b0814cc4ad87c39ee15c5da184990

			}
			return (Coord) JOptionPane.showInputDialog(null, "Quel robot voulez vous utiliser?", "Attaquer",
					JOptionPane.QUESTION_MESSAGE, iconQ, choixB, choixB[0].toString());

		}
		return null;

	}
	
	public static Coord ActionDemandeIa(int equipe) {

<<<<<<< HEAD
		Coord[] choixA = new Coord[Jouer.infoRobotA.size()];

		String[] typeA = new String[5];

		Coord[] choixB = new Coord[Jouer.infoRobotB.size()];
=======
		Coord[] choixA = new Coord[EssaiIa.infoRobotA.size()];

		String[] typeA = new String[5];

		Coord[] choixB = new Coord[EssaiIa.infoRobotB.size()];
>>>>>>> 01e94f70828b0814cc4ad87c39ee15c5da184990

		String[] typeB = new String[5];

		if (equipe == 1) {

<<<<<<< HEAD
			for (Robot r : Jouer.infoRobotA) {

				choixA[Jouer.infoRobotA.indexOf(r)] = r.getCoordonnee();
				typeA[Jouer.infoRobotA.indexOf(r)] = r.getType();
=======
			for (Robot r : EssaiIa.infoRobotA) {

				choixA[EssaiIa.infoRobotA.indexOf(r)] = r.getCoordonnee();
				typeA[EssaiIa.infoRobotA.indexOf(r)] = r.getType();
>>>>>>> 01e94f70828b0814cc4ad87c39ee15c5da184990

			}
			return (Coord) JOptionPane.showInputDialog(null, "Quel robot voulez vous utiliser?", "Attaquer",
					JOptionPane.QUESTION_MESSAGE, iconQ, choixA, choixA[0].toString() + "hello");

		}

		if (equipe == 2) {

<<<<<<< HEAD
			for (Robot r : Jouer.infoRobotB) {

				choixB[Jouer.infoRobotB.indexOf(r)] = r.getCoordonnee();
				typeB[Jouer.infoRobotB.indexOf(r)] = r.getType();
=======
			for (Robot r : EssaiIa.infoRobotB) {

				choixB[EssaiIa.infoRobotB.indexOf(r)] = r.getCoordonnee();
				typeB[EssaiIa.infoRobotB.indexOf(r)] = r.getType();
>>>>>>> 01e94f70828b0814cc4ad87c39ee15c5da184990

			}
			return (Coord) JOptionPane.showInputDialog(null, "Quel robot voulez vous utiliser?", "Attaquer",
					JOptionPane.QUESTION_MESSAGE, iconQ, choixB, choixB[0].toString());

		}
		return null;

	}

	public static String queVoulezVousFaire(int equipe) {
		String[] choix = { "Bouger un robot", "Sortir un robot de la base", "Tirer/Poser une mine" };
		String[] choix0 = { "Sortir un robot de la base" };
		String nom = "";
		JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
<<<<<<< HEAD
		if (equipe == 1 && Jouer.robotSortiA > 0) {
			nom = (String) jop.showInputDialog(null, "Que voulez-vous faire ?", "Choix action",
					JOptionPane.QUESTION_MESSAGE, iconQ, choix, choix[0]);
		} else if (equipe == 1 && Jouer.robotSortiA == 0) {
			nom = (String) jop.showInputDialog(null, "Que voulez-vous faire ?", "Choix action",
					JOptionPane.QUESTION_MESSAGE, iconQ, choix0, choix0[0]);

		} else if (equipe == 2 && Jouer.robotSortiB > 0) {
			nom = (String) jop.showInputDialog(null, "Que voulez-vous faire ?", "Choix action",
					JOptionPane.QUESTION_MESSAGE, iconQ, choix, choix[0]);

		} else if (equipe == 2 && Jouer.robotSortiB == 0) {
=======
		if (equipe == 1 && EssaiIa.robotSortiA > 0) {
			nom = (String) jop.showInputDialog(null, "Que voulez-vous faire ?", "Choix action",
					JOptionPane.QUESTION_MESSAGE, iconQ, choix, choix[0]);
		} else if (equipe == 1 && EssaiIa.robotSortiA == 0) {
			nom = (String) jop.showInputDialog(null, "Que voulez-vous faire ?", "Choix action",
					JOptionPane.QUESTION_MESSAGE, iconQ, choix0, choix0[0]);

		} else if (equipe == 2 && EssaiIa.robotSortiB > 0) {
			nom = (String) jop.showInputDialog(null, "Que voulez-vous faire ?", "Choix action",
					JOptionPane.QUESTION_MESSAGE, iconQ, choix, choix[0]);

		} else if (equipe == 2 && EssaiIa.robotSortiB == 0) {
>>>>>>> 01e94f70828b0814cc4ad87c39ee15c5da184990
			nom = (String) jop.showInputDialog(null, "Que voulez-vous faire ?", "Choix action",
					JOptionPane.QUESTION_MESSAGE, iconQ, choix0, choix0[0]);

		}

		return nom;
	}
	public static String queVoulezVousFaireIa(int equipe) {
		String[] choix = { "Bouger un robot", "Sortir un robot de la base", "Tirer/Poser une mine" };
		String[] choix0 = { "Sortir un robot de la base" };
		String nom = "";
		JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
<<<<<<< HEAD
		if (equipe == 1 && Jouer.robotSortiA > 0) {
			nom = (String) jop.showInputDialog(null, "Que voulez-vous faire ?", "Choix action",
					JOptionPane.QUESTION_MESSAGE, iconQ, choix, choix[0]);
		} else if (equipe == 1 && Jouer.robotSortiA == 0) {
			nom = (String) jop.showInputDialog(null, "Que voulez-vous faire ?", "Choix action",
					JOptionPane.QUESTION_MESSAGE, iconQ, choix0, choix0[0]);

		} else if (equipe == 2 && Jouer.robotSortiB > 0) {
			nom = (String) jop.showInputDialog(null, "Que voulez-vous faire ?", "Choix action",
					JOptionPane.QUESTION_MESSAGE, iconQ, choix, choix[0]);

		} else if (equipe == 2 && Jouer.robotSortiB == 0) {
=======
		if (equipe == 1 && EssaiIa.robotSortiA > 0) {
			nom = (String) jop.showInputDialog(null, "Que voulez-vous faire ?", "Choix action",
					JOptionPane.QUESTION_MESSAGE, iconQ, choix, choix[0]);
		} else if (equipe == 1 && EssaiIa.robotSortiA == 0) {
			nom = (String) jop.showInputDialog(null, "Que voulez-vous faire ?", "Choix action",
					JOptionPane.QUESTION_MESSAGE, iconQ, choix0, choix0[0]);

		} else if (equipe == 2 && EssaiIa.robotSortiB > 0) {
			nom = (String) jop.showInputDialog(null, "Que voulez-vous faire ?", "Choix action",
					JOptionPane.QUESTION_MESSAGE, iconQ, choix, choix[0]);

		} else if (equipe == 2 && EssaiIa.robotSortiB == 0) {
>>>>>>> 01e94f70828b0814cc4ad87c39ee15c5da184990
			nom = (String) jop.showInputDialog(null, "Que voulez-vous faire ?", "Choix action",
					JOptionPane.QUESTION_MESSAGE, iconQ, choix0, choix0[0]);

		}

		return nom;
	}

	public static String QuelType() {

		String[] choix = { "Tireur", "Piegeur", "Char" };
		return (String) JOptionPane.showInputDialog(null, "Quel type de robot voulez vous bouger?",
				"Sortir un Robot de la base", JOptionPane.QUESTION_MESSAGE, iconQ, choix, choix[0]);

	}

	public static void presentation() {
		jop1 = new JOptionPane();
		ImageIcon img = new ImageIcon("image/valiant_hearts-0.jpg");
		jop1.showMessageDialog(null,
				"Bienvenue sur Virtual War \n Codé par Geoffrey, Louis, Gauthier, Raphaël, Alexandre", "Bienvenue",
				JOptionPane.INFORMATION_MESSAGE, img);
	}

	public static Coord choixCoord() {
		JOptionPane jop = new JOptionPane();
		JOptionPane jop1 = new JOptionPane();
		int x = 0;
		int y = 0;
		String b = "";
		do {

			b = (String) jop.showInputDialog(null, "Ligne ?", "Choix déplacement", JOptionPane.QUESTION_MESSAGE, iconQ,
					null, "");

			if (estNum(b)) {
				x = Integer.parseInt(b);
			}
			if (x < 0 || x > 20) {
				jop.showMessageDialog(null, "Votre ligne n'est pas correcte", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
		} while (x > 20 || x < 0 || !estNum(b));

		do {
			b = (String) jop.showInputDialog(null, "Quel est sa colonne ?", "Choix déplacement",
					JOptionPane.QUESTION_MESSAGE, iconQ, null, "");
			if (estNum(b)) {
				y = Integer.parseInt(b);
			}
			if (y > 20 || y < 0) {
				jop.showMessageDialog(null, "Votre ordonnée n'est pas correcte", "Erreur", JOptionPane.ERROR_MESSAGE);

			}
		} while (y > 20 || y < 0 || !estNum(b));
		return new Coord(x, y);

	}

	public static Coord ChoixList(Robot r){
		
		ArrayList<Coord> list = new ArrayList<Coord>();
		
		for(int i = 0; i < Plateau.grille.length; i ++){
			for(int j = 0; j < Plateau.grille[0].length; j ++){
				
				Deplacement tmp = new Deplacement(r,Plateau.grille[i][j]);
				
				if(r instanceof Tireur || r instanceof Piegeur){
				if(tmp.peutYAller()){
					list.add(Plateau.grille[i][j].getCoordCell());
				}
			}
				 else if(r instanceof Char){
					 if(tmp.peutYAllerChar()){
						 list.add(Plateau.grille[i][j].getCoordCell());
					 }
				 }
				 }
		
			
		}
		
		Coord choixList[] = new Coord [list.size()];
		
		for(Coord c : list){
			
			choixList[list.indexOf(c)] = c;
			
		}
		return (Coord) JOptionPane.showInputDialog(null, "Choisissez ou vous voulez déplacer votre robot", "Deplacement",
				JOptionPane.QUESTION_MESSAGE, iconQ, choixList, choixList[0].toString());
		
		
	
		
	}
public static ArrayList<Coord> ChoixListIa(Robot r){
		
		ArrayList<Coord> list = new ArrayList<Coord>();
		
		for(int i = 0; i < Plateau.grille.length; i ++){
			for(int j = 0; j < Plateau.grille[0].length; j ++){
				
				Deplacement tmp = new Deplacement(r,Plateau.grille[i][j]);
				
				if(r instanceof Tireur || r instanceof Piegeur){
				if(tmp.peutYAller()){
					list.add(Plateau.grille[i][j].getCoordCell());
				}
			}
				 else if(r instanceof Char){
					 if(tmp.peutYAllerChar()){
						 list.add(Plateau.grille[i][j].getCoordCell());
					 }
				 }
				 }
		
			
		}
		
	
		return list;
		
	
		
	}

	public static Coord choixAction() {
		JOptionPane jop = new JOptionPane();
		JOptionPane jop1 = new JOptionPane();
		int x = 0;
		int y = 0;
		String b = "";
		do {

			b = (String) jop.showInputDialog(null, "Ligne ?", "Choix attaque", JOptionPane.QUESTION_MESSAGE, iconQ,
					null, "");

			if (estNum(b)) {
				x = Integer.parseInt(b);
			}
			if (x < 0 || x > 20) {
				jop.showMessageDialog(null, "Votre ligne n'est pas correcte", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
		} while (x > 20 || x < 0 || !estNum(b));

		do {
			b = (String) jop.showInputDialog(null, "Quel est sa colonne ?", "Choix attaque",
					JOptionPane.QUESTION_MESSAGE, iconQ, null, "");
			if (estNum(b)) {
				y = Integer.parseInt(b);
			}
			if (y > 20 || y < 0) {
				jop.showMessageDialog(null, "Votre ordonnée n'est pas correcte", "Erreur", JOptionPane.ERROR_MESSAGE);

			}
		} while (y > 20 || y < 0 || !estNum(b));
		return new Coord(x, y);

	}
	
	public static Coord listAction(Robot r){
		
	ArrayList<Coord> list = new ArrayList<Coord>();
		
		for(int i = 0; i < Plateau.grille.length; i ++){
			for(int j = 0; j < Plateau.grille[0].length; j ++){
				
				Attaque tmp = new Attaque(r,Plateau.grille[i][j]);
				
				if(r instanceof Tireur){
				if(tmp.tirer(Plateau.grille[i][j])){
					list.add(Plateau.grille[i][j].getCoordCell());
				}
			}
				 else if(r instanceof Piegeur){
					 if(tmp.poserMine(Plateau.grille[i][j])){
						 list.add(Plateau.grille[i][j].getCoordCell());
					 }
				 }
				 else if(r instanceof Char){
					 if(tmp.tirerTank(Plateau.grille[i][j])){
						 list.add(Plateau.grille[i][j].getCoordCell());
					 }
				 }
				 }
		
			
		}
		
		Coord choixList[] = new Coord [list.size()];
		
		for(Coord c : list){
			
			choixList[list.indexOf(c)] = c;
			
		}
		return (Coord) JOptionPane.showInputDialog(null, "Choisissez ou vous voulez attaquer", "Attaquer",
				JOptionPane.QUESTION_MESSAGE, iconQ, choixList, choixList[0].toString());
		
		
		
	}
	

	public static String SaisieString() {
		// System.out.println(message + ": ");
		return jop1.showInputDialog(null, message, "Mettez nous 20 svp");
	}

	public static boolean estNum(String chaine) {
		for (int i = 0; i < chaine.length(); i++) {
			if ((int) chaine.charAt(i) < 48 || (int) chaine.charAt(i) > 57) {
				return false;
			}
		}
		if (chaine.equals("")) {
			return false;
		}
		return true;
	}

	public static int SaisieInt() {
		// System.out.println(message + ": ");
		String mess = jop1.showInputDialog(null, message, "42");
		return Integer.parseInt(mess);
	}

	public void setMessage(String message) {

		this.message = message;
	}

	public static String choixRobotDebut() {
		String tmp = "";
		do {
			JOptionPane jop = new JOptionPane();
			tmp = jop.showInputDialog(null, "Quel type de robot voulez vous bouger ?  ");
		} while (!tmp.equals("Char") && !tmp.equals("Tireur") && !tmp.equals("Piegeur"));
		return tmp;
	}

	public static boolean estInt(String chaine) {

		for (int idx = 0; idx < chaine.length(); idx++) {

			if (chaine.charAt(idx) < '0' || chaine.charAt(idx) > '9') {
				return false;
			}

		}
		return true;
	}

	// probleme si on choisit 5 tireurs ou 5 piegeurs ou 5 char
	public int[] configurationJoueur(Vue uneVue, Base uneBase, boolean equipe) {

		final ImageIcon iconS = new ImageIcon("image/iconS.png");
		final ImageIcon iconC = new ImageIcon("image/iconC.png");
		final ImageIcon iconT = new ImageIcon("image/iconT.png");
		final ImageIcon iconP = new ImageIcon("image/iconP.png");
		final ImageIcon iconCh = new ImageIcon("image/iconCh.png");
		final ImageIcon iconR = new ImageIcon("image/iconR.png");

		String nbT;
		String nbC;
		String nbP;
		String nbTB;
		String nbCB;
		String nbPB;

		int[] nbRobotDeChaqueType = new int[3];

		final int max = 5;

		if (!equipe) {
			do {
				do {
					Object nb = JOptionPane.showInputDialog(null, "Tireur : Nombre ?", "Tireur Equipe 1",
							JOptionPane.QUESTION_MESSAGE, iconT, null, " ");
					nbT = nb.toString();
				} while (!estInt(nbT));
				if (Integer.valueOf(nbT) <= max && nbrobot <= max) {
					nbrobot += Integer.valueOf(nbT);
					nbRobotDeChaqueType[0] = Integer.valueOf(nbT);
					// for (int idx = 0; idx < Integer.valueOf(nbT); idx++) {

					// uneBase.addRobot(new Tireur(uneVue, 1, 0, 0));
					// uneBase.addRobotDansTableau(new Tireur(uneVue, 1, 0, 0),
					// idx);

					// }
				}
			} while (Integer.valueOf(nbT) > max || nbrobot > max);

			if (nbrobot < max) {
				int tmp = nbrobot;
				do {
					nbrobot = tmp;
					do {
						Object nb = JOptionPane.showInputDialog(null, "Piegeur : Nombre ?", "Piegeur Equipe 1",
								JOptionPane.QUESTION_MESSAGE, iconP, null, " ");
						nbP = nb.toString();
					} while (!estInt(nbP));
					nbrobot += Integer.valueOf(nbP);
					nbRobotDeChaqueType[1] = Integer.valueOf(nbP);
					/*
					 * for (int idx = 0; idx < Integer.valueOf(nbP); idx++) {
					 * //uneBase.addRobot(new Piegeur(uneVue, 1, 0, 0));
					 * uneBase.addRobotDansTableau(new Piegeur(uneVue, 1, 0, 0),
					 * idx); }
					 */

				} while (nbrobot > max);
			}
			if (nbrobot < max) {
				int tmp = nbrobot;
				do {
					nbrobot = tmp;
					do {
						Object nb = JOptionPane.showInputDialog(null, "Char : Nombre ?", "Char Equipe 1",
								JOptionPane.QUESTION_MESSAGE, iconCh, null, " ");
						nbC = nb.toString();
					} while (!estInt(nbC));
					nbrobot += Integer.valueOf(nbC);
					nbRobotDeChaqueType[2] = Integer.valueOf(nbC);
					/*
					 * for (int idx = 0; idx < Integer.valueOf(nbC); idx++) {
					 * //uneBase.addRobot(new Piegeur(uneVue, 1, 0, 0));
					 * uneBase.addRobotDansTableau(new Char(uneVue, 1, 0, 0),
					 * idx); }
					 */

				} while (nbrobot > max);

			}

			JOptionPane.showMessageDialog(null, "Votre armée a été configurée avec succès !",
					"Configuration de l'armée Equipe 1", JOptionPane.INFORMATION_MESSAGE, iconS);

		} else {

			do {
				do {
					Object nb = JOptionPane.showInputDialog(null, "Tireur : Nombre ?", "Tireur Equipe 2",
							JOptionPane.QUESTION_MESSAGE, iconT, null, " ");
					nbTB = nb.toString();
				} while (!estInt(nbTB));
				if (Integer.valueOf(nbTB) <= max && nbrobotB <= max) {
					nbrobotB += Integer.valueOf(nbTB);
					nbRobotDeChaqueType[0] = Integer.valueOf(nbTB);
					/*
					 * for (int idx = 0; idx < Integer.valueOf(nbTB); idx++) {
					 * //uneBase.addRobot(new Tireur(uneVue, 1,
					 * Plateau.grille.length -1, Plateau.grille[0].length));
					 * uneBase.addRobotDansTableau(new Tireur(uneVue, 2,
					 * Plateau.grille.length -1, Plateau.grille[0].length),
					 * idx); }
					 */
				}
			} while (Integer.valueOf(nbTB) > max || nbrobotB > max);

			if (nbrobotB < max) {
				int tmp = nbrobotB;
				do {
					nbrobotB = tmp;
					do {
						Object nb = JOptionPane.showInputDialog(null, "Piegeur : Nombre ?", "Piegeur Equipe 2",
								JOptionPane.QUESTION_MESSAGE, iconP, null, " ");
						nbPB = nb.toString();
					} while (!estInt(nbPB));
					nbrobotB += Integer.valueOf(nbPB);
					nbRobotDeChaqueType[1] = Integer.valueOf(nbPB);

					/*
					 * for (int idx = 0; idx < Integer.valueOf(nbPB); idx++) {
					 * //uneBase.addRobot(new Piegeur(uneVue, 1,
					 * Plateau.grille.length -1, Plateau.grille[0].length));
					 * uneBase.addRobotDansTableau(new Piegeur(uneVue, 2,
					 * Plateau.grille.length -1, Plateau.grille[0].length),
					 * idx); }
					 */

				} while (nbrobotB > max);
			}
			if (nbrobotB < max) {
				int tmp = nbrobotB;
				do {
					nbrobotB = tmp;
					do {
						Object nb = JOptionPane.showInputDialog(null, "Char : Nombre ?", "Char Equipe 2",
								JOptionPane.QUESTION_MESSAGE, iconCh, null, " ");
						nbCB = nb.toString();
					} while (!estInt(nbCB));
					nbrobotB += Integer.valueOf(nbCB);
					nbRobotDeChaqueType[2] = Integer.valueOf(nbCB);
					/*
					 * for (int idx = 0; idx < Integer.valueOf(nbCB); idx++) {
					 * //uneBase.addRobot(new Piegeur(uneVue,
					 * 1,Plateau.grille.length -1, Plateau.grille[0].length));
					 * uneBase.addRobotDansTableau(new Char(uneVue, 2,
					 * Plateau.grille.length -1, Plateau.grille[0].length),
					 * idx); }
					 */

				} while (nbrobotB > max);
			}

			JOptionPane.showMessageDialog(null, "Votre armée a été configurer avec succès !",
					"Configuration de l'armée Equipe 2", JOptionPane.INFORMATION_MESSAGE, iconS);

		}
		return nbRobotDeChaqueType;

	}

	
	public static Coord choixAction(Robot r){
		
		ArrayList<Coord> possible = new ArrayList<Coord>();
		Coord[] choixAction;
		Coord test;
	
		if(r instanceof Tireur || r instanceof Piegeur){
			
			
			test = r.getCoordonnee().ajouterCoord(Constantes.bas);
			try{
			if(Plateau.grille[test.getPositionX()][test.getPositionY()].celluleValide()){
				
					possible.add(test);
				
				
			}
			}catch(Exception e){
				e.toString();
			}
			
			test = r.getCoordonnee().ajouterCoord(Constantes.haut);
			try{
			if(Plateau.grille[test.getPositionX()][test.getPositionY()].celluleValide()){
				
				
					possible.add(test);
				
			}
				
			}catch(Exception e){
				e.toString();
			}
				
			test = r.getCoordonnee().ajouterCoord(Constantes.gauche);
			try{
			if(Plateau.grille[test.getPositionX()][test.getPositionY()].celluleValide()){
				
			
					possible.add(test);
				
			}
				
			}catch(Exception e){
				e.toString();
			}
			test = r.getCoordonnee().ajouterCoord(Constantes.droite);
			try{
			if(Plateau.grille[test.getPositionX()][test.getPositionY()].celluleValide()){
				
				
					possible.add(test);
				
			}
				
			}catch(Exception e){
				e.toString();
			}
			test = r.getCoordonnee().ajouterCoord(Constantes.diagoBasDroite);
			try{
			if(Plateau.grille[test.getPositionX()][test.getPositionY()].celluleValide()){
				
				
					possible.add(test);
				
			}
				
			}catch(Exception e){
				e.toString();
			}
			test = r.getCoordonnee().ajouterCoord(Constantes.diagoBasGauche);
			try{
			if(Plateau.grille[test.getPositionX()][test.getPositionY()].celluleValide()){
				
				
					possible.add(test);
				
			}
				
			}catch(Exception e){
				e.toString();
			}
			test = r.getCoordonnee().ajouterCoord(Constantes.diagoHautDroite);
			try{
			if(Plateau.grille[test.getPositionX()][test.getPositionY()].celluleValide()){
				
				
					possible.add(test);
				
			}
				
			}catch(Exception e){
				e.toString();
			}
			test = r.getCoordonnee().ajouterCoord(Constantes.diagoHautGauche);
			try{
			if(Plateau.grille[test.getPositionX()][test.getPositionY()].celluleValide()){
				
			
					possible.add(test);
			}
				
			}catch(Exception e){
				e.toString();
			}
			
			
			choixAction = new Coord[possible.size()];
			for(Coord c : possible){
				choixAction[possible.indexOf(c)] = c;
			}
			
			return (Coord) JOptionPane.showInputDialog(null, "Choisissez ou vous voulez attaquer", "Attaquer",
					JOptionPane.QUESTION_MESSAGE, iconQ, choixAction, choixAction[0].toString());
			
				}
				
		if(r instanceof Char){
			
			
			test = r.getCoordonnee().ajouterCoord(Constantes.bas);
			try{
			if(Plateau.grille[test.getPositionX()][test.getPositionY()].celluleValide()){
				
					possible.add(test);
				
				
			}
			}catch(Exception e){
				e.toString();
			}
			
			test = r.getCoordonnee().ajouterCoord(Constantes.haut);
			try{
			if(Plateau.grille[test.getPositionX()][test.getPositionY()].celluleValide()){
				
				
					possible.add(test);
				
			}
				
			}catch(Exception e){
				e.toString();
			}
				
			test = r.getCoordonnee().ajouterCoord(Constantes.gauche);
			try{
			if(Plateau.grille[test.getPositionX()][test.getPositionY()].celluleValide()){
				
			
					possible.add(test);
				
			}
				
			}catch(Exception e){
				e.toString();
			}
			test = r.getCoordonnee().ajouterCoord(Constantes.droite);
			try{
			if(Plateau.grille[test.getPositionX()][test.getPositionY()].celluleValide()){
				
				
					possible.add(test);
				
			}
				
			}catch(Exception e){
				e.toString();
			}
			test = r.getCoordonnee().ajouterCoord(Constantes.diagoBasDroite);
			try{
			if(Plateau.grille[test.getPositionX()][test.getPositionY()].celluleValide()){
				
				
					possible.add(test);
				
			}
				
			}catch(Exception e){
				e.toString();
			}
			test = r.getCoordonnee().ajouterCoord(Constantes.diagoBasGauche);
			try{
			if(Plateau.grille[test.getPositionX()][test.getPositionY()].celluleValide()){
				
				
					possible.add(test);
				
			}
				
			}catch(Exception e){
				e.toString();
			}
			test = r.getCoordonnee().ajouterCoord(Constantes.diagoHautDroite);
			try{
			if(Plateau.grille[test.getPositionX()][test.getPositionY()].celluleValide()){
				
				
					possible.add(test);
				
			}
				
			}catch(Exception e){
				e.toString();
			}
			test = r.getCoordonnee().ajouterCoord(Constantes.diagoHautGauche);
			try{
			if(Plateau.grille[test.getPositionX()][test.getPositionY()].celluleValide()){
				
			
					possible.add(test);
			}
				
			}catch(Exception e){
				e.toString();
			}
			
			
			
			test = r.getCoordonnee().ajouterCoord(Constantes.basChar);
			try{
			if(Plateau.grille[test.getPositionX()][test.getPositionY()].celluleValide()){
				
					possible.add(test);
				
				
			}
			}catch(Exception e){
				e.toString();
			}
			
			test = r.getCoordonnee().ajouterCoord(Constantes.hautChar);
			try{
			if(Plateau.grille[test.getPositionX()][test.getPositionY()].celluleValide()){
				
				
					possible.add(test);
				
			}
				
			}catch(Exception e){
				e.toString();
			}
				
			test = r.getCoordonnee().ajouterCoord(Constantes.gaucheChar);
			try{
			if(Plateau.grille[test.getPositionX()][test.getPositionY()].celluleValide()){
				
			
					possible.add(test);
				
			}
				
			}catch(Exception e){
				e.toString();
			}
			test = r.getCoordonnee().ajouterCoord(Constantes.droiteChar);
			try{
			if(Plateau.grille[test.getPositionX()][test.getPositionY()].celluleValide()){
				
				
					possible.add(test);
				
			}
				
			}catch(Exception e){
				e.toString();
			}
			test = r.getCoordonnee().ajouterCoord(Constantes.diagoBasDroite).ajouterCoord(Constantes.diagoBasDroite);
			try{
			if(Plateau.grille[test.getPositionX()][test.getPositionY()].celluleValide()){
				
				
					possible.add(test);
				
			}
				
			}catch(Exception e){
				e.toString();
			}
			test = r.getCoordonnee().ajouterCoord(Constantes.diagoBasGauche).ajouterCoord(Constantes.diagoBasGauche);
			try{
			if(Plateau.grille[test.getPositionX()][test.getPositionY()].celluleValide()){
				
				
					possible.add(test);
				
			}
				
			}catch(Exception e){
				e.toString();
			}
			test = r.getCoordonnee().ajouterCoord(Constantes.diagoHautDroite).ajouterCoord(Constantes.diagoHautDroite);
			try{
			if(Plateau.grille[test.getPositionX()][test.getPositionY()].celluleValide()){
				
				
					possible.add(test);
				
			}
				
			}catch(Exception e){
				e.toString();
			}
			test = r.getCoordonnee().ajouterCoord(Constantes.diagoHautGauche).ajouterCoord(Constantes.diagoHautGauche);
			try{
			if(Plateau.grille[test.getPositionX()][test.getPositionY()].celluleValide()){
				
			
					possible.add(test);
			}
				
			}catch(Exception e){
				e.toString();
			}
			
			choixAction = new Coord[possible.size()];
			for(Coord c : possible){
				choixAction[possible.indexOf(c)] = c;
			}
			
			return (Coord) JOptionPane.showInputDialog(null, "Choisissez ou vous voulez attaquer", "Attaquer",
					JOptionPane.QUESTION_MESSAGE, iconQ, choixAction, choixAction[0].toString());
		
				}
					
			
	return null;
		
	}


public static ArrayList<Coord> choixActionIa(Robot r){
	
	ArrayList<Coord> possible = new ArrayList<Coord>();
	Coord[] choixAction;
	Coord test;

	if(r instanceof Tireur || r instanceof Piegeur){
		
		
		test = r.getCoordonnee().ajouterCoord(Constantes.bas);
		try{
		if(Plateau.grille[test.getPositionX()][test.getPositionY()].celluleValide()){
			
				possible.add(test);
			
			
		}
		}catch(Exception e){
			e.toString();
		}
		
		test = r.getCoordonnee().ajouterCoord(Constantes.haut);
		try{
		if(Plateau.grille[test.getPositionX()][test.getPositionY()].celluleValide()){
			
			
				possible.add(test);
			
		}
			
		}catch(Exception e){
			e.toString();
		}
			
		test = r.getCoordonnee().ajouterCoord(Constantes.gauche);
		try{
		if(Plateau.grille[test.getPositionX()][test.getPositionY()].celluleValide()){
			
		
				possible.add(test);
			
		}
			
		}catch(Exception e){
			e.toString();
		}
		test = r.getCoordonnee().ajouterCoord(Constantes.droite);
		try{
		if(Plateau.grille[test.getPositionX()][test.getPositionY()].celluleValide()){
			
			
				possible.add(test);
			
		}
			
		}catch(Exception e){
			e.toString();
		}
		test = r.getCoordonnee().ajouterCoord(Constantes.diagoBasDroite);
		try{
		if(Plateau.grille[test.getPositionX()][test.getPositionY()].celluleValide()){
			
			
				possible.add(test);
			
		}
			
		}catch(Exception e){
			e.toString();
		}
		test = r.getCoordonnee().ajouterCoord(Constantes.diagoBasGauche);
		try{
		if(Plateau.grille[test.getPositionX()][test.getPositionY()].celluleValide()){
			
			
				possible.add(test);
			
		}
			
		}catch(Exception e){
			e.toString();
		}
		test = r.getCoordonnee().ajouterCoord(Constantes.diagoHautDroite);
		try{
		if(Plateau.grille[test.getPositionX()][test.getPositionY()].celluleValide()){
			
			
				possible.add(test);
			
		}
			
		}catch(Exception e){
			e.toString();
		}
		test = r.getCoordonnee().ajouterCoord(Constantes.diagoHautGauche);
		try{
		if(Plateau.grille[test.getPositionX()][test.getPositionY()].celluleValide()){
			
		
				possible.add(test);
		}
			
		}catch(Exception e){
			e.toString();
		}
		
		
		choixAction = new Coord[possible.size()];
		for(Coord c : possible){
			choixAction[possible.indexOf(c)] = c;
		}
		
		return possible;
		
			}
			
	if(r instanceof Char){
		
		
		test = r.getCoordonnee().ajouterCoord(Constantes.bas);
		try{
		if(Plateau.grille[test.getPositionX()][test.getPositionY()].celluleValide()){
			
				possible.add(test);
			
			
		}
		}catch(Exception e){
			e.toString();
		}
		
		test = r.getCoordonnee().ajouterCoord(Constantes.haut);
		try{
		if(Plateau.grille[test.getPositionX()][test.getPositionY()].celluleValide()){
			
			
				possible.add(test);
			
		}
			
		}catch(Exception e){
			e.toString();
		}
			
		test = r.getCoordonnee().ajouterCoord(Constantes.gauche);
		try{
		if(Plateau.grille[test.getPositionX()][test.getPositionY()].celluleValide()){
			
		
				possible.add(test);
			
		}
			
		}catch(Exception e){
			e.toString();
		}
		test = r.getCoordonnee().ajouterCoord(Constantes.droite);
		try{
		if(Plateau.grille[test.getPositionX()][test.getPositionY()].celluleValide()){
			
			
				possible.add(test);
			
		}
			
		}catch(Exception e){
			e.toString();
		}
		test = r.getCoordonnee().ajouterCoord(Constantes.diagoBasDroite);
		try{
		if(Plateau.grille[test.getPositionX()][test.getPositionY()].celluleValide()){
			
			
				possible.add(test);
			
		}
			
		}catch(Exception e){
			e.toString();
		}
		test = r.getCoordonnee().ajouterCoord(Constantes.diagoBasGauche);
		try{
		if(Plateau.grille[test.getPositionX()][test.getPositionY()].celluleValide()){
			
			
				possible.add(test);
			
		}
			
		}catch(Exception e){
			e.toString();
		}
		test = r.getCoordonnee().ajouterCoord(Constantes.diagoHautDroite);
		try{
		if(Plateau.grille[test.getPositionX()][test.getPositionY()].celluleValide()){
			
			
				possible.add(test);
			
		}
			
		}catch(Exception e){
			e.toString();
		}
		test = r.getCoordonnee().ajouterCoord(Constantes.diagoHautGauche);
		try{
		if(Plateau.grille[test.getPositionX()][test.getPositionY()].celluleValide()){
			
		
				possible.add(test);
		}
			
		}catch(Exception e){
			e.toString();
		}
		
		
		
		test = r.getCoordonnee().ajouterCoord(Constantes.basChar);
		try{
		if(Plateau.grille[test.getPositionX()][test.getPositionY()].celluleValide()){
			
				possible.add(test);
			
			
		}
		}catch(Exception e){
			e.toString();
		}
		
		test = r.getCoordonnee().ajouterCoord(Constantes.hautChar);
		try{
		if(Plateau.grille[test.getPositionX()][test.getPositionY()].celluleValide()){
			
			
				possible.add(test);
			
		}
			
		}catch(Exception e){
			e.toString();
		}
			
		test = r.getCoordonnee().ajouterCoord(Constantes.gaucheChar);
		try{
		if(Plateau.grille[test.getPositionX()][test.getPositionY()].celluleValide()){
			
		
				possible.add(test);
			
		}
			
		}catch(Exception e){
			e.toString();
		}
		test = r.getCoordonnee().ajouterCoord(Constantes.droiteChar);
		try{
		if(Plateau.grille[test.getPositionX()][test.getPositionY()].celluleValide()){
			
			
				possible.add(test);
			
		}
			
		}catch(Exception e){
			e.toString();
		}
		test = r.getCoordonnee().ajouterCoord(Constantes.diagoBasDroite).ajouterCoord(Constantes.diagoBasDroite);
		try{
		if(Plateau.grille[test.getPositionX()][test.getPositionY()].celluleValide()){
			
			
				possible.add(test);
			
		}
			
		}catch(Exception e){
			e.toString();
		}
		test = r.getCoordonnee().ajouterCoord(Constantes.diagoBasGauche).ajouterCoord(Constantes.diagoBasGauche);
		try{
		if(Plateau.grille[test.getPositionX()][test.getPositionY()].celluleValide()){
			
			
				possible.add(test);
			
		}
			
		}catch(Exception e){
			e.toString();
		}
		test = r.getCoordonnee().ajouterCoord(Constantes.diagoHautDroite).ajouterCoord(Constantes.diagoHautDroite);
		try{
		if(Plateau.grille[test.getPositionX()][test.getPositionY()].celluleValide()){
			
			
				possible.add(test);
			
		}
			
		}catch(Exception e){
			e.toString();
		}
		test = r.getCoordonnee().ajouterCoord(Constantes.diagoHautGauche).ajouterCoord(Constantes.diagoHautGauche);
		try{
		if(Plateau.grille[test.getPositionX()][test.getPositionY()].celluleValide()){
			
		
				possible.add(test);
		}
			
		}catch(Exception e){
			e.toString();
		}
		
		choixAction = new Coord[possible.size()];
		for(Coord c : possible){
			choixAction[possible.indexOf(c)] = c;
		}
		
		return possible;
	
			}
				
		
return null;
	
}
	public static void main(String[] args) {
		Saisie tank = new Saisie("Quel robot voulez vous bouger ?");
		String a = "";
		queVoulezVousFaire(1);
		Coord test = choixCoord();
	}
}
