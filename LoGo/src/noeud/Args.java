/*****
 * @author Blerreau Alexandre
 */

package noeud;

import java.util.HashMap;
import java.util.Iterator;

import javax.sound.midi.Synthesizer;

public class Args {
	
	private HashMap listArguments;
	
	
	public Args() {
		listArguments = new HashMap<String,String>();
	}
	
	/*****
	 * @param Args ajouterArgument
	 * Permet d'ajouter un argument a la list
	 */
	public void ajouterArgument(String nom,String value) {
		listArguments.put(nom, value);
	}
	
	/*****
	 * @param Args clear
	 * Remet a 0 la liste d'arguments
	 */
	public void clear() {
		listArguments.clear();
	}
	/*****
	 * @param Args get
	 * Retourne la valeur de l'argument passé en paramétre
	 */
	
	public String get(String nom) {
		return (String)listArguments.get(nom);
	}
	
	/*****
	 * @param Args set
	 * Modifie la valeur de l'argument
	 */
	public void set(String nom,String value) {
		
			listArguments.put(nom, value);
	
		
	}
	
	
	public int nbArgs() {
		return this.listArguments.size();
	}
	
	public HashMap getList() {
		return this.listArguments;
	}

	
}
