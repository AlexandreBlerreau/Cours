package noeud;

import java.util.List;

import evaluateurs.EvaluateurScript;
import projet.Crayon;
/**
 * Implementation des courbes de Bezier.
 * @author Guillaume Renaud
 *
 */
public class CourbeBezier implements NoeudAST{
	
	private double param1;
	private double param2;
	private double param3;
	private double param4;
	private double param5;
	private double param6;
	
	EvaluateurScript es;

	/**
	 * Constructeur
	 * 
	 * @param param1
	 * Premier parametre
	 * @param param2
	 * Deuxieme parametre
	 * @param param3
	 * Troisieme parametre
	 * @param param4
	 * Quatrieme parametre
	 * @param param5
	 * Cinquieme parametre
	 * @param param6
	 * Sixieme parametre
	 * 
	 */
	public CourbeBezier(double param1, double param2, double param3, double param4, double param5, double param6) {
		this.param1=param1;
		this.param2=param2;
		this.param3=param3;
		this.param4=param4;
		this.param5=param5;
		this.param6=param6;
	}

	@Override
	public void accept(Crayon cr) {
		es = new EvaluateurScript(cr);
		es.visitBezier(this);
	}

	public double getParam1() {
		return param1;
	}

	public double getParam2() {
		return param2;
	}

	public double getParam3() {
		return param3;
	}

	public double getParam4() {
		return param4;
	}

	public double getParam5() {
		return param5;
	}

	public double getParam6() {
		return param6;
	}
	
}