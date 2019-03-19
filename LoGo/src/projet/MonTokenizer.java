/*****
 * @author Roheart Florian, Lewandoski Baptiste, Renaud Guillaume
 * Parser d'Instructions
 */

package projet;

import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import noeud.Fonction;
import condition.Condition;
import design.RedAlert;
import exprarith.ExpressionArithmetique;
import exprarith.Fois;
import exprarith.Plus;
import exprarith.Entier;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import jdk.nashorn.internal.runtime.ParserException;
import noeud.CommandeAllerA;
import noeud.CommandeAvant;
import noeud.CommandeCouleur;
import noeud.CommandeDroite;
import noeud.CommandeEpaisseur;
import noeud.CommandeGauche;
import noeud.CommandeLever;
import noeud.CommandePoser;
import noeud.CourbeBezier;
import noeud.NoeudAST;
import noeud.NoeudRepeter;
import noeud.NoeudTantQue;
import noeud.OpCompare;
import noeud.OpExprAri;
import noeud.Script;
import noeud.Variables;

public class MonTokenizer {

	@FXML
	Canvas cv;

	private StreamTokenizer st;

	private ArrayList<NoeudAST> list = new ArrayList<NoeudAST>();
	private ArrayList<NoeudAST> listRepeter = new ArrayList<NoeudAST>();
	private Stack<String> listverif = new Stack<String>();
	public static Variables vars = new Variables();
	public static HashMap<String,Fonction> listFonction = new HashMap<>();
	public int nbFonction = 0;


	/*****
	 * @param r Reader
	 * Constructeur.
	 */
	public MonTokenizer(Reader r) {
		this.st = new StreamTokenizer(r);
		st.wordChars('$', '$');
		st.wordChars('(', '(');
		st.wordChars(')', ')');
		st.wordChars(',', ',');
	} 

