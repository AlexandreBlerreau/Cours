/*****
 * @author Lewandoski Baptiste, Guillaume Renaud
 * Enumeration des Actions Possibles
 */

package projet;

public enum ActionsScript {
	
	AVANT("AVANT"),
	DROITE("DROITE"),
	GAUCHE("GAUCHE"),
	ALLERA("ALLERA"),
	POSER("POSER"),
	LEVER("LEVER"),
	COULEUR("COULEUR"),
	EPAISSEUR("EPAISSEUR"),
	AIDE("AIDE"),
	BAS("BAS"),
	HAUT("HAUT"),
	SCRIPT("SCRIPT"),
	TANTQUE("TANTQUE"),
	REPETER("REPETER"),
	SINON("SINON"),
	ALORS("ALORS"),
	SI("SI"),
	FIN("FIN"),
	VAR("VAR"),
	SOIT("SOIT"),
	FONCTION("FONCTION"),
	FINFONCTION("FINFONCTION"),
	RUN("RUN"),
	BEZIER("BEZIER");
	
	String nom;
	
	/*****
	 * @param name String
	 * Constructeur.
	 */
	ActionsScript(String name){
		this.nom = name;
	}

	/*****
	 * @return nom String
	 */
	public String getNom() {
		return nom;
	}

}
