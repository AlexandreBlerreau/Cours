package pack;

import java.io.Console;
import java.util.Scanner;

public class Aide {

	private Scanner sc;
	
	public Aide() {
		super();
		affichage();
		sc = new Scanner(System.in);
	}
	
	private void affichage() {
		System.out.println("Bienvenue sur Wazabi!\n");
		System.err.println("    Wa");
		System.err.println("      zA");
		System.err.println("    Bi!\n");
		
		System.out.println("Voici les règles:");
			System.out.println();
			
			
			System.out.println("Un seul but : se débarrasser de ses dés. Le piège : moins vous en avez, plus ce sera difficile !\n" + 
					"Les règles très simples cachent un mécanisme hyper original qui risque de vous rendre accro :\nmoins vous avez de dés, plus minces sont vos chances de vous en défausser.\nLes cartes aux effets dévastateurs pour vos adversaires sont jouées en fonction de votre tirage de dés : \nelles modifient en permanence le nombre de cartes et de dés détenus par chacun. Dés et cartes passent de main en main ou sont défaussés,\nchangeant constamment les rapports de force !\n\n");
			
			
			
		}
	}

