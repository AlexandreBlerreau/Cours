/*****
 * @author Blerreau Alexandre
 * Les Variables.
 */

package noeud;

import java.util.HashMap;

public class Variables {
	
	private HashMap<String,String> variables;
	
	public Variables() {
		variables = new HashMap<String,String>();
	}
	
	/*****
	 * @param Variables ajouter
	 * Ajouter des variables
	 */
	public void ajouter(String nom,String valeur) {
		variables.put(nom, valeur);
	}
	
	/*****
	 * @param Variables get
	 * Retourne la valeur de la variable passé en paramétre
	 */
	public String get(String nom) {
		if(this.existe(nom)) {
		return variables.get(nom);
		}
		return null;
	}
	
	/*****
	 * @param Variables set
	 * Change la valeur d'une variable 
	 */
	public void set(String nom,String value) {
		variables.put(nom,value);
	}
	
	/*****
	 * @param Variables reset
	 * Remet a 0 la liste des variables
	 */
	public void reset() {
		variables.clear();
	}
	
	/*****
	 * @param Variables isValideType
	 * Verifie que le contenu de la variable est un Int ou un boolean
	 */
	public boolean isValideType(String value) {
		if(value.toUpperCase().equals("TRUE") || value.toUpperCase().equals("FALSE")) {
			return true;
		}
		else {
			for(int i = 0; i < value.length(); i ++) {
				if(value.charAt(i) < '0' || value.charAt(i) > '9') {
					return false;
				}
			}
		}
		return true;
	}
	
	/*****
	 * @param Variables exist
	 * Vérifie si la variable existe
	 */
	public boolean existe(String name) {
		if(variables.containsKey(name)) {
			return true;
		}
		return false;
	}
	
	/*****
	 * @param Variables isBooleanType
	 * Vérifie si la variable est boolean 
	 */
	public boolean isBooleanType(String value) {
		return value.toUpperCase().equals("TRUE") || value.toUpperCase().equals("FALSE");
	}
}
