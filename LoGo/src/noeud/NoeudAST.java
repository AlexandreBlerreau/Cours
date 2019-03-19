/*****
 * @author Renaud Guillaume
 * Interface des Noeuds.
 */

package noeud;

import projet.Crayon;

public interface NoeudAST {
	
	/*****
	 * @param cr Crayon
	 */
	public void accept(Crayon cr);

}
