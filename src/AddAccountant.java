

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

/**
 * Servlet implementation class AddAccountant
 */
@WebServlet("/AddAccountant")
public class AddAccountant extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		String id=request.getParameter("id");
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String address=request.getParameter("address");
		String contact=request.getParameter("contact");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		
		if(id.equals("") || name.equals("") || email.equals("")|| address.equals("") || contact.equals("") || username.equals("") || password.equals(""))
		{
				RequestDispatcher rd=request.getRequestDispatcher("addaccountant.html");
				rd.include(request, response);
				out.println("<script>window.alert('Please Enter All Details');</script>");
		}
       
		else{

		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/feemanagement","root","1213");
			String qr = "insert into  addaccountant values(?,?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(qr);
			ps.setString(1, id);
			ps.setString(2, name);
			ps.setString(3, email);
			ps.setString(4, address);
			ps.setString(5, contact);
			ps.setString(6, username);
			ps.setString(7, password);
			
			int i=ps.executeUpdate();
			out.println("<script>window.alert('Accountant Added Sucessfully');</script>");
			RequestDispatcher rd=request.getRequestDispatcher("admin.html");
			rd.include(request, response);
			con.close();

		    
			
		} catch (Exception e) {
			
			out.println(e);
			e.printStackTrace();
		}
		}
		
	}

}
