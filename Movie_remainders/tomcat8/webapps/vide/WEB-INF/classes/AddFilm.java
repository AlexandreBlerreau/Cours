// servlet d'ajout d'un film
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;
import java.util.*;

@WebServlet("/addfilm")
public class AddFilm extends HttpServlet
{
    
    public void doGet( HttpServletRequest req, HttpServletResponse res )throws  IOException{
        PrintWriter out = res.getWriter();
        Connection conn = null;
        Statement st = null;
        Statement st2 = null;
        ResultSet rs = null;
        String dataBase = "jdbc:sqlite:../bdd";
        int id = 0;
        
        // requette permettant de recuperer le dernier id des films
        String queryId = "SELECT idfilm FROM film WHERE idfilm = (SELECT MAX(idfilm) FROM film)";
        // requette permettant de recuperer les id genres
        String queryGenre = "Select * from genre";
        
        HttpSession session = req.getSession();
        int tmp = 0;
        try{
            // recuperation de la valeur de la session
            tmp = (int) session.getAttribute("key");
            
            
            if(tmp == 404 || tmp == 408){
                // l'utilisateur est admin ou modo il peut acceder
                
                try{
                    Class.forName("org.sqlite.JDBC");
                } catch(Exception e){}
                
                
                try {
                    // Connection a la base sqlite3
                    conn = DriverManager.getConnection(dataBase);
                    
                    // requete sql a executer
                    st = conn.createStatement();
                    st.execute(queryId);
                    id = st.getResultSet().getInt(1);
                    // préparation de l'id pour l'ajout d'un nouveau film
                    id = id+1;
                }catch(Exception e){}
                
                // affichage du formulaire
                
                out.println("<html><head><link rel='stylesheet' type='text/css' href='connectionstyle.css'/></head>");
                out.println("<body>");
                
                out.println("<center><h2>Ajouter un film</h2><br /></center>");
                out.println("<form method='post' action='http://localhost:8080/vide/addfilm'>");
                
                out.println("<table id='create'>");
                
                out.println("<tr>");
                out.println("<input type='hidden' name='idfilm' value='"+id+"'></td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td><a>Titre</a><br /><input type='textbox' name='titre' required></td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td><a>Déscription</a><br /><textarea name='description' id='txt' rows='10' cols='30'></textarea></td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td><a>Durée (en minutes)</a><br /><input type='number' name='duree' required></td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td><a>Date de sortie (JJMMAAAA)</a><br /><input type='textbox' name='date' required></td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td><a>Image (lien)</a><br /><input type='textbox' name='image' required></td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td><a>Genre <a href='http://localhost:8080/vide/aide' target=_blank>aide</a></a><br /><select name='genre'>");
                try{
                    st2 = conn.createStatement();
                    rs = st2.executeQuery(queryGenre);
                    // recupération de tout les id film dans une liste déroulante
                    while(rs.next()){
                        out.println("<option>" + rs.getInt("idgenre"));
                        
                    }
                    out.println("</option></select>");
                    
                    out.println("</td>");
                    out.println("</tr>");
                    
                    out.println("</body></html>");
                    
                }catch(Exception e){}
                
                out.println("<tr><td><input type='submit' style='height:30px; background-color: #87CEEB; border: none;font-size: 15px' value='Ajouter'</td></tr>");
                out.println("</form>");
                
            }else{
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
    
    
    
    public void doPost( HttpServletRequest req, HttpServletResponse res )
            throws IOException
    {
        // traitement de l'ajout
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
            // requete d'insertion
            stx.executeUpdate("insert into film (idfilm,titre,description,durée,image,date_sortie,genre) values("+id+",'"+titre+"','"+description+"',"+duree+",'"+image+"','"+datesortie+"',"+genre+")");
            // Si tout se passe bien on redirige l'admin sur l'adminpanel
            res.sendRedirect("http://localhost:8080/vide/adminpanel");
            
        }catch(Exception e){
            // sinon on le fait recommencer
            res.sendRedirect("http://localhost:8080/vide/addfilm");
        }finally{
            try{
                conn.close();
            }catch(Exception e){}
        }
        
        
    }
}