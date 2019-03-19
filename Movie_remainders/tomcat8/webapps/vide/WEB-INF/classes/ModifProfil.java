import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;
import java.util.*;

@WebServlet("/modifProfil")
public class ModifProfil extends HttpServlet{

  // sert a la vérification de l'email
    String oldmail;
    boolean erreur = false;

  public void doGet( HttpServletRequest req, HttpServletResponse res )
  throws  IOException
  {
    PrintWriter out = res.getWriter();
    Connection conn = null;
    Statement st = null;
    ResultSet rs = null;
    Statement stid = null;
    
    
    String dataBase = "jdbc:sqlite:../bdd";
    int tmp = 0;
    
    String iduser = null;
    int id = 0;
    
    
    Cookie[] cookies = req.getCookies();
    for(int i=0; i < cookies.length; i++) {
      Cookie MonCookie = cookies[i];
      if (MonCookie.getName().equals("user")) {
        iduser = cookies[i].getValue();
      }
    }
    
    
    HttpSession session = req.getSession();
    
    try{
            // recuperation de la valeur de la session
      tmp = (int) session.getAttribute("key");
      
      if(tmp == 42 || tmp == 408){
                // l'utilisateur est admin
        try{
          Class.forName("org.sqlite.JDBC");
        }catch(Exception e){}
        
        
        try {
                    // Connection a la base sqlite3
          conn = DriverManager.getConnection(dataBase);
          
                    // requete sql a executer
          String query = "select * from utilisateurs where iduser='"+Integer.parseInt(iduser)+"'";
          st = conn.createStatement();
          rs = st.executeQuery(query);
          
          
          out.println("<html><head><link rel='stylesheet' type='text/css' href='connectionstyle.css'/></head>");
          out.println("<body>");
          out.println("<a href='http://localhost:8080/vide/manageuser'><img src='back.png' alt='logo'  height='50' width='50'></a>");
          out.println("<center><h2>Modifier votre profil</h2><br /></center>");
          if(erreur){
            // si erreur alors on affiche en rouge un message
          out.println("<center><i>Adresse email déjà existante !</i></center>");
          erreur = !erreur;
          }
          out.println("<form method='post' action='http://localhost:8080/vide/modifProfil'>");
          
          out.println("<table id='create'>");
          
          out.println("<tr>");
          out.println("<input type='hidden' name='iduser' value='"+Integer.parseInt(iduser)+"'></td>");
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
            // stockage du mail avant changement
          oldmail = rs.getString("mail");
          out.println("</tr>");
          out.println("<tr>");
          out.println("<td><a>Mot de passe</a><br /><input type='password' value='"+ rs.getString("mdp") +"' name='password' required></td>");
          out.println("</tr>");
          out.println("<tr>");
          out.println("<td><a>Photo (lien)</a><br /><input type='textbox'  value='"+ rs.getString("photo") +"'name='image' required></td>");
          out.println("</tr>");
          out.println("<tr>");
          out.println("</body></html>");
          
          out.println("<tr><td><input type='submit' name='mod' style='height:30px; background-color: #87CEEB; border: none;font-size: 15px' value='Modifier'</td></tr>");
          out.println("<tr><td><input type='submit' name='suppr' style='height:30px; background-color: #87CEEB; border: none;font-size: 15px' value='Supprimer mon compte'</td></tr>");
          out.println("</form>");
          
          
          
        }catch(Exception e){
          out.println(e);
        }finally{
          try{
            conn.close();
          }catch(Exception e){}
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
    Statement st2 = null;
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
       conn = DriverManager.getConnection(dataBase);
    } catch(Exception e){}


    if(req.getParameter("suppr") != null){
      SupprUser(conn,id,res);



    }

    
    
  if(req.getParameter("mod") != null){  
    // Traitement de la demande de modification
    try {
            // Connection a la base sqlite3
     


      // vérification que l'email n'est pas utilisée 
      st2 = conn.createStatement();
      st2.execute("Select count(mail) from utilisateurs where mail='"+mail+"'");
      int tmp = st2.getResultSet().getInt(1);
      out.println(tmp);
      st2.close();
      if( tmp >= 1 && !mail.equals(oldmail)){
        mail = oldmail;
        erreur = true;
        // l'email existe deja et n'appartien pas a l'utilisateur, on le redirige avec l'erreur
        res.sendRedirect("http://localhost:8080/vide/modifProfil");
      }
      
      
            // requete sql a executer
      stx = conn.createStatement();
      String query = "update utilisateurs set nom='"+nom+"',prenom='"+prenom+"',age="+age+",mail='"+mail+"',photo='"+photo+"',mdp='"+passwd+"' where iduser="+id+"";
      stx.executeUpdate(query);
      res.sendRedirect("http://localhost:8080/vide/manageuser");
      
    }catch(Exception e){
      out.println(e);
    }
  }
  }

  private void SupprUser(Connection conn,int id,HttpServletResponse res){
    try{
      Statement st = conn.createStatement();
      st.executeUpdate("delete from utilisateurs where iduser="+id+"");
      st.close();
      res.sendRedirect("index.html");
    }catch(Exception e){}


  }

}