	/*****
	 * @return s Script
	 * @throws IOException
	 * @throws ParseException
	 * Verifie si les tokens coupes par le Tokenizer sont corrects.
	 */
	public Script parseScript() throws IOException, ParseException {
		while (st.nextToken() != StreamTokenizer.TT_EOF ) {
			String nomFonction="";
			ActionsScript a;
			Couleurs col = null;
			Script scriptrepeter = null;
			Script scripttantque = null;
			Script scriptsialors = null;
			Script scriptsisinon = null;
			ExpressionArithmetique ea = null;
			Condition conditiontantque = null;
			Condition conditionsi = null;
			int nb = 0;
			int nb1 = 0;
			double p1=0;
			double p2=0;
			double p3=0;
			double p4=0;
			double p5=0;
			double p6=0;
			String token = st.sval;
			token = this.isOk(token);
			a = this.isAction();
			switch(a){
			case COULEUR : 
				st.nextToken();
				col = this.parseCouleur();
				listverif.push("Cmd");
				break;
			case REPETER:
				st.nextToken();
				ea = this.parseExprAri();
				scriptrepeter = parseRepeter();
				listverif.push("Cmd");
				break;
			case TANTQUE:
				st.nextToken();
				conditiontantque = this.parseCondition();
				scripttantque = parseTantQue();
				listverif.push("Cmd");
				break;
			case SI:
				st.nextToken();
				conditionsi = this.parseCondition();
				scriptsialors = parseSi();
				scriptsisinon = parseSi();
				listverif.push("Cmd");
				break;
			case AVANT:
			case DROITE:
			case GAUCHE:
			case BAS:
			case HAUT:
			case EPAISSEUR: 	
				st.nextToken();
				nb = this.parseInt();
				listverif.push("Cmd");
				break;
			case SCRIPT:
				listverif.push("Script");
				break;
			case FIN:
				listverif.push("Fin");
				break;
			case ALLERA:
				st.nextToken();
				nb = this.parseInt();
				st.nextToken();
				nb1 = this.parseInt();
				listverif.push("Cmd");
				break;
			case BEZIER:
				st.nextToken();
				p1 = this.parseInt();
				st.nextToken();
				p2 = this.parseInt();
				st.nextToken();
				p3 = this.parseInt();
				st.nextToken();
				p4 = this.parseInt();
				st.nextToken();
				p5 = this.parseInt();
				st.nextToken();
				p6 = this.parseInt();
				listverif.push("Cmd");
				break;
			default:
				listverif.push("Cmd");
				break;
			}
			if(st.ttype != StreamTokenizer.TT_EOL && st.ttype != StreamTokenizer.TT_EOF && st.ttype != StreamTokenizer.TT_WORD && st.ttype != StreamTokenizer.TT_NUMBER ){
				vars.reset();
				listFonction.clear();
				throw new ParseException("", -1);
			}
			switch(a){
			case REPETER:
				list.add(new NoeudRepeter(ea,scriptrepeter));
			case COULEUR:
				list.add(new CommandeCouleur(col));
				break;
			case TANTQUE:
				list.add(new NoeudTantQue(conditiontantque, scripttantque));
			case AVANT:
				list.add(new CommandeAvant(nb));
				break;
			case DROITE:
				if(nb < 0){
					list.add(new CommandeGauche(nb*-1));
				}
				else{
					list.add(new CommandeDroite(nb));
				}
				break;
			case GAUCHE:
				if(nb < 0){
					list.add(new CommandeDroite(nb*-1));
				}
				else{
					list.add(new CommandeGauche(nb));
				}
				break;
			case ALLERA:
				if(nb < 0 || nb1 < 0 ){
					RedAlert  redAlert = new RedAlert();
					redAlert.colorError(st.lineno(),MyView.getTa());
					Alert erreur = new Alert(AlertType.ERROR);
					erreur.setContentText("Ligne " + st.lineno() + ": ALLERA: L'une des valeurs n'est pas valide\n"
							+ "Utilisez: ALLERA [valeur] [valeur]");
					erreur.setTitle("Erreur LoGo");
					erreur.setHeaderText(null);
					erreur.showAndWait();
					vars.reset();
					listFonction.clear();
					throw new ParseException("", -1);
				}
				else{
					list.add(new CommandeAllerA(nb,nb1));
				}
				break;
			case BEZIER:
				if(p1 < 0 || p2 < 0 || p3 < 0 || p4 < 0 || p5 < 0 || p6 < 0){
					RedAlert  redAlert = new RedAlert();
					redAlert.colorError(st.lineno(),MyView.getTa());
					Alert erreur = new Alert(AlertType.ERROR);
					erreur.setContentText("Ligne " + st.lineno() + ": BEZIER: L'une des valeurs n'est pas valide\n"
							+ "Utilisez: BEZIER [valeur1] [valeur2] [valeur3] [valeur4] [valeur5] [valeur6]");
					erreur.setTitle("Erreur LoGo");
					erreur.setHeaderText(null);
					erreur.showAndWait();
					vars.reset();
					listFonction.clear();
					throw new ParseException("", -1);
				}
				else{
					list.add(new CourbeBezier(p1,p2,p3,p4,p5,p6));
					p1=0;
					p2=0;
					p3=0;
					p4=0;
					p5=0;
					p6=0;
				}
				break;
			case EPAISSEUR:
				list.add(new CommandeEpaisseur(nb));
				break;
			case LEVER:
				list.add(new CommandeLever());
				break;
			case POSER:
				list.add(new CommandePoser());
				break;
			case AIDE:
				MyView.tuto.show();
				break;
			case VAR:
				String nom = "";
				st.nextToken();
				nom = st.sval;
				if(vars.existe(nom)) {
					RedAlert  redAlert = new RedAlert();
					redAlert.colorError(st.lineno(),MyView.getTa());
					Alert erreur = new Alert(AlertType.ERROR);
					erreur.setContentText("Ligne " + st.lineno() + ": VAR: impossible de déclarer deux fois la même variable");
					erreur.setTitle("Erreur LoGo");
					erreur.setHeaderText(null);
					erreur.showAndWait();
					vars.reset();
					listFonction.clear();
					throw new ParseException("", -1);	
				}	
				break;
			case SOIT:
				st.nextToken();
				String nomVar = st.sval;
				st.nextToken();
				st.nextToken();
				String aVerif = "";
				if(st.sval == null) {
					int value = (int) st.nval;
					aVerif = value + "";
				}
				else {
					aVerif = st.sval;
				}
				if(! vars.isValideType(aVerif)){
					RedAlert  redAlert = new RedAlert();
					redAlert.colorError(st.lineno(),MyView.getTa());
					Alert erreur = new Alert(AlertType.ERROR);
					erreur.setContentText("Ligne " + st.lineno() + ": SOIT: affectation non conforme\nUtilisez SOIT [nom] = [(boolean,integer)valeur]");
					erreur.setTitle("Erreur LoGo");
					erreur.setHeaderText(null);
					erreur.showAndWait();
					vars.reset();
					listFonction.clear();
					throw new ParseException("", -1);
				}
				else {
					vars.set(nomVar, aVerif);
				}
				break;
				
				case FONCTION:
				
				
				Fonction tmp = new Fonction();
				st.nextToken();
				
				
				// enregistrement de la fonction
				if(!listFonction.containsKey(st.sval)) {
					nomFonction = st.sval;
				listFonction.put(st.sval, tmp);
				nbFonction =1;
				}
				else {
					RedAlert  redAlert = new RedAlert();
					redAlert.colorError(st.lineno(),MyView.getTa());
					Alert erreur = new Alert(AlertType.ERROR);
					erreur.setContentText("Ligne " + st.lineno() + ": FONCTION : impossible de déclarer 2 fois la même fonction");
					erreur.setTitle("Erreur LoGo");
					erreur.setHeaderText(null);
					erreur.showAndWait();
					vars.reset();
					listFonction.clear();
					throw new ParseException("",-1);
				}
				
				// des arguments ??
				
				
				st.nextToken();
				if(st.sval.equals("()")) {
					System.out.println("no args");
					
				}
				else if (st.sval.length() > 2 && st.sval.startsWith("(",0) && st.sval.endsWith(")")) {
					System.out.println("passé");
					int nbArgs = Integer.valueOf(st.sval.substring(1,st.sval.length() -1));
					System.out.println("nb=" + nbArgs);
					Fonction f = (Fonction) listFonction.get(nomFonction);
					for(int i = 0; i < nbArgs; i ++) {
						f.getArguments().getList().put("$" + (i+1) , "0");
						System.out.println("add");
					}
				
					
				}
				else {
					RedAlert  redAlert = new RedAlert();
					redAlert.colorError(st.lineno(),MyView.getTa());
					Alert erreur = new Alert(AlertType.ERROR);
					erreur.setContentText("Ligne " + st.lineno() + ": FONCTION : Erreur de syntaxe dans la signature de la fonction");
					erreur.setTitle("Erreur LoGo");
					erreur.setHeaderText(null);
					erreur.showAndWait();
					vars.reset();
					listFonction.clear();
					throw new ParseException("",-1);
				}
				
				Fonction f = (Fonction) listFonction.get(nomFonction);
				f.setScript(parseFonction(nomFonction));
				
				for(NoeudAST n : f.getScript().getList()) {
					System.out.println(n.toString());
				}
				
				break;
				
			
			case RUN:
				int nbArgs = 0;
				
				st.nextToken();
				System.out.println(st.sval);
				Fonction fonc = (Fonction) listFonction.get(st.sval);
				int nbArg = 0;
				if(st.sval.equals("()")) {
					System.out.println("no args");
					if(nbArgs < fonc.getArguments().nbArgs()) {
						RedAlert  redAlert = new RedAlert();
						redAlert.colorError(st.lineno(),MyView.getTa());
						Alert erreur = new Alert(AlertType.ERROR);
						erreur.setContentText("Ligne " + st.lineno() + ": FONCTION : Erreur de syntaxe dans la signature de la fonction");
						erreur.setTitle("Erreur LoGo");
						erreur.setHeaderText(null);
						erreur.showAndWait();
						vars.reset();
						listFonction.clear();
						throw new ParseException("",-1);	
					}
			
		
				}
				else if (st.sval.length() > 2 && st.sval.startsWith("(",0) && st.sval.endsWith(")")) {
					
					int cpt = 0;
					for(String s : st.sval.split(",")) {
						String p = s;
						if(s.charAt(0) == '(') {
							s = s.substring(1);
						
						}
						if(s.contains(")")) {
							s = s.substring(0, s.length() -1);
							System.out.println(s);
						}
						
						cpt ++;
						System.out.println(fonc.getScript().getList().get(st.lineno() -2));
						fonc.getArguments().getList().remove("$" + cpt);
						fonc.getArguments().getList().put("$" + cpt, s);
						
					}
					
					
					if(cpt*2 < fonc.getArguments().nbArgs() || cpt*2 > fonc.getArguments().nbArgs()) {
						RedAlert  redAlert = new RedAlert();
						redAlert.colorError(st.lineno(),MyView.getTa());
						Alert erreur = new Alert(AlertType.ERROR);
						erreur.setContentText("Ligne " + st.lineno() + ": FONCTION : Erreur de syntaxe dans la signature de la fonction");
						erreur.setTitle("Erreur LoGo");
						erreur.setHeaderText(null);
						erreur.showAndWait();
						vars.reset();
						listFonction.clear();
						throw new ParseException("",-1);	
					}
					
					
				}
				else {
					RedAlert  redAlert = new RedAlert();
					redAlert.colorError(st.lineno(),MyView.getTa());
					Alert erreur = new Alert(AlertType.ERROR);
					erreur.setContentText("Ligne " + st.lineno() + ": FONCTION : Erreur de syntaxe dans la signature de la fonction");
					erreur.setTitle("Erreur LoGo");
					erreur.setHeaderText(null);
					erreur.showAndWait();
					vars.reset();
					listFonction.clear();
					throw new ParseException("",-1);
				}
				
				for(NoeudAST n : fonc.getScript().getList()) {
					list.add(n);
				}
			
				break;
				
			default:
				break;
			}
		}
		verifScriptFin(listverif);
		return new Script(list);
	}

