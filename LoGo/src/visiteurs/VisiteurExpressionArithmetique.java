/*****
 * @author Lewandoski Baptiste, Renaud Guillaume
 * Visiteur d'Expressions Arithmetiques.
 */

package visiteurs;

import exprarith.Divise;
import exprarith.Entier;
import exprarith.Fois;
import exprarith.Moins;
import exprarith.Plus;

public interface VisiteurExpressionArithmetique {
	
	/*****
	 * @param e Entier
	 * Visiteur d'Expression Arithmetique.
	 */
	public void visitEntier(Entier e);
	
	/*****
	 * @param p Plus
	 * Visiteur d'Expression Arithmetique.
	 */
	public void visitPlus(Plus p);
	
	/*****
	 * @param m Moins
	 * Visiteur d'Expression Arithmetique.
	 */
	public void visitMoins(Moins m);
	
	/*****
	 * @param f Fois
	 * Visiteur d'Expression Arithmetique.
	 */
	public void visitFois(Fois f);
	
	/*****
	 * @param d Divise
	 * Visiteur d'Expression Arithmetique.
	 */
	public void visitDivise(Divise d);
	
}
