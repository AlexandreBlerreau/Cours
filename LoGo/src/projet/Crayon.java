/*****
 * @author Lewandoski Baptiste, Renaud Guillaume
 * Crayon de Dessin du Logiciel.
 */

package projet;

import java.util.Observable;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;

public class Crayon extends Observable{

	private static Crayon instance = null;
	private Coordonnees coor;
	private Couleurs color = Couleurs.NOIR;
	private int orientation=0; 
	private int width = 2;
	private boolean ecrit = true;
	AnimationTimer timer;
	boolean toggle;
	long lastTimerCall;
	int x;
	int y;
	int oldx;
	int oldy;

	GraphicsContext gc = null;

	/*****
	 * @return c Crayon
	 * Singleton du Crayon
	 */
	public static synchronized Crayon getInstance(){
		if (instance==null) instance = new Crayon(0,0);
		return instance;
	}

	/*****
	 * @param x Int
	 * @param y Int
	 * Constructeur.
	 */
	public Crayon(int x,int y) {
		coor = new Coordonnees(x,y);
	}

	/*****
	 * @param gc GraphicsContext
	 */
	public void setGc(GraphicsContext gc){
		this.gc = gc;
		this.setChanged();
		this.notifyObservers();
	}
	
	/*****
	 * @return gc GraphicsContext
	 */
	public GraphicsContext getGc(){
		return this.gc;
	}

	/*****
	 * @param w Int
	 */
	public void setWidth(int w) {
		this.width = w;
		this.setChanged();
		this.notifyObservers();
	}

	/*****
	 * @return width Int
	 */
	public int getWidth() {
		return width;
	}

	/*****
	 * @return c Coordonnees
	 */
	public Coordonnees getCoord() {
		return coor;
	}

	/*****
	 * @param x Int
	 * @param y Int
	 */
	public void setCoord(int x, int y){
		this.coor.setX(x);
		this.coor.setY(y);
		this.setChanged();
		this.notifyObservers();
	}

	/*****
	 * @return orientation Int
	 */
	public int getOrientation() {
		return orientation;
	}

	/*****
	 * @param orientation Int
	 */
	public void setOrientation(int orientation) {
		this.orientation = orientation;
		this.setChanged();
		this.notifyObservers();
	}

	/*****
	 * @return c Couleurs
	 */
	public Couleurs getColor() {
		return color;
	}

	/*****
	 * @param color Couleurs
	 */
	public void setColor(Couleurs color) {
		this.color = color;
		this.setChanged();
		this.notifyObservers();
	}

	/*****
	 * @return ecrit boolean
	 */
	public boolean isEcrit() {
		return ecrit;
	}

	/*****
	 * @param ecrit boolean
	 */
	public void setEcrit(boolean ecrit) {
		this.ecrit = ecrit;
		this.setChanged();
		this.notifyObservers();
	}

	/*****
	 * @param x Int
	 */
	public void setX(int x) {
		coor.x = x;
		this.setChanged();
		this.notifyObservers();
	}

	/*****
	 * @param y Int
	 */
	public void setY(int y) {
		coor.y = y;
		this.setChanged();
		this.notifyObservers();
	}

	/*****
	 * @return x Int
	 */
	public int getX() {
		return coor.x;
	}

	/*****
	 * @return y Int
	 */
	public int getY() {
		return coor.y;
	}

}