	/*****
	 * @return s Script
	 * @throws IOException
	 * @throws ParseException
	 * Verifie si les tokens coupes par le Tokenizer sont corrects pour un noeudRepeter.
	 */
	public Script parseRepeter() throws ParseException, IOException {
		while (st.nextToken() != StreamTokenizer.TT_EOF ) {
			ActionsScript a;
			Couleurs col = null;
			Script scriptrepeter = null;
			ExpressionArithmetique ea = null;
			int nb = 0;
			int nb1 = 0;
			String token = st.sval;
			token = this.isOk(token);
			a = this.isAction();
			switch(a){
			case COULEUR : 
				st.nextToken();
				col = this.parseCouleur();
				break;
			case REPETER:
				st.nextToken();
				ea = this.parseExprAri();
				scriptrepeter = parseRepeter();
				break;
			case AVANT:
			case DROITE:
			case GAUCHE:
			case BAS:
			case HAUT:
			case EPAISSEUR: 
				st.nextToken();
				nb = this.parseInt();
				break;
			case SCRIPT:
				break;
			case FIN:
				return new Script(listRepeter);
			case ALLERA:
				st.nextToken();
				nb = this.parseInt();
				st.nextToken();
				nb1 = this.parseInt();
				break;
			default:
				break;
			}
			if(st.ttype != StreamTokenizer.TT_EOL && st.ttype != StreamTokenizer.TT_EOF && st.ttype != StreamTokenizer.TT_WORD && st.ttype != StreamTokenizer.TT_NUMBER){
				vars.reset();
				throw new ParseException("", -1);
			}
			switch(a){
			case REPETER:
				listRepeter.add(new NoeudRepeter(ea,scriptrepeter));
			case COULEUR:
				listRepeter.add(new CommandeCouleur(col));
				break;
			case AVANT:
				listRepeter.add(new CommandeAvant(nb));
				break;
			case DROITE:
				if(nb < 0){
					listRepeter.add(new CommandeGauche(nb*-1));
				}
				else{
					listRepeter.add(new CommandeDroite(nb));
				}
				break;
			case GAUCHE:
				if(nb < 0){
					listRepeter.add(new CommandeDroite(nb*-1));
				}
				else{
					listRepeter.add(new CommandeGauche(nb));
				}
				break;
			case ALLERA:
				if(nb < 0 || nb1 < 0 ){
					RedAlert  redAlert = new RedAlert();
					redAlert.colorError(st.lineno(),MyView.getTa());
					Alert erreur = new Alert(AlertType.ERROR);
					erreur.setContentText("Ligne " + st.lineno() + ": ALLERA: L'une des valeurs n'est pas valide\n"
							+ "Utilisez: ALLERA [valeur] [valeur]");
					erreur.setTitle("Erreur LoGo");
					erreur.setHeaderText(null);
					erreur.showAndWait();
					vars.reset();
					throw new ParseException("", -1);
				}
				else{
					listRepeter.add(new CommandeAllerA(nb,nb1));
				}
				break;
			case EPAISSEUR:
				listRepeter.add(new CommandeEpaisseur(nb));
				break;
			case LEVER:
				listRepeter.add(new CommandeLever());
				break;
			case POSER:
				listRepeter.add(new CommandePoser());
				break;
			case AIDE:
				MyView.tuto.show();
				break;
			default:
				break;
			}
		}
		return new Script(list);
	}

