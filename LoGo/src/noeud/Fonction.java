/*****
 * @author Blerreau Alexandre
 */

package noeud;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

import design.Cursor;
import design.LiveExecution;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.text.TextFlow;
import javafx.util.Duration;
import projet.MyView;
import projet.MonTokenizer;

public class Fonction {

	private Script script;
	private Args arguments;
	private MonTokenizer mt;
	private Reader r;
	private String commandes;
	public LiveExecution live = new LiveExecution();
	public TextArea ta = MyView.getTa();
	public ImageView iv = MyView.iv;
	public int vitesseVal = MyView.vitesseVal;
	public Cursor curseur = MyView.curseur;
	public TextFlow liveTextArea = MyView.liveTextArea;
	
	
	
	
	public Fonction() {
		arguments = new Args();
	}
	
	/*****
	 * @param Fonction clear
	 * Remet a 0 la liste des fonctions
	 */
	public void clear() {
		script.getList().clear();
	}
	
	/*****
	 * @param Fonction getArguments
	 * Retourne la liste d'argument de la fonction (ne fonctionne pas)
	 */
	public Args getArguments() {
		return this.arguments;
	}
	
	/*
	 * @param Fonction getScript
	 * Retourne le script contenu dans la fonction
	 */
	public Script getScript(){
		return this.script;
	}
	/*****
	 * @param Fonction setScript
	 * Modifie le script de la fonction
	 */
	public void setScript(Script script) {
		this.script = script;
	}
	
	
}
