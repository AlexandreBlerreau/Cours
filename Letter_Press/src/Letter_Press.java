import extensions.CSVFile;
class Letter_Press extends Program {

    final String[] csvFiles = getAllCSVFiles();
    boolean captureFini = false;
    boolean captureActif = false;
    Joueur joueur1 = new Joueur();
    Joueur joueur2 = new Joueur();
    Mot unMot = new Mot();

    void algorithm() {
	clearScreen();
	Plateau jeu = initialiser();
	
	// true = joueur1 | false = joueur2
	boolean changer = true;

	// mise a 0 des point pour les deux joueurs
	joueur1.points = 0; 
	joueur2.points = 0;
		
	String mot = "";
       

	println("Joueur 1 :");
	joueur1 = newJoueur();
	println("Joueur 2 :");
	joueur2 = newJoueur();
	clearScreen();

	println("Présentation des règles du jeu:" + "\n"
			+ "Premierement c'est un jeu avec un tableau de lettres, c'est lettres permette de former des mot selon vos choix." + "\n"
			+ "Ensuite les mots que vous pouvez construire sont tous ceux présent dans un dictionnaire francais, une fois qu'une lettre" + "\n"
			+ "est utiliser alors le joueur en question possede la lettre. Si un joueure possede les lettres qui se trouve autour d'elle" + "\n"
			+ "alors cette lettre devient une lettre capturer par le joueur. Enfin toute lettre que vous utiliser vous donne des points" + "\n"
			+ "sauf celle qui sont capturer par l'autre joueur");
	
	do {
	    println("\n");
	    println("---------------------------------------------------------");
	    print(tableauToString(jeu));
	    println("---------------------------------------------------------");
	    println("J1P = possédée par " + joueur1.nom +"   J2P = possédée par " + joueur2.nom);
			
	    do {
		
		do {	   
		mot = saisie(changer);
		}
		while(! indiceValide(mot));
		      	
		
		unMot = newMot(mot, jeu);
				
	    }
	    while (!rechercherCsv(loadCSV(csvFiles[0]),motToString(unMot)));

	    possede(changer,mot,jeu);

	    if (changer){
		joueur1.points = points(jeu,changer);
		println("Tu comptabilise " + joueur1.points + " points!");
	    }
	    
	    if (!changer){
		joueur2.points = points(jeu,changer);
		println("Tu comptabilise " + joueur2.points + " points!");
	    }
			
	    changer = !changer;
	    captureActif = false;
	}
		
	while(!jeuFini(jeu));


	if(joueur1.points > joueur2.points) {
		    
	    println(joueur1.nom +" à gagné !!!");
	    
	}
	else if ( joueur1.points < joueur2.points) {
		    
	    println(joueur2.nom +"  à gagné !!!");
	    
	}
	else {
	    println("Aucun joueur n'a gagné, Match Null !!!");
	}
    }

    
    

    void keyTypedInConsole(char key){
	// capture de touches

     	switch(key) {

	case (char)13:
	    clearLine();
	    captureFini = true;
	    captureActif = false;
	    enableKeyTypedInConsole(false);

     	}
    }
    


    String[] getAllCSVFiles() {
	// recuperation du fichier CSV
        String[] filenames = getAllFilesFromCurrentDirectory();
        String[] csvFiles = new String[length(filenames)];
        int idxCSV = 0;
        // On filtre les fichiers selon leur extension en
        // ne conservant que les fichiers ".csv"
        for (int i=0; i<length(filenames); i++) {
            String filename = filenames[i];
            String extension = "?";
            if (length(filename)>4) {
		extension = substring(filename, length
				      (filename)-3,length(filename));
            }
            if (equals(extension, "csv")) {
		csvFiles[idxCSV] = filename;
		idxCSV = idxCSV + 1;
            }
        }
        
        // On recopie dans un tableau faisant juste la bonne taille
        String[] result = new String[idxCSV-1];
        for (int i=0; i<length(result); i++) {
            result[i] = csvFiles[i];
        }
        return result;
    }


        
    String attribuer(CSVFile csv,int line, int column) {
	// placer dans une variable un partie definie du ficher CSV
	String var;
	var = getCell(csv, line, column);
   
	return var;
    }
    
   
    void testRechercherCsv(){
	assertTrue(rechercherCsv(loadCSV(csvFiles[0]),"cafe"));
	assertTrue(rechercherCsv(loadCSV(csvFiles[0]),"ordinateur"));
	assertFalse(rechercherCsv(loadCSV(csvFiles[0]),"computeur"));
	assertFalse(rechercherCsv(loadCSV(csvFiles[0]),"cofee"));
	
    }

    boolean rechercherCsv(CSVFile csv, String unMot){
	// recherche un mot donne en parametre dans le fichier CSV
	String motTester = "";
	for(int line = 0; line <rowCount(csv); line ++) {
	    for(int column = 0; column<columnCount(csv,line); column ++){
		motTester = attribuer(csv,line,column);
		if(motTester.equals(unMot)){
		    println("\n");
		    println("Tu as joué(e) " +"'"+ motTester+"'" + " bravo!");
		    return true;
		}
	    }    
	}
	println("\n");
	println("Ce mot n'est pas dans le dictionnaire :(");
	return false;
    }



