/*****
 * @author Lewandoski Baptiste, Renaud Guillaume
 * Classe d'�valuation de conditions. Le bool�an pr�sent dans la classe sera modifi� par le visiteur.
 */

package evaluateurs;

import condition.ConditionEstLeve;
import condition.ConditionEt;
import condition.ConditionNon;
import condition.ConditionOu;
import condition.ConditionPosCouleur;
import condition.ConditionPosX;
import condition.ConditionPosY;
import projet.Crayon;
import visiteurs.VisiteurCondition;

public class EvaluateurCondition implements VisiteurCondition {

	public Boolean value = true;
	private Crayon crayon;

	/*****
	 * @param cr Crayon
	 * Constructeur.
	 */
	public EvaluateurCondition(Crayon cr) {
		super();
		this.crayon=cr;
	}

	/*****
	 * @return Boolean
	 * La valeur actuelle du bool�en pr�sent dans la classe.
	 */
	public boolean getValue(){
		return value;
	}

	/*****
	 * @param c ConditionEstLeve
	 * Condition estLev� (True si estLev�, False sinon.)
	 */
	@Override
	public void visitEstLeve(ConditionEstLeve c) {
		this.value = crayon.isEcrit();
	}

	/*****
	 * @param c ConditionPosX
	 * Compare les positions du crayon en X et de l'autre chiffre donn�.
	 */
	@SuppressWarnings("unlikely-arg-type")
	@Override
	public void visitPosX(ConditionPosX c) {
		if(c.getOc().equals("<=")) {
			if(crayon.getX() <= c.getN()) {
				value=true;
			}
			else {
				value=false;
			}
		}
		if(c.getOc().equals(">=")) {
			if(crayon.getX() >= c.getN()) {
				value=true;
			}
			else {
				value=false;
			}
		}	
		if(c.getOc().equals("<")) {
			if(crayon.getX() < c.getN()) {
				value=true;
			}
			else {
				value=false;
			}
		}
		if(c.getOc().equals(">")) {
			if(crayon.getX() > c.getN()) {
				value=true;
			}
			else {
				value=false;
			}
		}
		if(c.getOc().equals("==")) {
			if(crayon.getX() == c.getN()) {
				value=true;
			}
			else {
				value=false;
			}
		}
	}

	@SuppressWarnings("unlikely-arg-type")
	@Override
	public void visitPosY(ConditionPosY c) {
		if(c.getOc().equals("<=")) {
			if(crayon.getY() <= c.getN()) {
				value=true;
			}
			else {
				value=false;
			}
		}
		if(c.getOc().equals(">=")) {
			if(crayon.getY() >= c.getN()) {
				value=true;
			}
			else {
				value=false;
			}
		}
		if(c.getOc().equals("<")) {
			if(crayon.getY() < c.getN()) {
				value=true;
			}
			else {
				value=false;
			}
		}
		if(c.getOc().equals(">")) {
			if(crayon.getY() > c.getN()) {
				value=true;
			}
			else {
				value=false;
			}
		}
		if(c.getOc().equals("==")) {
			if(crayon.getY() == c.getN()) {
				value=true;
			}
			else {
				value=false;
			}
		}
	}
	
	/*****
	 * @param c ConditionEt
	 * True ou false selon la comparaison en "&&"
	 */
	@Override
	public void visitEt(ConditionEt c) {
		c.getC1().accept(this);
		boolean b1 = this.getValue();
		c.getC2().accept(this);
		boolean b2 = this.getValue();
		this.value = b1 && b2;
	}

	/*****
	 * @param c ConditionOu
	 * True ou false selon la comparaison en "||"
	 */
	@Override
	public void visitOu(ConditionOu c) {
		c.getC1().accept(this);
		boolean b1 = this.getValue();
		c.getC2().accept(this);
		boolean b2 = this.getValue();
		this.value = b1 || b2;
	}

	/*****
	 * @param c ConditionPosCouleur
	 * True ou False si la couleur en param�tre est identique � celle du pixel.
	 */
	@Override
	public void visitPosCouleur(ConditionPosCouleur c) {
		if(crayon.getColor() == c.getColor()) {
			value=true;
		}
		else {
			value=false;
		}
	}

	/*****
	 * @param c ConditionNon
	 * Inverse de la Condition donn�e.
	 */
	@Override
	public void visitNon(ConditionNon c) {
		c.getC().accept(this);
		value = !value;
	}
}
