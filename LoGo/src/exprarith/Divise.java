/*****
 * @author Renaud Guillaume, Lewandoski Baptiste
 * Classe contenant l'Expression Arithm�tique "Divise"
 */

package exprarith;

import evaluateurs.EvaluateurExpressionArithmetique;

public class Divise implements ExpressionArithmetique {

	ExpressionArithmetique left;
	ExpressionArithmetique right;
	
	/*****
	 * @param e1 ExpressionArithmetique
	 * @param e2 ExpressionArithmetique
	 * Constructeur
	 */
	public Divise(ExpressionArithmetique e1, ExpressionArithmetique e2){
		this.left=e1;
		this.right=e2;
	}

	/*****
	 * @param eea EvaluateurExpressionArithmetique
	 * M�thode accept du visiteur.
	 */
	@Override
	public void accept(EvaluateurExpressionArithmetique eea) {
		eea.visitDivise(this);
	}

	/*****
	 * @return ExpressionArithmetique
	 */
	public ExpressionArithmetique getLeft() {
		return left;
	}

	/*****
	 * @return ExpressionArithmetique
	 */
	public ExpressionArithmetique getRight() {
		return right;
	}
	
}
