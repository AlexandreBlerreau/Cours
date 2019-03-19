package projet;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;

import javax.imageio.ImageIO;

import interfaces.MainAide;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import noeud.CommandeAllerA;
import noeud.CommandeAvant;
import noeud.CommandeCouleur;
import noeud.CommandeDroite;
import noeud.CommandeEpaisseur;
import noeud.CommandeGauche;
import noeud.CommandeLever;
import noeud.CommandePoser;
import noeud.NoeudAST;
import noeud.NoeudRepeter;

public class MonController {

	MyView v;
	
	public MonController(MyView v){
		this.v=v;
	}
	
	class onButtonClick implements EventHandler<ActionEvent>{

		public void handle(ActionEvent event) {
			/** Affichage de la palette de couleur **/
			if(event.getTarget().equals(v.couleurs)){
				v.colorStage.show();
			}
			/** Selection de la couleur blanche **/
			if(event.getTarget().equals(v.blanc) ){
				v.couleurs.setStyle("-fx-background-color: white");
				MyView.ta.setText(MyView.ta.getText() + "COULEUR BLANC\n");
				v.colorStage.close();
			}
			/** Selection de la couleur grise **/
			if(event.getTarget().equals(v.gris) ){
				v.couleurs.setStyle("-fx-background-color: grey");
				MyView.ta.setText(MyView.ta.getText() + "COULEUR GRIS\n");
				v.colorStage.close();
			}
			/** Selection de la couleur bleu **/
			if(event.getTarget().equals(v.bleu) ){
				v.couleurs.setStyle("-fx-background-color: blue");
				MyView.ta.setText(MyView.ta.getText() +"COULEUR BLEU\n");
				v.colorStage.close();
			}
			/** Selection de la couleur verte **/
			if(event.getTarget().equals(v.vert) ){
				v.couleurs.setStyle("-fx-background-color: green");
				MyView.ta.setText(MyView.ta.getText() + "COULEUR VERT\n");
				v.colorStage.close();
			}
			/** Selection de la couleur rouge **/
			if(event.getTarget().equals(v.rouge) ){
				v.couleurs.setStyle("-fx-background-color: red");
				MyView.ta.setText(MyView.ta.getText() + "COULEUR ROUGE\n");
				v.colorStage.close();
			}
			/** Selection de la couleur jaune **/
			if(event.getTarget().equals(v.jaune) ){
				v.couleurs.setStyle("-fx-background-color: yellow");
				MyView.ta.setText(MyView.ta.getText() + "COULEUR JAUNE\n");
				v.colorStage.close();
			}
			/** Selection de la couleur rose **/
			if(event.getTarget().equals(v.rose) ){
				v.couleurs.setStyle("-fx-background-color: pink");
				MyView.ta.setText(MyView.ta.getText() + "COULEUR ROSE\n");
				v.colorStage.close();
			}
			/** Selection de la couleur orange **/
			if(event.getTarget().equals(v.orange) ){
				v.couleurs.setStyle("-fx-background-color: orange");
				MyView.ta.setText(MyView.ta.getText() + "COULEUR ORANGE\n");
				v.colorStage.close();
			}
			/** Selection de la couleur violette **/
			if(event.getTarget().equals(v.violet) ){
				v.couleurs.setStyle("-fx-background-color: purple");
				MyView.ta.setText(MyView.ta.getText() + "COULEUR VIOLET\n");
				v.colorStage.close();
			}
			/** Selection de la couleur noir **/
			if(event.getTarget().equals(v.noir) ){
				v.couleurs.setStyle("-fx-background-color: black");
				MyView.ta.setText(MyView.ta.getText() + "COULEUR NOIR\n");
				v.colorStage.close();
			}
			/** Selection de la couleur marron **/
			if(event.getTarget().equals(v.marron) ){
				v.couleurs.setStyle("-fx-background-color: brown");
				MyView.ta.setText(MyView.ta.getText() + "COULEUR MARRON\n");
				v.colorStage.close();
			}
			/** Affichage du reglage de l'epaisseur**/
			if(event.getTarget().equals(v.epaisseur)){
				v.stageEp.show();
			}
			
			/** commande avancer **/
			if(event.getTarget().equals(v.avancer)) {
				MyView.ta.setText(MyView.ta.getText() + "AVANT 10\n");
			}
			
			/** Appliquer le reglage de l'epaisseur **/
			if(event.getTarget().equals(v.okEp)){
				MyView.ta.setText(MyView.ta.getText() + "EPAISSEUR " + v.textEp.getText()+"\n");
				v.stageEp.close();
			}
			/** Affichage du tuto **/
			if(event.getTarget().equals(v.aide)){
				MainAide a = new MainAide();
				try {
					a.start(MainAide.getStage());
				} catch (Exception e) {
					System.err.println(e.toString());
				}
			}
			
			/** Selection de la commande script**/
			if(event.getTarget().equals(v.scriptButon)){
				MyView.ta.setText(MyView.ta.getText() + "SCRIPT\n");
			}
			/** Selection de la commande fin **/
			if(event.getTarget().equals(v.fin)){
				MyView.ta.setText(MyView.ta.getText() + "FIN\n");
			}
			/** Selection de la commande repeter**/
			if(event.getTarget().equals(v.repeter)){
				MyView.ta.setText(MyView.ta.getText() + "REPETER 5\n");
			}
			/** Saver le projet **/
			if(event.getTarget().equals(v.sauver)){
				//TODO
				Alert a = new Alert(AlertType.INFORMATION);
				a.setHeaderText(null);
				a.setContentText("Prochainement disponible...");
				a.show();
			}
			if(event.getTarget().equals(v.screenshot)) {
				File file;
				FileChooser path = new FileChooser();
				path.setInitialFileName("sansTitre.png");
				v.save = new WritableImage((int) v.cv.getHeight(), (int) v.cv.getWidth());
				v.cv.snapshot(null, v.save);
				file = path.showSaveDialog(v.global);



				try {
					ImageIO.write(SwingFXUtils.fromFXImage(v.save, null), "png", file);
					Alert ok = new Alert(AlertType.INFORMATION);
					ok.setTitle("Exportation réussite!");
					ok.setContentText("Votre dessin a été exporté.");
					ok.setHeaderText(null);
					ok.showAndWait();

				}catch(Exception e ){

				} 
			}
			
			/** Ouvrir le projet explorer **/
			if(event.getTarget().equals(v.explorer)){
				//TODO
				Alert a = new Alert(AlertType.INFORMATION);
				a.setHeaderText(null);
				a.setContentText("Prochainement disponible...");
				a.show();
				
			}
			/** remise a zero **/
			if(event.getTarget().equals(v.raz)){
				clear();
			}
			/** Dessiner **/
			if(event.getTarget().equals(v.submit)){
				try {
					submit();
				} catch (IOException | ParseException e) {

				}
			}
			/** reglage de la vitesse des animations **/
			if(event.getTarget().equals(v.vitesse)){
				if(MyView.vitesseVal == 3){
					v.decr = true;
				}
				if(MyView.vitesseVal == 1){
					v.decr = false;
				}

				if(!v.decr){
					MyView.vitesseVal ++;

				}
				else {
					MyView.vitesseVal --;

				}

				switch(MyView.vitesseVal){

				case 1: v.vitesse.setText("Rapide");
				break;
				case 2: v.vitesse.setText("Moyen");
				break;
				case 3: v.vitesse.setText("Lent");
				break;
				}
			}
			if(event.getTarget().equals(v.reglagesB)){
				MyView.reglages.show();
			}
		}
		
