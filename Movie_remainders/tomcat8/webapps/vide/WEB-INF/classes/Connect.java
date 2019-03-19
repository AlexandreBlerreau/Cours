// Servlet d'authentification
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;

@WebServlet("/conn")
public class Connect extends HttpServlet{
  public void service( HttpServletRequest req, HttpServletResponse res ) throws  IOException{
    Connection conn = null;
    Statement st = null;
    ResultSet rs = null;
    String dataBase = "jdbc:sqlite:../bdd";
    PrintWriter out = res.getWriter();
    boolean auth = false; 
    HttpSession session;
    Cookie gateau;

    try{
      Class.forName("org.sqlite.JDBC");
    } catch(Exception e){
      e.printStackTrace();
    }

    try {
      // Connection a la base sqlite3
      conn = DriverManager.getConnection(dataBase);

      // requete sql a executer 
      st = conn.createStatement();
      rs = st.executeQuery("Select iduser, mail, mdp, idstatus from utilisateurs");


          // Verification de l'utilisateur au pres de la base de donnes et vérification qu'il n'est pas administrateur
      while(rs.next()){
        if(rs.getString("mail").equals(req.getParameter("iduser")) 
          && rs.getString("mdp").equals(req.getParameter("mdp")) 
          && rs.getInt("idstatus") == 3){
          auth = true;
          // attribution de la session pour acceder aux pages
          session = req.getSession();
          session.setAttribute("key", 42);
          // Cookie pour connaitre l'utilisateur courant
          gateau = new Cookie("user","" + rs.getInt("iduser"));
          res.addCookie(gateau);
          // redirection vers la page menu
          res.sendRedirect("http://localhost:8080/vide/menu");
        }else if (rs.getString("mail").equals(req.getParameter("iduser")) 
          && rs.getString("mdp").equals(req.getParameter("mdp")) 
          && rs.getInt("idstatus") == 1){
          auth = true;
          // attribution de la session pour acceder aux pages
          // attribution d'une session spécial administateur
          session = req.getSession();
          session.setAttribute("key",404);
          // redirection vers la servlet d'administration
          res.sendRedirect("http://localhost:8080/vide/adminpanel");

        }else if (rs.getString("mail").equals(req.getParameter("iduser")) 
          && rs.getString("mdp").equals(req.getParameter("mdp")) 
          && rs.getInt("idstatus") == 2){
          auth = true;
          // attribution de la session pour acceder aux pages
          // attribution d'une session spécial modérateur
          session = req.getSession();
          session.setAttribute("key",408);
          out.println(rs.getInt("iduser") + "loooool");
          gateau = new Cookie("user","" + rs.getInt("iduser"));
          res.addCookie(gateau);
          // redirection vers la servlet de modération
          res.sendRedirect("http://localhost:8080/vide/menu");
        }
      }
        if (!auth) {
        // l'utilisateur est inconnu redirection vers le formulaire 
        out.println("<HTML>");
        out.println("<HEAD>");
        out.println("<META http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
        out.println("<link rel='stylesheet' type='text/css' href='connectionstyle.css'/>");
        out.println("<TITLE>Movie remainders - Connection</TITLE>");
        out.println("</HEAD>");
        out.println("<BODY>");

        out.println("<center>");
        out.println("<img src='logo.png' alt='logo'  height='125' width='260'>");
        out.println("</center>"); 

        out.println("<br>");

        out.println("<form method='post' action='http://localhost:8080/vide/conn'>");

        out.println("<center><i>Identifiant ou mot de passe inccorect</i></center><br />");
        out.println("<table>");
        out.println("<tr><td><a>Adresse mail:</a><br /><input type='textbox' name='iduser' required></td></tr>");
        out.println("<tr><td><a>Mot de passe:</a><br /><input type='password' name='mdp' required></td></tr>");

        out.println("<tr><td><input type='submit' style='height:30px; background-color: #87CEEB; border: none;font-size: 15px' value='Se connecter'</td></tr>");
        out.println("<tr><td><a>Vous n'etes pas encore inscrit ?</a><br/><a href='create.html'>Inscription</a></td></tr>");
        out.println("</table>");

        out.println("</BODY>");
        out.println("</HTML>");
      } 
    }catch (SQLException ex) {
      out.println(ex.toString());
    }finally {
      try{
        if (rs != null) {
          rs.close();
        }
        if (st != null) {
          st.close();
        }
        if (conn != null) {
          conn.close();
        } 
      }catch(Exception e){
        out.println(e);
      }
    }
  }
}

