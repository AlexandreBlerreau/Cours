package projet;

import java.util.Observable;
import java.util.Observer;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class MyViewCanvas extends Canvas implements Observer{

	GraphicsContext gc;
	
	public MyViewCanvas(int x, int y){
		super(x,y);
		gc=this.getGraphicsContext2D();
	}
	
	
	@Override
	public void update(Observable o, Object arg) {
		Crayon cr = (Crayon) o;
		//gc.strokeLine(x1, y1, cr.getX(), cr.getY());
	}

}
