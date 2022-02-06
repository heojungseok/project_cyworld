package project.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project.service.CyMiniHpService;

@WebServlet("/cy_follow_user")
public class FollowUserCtl extends HttpServlet {

	CyMiniHpService miniHpService = new CyMiniHpService();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		HttpSession cySession = request.getSession();
		String userID = String.valueOf(cySession.getAttribute("userID"));// 현재 접속 ID
		String curUser = String.valueOf(cySession.getAttribute("username"));// 현재 접속자 이름
		int m_idx = Integer.parseInt(String.valueOf(cySession.getAttribute("idx")));// 현재 접속한 m_idx

		String h_username = String.valueOf(request.getParameter("h_username"));// 홈피 주인 이름
		int h_idx = Integer.parseInt(String.valueOf(request.getParameter("h_idx")));// 홈피 주인 h_idx
//		System.out.println("홈피 주인 m_idx: "+miniHpService.getUserIdx(h_idx));
		int m_idx_2 = miniHpService.getUserIdx(h_idx);// 홈피 주인 m_idx

		if (h_idx != 0 && h_username != null) { // 파라미터 안받아왔을 시 대비
			if (m_idx != -1) {// 게스트로 일촌 맺음을 대비
				// 일촌 신청하는 서비스
				miniHpService.followCyUser(m_idx, m_idx_2, curUser, h_username);

				response.sendRedirect("cy_mini?idx=" + h_idx);// 일촌 맺고 해당 페이지에 남아있는 것 처럼 보이기

			} else {

				response.sendRedirect("cy_alert.jsp");

			}

		}
	}
}
