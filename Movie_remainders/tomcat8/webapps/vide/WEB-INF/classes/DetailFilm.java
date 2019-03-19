import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;
import java.util.*;

@WebServlet("/detailFilm")
public class DetailFilm extends HttpServlet{

	public void doGet( HttpServletRequest req, HttpServletResponse res )throws  IOException{
		PrintWriter out = res.getWriter();
		Connection conn = null;
		Statement st = null;
		Statement st2 = null;
		String dataBase = "jdbc:sqlite:../bdd";
		HttpSession session = req.getSession();
		Cookie gateau;

		try{
			int tmp = (int)session.getAttribute("key");
			if(tmp != 42){
				res.sendRedirect("index.html");
			}
			Enumeration <String> parametres = req.getParameterNames();
			String param = parametres.nextElement();
			String idfilm = param.substring(0,param.length()-2);
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection(dataBase);
			st = conn.createStatement();
			ResultSet rs = st.executeQuery("Select * from film where idfilm = '" + Integer.parseInt(idfilm) + "'");
			String image = rs.getString("image");
			String titre = rs.getString("titre");
			String date = rs.getString("date_sortie");
			int genre = rs.getInt("genre");
			String description = rs.getString("description");
			int durée = rs.getInt("durée");
			st.close();
			rs.close();

			st2 = conn.createStatement();
			st2.execute("select libelle from genre where idgenre=" + genre +"");
			String genrelib = st2.getResultSet().getString("libelle");
			st2.close();

			gateau = new Cookie("idfilm", idfilm);
    		res.addCookie(gateau);

			out.println("<HTML>");
			out.println("<header>");
			out.println("<link rel='stylesheet' type='text/css' href='detailsfilmstyle.css'/>");
			out.println("</header>");
			out.println("<body>");
			

			out.println("<div id='container'>");

    		out.println("<div id='results'>");
      		out.println("<div id='movie'>");
        	out.println("<div class='movie-details-container'>");
          	out.println("<div class='column column-md'>");
          	//Bouton retour.
			out.println("<a href='http://localhost:8080/vide/menu'><img src='back.png' alt='logo'  height='50' width='50'></a>");

			//Formulaire qui ajoute un film dans les préférences de l'utilisateur
			out.println("<form method='get' action='http://localhost:8080/vide/addfilmPreferenceUser'>");
			out.println("<tr><td><input type='submit' style='height:30px; background-color: #87CEEB; border: none;font-size: 15px' value='Ajouter à mes préférences'</td></tr>");
			out.println("</form>");

			out.println("<img src=" + image + " id='poster'>");
          	out.println("</div>");
          	out.println("<div class='column column-md'>");
            out.println("<h1 id='title'>" + titre + "</h1>");
            out.println("<div class='row'>");
            out.println("" + genrelib + " | " + traiterDate(date));
            out.println("</div>");
            out.println("<h3 id='tagline'>Descrption</h3>");
            out.println("<div id='overview'>");
            out.println("<p id='synopsis'> " + description + "</p>");
            out.println("</div>");
            out.println("<div class='row'>");
            out.println("<div class='column column-md'>");
            out.println("<h2>Durée</h2>");
            out.println("<div id='runtime'> "+ durée + " minutes</div>");
            out.println("</div>");
            out.println("</div>");
            out.println("</div>");
            out.println("</div>");
            out.println("</div>");
            out.println("</div>");
            out.println("</div>");
            out.println("</div>");

			out.println("</body>");
			out.println("</HTML>");
			
		}catch(Exception e){
			res.sendRedirect("index.html");
		}finally{
			try{
				if (st != null){st.close();}
				if (conn != null){conn.close();} 
			}catch(Exception ioe){
				//out.println(ioe);
			}
		}
	}

	private String traiterDate(String date){
  	// traitement du format de la date
		int index = 0;
		String result = "";

		if(date.length() > 8){
			return "format invalide";
		}else {
			result += date.substring(index, index+2);
			result += "/";
			index +=2;
			result += date.substring(index, index+2);
			result+= "/";
			index += index+2;
			result+= date.substring(index, index+2);			
		}
		return result;
	}
}