

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
 * Servlet implementation class AddStudent
 */
@WebServlet("/AddStudent")
public class AddStudent extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		String roll = request.getParameter("roll");
		String name = request.getParameter("name");
		String contact = request.getParameter("contact");
		String address = request.getParameter("address");
		String feedue = request.getParameter("feedue");
		
		if(roll.equals("") || name.equals("") || contact.equals("")|| address.equals("") || feedue.equals(""))
		{
				RequestDispatcher rd=request.getRequestDispatcher("addstudent.html");
				rd.include(request, response);
				out.println("<script>window.alert('Please Enter All Details');</script>");
		}

		else{
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/feemanagement","root","1213");
			String qr = "insert into addstudent values(?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(qr);
			ps.setString(1, roll);
			ps.setString(2, name);
			ps.setString(3, contact);
			ps.setString(4, address);
			ps.setString(5, feedue);
			
			int i = ps.executeUpdate();
			
			RequestDispatcher rs = request.getRequestDispatcher("accountant.html");
			rs.include(request, response);
			out.println("<script>window.alert('successfully added')</script>");
			
		} catch (Exception e) {
			
			out.println(e);
		}
		}
	}

}
