

import java.io.IOException;
import java.io.PrintWriter;
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
public class LoginSer extends HttpServlet {
	
	String driver, URL, user, password;
	public void init(ServletConfig scf) throws ServletException
	{
		ServletContext ctx=scf.getServletContext();
		
		driver=ctx.getInitParameter("driver");
		URL=ctx.getInitParameter("URL");
		user=ctx.getInitParameter("user");
		password=ctx.getInitParameter("password");
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String U_Username=request.getParameter("un");
		String U_Password=request.getParameter("pass");
		try
		{
			Class.forName(driver);
			RowSetFactory rf=RowSetProvider.newFactory();
			JdbcRowSet jr=rf.createJdbcRowSet();
			
			jr.setUrl(URL);
			jr.setUsername(user);
			jr.setPassword(password);
			jr.setCommand("select Username,Password from irctc");
			
			jr.execute();
			
			Vector v=new Vector();
			for(;jr.next();)
			{
				v.add(jr.getString("Username"));
				v.add(jr.getString("Password"));
			}
				
			PrintWriter out=response.getWriter();
			
			HttpSession hss=request.getSession();
			
			if(v.contains(U_Username) && v.contains(U_Password))
			{
				hss.setAttribute("u", U_Username);
				hss.setAttribute("p", U_Password);
				
				RequestDispatcher rd=request.getRequestDispatcher("FetchProfile");
				rd.forward(request, response);
			}
			else {
				out.println("<html>");
				out.println("<center>");
				out.println("Invalid Username or Password");
				RequestDispatcher rd=request.getRequestDispatcher("Login.html");
				rd.include(request, response);
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
