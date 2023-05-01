

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
public class Delete extends HttpServlet {
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

		String eid=request.getParameter("username");
		PrintWriter out=response.getWriter();
		
		try
			{
					Class.forName(driver).newInstance();
					Connection con=DriverManager.getConnection(URL, user, password);
					PreparedStatement pst=con.prepareStatement("delete from irctc  where Username=?");
					pst.setString(1, eid);
					
					int i=pst.executeUpdate();
					
					out.println("<html><center><h1><font color='green'>Record Deleted</font></h1></center></html>");
					
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
