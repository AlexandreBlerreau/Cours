import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;
import java.util.*;

@WebServlet("/menu")
public class Menu extends HttpServlet{

	private ArrayList<Integer> traitement;
	public void service( HttpServletRequest req, HttpServletResponse res )throws  IOException {
		Connection conn = null;
        String dataBase = "jdbc:sqlite:../bdd";

		HttpSession session = req.getSession();
		PrintWriter out = res.getWriter();

		try{
			Class.forName("org.sqlite.JDBC");
		} catch(Exception e){
			e.printStackTrace();
		}

		

		try{
			int tmp = (int)session.getAttribute("key");
			

      		// on verifie la session du client pour savoir si il peut acceder a cette page
			if(tmp == 42 || tmp == 408){

				conn = DriverManager.getConnection(dataBase);
      			
				out.println("<HTML><header>");
				out.println("<link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css' integrity='sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO' crossorigin='anonymous'>");
				out.println("<link rel='stylesheet' type='text/css' href='menustyle.css'/>");
				out.println("</header><body>");
				


				// affichage du profil de l'utilisateur 

				Cookie[] cookies = req.getCookies();
				String idUser = null;
				for(int i=0; i < cookies.length; i++) {
					Cookie MonCookie = cookies[i];
					if (MonCookie.getName().equals("user")) {
						idUser = cookies[i].getValue();
					}
				}
				// recupereration des information de l'utilisateur courant
				String query = "Select * from utilisateurs where iduser='"+Integer.parseInt(idUser)+"'";
				Statement stid = null;
				ResultSet rsid = null;

				stid = conn.createStatement();
         		rsid = stid.executeQuery(query);

         		int iduser = rsid.getInt("iduser");
         		String prenom = rsid.getString("prenom");
         		String nom = rsid.getString("nom");
         		String image = rsid.getString("photo");

         		stid.close();
         		rsid.close();

				

				// permet de recuperer le total de temps sur les films
				String queryFilm = "select sum(film.durée) from film, preferences_film where film.idfilm=preferences_film.idfilm and iduser='"+iduser+"'";
				

    			// total vue des films en minutes     
				Statement stf = conn.createStatement();
				stf.execute(queryFilm);
				int f = stf.getResultSet().getInt(1);
				stf.close();


    			// transformation du resultat en heure et minutes
         		int heure = f/60;
         		int minutes = f%60;

         		// bandeau profil en haut de page 
         		out.println("<form method='post' action='http://localhost:8080/vide/trash'>");
				out.println("<tr><center><td><input type='submit' style='margin-left:15px;float:left;height:30px; background-color: #87CEEB; border: none;font-size: 15px' value='Se Deconnecter'</td></center></tr>");
				out.println("</form>");
         		out.println("<div class='jumbotron jumbotron-fluid'>");
         		out.println("<div class='container'>");
         		
         		out.println("<center>");
         		out.println("<a href='http://localhost:8080/vide/modifProfil'><img style='-webkit-border-radius:50px; -moz-border-radius:50px; border-radius:50px;' src='"+image+"' width=100 height=100/></a>");
         		out.println("<h1 class='display-4'>"+ prenom +"  "+ nom +"</h1>");
         		out.println("<p class='lead'>Tu as passé "+ heure+" heures "+ minutes + " minutes à regarder de superbre films! </p>");
         		out.println("</center>");
         		// bouton de deconnection 
         		
         		out.println("</div>");
         		out.println("</div>");

         		
         		// affichage des liste des films
         		out.println("<center>");
        		out.println("<div id=\"gauche\">");
        		out.println("<a>Prochainement....</a>");
				out.println("<ul class='list-group' style=\"width: 255px; height: 500px; overflow: auto;  border: solid 2px #87CEEB;\">");
				//Méthode qui list la list des Films
				createListProchainFilm(conn, out, iduser);
				out.println("</ul>");

				out.println("</div>");
				out.println("<div id=\"milieu\">");

				out.println("<a>Des films qui peuvent vous plaire</a>");
				out.println("<ul class='list-group' style=\"width: 255px; height: 500px; overflow: auto;  border: solid 2px #87CEEB;\">");
				createListFilm(conn, out, iduser);
				out.println("</ul>");

				out.println("</div>");
				out.println("<div id=\"droite\">");

				out.println("<a>Vous avez aimé...</a>");
				out.println("<ul class='list-group' style=\"width: 255px; height: 500px; overflow: auto;  border: solid 2px #87CEEB;\">");
				createListPreference(conn, out, iduser);
				out.println("</ul>");
				out.println("</div>");
				out.println("</center>");
				if(tmp == 408){
					out.println("<form method='post' action='http://localhost:8080/vide/modopanel'>");
				out.println("<center><input type='submit' style='height:30px; background-color: #87CEEB; border: none;font-size: 15px' value='Modération'</center>");
				out.println("</form>");
				out.println("<br /><br />");
				}
				out.println("</body>");
				
				out.println("<footer><center>Movie remainders - Alexy Ledain & Alexandre Blerreau - 2018</center></footer></html>");


			}else {
				//on le redirige sinon
				res.sendRedirect("index.html");
			}

		}catch(Exception e){
			res.sendRedirect("index.html");
			//out.println(e);
		}finally{
			try{
				if (conn != null){
					conn.close();
				} 
			}catch(Exception ioe){
				out.println(ioe );
			}
		}
	}


