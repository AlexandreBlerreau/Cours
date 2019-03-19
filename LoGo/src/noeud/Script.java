/*****
 * @author Lewandoski Baptiste, Renaud Guillaume
 * Le Script contenant une liste de Noeuds. 
 */

package noeud;

import java.util.List;

import evaluateurs.EvaluateurScript;
import projet.Crayon;

public class Script implements NoeudAST {
	
	private List<NoeudAST> list;
	EvaluateurScript es;

	/*****
	 * @param list List<NoeudAST>
	 * Constructeur.
	 */
	public Script(List<NoeudAST> list){
		this.list = list;
	}

	/*****
	 * @return list List<NoeudAST>
	 */
	public List<NoeudAST> getList() {
		return list;
	}

	/*****
	 * @param list List<NoeudAST>
	 */
	public void setList(List<NoeudAST> list) {
		this.list = list;
	}
	
	/*****
	 * @param evaluateurCondition EvaluateurCondition
	 * Méthode accept du visiteur.
	 */
	@Override
	public void accept(Crayon cr) {
		es = new EvaluateurScript(cr);
		es.visitScript(this);
	}

}
