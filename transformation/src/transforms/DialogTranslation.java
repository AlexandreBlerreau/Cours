package transforms;

import java.util.Optional;



import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

public class DialogTranslation {

		private String x;
		private String y;
	
	public DialogTranslation() {
		
	}
	
	public void call() {
		
		Dialog<Pair<String, String>> dialog = new Dialog<>();
		
		dialog.setHeaderText("Configuration de la translation");

		

		
		ButtonType validerButtonType = new ButtonType("Ajouter", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(validerButtonType, ButtonType.CANCEL);

		
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		TextField valueX = new TextField();
		valueX.setPromptText("X");
		TextField valueY = new TextField();
		valueY.setPromptText("Y");

		grid.add(new Label("Valeur X:"), 0, 0);
		grid.add(valueX, 1, 0);
		grid.add(new Label("Valeur Y:"), 0, 1);
		grid.add(valueY, 1, 1);

		
		dialog.getDialogPane().setContent(grid);

		
		Platform.runLater(() -> valueX.requestFocus());

		
		dialog.setResultConverter(dialogButton -> {
		    if (dialogButton == validerButtonType) {
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
}