	private void createListFilm(Connection con, PrintWriter out, int iduser)throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			String query ="select * from film where idfilm not in (select idfilm from preferences_film where iduser = " + iduser + ");";
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				if(traitement.indexOf(rs.getInt("idfilm")) == -1){	
					out.println("<li style='text-align: center;><a'><form method='get' action='http://localhost:8080/vide/detailFilm'>");
					out.println("<input type='image' width='200' height='230' src='" + rs.getString("image") 
						+ "' name='" 
						+ rs.getInt("idfilm") + "'>");
					out.println("</form></a></li>");
				}
			}
		} catch (SQLException e ) {
			out.println(e);
		} finally {
			if (rs != null) { rs.close(); }
			if (stmt != null) { stmt.close(); }
		}
	}

	private void createListPreference(Connection con, PrintWriter out, int iduser)throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.createStatement();
			String query ="Select * from film where idfilm in (Select idfilm from preferences_film where iduser = " + iduser + ")";
			rs = stmt.executeQuery(query);
			int taille = 0;
			while (rs.next()){
				out.println("<li><a><form method='get' action='http://localhost:8080/vide/deleteDetailFilm'>");
				out.println("<input type='image' width='200' height='230' src='" + rs.getString("image") 
					+ "' name='" 
					+ rs.getInt("idfilm") + "'>");
				out.println("</form></a></li>");
				taille ++;
			}
			if(taille == 0){
				// si l'utilisateur n'a pas encore de préférences on affiche une image 
				out.println("<li><a><input type='image' width='200' height='230' src='nofilm.png'></a></li>");
			}
		} catch (SQLException e ) {
			out.println(e);
		} finally {
			if (rs != null) { rs.close(); }
			if (stmt != null) { stmt.close(); }
		}
	}

	private void createListProchainFilm(Connection conn, PrintWriter out, int iduser){
		traitement = new ArrayList<Integer>();
		// recuperation date actuelle 
		Calendar cal = Calendar.getInstance();
		int annee = cal.get(Calendar.YEAR);
		int mois =  cal.get(Calendar.MONTH);
		int jour = cal.get(Calendar.DATE);
		// requetes...
		String query ="select * from film where idfilm not in (select idfilm from preferences_film where iduser = " + iduser + ");";
		// traitement de la requête
		Statement st = null;
		ResultSet  rs = null;
		try{
			st = conn.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()){
				// annee plus grande ?
				if(Integer.parseInt(rs.getString("date_sortie").substring(4,8)) > annee){
					
					traitement.add(rs.getInt("idfilm"));
					//break;
				}
				// sortie prevu pour l'année actuelle, verification du mois et du jour 
				if(Integer.parseInt(rs.getString("date_sortie").substring(4,8)) == annee){
					if(Integer.parseInt(rs.getString("date_sortie").substring(2,4)) > mois){
						
						traitement.add(rs.getInt("idfilm"));
						//break;
					}
				}
				// même mois ? verifions le jour
				if(Integer.parseInt(rs.getString("date_sortie").substring(2,4)) == mois){
					if(Integer.parseInt(rs.getString("date_sortie").substring(0,2)) > jour){

						traitement.add(rs.getInt("idfilm"));
						//break;
					}
				}
			}
			st.close();
			rs.close();
			for(int i = 0; i < traitement.size(); i ++){
				Statement stx = conn.createStatement();
				stx.execute("select image from film where idfilm="+ traitement.get(i) + "");
				out.println("<li><a><form method='get' action='http://localhost:8080/vide/detailFilm'>");
				out.println("<input type='image' width='200' height='230' src='" + stx.getResultSet().getString("image") 
					+ "' name='"
					+ traitement.get(i) + "'>");
				out.println("</form></a></li>");
				stx.close();

			}

		// traitement de tri termine close st rs conn traiter l'affichage

		}catch(Exception e){
			out.println(e);
		}	

	}
}