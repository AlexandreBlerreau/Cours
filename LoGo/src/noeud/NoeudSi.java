/*****
 * @author Renaud Guillaume, Lewandoski Baptiste
 * Commande Noeud Si.
 */

package noeud;

import condition.Condition;
import evaluateurs.EvaluateurScript;
import projet.Crayon;

public class NoeudSi implements NoeudAST {
	
	private Condition condition;
	private Script alors;
	private Script sinon;
	private EvaluateurScript es;
	
	/*****
	 * @param condition Condition
	 * @param alors Script
	 * @param sinon Script
	 * Constructeur.
	 */
	public NoeudSi(Condition condition, Script alors, Script sinon){
		this.condition = condition;
		this.alors = alors;
		this.sinon = sinon;
	}

	/*****
	 * @return c Condition
	 */
	public Condition getCondition() {
		return condition;
	}

	/*****
	 * @return alors Script
	 */
	public Script getAlors() {
		return alors;
	}

	/*****
	 * @return sinon Script
	 */
	public Script getSinon() {
		return sinon;
	}

	/*****
	 * @return es EvaluateurScript
	 */
	public EvaluateurScript getEs() {
		return es;
	}

	/*****
	 * @param evaluateurCondition EvaluateurCondition
	 * Méthode accept du visiteur.
	 */
	@Override
	public void accept(Crayon cr) {
		es = new EvaluateurScript(cr);
		es.visitSi(this);
	}
	
}
