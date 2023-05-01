

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class Editreturn extends HttpServlet {
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
					PreparedStatement pst=con.prepareStatement("select * from irctc where Username=?");
					pst.setString(1, eid);
					ResultSet rs=pst.executeQuery();
					
					while(rs.next())
					{
						out.println("<html>");
						out.println("<center>");
						out.println("<form action='EditServlet'>");
						out.println("<table border='1'>");
						
						out.println("<tr>	"+"<th style='text-align: left;'>"+"First Name"+"</th>"+"<td><input type='text' name='fn' id='fn' value='"+rs.getString(1)+"'/></td></tr>");
						out.println("<tr>	"+"<th style='text-align: left;'>"+"Last Name"+"</th>"+"<td><input type='text' name='ln' id='ln' value='"+rs.getString(2)+"'/></td></tr>");
						out.println("<tr>	"+"<th style='text-align: left;'>"+"Email"+"</th>"+"<td><input type='email' name='em' id='fn' value='"+rs.getString(3)+"'/></td></tr>");
						out.println("<tr>	"+"<th style='text-align: left;'>"+"Country Code"+"</th>"+"<td><input type='text' name='std' id='std' value='"+rs.getString(4)+"'/></td></tr>");
						out.println("<tr>	"+"<th style='text-align: left;'>"+"Phone No"+"</th>"+"<td><input type='text' name='ph' id='ph' value='"+rs.getString(5)+"'/></td></tr>");
						out.println("<tr>	"+"<th style='text-align: left;'>"+"Username"+"</th>"+"<td><input type='text' name='un' id='un' value='"+rs.getString(6)+"'/></td></tr>");
						out.println("<tr>	"+"<th style='text-align: left;'>"+"Password"+"</th>"+"<td><input type='text' name='pass' id='pass' value='"+rs.getString(7)+"'/></td></tr>");
						out.println("<tr>	"+"<th style='text-align: left;'>"+"Country"+"</th>"+"<td><input type='text' name='con' id='con' value='"+rs.getString(8)+"'/></td></tr>");
						out.println("<tr>	"+"<th style='text-align: left;'>"+"Gender"+"</th>"+"<td><input type='text' name='gm' id='gm' value='"+rs.getString(9)+"'/></td></tr>");
						
						out.println("<tr>	<td colspan='2' style='text-align: center;'><input type='submit' value='Edit'/></td></tr>");
						
						out.println("</table>");
						out.println("</form>");
						out.println("</center>");
						out.println("</html>");
					}
					
					
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
