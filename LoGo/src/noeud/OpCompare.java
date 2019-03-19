/*****
 * @author Renaud Guillaume, Lewandoski Baptiste
 * Enumération de Comparateurs
 */

package noeud;

public enum OpCompare {

	EGAL("=="),
	INFERIEUR("<"),
	INFERIEUROUEGAL("<="),
	SUPERIEUR(">"),
	SUPERIEUROUEGAL(">=");
	
	OpCompare (String name){}
	
}