		public void clear() {
			v.gc1.setFill(Color.WHITE);
			v.gc1.fillRect(0, 0, 420, 360);
			MyView.ta.clear();
			MyView.getEs().getCrayon().setColor(Couleurs.NOIR);
			MyView.getEs().getCrayon().setCoord(0,0);
			MyView.getEs().getCrayon().setWidth(2);
			MyView.getEs().getCrayon().setOrientation(0);
			MyView.iv.setX(0);
			MyView.iv.setY(0);
			MyView.iv.setRotate(0);
			MyView.iv.setImage(MyView.curseur.getCursor("NOIR"));
			v.couleurs.setStyle("-fx-background-color: black");
			v.textEp.setText(MyView.getEs().getCrayon().getWidth() + "");
			v.slideEp.setValue(MyView.getEs().getCrayon().getWidth());
			MonTokenizer.listFonction.clear();
			MonTokenizer.vars.reset();
		}
		
		public void submit() throws IOException, ParseException {
			if(MyView.getEs().getCrayon().getGc() == null){
				v.gc1 = v.cv.getGraphicsContext2D();
				MyView.getEs().getCrayon().setGc(v.gc1);
			}
			v.r = new StringReader(MyView.ta.getText());
			MyView.mt = new MonTokenizer(v.r);
			MyView.script = MyView.mt.parseScript();
			moveCursor(0);
		}
		
