package projet;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.text.ParseException;

import javax.imageio.ImageIO;

import evaluateurs.EvaluateurScript;
import interfaces.MainAide;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import noeud.NoeudAST;
import noeud.NoeudRepeter;
import noeud.Script;
import noeud.Variables;
import noeud.CommandeAllerA;
import noeud.CommandeAvant;
import noeud.CommandeCouleur;
import noeud.CommandeDroite;
import noeud.CommandeEpaisseur;
import noeud.CommandeGauche;
import noeud.CommandeLever;
import noeud.CommandePoser;
import noeud.CourbeBezier;
import design.Cursor;
import design.RedAlert;
import design.LiveExecution;


public class MyView extends Application {
	
	/** Variables necessaire au fonctionnement **/
	Reader r;
	static EvaluateurScript es = new EvaluateurScript(Crayon.getInstance());

	public static MonTokenizer mt;
	static Script script;

	/** haut de page **/
	

	/**  Menu controles **/
	Button raz = new Button();
	
	Button explorer = new Button();
	Button sauver = new Button();
	Button screenshot = new Button();
	
	Button couleurs = new Button();
	Button epaisseur = new Button();
	Button avancer = new Button();
	
	Button scriptButon = new Button();
	Button fin= new Button();
	Button repeter = new Button();
	
	Button reglagesB = new Button();
	Button aide = new Button();


	/** Zone de dessin & Zone d'écriture **/

	/** pane et image et imageView necessaire au fonctionnement du curseur **/
	public static Cursor curseur = new Cursor();
	public static Pane animation = new Pane();
	public static ImageView iv = new ImageView(curseur.getCursor(es.getCrayon().getColor().toString()));

	/** pane pour l'affichage du dessin **/
	Canvas cv = new Canvas(420,360);
	GraphicsContext gc1;

	/** stackPane supperposant le curseur a la zone de dessin **/
	StackPane lesCanvas = new StackPane();

	/** zone de saisie du text + button **/
	StackPane textAreaAndAnimation = new StackPane();
	static TextArea ta = new TextArea();
	public static TextFlow errorTextArea = new TextFlow();
	public static TextFlow liveTextArea = new TextFlow();
	public LiveExecution live = new LiveExecution();
	Button submit = new Button("  Dessiner!  ");


	/** Composant et variable necessaire a l'affichage de la palette de couleurs**/
	Stage colorStage = new Stage();
	Scene colorScene;
	Button blanc = new Button();
	Button gris = new Button();
	Button bleu = new Button();
	Button vert = new Button();
	Button rouge = new Button();
	Button jaune = new Button();
	Button rose = new Button();
	Button orange = new Button();
	Button violet = new Button();
	Button noir = new Button();
	Button marron = new Button();

	/** Composant et variable necessaire a l'affichage du réglage de l'epaisseur**/
	Slider slideEp = new Slider();
	TextField textEp = new TextField(es.getCrayon().getWidth() + "");
	Stage stageEp = new Stage();
	Scene sceneEp;
	Button okEp = new Button(" Appliquer ");


	/** Composant et variable necessaire a l'affichage du tuto**/
	static Stage tuto = new Stage();
	Scene sceneTuto;

	/** Composant et variable necessaire a l'affiche des réglages*/
	static Stage reglages = new Stage();
	Scene sceneReglages;
	VBox VBreglages = new VBox();
	Button vitesse = new Button("Moyen");
	public static int vitesseVal = 2;
	boolean decr = false;


	/** Composant et variable necessaire a l'exportation du dessin**/
	Stage global;
	WritableImage save; 

	/** Evenement button **/
	
	class onButtonClick implements EventHandler<ActionEvent>{

