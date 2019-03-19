/*****
 * @author Lewandoski Baptiste, Renaug Guillaume
 * Condition "Deux conditions, un "&&" --> True ou False ?"
 */

package condition;

import evaluateurs.EvaluateurCondition;

public class ConditionEt implements Condition {
	
	private Condition c1, c2;

	/***** 
	 * @return c Condition
	 */
	public Condition getC1() {
		return c1;
	}

	/***** 
	 * @return c Condition
	 */
	public Condition getC2() {
		return c2;
	}

	/*****
	 * @param evaluateurCondition EvaluateurCondition
	 * Méthode accept du visiteur.
	 */
	@Override
	public void accept(EvaluateurCondition evaluateurCondition) {
		evaluateurCondition.visitEt(this);
	}
	
	
}
