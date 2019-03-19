/*****
 * @author Lewandoski Baptiste, Renaug Guillaume
 * Condition "Position en Y du crayon comparé à une quantité."
 */

package condition;

import evaluateurs.EvaluateurCondition;
import exprarith.ExpressionArithmetique;
import noeud.OpCompare;

public class ConditionPosY implements Condition {

	int n;
	ExpressionArithmetique ea;
	OpCompare oc;
	
	/*****
	 * @param ea ExpressionArithmetique
	 * @param oc OpCompare
	 * @param n Int
	 * Contructeur.
	 */
	public ConditionPosY(ExpressionArithmetique ea, OpCompare oc, int n){
		this.ea=ea;
		this.oc = oc;
		this.n=n;
	}
	
	/***** 
	 * @return oc OpCompare
	 */
	public OpCompare getOc() {
		return this.oc;
	}
	
	/***** 
	 * @return ea ExpressionArithmetique
	 */
	public ExpressionArithmetique getEa() {
		return ea;
	}
	
	/***** 
	 * @return n Int
	 */
	public int getN() {
		return n;
	}

	/*****
	 * @param evaluateurCondition EvaluateurCondition
	 * Méthode accept du visiteur.
	 */
	@Override
	public void accept(EvaluateurCondition evaluateurCondition) {
		evaluateurCondition.visitPosY(this);
	}
	
}
