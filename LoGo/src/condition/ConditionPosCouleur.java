/*****
 * @author Lewandoski Baptiste, Renaug Guillaume
 * Condition "La couleur du pixel est-elle identique à celle passée en paramètre ?"
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
	 * Méthode accept du visiteur.
	 */
	@Override
	public void accept(EvaluateurCondition evaluateurCondition) {
		evaluateurCondition.visitPosCouleur(this);
	}

}
