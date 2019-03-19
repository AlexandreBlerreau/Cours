package transforms;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Transition;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.WritableValue;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Transform;
import javafx.util.Duration;
import transforms.Transformation;

public class Rotation extends Rotate implements Transformation{

	double angle;
	double pivotXx;
	double pivotYy;
	
	public double getAnglee() {
		return angle;
	}

	public double getPivotXx() {
		return pivotXx;
	}

	public double getPivotYy() {
		return pivotYy;
	}

	public Rotation(double angle, double cx, double cy) {
        this.setAngle(angle);
        this.setPivotX(cx);
        this.setPivotY(cy);
        this.angle = angle;
        this.pivotXx = cx;
        this.pivotYy = cy;
    }

    @Override
    public boolean estIdentité() {
        return this.getAngle() == 0.0;
    }

    @Override
    public List<KeyFrame> getKeyFrame(Duration d) {
        ArrayList<KeyFrame> list = new ArrayList<KeyFrame>();
        if (d != Duration.seconds(0.0)) {
            list.add(new KeyFrame(Duration.seconds(0.0), new KeyValue[]{new KeyValue(this.angleProperty(), 0.0)}));
        }
        list.add(new KeyFrame(d, new KeyValue[]{new KeyValue(this.angleProperty(), 0.0)}));
        list.add(new KeyFrame(d.add(Duration.seconds(2.0)), new KeyValue[]{new KeyValue(this.angleProperty(),this.getAngle())}));
        return list;
    }

	

	

}
