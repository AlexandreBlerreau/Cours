/*****
 * @author Renaud Guillaume, Lewandoski Baptiste
 * Classe contenant l'Expression Arithm�tique "Entier"
 */

package exprarith;

import evaluateurs.EvaluateurExpressionArithmetique;

public class Entier implements ExpressionArithmetique{
	
	int n;
	
	/*****
	 * @param e Int
	 * Constructeur
	 */
	public Entier(int n){
		this.n=n;
	}

	/*****
	 * @param eea EvaluateurExpressionArithmetique
	 * M�thode accept du visiteur.
	 */
	@Override
	public void accept(EvaluateurExpressionArithmetique eea) {
		eea.visitEntier(this);
	}

	/*****
	 * @return Int
	 */
	public int getN() {
		return n;
	}

}
