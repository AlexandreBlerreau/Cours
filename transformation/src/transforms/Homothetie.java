package transforms;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Transition;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.WritableValue;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Transform;
import javafx.util.Duration;
import transforms.Transformation;

public class Homothetie extends Scale implements Transformation{

	double scale;
	double pivotXx;
	double pivotYy;
	
	public Homothetie(double scale) {
		super(scale,0.0,0.0);
	}
	
	public Homothetie(double scale,double pivotX,double pivotY) {
		super(scale,pivotX,pivotY);
		this.scale = scale;
		this.pivotXx = pivotX;
		this.pivotYy = pivotY;
	}
	
	public double getScale() {
		return scale;
	}

	public double getPivotXx() {
		return pivotXx;
	}

	public double getPivotYy() {
		return pivotYy;
	}

	public boolean estIdentité() {
		return this.getX() == 1.0 && this.getY() == 1.0;
	}
	
	public List<KeyFrame> getKeyFrame(Duration dernier){
		ArrayList<KeyFrame>list = new ArrayList<>();
		
		if (dernier != Duration.seconds(0.0)) {
            list.add(new KeyFrame(Duration.seconds(0.0), new KeyValue[]{new KeyValue(this.xProperty(),1.0), new KeyValue(this.yProperty(),1.0)}));
        }
        list.add(new KeyFrame(dernier, new KeyValue[]{new KeyValue(this.xProperty(),1.0), new KeyValue(this.yProperty(),1.0)}));
        list.add(new KeyFrame(dernier.add(Duration.seconds(2.0)), new KeyValue[]{new KeyValue(this.xProperty(),this.getX()), new KeyValue(this.yProperty(), this.getY())}));
        return list;
    }
		
		
	}
	
