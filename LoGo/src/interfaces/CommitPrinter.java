package interfaces;
/*****
 * @author Blerreau Alexandre
*/

public class CommitPrinter {
	
	private String titre;
	private String auteur;
	private String date;
	
	
	/*****
	 * @param tritre String
	 * @param auteur String
	 * @param date String
	 */
	public CommitPrinter(String titre,String auteur,String date) {
		this.titre = titre;
		this.auteur = auteur;
		this.date = date;
	}
	
	/*****
	 * @param CommitPrinter getTitre
	 * Retourne le titre du commit
	 */
	
	public String getTitre() {
		return this.titre;
	}
	
	/*****
	 * @param CommitPrinter getAuteur
	 * Retourne l'auteur du commit
	 */
	public String getAuteur() {
		return this.auteur;
	}
	
	/*****
	 * @param CommitPrinter getDate
	 * Retourne la date du commit
	 */
	public String getDate() {
		return this.date;
	}
	
	public String toString() {
		return " Mise a jour : " + date +"\n" +">> "+ titre + " \n Editer par : " + auteur +"\n"; 
	}

}
