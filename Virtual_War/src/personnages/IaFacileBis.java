package personnages;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.UnsupportedLookAndFeelException;

 public class IaFacileBis {

		
		
		static int tmp;
		static int tmp2;
		static boolean equipe=true;
		static int[] nbRobotDeChaqueType=new int[3];
		static Random rand=new Random();
		static int nbChar;
		static int nbTireur;
		static int nbPiegeur;
		static boolean forcer = false;
		

		public static int[] init() { //selectionne al�atoirement les robots composants l'�quipe
			while(nbChar==0 || nbPiegeur==0 || nbTireur==0) {
				nbChar=0;
				nbTireur=0;
				nbPiegeur=0;
				for(int i=0;i<5;i++) {
					tmp=rand.nextInt(3);
					if(tmp==0) {
						nbChar++;
					}
					else if(tmp==1) {
						nbTireur++;
					}
					else {
						nbPiegeur++;
					}
				}
			}
			nbRobotDeChaqueType[0]=nbTireur;
			nbRobotDeChaqueType[1]=nbPiegeur;
			nbRobotDeChaqueType[2]=nbChar;
			
			return nbRobotDeChaqueType;
		}
		
		public static void sortiRandomRobot() throws UnsupportedAudioFileException, IOException, LineUnavailableException { //sort un robot de la base au hasard
			tmp= rand.nextInt(3);
			if(forcer){
				
<<<<<<< HEAD
				if(Jouer.tableauEquipe2[0] != 0){
=======
				if(EssaiIa.tableauEquipe2[0] != 0){
>>>>>>> 01e94f70828b0814cc4ad87c39ee15c5da184990
					tmp = 0;
					
					forcer = false;
				}
<<<<<<< HEAD
				else if(Jouer.tableauEquipe2[1] != 0){
=======
				else if(EssaiIa.tableauEquipe2[1] != 0){
>>>>>>> 01e94f70828b0814cc4ad87c39ee15c5da184990
					tmp = 1;
					
					forcer = false;
				}
<<<<<<< HEAD
				else if(Jouer.tableauEquipe2[2] != 0){
=======
				else if(EssaiIa.tableauEquipe2[2] != 0){
>>>>>>> 01e94f70828b0814cc4ad87c39ee15c5da184990
					tmp = 2;
					
					forcer = false;
					
				}
			}
<<<<<<< HEAD
			if(Jouer.tableauEquipe2[0]!=0 && tmp==0){
				
				if(Plateau.grille[Plateau.grille.length -1][Plateau.grille[0].length - 2].estLibre()){ // l9|c8
					Plateau.grille[Plateau.grille.length -1][Plateau.grille[0].length - 2].addRobot(new Tireur(Jouer.equipeB,2,Plateau.grille.length -1,Plateau.grille[0].length -2));
					Jouer.tableauEquipe2[0]--;
=======
			if(EssaiIa.tableauEquipe2[0]!=0 && tmp==0){
				
				if(Plateau.grille[Plateau.grille.length -1][Plateau.grille[0].length - 2].estLibre()){ // l9|c8
					Plateau.grille[Plateau.grille.length -1][Plateau.grille[0].length - 2].addRobot(new Tireur(EssaiIa.equipeB,2,Plateau.grille.length -1,Plateau.grille[0].length -2));
					EssaiIa.tableauEquipe2[0]--;
>>>>>>> 01e94f70828b0814cc4ad87c39ee15c5da184990
					File sortiefile = new File("music/sortie.wav");
					AudioInputStream sortieAudio = AudioSystem.getAudioInputStream(sortiefile);
					Clip sortie = AudioSystem.getClip();
					sortie.open(sortieAudio);
					sortie.start();
				}
				else if(Plateau.grille[Plateau.grille.length -2][Plateau.grille[0].length -1].estLibre()){ // l8|c9
<<<<<<< HEAD
					Jouer.tableauEquipe2[0]--;
					Plateau.grille[Plateau.grille.length -2][Plateau.grille[0].length -1].addRobot(new Tireur(Jouer.equipeB,2,Plateau.grille.length -2,Plateau.grille[0].length -1));
=======
					EssaiIa.tableauEquipe2[0]--;
					Plateau.grille[Plateau.grille.length -2][Plateau.grille[0].length -1].addRobot(new Tireur(EssaiIa.equipeB,2,Plateau.grille.length -2,Plateau.grille[0].length -1));
>>>>>>> 01e94f70828b0814cc4ad87c39ee15c5da184990
					File sortiefile = new File("music/sortie.wav");
					AudioInputStream sortieAudio = AudioSystem.getAudioInputStream(sortiefile);
					Clip sortie = AudioSystem.getClip();
					sortie.open(sortieAudio);
					sortie.start();
				}
			}
<<<<<<< HEAD
			else if(Jouer.tableauEquipe2[1]!=0 && tmp==1) {
				
				if(Plateau.grille[Plateau.grille.length -1][Plateau.grille[0].length - 2].estLibre()){ // l9|c8
					Plateau.grille[Plateau.grille.length -1][Plateau.grille[0].length - 2].addRobot(new Piegeur(Jouer.equipeB,2,Plateau.grille.length -1,Plateau.grille[0].length -2));
					Jouer.tableauEquipe2[1]--;
=======
			else if(EssaiIa.tableauEquipe2[1]!=0 && tmp==1) {
				
				if(Plateau.grille[Plateau.grille.length -1][Plateau.grille[0].length - 2].estLibre()){ // l9|c8
					Plateau.grille[Plateau.grille.length -1][Plateau.grille[0].length - 2].addRobot(new Piegeur(EssaiIa.equipeB,2,Plateau.grille.length -1,Plateau.grille[0].length -2));
					EssaiIa.tableauEquipe2[1]--;
>>>>>>> 01e94f70828b0814cc4ad87c39ee15c5da184990
					File sortiefile = new File("music/sortie.wav");
					AudioInputStream sortieAudio = AudioSystem.getAudioInputStream(sortiefile);
					Clip sortie = AudioSystem.getClip();
					sortie.open(sortieAudio);
					sortie.start();
				}
				else if(Plateau.grille[Plateau.grille.length -2][Plateau.grille[0].length -1].estLibre()){ // l8|c9
<<<<<<< HEAD
					Plateau.grille[Plateau.grille.length -2][Plateau.grille[0].length -1].addRobot(new Piegeur(Jouer.equipeB,2,Plateau.grille.length -2,Plateau.grille[0].length -1));
					Jouer.tableauEquipe2[1]--;
=======
					Plateau.grille[Plateau.grille.length -2][Plateau.grille[0].length -1].addRobot(new Piegeur(EssaiIa.equipeB,2,Plateau.grille.length -2,Plateau.grille[0].length -1));
					EssaiIa.tableauEquipe2[1]--;
>>>>>>> 01e94f70828b0814cc4ad87c39ee15c5da184990
					File sortiefile = new File("music/sortie.wav");
					AudioInputStream sortieAudio = AudioSystem.getAudioInputStream(sortiefile);
					Clip sortie = AudioSystem.getClip();
					sortie.open(sortieAudio);
					sortie.start();
				}	
			}
<<<<<<< HEAD
			else if (Jouer.tableauEquipe2[2]!=0 && tmp==2) {
				//System.err.println("CHAR SORTIE");
				
				if(Plateau.grille[Plateau.grille.length -1][Plateau.grille[0].length - 2].estLibre()){ // l9|c8
					Plateau.grille[Plateau.grille.length -1][Plateau.grille[0].length - 2].addRobot(new Char(Jouer.equipeB,2,Plateau.grille.length -1,Plateau.grille[0].length -2));
					Jouer.tableauEquipe2[2]--;
=======
			else if (EssaiIa.tableauEquipe2[2]!=0 && tmp==2) {
				//System.err.println("CHAR SORTIE");
				
				if(Plateau.grille[Plateau.grille.length -1][Plateau.grille[0].length - 2].estLibre()){ // l9|c8
					Plateau.grille[Plateau.grille.length -1][Plateau.grille[0].length - 2].addRobot(new Char(EssaiIa.equipeB,2,Plateau.grille.length -1,Plateau.grille[0].length -2));
					EssaiIa.tableauEquipe2[2]--;
>>>>>>> 01e94f70828b0814cc4ad87c39ee15c5da184990
					File sortiefile = new File("music/sortie.wav");
					AudioInputStream sortieAudio = AudioSystem.getAudioInputStream(sortiefile);
					Clip sortie = AudioSystem.getClip();
					sortie.open(sortieAudio);
					sortie.start();
				}
				else if(Plateau.grille[Plateau.grille.length -2][Plateau.grille[0].length -1].estLibre()){ // l8|c9
<<<<<<< HEAD
					Plateau.grille[Plateau.grille.length -2][Plateau.grille[0].length -1].addRobot(new Char(Jouer.equipeB,2,Plateau.grille.length -2,Plateau.grille[0].length -1));
					Jouer.tableauEquipe2[2]--;
=======
					Plateau.grille[Plateau.grille.length -2][Plateau.grille[0].length -1].addRobot(new Char(EssaiIa.equipeB,2,Plateau.grille.length -2,Plateau.grille[0].length -1));
					EssaiIa.tableauEquipe2[2]--;
>>>>>>> 01e94f70828b0814cc4ad87c39ee15c5da184990
					File sortiefile = new File("music/sortie.wav");
					AudioInputStream sortieAudio = AudioSystem.getAudioInputStream(sortiefile);
					Clip sortie = AudioSystem.getClip();
					sortie.open(sortieAudio);
					sortie.start();
				}
			}
			
		}
		
		public static void joue() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
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
			
			boolean valide;
<<<<<<< HEAD
			if(Jouer.infoRobotB.isEmpty()){
				sortiRandomRobot();
				Jouer.GetInfoRobot();
			}
			if(Jouer.infoRobotB.size() <= 1 && Jouer.tableauEquipe2.length != 0){
=======
			if(EssaiIa.infoRobotB.isEmpty()){
				sortiRandomRobot();
				EssaiIa.GetInfoRobot();
			}
			if(EssaiIa.infoRobotB.size() <= 1 && EssaiIa.tableauEquipe2.length != 0){
>>>>>>> 01e94f70828b0814cc4ad87c39ee15c5da184990
				
				forcer = true;
				
				sortiRandomRobot();
				
<<<<<<< HEAD
				Jouer.GetInfoRobot();
=======
				EssaiIa.GetInfoRobot();
>>>>>>> 01e94f70828b0814cc4ad87c39ee15c5da184990
				
			}
			tmp = rand.nextInt(3);
			
			if(tmp==0 ) {
				sortiRandomRobot();
<<<<<<< HEAD
				Jouer.GetInfoRobot();
			}
			else if (tmp==1 && Jouer.infoRobotB.size() > 0) {//bouge un robot au hasard dans un endroit au hasard
				
				int alea =rand.nextInt(Jouer.infoRobotB.size()); //choisi un robot au hasard dans la liste de robot sortie

				Robot aDeplacer = Jouer.infoRobotB.get(alea);
=======
				EssaiIa.GetInfoRobot();
			}
			else if (tmp==1 && EssaiIa.infoRobotB.size() > 0) {//bouge un robot au hasard dans un endroit au hasard
				
				int alea =rand.nextInt(EssaiIa.infoRobotB.size()); //choisi un robot au hasard dans la liste de robot sortie

				Robot aDeplacer = EssaiIa.infoRobotB.get(alea);
>>>>>>> 01e94f70828b0814cc4ad87c39ee15c5da184990
				valide = false;
				while (!valide) {
					

					
					//effectue un deplacement
					
						ArrayList<Coord> possible = Saisie.ChoixListIa(aDeplacer);
						Coord objectif = possible.get(rand.nextInt(possible.size()));
<<<<<<< HEAD
						Action deplacement = new Deplacement(aDeplacer, Jouer.unPlateau.getCellule(objectif));
=======
						Action deplacement = new Deplacement(aDeplacer, EssaiIa.unPlateau.getCellule(objectif));
>>>>>>> 01e94f70828b0814cc4ad87c39ee15c5da184990
						deplacement.agit();
						dep.open(sonDep);
						dep.start();
						//EssaiIa.unPlateau.affichage();
<<<<<<< HEAD
						Jouer.GetInfoRobot();
=======
						EssaiIa.GetInfoRobot();
>>>>>>> 01e94f70828b0814cc4ad87c39ee15c5da184990
						valide = true;
						
						break;
					//effectue une action
					
					}
				}
<<<<<<< HEAD
			else if(tmp == 2 && Jouer.infoRobotB.size() > 0){
				//tirer ou poser une mine
				int alea=rand.nextInt(Jouer.infoRobotB.size()); //choisi un robot au hasard dans la liste de robot sortie
				Robot effectueAction = Jouer.infoRobotB.get(alea);
=======
			else if(tmp == 2 && EssaiIa.infoRobotB.size() > 0){
				//tirer ou poser une mine
				int alea=rand.nextInt(EssaiIa.infoRobotB.size()); //choisi un robot au hasard dans la liste de robot sortie
				Robot effectueAction = EssaiIa.infoRobotB.get(alea);
>>>>>>> 01e94f70828b0814cc4ad87c39ee15c5da184990
				ArrayList<Coord> possible = Saisie.choixActionIa(effectueAction);
				valide = false;
				while(! valide){
					Coord objectif = possible.get(rand.nextInt(possible.size()));
<<<<<<< HEAD
					Action attaque = new Attaque(effectueAction,Jouer.unPlateau.getCellule(objectif));
=======
					Action attaque = new Attaque(effectueAction,EssaiIa.unPlateau.getCellule(objectif));
>>>>>>> 01e94f70828b0814cc4ad87c39ee15c5da184990
					attaque.agit();
					if (effectueAction.getType().equals("Tireur")) {
						clip.open(sonTir);
						clip.start();
						
					} else if (effectueAction.getType().equals("Char")) {
						charTir.open(sonChar);
						charTir.start();
						
					} else if (effectueAction.getType().equals("Piegeur")) {
						poseMine.open(sonMine);
						poseMine.start();
					
					}
<<<<<<< HEAD
					Jouer.GetInfoRobot();
=======
					EssaiIa.GetInfoRobot();
>>>>>>> 01e94f70828b0814cc4ad87c39ee15c5da184990
					valide = true;
					break;
				}
				
			
				
				
			}
		}
	}

						
				//gauche
					
					