	/*****
	 * @param listverif Stack<String>
	 * @throws ParseException
	 * Verifie si la pile remplie dans le tokenizer plus haut est correcte.
	 */
	private void verifScriptFin(Stack<String> listverif) throws ParseException {
		int endrfin = listverif.search("Fin");
		int endrscript = listverif.search("Script");
		int nbfin = 0;
		int nbscript = 0;
		if ( (endrfin != -1 && endrscript == -1) || (endrfin == -1 && endrscript != -1) || (endrfin == -1 && endrscript == -1) || (endrscript < endrfin) ) {
			RedAlert  redAlert = new RedAlert();
			redAlert.colorError(-42,MyView.getTa());
			Alert erreur = new Alert(AlertType.ERROR);
			erreur.setContentText("Probleme au niveau de votre Script et Fin");
			erreur.setTitle("Erreur LoGo");
			erreur.setHeaderText(null);
			erreur.showAndWait();
			vars.reset();
			throw new ParseException("", -1);
		}
		else {
			listverif.pop();
			nbfin++;
		}
		while ( !listverif.isEmpty() ) {
			int newendrscript = listverif.search("Script");
			int newendrfin = listverif.search("Fin");
			if ( listverif.search("Script") != -1 ) {
				String str = listverif.pop();
				if ( str.equals("Fin") ) {
					nbfin++;
				}
				if ( str.equals("Script") ) {
					nbscript++;
				}
			}
			if ( newendrfin == -1 ) {
				while (listverif.search("Script") != -1) {
					String str = listverif.pop();
					if ( str.equals("Fin") ) {
						nbfin++;
					}
					if ( str.equals("Script") ) {
						nbscript++;
					}
				}
			}
			if ( newendrscript == endrscript && newendrfin != endrfin ) {
				RedAlert  redAlert = new RedAlert();
				redAlert.colorError(-42,MyView.getTa());
				Alert erreur = new Alert(AlertType.ERROR);
				erreur.setContentText("Probleme au niveau du nombre de Script/Fin");
				erreur.setTitle("Erreur LoGo");
				erreur.setHeaderText(null);
				erreur.showAndWait();
				vars.reset();
				throw new ParseException("", -1);
			}
		}
		if ( nbfin != nbscript ) {
			RedAlert  redAlert = new RedAlert();
			redAlert.colorError(-42,MyView.getTa());
			Alert erreur = new Alert(AlertType.ERROR);
			erreur.setContentText("Nombre de Script et Fin inegal");
			erreur.setTitle("Erreur LoGo");
			erreur.setHeaderText(null);
			erreur.showAndWait();
			vars.reset();
			throw new ParseException("", -1);
		}
	}


