package interfaces;






import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import com.sun.javafx.geom.Rectangle;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class MainAide extends Application {

	private static Stage mainStage = new Stage();
	private Scene mainScene;
	
	private Stage majStage = new Stage();
	private Scene majScene;
	
	private Scene exemplesScene;
	
	private ArrayList<String> commits = new ArrayList<String>();
	private ArrayList<CommitPrinter> printCommits = new ArrayList<CommitPrinter>();
	
	private int nb = 0;
	private Button report = new Button("Signaler");
	
	/** video exemples **/
	
	private File exemple1 = new File("ressources/videos/exemple1.mp4"); 
	private File exemple2 = new File("ressources/videos/exemple2.mp4");
	private File exemple3 = new File("ressources/videos/exemple3.mp4");
	
	private Media media;
	private MediaPlayer  mp;
	private MediaView mv;
	
	
	/** boutons du menu **/
	
	Button commande = new Button("");
	Button interfaces = new Button ("");
	Button exemples = new Button("");
	Button maj = new Button("");
	
	
	/** **/
	
	public void start(Stage stage) throws Exception {
		
		/** reglages de la fenetre **/
		mainStage = stage;	
		stage.setResizable(false);
		stage.setTitle("Mannuel D'utilisation");
		VBox root = new VBox();
		root.setStyle("-fx-background-color: #D3D3D3");
		root.setBorder(new Border(new BorderStroke(Color.web("334454"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY,new BorderWidths(10,10,10,10))));
		/** **/
		
		/** titre du haut de la fenetre **/
		HBox zoneTitre =  new HBox();
		zoneTitre.setPadding(new Insets(10,10,10,10));
		TextFlow titre = new TextFlow();
		titre.setStyle("-fx-background-color: #334454");
		titre.setPrefSize(900, 25);
		Text textTitre = new Text("Manuel d'utilisation");
		textTitre.setFill(Color.WHITE);
		textTitre.setFont(Font.loadFont("file:ressources/font/4.otf", 25));
		
		titre.getChildren().add(textTitre);
		zoneTitre.getChildren().add(titre);
		titre.setTextAlignment(TextAlignment.CENTER);	
		/** **/
		
		
		/** centre de la fenetre **/
		
		HBox mainCentre = new HBox();
		
		
		VBox listMenu = new VBox();
		listMenu.setPadding(new Insets(25,10,10,15));
		listMenu.setStyle("-fx-background-color: white");
		listMenu.setMinSize(420, 390);
		listMenu.setMaxSize(420, 390);
		listMenu.setSpacing(15);
		
		
		commande.setBackground(new Background(new BackgroundImage(new Image("file:ressources/aide/commandes.png"),null,null,null,null)));
		commande.setPrefSize(360, 70);
		
		commande.setOnMouseEntered(e->{
			commande.setBackground(new Background(new BackgroundImage(new Image("file:ressources/aide/commandeHover.png"),null,null,null,null)));
			commande.setPrefSize(360, 70);
			
		});
		commande.setOnMouseExited(e ->{
			commande.setBackground(new Background(new BackgroundImage(new Image("file:ressources/aide/commandes.png"),null,null,null,null)));
			commande.setPrefSize(360, 70);
			
		});
		
		
		
		interfaces.setBackground(new Background(new BackgroundImage(new Image("file:ressources/aide/interface.png"),null,null,null,null)));
		interfaces.setPrefSize(360, 70);
		
		interfaces.setOnMouseEntered(e ->{
			interfaces.setBackground(new Background(new BackgroundImage(new Image("file:ressources/aide/interfaceHover.png"),null,null,null,null)));
			interfaces.setPrefSize(360, 70);
		});
		
		interfaces.setOnMouseExited(e-> {
			interfaces.setBackground(new Background(new BackgroundImage(new Image("file:ressources/aide/interface.png"),null,null,null,null)));
			interfaces.setPrefSize(360, 70);
		});
		
		
		
		exemples.setBackground(new Background(new BackgroundImage(new Image("file:ressources/aide/exemples.png"),null,null,null,null)));
		exemples.setPrefSize(360, 70);
		
		exemples.setOnMouseEntered(e ->{
			exemples.setBackground(new Background(new BackgroundImage(new Image("file:ressources/aide/exempleHover.png"),null,null,null,null)));
			exemples.setPrefSize(360, 70);
		});
		
		exemples.setOnMouseExited(e -> {
			exemples.setBackground(new Background(new BackgroundImage(new Image("file:ressources/aide/exemples.png"),null,null,null,null)));
			exemples.setPrefSize(360, 70);
		});
		
		exemples.setOnMouseClicked(e -> {
			stage.setScene(exemplesScene);
		});
		
		
		maj.setBackground(new Background(new BackgroundImage(new Image("file:ressources/aide/maj.png"),null,null,null,null)));
		maj.setPrefSize(360, 70);
		
		maj.setOnMouseEntered(e ->{
			maj.setBackground(new Background(new BackgroundImage(new Image("file:ressources/aide/majHover.png"),null,null,null,null)));
			maj.setPrefSize(360, 70);
		});
		maj.setOnMouseClicked(e ->{
			stage.setScene(majScene);
		});
		
		maj.setOnMouseExited(e -> {
			maj.setBackground(new Background(new BackgroundImage(new Image("file:ressources/aide/maj.png"),null,null,null,null)));
			maj.setPrefSize(360, 70);
		});
		
		
		
		
		listMenu.getChildren().addAll(commande,interfaces,exemples,maj);

		/** fin **/
		
		
		
		
		//** droite **/
		
		HBox affichage = new HBox();
		affichage.setStyle("-fx-background-color: white");
		Image welcomeImg = new Image("file:ressources/aide/imgwelcome.png");
		ImageView  welcomeIV = new ImageView(welcomeImg);
		Pane affichageImg = new Pane();
		affichage.setMinSize(420, 390);
		affichage.setMaxSize(420, 390);
		welcomeIV.setStyle("-fx-background-color: white");
		affichageImg.getChildren().add(welcomeIV);
		affichage.getChildren().add(affichageImg);
		//affichage.setPadding(new Insets(10,10,10,10));
		
		mainCentre.getChildren().addAll(listMenu,affichage);
		
		mainCentre.setPadding(new Insets(10,10,10,10));
		mainCentre.setSpacing(15);
		
		/** fin **/
		
		/** zone d'ajout des enfants **/
		
		root.getChildren().addAll(zoneTitre,mainCentre);
		
		/** fin **/
		
		
		/** maj & bug **/
		
		Button back = new Button(" ");
		try {
		getLastCommits();
		}catch(Exception e) {
			System.out.println("pas de connexion internet");
			Alert a = new Alert(AlertType.ERROR,"bug");
			a.setHeaderText(null);
			a.setAlertType(AlertType.ERROR);
			a.setContentText("Désolé la page n'est pas accesible sans internet pour le moment");
			a.showAndWait();
		}
			
		
		
		majStage.setResizable(false);
		majStage.setTitle("Mise a jour & bugs");
		VBox rootMaj = new VBox();
		rootMaj.setStyle("-fx-background-color: #D3D3D3");
		rootMaj.setBorder(new Border(new BorderStroke(Color.web("334454"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY,new BorderWidths(10,10,10,10))));
		
		
		/** titre du haut de la fenetre **/
		HBox zoneTitreMaj =  new HBox();
		zoneTitreMaj.setPadding(new Insets(10,10,10,10));
		TextFlow titreMaj = new TextFlow();
		titreMaj.setStyle("-fx-background-color: #334454");
		zoneTitreMaj.setStyle("-fx-background-color: #334454");
		
		titreMaj.setPrefSize(900, 25);
		Text textTitreMaj = new Text("Mise a jour et bugs");
		textTitreMaj.setFill(Color.WHITE);
		textTitreMaj.setFont(Font.loadFont("file:ressources/font/4.otf", 25));
		
		titreMaj.getChildren().add(textTitreMaj);
		back.setBackground(new Background(new BackgroundImage(new Image("file:ressources/home.png",44,44,true,true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT)));
		back.setMinSize(44, 44);
		back.setOnMouseClicked(e -> {
		 mainStage.setScene(mainScene);
			
		});
		zoneTitreMaj.getChildren().addAll(back,titreMaj);
		titreMaj.setTextAlignment(TextAlignment.CENTER);	
		
		
		
		/** **/
		
		HBox centreMaj = new HBox();
		
		VBox lastCommits = new VBox();
		
		VBox bugReport = new VBox();
		
		centreMaj.setPadding(new Insets(10,10,10,10));
		centreMaj.setSpacing(15);
		lastCommits.setStyle("-fx-background-color: white");
		lastCommits.setPrefSize(420, 390);
		lastCommits.setPadding(new Insets(10,10,10,10));
		
		bugReport.setStyle("-fx-background-color: white");
		bugReport.setPrefSize(420, 390);
	
		
		
		
		Image logoGit = new Image("file:ressources/aide/git.png");
		ImageView logoGitIv = new ImageView(logoGit);
		StackPane supportImage = new StackPane();
		Text link = new Text("                             https://git-iut.univ-lille1.fr/rohaertf/ProjetMode2017-K5.git");
		// oui cet espace a une utilité ^
		
		link.setFont(Font.font(9));
		link.setFill(Color.GRAY);
		
		TextFlow printLink = new TextFlow(link);
		supportImage.setMinSize(220, 78);
		supportImage.getChildren().add(logoGitIv);
		supportImage.setAlignment(logoGitIv, Pos.CENTER);
		
		VBox affichageCommit = new VBox();
		affichageCommit.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,new BorderWidths(3,3,3,3))));
		affichageCommit.setPrefSize(420, 300);
		affichageCommit.setSpacing(5);
		
		for(int i = 0; i < 5; i++) {
			Text date = new Text(printCommits.get(i).getDate() + "\n");
			date.setFill(Color.GREEN);
			Text nom = new Text(">> " + printCommits.get(i).getTitre() + "\n");
			Text auteur = new Text(printCommits.get(i).getAuteur());
			auteur.setFill(Color.BLUE);
			TextFlow list = new TextFlow(date,nom,auteur);
			list.setStyle("-fx-background-color: #D3D3D3");
			affichageCommit.getChildren().add(list);
			
		}
		
		
		lastCommits.getChildren().addAll(supportImage,printLink,affichageCommit);	
		lastCommits.setSpacing(3);
		
		centreMaj.getChildren().addAll(lastCommits,bugReport);
		
		rootMaj.getChildren().addAll(zoneTitreMaj,centreMaj);
		/** fin **/
		/** fin **/
		
		
		
		
		majScene = new Scene(rootMaj, 900,500);
		//majStage.setScene(majScene);
		//majStage.show();
		
		/** bug report **/
		TextField to = new TextField("team@developpers.com");
		to.setEditable(false);
		to.setStyle("-fx-background-color: #334454; -fx-text-fill: white;");
		
		to.setMinSize(225, 25);
		to.setMaxSize(255, 25);
	
		
		TextField subject = new TextField("#bugReport: ");
		subject.setStyle("-fx-background-color: #334454; -fx-text-fill: white;");
		
		subject.setMinSize(225, 25);
		subject.setMaxSize(255, 25);
		
		TextArea message = new TextArea();
		
		message.setText("Expliquez en quelque lignes le problème rencontré....\nSi vous le pouvez joignez des captures d'écran avec lien");
		message.setStyle("-fx-text-fill: grey;");
		
		
		message.setOnMouseClicked(e -> {
			if(nb == 0) {
				message.setText("");
				message.setStyle("-fx-text-fill: black;");
				nb++;
			}
		});
		
		TextFlow txt = new TextFlow();
		Text contentTxt = new Text("Signaler un bug");
		contentTxt.setFill(Color.BLACK);
		contentTxt.setFont(Font.loadFont("file:ressources/font/4.otf", 15));
		
		
		txt.setTextAlignment(TextAlignment.CENTER);
		txt.getChildren().add(contentTxt);
		
		report.setStyle("-fx-background-color: #334454; -fx-text-fill: white;");
		report.setMinSize(100, 10);
		HBox button = new HBox();
		Region r = new Region();
		r.setPadding(new Insets(120,10,10,120));
		button.getChildren().addAll(r,report);
		
		report.setOnMouseClicked(e ->{
			MailSender ms = new MailSender(subject.getText(),message.getText());
			ms.send();
			
			Alert a = new Alert(AlertType.INFORMATION);
			a.setTitle("Merci!");
			a.setHeaderText(null);
			a.setContentText("Le bug a été signalé aux developpeurs, merci.");
			a.show();
			message.setText("");
		});
		
		bugReport.setSpacing(15);
		bugReport.setPadding(new Insets(20,10,10,30));
		bugReport.getChildren().addAll(txt, to,subject,message,button);
		
		
		/** exemples **/
		
		VBox rootExemple = new VBox();
		rootExemple.setStyle("-fx-background-color: #D3D3D3");
		rootExemple.setBorder(new Border(new BorderStroke(Color.web("334454"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY,new BorderWidths(10,10,10,10))));
		
		Button backEx = new Button();
		
		HBox zoneTitreExemple =  new HBox();
		zoneTitreExemple.setPadding(new Insets(10,10,10,10));
		TextFlow titreExemple = new TextFlow();
		titreExemple.setStyle("-fx-background-color: #334454");
		zoneTitreExemple.setStyle("-fx-background-color: #334454");
		
		titreExemple.setPrefSize(900, 25);
		Text textTitreExemple = new Text("Exemples...");
		textTitreExemple.setFill(Color.WHITE);
		textTitreExemple.setFont(Font.loadFont("file:ressources/font/4.otf", 25));
		
		titreExemple.getChildren().add(textTitreExemple);
		backEx.setBackground(new Background(new BackgroundImage(new Image("file:ressources/home.png",44,44,true,true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT)));
		backEx.setMinSize(44, 44);
		backEx.setOnMouseClicked(e -> {
		 mainStage.setScene(mainScene);
			
		});
		zoneTitreExemple.getChildren().addAll(backEx,titreExemple);
		titreExemple.setTextAlignment(TextAlignment.CENTER);	
		
		rootExemple.getChildren().add(zoneTitreExemple);
		
		exemplesScene = new Scene(rootExemple,900,500);
		
		
		HBox centreExemple = new HBox();
		
		VBox listExemples = new VBox();
		
		VBox exemplesPlayer = new VBox();
		StackPane player = new StackPane();
		
		
		listExemples.setPadding(new Insets(25,10,10,15));
		listExemples.setStyle("-fx-background-color: white");
		listExemples.setMinSize(420, 390);
		listExemples.setMaxSize(420, 390);
		listExemples.setSpacing(15);
		
		Button ExempleI = new Button();
		Button ExempleII = new Button();
		Button ExempleIII = new Button();
		
		ExempleI.setBackground(new Background(new BackgroundImage(new Image("file:ressources/aide/exemple1.png"),null,null,null,null)));
		ExempleI.setPrefSize(360, 70);
		
		ExempleI.setOnMouseEntered(e->{
			ExempleI.setBackground(new Background(new BackgroundImage(new Image("file:ressources/aide/exemple1Hover.png"),null,null,null,null)));
			ExempleI.setPrefSize(360, 70);
			
		});
		ExempleI.setOnMouseExited(e ->{
			ExempleI.setBackground(new Background(new BackgroundImage(new Image("file:ressources/aide/exemple1.png"),null,null,null,null)));
			ExempleI.setPrefSize(360, 70);
			
		});
		
		ExempleI.setOnMouseClicked(e -> {
			
			player.getChildren().clear();
			
			try {
				media = new Media(exemple1.toURI().toURL().toString());
			} catch (MalformedURLException e1) {
			}
			mp = new MediaPlayer(media);
			mv = new MediaView(mp);
			player.getChildren().add(mv);
			
			mp.play();
			
			
		});
		
		
		ExempleII.setBackground(new Background(new BackgroundImage(new Image("file:ressources/aide/exemple2.png"),null,null,null,null)));
		ExempleII.setPrefSize(360, 70);
		
		ExempleII.setOnMouseEntered(e ->{
			ExempleII.setBackground(new Background(new BackgroundImage(new Image("file:ressources/aide/exemple2Hover.png"),null,null,null,null)));
			ExempleII.setPrefSize(360, 70);
		});
		
		ExempleII.setOnMouseExited(e-> {
			ExempleII.setBackground(new Background(new BackgroundImage(new Image("file:ressources/aide/exemple2.png"),null,null,null,null)));
			ExempleII.setPrefSize(360, 70);
		});
		
		ExempleII.setOnMouseClicked(e -> {
			player.getChildren().clear();
			
			try {
				media = new Media(exemple2.toURI().toURL().toString());
			} catch (MalformedURLException e1) {
			}
			mp = new MediaPlayer(media);
			mv = new MediaView(mp);
			player.getChildren().add(mv);
			
			mp.play();
		});
		
		
		ExempleIII .setBackground(new Background(new BackgroundImage(new Image("file:ressources/aide/exemple3.png"),null,null,null,null)));
		ExempleIII .setPrefSize(360, 70);
		
		ExempleIII .setOnMouseEntered(e ->{
			ExempleIII .setBackground(new Background(new BackgroundImage(new Image("file:ressources/aide/exemple3Hover.png"),null,null,null,null)));
			ExempleIII .setPrefSize(360, 70);
		});
		
		ExempleIII .setOnMouseExited(e -> {
			ExempleIII .setBackground(new Background(new BackgroundImage(new Image("file:ressources/aide/exemple3.png"),null,null,null,null)));
			ExempleIII .setPrefSize(360, 70);
		});
		
		ExempleIII.setOnMouseClicked(e -> {
			
			player.getChildren().clear();
			
			try {
				media = new Media(exemple3.toURI().toURL().toString());
			} catch (MalformedURLException e1) {
			}
			mp = new MediaPlayer(media);
			mv = new MediaView(mp);
			player.getChildren().add(mv);
			
			mp.play();
			
			
		});
		TextFlow info = new TextFlow();
		Text infoContent = new Text("\nD'autes exemples sont a prévoir avec les prochaines mise à jour du logiciel...");
		infoContent.setFill(Color.GREY);
		infoContent.setFont(Font.font(STYLESHEET_MODENA, 15));
		info.getChildren().add(infoContent);
		
		centreExemple.setPadding(new Insets(10,10,10,10));
		listExemples.getChildren().addAll(ExempleI,ExempleII,ExempleIII,info);
		
		
		
		//player.setPadding(new Insets(10,0,0,15));
		player.setMinSize(420, 390);
		player.setMaxSize(420, 390);
		
		exemplesPlayer.getChildren().add(player);
		
		//mp.play();
		
		centreExemple.setSpacing(15);
		centreExemple.getChildren().addAll(listExemples,exemplesPlayer);
		
		rootExemple.getChildren().add(centreExemple);
		
		
		/** fin **/
		
		
		mainScene = new Scene(root, 900,500);
		stage.setScene(mainScene);
		stage.show();
	}
	
	

	public static Stage getStage() {
		return mainStage;
	}
	public Scene getScene() {
		return this.mainScene;
	}
	
	public boolean getLastCommits() throws IOException {
		String infoReturn = "";
		
		URL u = null;
		try {
			u = new URL("https://git-iut.univ-lille1.fr/api/v3/projects/974/repository/commits?private_token=yQ3L47kvoyZsHzt95sqR");
		} catch (MalformedURLException e1) {
				e1.printStackTrace();
				return false;
				
		}
		URLConnection con = u.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		
		infoReturn = in.readLine();
		in.close();
		
		for(String s : infoReturn.split("parent_id")) {
			try {
			commits.add(s.substring(s.indexOf("message") + 10, s.indexOf("committer_name") - 22));
			}
			catch (Exception e) {
			}
		}
		
		
		for(String s : commits) {
			printCommits.add(new CommitPrinter(s.substring(0,s.indexOf("author_name") -3),s.substring(s.indexOf("author_name") +14 , s.indexOf("author_email") -3), s.substring(s.indexOf("authored_date") +16, s.length())));
		}
		
		return true;
	}
}
