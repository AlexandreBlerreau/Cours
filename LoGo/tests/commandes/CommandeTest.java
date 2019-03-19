package commandes;

import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import evaluateurs.EvaluateurScript;
import noeud.CommandeAllerA;
import noeud.CommandeAvant;
import noeud.CommandeCouleur;
import noeud.CommandeDroite;
import noeud.CommandeEpaisseur;
import noeud.CommandeGauche;
import noeud.CommandeLever;
import noeud.CommandePoser;
import noeud.NoeudAST;
import noeud.Script;
import projet.Couleurs;
import projet.Crayon;
import projet.MonTokenizer;

public class CommandeTest {

	@Test
	public void test() throws IOException, ParseException {
		CommandeLever c1 = new CommandeLever();
		CommandeAvant c2 = new CommandeAvant(50);
		List<NoeudAST> l = new ArrayList<>();
		l.add(c1);
		l.add(c2);
		Script c3 = new Script(l);
		MonTokenizer tk = new MonTokenizer(new InputStreamReader(new FileInputStream("ressources-test/test0")));
		NoeudAST n = tk.parseScript();
		
		assertEquals(n,c3);
		
	}

	@Test
	public void testAvant() throws IOException, ParseException{
		Crayon c = new Crayon(10,10);
		EvaluateurScript es = new EvaluateurScript(c);
		CommandeAvant c1 = new CommandeAvant(10);
		c1.accept(es.getCrayon());
	}
	
	@Test
	public void testDroite() throws IOException, ParseException{
		Crayon c = new Crayon(10,10);
		EvaluateurScript es = new EvaluateurScript(c);
		CommandeDroite c1 = new CommandeDroite(90);
		c1.accept(es.getCrayon());
	}
	
	@Test
	public void testGauche() throws IOException, ParseException{
		Crayon c = new Crayon(10,10);
		EvaluateurScript es = new EvaluateurScript(c);
		CommandeGauche c1 = new CommandeGauche(90);
		c1.accept(es.getCrayon());
	}
	
	@Test
	public void testAllerA() throws IOException, ParseException{
		Crayon c = new Crayon(10,10);
		EvaluateurScript es = new EvaluateurScript(c);
		CommandeAllerA c1 = new CommandeAllerA(10,50);
		c1.accept(es.getCrayon());
	}
	
	@Test
	public void testCouleur() throws IOException, ParseException{
		Crayon c = new Crayon(10,10);
		Couleurs col = Couleurs.ROSE;
		EvaluateurScript es = new EvaluateurScript(c);
		CommandeCouleur c1 = new CommandeCouleur(col);
		c1.accept(es.getCrayon());
	}
	
	@Test
	public void testEpaisseur() throws IOException, ParseException{
		Crayon c = new Crayon(10,10);
		EvaluateurScript es = new EvaluateurScript(c);
		CommandeEpaisseur c1 = new CommandeEpaisseur(10);
		c1.accept(es.getCrayon());
	}
	
	@Test
	public void testLever() throws IOException, ParseException{
		Crayon c = new Crayon(10,10);
		EvaluateurScript es = new EvaluateurScript(c);
		CommandeLever c1 = new CommandeLever();
		c1.accept(es.getCrayon());
	}
	
	@Test
	public void testPoser() throws IOException, ParseException{
		Crayon c = new Crayon(10,10);
		EvaluateurScript es = new EvaluateurScript(c);
		CommandePoser c1 = new CommandePoser();
		c1.accept(es.getCrayon());
	}
}
