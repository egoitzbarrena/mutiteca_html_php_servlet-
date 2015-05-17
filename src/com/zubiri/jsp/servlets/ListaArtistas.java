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

/**
 * Servlet implementation class ListaArtistas
 */
public class ListaArtistas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListaArtistas() {
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

	    Connection conn = null;
	    Statement stmt = null;
	    try
	    {
	      Class.forName("com.mysql.jdbc.Driver");

	      System.out.println("Conenctando a la base de datos seleccionada...");
	      conn = DriverManager.getConnection("jdbc:mysql://localhost/multiteca", "root", "zubiri");
	      System.out.println("Conexion a la base de datos establecida...");

	      stmt = conn.createStatement();
	      String sqlselect = "SELECT * FROM ARTISTA";
	      ResultSet rs = stmt.executeQuery(sqlselect);

	      out.print("<html>");
	      out.print("<head><title></title>");
	      out.print("<link rel='stylesheet' href='listaartista.css' type='text/css'>");
	      out.print("</head>");
	      out.print("<body>");
	      out.println("<table align='left' width='40%' border='10' >  ");
	      out.println("<th>	ID_ARTISTA</th>");
	      out.println("<th> NOMBRE</th>");
	      out.println("<th> TIPO</th>");
	      out.println("<th> AÑONACIMIENTO</th>");
	      out.println("<th> FOTO</th>");
	      out.println("</tr>");
	      while (rs.next()) {
	    	  
	    	int idartistaselect = rs.getInt("ID_ARTISTA");
	    	String nombreselect = rs.getString("NOMBRE");
	        String tiposelect = rs.getString("TIPO");
	        int anoNacimientoselect = rs.getInt("AÑONACIMIENTO");
	        String fotoselect = rs.getString("FOTO");
	        

	        out.println("<tr>");
	        out.println("<td>" + idartistaselect + "</FONT></td>");
	        out.println("<td>" + nombreselect + "</FONT></td>");
	        out.println("<td>" + tiposelect + "</FONT></td>");
	        out.println("<td>" + anoNacimientoselect + "</FONT></td>");
	        out.println("<td><img align='center 'width='100' height='100' src='" + fotoselect + "' />" + "</td>");
	        out.println("</tr>");
	      }
	      rs.close();
	      out.println("</table");
	      out.print("</body>");
	      out.print("</html>");
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