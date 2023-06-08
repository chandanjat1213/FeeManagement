

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

/**
 * Servlet implementation class View
 */
@WebServlet("/View")
public class View extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/feemanagement","root","1213");
			String qr = "select * from addaccountant";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(qr);
			
			if(rs.next())
			{
				request.getRequestDispatcher("admin.html").include(request, response);
				
				
				out.println("<table align='center' border='1px'>");
				
				out.println("<tr>");
				out.println("<td>Id</td>");
				out.println("<td>Name</td>");
				out.println("<td>Email</td>");
				out.println("<td>Address</td>");
				out.println("<td>Contact</td>");
				out.println("<td>Username</td>");
				out.println("<td>Password</td>");
				out.println("<td>Action</td>");
				
				out.println("</tr>");
				
				do{
					
					String id=rs.getString("id");
					String name=rs.getString("name");
					String email=rs.getString("email");
					String address=rs.getString("address");
					String contact=rs.getString("contact");
					String username=rs.getString("username");
					String password=rs.getString("password");

					
					out.println("<tr>");
					out.println("<form action='UpdateAccountant'>");
					out.println("<td>");
					out.println("<input type=hidden name=id value="+id+">"+id);
					out.println("</td>");
					out.println("<td>");
					out.println("<input type=text name=name value="+name+">");
					out.println("</td>");
					out.println("<td>");
					out.println("<input type=email name=email value="+email+">");
					out.println("</td>");
					out.println("<td>");
					out.println("<input type=text name=address value="+address+">");
					out.println("</td>");
					out.println("<td>");
					out.println("<input type=text name=contact value="+contact+">");
					out.println("</td>");
					out.println("<td>");
					out.println("<input type=text name=username value="+username+">");
					out.println("</td>");
					out.println("<td>");
					out.println("<input type=text name=password value="+password+">");
					out.println("</td>");
					out.println("<td>");
					out.println("<input type=submit value=Update>");
					out.println("</td>");

					out.println("</form>");
					out.println("<td>");
	    			out.println("<a href=DeleteAccountant?id="+id+">Delete</a>");
	    			out.println("</td>");

					out.println("</tr>");
					
					
				}while(rs.next());
				
			  out.println("</table>");
			}
			else
			{
				out.println("no data found");
			}
			
			con.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
