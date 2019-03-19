/*****
 * @author Blerreau Alexandre
 * Classe d'indications pour la validit� du script
 */

package design;

import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import projet.MyView;

public class LiveExecution {

	public LiveExecution() {
		super();
	}

	/*****
	 * @param ta TextArea
	 * @param int nbLigne
	 * 
	 * M�thode de compr�hension pour les utilisateurs : Colore en rouge ou vert si l'instruction est bonne ou mauvaise pendant l'�x�cution
	 */
	public void ColorExecution(TextArea ta, int nbLigne) {
		MyView.liveTextArea.getChildren().clear();
		if(nbLigne == -1) {
			for(String ligne : ta.getText().split("\n")) {
				MyView.liveTextArea.getChildren().add(new Text("  " + ligne + "\n"));
				MyView.liveTextArea.setVisible(true);
			}
		} else {
			int index = 0;
			for(String ligne : ta.getText().split("\n")) {
				if(index == nbLigne  && (!ligne.toUpperCase().equals("SCRIPT") && !ligne.toUpperCase().equals("FIN"))) {
					Text t = new Text("  " + ligne + "\n");
					t.setFill(Color.GREEN);
					t.setFont(Font.font(16));
					MyView.liveTextArea.getChildren().add(t);
					index ++;
				}
				else if(index != nbLigne && (!ligne.toUpperCase().equals("SCRIPT") && !ligne.toUpperCase().equals("FIN"))) {
					MyView.liveTextArea.getChildren().add(new Text("  " + ligne + "\n"));
					index ++;
				}
			}
			MyView.liveTextArea.setVisible(true);
		}
	}

}
