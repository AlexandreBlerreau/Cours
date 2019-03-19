/*****
 * @author Lewandoski Baptiste, Renaud Guillaume
 * Interface des conditions pour le pattern visiteur.
 */

package condition;

import evaluateurs.EvaluateurCondition;

public interface Condition {

	/*****
	 * @param evaluateurCondition EvaluateurCondition
	 */
	void accept(EvaluateurCondition evaluateurCondition);
	
}