	/*****
	 * @return ea ExpressionArithmetique
	 * @throws IOException
	 * @throws ParserException
	 * Parse une Expression Arithmetique
	 */
	public ExpressionArithmetique parseExprAri() throws IOException, ParserException {
		ExpressionArithmetique t = parseT();
		ExpressionArithmetique plus = parsePlus();
		if (plus == null) {
			return t; // un nombre
		}
		else {
			return new Plus(t,plus);
		}
	}

	/*****
	 * @return ea ExpressionArithmetique
	 * @throws IOException
	 * @throws ParserException
	 * Suite de parsage de l'Expression Arithmetique
	 */
	public ExpressionArithmetique parseT() throws ParserException, IOException {
		ExpressionArithmetique f = parseF();
		ExpressionArithmetique fois = parseFois();
		if (fois == null) {
			return f; // un nombre
		}
		else {
			return new Fois(f,fois);
		}
	}

	/*****
	 * @return ea ExpressionArithmetique
	 * @throws IOException
	 * @throws ParserException
	 * Suite de parsage de l'Expression Arithmetique
	 */
	public ExpressionArithmetique parsePlus () throws ParserException, IOException {
		if(st.ttype == '+') {
			consommer('+');
			parseT();
			return parsePlus();
		}
		else {
			return null;
		}
	}

	/*****
	 * @return ea ExpressionArithmetique
	 * @throws IOException
	 * @throws ParserException
	 * Suite de parsage de l'Expression Arithmetique
	 */
	public ExpressionArithmetique parseF () throws ParserException, IOException {
		if ( st.ttype == StreamTokenizer.TT_NUMBER ) {
			return parseEntier();
		}
		else {
			consommer('('); 
			ExpressionArithmetique e = parseExprAri(); 
			consommer(')'); 
			return e;
		}
	}

	/*****
	 * @return ea ExpressionArithmetique
	 * @throws IOException
	 * @throws ParserException
	 * Suite de parsage de l'Expression Arithmetique
	 */
	public ExpressionArithmetique parseFois () throws ParserException, IOException {
		if(st.ttype == '*') {
			consommer('*');
			parseF();
			return parseFois();
		}
		else {
			return null;
		}
	}

	/*****
	 * @return e Entier
	 * @throws IOException
	 * @throws ParserException
	 * Verifie si on a bien un entier.
	 */
	public Entier parseEntier() throws ParserException, IOException {
		if(st.ttype == StreamTokenizer.TT_NUMBER) {
			Entier res =  new Entier((int) st.nval);
			st.nextToken();
			return res;
		}
		else {
			RedAlert  redAlert = new RedAlert();
			redAlert.colorError(st.lineno(),MyView.getTa());
			Alert erreur = new Alert(AlertType.ERROR);
			erreur.setContentText("Entier Invalide " + st.sval + " "+ st.nval);
			erreur.setTitle("Erreur LoGo");
			erreur.setHeaderText(null);
			erreur.showAndWait();
			throw new ParserException("Erreur");
		}
	}

	/*****
	 * @param type Int
	 * @throws ParserException
	 * @throws IOException
	 * Detruit le token.
	 */
	public void consommer(int type) throws ParserException, IOException {
		if(st.ttype == type) {
			st.nextToken();
		}
		else {
			throw new ParserException(""+ st.lineno()+" : " + type + " attendu");
		}
	}

	/*****
	 * @return c Condition
	 * @throws IOException
	 * @throws ParseException
	 * Est cense parser une condition
	 */
	public Condition parseCondition() throws IOException, ParseException {
		String token = null;
		OpCompare oc = null;
		Condition c = null;
		return null;
	}

	/*****
	 * @return s Script
	 * @throws IOException
	 * @throws ParseException
	 * Parse un Tant Que
	 */
	public Script parseTantQue() throws IOException, ParseException {
		return null;
	}

	/*****
	 * @return s Script
	 * @throws IOException
	 * @throws ParseException
	 * Paser un Si
	 */
	public Script parseSi() throws IOException, ParseException {
		return null;
	}

	/*****
	 * @return c Couleurs
	 * @throws IOException
	 * @throws ParseException
	 * Verifie si on a bien une couleur.
	 */
	public Couleurs parseCouleur() throws IOException, ParseException {
		String token = st.sval.toUpperCase();
		Couleurs col = null;
		try{
			col = Couleurs.valueOf(Couleurs.class, token);
		} 
		catch(IllegalArgumentException e){
			RedAlert  redAlert = new RedAlert();
			redAlert.colorError(st.lineno(),MyView.getTa());
			Alert erreur = new Alert(AlertType.ERROR);
			erreur.setContentText("Ligne " + st.lineno() + ": La couleur n'est pas valide");
			erreur.setTitle("Erreur LoGo");
			erreur.setHeaderText(null);
			erreur.showAndWait();
			vars.reset();
			throw new ParseException("", -1);
		}
		try{
			token = st.sval.toUpperCase();
		} 
		catch(NullPointerException e){
			RedAlert  redAlert = new RedAlert();
			redAlert.colorError(st.lineno(),MyView.getTa());
			Alert erreur = new Alert(AlertType.ERROR);
			erreur.setContentText("Ligne " + st.lineno() + ": La couleur n'est pas valide");
			erreur.setTitle("Erreur LoGo");
			erreur.setHeaderText(null);
			erreur.showAndWait();
			vars.reset();
			throw new ParseException("", -1);
		}
		return col;
	}

