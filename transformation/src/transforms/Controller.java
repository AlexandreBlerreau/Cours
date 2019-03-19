package transforms;


import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;
import javafx.util.Pair;

import static transforms.Constants.IDENTITY;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Controller {

    @FXML
    private Pane pane;
    
    @FXML
    public ScrollPane trans;
    
  
    
    
    private DrawContext drawContext;
    private  Composition composition = new Composition();

    public void initialize() throws IOException {
        drawContext = new DrawContext(pane);
       
        update(trans);
        Tooltip t = new Tooltip("Cliquez pour supprimer une transformation");
    	t.activatedProperty();
    	t.autoFixProperty();
    	trans.setTooltip(t);
        
    }

    private void reset() {
        pane.getChildren().add(new Grille());
        composition.clear();
    }
    
    private void resetAfterRemove() throws IOException, NullPointerException, IndexOutOfBoundsException {
        pane.getChildren().add(new Grille());
        composition.clearFile();
        this.show();
       
    }
    


    private void update(ScrollPane p) throws IOException {
        reset();
        show();
    }

    private void show() throws IOException,NullPointerException, IndexOutOfBoundsException {
        drawContext.drawClear();
        
        Maison m1 = new Maison();
        for (int index = 0 ; index <= composition.size() ; index++) {
            if (index == 0) {
                composition.drawStep(0, drawContext);
                composition.displayMatrix(null,IDENTITY, m1);
            } else {
                composition.drawStep(index, drawContext);

                try {
                    composition.displayMatrix(composition.getAtomique(index-1), composition.getCumulated(index-1), m1);
                } catch (TransformationException e) {
                }
            }
        }
    }

    
    public void animate() throws NullPointerException, IndexOutOfBoundsException{
    	Maison maisonAnimée = new Maison();
    	Maison maisonAnimée2 = new Maison();
        drawContext.drawClear();
        composition.drawStep(0, drawContext);


        Timeline timeline = new Timeline();

       
        maisonAnimée.setStroke(Constants.DESSIN_MOUVEMENT);
        drawContext.drawAdd(maisonAnimée);
        composition.animate(timeline, maisonAnimée, drawContext);

        
        maisonAnimée2.setStroke(Constants.DESSIN_ORIGINE);
        drawContext.drawAdd(maisonAnimée2);
        timeline.play();
    }
    
    
    public void open() throws IOException {
    	File file = new File("Matrice.txt");
    	Desktop desktop = Desktop.getDesktop();
    	if(file.exists()) desktop.open(file);
    
    }
    
    public void newTransforms() throws IndexOutOfBoundsException, IOException {
    	Stage stage = new Stage();
    	
        VBox root = new VBox();
        root.setPadding(new Insets(20,20,20,20));
        root.setSpacing(20);
        root.setStyle("-fx-background-color: #D3D3D3");
        
        HBox transfo = new HBox();
        transfo.setAlignment(Pos.CENTER);
        transfo.setSpacing(20);
        
        Button t = new Button("Ajouter une Translation");
        t.setStyle("-fx-text-fill: white; -fx-background-color : #334453");
        t.setMinWidth(190);
        
        
        ImageView tImg = new ImageView(new Image("file:ressource/translation.png",169,169,true,true));
        transfo.getChildren().addAll(tImg,t);
        
        
        
        HBox rot = new HBox();
        rot.setAlignment(Pos.CENTER);
        rot.setSpacing(20);
        
        Button r = new Button("Ajouter une Rotation");
        r.setStyle("-fx-text-fill: white; -fx-background-color : #334453");
        r.setMinWidth(190);
        
        ImageView rImg = new ImageView(new Image("file:ressource/rotation.png",169,169,true,true));
        rot.getChildren().addAll(rImg,r);
        
        
        
        
        
        HBox homo = new HBox();
        homo.setAlignment(Pos.CENTER);
        homo.setSpacing(20);
        Button h = new Button("Ajouter une Homothétie");
        h.setStyle("-fx-text-fill: white; -fx-background-color : #334453");
        h.setMinWidth(190);
        
        ImageView hImg = new ImageView(new Image("file:ressource/homothetie.png",169,169,true,true));
        homo.getChildren().addAll(hImg,h);
        
        
        
        
        
        
        root.getChildren().addAll(transfo,rot,homo);
        Scene sc = new Scene(root);
        stage.setScene(sc);
        stage.setResizable(false);
        stage.show();
        
        t.setOnMouseClicked(e ->{
        	DialogTranslation d = new DialogTranslation();
        	d.call();
        	composition.add(new Translation(Double.parseDouble(d.getX()),Double.parseDouble(d.getY())), trans);
        	 try {
 				this.resetAfterRemove();
 				
 				
 			} catch (Exception e1) {
 				
 			}
        	
        });
        
        r.setOnMouseClicked(e ->{
        	DialogRotation dr = new DialogRotation();
        	dr.call();
        	composition.add(new Rotation(Double.parseDouble(dr.getA()),Double.parseDouble(dr.getX()),Double.parseDouble(dr.getY())), trans);
        	 try {
 				this.resetAfterRemove();
 				

 			} catch (Exception e1) {
 				
 			}
        });
        
        h.setOnMouseClicked(e ->{
        	DialogHomothetie d = new DialogHomothetie();
        	d.call();
        	composition.add(new Homothetie(Double.parseDouble(d.getR()),Double.parseDouble(d.getX()),Double.parseDouble(d.getY())), trans);
        	 try {
				this.resetAfterRemove();
				

			} catch (Exception e1) {
				
			}
        	
        });
        
       
        
       
    }
    
    public void remove() {
    	trans.setOnMousePressed(e ->{
    		ImageView target = (ImageView) e.getTarget();
    		try {
				composition.remove(target, trans);
			} catch (IOException e2) {
			}
    	
    		try {
				this.resetAfterRemove();
			} catch (IOException e1) {
			}
    		
    		
    		
    	});
    }
    
    public void changeMaison() throws IOException {
    	
    
    	String matrix = "";
    	
    	
    	DialogMatrice d = new DialogMatrice();
    	d.call();
    	
    	matrix = d.getMatrice();
    	matrix = matrix.replaceAll("\\s+","");
    	System.err.println(matrix.length());
    	int taille = 0;
    	
    	ArrayList<Double> pts = new ArrayList<Double>();
    	
    	for(String s : matrix.split(",")) {
    		pts.add(Double.parseDouble(s));
    	}
    	taille = pts.size();
    	
    	Double [] arrayOfPts = pts.toArray(new Double[taille]);
    	Constants.setMatrixDessin(arrayOfPts);
    	this.resetAfterRemove();
    	
    }
    
    public void changeCouleur() throws IOException {
    	DialogCouleur d = new DialogCouleur();
    	d.call();
    	Constants.setDessin(this.stringToColor(d.getDessin()));
    	Constants.setFin(this.stringToColor(d.getDessinFin()));
    	Constants.setEtape(this.stringToColor(d.getDessinEtape()));
    	Constants.setMouvement(this.stringToColor(d.getDessinMouvement()));
    	Constants.setOrigine(this.stringToColor(d.getDessinOrigine()));
    	this.resetAfterRemove();
    	
    }
    
    private Color stringToColor(String c) {
    	
    	switch(c.toUpperCase()) {
    	case "ROUGE" : return Color.RED;
    	case "BLEU" : return Color.BLUE;
    	case "GRIS" : return Color.GRAY;
    	case "VERT" : return Color.GREEN;
    	case "NOIR" : return Color.BLACK;
    	default : return Color.BLACK;
    	
    	}
    	
    }
    
    
}