		public void handle(ActionEvent event) {
			/** Affichage de la palette de couleur **/
			if(event.getTarget().equals(couleurs)){
				colorStage.show();
			}
			/** Selection de la couleur blanche **/
			if(event.getTarget().equals(blanc) ){
				couleurs.setStyle("-fx-background-color: white");
				ta.setText(ta.getText() + "COULEUR BLANC\n");
				colorStage.close();
			}
			/** Selection de la couleur grise **/
			if(event.getTarget().equals(gris) ){
				couleurs.setStyle("-fx-background-color: grey");
				ta.setText(ta.getText() + "COULEUR GRIS\n");
				colorStage.close();
			}
			/** Selection de la couleur bleu **/
			if(event.getTarget().equals(bleu) ){
				couleurs.setStyle("-fx-background-color: blue");
				ta.setText(ta.getText() +"COULEUR BLEU\n");
				colorStage.close();
			}
			/** Selection de la couleur verte **/
			if(event.getTarget().equals(vert) ){
				couleurs.setStyle("-fx-background-color: green");
				ta.setText(ta.getText() + "COULEUR VERT\n");
				colorStage.close();
			}
			/** Selection de la couleur rouge **/
			if(event.getTarget().equals(rouge) ){
				couleurs.setStyle("-fx-background-color: red");
				ta.setText(ta.getText() + "COULEUR ROUGE\n");
				colorStage.close();
			}
			/** Selection de la couleur jaune **/
			if(event.getTarget().equals(jaune) ){
				couleurs.setStyle("-fx-background-color: yellow");
				ta.setText(ta.getText() + "COULEUR JAUNE\n");
				colorStage.close();
			}
			/** Selection de la couleur rose **/
			if(event.getTarget().equals(rose) ){
				couleurs.setStyle("-fx-background-color: pink");
				ta.setText(ta.getText() + "COULEUR ROSE\n");
				colorStage.close();
			}
			/** Selection de la couleur orange **/
			if(event.getTarget().equals(orange) ){
				couleurs.setStyle("-fx-background-color: orange");
				ta.setText(ta.getText() + "COULEUR ORANGE\n");
				colorStage.close();
			}
			/** Selection de la couleur violette **/
			if(event.getTarget().equals(violet) ){
				couleurs.setStyle("-fx-background-color: purple");
				ta.setText(ta.getText() + "COULEUR VIOLET\n");
				colorStage.close();
			}
			/** Selection de la couleur noir **/
			if(event.getTarget().equals(noir) ){
				couleurs.setStyle("-fx-background-color: black");
				ta.setText(ta.getText() + "COULEUR NOIR\n");
				colorStage.close();
			}
			/** Selection de la couleur marron **/
			if(event.getTarget().equals(marron) ){
				couleurs.setStyle("-fx-background-color: brown");
				ta.setText(ta.getText() + "COULEUR MARRON\n");
				colorStage.close();
			}
			/** Affichage du reglage de l'epaisseur**/
			if(event.getTarget().equals(epaisseur)){
				stageEp.show();
			}
			
			/** commande avancer **/
			if(event.getTarget().equals(avancer)) {
				ta.setText(ta.getText() + "AVANT 10\n");
			}
			
			/** Appliquer le reglage de l'epaisseur **/
			if(event.getTarget().equals(okEp)){
				ta.setText(ta.getText() + "EPAISSEUR " + textEp.getText()+"\n");
				stageEp.close();
			}
			/** Affichage du tuto **/
			if(event.getTarget().equals(aide)){
				MainAide a = new MainAide();
				try {
					a.start(a.getStage());
				} catch (Exception e) {
					System.err.println(e.toString());
				}
			}
			
			/** Selection de la commande script**/
			if(event.getTarget().equals(scriptButon)){
				ta.setText(ta.getText() + "SCRIPT\n");
			}
			/** Selection de la commande fin **/
			if(event.getTarget().equals(fin)){
				ta.setText(ta.getText() + "FIN\n");
			}
			/** Selection de la commande repeter**/
			if(event.getTarget().equals(repeter)){
				ta.setText(ta.getText() + "REPETER 5\n");
			}
			/** Saver le projet **/
			if(event.getTarget().equals(sauver)){
				//TODO
				Alert a = new Alert(AlertType.INFORMATION);
				a.setHeaderText(null);
				a.setContentText("Prochainement disponible...");
				a.show();
			}
			if(event.getTarget().equals(screenshot)) {
				File file;
				FileChooser path = new FileChooser();
				path.setInitialFileName("sansTitre.png");
				save = new WritableImage((int) cv.getHeight(), (int) cv.getWidth());
				cv.snapshot(null, save);
				file = path.showSaveDialog(global);



				try {
					ImageIO.write(SwingFXUtils.fromFXImage(save, null), "png", file);
					Alert ok = new Alert(AlertType.INFORMATION);
					ok.setTitle("Exportation réussite!");
					ok.setContentText("Votre dessin a été exporté.");
					ok.setHeaderText(null);
					ok.showAndWait();

				}catch(Exception e ){

				} 
			}
			
			/** Ouvrir le projet explorer **/
			if(event.getTarget().equals(explorer)){
				//TODO
				Alert a = new Alert(AlertType.INFORMATION);
				a.setHeaderText(null);
				a.setContentText("Prochainement disponible...");
				a.show();
				
			}
			/** remise a zero **/
			if(event.getTarget().equals(raz)){
				clear();
			}
			/** Dessiner **/
			if(event.getTarget().equals(submit)){
				try {
					submit();
				} catch (IOException | ParseException e) {

				}
			}
			/** reglage de la vitesse des animations **/
			if(event.getTarget().equals(vitesse)){
				if(vitesseVal == 3){
					decr = true;
				}
				if(vitesseVal == 1){
					decr = false;
				}

				if(!decr){
					vitesseVal ++;

				}
				else {
					vitesseVal --;

				}

				switch(vitesseVal){

				case 1: vitesse.setText("Rapide");
				break;
				case 2: vitesse.setText("Moyen");
				break;
				case 3: vitesse.setText("Lent");
				break;
				}
			}
			if(event.getTarget().equals(reglagesB)){
				reglages.show();
			}
		}

	}


