package main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.print.attribute.standard.DialogTypeSelection;
import javax.swing.JOptionPane;

import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.animation.Animation;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.PerspectiveTransform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;

import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class App extends Application {

	TabPane tab = new TabPane();
	Tab main = new Tab("Accueil");
	Tab tuto = new Tab("Aide");
	Tab comparaison = new Tab("Comparer");
	Canvas img = new Canvas(400, 400);
	Canvas img1 = new Canvas(300, 300);
	Canvas img2 = new Canvas(300, 300);
	ScrollPane imgBase = new ScrollPane();
	ScrollPane imgPorte = new ScrollPane();
	Button uploadBase = new Button("Ajouter");
	Button uploadPorte = new Button("Ajouter");
	Button reset = new Button("Remise a zéro");
	Button quit = new Button("Quitter");
	Button uploadComparer = new Button("Ajouter");
	Button resetComp = new Button("Remise a zéro");

	Scene scene;
	HBox hbox = new HBox();
	VBox scrollBase = new VBox();
	VBox scrollPorte = new VBox();
	VBox vbox = new VBox();
	GraphicsContext c;
	Region espaceC = new Region();
	Region espaceP = new Region();
	Region espaceB = new Region();
	FileChooser porte = new FileChooser();
	ArrayList<Image> iconP = new ArrayList<Image>();
	ArrayList<Image> iconB = new ArrayList<Image>();
	ArrayList<Image> imgP = new ArrayList<Image>();
	ArrayList<Image> imgB = new ArrayList<Image>();
	VBox affichage = new VBox();

	WritableImage save = new WritableImage((int) img.getHeight(), (int) img.getWidth());
	Button sauvegarde = new Button("Exporter");
	File file;
	Boolean exportation = false;
	PerspectiveTransform perspectiveTransform = new PerspectiveTransform();
	Image transform;

	ArrayList<Rectangle> list = new ArrayList<Rectangle>();
	boolean drag = false;
	Rectangle dep;
	int nbBase;
	int nbComp;

	FileChooser base = new FileChooser();
	ScrollPane chooseComparaison = new ScrollPane();
	HBox scrollComparaison = new HBox();

	FileChooser pathcomparaison = new FileChooser();
	ArrayList<Image> iconComp = new ArrayList<Image>();
	ArrayList<Image> imgComp = new ArrayList<Image>();
	GraphicsContext f;
	GraphicsContext d;

	Stage global;

	int cptexport = 0;
	int cptreset = 0;
	int astuceImgB = 0;
	int astuceComp = 0;

	boolean estUtiliseF = false;
	boolean estUtiliseD = false;
	boolean resetdessin = false;

	public class onButtonClick implements EventHandler<ActionEvent> {

		public void handle(ActionEvent event) {
			if (event.getTarget().equals(uploadPorte)) {
				scrollPorte.getChildren().clear();
				iconP.clear();
				imgP.clear();
				porte.getExtensionFilters().addAll(new ExtensionFilter("Img type", "*.jpg", "*.png", "*.jpeg"));

				porte.setTitle("Choisiez vos image de portes");
				List<File> list = porte.showOpenMultipleDialog(global);
				for (File f : list) {
					iconP.add(new Image(f.toURI().toString(), 100, 100, false, false));
					imgP.add(new Image(f.toURI().toString(), 400, 400, false, false));

				}
				for (Image i : iconP) {

					scrollPorte.getChildren().add(new ImageView(i));

					imgPorte.setContent(scrollPorte);

				}

			}
			if (event.getTarget().equals(uploadComparer)) {
				scrollComparaison.getChildren().clear();
				iconComp.clear();
				imgComp.clear();
				pathcomparaison.getExtensionFilters()
						.addAll(new ExtensionFilter("Img type", "*.jpg", "*.png", "*.jpeg"));

				pathcomparaison.setTitle("Choisissez les images a comparer");
				List<File> list = pathcomparaison.showOpenMultipleDialog(global);
				for (File f : list) {
					iconComp.add(new Image(f.toURI().toString(), 100, 100, false, false));
					imgComp.add(new Image(f.toURI().toString(), 300, 300, false, false));

				}
				for (Image i : iconComp) {

					scrollComparaison.getChildren().add(new ImageView(i));
					chooseComparaison.setContent(scrollComparaison);

				}

			}
			if (event.getTarget().equals(resetComp)) {
				resetdessin = true;
				dessinerComp(f);
				estUtiliseF = false;
				dessinerComp(d);
				estUtiliseD = false;
				resetdessin = false;
			}

			if (event.getTarget().equals(uploadBase)) {
				scrollBase.getChildren().clear();
				iconB.clear();
				imgB.clear();
				base.getExtensionFilters().addAll(new ExtensionFilter("Img type", "*.jpg", "*.png", "*.jpeg"));
				List<File> list = base.showOpenMultipleDialog(global);
				base.setTitle("Choisiez vos image de maisons");
				for (File f : list) {
					iconB.add(new Image(f.toURI().toString(), 100, 100, false, false));
					imgB.add(new Image(f.toURI().toString(), 400, 400, false, false));

				}
				for (Image i : iconB) {
					scrollBase.getChildren().add(new ImageView(i));
					imgBase.setContent(scrollBase);

				}

			}
			if (event.getTarget().equals(reset)) {
				if (cptreset >= 3) {

					Alert tuto = new Alert(AlertType.CONFIRMATION);
					tuto.setHeaderText(null);
					tuto.setTitle("Aide");
					tuto.setContentText("Avez-vous besoin d'aide ?");
					tuto.showAndWait();
					if (tuto.getResult() == ButtonType.OK) {
						tab.getSelectionModel().select(2);
						cptreset = 0;
					}
					if (tuto.getResult() == ButtonType.CANCEL) {
						cptreset = 0;
					}
				}
				cptexport = 0;
				cptreset++;
				dessiner(c);

			}
			if (event.getTarget().equals(sauvegarde)) {
				FileChooser path = new FileChooser();
				path.setInitialFileName("export.png");
				exportation = true;
				dessiner(c);

				img.snapshot(null, save);
				file = path.showSaveDialog(global);

				try {
					ImageIO.write(SwingFXUtils.fromFXImage(save, null), "png", file);
					Alert exportok = new Alert(AlertType.INFORMATION);
					exportok.setTitle("Opération réussite!");
					exportok.setHeaderText(null);
					exportok.setContentText("Votre image a été exportée! \n");
					exportok.showAndWait();
					cptexport++;

				} catch (Exception ex) {
					System.out.println(ex.toString());
				}
			}
			if (event.getTarget().equals(quit)) {
				if (cptexport == 0) {
					Alert alertQuit = new Alert(AlertType.CONFIRMATION);
					alertQuit.setTitle("Quitter");
					alertQuit.setHeaderText(null);
					alertQuit.setContentText("Voulez-vous exporter votre travail avant de quitter?");

					alertQuit.showAndWait();

					if (alertQuit.getResult() == ButtonType.OK) {
						FileChooser path = new FileChooser();
						path.setInitialFileName("export.png");
						exportation = true;
						dessiner(c);

						img.snapshot(null, save);
						file = path.showSaveDialog(global);

						try {
							ImageIO.write(SwingFXUtils.fromFXImage(save, null), "png", file);
							Alert exportok = new Alert(AlertType.INFORMATION);
							exportok.setTitle("Opération réussite!");
							exportok.setHeaderText(null);
							exportok.setContentText("Votre image a été exportée! \n");
							exportok.showAndWait();

						} catch (Exception ex) {
							System.out.println(ex.toString());
						}
					}

					System.exit(0);
				}
				System.exit(0);
			}
		}
	}

	public void start(Stage stage) throws Exception {

		stage.setResizable(false);
		global = stage;

		list.add(new Rectangle(10, 10, 10, 10));
		list.add(new Rectangle(50, 10, 10, 10));

		list.add(new Rectangle(50, 50, 10, 10));
		list.add(new Rectangle(10, 50, 10, 10));

		Image bg = new Image("file:img/bg.png", 1100, 550, false, false);
		tab.setBackground(new Background(
				new BackgroundImage(bg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, null, null)));

		img.setOnMousePressed(e -> {
			drag = true;
			for (Rectangle r : list) {
				if (r.contains(e.getX(), e.getY())) {
					dep = r;

				}
			}

		});

		img.setOnMouseDragged(e -> {

			if (drag) {

				dep.setX(e.getX());
				dep.setY(e.getY());
			}

			dessiner(c);

		});

		img.setOnMouseReleased(e -> {
			drag = false;

		});

		stage.setTitle("Visualisation");
		stage.setHeight(550);
		stage.setWidth(1100);
		main.setClosable(false);
		tuto.setClosable(false);
		comparaison.setClosable(false);
		tab.getTabs().setAll(main, comparaison, tuto);

		imgPorte.setContent(scrollPorte);
		imgBase.setContent(scrollBase);

		imgPorte.setContent(new Label("Aucune images"));
		imgBase.setContent(new Label("Aucune images"));
		chooseComparaison.setContent(new Label("Aucune images"));

		c = img.getGraphicsContext2D();
		c.drawImage(new Image("file:img/imgbase.png", 400, 400, true, true), 0, 0);
		espaceC.setPadding(new Insets(10, 10, 10, 10));
		espaceP.setPadding(new Insets(80, 60, 60, 25));
		espaceB.setPadding(new Insets(10, 10, 10, 10));
		hbox.setPadding(new Insets(30, 30, 30, 30));
		imgPorte.setPadding(new Insets(20, 20, 20, 20));
		imgBase.setPadding(new Insets(20, 20, 20, 20));
		dessiner(c);

		uploadPorte.addEventHandler(ActionEvent.ACTION, new onButtonClick());
		uploadBase.addEventHandler(ActionEvent.ACTION, new onButtonClick());
		reset.addEventHandler(ActionEvent.ACTION, new onButtonClick());
		sauvegarde.addEventHandler(ActionEvent.ACTION, new onButtonClick());
		resetComp.addEventFilter(ActionEvent.ACTION, new onButtonClick());

		imgBase.setOnMouseMoved(e -> {
			if (!iconB.isEmpty() && astuceImgB == 0) {
				Alert test = new Alert(AlertType.INFORMATION);
				test.setHeaderText(null);
				test.setTitle("Astuce");
				test.setContentText("Utilisez SHIFT+clic pour supprimer une image");
				test.showAndWait();
				astuceImgB++;
			}

		});

		imgBase.setOnMouseClicked(e -> {
			int idx = 0;
			
			if (e.isShiftDown()) {
				ImageView target = (ImageView) e.getTarget();
				Image imgS = target.getImage();

				for (Image i : iconB) {
					if (i.equals(imgS)) {
						idx = iconB.indexOf(i);

					}
				}
				iconB.remove(idx);
				imgB.remove(idx);
				scrollBase.getChildren().clear();
				for (Image i : iconB) {
					scrollBase.getChildren().add(new ImageView(i));

				}

			}
			try {
				ImageView target = (ImageView) e.getTarget();
				Image imgS = target.getImage();

				for (Image i : iconB) {
					if (i.equals(imgS)) {
						nbBase = iconB.indexOf(i);
						imgS = imgB.get(nbBase);
						dessiner(c);
					}

				}
			} catch (Exception ex) {

			}

		});

		imgPorte.setOnMouseClicked(e -> {
			int idx = 0;
			if (e.isShiftDown()) {
				ImageView target = (ImageView) e.getTarget();
				Image imgS = target.getImage();

				for (Image i : iconP) {
					if (i.equals(imgS)) {
						idx = iconP.indexOf(i);

					}
				}
				iconP.remove(idx);
				imgP.remove(idx);
				scrollPorte.getChildren().clear();
				for (Image i : iconP) {
					scrollPorte.getChildren().add(new ImageView(i));

				}

			}
			try {
				ImageView target = (ImageView) e.getTarget();
				Image imgS = target.getImage();
				transform = imgS;
				for (Image i : iconP) {
					if (i.equals(imgS)) {
						idx = iconP.indexOf(i);
						imgS = imgP.get(idx);
						dessiner(c);

						perspectiveTransform.setUlx(list.get(0).getX() + 4);
						perspectiveTransform.setUly(list.get(0).getY() + 4);
						perspectiveTransform.setUrx(list.get(1).getX() + 4);
						perspectiveTransform.setUry(list.get(1).getY() + 4);
						perspectiveTransform.setLrx(list.get(2).getX() + 4);
						perspectiveTransform.setLry(list.get(2).getY() + 4);
						perspectiveTransform.setLlx(list.get(3).getX() + 4);
						perspectiveTransform.setLly(list.get(3).getY() + 4);

						c.setEffect(perspectiveTransform);
						c.drawImage(imgS, 0, 0);
						c.setEffect(null);
					}
				}

			} catch (Exception ex) {

			}

		});

		VBox porte = new VBox();
		uploadPorte.setPadding(new Insets(10, 48, 10, 48));
		Label labelPorte = new Label("Portes/Fenêtres");
		Region espaceImgPorte = new Region();
		labelPorte.setPadding(new Insets(5, 10, 10, 20));
		espaceImgPorte.setPadding(new Insets(5, 10, 10, 10));
		porte.getChildren().addAll(labelPorte, imgPorte, espaceImgPorte, uploadPorte);

		VBox base = new VBox();
		uploadBase.setPadding(new Insets(10, 48, 10, 48));
		Region espaceImgBase = new Region();
		espaceImgBase.setPadding(new Insets(5, 10, 10, 10));

		Label labelBase = new Label("Façades");
		labelBase.setPadding(new Insets(5, 10, 10, 45));
		base.getChildren().addAll(labelBase, imgBase, espaceImgBase, uploadBase);

		VBox controls = new VBox();
		Region espaceBouton = new Region();
		Region espaceBouton2 = new Region();
		espaceBouton.setPadding(new Insets(20, 20, 20, 20));
		espaceBouton2.setPadding(new Insets(10, 10, 10, 10));
		reset.setPadding(new Insets(10, 10, 10, 10));
		sauvegarde.setPadding(new Insets(10, 27, 10, 27));
		quit.setPadding(new Insets(10, 32, 10, 32));
		quit.addEventHandler(ActionEvent.ACTION, new onButtonClick());
		controls.setPadding(new Insets(130, 10, 10, 10));
		controls.getChildren().addAll(sauvegarde, espaceBouton, reset, espaceBouton2, quit);

		Region espaceBase = new Region();
		espaceBase.setPadding(new Insets(10, 10, 10, 70));

		hbox.getChildren().addAll(espaceC, img, espaceP, porte, espaceB, base, espaceBase, controls);
		main.setContent(hbox);

		// aide
		HBox aide = new HBox();
		Label txt = new Label("\t \t \t Bienvenue sur le menu d'aide \n \n"
				+ "1. Première étape : ajouter des photos de votre façade.\n"
				+ "2. Deuxième étape : ajouter des photos de portes/fenêtre.s\n"
				+ "3. Troisième étape : choisir votre façade parmis celles ajoutées.\n\n"
				+ "4. Etape de séléction : avec le selecteur rouge et noir\n définissez ou votre porte/fenêtre devra ce situer.\n\n"
				+ "5. Dernière étape : choisir une potre/fenêtre\n parmis celles ajoutées.");

		txt.setFont(Font.font("", FontWeight.BOLD, 14));
		txt.setTextFill(Color.BLACK);

		Image gif = new Image("file:img/aide.gif", 600, 600, true, true);
		Pane imgaide = new Pane();
		imgaide.setPadding(new Insets(10, 10, 10, 10));
		imgaide.getChildren().add(new ImageView(gif));

		Region espImg = new Region();
		espImg.setPadding(new Insets(10, 10, 10, 10));
		aide.setPadding(new Insets(80, 20, 20, 20));
		aide.getChildren().addAll(imgaide, espImg, txt);
		tuto.setContent(aide);

		// comparaison
		uploadComparer.addEventHandler(ActionEvent.ACTION, new onButtonClick());

		Region espaceImgComp = new Region();
		Region espControl = new Region();
		espaceImgComp.setPadding(new Insets(10, 320, 10, 10));
		VBox Vboxcomparaison = new VBox();
		HBox imgComparaison = new HBox();
		VBox controlComp = new VBox();

		imgComparaison.setPadding(new Insets(40, 10, 10, 10));
		espControl.setPadding(new Insets(20, 20, 20, 20));
		uploadComparer.setPadding(new Insets(10, 31, 10, 31));
		resetComp.setPadding(new Insets(10, 10, 10, 10));
		f = img1.getGraphicsContext2D();
		d = img2.getGraphicsContext2D();
		f.drawImage(new Image("file:img/imgbase.png", 300, 300, true, true), 0, 0);
		d.drawImage(new Image("file:img/imgbase.png", 300, 300, true, true), 0, 0);
		controlComp.getChildren().addAll(uploadComparer, espControl, resetComp);
		imgComparaison.getChildren().addAll(img1, /* espaceImgComp */controlComp, img2);
		controlComp.setPadding(new Insets(80, 185, 80, 185));
		chooseComparaison.setPadding(new Insets(10, 10, 10, 10));
		chooseComparaison.setContent(scrollComparaison);

		chooseComparaison.setOnMouseMoved(e -> {
			if (!iconComp.isEmpty() && astuceComp == 0) {
				Alert test = new Alert(AlertType.INFORMATION);
				test.setHeaderText(null);
				test.setTitle("Astuce");
				test.setContentText("Utilisez SHIFT+clic pour supprimer une image");
				test.showAndWait();
				astuceComp++;
			}

		});

		chooseComparaison.setOnMouseClicked(e -> {
			if (e.isShiftDown()) {
				int idx = 0;
				ImageView target = (ImageView) e.getTarget();
				Image imgS = target.getImage();

				for (Image i : iconComp) {
					if (i.equals(imgS)) {
						idx = iconComp.indexOf(i);

					}
				}
				iconComp.remove(idx);
				imgComp.remove(idx);
				scrollComparaison.getChildren().clear();
				for (Image i : iconComp) {
					scrollComparaison.getChildren().add(new ImageView(i));

				}
				chooseComparaison.setContent(scrollComparaison);
			}
			try {
				ImageView target = (ImageView) e.getTarget();
				Image imgS = target.getImage();

				for (Image i : iconComp) {
					if (i.equals(imgS)) {
						nbComp = iconComp.indexOf(i);
						imgS = imgComp.get(nbComp);
						if (!estUtiliseF) {
							dessinerComp(f);
							this.estUtiliseF = true;
							break;
						}
						if (!estUtiliseD) {
							dessinerComp(d);
							this.estUtiliseD = true;
							break;
						}

					}

				}
			} catch (Exception ex) {

			}
		});

		Region espimgcont = new Region();
		espimgcont.setPadding(new Insets(10, 10, 10, 10));
		scrollComparaison.setSpacing(5);
		Vboxcomparaison.getChildren().addAll(imgComparaison, espimgcont, chooseComparaison);
		comparaison.setContent(Vboxcomparaison);

		scrollPorte.setSpacing(4);
		scrollBase.setSpacing(4);
		scene = new Scene(tab);
		stage.setScene(scene);
		stage.show();

	}

	public void dessiner(GraphicsContext gc) {

		if (!imgB.isEmpty() && !exportation) {
			gc.drawImage(imgB.get(nbBase), 0, 0);

			for (Rectangle r : list) {

				gc.setFill(Color.BLACK);
				gc.fillRect(r.getX(), r.getY(), 10, 10);

			}

			gc.setStroke(Color.RED);
			gc.setLineWidth(1.5);

			gc.strokeLine(list.get(0).getX() + 5, list.get(0).getY() + 5, list.get(1).getX() + 5,
					list.get(1).getY() + 5);
			gc.strokeLine(list.get(1).getX() + 5, list.get(1).getY() + 5, list.get(2).getX() + 5,
					list.get(2).getY() + 5);
			gc.strokeLine(list.get(2).getX() + 5, list.get(2).getY() + 5, list.get(3).getX() + 5,
					list.get(3).getY() + 5);
			gc.strokeLine(list.get(3).getX() + 5, list.get(3).getY() + 5, list.get(0).getX() + 5,
					list.get(0).getY() + 5);

		}
		if (!imgB.isEmpty() && exportation) {

			// essai qualité
			gc.clearRect(0, 0, 400, 400);

			gc.drawImage(imgB.get(nbBase), 0, 0);

			perspectiveTransform.setUlx(list.get(0).getX() + 4);
			perspectiveTransform.setUly(list.get(0).getY() + 4);
			perspectiveTransform.setUrx(list.get(1).getX() + 4);
			perspectiveTransform.setUry(list.get(1).getY() + 4);
			perspectiveTransform.setLrx(list.get(2).getX() + 4);
			perspectiveTransform.setLry(list.get(2).getY() + 4);
			perspectiveTransform.setLlx(list.get(3).getX() + 4);
			perspectiveTransform.setLly(list.get(3).getY() + 4);

			c.setEffect(perspectiveTransform);
			c.drawImage(transform, 0, 0);
			c.setEffect(null);
			exportation = false;

		}

	}

	public void dessinerComp(GraphicsContext gc) {

		if (!imgComp.isEmpty() && !resetdessin) {
			gc.drawImage(imgComp.get(nbComp), 0, 0);
		}
		if (resetdessin) {

			gc.drawImage(new Image("file:img/imgbase.png", 300, 300, false, false), 0, 0);

		}

	}

	public static void main(String[] args) {
		Application.launch(args);

	}

}
