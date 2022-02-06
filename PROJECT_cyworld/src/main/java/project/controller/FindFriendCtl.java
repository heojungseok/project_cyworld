package project.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.service.CyMiniHpService;
@WebServlet("/cy_find_friend")
public class FindFriendCtl extends HttpServlet {

	CyMiniHpService miniHpService = new CyMiniHpService();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String username = request.getParameter("username");
//		System.out.println("find-f: "+username);
		request.setAttribute("friend", miniHpService.getFindList(username));
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("cy_find_friend.jsp");
		dispatcher.forward(request, response);
		
	}

}
