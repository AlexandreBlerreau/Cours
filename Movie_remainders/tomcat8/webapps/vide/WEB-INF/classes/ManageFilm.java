// servlet de gestion des films
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;
import java.util.*;

@WebServlet("/managefilm")
public class ManageFilm extends HttpServlet
{


	Connection conn = null;
	Statement st = null;
	ResultSet rs = null;
	String dataBase = "jdbc:sqlite:../bdd";
	String query = "select * from film";
	int tmp = -1;

  public void service( HttpServletRequest req, HttpServletResponse res ) 
  throws  IOException
  {

  	PrintWriter out = res.getWriter();
  	HttpSession session = req.getSession();

  	try{
  		// recuperation de la valeur de la session
  		tmp = (int) session.getAttribute("key");

  		if(tmp == 404 || tmp == 408){
  		// l'utilisateur est admin ou modo

	  	try{
	  		Class.forName("org.sqlite.JDBC");
	  	}catch(Exception e){}

	  	try{
	  	// connection a la bdd
	  	conn = DriverManager.getConnection(dataBase);
	  	st = conn.createStatement();
	  	rs = st.executeQuery(query);

	  	// traitement du format de la date
	  	
	  	

	  	

	  	// affiche la page
	  	out.println("<html><head><link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css' integrity='sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO' crossorigin='anonymous'></head>");
	  	out.println("<body>");
	  	out.println("<table class='table table-striped'><tr><th>ID</th><th>Image</th><th>Titre</th><th>Description</th><th>Durée</th><th>Date de sortie</th><th>genre</th><th>  </th><th>  </th></tr>");
	  	out.println("<a href='http://localhost:8080/vide/adminpanel'><img src='back.png' alt='logo'  height='50' width='50'></a>");

	  	while(rs.next()){
	  		String date = traiterDate(rs.getString("date_sortie"));
	  		out.println("<tr style='text-align:center;'>");
	  		out.println("<form method='get' action='http://localhost:8080/vide/editfilm'>");
	  		// champs cache de l'idfilm pour le modifier
	  		out.println("<input type='hidden' name='idfilm' value='"+rs.getInt("idfilm") +"'></td>");
	  		out.println("<td>"+ rs.getInt("idfilm") +"</td>");
	  		out.println("<td><img src='"+ rs.getString("image") +"' width='100' height='100'></td>");
	  		out.println("<td>"+ rs.getString("titre") +"</td>");
	  		out.println("<td>"+ rs.getString("description") +"</td>");
	  		out.println("<td>"+ rs.getInt("durée") +"</td>");
	  		out.println("<td>"+ date +"</td>");
	  		out.println("<td>"+ rs.getInt("genre") +"</td>");

	  		// bouton permettant de modifier, supprimer le film concerné
	  		out.println("<td><input type='submit' name='mod' style='height:30px; background-color: #87CEEB; border: none;font-size: 15px' value='Modifier'</td>");
	  		out.println("<td><input type='submit' name='suppr' style='height:30px; background-color: #87CEEB; border: none;font-size: 15px' value='Supprimer'</td>");



	  		out.println("</form>");
	  		out.println("</tr>");


	  	}
	  
	  	out.println("</table></body></html>");



	  	}catch(Exception e){
	  		// en cas d'erreur sql
	  		out.println(e);
	  	}	













  		}else {
  			// l'utilisateur n'est pas admin on le redirige
  			res.sendRedirect("http://localhost:8080/vide/menu");
  		}











  	}catch(Exception e){
  		// l'utilisateur n'a pas de session on le redirige
  		res.sendRedirect("http://localhost:8080/vide/menu");
  	}



  	try{
  		conn.close();
  	}catch(Exception e){}


  





  }

  public static String traiterDate(String date){
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

