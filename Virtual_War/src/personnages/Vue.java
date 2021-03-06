package personnages;


public class Vue {

	private int equipe;
	private int cptMort=0;
	public int nbMine = 10;
	
	public Vue(int equipe, Plateau plateau){
		this.equipe=equipe;
		
	}

	public int getEquipe() {
		return equipe;
	}

	
	
	public void poserRobot(Robot robot, Coord coordonnee){
		Plateau.grille[coordonnee.getPositionX()][coordonnee.getPositionY()].deplaceSur(robot);;
	}
	
	public void viderCase(Robot robot){ 
		Plateau.grille[robot.getCoordonnee().getPositionX()][robot.getCoordonnee().getPositionY()].videCase();
	}
	
	
	//case vide ? 
	public boolean estDisponible(Coord coordonnee){
		return Plateau.grille[coordonnee.getPositionX()][coordonnee.getPositionY()].estLibre();
	}
	
	//jvois pas à quoi ça sert
	public int getContenu(Coord coordonnee){
		return -1;
	}
	
	//si le int de estBase() correspond à son équipe il est à la base 
	public boolean estBase(Robot robot){
		return Plateau.grille[robot.getCoordonnee().getPositionX()][robot.getCoordonnee().getPositionY()].estBase()==robot.getEquipe();
	}
	
	//savoir si il est vivant ?
	public boolean estOK(Robot robot){
		return robot.getEnergie()>0;
		//vivant si sn energie est differente de 0 
	}

	public int getCptMort() {
		return cptMort;
	}

	public void augmenteCptMort() {
		this.cptMort++;
	}
	
	
}