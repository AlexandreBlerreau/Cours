/*****
 * @author Lewandoski Baptiste, Renaug Guillaume
 * Condition "Inverse de la condition donn�e"
 */

package condition;

import evaluateurs.EvaluateurCondition;

public class ConditionNon implements Condition {
	
	Condition c;
	
	/***** 
	 * @return c Condition
	 */
	public Condition getC() {
		return c;
	}

	/*****
	 * @param evaluateurCondition EvaluateurCondition
	 * M�thode accept du visiteur.
	 */
	@Override
	public void accept(EvaluateurCondition evaluateurCondition) {
		evaluateurCondition.visitNon(this);
	}

}
