/*****
 * @author Renaud Guillaume, Lewandoski Baptiste
 * Enum�ration de Comparateurs
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
