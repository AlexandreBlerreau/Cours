package transforms;

import java.util.Optional;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

public class DialogRotation {

	private String x;
	private String y;
	private String a;


public void call() {
	
	Dialog<Pair<String, String>> dialog = new Dialog<>();
	
	dialog.setHeaderText("Configuration de la rotation");

	

	
	ButtonType validerButtonType = new ButtonType("Ajouter", ButtonData.OK_DONE);
	dialog.getDialogPane().getButtonTypes().addAll(validerButtonType, ButtonType.CANCEL);

	
	GridPane grid = new GridPane();
	grid.setHgap(10);
	grid.setVgap(10);
	grid.setPadding(new Insets(20, 150, 10, 10));

	
	TextField valueA = new TextField();
	valueA.setPromptText("ANGLE");
	TextField valueX = new TextField();
	valueX.setPromptText("X");
	TextField valueY = new TextField();
	valueY.setPromptText("Y");

	grid.add(new Label("Valeur Angle:"), 0, 0);
	grid.add(valueA, 1, 0);
	grid.add(new Label("Valeur X:"), 0, 1);
	grid.add(valueX, 1, 1);
	grid.add(new Label("Valeur Y:"), 0, 2);
	grid.add(valueY, 1, 2);

	
	dialog.getDialogPane().setContent(grid);

	
	Platform.runLater(() -> valueA.requestFocus());

	
	dialog.setResultConverter(dialogButton -> {
	    if (dialogButton == validerButtonType) {
	    	this.a = valueA.getText();
	    	this.x = valueX.getText();
			this.y = valueY.getText();
	        return new Pair<>(valueX.getText(), valueY.getText());
	    }
	    return null;
	});

		
		
	Optional<Pair<String, String>> result = dialog.showAndWait();

	result.ifPresent(xETy -> {
	    
	});
}

public String getX() {
	return x;
}

public String getY() {
	return y;
}
public String getA() {
	return a;
}
}
