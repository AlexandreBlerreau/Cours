/*****
 * @author Lewandoski Baptiste
 * Enum�ration de Couleurs
 */

package projet;

import javafx.scene.paint.Color;

public enum Couleurs {

	BLANC (Color.WHITE, "-fx-background-color: white"),
	GRIS (Color.GREY, "-fx-background-color: grey"),
	BLEU (Color.BLUE, "-fx-background-color: blue"),
	VERT (Color.GREEN, "-fx-background-color: green"),
	ROUGE (Color.RED, "-fx-background-color: red"),
	JAUNE (Color.YELLOW, "-fx-background-color: yellow"),
	ROSE (Color.PINK, "-fx-background-color: pink"),
	ORANGE (Color.ORANGE, "-fx-background-color: orange"),
	VIOLET (Color.PURPLE, "-fx-background-color: purple"),
	NOIR (Color.BLACK, "-fx-background-color: black"),
	MARRON (Color.BROWN, "-fx-background-color: brown");

	private Color col;
	private String description;

	/*****
	 * @param c Color
	 * Constructeur.
	 */
	Couleurs(Color c, String s)  {
		this.col = c;
		description=s;
	}

	/*****
	 * @return c Color
	 */
	public Color getColor(){
		return this.col;
	}
	
	public String getDescription(){
		return description;
	}

}