	@Override
	public void start(Stage stage) throws Exception {

		/** recuperation du stage **/
		global = stage;

		/** disposition et style général de l'interface**/
		stage.setTitle("- LoGo -");
		stage.setResizable(false);
		Scene mainScene;
		VBox root = new VBox();
		root.setStyle("-fx-background-color: #D3D3D3");
		root.setBorder(new Border(new BorderStroke(Color.web("334454"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY,new BorderWidths(10,10,10,10))));

		/** disposition et style haut de page **/
		HBox avantPage = new HBox();
		avantPage.setPadding(new Insets(13,13,13,13));
		avantPage.setSpacing(610);
		
		//TODO

		/** dispositon et style du menu controles **/
		HBox menu = new HBox();
		menu.setStyle("-fx-background-color: #334454");
		menu.setPadding(new Insets(10,10,10,10));
		menu.setSpacing(5);

		/** dispositon et style du button remise a zero **/
		raz.setBackground(new Background(new BackgroundImage(new Image("file:ressources/clear.png",44,44,true,true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT)));
		raz.setMinSize(44, 44);
		Tooltip razTip = new Tooltip("Remise à zéro");
		razTip.activatedProperty();
		razTip.autoFixProperty();
		raz.setTooltip(razTip);
		raz.addEventHandler(ActionEvent.ACTION, new onButtonClick());

		/** dispositon et style du button explorer **/
		explorer.setBackground(new Background(new BackgroundImage(new Image("file:ressources/explorer.png",44,44,true,true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT)));
		explorer.setMinSize(44, 44);
		Tooltip explorerTip = new Tooltip("Projet explorer");
		explorerTip.activatedProperty();
		explorerTip.autoFixProperty();
		explorer.setTooltip(explorerTip);
		explorer.addEventHandler(ActionEvent.ACTION, new onButtonClick());

		/** dispositon et style du button sauver **/
		sauver.setBackground(new Background(new BackgroundImage(new Image("file:ressources/sauver.png",44,44,true,true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT)));
		sauver.setMinSize(44, 44);
		Tooltip sauverTip = new Tooltip("Sauvegarder le projet");
		sauverTip.activatedProperty();
		sauverTip.autoFixProperty();
		sauver.setTooltip(sauverTip);
		sauver.addEventHandler(ActionEvent.ACTION, new onButtonClick());
		
		/** disposition et style du button screenshot **/
		
		screenshot.setBackground(new Background(new BackgroundImage(new Image("file:ressources/screenshot.png",44,44,true,true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT)));
		screenshot.setMinSize(44, 44);
		Tooltip shotTip = new Tooltip("Capturer le dessin");
		shotTip.activatedProperty();
		shotTip.autoFixProperty();
		screenshot.setTooltip(shotTip);
		screenshot.addEventHandler(ActionEvent.ACTION, new onButtonClick());
		
		/** dispositon et style du button couleur **/
		couleurs.setStyle("-fx-background-color: black");
		couleurs.setMinSize(44, 44);
		Tooltip coulTip = new Tooltip("Couleurs...");
		coulTip.activatedProperty();
		coulTip.autoFixProperty();
		couleurs.setTooltip(coulTip);
		couleurs.addEventHandler(ActionEvent.ACTION, new onButtonClick());

		/** dispositon et style du button epaisseur **/
		epaisseur.setBackground(new Background(new BackgroundImage(new Image("file:ressources/epaisseur.png",44,44,true,true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT)));
		epaisseur.setMinSize(44, 44);
		Tooltip epTip = new Tooltip("Epaisseur du crayon");
		epTip.activatedProperty();
		epTip.autoFixProperty();
		epaisseur.setTooltip(epTip);
		epaisseur.addEventHandler(ActionEvent.ACTION, new onButtonClick());
		
		/** dispositon et style du button avancer**/
		avancer.setBackground(new Background(new BackgroundImage(new Image("file:ressources/avancer.png",44,44,true,true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT)));
		avancer.setMinSize(44, 44);
		Tooltip avancerTip = new Tooltip("AVANT 10");
		avancerTip.activatedProperty();
		avancerTip.autoFixProperty();
		avancer.setTooltip(avancerTip);
		avancer.addEventHandler(ActionEvent.ACTION, new onButtonClick());

		/** dispositon et style du button gauche **/
		scriptButon.setBackground(new Background(new BackgroundImage(new Image("file:ressources/script.png",44,44,true,true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT)));
		scriptButon.setMinSize(44, 44);
		Tooltip scriptTip = new Tooltip("SCRIPT");
		scriptTip.activatedProperty();
		scriptTip.autoFixProperty();
		scriptButon.setTooltip(scriptTip);
		scriptButon.addEventHandler(ActionEvent.ACTION, new onButtonClick());

		/** dispositon et style du button droite **/
		fin.setBackground(new Background(new BackgroundImage(new Image("file:ressources/fin.png",44,44,true,true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT)));
		fin.setMinSize(44, 44);
		Tooltip finTip = new Tooltip("FIN");
		finTip.activatedProperty();
		finTip.autoFixProperty();
		fin.setTooltip(finTip);
		fin.addEventHandler(ActionEvent.ACTION, new onButtonClick());

		/** dispositon et style du button avant **/
		repeter.setBackground(new Background(new BackgroundImage(new Image("file:ressources/repeter.png",44,44,true,true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT)));
		repeter.setMinSize(44, 44);
		Tooltip repeterTip = new Tooltip("REPETER 5");
		repeterTip.activatedProperty();
		repeterTip.autoFixProperty();
		repeter.setTooltip(repeterTip);
		repeter.addEventHandler(ActionEvent.ACTION, new onButtonClick());

		/** dispositon et style du button reglages **/
		reglagesB.setBackground(new Background(new BackgroundImage(new Image("file:ressources/reglages.png",44,44,true,true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT)));
		reglagesB.setMinSize(44, 44);
		Tooltip reglagesTip = new Tooltip("Régalges...");
		reglagesTip.activatedProperty();
		reglagesTip.autoFixProperty();
		reglagesB.setTooltip(reglagesTip);
		reglagesB.addEventHandler(ActionEvent.ACTION, new onButtonClick());

		/** dispositon et style du button aide **/
		aide.setBackground(new Background(new BackgroundImage(new Image("file:ressources/aide.png",44,44,true,true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT)));
		aide.setMinSize(44, 44);
		Tooltip aideTip = new Tooltip("Besoin d'aide?");
		aideTip.activatedProperty();
		aideTip.autoFixProperty();
		aide.setTooltip(aideTip);
		aide.addEventHandler(ActionEvent.ACTION, new onButtonClick());

		/** quelques espaces... **/
		Region espRaz = new Region();
		espRaz.setPadding(new Insets(20,20,20,20));

		Region espPoser = new Region();
		espPoser.setPadding(new Insets(20,20,20,20));

		Region espEpaisseur = new Region();
		espEpaisseur.setPadding(new Insets(20,20,20,20));

		Region espavant = new Region();
		espavant.setPadding(new Insets(0,0,0,140));

		/** ajout des boutons composant le menu de controle **/
		menu.getChildren().addAll(raz,espRaz,explorer,sauver,screenshot,espPoser,scriptButon,fin,repeter,espEpaisseur,couleurs,epaisseur,avancer,espavant,aide,reglagesB);

		// canvas & textarea

		HBox edition = new HBox();
		VBox text = new VBox();
		edition.setSpacing(10);
		edition.setPadding(new Insets(10,10,10,10));
		gc1 = cv.getGraphicsContext2D();
		gc1.setFill(Color.WHITE);
		gc1.fillRect(0, 0, 420, 360);


		ta.setMinSize(300, 300);
		errorTextArea.setMinSize(300, 300);
		errorTextArea.setStyle("-fx-background-color: white");
		errorTextArea.setVisible(false);
		errorTextArea.setLineSpacing(2);

		errorTextArea.setOnMouseClicked(e->{
			errorTextArea.setVisible(false);
		});


		liveTextArea.setMinSize(300, 300);
		liveTextArea.setStyle("-fx-background-color: white");
		liveTextArea.setVisible(false);
		liveTextArea.setLineSpacing(2);


		submit.setStyle("-fx-background-color: #334454; -fx-text-fill: white");
		submit.addEventHandler(ActionEvent.ACTION, new onButtonClick());
		text.setSpacing(15);
		Region espSubmit = new Region();
		espSubmit.setPadding(new Insets(0,175,0,0));
		animation.getChildren().add(iv);
		animation.setMinSize(cv.getWidth(), cv.getHeight());
		animation.setMaxSize(cv.getWidth(), cv.getHeight());
		HBox boutonesp = new HBox();

		// palette couleurs

		VBox mainCouleurs = new VBox();
		mainCouleurs.setStyle("-fx-background-color: #334454");
		HBox l1 = new HBox();
		l1.setPadding(new Insets(3,3,3,3));
		l1.setSpacing(3);
		HBox l2 = new HBox();
		l2.setPadding(new Insets(3,3,3,3));
		l2.setSpacing(3);
		HBox l3 = new HBox();
		l3.setPadding(new Insets(3,3,3,3));
		l3.setSpacing(3);

		blanc.setStyle("-fx-background-color: white");
		blanc.setMinSize(40, 40);
		blanc.addEventHandler(ActionEvent.ACTION, new onButtonClick());

		gris.setStyle("-fx-background-color: gray");
		gris.setMinSize(40, 40);
		gris.addEventHandler(ActionEvent.ACTION, new onButtonClick());

		bleu.setStyle("-fx-background-color: blue");
		bleu.setMinSize(40, 40);
		bleu.addEventHandler(ActionEvent.ACTION, new onButtonClick());

		vert.setStyle("-fx-background-color: green");
		vert.setMinSize(40, 40);
		vert.addEventHandler(ActionEvent.ACTION, new onButtonClick());

		rouge.setStyle("-fx-background-color: red");
		rouge.setMinSize(40, 40);
		rouge.addEventHandler(ActionEvent.ACTION, new onButtonClick());

		jaune.setStyle("-fx-background-color: yellow");
		jaune.setMinSize(40, 40);
		jaune.addEventHandler(ActionEvent.ACTION, new onButtonClick());

		rose.setStyle("-fx-background-color: pink");
		rose.setMinSize(40, 40);
		rose.addEventHandler(ActionEvent.ACTION, new onButtonClick());

		orange.setStyle("-fx-background-color: orange");
		orange.setMinSize(40, 40);
		orange.addEventHandler(ActionEvent.ACTION, new onButtonClick());

		violet.setStyle("-fx-background-color: purple");
		violet.setMinSize(40, 40);
		violet.addEventHandler(ActionEvent.ACTION, new onButtonClick());

		noir.setStyle("-fx-background-color: black");
		noir.setMinSize(40, 40);
		noir.addEventHandler(ActionEvent.ACTION, new onButtonClick());

		marron.setStyle("-fx-background-color: brown");
		marron.setMinSize(40, 40);
		marron.addEventHandler(ActionEvent.ACTION, new onButtonClick());

		l1.getChildren().addAll(blanc,gris,bleu,vert);
		l2.getChildren().addAll(rouge,jaune,rose,orange);
		l3.getChildren().addAll(violet,noir,marron);

		mainCouleurs.getChildren().addAll(l1,l2,l3);
		colorScene = new Scene(mainCouleurs);
		colorStage.setScene(colorScene);
		colorStage.setResizable(false);
		colorStage.setTitle("Couleurs");


		// epaisseur 

		VBox mainEp = new VBox();
		mainEp.setStyle("-fx-background-color: lightgray");
		mainEp.setPadding(new Insets(10,10,10,10));
		mainEp.setSpacing(10);
		slideEp.setValue(es.getCrayon().getWidth());
		slideEp.setOrientation(Orientation.HORIZONTAL);
		slideEp.setMin(1);
		slideEp.setMax(70);
		slideEp.setShowTickMarks(true);
		slideEp.setShowTickLabels(true);

		slideEp.setMajorTickUnit(10);

		mainEp.getChildren().addAll(slideEp,textEp,okEp);
		sceneEp = new Scene(mainEp);
		stageEp.setScene(sceneEp);
		stageEp.setTitle("Epaisseur");
		stageEp.setResizable(false);
		textEp.setStyle("-fx-alignment: center");
		okEp.setStyle("-fx-background-color: #334454; -fx-text-fill: white;");
		okEp.setPadding(new Insets(5,50,5,50));
		okEp.setAlignment(Pos.CENTER);
		okEp.addEventHandler(ActionEvent.ACTION, new onButtonClick());
		textEp.setOnKeyReleased(e -> {
			Tooltip erreurS = new Tooltip("La saisie dois contenir uniquement des entiers");
			Tooltip erreurV = new Tooltip("Le chiffre saisie ne respecte pas l'intervale");
			erreurS.setStyle("-fx-background-color : red");
			erreurV.setStyle("-fx-background-color: red");

			if(estEntier(textEp.getText())){
				if(Integer.valueOf(textEp.getText()) < 1 || Integer.valueOf(textEp.getText()) > 70){
					erreurV.show(stageEp);
					erreurV.setAutoHide(true);
					erreurV.setHideOnEscape(true);
					okEp.setDisable(true);
				}
				else {
					slideEp.setValue(Integer.valueOf(textEp.getText()));
					okEp.setDisable(false);
				}
			}
			else {
				erreurS.show(stageEp);
				erreurS.setAutoHide(true);
				erreurS.setHideOnEscape(true);
				okEp.setDisable(true);

			}

		});
		slideEp.setOnMouseReleased(e -> {
			textEp.setText((int)slideEp.getValue() + "");
		});

		// tuto

		TabPane main = new TabPane();
		Image bg = new Image("file:ressources/bgtuto.jpg");
		Image bg2 = new Image("file:ressources/bgtuto2.jpg");
		Image bg3 = new Image("file:ressources/bgtuto3.jpg");

		Tab deplacement = new Tab("Déplacements");
		deplacement.setClosable(false);
		VBox boxDeplacement = new VBox();

		boxDeplacement.setBackground(new Background(
				new BackgroundImage(bg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, null, null)));
		Label presentationDeplacement = new Label("\nSe déplacer pour dessiner!");
		presentationDeplacement.setFont(Font.font(STYLESHEET_MODENA, 20));
		presentationDeplacement.setPadding(new Insets(10,10,10,125));

		Label listcmdDep = new Label("• " + ActionsScript.AVANT + " [valeur pixels]\n\n"
				+ "• " + ActionsScript.DROITE + " [valeur pixels]\n\n"
				+ "• " + ActionsScript.GAUCHE + " [valeur pixels]\n\n"
				+ "• " + ActionsScript.ALLERA + " [valeur x] [valeur y]");
		listcmdDep.setPadding(new Insets(20,10,10,45));
		listcmdDep.setTextFill(Color.BLACK);
		listcmdDep.setFont(Font.font(STYLESHEET_CASPIAN,FontWeight.BOLD, 15));
		boxDeplacement.getChildren().addAll(presentationDeplacement,listcmdDep);
		deplacement.setContent(boxDeplacement);

		Tab crayon = new Tab("Crayon");
		crayon.setClosable(false);

		VBox boxCrayon = new VBox();

		boxCrayon.setBackground(new Background(
				new BackgroundImage(bg2, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, null, null)));
		Label presentationCrayon = new Label("\nParametrer son crayon!");
		presentationCrayon.setFont(Font.font(STYLESHEET_MODENA, 20));
		presentationCrayon.setPadding(new Insets(10,10,10,130));

		Label listcmdCr = new Label("• " + ActionsScript.EPAISSEUR + " [valeur pixels]\n\n"
				+ "• " + ActionsScript.LEVER + "\n\n"
				+ "• " + ActionsScript.POSER + "\n\n");

		listcmdCr.setPadding(new Insets(20,10,10,45));
		listcmdCr.setTextFill(Color.BLACK);
		listcmdCr.setFont(Font.font(STYLESHEET_CASPIAN,FontWeight.BOLD, 15));
		boxCrayon.getChildren().addAll(presentationCrayon,listcmdCr);
		crayon.setContent(boxCrayon);

		Tab couleurs = new Tab("Couleurs");
		couleurs.setClosable(false);

		VBox boxCouleurs = new VBox();

		boxCouleurs.setBackground(new Background(
				new BackgroundImage(bg3, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, null, null)));
		Label presentationCouleurs = new Label("\nEnvoyez les couleurs!");
		presentationCouleurs.setFont(Font.font(STYLESHEET_MODENA, 20));
		presentationCouleurs.setPadding(new Insets(10,10,10,150));

		Label listcmdCoul = new Label("• " + Couleurs.BLANC + "\n"
				+ "• " + Couleurs.BLEU + "\n"
				+ "• " + Couleurs.GRIS + "\n"
				+  "• " + Couleurs.JAUNE + "\n"
				+ "• " + Couleurs.MARRON + "\n"
				+  "• " + Couleurs.NOIR + "\n"
				+ "• " + Couleurs.ORANGE + "\n"
				+  "• " + Couleurs.ROSE + "\n"
				+  "• " + Couleurs.ROUGE + "\n"
				+  "• " + Couleurs.VERT + "\n"
				+  "• " + Couleurs.VIOLET + "\n");

		listcmdCoul.setPadding(new Insets(20,10,10,45));
		listcmdCoul.setTextFill(Color.BLACK);
		listcmdCoul.setFont(Font.font(STYLESHEET_CASPIAN,FontWeight.BOLD, 15));
		boxCouleurs.getChildren().addAll(presentationCouleurs,listcmdCoul);
		couleurs.setContent(boxCouleurs);



		main.getTabs().addAll(deplacement,crayon,couleurs);


		sceneTuto = new Scene(main,500,500);

		tuto.setScene(sceneTuto);
		tuto.setResizable(false);

		// scene reglages
		Label labelVit = new Label("Vitesse des animations");
		VBreglages.getChildren().addAll(labelVit,vitesse);
		VBreglages.setSpacing(10);
		VBreglages.setPadding(new Insets(10,10,10,10));
		VBreglages.setStyle("-fx-background-color: lightgray");
		vitesse.setStyle("-fx-background-color: #334454; -fx-text-fill: white;");
		vitesse.addEventHandler(ActionEvent.ACTION, new onButtonClick());
		vitesse.setMinSize(145, 25);
		vitesse.setMaxSize(145,25);
		sceneReglages = new Scene(VBreglages);
		reglages.setTitle("Reglages");
		reglages.setResizable(false);
		reglages.setScene(sceneReglages);




		boutonesp.getChildren().addAll(espSubmit,submit);
		textAreaAndAnimation.getChildren().addAll(ta,errorTextArea,liveTextArea);
		text.getChildren().addAll(textAreaAndAnimation,boutonesp);
		lesCanvas.getChildren().addAll(cv,animation);
		animation.setMinSize(420, 360);

		edition.getChildren().addAll(lesCanvas,text);
		iv.setLayoutX(0);
		iv.setLayoutY(0);




		root.getChildren().addAll(avantPage,menu,edition);
		mainScene = new Scene(root,900,500);
		stage.setScene(mainScene);
		stage.show();

	}

	public static void main(String [] args){
		
	Application.launch(args);

	}

	public boolean estEntier(String valeur){
		for(int i = 0; i < valeur.length(); i ++){
			if(valeur.charAt(i) < '0' || valeur.charAt(i) > '9'){
				return false;
			}
		}
		return true;
	}

	public void clear() {
		gc1.setFill(Color.WHITE);
		gc1.fillRect(0, 0, 420, 360);
		ta.clear();
		es.getCrayon().setColor(Couleurs.NOIR);
		es.getCrayon().setCoord(0,0);
		es.getCrayon().setWidth(2);
		es.getCrayon().setOrientation(0);
		iv.setX(0);
		iv.setY(0);
		iv.setRotate(0);
		iv.setImage(curseur.getCursor("NOIR"));
		couleurs.setStyle("-fx-background-color: black");
		textEp.setText(es.getCrayon().getWidth() + "");
		slideEp.setValue(es.getCrayon().getWidth());
		MonTokenizer.listFonction.clear();
		MonTokenizer.vars.reset();
	}

	public static EvaluateurScript getEs() {
		return es;
	}

	public void submit() throws IOException, ParseException {
		if(es.getCrayon().getGc() == null){
			gc1 = cv.getGraphicsContext2D();
			es.getCrayon().setGc(gc1);
		}
		r = new StringReader(ta.getText());
		mt = new MonTokenizer(r);
		script = mt.parseScript();
		moveCursor(0);
	}

	public  void moveCursor(int i) {
		if(i < script.getList().size()) {
			/** recuperation de la commande a l'index i **/
			NoeudAST command = script.getList().get(i);
			if(command instanceof NoeudRepeter) {
				command.accept(es.getCrayon());
				moveCursor(i+2);
			}
			if(command instanceof CommandeAllerA) {
				live.ColorExecution(ta, i);
				CommandeAllerA c = (CommandeAllerA) command;
				/** animation **/
				Timeline tl = new Timeline(new KeyFrame(Duration.seconds(vitesseVal),new KeyValue(iv.xProperty(),c.n1), new KeyValue(iv.yProperty(), c.n2)));
				tl.setCycleCount(1);
				tl.play();
				/** execution de la commande **/
				tl.setOnFinished(e -> {
					c.accept(es.getCrayon());
					moveCursor(i+1);
				});
			}
			if(command instanceof CourbeBezier) {
				command.accept(es.getCrayon());
				moveCursor(i+1);
			}
			if(command instanceof CommandeAvant) {
				CommandeAvant c = (CommandeAvant) command;
				int oldx = es.getCrayon().getX();
				int oldy = es.getCrayon().getY();
				int newx = (int) (c.getNb() * Math.cos(Math.toRadians(es.getCrayon().getOrientation()))+ es.getCrayon().getX());
				int newy = (int) (c.getNb() * Math.sin(Math.toRadians(es.getCrayon().getOrientation()))+es.getCrayon().getY());
				if(es.getCrayon().isEcrit()) {
					live.ColorExecution(ta, i);
					if(newx <= 420 && newx >= 0 && newy <= 360 && newy >= 0){
						Timeline tl = new Timeline(new KeyFrame(Duration.seconds(vitesseVal), new KeyValue(iv.xProperty(), newx), new KeyValue(iv.yProperty(), newy)));
						tl.setCycleCount(1);
						tl.play();

						tl.setOnFinished(e->{
							c.accept(es.getCrayon());
							moveCursor(i +1);
						});
					}
					if(newx > 420 || newx < 0 || newy > 360 || newy < 0){
						Timeline tl = new Timeline(new KeyFrame(Duration.seconds(vitesseVal), new KeyValue(iv.xProperty(), oldx), new KeyValue(iv.yProperty(), oldy), new KeyValue(iv.rotateProperty(), 0.0)));
						tl.setCycleCount(1);
						tl.play();

						tl.setOnFinished(e->{
							c.accept(es.getCrayon());
							moveCursor(i +1);
						});
					}
				} else {
					Timeline tl = new Timeline(new KeyFrame(Duration.seconds(vitesseVal), new KeyValue(iv.xProperty(), newx), new KeyValue(iv.yProperty(), newy)));
					tl.setCycleCount(1);
					tl.play();
					tl.setOnFinished(e->{
						c.accept(es.getCrayon());
						//live.ColorExecution(ta, -1);
						moveCursor(i +1);
					});
				}	
				if(newx > 420 || newx < 0 || newy > 360 || newy < 0){
					live.ColorExecution(ta, i);
					Timeline tl = new Timeline(new KeyFrame(Duration.seconds(vitesseVal), new KeyValue(iv.xProperty(), oldx), new KeyValue(iv.yProperty(), oldy), new KeyValue(iv.rotateProperty(), 0.0)));
					tl.setCycleCount(1);
					tl.play();

					tl.setOnFinished(e->{
						c.accept(es.getCrayon());
						//live.ColorExecution(ta, -1);;
						moveCursor(i +1);
					});
				}
				else {
					live.ColorExecution(ta, i);
					Timeline tl = new Timeline(new KeyFrame(Duration.seconds(vitesseVal), new KeyValue(iv.xProperty(), newx), new KeyValue(iv.yProperty(), newy)));
				}
			}
			if(command instanceof CommandeDroite) {
				CommandeDroite c = (CommandeDroite) command;
				live.ColorExecution(ta, i);
				Timeline tl = new Timeline(new KeyFrame(Duration.seconds(vitesseVal),new KeyValue(MyView.iv.rotateProperty(),es.getCrayon().getOrientation() + c.getNb())));
				tl.setCycleCount(1);
				tl.play();

				tl.setOnFinished(e ->{
					c.accept(es.getCrayon());
					moveCursor(i +1);
				});
			}
			if(command instanceof CommandeGauche) {
				CommandeGauche c = (CommandeGauche) command;
				live.ColorExecution(ta, i);
				Timeline tl = new Timeline(new KeyFrame(Duration.seconds(vitesseVal),new KeyValue(MyView.iv.rotateProperty(),es.getCrayon().getOrientation() - c.getNb())));
				tl.setCycleCount(1);
				tl.play();
				tl.setOnFinished(e ->{
					c.accept(es.getCrayon());
					moveCursor(i+1);
				});
			}
			if(command instanceof CommandeEpaisseur || command instanceof CommandeLever || command instanceof CommandePoser || command instanceof CommandeCouleur) {

				if(command instanceof CommandeCouleur) {
					CommandeCouleur c = (CommandeCouleur) command;
					this.iv.setImage(curseur.getCursor(c.getCol().toString()));
					if(c.getCol().toString() == "BLANC") {
						couleurs.setStyle("-fx-background-color: white");

					}
					else if (c.getCol().toString().equals("GRIS")) {
						couleurs.setStyle("-fx-background-color: gray");
					}
					else if (c.getCol().toString().equals("BLEU")) {
						couleurs.setStyle("-fx-background-color: blue");
					}
					else if (c.getCol().toString().equals("VERT")) {
						couleurs.setStyle("-fx-background-color: green");
					}
					else if (c.getCol().toString().equals("ROUGE")) {
						couleurs.setStyle("-fx-background-color: red");
					}
					else if (c.getCol().toString().equals("JAUNE")) {
						couleurs.setStyle("-fx-background-color: yellow");
					}
					else if (c.getCol().toString().equals("ROSE")) {
						couleurs.setStyle("-fx-background-color: pink");
					}
					else if (c.getCol().toString().equals("ORANGE")) {
						couleurs.setStyle("-fx-background-color: orange");
					}
					else if (c.getCol().toString().equals("VIOLET")) {
						couleurs.setStyle("-fx-background-color: purple");
					}
					else if (c.getCol().toString().equals("NOIR")) {
						couleurs.setStyle("-fx-background-color: black");
					}
					else if (c.getCol().toString().equals("MARRON")) {
						couleurs.setStyle("-fx-background-color: brown");
					}

				}
				command.accept(es.getCrayon());
				moveCursor(i+1);
			}
		} else {
			liveTextArea.setVisible(false);
			MonTokenizer.listFonction.clear();
			MonTokenizer.vars.reset();
		}
		//ta.clear();
	}

	public static TextArea getTa() {
		return ta;
	}
	
	

}


