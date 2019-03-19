package projet;

import java.util.Observable;
import java.util.Observer;

import javafx.scene.control.Button;

public class MyViewButton extends Button implements Observer{

	@Override
	public void update(Observable o, Object arg) {
		Crayon cr = (Crayon) o;
		this.setStyle(cr.getColor().getDescription());
	}

}
