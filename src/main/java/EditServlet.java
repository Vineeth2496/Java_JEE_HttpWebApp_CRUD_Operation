

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditServlet extends HttpServlet {
	String driver, URL, user, password;
	public void init(ServletConfig sc) throws ServletException
	{
		ServletContext ct=sc.getServletContext();
		driver=ct.getInitParameter("driver");
		URL=ct.getInitParameter("URL");
		user=ct.getInitParameter("user");
		password=ct.getInitParameter("password");
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String Firstname=request.getParameter("fn");
		String Lastname=request.getParameter("ln");
		String Email=request.getParameter("em");
		String Stdcode=request.getParameter("std");
		String Phoneno=request.getParameter("ph");
		String Username=request.getParameter("un");
		String Password=request.getParameter("pass");
		String Country=request.getParameter("con");
		String Gender=request.getParameter("gm");
		
		PrintWriter out=response.getWriter();
		
		try
			{
					Class.forName(driver).newInstance();
					Connection con=DriverManager.getConnection(URL, user, password);
					PreparedStatement pst=con.prepareStatement("update irctc set Firstname=?, Lastname=?, Email=?, Stdcode=?, Phoneno=?, Username=?, Password=?, Country=?, Gender=? where Username=?");
					pst.setString(1, Firstname);
					pst.setString(2, Lastname);
					pst.setString(3, Email);
					pst.setString(4, Stdcode);
					pst.setString(5, Phoneno);
					pst.setString(6, Username);
					pst.setString(7, Password);
					pst.setString(8, Country);
					pst.setString(9, Gender);
					pst.setString(10, Username);
					
					int i=pst.executeUpdate();
					
					out.println("<html><center><h1><font color='green'>Record Updated</font></h1></center></html>");
					
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
