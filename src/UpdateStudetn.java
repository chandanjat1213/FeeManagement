

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
 * Servlet implementation class UpdateStudetn
 */
@WebServlet("/UpdateStudetn")
public class UpdateStudetn extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		String roll = request.getParameter("roll");
		String name = request.getParameter("name");
		String contact = request.getParameter("contact");
		String address = request.getParameter("address");
		String feedue = request.getParameter("feedue");
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/feemanagement","root","1213");
			String qr = "update addstudent set name=?,contact=?,address=?,feedue=? where roll=?";
			PreparedStatement ps = con.prepareStatement(qr);
			ps.setString(1, name);
			ps.setString(2, contact);
			ps.setString(3, address);
			ps.setString(4, feedue);
			ps.setString(5, roll);
			
			int i = ps.executeUpdate();
			
			RequestDispatcher rs = request.getRequestDispatcher("ViewStudent");
			rs.include(request, response);
			out.println("successfully updated");
			
		} catch (Exception e) {
			
			out.println(e);
		}
	}

}
