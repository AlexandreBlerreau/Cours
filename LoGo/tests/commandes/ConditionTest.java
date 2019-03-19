package commandes;

import java.io.IOException;
import java.text.ParseException;

import org.junit.Test;

import condition.ConditionEstLeve;
import condition.ConditionEt;
import condition.ConditionNon;
import condition.ConditionOu;
import condition.ConditionPosCouleur;
import condition.ConditionPosX;
import condition.ConditionPosY;
import evaluateurs.EvaluateurCondition;
import projet.Crayon;

public class ConditionTest {
	
	@Test
	public void estLeveTest() throws IOException, ParseException {
		ConditionEstLeve c = new ConditionEstLeve();
		Crayon cr = new Crayon(10, 10);
		EvaluateurCondition ec = new EvaluateurCondition(cr);
		c.accept(ec);
	}
	
	@Test
	public void etTest() throws IOException, ParseException {
		ConditionEt c = new ConditionEt();
		Crayon cr = new Crayon(10, 10);
		EvaluateurCondition ec = new EvaluateurCondition(cr);
		c.accept(ec);
	}
	
	@Test
	public void nonTest() throws IOException, ParseException {
		ConditionNon c = new ConditionNon();
		Crayon cr = new Crayon(10, 10);
		EvaluateurCondition ec = new EvaluateurCondition(cr);
		c.accept(ec);
	}
	
	@Test
	public void ouTest() throws IOException, ParseException {
		ConditionOu c = new ConditionOu();
		Crayon cr = new Crayon(10, 10);
		EvaluateurCondition ec = new EvaluateurCondition(cr);
		c.accept(ec);
	}
	
	@Test
	public void posCouleurTest() throws IOException, ParseException {
		ConditionPosCouleur c = new ConditionPosCouleur();
		Crayon cr = new Crayon(10, 10);
		EvaluateurCondition ec = new EvaluateurCondition(cr);
		c.accept(ec);
	}
	
	@Test
	public void posXTest() throws IOException, ParseException {
		ConditionPosX c = new ConditionPosX(null, null, 0);
		Crayon cr = new Crayon(10, 10);
		EvaluateurCondition ec = new EvaluateurCondition(cr);
		c.accept(ec);
	}

	@Test
	public void posYTest() throws IOException, ParseException {
		ConditionPosY c = new ConditionPosY(null, null, 0);
		Crayon cr = new Crayon(10, 10);
		EvaluateurCondition ec = new EvaluateurCondition(cr);
		c.accept(ec);
	}
}
