package project.test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/test")
public class TestServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userID = request.getParameter("userID");
		String userPW = request.getParameter("userPW");
		
		System.out.println("cyworld :"+userID+","+userPW);
	}
}
