/*****
 * @author Lewandoski Baptiste, Renaud Guillaume
 * Visiteur de Commandes.
 */

package visiteurs;

import noeud.CommandeAllerA;
import noeud.CommandeAvant;
import noeud.CommandeCouleur;
import noeud.CommandeDroite;
import noeud.CommandeEpaisseur;
import noeud.CommandeGauche;
import noeud.CommandeLever;
import noeud.CommandePoser;
import noeud.NoeudRepeter;
import noeud.NoeudSi;
import noeud.NoeudTantQue;
import noeud.Script;

public interface VisiteurNoeudAST {
	
	/*****
	 * @param c Commande
	 * Visiteur de Commande.
	 */
	public void visitAllerA(CommandeAllerA c);
	
	/*****
	 * @param c Commande
	 * Visiteur de Commande.
	 */
	public void visitCouleur(CommandeCouleur c);
	
	/*****
	 * @param c Commande
	 * Visiteur de Commande.
	 */
	public void visitAvant(CommandeAvant c);
	
	/*****
	 * @param c Commande
	 * Visiteur de Commande.
	 */
	public void visitDroite(CommandeDroite c);
	
	/*****
	 * @param c Commande
	 * Visiteur de Commande.
	 */
	public void visitEpaisseur(CommandeEpaisseur c);
	
	/*****
	 * @param c Commande
	 * Visiteur de Commande.
	 */
	public void visitGauche(CommandeGauche c);
	
	/*****
	 * @param c Commande
	 * Visiteur de Commande.
	 */
	public void visitLever(CommandeLever c);
	
	/*****
	 * @param c Commande
	 * Visiteur de Commande.
	 */
	public void visitPoser(CommandePoser c);
	
	/*****
	 * @param s Script
	 * Visiteur de Script.
	 */
	public void visitScript(Script s);
	
	/*****
	 * @param n NoeudSi
	 * Visiteur de NoeudSi.
	 */
	public void visitSi(NoeudSi n);
	
	/*****
	 * @param c NoeudRepeter
	 * Visiteur de NoeudRepeter.
	 */
	public void visitRepeter(NoeudRepeter n);
	
	/*****
	 * @param c NoeudTantQue
	 * Visiteur de NoeudTantQue.
	 */
	public void visitTantQue(NoeudTantQue n);
	
}
