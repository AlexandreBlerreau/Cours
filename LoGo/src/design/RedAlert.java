/*****
 * @author Blerreau Alexandre
 * Classe d'indications pour la validit� du script
 */

package design;

import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import projet.MyView;


public class RedAlert {

	/*****
	 * Constructeur.
	 */
	public RedAlert() {
		super();
	}

	/*****
	 * @param ta TextArea
	 * @param int nbLigne
	 * 
	 * Colorie les erreurs �ventuelles en rouge.
	 */
	public void colorError(int nbLigne, TextArea ta) {
		MyView.errorTextArea.getChildren().clear();
		int ligneIdx = 1;
		if(nbLigne == -42) {
			Rectangle rscript = new Rectangle();
			rscript.setX(0);
			rscript.setY(0);
			rscript.setWidth(200);
			rscript.setHeight(15);
			rscript.setFill(Color.INDIANRED);
			MyView.errorTextArea.getChildren().addAll(rscript, new Text("\n"));
			for(String ligne : ta.getText().split("\n")) {
				MyView.errorTextArea.getChildren().add(new Text("  " + ligne + "\n"));
			}
			Text e = (Text) MyView.errorTextArea.getChildren().get(MyView.errorTextArea.getChildren().size() -1);
			Rectangle rfin = new Rectangle();
			rfin.setX(e.getX() +15);
			rfin.setY(e.getY() + 15);
			rfin.setWidth(200);
			rfin.setHeight(15);
			rfin.setFill(Color.INDIANRED);
			MyView.errorTextArea.getChildren().addAll(rfin,new Text("\n"));
		}else {
			for(String ligne : ta.getText().split("\n")) {
				if(ligneIdx == nbLigne) {
					Text t = new Text("  " + ligne + "\n");
					t.setFill(Color.INDIANRED);
					t.setFont(Font.font(15));
					MyView.errorTextArea.getChildren().add(t);
					ligneIdx ++;
				}
				else {
					MyView.errorTextArea.getChildren().add(new Text("  " + ligne + "\n"));
					ligneIdx ++;
				}
			}
		}
		MyView.errorTextArea.setVisible(true);
	}

}