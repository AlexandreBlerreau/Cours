/*****
 * @author Renaud Guillaume, Lewandoski Baptiste
 * Commande Noeud Tant Que.
 */

package noeud;

import condition.Condition;
import evaluateurs.EvaluateurScript;
import projet.Crayon;

public class NoeudTantQue implements NoeudAST {

	private Condition condition;
	private Script s;
	private EvaluateurScript es;
	
	/*****
	 * @param condition Condition
	 * @param script Script
	 * Constructeur.
	 */
	public NoeudTantQue(Condition condition,Script script) {
		this.condition = condition;
		this.s = script;
	}

	/*****
	 * @return c Condition
	 */
	public Condition getCondition() {
		return condition;
	}

	/*****
	 * @return s Script
	 */
	public Script getS() {
		return s;
	}

	/*****
	 * @param evaluateurCondition EvaluateurCondition
	 * Méthode accept du visiteur.
	 */
	@Override
	public void accept(Crayon cr) {
		es = new EvaluateurScript(cr);
		es.visitTantQue(this);
	}
	
}
