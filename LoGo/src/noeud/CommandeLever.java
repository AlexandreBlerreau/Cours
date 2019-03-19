/*****
 * @author Renaud Guillaume, Lewandoski Baptiste
 * Commande Lever.
 */

package noeud;

import evaluateurs.EvaluateurScript;
import projet.Crayon;

public class CommandeLever implements NoeudAST {

	private boolean b;
	EvaluateurScript es;
	
	/*****
	 * Constructeur.
	 */
	public CommandeLever() {
		this.b = false;
	}
	
	/*****
	 * @return b boolean
	 */
	public boolean isB() {
		return b;
	}

	/*****
	 * @param evaluateurCondition EvaluateurCondition
	 * Méthode accept du visiteur.
	 */
	@Override
	public void accept(Crayon cr) {
		es = new EvaluateurScript(cr);
		es.visitLever(this);
	}

}