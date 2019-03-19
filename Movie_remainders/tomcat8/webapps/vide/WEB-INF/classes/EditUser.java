import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;
import java.util.*;

@WebServlet("/edituser")
public class EditUser extends HttpServlet
{
  int idUser;

  public void doGet( HttpServletRequest req, HttpServletResponse res ) 
  throws  IOException
  {
   PrintWriter out = res.getWriter();
   Connection conn = null;
   Statement st = null;
   ResultSet rs = null;
   String dataBase = "jdbc:sqlite:../bdd";
   int tmp = -1;
   idUser = Integer.parseInt(req.getParameter("iduser"));



   HttpSession session = req.getSession();

   try{
      // recuperation de la valeur de la session
    tmp = (int) session.getAttribute("key");

    if(tmp == 404){
      // l'utilisateur est admin 
      try{
        Class.forName("org.sqlite.JDBC");
      }catch(Exception e){}



      if (req.getParameter("suppr") != null) {
          // le bouton utilise est supprimer -> appel de la methode pour supprimer un utilisateur
           supprimer(idUser);
           res.sendRedirect("http://localhost:8080/vide/manageuser");


          }

          if (req.getParameter("mod") != null) {
          // le bouton utilise est modifier -> affichage d'un formulaire de modification

           try {
            // Connection a la base sqlite3
            conn = DriverManager.getConnection(dataBase);

            // requete sql a executer 
            String query = "select * from utilisateurs where iduser='"+idUser+"'";
            st = conn.createStatement();
            rs = st.executeQuery(query);


            out.println("<html><head><link rel='stylesheet' type='text/css' href='connectionstyle.css'/></head>");
            out.println("<body>");
            out.println("<a href='http://localhost:8080/vide/manageuser'><img src='back.png' alt='logo'  height='50' width='50'></a>");
            out.println("<center><h2>Modifier un utilisateur</h2><br /></center>");
            out.println("<form method='post' action='http://localhost:8080/vide/edituser'>");

            out.println("<table id='create'>");

            out.println("<tr>");
            out.println("<input type='hidden' name='iduser' value='"+idUser+"'></td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td><a>Nom</a><br /><input type='textbox' name='nom' value='"+ rs.getString("nom") +"'required></td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td><a>Prénom</a><br /><input type='textbox' name='prenom' value='"+ rs.getString("prenom") +"'required></td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td><a>Age</a><br /><input type='number' value='"+rs.getInt("age") +"' name='age' required></td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td><a>Mail</a><br /><input type='textbox' value='"+ rs.getString("mail") +"' name='mail' required></td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td><a>Mot de passe</a><br /><input type='password' value='"+ rs.getString("mdp") +"' name='password' required></td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td><a>Photo (lien)</a><br /><input type='textbox'  value='"+ rs.getString("photo") +"'name='image' required></td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("</body></html>");

             out.println("<tr><td><input type='submit' style='height:30px; background-color: #87CEEB; border: none;font-size: 15px' value='Modifier'</td></tr>");
             out.println("</form>");



          }catch(Exception e){
            out.println(e);
          }finally{
            try{
              conn.close();
            }catch(Exception e){}
          }
        }

        }else if(tmp == 408){
        res.sendRedirect("http://localhost:8080/vide/modopanel");
    } else {
        // l'utilisateur n'est pas admin
      res.sendRedirect("http://localhost:8080/vide/menu");
    }

  }catch(Exception e){
        // l'utlisateur n'a pas de session
   res.sendRedirect("http://localhost:8080/vide/menu");

 }

}

public void doPost( HttpServletRequest req, HttpServletResponse res ) 
  throws  IOException
  {

    // traitement de la modification
     PrintWriter out = res.getWriter();
     Connection conn = null;
     Statement stx = null;
     String dataBase = "jdbc:sqlite:../bdd";

     // recupération des champs du formulaire
     int id = Integer.parseInt(req.getParameter("iduser"));
     String nom = req.getParameter("nom");
     String prenom = req.getParameter("prenom");
     int age = Integer.parseInt(req.getParameter("age"));
     String mail = req.getParameter("mail");
     String photo = req.getParameter("image");
     String passwd = req.getParameter("password");



      try{
             Class.forName("org.sqlite.JDBC");
         } catch(Exception e){}



           try {
            // Connection a la base sqlite3
            conn = DriverManager.getConnection(dataBase);

            // requete sql a executer 
            stx = conn.createStatement();
            String query = "update utilisateurs set nom='"+nom+"',prenom='"+prenom+"',age="+age+",mail='"+mail+"',photo='"+photo+"',mdp='"+passwd+"' where iduser="+id+"";
            stx.executeUpdate(query);
            res.sendRedirect("http://localhost:8080/vide/manageuser");

          }catch(Exception e){
            out.println(e);
          }
}



 public void supprimer(int id){
      // la fonction supprimera l'utilisateur

       String querySuppr = "delete from utilisateurs where iduser='"+id+"'";
       String dataBase = "jdbc:sqlite:../bdd";
       Connection conn = null;
       Statement st = null;         

       try{
        Class.forName("org.sqlite.JDBC");
      }catch(Exception e){}


      try {
            // Connection a la base sqlite3
        conn = DriverManager.getConnection(dataBase);

            // requete sql a executer 
        st = conn.createStatement();
        st.executeUpdate(querySuppr);
        


      }catch(Exception e){
            
      }finally{
        try{
          conn.close();
        }catch(Exception e){}
      }

    }
}
