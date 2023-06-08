

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ViewStudent
 */
@WebServlet("/ViewStudent")
public class ViewStudent extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/feemanagement","root","1213");
			String qr = "select * from addstudent";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(qr);
			
			if(rs.next())
			{
				
				
				request.getRequestDispatcher("accountant.html").include(request, response);
				
				out.println("<table align='center' border='1px'>");
				out.println("<tr>");
				out.println("<td>Roll</td>");
				out.println("<td>Name</td>");
				out.println("<td>Contact</td>");
				out.println("<td>Address</td>");
				out.println("<td>Feedue</td>");
				out.println("<tr>");
				do{
					
					String roll = rs.getString("roll");
					String name = rs.getString("name");
					String contact = rs.getString("contact");
					String address = rs.getString("address");
					String feedue = rs.getString("feedue");
					
					out.println("<tr>");
					out.println("<form action='UpdateStudetn'>");
					out.println("<td>");
					out.println("<input type=hidden name=roll value="+roll+" >"+roll);
					out.println("</td>");
					out.println("<td>");
					out.println("<input type=text name=name value="+name+">");
					out.println("</td>");
					out.println("<td>");
					out.println("<input type=text name=contact value="+contact+">");
					out.println("</td>");
					out.println("<td>");
					out.println("<input type=text name=address value="+address+">");
					out.println("</td>");
					out.println("<td>");
					out.println("<input type=text name=feedue value="+feedue+">");
					out.println("</td>");
					out.println("<td>");
				    out.println("<button>Update</button");
					out.println("</td>");

					out.println("</form>");
					out.println("<td>");
					out.println("<a href=DeleteStudent?roll="+roll+">Delete</a>");
					out.println("</td>");

					out.println("</tr>");
					
					
				}while(rs.next());
				
				out.println("</table>");
			}
			else	
			{
				out.println("no data found");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
