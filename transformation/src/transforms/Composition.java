package transforms;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.tools.Tool;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.transform.Transform;
import javafx.util.Duration;
import transforms.Composition;
import transforms.Constants;
import transforms.DrawContext;
import transforms.Maison;
import transforms.Transformation;
import transforms.TransformationException;


public class Composition {
	
    private ArrayList<Transformation> transforms = new ArrayList();
    private ArrayList<Transform> cumulées = new ArrayList();
    VBox b = new VBox();
    File file;
    FileOutputStream fos = null;
    
   

    public Composition()  {
    	 file = new File("Matrice.txt"); 
    	
		try {
			fos = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
		}
    	PrintStream ps = new PrintStream(fos);
    	System.setOut(ps);
    }

    public void clear() {
        this.transforms.clear();
    }

    public void add(Transformation transformation,ScrollPane p) {
    	
        this.transforms.add(transformation);
        Transform last;
        if(this.size() <= 1) {
        	last = Constants.IDENTITY;
        }else {
        	last = this.cumulées.get(this.cumulées.size() -1);
        }
        
        Transform cumul = transformation.getTransform().createConcatenation(last);
        this.cumulées.add(cumul);
        this.updateVisuel(p);
    }

    public int size() {
        return this.transforms.size();
    }

    private void checkIndex(int index) throws TransformationException {
        if (index < 0) {
            throw new TransformationException();
        }
        if (index >= this.transforms.size()) {
            throw new TransformationException();
        }
    }

    public Transform getAtomique(int index) throws TransformationException {
        this.checkIndex(index);
        return this.transforms.get(index).getTransform();
    }

    public Transform getCumulated(int index) throws TransformationException {
        this.checkIndex(index);
        return this.cumulées.get(index);
    }

    private String showRow(Transform transformation, Row row) {
        if (transformation == null) {
            return "                  ";
        }
        switch (Comp.transCompositionRow[row.ordinal()]) {
            case 1: {
                return String.format("%5.2f %5.2f %+6.1f", transformation.getMxx(), transformation.getMxy(), transformation.getTx());
            }
            case 2: {
                return String.format("%5.2f %5.2f %+6.1f", transformation.getMxy(), transformation.getMyy(), transformation.getTy());
            }
            case 3: {
                return String.format("%5.2f %5.2f %+6.1f", 0.0, 0.0, 1.0);
            }
        }
        return null;
    }

    private void displayMobile(ObservableList<Double> liste, Row row, Transform t) throws NullPointerException, IndexOutOfBoundsException {
    	
        backFor: for (int i = 0; i < liste.size(); i += 2) {
            Point2D point = t.transform(new Point2D((liste.get(i)).doubleValue(), ((Double)liste.get(i + 1)).doubleValue()));
            
            
            switch (Comp.transCompositionRow[row.ordinal()]) {
                case 1: {
                	
                	
                	
                    System.out.print(String.format("%6.2f ", point.getX()));
                    
                    continue backFor;
                }
                case 2: {
                	
                    System.out.print(String.format("%6.2f ", point.getY()));
                    break;
                }
            }
        }
    	
    }

    void displayMatrix(Transform transform1, Transform transform2, Maison m) throws IOException, NullPointerException, IndexOutOfBoundsException  {
    	ObservableList<Double> liste = m.getPoints();
        if (transform1 == null) {
        	
            System.out.println("                   : ----- ----- ------");
        } else {
        	
            System.out.println("----- ----- ------ : ----- ----- ------");
        }
        for (Row row : Row.values()) {
        	
            System.out.print(this.showRow(transform1, row) + " : " + this.showRow(transform2, row));
            
           
          
            
            System.out.print(" => ");
            
            System.out.println();
            this.displayMobile(liste, row, transform2);
           
        }
        
        
        
        
        
    }

    private Color color(double value) {
        if (value == 1.0) {
            return Color.BLUE;
        }
        return new Color(value, value, value, 1.0);
    }

