

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
 * Servlet implementation class DeleteAccountant
 */
@WebServlet("/DeleteAccountant")
public class DeleteAccountant extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		String id = request.getParameter("id");
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/feemanagement","root","1213");
			String qr = "delete from addaccountant where id=?";
			PreparedStatement ps = con.prepareStatement(qr);
			ps.setString(1, id);
			int i = ps.executeUpdate();
			if(i>0)
			{
				RequestDispatcher rs = request.getRequestDispatcher("View");
				rs.include(request, response);
				out.println("<script>window.alert('Delete successfully')</script>");
			}
			
		} catch (Exception e) {
			
			out.println(e);
		}
	}

}
