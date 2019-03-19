/*****
 * @author Renaud Guillaume, Lewandoski Baptiste
 * Commande Droite.
 */

package noeud;

import evaluateurs.EvaluateurScript;
import projet.Crayon;

public class CommandeDroite implements NoeudAST {

	private int nb;
	EvaluateurScript es;
	
	/*****
	 * @param nb Int
	 * Constructeur.
	 */
	public CommandeDroite(int nb) {
		this.nb = nb;
	}

	/*****
	 * @return nb Int
	 */
	public int getNb() {
		return this.nb;
	}

	/*****
	 * @param evaluateurCondition EvaluateurCondition
	 * Méthode accept du visiteur.
	 */
	@Override
	public void accept(Crayon cr) {
		es = new EvaluateurScript(cr);
		es.visitDroite(this);
	}

}
