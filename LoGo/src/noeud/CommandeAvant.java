/*****
 * @author Renaud Guillaume, Lewandoski Baptiste
 * Commande Avant.
 */

package noeud;

import evaluateurs.EvaluateurScript;
import projet.Crayon;

public class CommandeAvant implements NoeudAST {

	private int nb;
	EvaluateurScript es;
	
	/*****
	 * @param nb Int
	 * Constructeur
	 */
	public CommandeAvant(int nb){
		this.nb=nb;
	}

	/*****
	 * @return int Nb
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
		es.visitAvant(this);
	}
	
}
