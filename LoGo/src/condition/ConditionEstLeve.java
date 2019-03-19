/*****
 * @author Lewandoski Baptiste, Renaug Guillaume
 * Condition "Le Crayon est-il levé ?"
 */

package condition;

import evaluateurs.EvaluateurCondition;

public class ConditionEstLeve implements Condition {

	Condition c;
	
	/***** 
	 * @return c Condition
	 */
	public Condition getC() {
		return c;
	}
	
	/*****
	 * @param evaluateurCondition EvaluateurCondition
	 * Méthode accept du visiteur.
	 */
	@Override
	public void accept(EvaluateurCondition evaluateurCondition) {
		evaluateurCondition.visitEstLeve(this);
	}

}
