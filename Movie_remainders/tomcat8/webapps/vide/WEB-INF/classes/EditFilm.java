// servlet d'ajout d'un film
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;
import java.util.*;

@WebServlet("/editfilm")
public class EditFilm extends HttpServlet
{
  int idFilm;

  public void doGet( HttpServletRequest req, HttpServletResponse res ) 
  throws  IOException
  {
   PrintWriter out = res.getWriter();
   Connection conn = null;
   Statement st = null;
   Statement st2 = null;
   ResultSet rs = null;
   String dataBase = "jdbc:sqlite:../bdd";
   int tmp = -1;
   idFilm = Integer.parseInt(req.getParameter("idfilm"));



   HttpSession session = req.getSession();

   try{
      // recuperation de la valeur de la session
    tmp = (int) session.getAttribute("key");

    if(tmp == 404 || tmp == 408){
      // l'utilisateur est admin 
      try{
        Class.forName("org.sqlite.JDBC");
      }catch(Exception e){}



      if (req.getParameter("suppr") != null) {
          // le bouton utilise est supprimer -> appel de la methode pour supprimer un film
           supprimer(idFilm);
           res.sendRedirect("http://localhost:8080/vide/managefilm");


          }

          if (req.getParameter("mod") != null) {
          // le bouton utilise est modifier -> affichage d'un formulaire de modification

           try {
            // Connection a la base sqlite3
            conn = DriverManager.getConnection(dataBase);

            // requete sql a executer 
            String query = "select * from film where idfilm='"+idFilm+"'";
            st = conn.createStatement();
            rs = st.executeQuery(query);


            out.println("<html><head><link rel='stylesheet' type='text/css' href='connectionstyle.css'/></head>");
            out.println("<body>");

          
            out.println("<a href='http://localhost:8080/vide/managefilm'><img src='back.png' alt='logo'  height='50' width='50'></a>");
            out.println("<center><h2>Modifier un film</h2><br /></center>");
            out.println("<form method='post' action='http://localhost:8080/vide/editfilm'>");

            out.println("<table id='create'>");

            out.println("<tr>");
            out.println("<input type='hidden' name='idfilm' value='"+idFilm+"'></td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td><a>Titre</a><br /><input type='textbox' name='titre' value='"+ rs.getString("titre") +"'required></td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td><a>Déscription</a><br /><textarea name='description' id='txt' rows='10' cols='30'>"+rs.getString("description")+"</textarea></td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td><a>Durée (en minutes)</a><br /><input type='number' value='"+rs.getInt("durée") +"' name='duree' required></td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td><a>Date de sortie (JJMMAAAA)</a><br /><input type='textbox' value='"+ rs.getString("date_sortie") +"' name='date' required></td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td><a>Image (lien)</a><br /><input type='textbox'  value='"+ rs.getString("image") +"'name='image' required></td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td><a>Genre <a href='#'>aide</a></a><br /><select name='genre'>");


            st.close();
            st2 = conn.createStatement();
            rs = st2.executeQuery("select * from genre");


            while(rs.next()){
              out.println("<option>" + rs.getInt("idgenre"));
              
            }
            out.println("</option></select>");

            out.println("</td>");
            out.println("</tr>");

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
     int id = Integer.parseInt(req.getParameter("idfilm"));
     String titre = req.getParameter("titre");
     String description = req.getParameter("description");
     int duree = Integer.parseInt(req.getParameter("duree"));
     String datesortie = req.getParameter("date");
     String image = req.getParameter("image");
     int genre = Integer.parseInt(req.getParameter("genre"));



      try{
             Class.forName("org.sqlite.JDBC");
         } catch(Exception e){}



           try {
            // Connection a la base sqlite3
            conn = DriverManager.getConnection(dataBase);

            // requete sql a executer 
            stx = conn.createStatement();
            String query = "update film set titre='"+titre+"',description='"+description+"',durée="+duree+",date_sortie='"+datesortie+"',image='"+image+"',genre="+genre+" where idfilm="+id+"";
            stx.executeUpdate(query);
            res.sendRedirect("http://localhost:8080/vide/managefilm");

          }catch(Exception e){
            out.println(e);
          }
}




     public void supprimer(int id){
      // la fonction supprimera le film

       String querySuppr = "delete from film where idfilm='"+id+"'";
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
  