		public  void moveCursor(int i) {
			if(i < MyView.script.getList().size()) {
				/** recuperation de la commande a l'index i **/
				NoeudAST command = MyView.script.getList().get(i);
				if(command instanceof NoeudRepeter) {
					command.accept(MyView.getEs().getCrayon());
					moveCursor(i+2);
				}
				if(command instanceof CommandeAllerA) {
					v.live.ColorExecution(MyView.ta, i);
					CommandeAllerA c = (CommandeAllerA) command;
					/** animation **/
					Timeline tl = new Timeline(new KeyFrame(Duration.seconds(MyView.vitesseVal),new KeyValue(MyView.iv.xProperty(),c.n1), new KeyValue(MyView.iv.yProperty(), c.n2)));
					tl.setCycleCount(1);
					tl.play();
					/** execution de la commande **/
					tl.setOnFinished(e -> {
						c.accept(MyView.getEs().getCrayon());
						moveCursor(i+1);
					});
				}
				if(command instanceof CommandeAvant) {
					CommandeAvant c = (CommandeAvant) command;
					int oldx = MyView.getEs().getCrayon().getX();
					int oldy = MyView.getEs().getCrayon().getY();
					int newx = (int) (c.getNb() * Math.cos(Math.toRadians(MyView.getEs().getCrayon().getOrientation()))+ MyView.getEs().getCrayon().getX());
					int newy = (int) (c.getNb() * Math.sin(Math.toRadians(MyView.getEs().getCrayon().getOrientation()))+ MyView.getEs().getCrayon().getY());
					if(MyView.getEs().getCrayon().isEcrit()) {
						v.live.ColorExecution(MyView.ta, i);
						if(newx <= 420 && newx >= 0 && newy <= 360 && newy >= 0){
							Timeline tl = new Timeline(new KeyFrame(Duration.seconds(MyView.vitesseVal), new KeyValue(MyView.iv.xProperty(), newx), new KeyValue(MyView.iv.yProperty(), newy)));
							tl.setCycleCount(1);
							tl.play();

							tl.setOnFinished(e->{
								c.accept(MyView.getEs().getCrayon());
								moveCursor(i +1);
							});
						}
						if(newx > 420 || newx < 0 || newy > 360 || newy < 0){
							Timeline tl = new Timeline(new KeyFrame(Duration.seconds(MyView.vitesseVal), new KeyValue(MyView.iv.xProperty(), oldx), new KeyValue(MyView.iv.yProperty(), oldy), new KeyValue(MyView.iv.rotateProperty(), 0.0)));
							tl.setCycleCount(1);
							tl.play();

							tl.setOnFinished(e->{
								c.accept(MyView.getEs().getCrayon());
								moveCursor(i +1);
							});
						}
					} else {
						Timeline tl = new Timeline(new KeyFrame(Duration.seconds(MyView.vitesseVal), new KeyValue(MyView.iv.xProperty(), newx), new KeyValue(MyView.iv.yProperty(), newy)));
						tl.setCycleCount(1);
						tl.play();
						tl.setOnFinished(e->{
							c.accept(MyView.getEs().getCrayon());
							//live.ColorExecution(ta, -1);
							moveCursor(i +1);
						});
					}	
					if(newx > 420 || newx < 0 || newy > 360 || newy < 0){
						v.live.ColorExecution(MyView.ta, i);
						Timeline tl = new Timeline(new KeyFrame(Duration.seconds(MyView.vitesseVal), new KeyValue(MyView.iv.xProperty(), oldx), new KeyValue(MyView.iv.yProperty(), oldy), new KeyValue(MyView.iv.rotateProperty(), 0.0)));
						tl.setCycleCount(1);
						tl.play();

						tl.setOnFinished(e->{
							c.accept(MyView.getEs().getCrayon());
							//live.ColorExecution(ta, -1);;
							moveCursor(i +1);
						});
					}
					else {
						v.live.ColorExecution(MyView.ta, i);
						Timeline tl = new Timeline(new KeyFrame(Duration.seconds(MyView.vitesseVal), new KeyValue(MyView.iv.xProperty(), newx), new KeyValue(MyView.iv.yProperty(), newy)));
					}
				}
				if(command instanceof CommandeDroite) {
					CommandeDroite c = (CommandeDroite) command;
					v.live.ColorExecution(MyView.ta, i);
					Timeline tl = new Timeline(new KeyFrame(Duration.seconds(MyView.vitesseVal),new KeyValue(MyView.iv.rotateProperty(),MyView.getEs().getCrayon().getOrientation() + c.getNb())));
					tl.setCycleCount(1);
					tl.play();

					tl.setOnFinished(e ->{
						c.accept(MyView.getEs().getCrayon());
						moveCursor(i +1);
					});
				}
				if(command instanceof CommandeGauche) {
					CommandeGauche c = (CommandeGauche) command;
					v.live.ColorExecution(MyView.ta, i);
					Timeline tl = new Timeline(new KeyFrame(Duration.seconds(MyView.vitesseVal),new KeyValue(MyView.iv.rotateProperty(),MyView.getEs().getCrayon().getOrientation() - c.getNb())));
					tl.setCycleCount(1);
					tl.play();
					tl.setOnFinished(e ->{
						c.accept(MyView.getEs().getCrayon());
						moveCursor(i+1);
					});
				}
				if(command instanceof CommandeEpaisseur || command instanceof CommandeLever || command instanceof CommandePoser || command instanceof CommandeCouleur) {

					if(command instanceof CommandeCouleur) {
						CommandeCouleur c = (CommandeCouleur) command;
						MyView.iv.setImage(MyView.curseur.getCursor(c.getCol().toString()));
						if(c.getCol().toString() == "BLANC") {
							v.couleurs.setStyle("-fx-background-color: white");

						}
						else if (c.getCol().toString().equals("GRIS")) {
							v.couleurs.setStyle("-fx-background-color: gray");
						}
						else if (c.getCol().toString().equals("BLEU")) {
							v.couleurs.setStyle("-fx-background-color: blue");
						}
						else if (c.getCol().toString().equals("VERT")) {
							v.couleurs.setStyle("-fx-background-color: green");
						}
						else if (c.getCol().toString().equals("ROUGE")) {
							v.couleurs.setStyle("-fx-background-color: red");
						}
						else if (c.getCol().toString().equals("JAUNE")) {
							v.couleurs.setStyle("-fx-background-color: yellow");
						}
						else if (c.getCol().toString().equals("ROSE")) {
							v.couleurs.setStyle("-fx-background-color: pink");
						}
						else if (c.getCol().toString().equals("ORANGE")) {
							v.couleurs.setStyle("-fx-background-color: orange");
						}
						else if (c.getCol().toString().equals("VIOLET")) {
							v.couleurs.setStyle("-fx-background-color: purple");
						}
						else if (c.getCol().toString().equals("NOIR")) {
							v.couleurs.setStyle("-fx-background-color: black");
						}
						else if (c.getCol().toString().equals("MARRON")) {
							v.couleurs.setStyle("-fx-background-color: brown");
						}

					}
					command.accept(MyView.getEs().getCrayon());
					moveCursor(i+1);
				}
			} else {
				MyView.liveTextArea.setVisible(false);
				MonTokenizer.listFonction.clear();
				MonTokenizer.vars.reset();
			}
			//ta.clear();
		}
	}
}
