

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
 * Servlet implementation class DeleteStudent
 */
@WebServlet("/DeleteStudent")
public class DeleteStudent extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		String roll = request.getParameter("roll");
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/feemanagement","root","1213");
			String qr = "delete from addstudent where roll=?";
			PreparedStatement ps = con.prepareStatement(qr);
			ps.setString(1, roll);
			int i = ps.executeUpdate();
			
			if(i>0)
			{
				RequestDispatcher rs = request.getRequestDispatcher("ViewStudent");
				rs.include(request, response);
				out.println("successfully deleted");
			}
			else
			{
				out.println("somthing went wrong");
			}
			
		} catch (Exception e) {
			
			out.println(e);
		}
	}

}
