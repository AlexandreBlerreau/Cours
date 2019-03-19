import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;
import java.util.*;

@WebServlet("/deletefilmPreferenceUser")
public class DeleteFilmPreferenceUser extends HttpServlet{

  public void doGet( HttpServletRequest req, HttpServletResponse res )throws  IOException{
     PrintWriter out = res.getWriter();
     Connection conn = null;
     Statement st = null;
     ResultSet rs = null;
     String dataBase = "jdbc:sqlite:../bdd";

    try{
      Cookie[] cookies = req.getCookies();
      String iduser = null;
      String idfilm = null;
      for(int i=0; i < cookies.length; i++) {
        Cookie MonCookie = cookies[i];
        if (MonCookie.getName().equals("user")) {
          iduser = cookies[i].getValue();
        }
        if (MonCookie.getName().equals("idfilm")) {
          idfilm = cookies[i].getValue();
        }
      }
      conn = DriverManager.getConnection(dataBase);
      st = conn.createStatement();
      st.executeUpdate("delete from preferences_film where idfilm = " + Integer.parseInt(idfilm) +" AND iduser = "+ Integer.parseInt(iduser));
      res.sendRedirect("http://localhost:8080/vide/menu");
    }catch(Exception e){
      out.println(e.toString());
    }finally{
      try{
        if (rs != null) { rs.close(); }
        if (st != null){
          st.close();
        }
        if (conn != null){
          conn.close();
        } 
      }catch(Exception ioe){
        out.println(ioe);
      }
    }
  }
}