	public int parseInt() throws IOException, ParseException {
		if(st.ttype != StreamTokenizer.TT_NUMBER && st.sval.charAt(0) == '$'){
			if(vars.existe(st.sval.substring(1))) {
				if(!vars.isBooleanType(vars.get(st.sval.substring(1)))) {

					int nb = Integer.valueOf(vars.get(st.sval.substring(1)));
					return nb;	
				}
				else {
					RedAlert  redAlert = new RedAlert();
					redAlert.colorError(st.lineno(),MyView.getTa());
					Alert erreur = new Alert(AlertType.ERROR);
					erreur.setContentText("Ligne " + st.lineno() + ": La variable est de type boolean, le type attendu est un entier");
					erreur.setTitle("Erreur LoGo");
					erreur.setHeaderText(null);
					erreur.showAndWait();
					vars.reset();
					throw new ParseException("",-1);
				}
			}
			else {
				RedAlert  redAlert = new RedAlert();
				redAlert.colorError(st.lineno(),MyView.getTa());
				Alert erreur = new Alert(AlertType.ERROR);
				erreur.setContentText("Ligne " + st.lineno() + ": La variable est inconnue");
				erreur.setTitle("Erreur LoGo");
				erreur.setHeaderText(null);
				erreur.showAndWait();
				vars.reset();
				throw new ParseException("",-1);
			}
		} else if(st.ttype != StreamTokenizer.TT_NUMBER){
			RedAlert  redAlert = new RedAlert();
			redAlert.colorError(st.lineno(),MyView.getTa());
			Alert erreur = new Alert(AlertType.ERROR);
			erreur.setContentText("Ligne " + st.lineno() + ": La valeur n'est pas valide");
			erreur.setTitle("Erreur LoGo");
			erreur.setHeaderText(null);
			erreur.showAndWait();
			vars.reset();
			throw new ParseException("", -1);
		}  else {
			int nb = (int) st.nval;
			return nb;
		}
	}

	/*****
	 * @param token String
	 * @return s String
	 * @throws IOException
	 * @throws ParseException
	 * Verifie la conformite du token.
	 */
	public String isOk(String token) throws IOException, ParseException {
		String retour;
		try{
			if(token.equals("=")) {
				retour = "=";
			}
			else if(token.charAt(0) == '$') {
				retour = "$" + token.substring(1);
			}
			else{
				retour = token.toUpperCase();
			}
		}
		catch(NullPointerException e){
			Alert erreur = new Alert(AlertType.ERROR);
			erreur.setContentText("Rentrez une action valide (qui commence par une lettre...) "+st.sval+" "+st.nval);
			erreur.setTitle("Erreur LoGo");
			erreur.setHeaderText(null);
			erreur.showAndWait();
			vars.reset();
			throw new ParseException("", -1);
		}
		return retour;
	}

	/*****
	 * @return as ActionsScript
	 * @throws IOException
	 * @throws ParseException
	 * Verifie s'il s'agit bien d'une action.
	 */
	public ActionsScript isAction() throws IOException, ParseException {
		String token = st.sval.toUpperCase();
		ActionsScript a = null;
		try{
			a = ActionsScript.valueOf(ActionsScript.class, token);
			token=st.sval;
		} catch(IllegalArgumentException e){	
			RedAlert  redAlert = new RedAlert();
			redAlert.colorError(st.lineno(),MyView.getTa());
			Alert erreur = new Alert(AlertType.ERROR);
			erreur.setContentText("Ligne " + st.lineno() + ": L'action n'est pas valide");
			erreur.setTitle("Erreur LoGo");
			erreur.setHeaderText(null);
			erreur.showAndWait();
			vars.reset();
			throw new ParseException("", -1);
		}
		return a;
	}
	
