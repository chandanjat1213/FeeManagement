

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UpdateAccountant")
public class UpdateAccountant extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String contact = request.getParameter("contact");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection  con = DriverManager.getConnection("jdbc:mysql://localhost:3306/feemanagement","root","1213");
			String qr = "update addaccountant set name=?,email=?,address=?,contact=?,username=?,password=? where id=?";
			PreparedStatement ps = con.prepareStatement(qr);
			
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, address);
			ps.setString(4, contact);
			ps.setString(5, username);
			ps.setString(6, password);
			ps.setString(7, id);
			
			int i = ps.executeUpdate();
			if(i>0)
			{
				RequestDispatcher rd=request.getRequestDispatcher("View");
				rd.include(request, response);
				out.println("<script>window.alert('Accountant Updated Sucessfully');</script>");
				
				
			}
			else
			{
				out.println("<script>window.alert('Not Updated Sucessfully');</script>");	
			}
			
			
			con.close();
			
		} catch (Exception e) {
			
			out.println(e);
			e.printStackTrace();
		}
		
	}

}
