/*****
 * @author Lewandoski Baptiste, Renaud Guillaume
 * Classe d'évaluation de conditions. Le booléan présent dans la classe sera modifié par le visiteur.
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
	 * La valeur actuelle du booléen présent dans la classe.
	 */
	public boolean getValue(){
		return value;
	}

	/*****
	 * @param c ConditionEstLeve
	 * Condition estLevé (True si estLevé, False sinon.)
	 */
	@Override
	public void visitEstLeve(ConditionEstLeve c) {
		this.value = crayon.isEcrit();
	}

	/*****
	 * @param c ConditionPosX
	 * Compare les positions du crayon en X et de l'autre chiffre donné.
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
	 * True ou False si la couleur en paramètre est identique à celle du pixel.
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
	 * Inverse de la Condition donnée.
	 */
	@Override
	public void visitNon(ConditionNon c) {
		c.getC().accept(this);
		value = !value;
	}
}
