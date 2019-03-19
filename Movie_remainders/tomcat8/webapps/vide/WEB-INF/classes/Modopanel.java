// Servlet d'administration admin
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;

@WebServlet("/modopanel")
public class Modopanel extends HttpServlet
{

	public void service( HttpServletRequest req, HttpServletResponse res ) 
	throws  IOException
	{

		PrintWriter out = res.getWriter();
		HttpSession session = req.getSession();
		int tmp = -1;
		try{
			// recuperation de la valeur de la session
			tmp = (int) session.getAttribute("key");


			if(tmp == 408 || tmp == 404){
			// session valide
				out.println("<HTML><header>");
				out.println("<link rel='stylesheet' type='text/css' href='panel.css'/>");
				out.println("</header><body>");


				out.println("<a href='http://localhost:8080/vide/menu'><img src='back.png' alt='logo'  height='50' width='50'></a>");
				out.println("<center>");
				out.println("<img src='logo.png' alt='logo'  height='125' width='260'>");
				out.println("</center>");

				out.println("<br>");
				
  
								
				// tableau bouton gestion des films
				out.println("<center>");
			
				out.println("<table style='text-align=center;'>");
				out.println("<form method='get' action='http://localhost:8080/vide/addfilm'>");
				out.println("<tr><td><input type='submit' style='height:40px; background-color: #87CEEB; border: none;font-size: 20px' value='Ajouter un film'</td></tr>");
				out.println("</form>");
				out.println("<form method='post' action='http://localhost:8080/vide/managefilm'>");
				out.println("<tr><td><input type='submit' style='height:40px; background-color: #87CEEB; border: none;font-size: 20px' value='Gérer les films'</td></tr>");
				out.println("</form>");
				out.println("</table>");


				out.println("<br><br><br>");
				out.println("<form method='post' action='http://localhost:8080/vide/trash'>");
				out.println("<tr><td><input type='submit' style='height:40px; background-color: #87CEEB; border: none;font-size: 20px' value='Se Déconnecter'</td></tr>");
				out.println("</form>");
				out.println("</center>");

				out.println("</body></html>");
			
			}else{
				// session invalide
				out.println("Vous n'avez pas acces a cette page");
				// on redirige
				res.sendRedirect("http://localhost:8080/vide/menu");
			}

		}catch(Exception e){
			// session null
			out.println("Vous n'avez pas acces a cette page");
			// on redirige
			res.sendRedirect("http://localhost:8080/vide/menu");
		}


	 }
 } 	