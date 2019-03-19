/*****
 * @author Renaud Guillaume, Lewandoski Baptiste
 * Interface Expression Arithmetique
 */

package exprarith;

import evaluateurs.EvaluateurExpressionArithmetique;

public interface ExpressionArithmetique {
	
	/*****
	 * @param eea EvaluateurExpressionArithmetique
	 */
	void accept(EvaluateurExpressionArithmetique eea);
	
}
