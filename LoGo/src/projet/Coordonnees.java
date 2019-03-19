/*****
 * @author Lewandoski Baptiste
 * Coordonnées du Crayon
 */

package projet;

public class Coordonnees {
	
	int x;
	int y;

	/*****
	 * @param x Int
	 * @param y Int
	 * Constructeur.
	 */
	public Coordonnees(int x,int y) {
		this.x = x;
		this.y = y;
	}

	/*****
	 * @return x Int
	 */
	public int getX() {
		return x;
	}

	/*****
	 * @param x Int
	 */
	public void setX(int x) {
		this.x = x;
	}

	/*****
	 * @return y Int
	 */
	public int getY() {
		return y;
	}

	/*****
	 * @param y Int
	 */
	public void setY(int y) {
		this.y = y;
	}

}
