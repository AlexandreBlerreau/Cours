package transforms;



import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Transform;
import javafx.scene.transform.Translate;

public class Constants {
	static final double ZERO_X = 0.0;
    static final double MIN_X = -10.0;
    static final double MAX_X = 10.0;
    static final double INC_X = 1.0;
    static final double ZERO_Y = 0.0;
    static final double MIN_Y = -10.0;
    static final double MAX_Y = 10.0;
    static final double INC_Y = 1.0;
    static final double SCALE = 35.0;
    private static final double OFFSET_X = 350.0;
    private static final double OFFSET_Y = 350.0;
    static final Transform SCREEN_SCALE = new Scale(35.0, -35.0);
    static final Transform SCREEN_OFFSET = new Translate(350.0, 350.0);
    static final Transform SCREEN_TRANSFORM = SCREEN_OFFSET.createConcatenation(SCREEN_SCALE);
    static final Point2D SCREEN_ORIGIN = SCREEN_TRANSFORM.transform(new Point2D(0.0, 0.0));
    public static Transform IDENTITY = new Translate();
    
    public static Color DESSIN = Color.BLUE;
    public static Color DESSIN_FIN = Color.BLUE;
    public static Color DESSIN_ETAPE = Color.BLACK;
    public static Color DESSIN_MOUVEMENT = Color.RED;
    public static Color DESSIN_ORIGINE = Color.GRAY;
    

    public static Double[] MATRIX_DESSIN = {0.0, 0.0, 0.0, 1.0, 0.5, 1.5, 1.0, 1.0, 1.0, 0.0, 0.5, 0.0, 0.5, 0.75, 0.75, 0.75, 0.75, 0.0, 0.0, 0.0};
    
    
    public static void setMatrixDessin(Double[] matrix) {
        MATRIX_DESSIN = matrix;
    }
    
    public static void setDessin(Color c) {
    	DESSIN = c;
    }
    
    public static void setFin(Color c) {
    	DESSIN_FIN = c;
    }
    
    public static void setEtape(Color c) {
    	DESSIN_ETAPE = c;
    }
    
    public static void setMouvement(Color c) {
    	DESSIN_MOUVEMENT = c;
    }
    
    public static void setOrigine(Color c) {
    	DESSIN_ORIGINE = c;
    }
    
}
