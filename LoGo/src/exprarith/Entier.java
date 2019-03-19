/*****
 * @author Renaud Guillaume, Lewandoski Baptiste
 * Classe contenant l'Expression Arithmétique "Entier"
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
	 * Méthode accept du visiteur.
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
