/*****
 * @author Blerreau Alexandre
 * Classe de curseurs 
 */

package design;

import java.util.HashMap;

import javafx.scene.image.Image;

public class Cursor {

	private HashMap<String,Image> imageList;

	/*****
	 * Constructeur.
	 */
	public Cursor() {
		imageList = initImage();
	}

	/*****
	 * @return HashMap<String,Image>
	 * Une Hashmap<String,Image> avec tous les curseurs initialisés.
	 */
	private HashMap<String,Image> initImage() {	
		HashMap<String,Image> res = new HashMap<String,Image>();
		res.put("NOIR", new Image("file:ressources/cursors/cursorblack.png"));
		res.put("BLANC", new Image("file:ressources/cursors/cursorwhite.png"));
		res.put("GRIS", new Image("file:ressources/cursors/cursorgrey.png"));
		res.put("BLEU", new Image("file:ressources/cursors/cursorblue.png"));
		res.put("VERT", new Image("file:ressources/cursors/cursorgreen.png"));
		res.put("ROUGE", new Image("file:ressources/cursors/cursorred.png"));
		res.put("JAUNE", new Image("file:ressources/cursors/cursoryellow.png"));
		res.put("ROSE", new Image("file:ressources/cursors/cursorpink.png"));
		res.put("ORANGE", new Image("file:ressources/cursors/cursororange.png"));
		res.put("VIOLET",new Image("file:ressources/cursors/cursorpurple.png"));
		res.put("MARRON",new Image("file:ressources/cursors/cursorbrown.png"));	
		return res;
	}

	/*****
	 * @param couleur Sous forme de String
	 * @return Image
	 * La couleur demandée en paramètre doit être sous forme de String (Cf Enum Couleurs)
	 * Renvoie le curseur de la couleur demandée sous la forme "Image"
	 */
	public Image getCursor(String couleur) {
		Image res = imageList.get(couleur.toUpperCase());
		return res;
	}

}