    Plateau initialiser() {
	// initialisation du plateau de jeu
	Lettre a = new Lettre();
	Lettre e = new Lettre();
	Lettre i = new Lettre();
	Lettre o = new Lettre();
	Lettre u = new Lettre();
	
	a.caractere='a';
	a.indice =1;
	e.caractere='e';
	e.indice=9;
	i.caractere='i';
	i.indice=19;
	o.caractere='o';
	o.indice = 12;
	u.caractere ='u';
	u.indice = 13;
	    
	int dimension = 5;
	Plateau tab_car = new Plateau();
	tab_car.lettres = new Lettre[dimension][dimension];
	char car = '_';
	char vide = ' ';
	int idx =01;
	for (int ligne=0; ligne<length(tab_car.lettres, 1); ligne++) {
	    for (int colonne=0; colonne<length(tab_car.lettres, 2); colonne++) {
		tab_car.lettres[ligne][colonne] = aleaLettre();
		tab_car.lettres[ligne][colonne].indice = idx;
		idx++;
	    }
	    tab_car.lettres[0][0] = a;
	    tab_car.lettres[1][3] = e; 
	    tab_car.lettres[3][3] = i;
	    tab_car.lettres[2][1] = o;
	    tab_car.lettres[2][2] = u;
	}
	return tab_car;
    }
    


    String tableauToString(Plateau tab) {
	// converti un plateau de jeu en type 'string'
	String rep = "";
	for (int ligne = 0; ligne<length(tab.lettres, 1); ligne++) {
	    for (int colonne = 0; colonne<length(tab.lettres, 2); colonne++) {
		rep += lettreToString(tab.lettres[ligne][colonne]) + " ";
	    }
	    rep += "      ";
	    for (int colonne2 =0; colonne2<length(tab.lettres, 2); colonne2++) {
		if (tab.lettres[ligne][colonne2].indice < 10) {
		    rep +="0" + tab.lettres[ligne][colonne2].indice + " ";
		} else {
		    rep +=tab.lettres[ligne][colonne2].indice + " ";
		}
	    }
	    rep+= "      ";
	    
	    for (int colonne3 =0; colonne3<length(tab.lettres, 2); colonne3++) {
		if (tab.lettres[ligne][colonne3].indice < 10) {
		    rep += tab.lettres[ligne][colonne3].etat + " ";
		} else {
		    rep +=tab.lettres[ligne][colonne3].etat + " ";
		}
	    }
	    rep+= "\n"; 
	}
	return rep;
    }

    
    /*void testNewLettre() {
      println(newLettre().caractere);
      }*/

    Lettre aleaLettre() {
	// tire une lettre aleatoirement
	Lettre l = new Lettre();
	int idxLettre = 0;
	idxLettre = ((int)(random()*26)) + 'a';
	l.caractere = (char)idxLettre;
      
	return l;
    }

    void testLettreToString() {
	Lettre test = new Lettre();
	test.caractere = 'a';
	assertEquals(lettreToString(test),"a");
      }

    String lettreToString(Lettre l) {
	// converti le type lettre en type string
    	String lettre = "";
    	lettre = l.caractere + "";
    	return lettre;
    }

    void possede(boolean joueur, String mot, Plateau jeu){
	String car = "";
	for (int idx=0; idx<(length(mot)); idx+=2) {
	    car = substring(mot, idx, idx+2);
	    etatPossede(car,jeu,joueur);
	}
    }

	boolean etatPossede(String indiceLettre, Plateau jeu, boolean joueur){
	    int indice = stringToInt(indiceLettre);
	    for(int idxl = 0; idxl < length(jeu.lettres,1); idxl ++) {
		for(int idxc = 0; idxc < length(jeu.lettres,2); idxc ++) {

		    if(indice == jeu.lettres[idxl][idxc].indice){
			if(joueur){
			jeu.lettres[idxl][idxc].etat = "J1P";
		
			}
			else {
			    jeu.lettres[idxl][idxc].etat ="J2P";
			    
			}
			return true;
		    }
		}
	    }
	    return false;
	
    }

    int points(Plateau jeu, boolean joueur){
	int j1Pts = 0;
	int j2Pts = 0;

	if(joueur){
	    for(int idxl = 0; idxl < length(jeu.lettres,1); idxl ++){
		for(int idxc = 0; idxc < length(jeu.lettres,2); idxc ++){

		    if(jeu.lettres[idxl][idxc].etat.equals("J1P")){
			j1Pts = j1Pts + 1;
		    }
		}
	    }
	    return j1Pts;
	}

	if(!joueur){
	    for(int idxl = 0; idxl < length(jeu.lettres,1); idxl ++){
		for(int idxc = 0; idxc < length(jeu.lettres,2); idxc ++){

		    if(jeu.lettres[idxl][idxc].etat.equals("J2P")){
			j2Pts = j2Pts + 1;
		    }
		}
	    }
	    return j2Pts;
	}
	return -1;
    }
	    
	    
    void capture(Lettre l, boolean joueur){

    }

