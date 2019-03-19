/*****
 * @author Lewandoski Baptiste, Renaud Guillaume
 */

package evaluateurs;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import noeud.CommandeAllerA;
import noeud.CommandeAvant;
import noeud.CommandeCouleur;
import noeud.CommandeDroite;
import noeud.CommandeEpaisseur;
import noeud.CommandeGauche;
import noeud.CommandeLever;
import noeud.CommandePoser;
import noeud.CourbeBezier;
import noeud.NoeudAST;
import noeud.NoeudRepeter;
import noeud.NoeudSi;
import noeud.NoeudTantQue;
import noeud.Script;
import projet.Crayon;
import visiteurs.VisiteurNoeudAST;

public class EvaluateurScript implements VisiteurNoeudAST {

	private Crayon crayon;

	/*****
	 * @param cr Crayon
	 * Constructeur.
	 */
	public EvaluateurScript(Crayon cr) {
		this.crayon = cr;
	}

	/*****
	 * @return Le Crayon utilis�.
	 */
	public Crayon getCrayon() {
		return this.crayon;
	}

	/*****
	 * @param c CommandeAllerA
	 * D�place le crayon � l'endroit souhait�.
	 */
	public void visitAllerA(CommandeAllerA c) {
		crayon.setCoord(c.getN1(),c.getN2());
	}

	/*****
	 * @param c CommandeCouleur
	 * Change la couleur du crayon.
	 */
	public void visitCouleur(CommandeCouleur c) {
		crayon.setColor(c.getCol());
	}

	/*****
	 * @param c CommandeEpaisseur
	 * Change l'�paisseur du crayon.
	 */
	public void visitEpaisseur(CommandeEpaisseur c) {
		crayon.setWidth(c.getNb());
	}

	/*****
	 * @param c CommandeLever
	 * L�ve le crayon.
	 */
	public void visitLever(CommandeLever c) {
		crayon.setEcrit(c.isB());
	}

	/*****
	 * @param c CommandePoser
	 * Pose le crayon.
	 */
	public void visitPoser(CommandePoser c) {
		crayon.setEcrit(c.isB());
	}

	/*****
	 * @param c CommandeGauche
	 * Oriente � gauche de la quantit� souhait�e.
	 */
	public void visitGauche(CommandeGauche c) {
		crayon.setOrientation(crayon.getOrientation()-c.getNb());

	}

	/*****
	 * @param c CommandeDroite
	 * Oriente � droite de la quantit� souhait�e.
	 */
	public void visitDroite(CommandeDroite c) {
		crayon.setOrientation(crayon.getOrientation()+c.getNb());
	}

	
	/*****
	 * @param c CommandeAvant
	 * Avance de la quantit� souhait�e.
	 */
	public void visitAvant(CommandeAvant c) {
		int oldx = crayon.getX();
		int oldy = crayon.getY();
		int newx = (int) (c.getNb() * Math.cos(Math.toRadians(crayon.getOrientation()))+ crayon.getX());
		int newy = (int) (c.getNb() * Math.sin(Math.toRadians(crayon.getOrientation()))+crayon.getY());
		if(crayon.isEcrit()){
			crayon.getGc().setStroke(crayon.getColor().getColor());
			crayon.getGc().setLineWidth(crayon.getWidth());
			crayon.getGc().strokeLine(crayon.getX(),crayon.getY(),newx,newy);		
		}
		crayon.setX(newx);
		crayon.setY(newy);
		crayon.getGc().moveTo(crayon.getX(),crayon.getY());
		if(crayon.getX() > 420 || crayon.getX() < 0 || crayon.getY() > 360 || crayon.getY() < 0){
			Alert erreur = new Alert(AlertType.ERROR);
			erreur.setContentText("Vous dépassez de la zone de dessin. Coordonnées réinitialisées, orientation vers la droite.");
			erreur.setTitle("Erreur LoGo");
			erreur.setHeaderText(null);
			erreur.show();
			crayon.setX(oldx);
			crayon.setY(oldy);
			crayon.setOrientation(0);
		}
	}

	/*****
	 * @param s Script
	 * Visite le script et �x�cute les commandes pr�sentes dedans.
	 */
	@Override
	public void visitScript(Script s) {
		for(NoeudAST n : s.getList()){
			n.accept(crayon);
		}
	}
	
	/*****
	 * @param n NoeudSi
	 * Visite le si et �x�cute les commandes pr�sentes dedans.
	 */
	@Override
	public void visitSi(NoeudSi n) {
		EvaluateurCondition ec = new EvaluateurCondition(crayon);
		n.getCondition().accept(ec);
		boolean condition = ec.getValue();
		Script executionAlors = n.getAlors();
		Script executionSinon = n.getSinon();
		if(condition) {
			executionAlors.accept(crayon);
		}
		else {
			executionSinon.accept(crayon);
		}
	}

	/*****
	 * @param n NoeudRepeter
	 * Visite le repeter et �x�cute les commandes pr�sentes dedans.
	 */
	@Override
	public void visitRepeter(NoeudRepeter n) {
		EvaluateurExpressionArithmetique v = new EvaluateurExpressionArithmetique();  
		n.getEA().accept(v);
		int nbfois = v.getValue();
		Script execution = n.getS();
		while (nbfois > 0) {
			execution.accept(crayon);
			nbfois--;
		}
	}

	/*****
	 * @param n NoeudTantQue
	 * Visite le tant que et �x�cute les commandes pr�sentes dedans.
	 */
	@Override
	public void visitTantQue(NoeudTantQue n) {
		EvaluateurCondition ec = new EvaluateurCondition(crayon);
		n.getCondition().accept(ec);
		boolean condition = ec.getValue();
		Script execution = n.getS();
		while(condition) {
			execution.accept(crayon);
		}
	}
	
	public void visitBezier(CourbeBezier cb){
		crayon.getGc().moveTo(crayon.getX(), crayon.getY());
		crayon.getGc().bezierCurveTo(cb.getParam1(), cb.getParam2(), cb.getParam3(), cb.getParam4(), cb.getParam5(), cb.getParam6());
		crayon.getGc().stroke();
		crayon.setX((int) cb.getParam5());
		crayon.setY((int) cb.getParam6());
	}

}