	public int parseIntFonction(String nomF) throws IOException, ParseException {
		Fonction f = (Fonction) listFonction.get(nomF);

		
		if(st.ttype != StreamTokenizer.TT_NUMBER && st.sval.charAt(0) == '$'){
				
					if(vars.existe(st.sval.substring(1))) {
						if(!vars.isBooleanType(vars.get(st.sval.substring(1)))) {
							
							int nb = Integer.valueOf(vars.get(st.sval.substring(1)));
							return nb;	
						}

						else {
							
							RedAlert  redAlert = new RedAlert();
							redAlert.colorError(st.lineno(),MyView.getTa());
							Alert erreur = new Alert(AlertType.ERROR);
							erreur.setContentText("Ligne " + st.lineno() + ": La variable est de type boolean, le type attendu est un entier");
							erreur.setTitle("Erreur LoGo");
							erreur.setHeaderText(null);
							erreur.showAndWait();
							vars.reset();
							listFonction.clear();
							throw new ParseException("",-1);
							
						}
					
					}
					
					else if(f.getArguments().getList().containsKey(st.sval)) {
						
						int nb =Integer.valueOf(f.getArguments().get(st.sval));
						System.out.println("nb=" + nb);
						return nb;
						
					}
					else {
						System.out.println(f.getArguments().getList().containsKey(st.sval));
						System.out.println(st.sval);
						RedAlert  redAlert = new RedAlert();
						redAlert.colorError(st.lineno(),MyView.getTa());
						Alert erreur = new Alert(AlertType.ERROR);
						erreur.setContentText("Ligne " + st.lineno() + ": La variable est inconnue parseIntFONC");
						erreur.setTitle("Erreur LoGo");
						erreur.setHeaderText(null);
						erreur.showAndWait();
						vars.reset();
						listFonction.clear();
						throw new ParseException("",-1);
					}
		
				} else if(st.ttype != StreamTokenizer.TT_NUMBER){

		if(st.ttype != StreamTokenizer.TT_NUMBER && st.sval.charAt(0) == '$'){
			if(vars.existe(st.sval.substring(1))) {
				if(!vars.isBooleanType(vars.get(st.sval.substring(1)))) {

					int nb = Integer.valueOf(vars.get(st.sval.substring(1)));
					return nb;	
				}
				else {
					RedAlert  redAlert = new RedAlert();
					redAlert.colorError(st.lineno(),MyView.getTa());
					Alert erreur = new Alert(AlertType.ERROR);
					erreur.setContentText("Ligne " + st.lineno() + ": La variable est de type boolean, le type attendu est un entier");
					erreur.setTitle("Erreur LoGo");
					erreur.setHeaderText(null);
					erreur.showAndWait();
					vars.reset();
					throw new ParseException("",-1);
				}
			}
			else {
				RedAlert  redAlert = new RedAlert();
				redAlert.colorError(st.lineno(),MyView.getTa());
				Alert erreur = new Alert(AlertType.ERROR);
				erreur.setContentText("Ligne " + st.lineno() + ": La variable est inconnue");
				erreur.setTitle("Erreur LoGo");
				erreur.setHeaderText(null);
				erreur.showAndWait();
				vars.reset();
				throw new ParseException("",-1);
			}
		} else if(st.ttype != StreamTokenizer.TT_NUMBER){

			RedAlert  redAlert = new RedAlert();
			redAlert.colorError(st.lineno(),MyView.getTa());
			Alert erreur = new Alert(AlertType.ERROR);
			erreur.setContentText("Ligne " + st.lineno() + ": La valeur n'est pas valide");
			erreur.setTitle("Erreur LoGo");
			erreur.setHeaderText(null);
			erreur.showAndWait();
			vars.reset();
			listFonction.clear();
			throw new ParseException("", -1);

		}  

			}
			int nb = (int) st.nval;
			return nb;
	
	}

