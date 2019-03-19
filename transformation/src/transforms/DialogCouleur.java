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

public class DialogCouleur {

	

	private String dessin;
	private String dessinFin;
	private String dessinEtape;
	private String dessinMouvement;
	private String dessinOrigine;


public void call() {
	
	Dialog<Pair<String, String>> dialog = new Dialog<>();
	
	dialog.setHeaderText("Configuration des couleurs\n(rouge,bleu,vert,gris,noir) -> Par defaut noir");

	

	
	ButtonType validerButtonType = new ButtonType("Ajouter", ButtonData.OK_DONE);
	dialog.getDialogPane().getButtonTypes().addAll(validerButtonType, ButtonType.CANCEL);

	
	GridPane grid = new GridPane();
	grid.setHgap(10);
	grid.setVgap(10);
	grid.setPadding(new Insets(20, 150, 10, 10));

	
	TextField valueDessin = new TextField();
	valueDessin.setPromptText("Dessin");
	TextField valueFin = new TextField();
	valueFin.setPromptText("Dessin fin");
	TextField valueEtapes = new TextField();
	valueEtapes.setPromptText("Etapes");
	TextField valueMouvements = new TextField();
	valueMouvements.setPromptText("Mouvements");
	TextField valueOrigine = new TextField();
	valueOrigine.setPromptText("Origine");

	grid.add(new Label("Dessin:"), 0, 0);
	grid.add(valueDessin, 1, 0);
	grid.add(new Label("Dessin fin:"), 0, 1);
	grid.add(valueFin, 1, 1);
	grid.add(new Label("Etapes:"), 0, 2);
	grid.add(valueEtapes, 1, 2);
	grid.add(new Label("Mouvement:"), 0, 3);
	grid.add(valueMouvements, 1, 3);
	grid.add(new Label("Origine:"), 0, 4);
	grid.add(valueOrigine, 1, 4);

	
	dialog.getDialogPane().setContent(grid);

	
	Platform.runLater(() -> valueDessin.requestFocus());

	
	dialog.setResultConverter(dialogButton -> {
	    if (dialogButton == validerButtonType) {
	    	this.dessin = valueDessin.getText();
	    	this.dessinFin = valueFin.getText();
			this.dessinEtape = valueEtapes.getText();
			this.dessinMouvement = valueMouvements.getText();
			this.dessinOrigine = valueOrigine.getText();
	        return new Pair<>(valueDessin.getText(), valueFin.getText());
	    }
	    return null;
	});

		
		
	Optional<Pair<String, String>> result = dialog.showAndWait();

	result.ifPresent(xETy -> {
	    
	});
}


public String getDessin() {
	return dessin;
}


public String getDessinFin() {
	return dessinFin;
}


public String getDessinEtape() {
	return dessinEtape;
}


public String getDessinMouvement() {
	return dessinMouvement;
}


public String getDessinOrigine() {
	return dessinOrigine;
}


}
