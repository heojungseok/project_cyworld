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
		String userID = String.valueOf(cySession.getAttribute("userID"));// ���� ���� ID
		String curUser = String.valueOf(cySession.getAttribute("username"));// ���� ������ �̸�
		int m_idx = Integer.parseInt(String.valueOf(cySession.getAttribute("idx")));// ���� ������ m_idx

		String h_username = String.valueOf(request.getParameter("h_username"));// Ȩ�� ���� �̸�
		int h_idx = Integer.parseInt(String.valueOf(request.getParameter("h_idx")));// Ȩ�� ���� h_idx
//		System.out.println("Ȩ�� ���� m_idx: "+miniHpService.getUserIdx(h_idx));
		int m_idx_2 = miniHpService.getUserIdx(h_idx);// Ȩ�� ���� m_idx

		if (h_idx != 0 && h_username != null) { // �Ķ���� �ȹ޾ƿ��� �� ���
			if (m_idx != -1) {// �Խ�Ʈ�� ���� ������ ���
				// ���� ��û�ϴ� ����
				miniHpService.followCyUser(m_idx, m_idx_2, curUser, h_username);

				response.sendRedirect("cy_mini?idx=" + h_idx);// ���� �ΰ� �ش� �������� �����ִ� �� ó�� ���̱�

			} else {

				response.sendRedirect("cy_alert.jsp");

			}

		}
	}
}