    void testIndiceValide() {
       assertTrue(indiceValide("1"));
       assertTrue(indiceValide("5"));
       assertTrue(indiceValide("10"));
       assertTrue(indiceValide("15"));
       assertTrue(indiceValide("20"));
       assertTrue(indiceValide("25"));
       assertFalse(indiceValide("a6"));
       assertFalse(indiceValide("2627zer999"));
       assertFalse(indiceValide("4dded/e38874"));
    }
    
    
       
    
    
    boolean indiceValide(String mot){
	// verifie que l indice sois bien un int
        boolean res = true;
	char chiffre;
	for (int idx=0; idx<length(mot); idx ++) {
	    chiffre= charAt(mot, idx);
	    if(!(chiffre >= '0' && chiffre <= '9')){
		res = false;
		return res;
	    }
	}
	//println(stringToInt(indice));   
        return res;
    }

    void testNomValide() {
      assertFalse(nomValide("a145z"));
      assertFalse(nomValide("145z"));
      assertFalse(nomValide("Alex"));
      assertTrue(nomValide("alex"));
      }

    boolean nomValide(String nom) {
	// verifie si le nom du joueur est valide
	boolean valide = false;
	char car;
	for (int i = 0; i<length(nom); i++) {
	    car = charAt(nom, i);
	    if(car >= 'a' && car <= 'z' && nom != joueur1.nom && nom != joueur2.nom) {
		
		valide = true;
	    } else {
		valide = false;
		return valide;
	    }
	}
	return valide;
    }

    Joueur newJoueur() {
	// initialise un nouveau joueur
	Joueur joueur = new Joueur();
	while (!nomValide(joueur.nom)) {
	    print("Entrez votre pseudo :");
	    joueur.nom = readString();
	}
	return joueur;
    }

    /*void testJeuFini() {
      assertFalse(jeuFini(initialiser()));
      }*/

    boolean jeuFini (Plateau plateau) {
	// verifie que la partie est terminee
	boolean fini = false; 
	for (int ligne = 0; ligne<length(plateau.lettres, 1); ligne++) {
	    for (int colonne = 0; colonne<length(plateau.lettres, 2); colonne++ ) {
		if(plateau.lettres[ligne][colonne].etat != "NUL") {
		    fini = true;
		} else {
		    fini = false;
		    return fini;
		}
	    }
	}
	return fini;
    }


    String motToString(Mot mot) {
	// converti un type mot en type string
	String res = "";
	for (int idx=0; idx<length(mot.lettres); idx++) {
	    res+= lettreToString(mot.lettres[idx]) + "";
	}
	return res;
    }

   

    Mot newMot(String mot, Plateau jeu) {
	// initialise un nouveau mot
	Mot word = new Mot();
	word.lettres = new Lettre[length(mot)/2];
	String car = "";
	for (int idx=0; idx<(length(mot)); idx+=2) {
	    car = substring(mot, idx, idx+2);
	    word.lettres[idx/2] = indiceToLettre(jeu, stringToInt(car));
	}
	word.estJouer = true;
	return word;
    }

    boolean motUtiliser(Mot mot) {
	// test si le mot a deja ete joue
	boolean valide = false;
	if(mot.estJouer = true) {
	    return true;
	}
	return valide;
    }


    String saisie(boolean joueur) {
	// systeme de saisie
	String mot = "";
	captureFini = false;
	captureActif = false;
		
		
	if (joueur) {
	    println("\n");
	    print(joueur1.nom +" veuillez choisir vos indices : ");
		       
	}
	else {
	    println("\n");
	    print(joueur2.nom + " veuillez choisir vos indices : ");
	}
				
	while(! captureFini ) { // capture de touche entrer
		    
	    if(! captureActif ){
		mot = readString();
		captureActif = true;
		print("Tapez 'entrée' pour valider et patientez...");
		enableKeyTypedInConsole(true);
		do {

		    delay(50);
			}
		
		while(captureActif);

		    
	    }	
	   
	    		   
	}	
		
		


	return mot;
    }

    

    void testLettreToChar() {
	Lettre test = new Lettre();
	test.caractere = 'a';
	assertEquals(lettreToChar(test),'a');
	}

    char lettreToChar(Lettre uneLettre){
	// converti un type lettre en type char
	char res = uneLettre.caractere;
	return res;	
    }


    Lettre indiceToLettre(Plateau plateau, int indice){
	// traduit un indice en lettre par rapport a un plateau de jeu
	Lettre res = new Lettre();
	int cpt = 0;
	for(int idxl = 0; idxl < length(plateau.lettres,1); idxl ++){
	    for(int idxc = 0; idxc < length(plateau.lettres,2); idxc ++){
		cpt = cpt + 1;
		if(indice == cpt) {
		    res.caractere = lettreToChar(plateau.lettres[idxl][idxc]);
		    res.utiliser = true;
		    return res;
		}
	    }
	}
	return res;
    }


    
}
