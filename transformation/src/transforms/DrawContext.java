package transforms;

import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;


public class DrawContext {

	    private Pane pane;
	    private ArrayList<Node> node = new ArrayList();

	    DrawContext(Pane pane) {
	        this.pane = pane;
	    }

	    void drawClear() {
	        for (Node node : this.node) {
	            this.pane.getChildren().remove(node);
	        }
	        this.node.clear();
	    }

	    void drawAdd(Node node) {
	    	
	    	this.node.add(node);
	    	
	        this.pane.getChildren().add(node);
	    }
}
