/*****
 * @author Lewandoski Baptiste, Renaug Guillaume
 * Condition "Position en X du crayon comparé à une quantité."
 */

package condition;

import evaluateurs.EvaluateurCondition;
import exprarith.ExpressionArithmetique;
import noeud.OpCompare;

public class ConditionPosX implements Condition {
	
	
	int n;
	ExpressionArithmetique ea;
	OpCompare oc;
	
	/*****
	 * @param ea ExpressionArithmetique
	 * @param oc OpCompare
	 * @param n Int
	 * Contructeur.
	 */
	public ConditionPosX(ExpressionArithmetique ea, OpCompare oc, int n){
		this.ea=ea;
		this.oc = oc;
		this.n=n;
	}

	/***** 
	 * @return n Int
	 */
	public int getN() {
		return n;
	}
	
	/***** 
	 * @return oc OpCompare
	 */
	public OpCompare getOc() {
		return oc;
	}
	
	
	/*****
	 * @param evaluateurCondition EvaluateurCondition
	 * Méthode accept du visiteur.
	 */
	@Override
	public void accept(EvaluateurCondition evaluateurCondition) {
		evaluateurCondition.visitPosX(this);
	}
	
}
