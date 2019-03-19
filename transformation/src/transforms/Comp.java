package transforms;

public class Comp {

	static final int[] transCompositionRow;

    static {
        transCompositionRow = new int[Row.values().length];
        try {
            Comp.transCompositionRow[Row.X.ordinal()] = 1;
        }
        catch (Exception e) {
            
        }
        try {
            Comp.transCompositionRow[Row.Y.ordinal()] = 2;
        }
        catch (Exception e) {
           
        }
        try {
            Comp.transCompositionRow[Row.T.ordinal()] = 3;
        }
        catch (Exception e) {
            
        }
    }
}
