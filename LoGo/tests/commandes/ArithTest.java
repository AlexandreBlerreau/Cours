package commandes;

import java.io.IOException;
import java.text.ParseException;

import org.junit.Test;

import evaluateurs.EvaluateurExpressionArithmetique;
import exprarith.Divise;
import exprarith.Entier;
import exprarith.Fois;
import exprarith.Moins;
import exprarith.Plus;

public class ArithTest {

	@Test
	public void diviseTest() throws IOException, ParseException {
		Entier e = new Entier(6);
		Entier e1 = new Entier(2);
		EvaluateurExpressionArithmetique eea = new EvaluateurExpressionArithmetique();
		Divise d = new Divise(e,e1);
		d.accept(eea);
	}
	
	@Test
	public void foisTest() throws IOException, ParseException {
		Entier e = new Entier(6);
		Entier e1 = new Entier(2);
		EvaluateurExpressionArithmetique eea = new EvaluateurExpressionArithmetique();
		Fois d = new Fois(e,e1);
		d.accept(eea);
	}
	
	@Test
	public void moinsTest() throws IOException, ParseException {
		Entier e = new Entier(6);
		Entier e1 = new Entier(2);
		EvaluateurExpressionArithmetique eea = new EvaluateurExpressionArithmetique();
		Moins d = new Moins(e,e1);
		d.accept(eea);
	}
	
	@Test
	public void PlusTest() throws IOException, ParseException {
		Entier e = new Entier(6);
		Entier e1 = new Entier(2);
		EvaluateurExpressionArithmetique eea = new EvaluateurExpressionArithmetique();
		Plus d = new Plus(e,e1);
		d.accept(eea);
	}
}
