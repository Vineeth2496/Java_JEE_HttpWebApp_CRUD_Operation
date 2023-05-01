

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class RegisterSer extends HttpServlet {
	
	String driver, URL, user, password;
	public void init(ServletConfig scf) throws ServletException
	{
		ServletContext ctx=scf.getServletContext();
		
		driver=ctx.getInitParameter("driver");
		URL=ctx.getInitParameter("URL");
		user=ctx.getInitParameter("user");
		password=ctx.getInitParameter("password");
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String Firstname=request.getParameter("fn");
		String Lastname=request.getParameter("ln");
		String Email=request.getParameter("em");
		String Stdcode=request.getParameter("std");
		String Phoneno=request.getParameter("ph");
		String Username=request.getParameter("un");
		String Password=request.getParameter("pass");
		String Country=request.getParameter("con");
		String Gender=request.getParameter("gm");
		try
		{
			Class.forName(driver).newInstance();
			Connection con=DriverManager.getConnection(URL, user, password);
			System.out.println(con);
			
		//	Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
		//	Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "vine96");
		//	System.out.println(con);
			
			PreparedStatement ps=con.prepareStatement("insert into irctc values(?,?,?,?,?,?,?,?,?)");
			ps.setString(1, Firstname);
			ps.setString(2, Lastname);
			ps.setString(3, Email);
			ps.setString(4, Stdcode);
			ps.setString(5, Phoneno);
			ps.setString(6, Username);
			ps.setString(7, Password);
			ps.setString(8, Country);
			ps.setString(9, Gender);
			
			int i=ps.executeUpdate();
			
			PrintWriter out=response.getWriter();
			
			out.println("IRCTC Account is registerd: "+"\n"+"Username: "+Username+"\n"+"Password: "+Password);
			
		    out.println("Driver class name	:"+driver);
			out.println("URL				:"+URL);
			out.println("Username			:"+user);
			out.println("Password			:"+password);		    
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
	
	public void destroy()
	{
		
	}
}