		public Script parseFonction(String nomFonction) throws IOException, ParseException, NullPointerException {


		Fonction fonc = (Fonction) listFonction.get(nomFonction);
		
		ArrayList<NoeudAST> listtmp = new ArrayList<NoeudAST>();
		Stack <String> listVerifFonction = new Stack<String>();
		
		
		do {
			st.nextToken();
			ActionsScript a;
			Couleurs col = null;
			Script scriptrepeter = null;
			Script scripttantque = null;
			Script scriptsialors = null;
			Script scriptsisinon = null;
			ExpressionArithmetique ea = null;
			Condition conditiontantque = null;
			Condition conditionsi = null;
			int nb = 0;
			int nb1 = 0;
			String token = st.sval;
			token = this.isOk(token);
			a = this.isAction();
			switch(a){
			case COULEUR : 
				st.nextToken();
				col = this.parseCouleur();
				listVerifFonction.push("Cmd");
				break;
			case REPETER:
				st.nextToken();
				ea = this.parseExprAri();
				scriptrepeter = parseRepeter();
				listVerifFonction.push("Cmd");
				break;
			case TANTQUE:
				st.nextToken();
				conditiontantque = this.parseCondition();
				scripttantque = parseTantQue();
				listVerifFonction.push("Cmd");
				break;
			case SI:
				st.nextToken();
				conditionsi = this.parseCondition();
				scriptsialors = parseSi();
				scriptsisinon = parseSi();
				listVerifFonction.push("Cmd");
				break;
			case AVANT:
			case DROITE:
			case GAUCHE:
			case BAS:
			case HAUT:
			case EPAISSEUR: 	
				st.nextToken();
				nb = this.parseIntFonction(nomFonction);
				listVerifFonction.push("Cmd");
				break;
			case SCRIPT:
				listVerifFonction.push("Script");
				break;
			case FIN:
				listVerifFonction.push("Fin");
				break;
			case ALLERA:
				st.nextToken();
				nb = this.parseIntFonction(nomFonction);
				st.nextToken();
				nb1 = this.parseIntFonction(nomFonction);
				listVerifFonction.push("Cmd");
				break;
			case FINFONCTION:
				listVerifFonction.push("FINFONCTION");
				break;
				
			default:
				listVerifFonction.push("Cmd");
				break;
			}
			if(st.ttype != StreamTokenizer.TT_EOL && st.ttype != StreamTokenizer.TT_EOF && st.ttype != StreamTokenizer.TT_WORD && st.ttype != StreamTokenizer.TT_NUMBER ){
				vars.reset();
				listFonction.clear();
				throw new ParseException("", -1);
			}
			switch(a){
			case REPETER:
				listtmp.add(new NoeudRepeter(ea,scriptrepeter));
			case COULEUR:
				listtmp.add(new CommandeCouleur(col));
				break;
			case TANTQUE:
				listtmp.add(new NoeudTantQue(conditiontantque, scripttantque));
			case AVANT:
				listtmp.add(new CommandeAvant(nb));
				break;
			case DROITE:
				if(nb < 0){
					listtmp.add(new CommandeGauche(nb*-1));
				}
				else{
					listtmp.add(new CommandeDroite(nb));
				}
				break;
			case GAUCHE:
				if(nb < 0){
					listtmp.add(new CommandeDroite(nb*-1));
				}
				else{
					listtmp.add(new CommandeGauche(nb));
				}

				break;
			case ALLERA:
				if(nb < 0 || nb1 < 0 ){
					RedAlert  redAlert = new RedAlert();
					redAlert.colorError(st.lineno(),MyView.getTa());
					Alert erreur = new Alert(AlertType.ERROR);
					erreur.setContentText("Ligne " + st.lineno() + ": ALLERA: L'une des valeurs n'est pas valide\n"
							+ "Utilisez: ALLERA [valeur] [valeur]");
					erreur.setTitle("Erreur LoGo");
					erreur.setHeaderText(null);
					erreur.showAndWait();
					vars.reset();
					listFonction.clear();
					throw new ParseException("", -1);
				}
				else{
					listtmp.add(new CommandeAllerA(nb,nb1));
				}
				break;
			case EPAISSEUR:
				listtmp.add(new CommandeEpaisseur(nb));
				break;
			case LEVER:
				listtmp.add(new CommandeLever());
				break;
			case POSER:
				listtmp.add(new CommandePoser());
				break;
			case AIDE:
				MyView.tuto.show();
				break;
				
			case FINFONCTION:
				break;
				
			case VAR:
				String nom = "";
				st.nextToken();
				nom = st.sval;
		
				
				
				if(vars.existe(nom)) {
					RedAlert  redAlert = new RedAlert();
					redAlert.colorError(st.lineno(),MyView.getTa());
					Alert erreur = new Alert(AlertType.ERROR);
					erreur.setContentText("Ligne " + st.lineno() + ": VAR: impossible de déclarer deux fois la même variable");
					erreur.setTitle("Erreur LoGo");
					erreur.setHeaderText(null);
					erreur.showAndWait();
					vars.reset();
					listFonction.clear();
					throw new ParseException("", -1);
					
				}
				
				break;
				
			case SOIT:
				st.nextToken();
				String nomVar = st.sval;
				st.nextToken();
				st.nextToken();
				
				String aVerif = "";
				
				if(st.sval == null) {
					int value = (int) st.nval;
					aVerif = value + "";
				}
				else {
					aVerif = st.sval;
					
				}

				if(! vars.isValideType(aVerif)){
					RedAlert  redAlert = new RedAlert();
					redAlert.colorError(st.lineno(),MyView.getTa());
					Alert erreur = new Alert(AlertType.ERROR);
					erreur.setContentText("Ligne " + st.lineno() + ": SOIT: affectation non conforme\nUtilisez SOIT [nom] = [(boolean,integer)valeur]");
					erreur.setTitle("Erreur LoGo");
					erreur.setHeaderText(null);
					erreur.showAndWait();
					vars.reset();
					listFonction.clear();
					throw new ParseException("", -1);
				}
				else {
						vars.set(nomVar, aVerif);
						
				}
				break;
					
			default:
				break;
			}
			if(st.sval == null) {
				st.sval = st.nval +"";
			}
		}while(!st.sval.equals("FINFONCTION"));
		
			verifFonctionFin(listVerifFonction);
			return new Script(listtmp);
		}
		

		
	private void verifFonctionFin(Stack<String> listverifF) throws ParseException {
		int endrfin = listverifF.search("FINFONCTION");
		System.out.println(endrfin);
		int nbfin = 0;
		if (nbFonction < nbfin || endrfin == -1){
			RedAlert  redAlert = new RedAlert();
			redAlert.colorError(-42,MyView.getTa());
			Alert erreur = new Alert(AlertType.ERROR);
			erreur.setContentText("Impossible de savoir ou ce termine la fonction");
			erreur.setTitle("Erreur LoGo");
			erreur.setHeaderText(null);
			erreur.showAndWait();
			vars.reset();
			listFonction.clear();
			throw new ParseException("", -1);
		}

		else {
			nbfin = listverifF.search("FINFONCTION");
			System.out.println(nbfin);
		}
		
		
		if ( nbfin != nbFonction ) {
			System.out.println(nbfin);
		}
	}


}
