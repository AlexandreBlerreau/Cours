// servlet profil utilisateur
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;
import java.util.*;

@WebServlet("/profile")
public class Profile extends HttpServlet
{

  public void service( HttpServletRequest req, HttpServletResponse res ) 
  throws  IOException
  {


  	HttpSession session = req.getSession();
  	PrintWriter out = res.getWriter();
    Cookie[] gateau = req.getCookies();
    String utilisateur = gateau[1].getValue();
    ResultSet rs = null;

    Connection conn = null;
    String dataBase = "jdbc:sqlite:../bdd";
    String query = "Select * from utilisateurs where mail='"+utilisateur+"'";
   
    Statement st;
    int tmp = 0;
    int idUser = -1;  
   





    try{

        tmp = (int)session.getAttribute("key");
     
  		// on verifie la session du client pour savoir si il peut acceder a cette page
      if(tmp == 42){

        try{

         // preparation pour l'execution de la requette 
         Class.forName("org.sqlite.JDBC");
         conn = DriverManager.getConnection(dataBase);
       
      
         st = conn.createStatement();
         rs = st.executeQuery(query);

      // recuperation des information de l'utilisateur courant
         idUser = Integer.parseInt(rs.getString("iduser"));
         out.println(rs.getString("prenom"));
         out.println(rs.getString("nom"));
         out.println(rs.getString("age"));
         st.close();
         rs.close();

         



   // permet de recuperer le total de temps sur les série et les films
         String queryFilm = "select sum(film.durée) from film, préférences_film where film.idfilm=préférences_film.idfilm and iduser='"+idUser+"'";
         String querySerie ="select sum(épisode.durée) from épisode, préférences_série where épisode.idepisode=préférences_série.idsérie and iduser='"+idUser+"'";

    // total vue des films en minutes     
         Statement stf = conn.createStatement();
         stf.execute(queryFilm);
         int f = stf.getResultSet().getInt(1);
         stf.close();

    // total vue des series en minutes   
         Statement sts = conn.createStatement();
         sts.execute(querySerie);
         int s = sts.getResultSet().getInt(1);
         sts.close();

    // transformation du resultat en heure et minutes
         double result = (s+f)/60;
         String str = "" + result;
         int index = str.indexOf('.');
         String minutes;
         if(index >= 0){
          minutes = str.substring(index + 1);
        }
        else{
          minutes = "0";
        }

        int heure = (int) result;

        
         
        out.println("h:"+ heure +"m:" + minutes);


      // afficher la photo de profil
        String queryPhoto ="Select photo from utilisateurs where mail='"+utilisateur+"'";
        Statement stp = conn.createStatement();
        stp.execute(queryPhoto);
        String link = stp.getResultSet().getString(1);
        stp.close();



       } catch(Exception e){
        // en cas de problème sql
        out.println(e);
        }
      }
    

  }catch(Exception e){
		// sinon on le redirige
    res.sendRedirect("index.html");
  }finally{
     try{
          conn.close();
        }catch(Exception e){
          
        }
  }
}
}