package project.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project.service.CyMemberService;
import project.service.CyMiniHpService;

@WebServlet("/cy_mini")
public class MiniHomeCtl extends HttpServlet {

	CyMemberService memberService = new CyMemberService();
	CyMiniHpService miniHpService = new CyMiniHpService();

	// ���ο��� �α���
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		String userID = request.getParameter("userID");
		String userPW = request.getParameter("userPW");

		// id, pw ���ؼ� idx, username ��������
		String[] userInfo = memberService.memberChk(userID, userPW).split(",");
		System.out.println("ó�� �α���: " + memberService.memberChk(userID, userPW));
		if (memberService.memberChk(userID, userPW) != "not user") {
			int idx = Integer.parseInt(userInfo[0]);
			String username = userInfo[1];
			System.out.println("cy :" + idx + username);
			// session ����
			HttpSession cySession = request.getSession();
			cySession.setAttribute("idx", idx);
			cySession.setAttribute("userID", userID);
			cySession.setAttribute("username", username);

			// �α��� Ȯ��
			if (cySession.getAttribute("idx") != null) {
//				System.out.println("�����۵�"+cySession.getAttribute("idx"));
				int curIdx = Integer.parseInt(String.valueOf(cySession.getAttribute("idx")));

				int h_idx = miniHpService.getMiniHpIdx(curIdx);// �α����� ����� Ȩ�Ƿ� �Ѿ�� ���ؼ� ����
				cySession.setAttribute("MyHidx", h_idx);
				response.sendRedirect("cy_mini?idx=" + h_idx);

			} else {
				response.sendRedirect("cy_main.jsp");
			}

		} else {

			response.sendRedirect("cy_main.jsp");

		}

	}

	// idx �� �Ķ����
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ���� ���� ���� ���
		HttpSession cySession = request.getSession();
		if (cySession.getAttribute("idx") != null) {
			// idx ���� �޾ƿ� ���
			if (request.getParameter("idx") != null) {

				int curIdx = Integer.parseInt(String.valueOf(cySession.getAttribute("idx")));// ���� ������ m_idx
				int h_idx = Integer.parseInt(String.valueOf(request.getParameter("idx")));// h_idx = miniHP �� �� �� �Ѿ����
																							// ���
				int hpOwner_idx = miniHpService.getUserIdx(h_idx);// Ȩ�� ���� m_idx
//				System.out.println(h_idx);
//				System.out.println("curIdx:"+cySession.getAttribute("idx")); 
				String userID = String.valueOf(cySession.getAttribute("userID"));
				String username = String.valueOf(cySession.getAttribute("username"));
				
				// ������ ó���ϴ� ��
				int curCountToday = miniHpService.countToday(hpOwner_idx);// ���� ������ �޾ƿ���
					if (curIdx != hpOwner_idx) {
						
						curCountToday += 1;
//					System.out.println("�ٸ� ��� ���� today: " + curCountToday);
						miniHpService.updateToday(curCountToday, hpOwner_idx);// update today
						
					} else {
//					System.out.println("������ ���� :" + curCountToday);
					
				}

				request.setAttribute("miniData", miniHpService.minihpData_minihp(h_idx));// Ȩ�� ������
				request.setAttribute("following", miniHpService.getFollow(hpOwner_idx, userID));// ���� ������(ArrayList)
//				request.setAttribute("f", miniHpService.followData(h_idx, userID));// ���� ������(��)
				request.setAttribute("isfollow", miniHpService.isFollow(curIdx, hpOwner_idx));// ���� ���� �ľ�
				request.setAttribute("MyHidx", cySession.getAttribute("MyHidx"));// ���� Ȩ�Ƿ� ���ư��� ����

				RequestDispatcher dispatcher = request.getRequestDispatcher("cy_mini.jsp");
				dispatcher.forward(request, response);
				// ����,m_idx �� �޾ƿ� ���
			}

		} else {

			response.sendRedirect("cy_main.jsp");
		}
	}

}
