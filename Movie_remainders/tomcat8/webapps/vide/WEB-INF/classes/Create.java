  // Servlet de creation d'un nouveau client
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;

@WebServlet("/create")
public class Create extends HttpServlet
{

  public void service( HttpServletRequest req, HttpServletResponse res ) 
  throws  IOException
  {
    Connection conn = null;
    Statement st = null;
    Statement st2 = null;
    ResultSet rs = null;
    String dataBase = "jdbc:sqlite:../bdd";
    PrintWriter out = res.getWriter();



    try{
     Class.forName("org.sqlite.JDBC");
   } catch(Exception e){
    e.printStackTrace();
  }

  try {
              // Connection a la base sqlite3
    conn = DriverManager.getConnection(dataBase);


              // recuperation des information du nouveau client

    String prenom = req.getParameter("prenom");
    String nom = req.getParameter("nom");
    int age = Integer.parseInt(req.getParameter("age"));
    String mdp = req.getParameter("mdp");
    String mail = req.getParameter("mail");
    String photo = req.getParameter("photo");

    st = conn.createStatement();

             // verification que l'email n'est pas deja utilise
    rs = st.executeQuery("Select count(*) from utilisateurs where mail='"+mail+"'");
    // libération de la base
    int nb = rs.getInt(1);
    st.close();

    if(nb > 0){
                // l'email est deja utilise
      out.println("<HTML>");
      out.println("<HEAD>");
      out.println("<META http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
      out.println("<link rel='stylesheet' type='text/css' href='connectionstyle.css'/>");
      out.println("<TITLE>Movie remainders - Inscription</TITLE>");
      out.println("</HEAD>");
      out.println("<BODY>");

      out.println("<center>");
      out.println("<a href='http://localhost:8080/vide/index.html'><img src='logo.png' alt='logo'  height='125' width='260'></a>");
      out.println("<h2>Inscription</h2>");
      out.println("</center>");

      out.println("<br>");
      out.println("<center><i>L'email est deja associee a un compte</i></center>");

      out.println("<form method='post' action='http://localhost:8080/vide/create'>");

      out.println("<table id='create'>");

      out.println("<tr>");
      out.println("<td><a>Votre prenom:</a><br /><input type='textbox' name='prenom' required></td>");
      out.println("</tr>");
      out.println("<tr>");
      out.println("<td><a>Votre nom:</a><br /><input type='textbox' name='nom' required></td>");
      out.println("</tr>");
      out.println("<tr>");
      out.println("<td><a>Votre age:</a><br /><input type='number' name='age' required></td>");
      out.println("</tr>");
      out.println("<tr>");
      out.println("<td><a>Votre mot de passe:</a><br /><input type='password' name='mdp' required></td>");
      out.println("</tr>");
       out.println("<tr>");
      out.println("<td><a>Votre photo:</a><br /><input type='text' name='photo' value='https://upload.wikimedia.org/wikipedia/commons/thumb/5/5b/Pictogram_voting_question.svg/220px-Pictogram_voting_question.svg.png' required></td>");
      out.println("</tr>");
      out.println("<tr>");
      out.println("<td><a>Votre mail:</a><br /><input type='email' name='mail' required></td>");
      out.println("</tr>");
      out.println("<tr>");
      out.println("<td><input type='submit' style='height:30px; background-color: #87CEEB; border: none;font-size: 15px' value='Inscrition'></td>");
      out.println("</tr>");

      out.println("</table>");   


      out.println("</BODY>");
      out.println("</HTML>");
    }
    else {
                // l'email n'est pas encore utilise
                // requete a executer
      String query = "INSERT INTO utilisateurs(idstatus, nom,prenom,age,mdp,mail,photo) VALUES (3 " + ",'" + nom + "','" + prenom + "'," + age + ",'" + mdp + "','" + mail + "','" + photo + "')";

      st2 = conn.createStatement();
      st2.executeUpdate(query);
                //redirection vers la page de connection
      res.sendRedirect("index.html");

    }  

  } catch (SQLException ex) {
    out.println(ex.toString());
  }finally{
    try{
          conn.close();
        }catch(Exception e){
          
        }
  }

}
}

