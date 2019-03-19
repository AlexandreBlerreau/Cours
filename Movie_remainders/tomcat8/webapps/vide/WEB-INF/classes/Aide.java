// Servlet aide
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;

@WebServlet("/aide")
public class Aide extends HttpServlet{
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
      rs = st.executeQuery("Select * from genre");
      out.println("<html>");
      out.println("<head></head>");
      out.println("<body>");
      out.println("<a href='http://localhost:8080/vide/addfilm'><img src='back.png' alt='logo'  height='50' width='50'></a>");
      out.println("<center>");
      out.println("<table>");
      while(rs.next()){
         
        out.println("<tr>");
        out.println("<td> id: " + rs.getInt("idgenre") + " -- nom: " + rs.getString("libelle") + "</td>");
        out.println("</tr>");
        
      }
      out.println("</table>");
      out.println("</center>");
      out.println("</body>");
      out.println("</html>");
    }catch(Exception e){

    }
  }
}