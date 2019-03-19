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

public class DialogMatrice {


			private String matrice;
	
		
		
		
		public void call() {
			
			Dialog<Pair<String, String>> dialog = new Dialog<>();
			
			dialog.setHeaderText("Configuration de la matrice à dessiner");

			

			
			ButtonType validerButtonType = new ButtonType("Ajouter", ButtonData.OK_DONE);
			dialog.getDialogPane().getButtonTypes().addAll(validerButtonType, ButtonType.CANCEL);

			
			GridPane grid = new GridPane();
			grid.setHgap(10);
			grid.setVgap(10);
			grid.setPadding(new Insets(20, 150, 10, 10));

			TextField matrix = new TextField();
			matrix.setMinWidth(750);
			matrix.setPromptText("point,point,point,point...:");
			

			grid.add(new Label("Matrice:"), 0, 0);
			grid.add(matrix, 1, 0);
			

			
			dialog.getDialogPane().setContent(grid);

			
			Platform.runLater(() -> matrix.requestFocus());

			
			dialog.setResultConverter(dialogButton -> {
			    if (dialogButton == validerButtonType) {
			    	this.matrice = matrix.getText();
					
			        return new Pair<>(matrix.getText(), null);
			    }
			    return null;
			});

				
				
			Optional<Pair<String, String>> result = dialog.showAndWait();

			result.ifPresent(xETy -> {
			    
			});
		}

		public String getMatrice() {
			return matrice;
		}


}
