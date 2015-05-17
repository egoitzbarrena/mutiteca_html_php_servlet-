package com.zubiri.jsp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zubiri.multiteca.Artista;
import com.zubiri.multiteca.Pelicula;

/**
 * Servlet implementation class Insertarcoche
 */
public class Insertarpelicula extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	/**
     * @see HttpServlet#HttpServlet()
     */
    public Insertarpelicula() {
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
		//recogemos los valores del formulario en sus respectivas variables y
		
		//accedemos a la base de datos
		Connection conexion = null;
				
		Artista autor = new Artista(request.getParameter("nombreautor"),Integer.parseInt(request.getParameter("añonacimientoautor")));
		
		Pelicula peli = new Pelicula(request.getParameter("isan"),request.getParameter("titulo"),autor,Integer.parseInt(request.getParameter("anoedicion")),request.getParameter("productora"),request.getParameter("interprete1"),request.getParameter("interprete2"),request.getParameter("interprete3"));
		
	    try{
	    	//registramos el JDBC Driver
		    Class.forName("com.mysql.jdbc.Driver");

		    //Abrimos la conexion
		    System.out.println("Conectando con la base de datos...");
		    conexion = DriverManager.getConnection("jdbc:mysql://localhost/multiteca", "root","zubiri");
		    System.out.println("Conexion establecida...");
		}
	    
	    catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		//si no existe creamos la tabla PELICULA
	    try{
	    	
	    	Statement st = conexion.createStatement();
	    	st.executeUpdate("CREATE TABLE IF NOT EXISTS PELICULA(ISAN VARCHAR(255) not NULL,TITULO VARCHAR(255),AUTOR VARCHAR(255),AÑOEDICION INTEGER,PRODUCTORA VARCHAR(255),INTERPRETE1 VARCHAR(255),INTERPRETE2 VARCHAR(255),INTERPRETE3 VARCHAR(255),PRIMARY KEY ( ISAN ))");
	    	
	    }
	    catch(Exception e){
	        out.println("error: " + e);
	    }
		
		//insertamos los datos del formulario en la BD
		try{
			Statement st = conexion.createStatement();	
	    	st.executeUpdate("INSERT INTO PELICULA (ISAN,TITULO,AUTOR,AÑOEDICION,PRODUCTORA,INTERPRETE1,INTERPRETE2,INTERPRETE3) "
                      + "VALUES ('"+peli.getIsan()+"','"+peli.getTitulo()+"','"+peli.getAutor().getNombre()+"','"+peli.getAñoEdicion()+"','"+peli.getProductora()+"','"+peli.getInterprete1()+"','"+peli.getInterprete2()+"','"+peli.getInterprete3()+"')");    
		}

		catch(Exception e){
			out.println("Error: " + e);
		}
		
	    //Diseño de la tabal
		out.print("<html>");
		out.print("<head><title></title>");
		out.print("</head>");
		out.print("<body>");
		out.println("<table align='center' width='40%' border='10' >  ");
		out.println("</tr>");
		out.println("<td> ISAN </td>");
		out.println("<td> TITULO </td>");
		out.println("<td> NOMBRE AUTOR </td>");
		out.println("<td> AÑO DE EDICION </td>");
		out.println("<td> PRODUCTORA </td>");
		out.println("<td> INTERPRETE1 </td>");
		out.println("<td> INTERPRETE2 </td>");
		out.println("<td> INTERPRETE3 </td>");
		out.println("</tr>");
	
		try{
	    				    
		    //Realizamos la consulta
			Statement st = conexion.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM PELICULA"); 
			//mientras haya registros en la BD se muestran en la tabla
			while (rs.next()){
				out.println("<tr>");
				out.println("<td>" + rs.getObject("ISAN") + "</td>");
				out.println("<td>" + rs.getObject("TITULO") + "</td>");
				out.println("<td>" + rs.getObject("AUTOR") + "</td>");
				out.println("<td>" + rs.getObject("AÑOEDICION") + "</td>");
				out.println("<td>" + rs.getObject("PRODUCTORA") + "</td>");
				out.println("<td>" + rs.getObject("INTERPRETE1") + "</td>");
				out.println("<td>" + rs.getObject("INTERPRETE2") + "</td>");
				out.println("<td>" + rs.getObject("INTERPRETE3") + "</td>");
				out.println("</tr>");
			}

				rs.close();
		}

		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	
		out.println("</table");
	
	
		out.println("</body>");
		out.println("</html>");
		
		// Cerramos la conexion a la base de datos. 
		try{
			conexion.close();
		}
		catch(Exception e){
			out.println("Error: " + e);
		}
	}
}
