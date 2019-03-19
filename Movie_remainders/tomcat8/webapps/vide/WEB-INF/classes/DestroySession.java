// Servlet de destruction de session
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;

@WebServlet("/trash")
public class DestroySession extends HttpServlet {
public void service( HttpServletRequest req, HttpServletResponse res ) 
       throws  IOException
  {


  	HttpSession session = req.getSession();
  	PrintWriter out = res.getWriter();


  	// on detruis le cookie de l'utilisateur courant
  	Cookie gateau = new Cookie("user","null");
  	res.addCookie(gateau);


  	// on detruis la session et on redirige le client vers la page de connection
  	session.invalidate();
  	res.sendRedirect("index.html");


  }
}