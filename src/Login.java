

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		String login  = request.getParameter("login");
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		if(login.equals("accountantlogin"))
		{
			try {
				
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/feemanagement","root","1213");
				String qr = "select * from addaccountant";
			    Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(qr);
				
				
			while(rs.next())
			{
					if(id.equals(rs.getString("username")) && pwd.equals(rs.getString("password")))
					{
						request.getRequestDispatcher("accountant.html").forward(request, response);
					}
		    }
			request.getRequestDispatcher("index.html").forward(request, response);
			
			con.close();	
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		else if(login.equals("adminlogin"))
		{
			try {
				
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/feemanagement","root","1213");
				String qr = "select * from admin";
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(qr);
				while(rs.next())
				{
					if(id.equals(rs.getString("id")) && pwd.equals(rs.getString("pwd")))
					{
				      //   request.getSession().invalidate();
				         HttpSession session=request.getSession(true);
				         session.setAttribute("id", "id");
				         request.getRequestDispatcher("admin.html").forward(request, response);;  
					}
                  
				}
				request.getRequestDispatcher("index.html").forward(request, response);
				
				st.close();
				con.close();
				
				
			} catch (Exception e) {
				
				e.printStackTrace();
				out.println(e);
			}
		}
	}

}
