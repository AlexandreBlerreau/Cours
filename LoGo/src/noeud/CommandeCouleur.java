/*****
 * @author Renaud Guillaume, Lewandoski Baptiste
 * Commande Couleur.
 */

package noeud;

import evaluateurs.EvaluateurScript;
import projet.Couleurs;
import projet.Crayon;

public class CommandeCouleur implements NoeudAST {

	private Couleurs col;
	EvaluateurScript es;
	
	/*****
	 * @param c Couleurs
	 * Constructeur.
	 */
	public CommandeCouleur(Couleurs c) {
		this.col = c;
	}
	
	/*****
	 * @return c Couleurs
	 */
	public Couleurs getCol() {
		return col;
	}

	/*****
	 * @param evaluateurCondition EvaluateurCondition
	 * Méthode accept du visiteur.
	 */
	@Override
	public void accept(Crayon cr) {
		es = new EvaluateurScript(cr);
		es.visitCouleur(this);
	}
	
}
