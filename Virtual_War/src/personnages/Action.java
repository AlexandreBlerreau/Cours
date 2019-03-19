package personnages;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public abstract class Action {

	protected  Robot robot;
	protected  Cellule objectif;
	
	public Action (Robot robot, Cellule objectif) {
		this.robot = robot;
		this.objectif=objectif;
		}
	
	public Robot getRobot() {
		return this.robot;
	}
	
	public Cellule getObjectif() {
		return this.objectif;
	}
	
	public abstract void agit() throws UnsupportedAudioFileException, IOException, LineUnavailableException;
}