/*****
 * @author Lewandoski Baptiste, Renaud Guillaume
 * Visiteur de Conditions.
 */

package visiteurs;

import condition.ConditionEstLeve;
import condition.ConditionEt;
import condition.ConditionNon;
import condition.ConditionOu;
import condition.ConditionPosCouleur;
import condition.ConditionPosX;
import condition.ConditionPosY;

public interface VisiteurCondition {
	
	/*****
	 * @param c Condition
	 * Méthode de visite.
	 */
	public void visitEstLeve(ConditionEstLeve c);
	
	/*****
	 * @param c Condition
	 * Méthode de visite.
	 */
	public void visitPosX(ConditionPosX c);
	
	/*****
	 * @param c Condition
	 * Méthode de visite.
	 */
	public void visitPosY(ConditionPosY c);
	
	/*****
	 * @param c Condition
	 * Méthode de visite.
	 */
	public void visitEt(ConditionEt c);
	
	/*****
	 * @param c Condition
	 * Méthode de visite.
	 */
	public void visitOu(ConditionOu c);
	
	/*****
	 * @param c Condition
	 * Méthode de visite.
	 */
	public void visitPosCouleur(ConditionPosCouleur c);
	
	/*****
	 * @param c Condition
	 * Méthode de visite.
	 */
	public void visitNon(ConditionNon c);
	
}
