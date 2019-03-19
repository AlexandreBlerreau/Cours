/*****
 * @author Renaud Guillaume, Lewandoski Baptiste
 * Commande Poser.
 */

package noeud;

import evaluateurs.EvaluateurScript;
import projet.Crayon;

public class CommandePoser implements NoeudAST {

	private boolean b;
	EvaluateurScript es;
	
	/*****
	 * Constructeur.
	 */
	public CommandePoser() {
		this.b = true;
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
		es.visitPoser(this);
	}

}
