/*****
 * @author Renaud Guillaume, Lewandoski Baptiste
 * Commande AllerA.
 */

package noeud;

import evaluateurs.EvaluateurScript;
import projet.Crayon;

public class CommandeAllerA implements NoeudAST {
	
	public int n1,n2;
	EvaluateurScript es;
	
	/*****
	 * @param n1 Int
	 * @param n2 Int
	 */
	public CommandeAllerA(int n1,int n2) {
		this.n1 = n1;
		this.n2 = n2;
	}

	/*****
	 * @return n1 Int
	 */
	public int getN1() {
		return n1;
	}

	/*****
	 * @return n2 Int
	 */
	public int getN2() {
		return n2;
	}

	/*****
	 * @param evaluateurCondition EvaluateurCondition
	 * Méthode accept du visiteur.
	 */
	@Override
	public void accept(Crayon cr) {
		es = new EvaluateurScript(cr);
		es.visitAllerA(this);
	}
	
}
