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
import javafx.scene.transform.Translate;
import javafx.util.Duration;
import transforms.Transformation;

public class Translation extends Translate implements Transformation{

	public Translation(double x, double y) {
        super(x, y);
    }

    @Override
    public boolean estIdentité() {
        return this.getX() == 0.0 && this.getY() == 0.0;
    }

    @Override
    public List<KeyFrame> getKeyFrame(Duration d) {
        ArrayList<KeyFrame> list = new ArrayList<KeyFrame>();
        if (d != Duration.seconds(0.0)) {
            list.add(new KeyFrame(Duration.seconds(0.0), new KeyValue[]{new KeyValue(this.xProperty(), 0.0), new KeyValue(this.yProperty(), 0.0)}));
        }
        list.add(new KeyFrame(d, new KeyValue[]{new KeyValue(this.xProperty(), 0.0), new KeyValue(this.yProperty(), 0.0)}));
        list.add(new KeyFrame(d.add(Duration.seconds(2.0)), new KeyValue[]{new KeyValue(this.xProperty(), this.getX()), new KeyValue(this.yProperty(), this.getY())}));
        return list;
    }
}

	
