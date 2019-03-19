/*****
 * @author Renaud Guillaume, Lewandoski Baptiste
 * Commande Noeud Repeter.
 */

package noeud;

import evaluateurs.EvaluateurScript;
import exprarith.ExpressionArithmetique;
import projet.Crayon;

public class NoeudRepeter implements NoeudAST{
	
	private EvaluateurScript es;
	private ExpressionArithmetique ea;
	private Script script;

	/*****
	 * @param ea ExpressionArithmetique
	 * @param script Script
	 * Constructeur
	 */
	public NoeudRepeter(ExpressionArithmetique ea,Script script) {
		this.ea = ea;
		this.script = script;
	}

	/*****
	 * @return ea ExpressionArithmetique
	 */
	public ExpressionArithmetique getEA() {
		return ea;
	}
	
	/*****
	 * @return s Script
	 */
	public Script getS() {
		return script;
	}
	
	/*****
	 * @param evaluateurCondition EvaluateurCondition
	 * Méthode accept du visiteur.
	 */
	@Override
	public void accept(Crayon cr) {
		es = new EvaluateurScript(cr);
		es.visitRepeter(this);
	}
	
}
