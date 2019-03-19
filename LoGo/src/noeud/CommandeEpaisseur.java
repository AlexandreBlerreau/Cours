/*****
 * @author Renaud Guillaume, Lewandoski Baptiste
 * Commande Epaisseur.
 */

package noeud;

import evaluateurs.EvaluateurScript;
import projet.Crayon;

public class CommandeEpaisseur implements NoeudAST {
	
	private int nb;
	EvaluateurScript es;
	
	/*****
	 * @param nb Int
	 * Constructeur
	 */
	public CommandeEpaisseur(int nb){
		this.nb=nb;
	}
	
	/*****
	 * @return nb Int
	 */
	public int getNb() {
		return nb;
	}

	/*****
	 * @param evaluateurCondition EvaluateurCondition
	 * Méthode accept du visiteur.
	 */
	@Override
	public void accept(Crayon cr) {
		es = new EvaluateurScript(cr);
		es.visitEpaisseur(this);
	}
	
}
