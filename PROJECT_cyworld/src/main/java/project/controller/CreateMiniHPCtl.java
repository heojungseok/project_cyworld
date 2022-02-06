package project.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project.service.CyMemberService;
import project.service.CyMiniHpService;

@WebServlet("/cy_create_minihp")
public class CreateMiniHPCtl extends HttpServlet {
	//처음 만들기 
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		String intro1 = request.getParameter("intro1");
		String intro2 = request.getParameter("intro2");
		String domain = request.getParameter("domain");
		int m_idx = Integer.parseInt(String.valueOf(request.getParameter("m_idx")));
		String username = request.getParameter("username");
		
//		System.out.println(intro1+intro2+domain+m_idx);
//		System.out.println(m_idx+username);
		//생성하고 미니홈피 관련 데이터를 담아놓기 MiniHomeCtl 보내기 위해
		CyMiniHpService miniHpService = new CyMiniHpService();
		miniHpService.createMiniHP(intro1, intro2, domain, m_idx);
//		miniHpService.minihpData(m_idx);
		
		response.sendRedirect("cy_main.jsp");
		
	}
}
