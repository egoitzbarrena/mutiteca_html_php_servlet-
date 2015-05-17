package com.zubiri.jsp.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Buscarcoche
 */
public class Buscardisco extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Buscardisco() {
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
		response.setContentType( "text/html; charset=iso-8859-1" );
		PrintWriter out = response.getWriter();
	
		//creamos el objeto de conexion
		Connection conexion=null;
		
	    try
	    {
	    	//registramos el JDBC Driver
		    Class.forName("com.mysql.jdbc.Driver");

		    //Abrimos la conexion
		    System.out.println("Conectando con la base de datos...");
		    conexion = DriverManager.getConnection("jdbc:mysql://localhost/multiteca", "root","zubiri");
		    System.out.println("Conexion establecida...");
			
	    }
	    catch(Exception e){
	        out.println("error: " + e);
	    }

	    out.print("<html>");
		out.print("<head><title></title>");
		out.print("</head>");
		out.print("<body>");
		out.println("<table align='center' width='40%' border='10' >  ");
		
		out.println("<td> ISMN </td>");
		out.println("<td> TITULO </td>");
		out.println("<td> AUTOR </td>");
		out.println("<td> AÑO DE EDICION </td>");
		out.println("<td> DISCOGRAFICA </td>");
		out.println("<td> NUMERO DE CANCIONES </td>");
		out.println("</tr>");

		try{     
			//Realizamos la consulta
			Statement st = conexion.createStatement();
		
			ResultSet rs = st.executeQuery("SELECT * FROM DISCO WHERE ISMN= '"+ request.getParameter("ismn") +"'");
					
			while (rs.next()){
				out.println("<tr>");
				out.println("<td>" + rs.getObject("ISMN") + "</td>");
				out.println("<td>" + rs.getObject("TITULO") + "</td>");
				out.println("<td>" + rs.getObject("AUTOR") + "</td>");
				out.println("<td>" + rs.getObject("AÑOEDICION") + "</td>");
				out.println("<td>" + rs.getObject("DISCOGRAFICA") + "</td>");
				out.println("<td>" + rs.getObject("NUMEROCANCIONES") + "</td>");
				out.println("</tr>");		
			}

			

			rs.close();
		}

		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		out.println("</table");

		// Cerramos la conexion a la base de datos. 
		try{
			conexion.close();
		}
		catch(Exception e){
			out.println("Error: " + e);
		}

		//boton para volver
		//<input type="button" value="VOLVER" onClick="location.href='index.html'" />;

		out.print("</body>");
		out.print("</html>");
			
	}		

}
