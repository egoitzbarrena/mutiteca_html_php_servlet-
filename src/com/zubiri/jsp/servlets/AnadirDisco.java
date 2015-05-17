package com.zubiri.jsp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zubiri.multiteca.Artista;
import com.zubiri.multiteca.Disco;
import com.zubiri.multiteca.*;


/**
 * Servlet implementation class AnadirDisco
 */
public class AnadirDisco extends HttpServlet {
	private static final long serialVersionUID = 1L;
	  static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	  static final String DB_URL = "jdbc:mysql://localhost/multiteca";
	  static final String USER = "root";
	  static final String PASS = "zubiri";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnadirDisco() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=iso-8859-1");
	    PrintWriter out = response.getWriter();
	    String ismnfinal = request.getParameter("ismn");
	    String titulofinal = request.getParameter("titulo");
	    String autorfinal = request.getParameter("autor");
	    String anoEdicion2 = request.getParameter("anoEdicion");
	    int anoEdicionfinal = Integer.parseInt(anoEdicion2);
	    String discograficafinal = request.getParameter("discografica");
	    String numCanciones2 = request.getParameter("numCanciones");
	    int numCancionesfinal = Integer.parseInt(numCanciones2);

	    Connection conn = null;
	    Statement stmt = null;
	    Artista art = new Artista(autorfinal,0);
	    String nombre = art.getNombre();
	    System.out.println("el nombre del autor es" + nombre);
	    Disco disco = new Disco(titulofinal,art,anoEdicionfinal,ismnfinal,discograficafinal,numCancionesfinal);
	    
	    try
	    {
	    	
	    	if (disco.getIsmn() == null)
	        {
	          out.print("<html>");
	          out.print("<head><title></title>");
	          out.print("</head>");
	          out.print("<body>");
	          out.print("no has metido bien el identificador : son 13 numeros");
	          out.print("</body>");
	          out.print("</html>");
	        }
	        else
	        {
	    	
	      Class.forName("com.mysql.jdbc.Driver");

	      System.out.println("Conenctando a la base de datos seleccionada...");
	      conn = DriverManager.getConnection("jdbc:mysql://localhost/multiteca", "root", "zubiri");
	      System.out.println("Conexion a la base de datos establecida...");

	      System.out.println("Creando la tabla en la base de datos...");
	      stmt = conn.createStatement();
	      
	      

	      String sql = "CREATE TABLE IF NOT EXISTS DISCO (ISMN VARCHAR(200) ,TITULO VARCHAR(50) not NULL , AUTOR VARCHAR(30) not NULL,  AÑOEDICION INTEGER, DISCOGRAFICA VARCHAR(50), NUMEROCANCIONES INTEGER, PRIMARY KEY ( ISMN ))";

	      stmt.executeUpdate(sql);
	      
	      
	      
	      stmt.executeUpdate("INSERT INTO DISCO (ISMN,TITULO,AUTOR,AÑOEDICION,DISCOGRAFICA,NUMEROCANCIONES) VALUES ('" + 
	        ismnfinal + "','" + titulofinal + "','" + autorfinal + "','" + anoEdicionfinal + "','" + discograficafinal + "','" + numCancionesfinal + "')");

	      String sqlselect = "SELECT * FROM DISCO";
	      ResultSet rs = stmt.executeQuery(sqlselect);
	      out.print("<html>");
	      out.print("<head><title></title>");
	      out.print("<link rel='stylesheet' href='insertardisco.css' type='text/css'>");
	      out.print("</head>");
	      out.print("<body>");
	      out.println("<table align='center' width='40%' border='10' >  ");
	      out.println("<th>	ISMN</th>");
	      out.println("<th> TITULO </th>");
	      out.println("<th> AUTOR </th>");
	      out.println("<th> AÑO DE EDICION </th>");
	      out.println("<th> DISCOGRAFICA</th>");
	      out.println("<th> NUMERO DE CANCIONES</th>");
	      out.println("</tr>");
	      while (rs.next()) {
	    	String ismnselect = rs.getString("ISMN");
	        String tituloselect = rs.getString("TITULO");
	        String autorselect = rs.getString("AUTOR");
	        int anoEdicionselect = rs.getInt("AÑOEDICION");
	        String discograficaselect = rs.getString("DISCOGRAFICA");
	        int numCancionesselect = rs.getInt("NUMEROCANCIONES");
	        

	        out.println("<tr>");
	        out.println("<td>" + ismnselect + "</td>");
	        out.println("<td>" + tituloselect + "</td>");
	        out.println("<td>" + autorselect + "</td>");
	        out.println("<td>" + anoEdicionselect + "</td>");
	        out.println("<td>" + discograficaselect + "</td>");
	        out.println("<td>" + numCancionesselect  + "</td>");
	        out.println("</tr>");
	      }
	      rs.close();
	      out.println("</table");
	      out.print("</body>");
	      out.print("</html>");
	    }
	    }
	    catch (SQLException se)
	    {
	      se.printStackTrace();
	      try
	      {
	        if (stmt != null)
	          conn.close();
	      } catch (SQLException localSQLException1) {
	      }
	      try {
	        if (conn != null)
	          conn.close();
	      } catch (SQLException sd) {
	        se.printStackTrace();
	      }
	    }
	    catch (Exception e)
	    {
	      e.printStackTrace();
	      try
	      {
	        if (stmt != null)
	          conn.close();
	      } catch (SQLException localSQLException2) {
	      }
	      try {
	        if (conn != null)
	          conn.close();
	      } catch (SQLException se) {
	        se.printStackTrace();
	      }
	    }
	    finally
	    {
	      try
	      {
	        if (stmt != null)
	          conn.close();
	      } catch (SQLException localSQLException3) {
	      }
	      try {
	        if (conn != null)
	          conn.close();
	      } catch (SQLException se) {
	        se.printStackTrace();
	      }
	    }
	    System.out.println("Adios y vuelve cuando quieras");
	    }
	}
	