    private Color color(int idx) {
        if (idx == 0) {
            return Constants.DESSIN;
        }
        if (idx == this.transforms.size()) {
            return Constants.DESSIN_FIN;
        }
        
        return Constants.DESSIN_ETAPE;
    }

    public void drawStep(int index, DrawContext drawContext) throws NullPointerException, ArrayIndexOutOfBoundsException {
        Maison m = new Maison();
        m.setStroke(this.color(index));
        if (index > 0) {
            m.getTransforms().add(this.cumulées.get(index - 1));
        }
        drawContext.drawAdd(m);
    }

    public void applyAll(int index, Node node) {
        Transform transf = this.transforms.get(index).getTransform();
        Transform cumul = this.cumulées.get(index);
        node.getTransforms().add(cumul);
    }

    private KeyFrame drawHouseKeyFrame(Duration last, int index, DrawContext drawContext) {
        return new KeyFrame(last, e -> this.drawStep(index, drawContext), new KeyValue[0]);
    }

    public void animate(Timeline timeline, Node mobile, DrawContext drawContext) {
        Duration last = Duration.seconds(0.0);
        for (int i = 0; i < this.size(); ++i) {
            Transformation transformation = this.transforms.get(i);
            Transform transform = transformation.getTransform();
            if (transformation.estIdentité()) continue;
            mobile.getTransforms().add(1,transformation.getTransform());
            timeline.getKeyFrames().addAll(transformation.getKeyFrame(last));
            last = last.add(Duration.seconds(2.0));
            timeline.getKeyFrames().add(this.drawHouseKeyFrame(last, i + 1, drawContext));
        }
    }
    
    public void remove(ImageView target,ScrollPane p) throws IOException {
    	ArrayList<Transformation> tmp = new ArrayList<Transformation>();
    	
    	int index;
    	index = b.getChildren().indexOf(target);
    	
    	if(index > 0) {
    		index = index/2;
    	}
    	
    	transforms.remove(index);
    	
    	for(Transformation ta : transforms) {
    		tmp.add(ta);
    	}
    
    	
    	
    	
    	transforms.clear();
    	cumulées.clear();
    	
    	for(Transformation ta : tmp) {
    		
    		this.add(ta, p);
    	}
    	clearFile();
    	this.updateVisuel(p);
    	
    	
    	
    }
    
    
    public void updateVisuel(ScrollPane p) {
    	
    	b.getChildren().clear();
    	b.setSpacing(10);
    	for(Transformation ta : transforms) {
    		if(ta instanceof Translation) {
    			ImageView img = new ImageView(new Image("file:ressource/translation.png",169,169,true,true));
    			b.getChildren().add(img);
    			Translation tmp = (Translation)ta;
    			b.getChildren().add(new Label("Translation X= "+ tmp.getX() + " Y= " + tmp.getY()+ ""));
    		}
    		if(ta instanceof Homothetie) {
    			b.getChildren().add(new ImageView(new Image("file:ressource/homothetie.png",169,169,true,true)));
    			Homothetie tmp = (Homothetie) ta;
    			Label l = new Label("Homothétie R= "+tmp.getScale() + "X= " + tmp.getPivotXx()+ "Y= "+tmp.getPivotYy());
    			l.setFont(new Font(11.4));
    			b.getChildren().add(l);
    		}
    		if(ta instanceof Rotation) {
    			b.getChildren().add(new ImageView(new Image("file:ressource/rotation.png",169,169,true,true)));
    			Rotation tmp = (Rotation)ta;
    			Label l = new Label("Rotation A= "+ tmp.getAnglee() + " X= " + tmp.getPivotXx()+ " Y= "+ tmp.getPivotYy());
    			l.setFont(new Font(11.4));
    			b.getChildren().add(l);
    		}
    	}
    	p.setContent(b);
    	
    }

    
    public void clearFile() throws IOException {
    	FileOutputStream writer = new FileOutputStream("Matrice.txt");
    	writer.write(("").getBytes());
    	writer.close();
    }
}

	
