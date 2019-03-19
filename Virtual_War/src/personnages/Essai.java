package personnages;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Properties;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import javafx.application.Platform;

public class Essai {

	public static boolean equipe = false;
	public static Plateau unPlateau;

	protected static ArrayList<Robot> infoRobotA;
	protected static ArrayList<Robot> infoRobotB;

	private static int nbMortA = 0;
	private static int nbMortB = 0;

	private static int totalRobotA = 0;
	private static int totalRobotB = 0;

	protected static int robotSortiA = 0;
	protected static int robotSortiB = 0;

	public static Vue equipeB;
	public static Vue equipeA;
	
	public static FileReader flux;
	public static BufferedReader fichier;
	public static Properties p = new Properties();

	public static boolean partiFini() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		File vic = new File("music/win.wav");
		AudioInputStream vict = AudioSystem.getAudioInputStream(vic);
		Clip victoire = AudioSystem.getClip();
		File win = new File("music/win.wav");
		AudioInputStream instru = AudioSystem.getAudioInputStream(win);
		Clip instrumental = AudioSystem.getClip();
		
		
		if (nbMortA == totalRobotA) {
			victoire.open(vict);
			victoire.start();
			instrumental.open(instru);
			instrumental.start();
			return true;
		} else if (nbMortB == totalRobotB) {
			victoire.open(vict);
			victoire.start();
			instrumental.open(instru);
			instrumental.start();
			return true;
			
		}
		return false;
	}

	public static void GetInfoRobot() {
		infoRobotA = new ArrayList<>();
		infoRobotB = new ArrayList<>();

		for (int i = 0; i < Plateau.grille.length; i++) {
			for (int j = 0; j < Plateau.grille[0].length; j++) {

				if (Plateau.grille[i][j].contienRobot()) {
					if (Plateau.grille[i][j].getUnRobot().getEquipe() == 1) {

						infoRobotA.add(Plateau.grille[i][j].getUnRobot());
						robotSortiA++;
					}
					if (Plateau.grille[i][j].getUnRobot().getEquipe() == 2) {
						infoRobotB.add(Plateau.grille[i][j].getUnRobot());
						robotSortiB++;
					}
				}
			}
		}
	}

	public static void estMort() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		File vic = new File("music/damn.wav");
		AudioInputStream vict = AudioSystem.getAudioInputStream(vic);
		Clip victoire = AudioSystem.getClip();

		for (int i = 0; i < Plateau.grille.length; i++) {
			for (int j = 0; j < Plateau.grille[0].length; j++) {

				if (Plateau.grille[i][j].contienRobot()) {

					if (Plateau.grille[i][j].getUnRobot().getEnergie() <= 0) {
						JOptionPane.showMessageDialog(null,
								"L'equipe " + Plateau.grille[i][j].getUnRobot().getEquipe() + " a perdu un "
										+ Plateau.grille[i][j].getUnRobot().getType(),
								"Aie", JOptionPane.WARNING_MESSAGE);
						victoire.open(vict);
						victoire.start();
						
						if (Plateau.grille[i][j].getUnRobot().getEquipe() == 1) {
							nbMortA++;
						} else if (Plateau.grille[i][j].getUnRobot().getEquipe() == 2) {
							nbMortB++;
						}
						Plateau.grille[i][j].viderCellule();

					}
				}

			}
		}

	}

	public static void config() throws FileNotFoundException {
		Bienvenue.launch(Bienvenue.class);

	}

	public static void main(String[] args) throws Exception {
		File pre = new File("music/presentation.wav");
		AudioInputStream pres = AudioSystem.getAudioInputStream(pre);
		Clip presentation = AudioSystem.getClip();
		presentation.open(pres);
		presentation.start();
		
		File j = new File("music/jeu.wav");
		AudioInputStream jeu = AudioSystem.getAudioInputStream(j);
		Clip game = AudioSystem.getClip();
		game.open(jeu);
		//Bienvenue.launch(Bienvenue.class, args);

		flux = new FileReader("ressources/configuration.properties");
		fichier = new BufferedReader(flux);
		p.load(flux);

		int h = Integer.valueOf(p.getProperty("plateau.hauteur"));
		int l = Integer.valueOf(p.getProperty("plateau.largeur"));
		int o = Integer.valueOf(p.getProperty("plateau.obstacle"));

		boolean iaFacile = false;
		boolean iaMoyenne = false;
		boolean iaDifficile = false;

		if (h == 0) {
			h = 5;
			l = 5;
		}

		int T1 = Integer.valueOf(p.getProperty("equipe1.tireur"));
		int T2 = Integer.valueOf(p.getProperty("equipe2.tireur"));

		int P1 = Integer.valueOf(p.getProperty("equipe1.piegeur"));
		int P2 = Integer.valueOf(p.getProperty("equipe2.piegeur"));

		int C1 = Integer.valueOf(p.getProperty("equipe1.char"));
		int C2 = Integer.valueOf(p.getProperty("equipe2.char"));

		unPlateau = new Plateau(h, l, o);

		int[] tableauEquipe1 = { T1, P1, C1 };
		int[] tableauEquipe2 = { T2, P2, C2 };

		Base baseA = new Base(0, 0, 1);
		Base baseB = new Base(unPlateau.getHauteur() - 1, unPlateau.getLargeur() - 1, 2);

		unPlateau.addBase(baseA);
		unPlateau.addBase(baseB);

		 equipeA = new Vue(1, unPlateau);
		 equipeB = new Vue(2, unPlateau);

		// System.out.println(Saisie.nbrobot);
		totalRobotA = T1 + P1 + C1;
		totalRobotB = T2 + P2 + C2;

		// System.out.println(Saisie.nbrobotB);
		int cpt = 0;
		presentation.close();
		estMort();
		unPlateau.affichage();
		unPlateau.afficherParEquipe(1);
		System.out.println();

		unPlateau.energieJ1();
		GetInfoRobot();
		System.out.println("\tIl vous reste " + equipeA.nbMine + " mines");
		Energie e = new Energie();
		game.start();

		do {
			
			
			File musicTir = new File("music/tir.wav");
			AudioInputStream sonTir = AudioSystem.getAudioInputStream(musicTir);
			Clip clip = AudioSystem.getClip();

			File musicChar = new File("music/tirChar.wav");
			AudioInputStream sonChar = AudioSystem.getAudioInputStream(musicChar);
			Clip charTir = AudioSystem.getClip();

			File musicPieg = new File("music/posemine.wav");
			AudioInputStream sonMine = AudioSystem.getAudioInputStream(musicPieg);
			Clip poseMine = AudioSystem.getClip();

			File musicDepl = new File("music/deplacement.wav");
			AudioInputStream sonDep = AudioSystem.getAudioInputStream(musicDepl);
			Clip dep = AudioSystem.getClip();

			cpt++;

			String robotADeplacerAuDebut = "";
			// affiche le plateau
			e.affichage();
			
			
			// faire jouer la premiere equipe
			String choixEquipe1 = Saisie.queVoulezVousFaire(1);
			if (choixEquipe1.equals("Sortir un robot de la base")) {

				String robotABouger = Saisie.QuelType();

				if (tableauEquipe1[0] == 0 && robotABouger.equals("Tireur")) {
					JOptionPane.showMessageDialog(null, "Aucun Tireur dans la base de disponible!", "Erreur",
							JOptionPane.ERROR_MESSAGE);
				}

				if (tableauEquipe1[1] == 0 && robotABouger.equals("Piegeur")) {
					JOptionPane.showMessageDialog(null, "Aucun Piegeur dans la base de disponible!", "Erreur",
							JOptionPane.ERROR_MESSAGE);
				}

				if (tableauEquipe1[2] == 0 && robotABouger.equals("Char")) {
					JOptionPane.showMessageDialog(null, "Aucun Char dans la base de disponible!", "Erreur",
							JOptionPane.ERROR_MESSAGE);
				}

				if (tableauEquipe1[0] != 0 && robotABouger.equals("Tireur")) {
					tableauEquipe1[0]--;
					if (Plateau.grille[1][0].estLibre()) {
						File sortiefile = new File("music/sortie.wav");
						AudioInputStream sortieAudio = AudioSystem.getAudioInputStream(sortiefile);
						Clip sortie = AudioSystem.getClip();
						sortie.open(sortieAudio);
						sortie.start();
						Plateau.grille[1][0].addRobot(new Tireur(equipeA, 1, 1, 0));
					} else if (Plateau.grille[0][1].estLibre()) {
						File sortiefile = new File("music/sortie.wav");
						AudioInputStream sortieAudio = AudioSystem.getAudioInputStream(sortiefile);
						Clip sortie = AudioSystem.getClip();
						sortie.open(sortieAudio);
						sortie.start();
						Plateau.grille[0][1].addRobot(new Tireur(equipeA, 1, 0, 1));
					} else {
						JOptionPane.showMessageDialog(null,
								"Aucune place de disponible autour de la base : bougez vos robots!", "Erreur",
								JOptionPane.ERROR_MESSAGE);
					}

				}

				if (tableauEquipe1[1] != 0 && robotABouger.equals("Piegeur")) {
					tableauEquipe1[1]--;
					if (Plateau.grille[1][0].estLibre()) {
						File sortiefile = new File("music/sortie.wav");
						AudioInputStream sortieAudio = AudioSystem.getAudioInputStream(sortiefile);
						Clip sortie = AudioSystem.getClip();
						sortie.open(sortieAudio);
						sortie.start();
						Plateau.grille[1][0].addRobot(new Piegeur(equipeA, 1, 1, 0));
					} else if (Plateau.grille[0][1].estLibre()) {
						File sortiefile = new File("music/sortie.wav");
						AudioInputStream sortieAudio = AudioSystem.getAudioInputStream(sortiefile);
						Clip sortie = AudioSystem.getClip();
						sortie.open(sortieAudio);
						sortie.start();
						Plateau.grille[0][1].addRobot(new Piegeur(equipeA, 1, 0, 1));
					} else {
						JOptionPane.showMessageDialog(null,
								"Aucune place de disponible autour de la base : bougez vos robots!", "Erreur",
								JOptionPane.ERROR_MESSAGE);
					}

				}

				if (tableauEquipe1[2] != 0 && robotABouger.equals("Char")) {
					tableauEquipe1[2]--;

					if (Plateau.grille[1][0].estLibre()) {
						File sortiefile = new File("music/sortie.wav");
						AudioInputStream sortieAudio = AudioSystem.getAudioInputStream(sortiefile);
						Clip sortie = AudioSystem.getClip();
						sortie.open(sortieAudio);
						sortie.start();
						Plateau.grille[1][0].addRobot(new Char(equipeA, 1, 1, 0));
					}

					else if (Plateau.grille[0][1].estLibre()) {
						File sortiefile = new File("music/sortie.wav");
						AudioInputStream sortieAudio = AudioSystem.getAudioInputStream(sortiefile);
						Clip sortie = AudioSystem.getClip();
						sortie.open(sortieAudio);
						sortie.start();
						Plateau.grille[0][1].addRobot(new Char(equipeA, 1, 0, 1));
					}

					else if (Plateau.grille[2][0].estLibre()) {
						File sortiefile = new File("music/sortie.wav");
						AudioInputStream sortieAudio = AudioSystem.getAudioInputStream(sortiefile);
						Clip sortie = AudioSystem.getClip();
						sortie.open(sortieAudio);
						sortie.start();
						Plateau.grille[2][0].addRobot(new Char(equipeA, 1, 2, 0));
					} else if (Plateau.grille[0][2].estLibre()) {
						File sortiefile = new File("music/sortie.wav");
						AudioInputStream sortieAudio = AudioSystem.getAudioInputStream(sortiefile);
						Clip sortie = AudioSystem.getClip();
						sortie.open(sortieAudio);
						sortie.start();
						Plateau.grille[0][2].addRobot(new Char(equipeA, 1, 0, 2));
					} else {
						JOptionPane.showMessageDialog(null,
								"Aucune place de disponible autour de la base : bougez vos robots!", "Erreur",
								JOptionPane.ERROR_MESSAGE);
					}

				}

			} else if (choixEquipe1.equals("Bouger un robot")) {
				Coord robot = Saisie.DeplacerDemande(1);
				if (Plateau.grille[robot.getPositionX()][robot.getPositionY()].contienRobot()) {

					Robot tmp = unPlateau.grille[robot.getPositionX()][robot.getPositionY()].getUnRobot();
					Coord objectif = Saisie.ChoixList(tmp);
					Action deplacement = new Deplacement(tmp, unPlateau.getCellule(objectif));
					dep.open(sonDep);
					dep.start();
					
					deplacement.agit();
				} else {
					JOptionPane.showMessageDialog(null, "Il n'y a pas de robot a cet endroit", "Erreur",
							JOptionPane.ERROR_MESSAGE);
				}

			} else if (choixEquipe1.equals("Tirer/Poser une mine")) {
				Coord tmp = Saisie.ActionDemande(1);
				if (Plateau.grille[tmp.getPositionX()][tmp.getPositionY()].contienRobot()) {
					if (unPlateau.grille[tmp.getPositionX()][tmp.getPositionY()].contienRobot()) {
						Robot robot = unPlateau.grille[tmp.getPositionX()][tmp.getPositionY()].getUnRobot();
<<<<<<< HEAD
						Coord cible = Saisie.choixAction(robot);
=======
						Coord cible = Saisie.choixAction(1,robot);
>>>>>>> 01e94f70828b0814cc4ad87c39ee15c5da184990
						Action agir = new Attaque(robot, unPlateau.getCellule(cible));
						if (robot.getType().equals("Tireur")) {
							clip.open(sonTir);
							clip.start();
							
						} else if (robot.getType().equals("Char")) {
							charTir.open(sonChar);
							charTir.start();
							
						} else if (robot.getType().equals("Piegeur")) {
							poseMine.open(sonMine);
							poseMine.start();
						
						}
						agir.agit();
					}

				} else {
					JOptionPane.showMessageDialog(null, "Il n'y a pas de robot a cet endroit", "Erreur",
							JOptionPane.ERROR_MESSAGE);
				}
			}
			estMort();
			equipe = true;
			unPlateau.affichage();
			unPlateau.afficherParEquipe(2);
			System.out.println();

			unPlateau.energieJ2();
			GetInfoRobot();
			System.out.println("\tIl vous reste " + equipeB.nbMine + " mines");
			e.affichage();
			 musicTir = new File("music/tir.wav");
			 sonTir = AudioSystem.getAudioInputStream(musicTir);
			 clip = AudioSystem.getClip();

		 musicChar = new File("music/tirChar.wav");
			 sonChar = AudioSystem.getAudioInputStream(musicChar);
			 charTir = AudioSystem.getClip();

			 musicPieg = new File("music/posemine.wav");
			 sonMine = AudioSystem.getAudioInputStream(musicPieg);
			 poseMine = AudioSystem.getClip();

			 musicDepl = new File("music/deplacement.wav");
			 sonDep = AudioSystem.getAudioInputStream(musicDepl);
			 dep = AudioSystem.getClip();
			String choixEquipe2 = Saisie.queVoulezVousFaire(2);
			if (choixEquipe2.equals("Sortir un robot de la base")) {

				String robotABouger = Saisie.QuelType();

				if (tableauEquipe2[0] == 0 && robotABouger.equals("Tireur")) {
					JOptionPane.showMessageDialog(null, "Aucun Tireur dans la base de disponible!", "Erreur",
							JOptionPane.ERROR_MESSAGE);
				}

				if (tableauEquipe2[1] == 0 && robotABouger.equals("Piegeur")) {
					JOptionPane.showMessageDialog(null, "Aucun Piegeur dans la base de disponible!", "Erreur",
							JOptionPane.ERROR_MESSAGE);
				}

				if (tableauEquipe2[2] == 0 && robotABouger.equals("Char")) {
					JOptionPane.showMessageDialog(null, "Aucun Char dans la base de disponible!", "Erreur",
							JOptionPane.ERROR_MESSAGE);
				}

				if (tableauEquipe2[0] != 0 && robotABouger.equals("Tireur")) {
					tableauEquipe2[0]--;

					if (Plateau.grille[Plateau.grille.length - 1][Plateau.grille[0].length - 2].estLibre()) { // l9|c8
						File sortiefile = new File("music/sortie.wav");
						AudioInputStream sortieAudio = AudioSystem.getAudioInputStream(sortiefile);
						Clip sortie = AudioSystem.getClip();
						sortie.open(sortieAudio);
						sortie.start();
						Plateau.grille[Plateau.grille.length - 1][Plateau.grille[0].length - 2].addRobot(
								new Tireur(equipeB, 2, Plateau.grille.length - 1, Plateau.grille[0].length - 2));
						
					} else if (Plateau.grille[Plateau.grille.length - 2][Plateau.grille[0].length - 1].estLibre()) { // l8|c9
						File sortiefile = new File("music/sortie.wav");
						AudioInputStream sortieAudio = AudioSystem.getAudioInputStream(sortiefile);
						Clip sortie = AudioSystem.getClip();
						sortie.open(sortieAudio);
						sortie.start();
						Plateau.grille[Plateau.grille.length - 2][Plateau.grille[0].length - 1].addRobot(
								new Tireur(equipeB, 2, Plateau.grille.length - 2, Plateau.grille[0].length - 1));
					} else {
						JOptionPane.showMessageDialog(null,
								"Aucune place de disponible autour de la base : bougez vos robots!", "Erreur",
								JOptionPane.ERROR_MESSAGE);
					}
				}

				if (tableauEquipe2[1] != 0 && robotABouger.equals("Piegeur")) {
					tableauEquipe2[1]--;

					if (Plateau.grille[Plateau.grille.length - 1][Plateau.grille[0].length - 2].estLibre()) {
						File sortiefile = new File("music/sortie.wav");
						AudioInputStream sortieAudio = AudioSystem.getAudioInputStream(sortiefile);
						Clip sortie = AudioSystem.getClip();
						sortie.open(sortieAudio);
						sortie.start();
						Plateau.grille[Plateau.grille.length - 1][Plateau.grille[0].length - 2].addRobot(
								new Piegeur(equipeB, 2, Plateau.grille.length - 1, Plateau.grille[0].length - 2));
					} else if (Plateau.grille[Plateau.grille.length - 2][Plateau.grille[0].length - 1].estLibre()) {
						File sortiefile = new File("music/sortie.wav");
						AudioInputStream sortieAudio = AudioSystem.getAudioInputStream(sortiefile);
						Clip sortie = AudioSystem.getClip();
						sortie.open(sortieAudio);
						sortie.start();
						Plateau.grille[Plateau.grille.length - 2][Plateau.grille[0].length - 1].addRobot(
								new Piegeur(equipeB, 2, Plateau.grille.length - 2, Plateau.grille[0].length - 1));
					} else {
						JOptionPane.showMessageDialog(null,
								"Aucune place de disponible autour de la base : bougez vos robots!", "Erreur",
								JOptionPane.ERROR_MESSAGE);
					}

				}

				if (tableauEquipe2[2] != 0 && robotABouger.equals("Char")) {
					tableauEquipe2[2]--;

					if (Plateau.grille[Plateau.grille.length - 1][Plateau.grille[0].length - 2].estLibre()) {
						File sortiefile = new File("music/sortie.wav");
						AudioInputStream sortieAudio = AudioSystem.getAudioInputStream(sortiefile);
						Clip sortie = AudioSystem.getClip();
						sortie.open(sortieAudio);
						sortie.start();
						Plateau.grille[Plateau.grille.length - 1][Plateau.grille[0].length - 2].addRobot(
								new Char(equipeB, 2, Plateau.grille.length - 1, Plateau.grille[0].length - 2));
					} else if (Plateau.grille[Plateau.grille.length - 2][Plateau.grille[0].length - 1].estLibre()) {
						File sortiefile = new File("music/sortie.wav");
						AudioInputStream sortieAudio = AudioSystem.getAudioInputStream(sortiefile);
						Clip sortie = AudioSystem.getClip();
						sortie.open(sortieAudio);
						sortie.start();
						Plateau.grille[Plateau.grille.length - 2][Plateau.grille[0].length - 1].addRobot(
								new Char(equipeB, 2, Plateau.grille.length - 2, Plateau.grille[0].length - 1));
					} else if (Plateau.grille[Plateau.grille.length - 3][Plateau.grille[0].length - 1].estLibre()) {
						File sortiefile = new File("music/sortie.wav");
						AudioInputStream sortieAudio = AudioSystem.getAudioInputStream(sortiefile);
						Clip sortie = AudioSystem.getClip();
						sortie.open(sortieAudio);
						sortie.start();
						Plateau.grille[Plateau.grille.length - 3][Plateau.grille[0].length - 1].addRobot(
								new Char(equipeB, 2, Plateau.grille.length - 3, Plateau.grille[0].length - 1));
					} else if (Plateau.grille[Plateau.grille.length - 1][Plateau.grille[0].length - 3].estLibre()) {
						File sortiefile = new File("music/sortie.wav");
						AudioInputStream sortieAudio = AudioSystem.getAudioInputStream(sortiefile);
						Clip sortie = AudioSystem.getClip();
						sortie.open(sortieAudio);
						sortie.start();
						Plateau.grille[Plateau.grille.length - 1][Plateau.grille[0].length - 3].addRobot(
								new Char(equipeB, 2, Plateau.grille.length - 1, Plateau.grille[0].length - 3));
					}

					else {
						JOptionPane.showMessageDialog(null,
								"Aucune place de disponible autour de la base : bougez vos robots!", "Erreur",
								JOptionPane.ERROR_MESSAGE);
					}

				}

			} else if (choixEquipe2.equals("Bouger un robot")) {
				Coord robot = Saisie.DeplacerDemande(2);
				if (Plateau.grille[robot.getPositionX()][robot.getPositionY()].contienRobot()) {

					Robot tmp = unPlateau.grille[robot.getPositionX()][robot.getPositionY()].getUnRobot();
					Coord objectif = Saisie.ChoixList(tmp);
					Action deplacement = new Deplacement(tmp, unPlateau.getCellule(objectif));
					dep.open(sonDep);
					dep.start();
					
					deplacement.agit();
				}

				else {
					JOptionPane.showMessageDialog(null, "Il n'y a pas de robot a cet endroit", "Erreur",
							JOptionPane.ERROR_MESSAGE);
				}

			} else if (choixEquipe2.equals("Tirer/Poser une mine")) {
				Coord tmp = Saisie.ActionDemande(2);
				if (Plateau.grille[tmp.getPositionX()][tmp.getPositionY()].contienRobot()) {
					if (unPlateau.grille[tmp.getPositionX()][tmp.getPositionY()].contienRobot()) {
						Robot robot = unPlateau.grille[tmp.getPositionX()][tmp.getPositionY()].getUnRobot();
<<<<<<< HEAD
						Coord cible = Saisie.choixAction(robot);
=======
						Coord cible = Saisie.choixAction(2,robot);
>>>>>>> 01e94f70828b0814cc4ad87c39ee15c5da184990
						Action agir = new Attaque(robot, unPlateau.getCellule(cible));
						if (robot.getType().equals("Tireur")) {
							clip.open(sonTir);
							clip.start();
						} else if (robot.getType().equals("Char")) {
							charTir.open(sonChar);
							charTir.start();
						} else if (robot.getType().equals("Piegeur")) {
							poseMine.open(sonMine);
							poseMine.start();
						}
						agir.agit();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Il n'y a pas de robot a cet endroit", "Erreur",
							JOptionPane.ERROR_MESSAGE);
				}

			}

			estMort();
			equipe = false;
			unPlateau.affichage();
			unPlateau.afficherParEquipe(1);
			System.out.println();

			unPlateau.energieJ1();
			GetInfoRobot();
			System.out.println("\tIl vous reste " + equipeA.nbMine + " mines");

		} while (!partiFini());

		if (nbMortA == totalRobotA) {
			JOptionPane.showMessageDialog(null, "L'équipe 2 a gagné", "Fin", JOptionPane.INFORMATION_MESSAGE);
		} else if (nbMortB == totalRobotB) {
			JOptionPane.showMessageDialog(null, "L'équipe 1 a gagné", "Fin", JOptionPane.INFORMATION_MESSAGE);
		}

	}
}
