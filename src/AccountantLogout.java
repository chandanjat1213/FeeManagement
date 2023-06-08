

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AccountantLogout
 */
@WebServlet("/AccountantLogout")
public class AccountantLogout extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		HttpSession se=request.getSession();
		se.getAttribute("id");
		se.invalidate();
		RequestDispatcher rd=request.getRequestDispatcher("index.html");
		rd.include(request, response);
		out.println("<script>window.alert('Logout');</script>");

	}

}
