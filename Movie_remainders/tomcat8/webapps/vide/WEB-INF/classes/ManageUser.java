// servlet de gestion des utilisateurs
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;
import java.util.*;

@WebServlet("/manageuser")
public class ManageUser extends HttpServlet
{


	Connection conn = null;
	Statement st = null;
	ResultSet rs = null;
	String dataBase = "jdbc:sqlite:../bdd";
	String query = "select * from utilisateurs";
	int tmp = -1;

  public void service( HttpServletRequest req, HttpServletResponse res ) 
  throws  IOException
  {

  	PrintWriter out = res.getWriter();
  	HttpSession session = req.getSession();

  	try{
  		// recuperation de la valeur de la session
  		tmp = (int) session.getAttribute("key");

  		if(tmp == 404){
  		// l'utilisateur est admin 

	  	try{
	  		Class.forName("org.sqlite.JDBC");
	  	}catch(Exception e){}

	  	try{
	  	// connection a la bdd
	  	conn = DriverManager.getConnection(dataBase);
	  	st = conn.createStatement();
	  	rs = st.executeQuery(query);

	  	
	  	
	  	// affiche la page
	  	out.println("<html><head><link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css' integrity='sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO' crossorigin='anonymous'></head>");
	  	out.println("<body>");
	  	out.println("<a href='http://localhost:8080/vide/adminpanel'><img src='back.png' alt='logo'  height='50' width='50'></a>");
	  	out.println("<table class='table table-striped'><tr><th>ID</th><th>Photo</th><th>Nom</th><th>Prénom</th><th>Age</th><th>Mail</th><th>  </th><th>  </th><th>  </th></tr>");

	  	while(rs.next()){
	  		out.println("<tr style='text-align:center;'>");
	  		out.println("<form method='get' action='http://localhost:8080/vide/edituser'>");
	  		// champs cache de l'iduser pour le modifier
	  		out.println("<input type='hidden' name='iduser' value='"+rs.getInt("iduser") +"'></td>");
	  		out.println("<td>"+ rs.getInt("iduser") +"</td>");
	  		out.println("<td><img src='"+ rs.getString("photo") +"' width='100' height='100'></td>");
	  		out.println("<td>"+ rs.getString("nom") +"</td>");
	  		out.println("<td>"+ rs.getString("prenom") +"</td>");
	  		out.println("<td>"+ rs.getInt("age") +"</td>");
	  		out.println("<td>"+ rs.getString("mail") +"</td>");

	  		// bouton permettant de modifier, supprimer l'utilisateur concerné
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











	  	}else if(tmp == 408){
				res.sendRedirect("http://localhost:8080/vide/modopanel");

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
}
