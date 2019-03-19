/*****
 * @author Lewandoski Baptiste, Renaug Guillaume
 * Condition "La couleur du pixel est-elle identique � celle pass�e en param�tre ?"
 */

package condition;

import evaluateurs.EvaluateurCondition;
import projet.Couleurs;

public class ConditionPosCouleur implements Condition {
	
	Couleurs c;
	
	/***** 
	 * @return c Condition
	 */
	public Couleurs getColor() {
		return c;
	}
	
	/*****
	 * @param evaluateurCondition EvaluateurCondition
	 * M�thode accept du visiteur.
	 */
	@Override
	public void accept(EvaluateurCondition evaluateurCondition) {
		evaluateurCondition.visitPosCouleur(this);
	}

}
