/*****
 * @author Lewandoski Baptiste, Renaud Guillaume
 * Classe d'évaluation d'expressions arithmétique. La valeur présents dans la classe sera modifiée par le visiteur.
 */

package evaluateurs;

import exprarith.Divise;
import exprarith.Entier;
import exprarith.Fois;
import exprarith.Moins;
import exprarith.Plus;
import visiteurs.VisiteurExpressionArithmetique;

public class EvaluateurExpressionArithmetique implements VisiteurExpressionArithmetique {

	private int value=0;

	/*****
	 * @param cr Crayon
	 * Constructeur.
	 */
	public EvaluateurExpressionArithmetique() {
		super();
	}

	
	/*****
	 * @return int
	 * La valeur actuelle du booléen présent dans la classe.
	 */
	public int getValue() {
		return value;
	}

	/*****
	 * @param e Entier 
	 * Si on croise un entier on le stock dans la valeur de la classe.
	 */
	@Override
	public void visitEntier(Entier e) {
		value = e.getN();
	}

	/*****
	 * @param p Plus
	 * Si on croise un plus on additionne et ajoute à la value.
	 */
	@Override
	public void visitPlus(Plus p) {
		p.getLeft().accept(this);
		int valLeft = value;
		p.getRight().accept(this);
		int valRight = value;
		value = valLeft + valRight;
	}

	/*****
	 * @param m Moins
	 * Si on croise un moins on soustrait et ajoute à la value.
	 */
	@Override
	public void visitMoins(Moins m) {
		m.getLeft().accept(this);
		int valLeft = value;
		m.getRight().accept(this);
		int valRight = value;
		value = valLeft - valRight;
	}

	/*****
	 * @param f Fois
	 * Si on croise un fois on multiplie et ajoute à la value.
	 */
	@Override
	public void visitFois(Fois f) {
		f.getLeft().accept(this);
		int valLeft = value;
		f.getRight().accept(this);
		int valRight = value;
		value = valLeft * valRight;
	}

	/*****
	 * @param d Divise
	 * Si on croise un divise on divise et ajoute à la value.
	 */
	@Override
	public void visitDivise(Divise d) {
		d.getLeft().accept(this);
		int valLeft = value;
		d.getRight().accept(this);
		int valRight = value;
		value = valLeft / valRight;
	}

}
