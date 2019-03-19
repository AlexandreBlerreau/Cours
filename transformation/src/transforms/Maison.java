package transforms;

import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polyline;
import javafx.scene.transform.Transform;
import transforms.Constants;

class Maison extends Group {

	private static final double X0 = 0.0;
    private static final double X1 = 1.0;
    private static final double Y0 = 0.0;
    private static final double Y1 = 1.0;
    private static final double MILLIEU_X = 0.5;
    private static final double Y2 = 1.5;
    private static final double D0 = 0.5;
    private static final double D1 = 0.75;
    private static final double DY = 0.75;
    private static final double CENTRE_X = 0.5;
    private static final double CENTRE_Y = 0.75;
    private static final Point2D CENTRE = new Point2D(0.5, 0.75);
    public Polyline pl = new Polyline();
    

    Maison() {
        this.pl.getPoints().addAll(Constants.MATRIX_DESSIN);
        this.pl.setStroke(Constants.DESSIN);
        this.pl.setStrokeWidth(0.025);
        this.getChildren().add(this.pl);
        this.getTransforms().add(Constants.SCREEN_TRANSFORM);
    }
    

    public Point2D getCenter() {
        return CENTRE;
    }

    public ObservableList<Double> getPoints() {
        return this.pl.getPoints();
    }

    void setStroke(Color color) {
        this.pl.setStroke(color);
    }

    public String toString() {
        String res = "[ ";
        res = res + String.format("( %5.2f , %5.2f @ %5.2f )", this.pl.getPoints().get(0) + this.getTranslateX(), this.pl.getPoints().get(1) + this.getTranslateY(), this.getRotate());
        res = res + ", ";
        res = res + String.format("( %5.2f , %5.2f @ %5.2f )", this.pl.getPoints().get(2) + this.getTranslateX(), this.pl.getPoints().get(3) + this.getTranslateY(), this.getRotate());
        res = res + " ]";
        return res;
    }
    
    public void setPl(Polyline pl) {
    	this.getChildren().clear();
    	this.getTransforms().clear();
    	this.pl.getPoints().clear();
    	this.pl = pl;
    	
    	this.getChildren().add(this.pl);
    	this.pl.setStroke(Constants.DESSIN);
        this.pl.setStrokeWidth(0.025);
    	this.getTransforms().add(Constants.SCREEN_TRANSFORM);
    }
    
}
