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
 * Servlet implementation class ListaPelicula
 */
public class ListaPelicula extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	  static final String DB_URL = "jdbc:mysql://localhost/multiteca";
	  static final String USER = "root";
	  static final String PASS = "zubiri";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListaPelicula() {
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
	      String sqlselect = "SELECT * FROM PELICULA";
	      ResultSet rs = stmt.executeQuery(sqlselect);

	      out.print("<html>");
	      out.print("<head><title></title>");
	      out.print("<link rel='stylesheet' href='listapelicula.css' type='text/css'>");
	      out.print("</head>");
	      out.print("<body>");
	      
	      out.println("<table align='center' width='40%' border='10' >  ");
	      out.println("<th> ISAN </th>");
	      out.println("<th> TITULO</th>");
	      out.println("<th> AUTOR </th>");
	      out.println("<th> AÑO DE EDICION </th>");
	      out.println("<th> PRODUCTORA </th>");
	      out.println("<th> INTERPRETE1 </th>");
	      out.println("<th> INTERPRETE2 </th>");
	      out.println("<th> INTERPRETE3 </th>");
	      out.println("</tr>");
	      while (rs.next()) {
	    	 int isanselect = rs.getInt("ISAN");
	        String tituloselect = rs.getString("TITULO");
	        String autorselect = rs.getString("AUTOR");
	        int anoEdicionselect = rs.getInt("AÑOEDICION");
	        String productoraselect = rs.getString("PRODUCTORA");
	        String interprete1select = rs.getString("INTERPRETE1");
	        String interprete2select = rs.getString("INTERPRETE2");
	        String interprete3select = rs.getString("INTERPRETE3");
	        

	        out.println("<tr>");
	        out.println("<td>" + isanselect + "</td>");
	        out.println("<td>" + tituloselect + "</td>");
	        out.println("<td>" + autorselect + "</td>");
	        out.println("<td>" + anoEdicionselect + "</td>");
	        out.println("<td>" + productoraselect + "</td>");
	        out.println("<td>" + interprete1select  + "</td>");
	        out.println("<td>" + interprete2select  + "</td>");
	        out.println("<td>" + interprete3select  + "</td>");
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