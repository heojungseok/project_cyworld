package project.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project.model.CYMiniHPData;
import project.service.CyMemberService;
import project.service.CyMiniHpService;

@WebServlet("/cy_visitbook")
public class VisitorBookCtl extends HttpServlet {

	CyMiniHpService miniHpService = new CyMiniHpService();
	CyMemberService memberService = new CyMemberService();

	// �̴�Ȩ�ǿ��� ������ �̵��� �ۼ��� �ʿ��� ������ ó���ϴ� ��
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// ������ ���� ��� �ۼ��ߴ��� �˱� ���� m_idx, h_idx �޾Ƴ���
		int h_idx = Integer.parseInt(String.valueOf(request.getParameter("idx")));// h_idx = miniHP �� ��

		HttpSession cySession = request.getSession();
		String userID = String.valueOf(cySession.getAttribute("userID"));
		String username = String.valueOf(cySession.getAttribute("username"));

		if (cySession.getAttribute("idx") != null) {
			int cuIdx = Integer.parseInt(String.valueOf(cySession.getAttribute("idx")));// m_idx = user �� ��
			if (cuIdx != -1) {
				if (h_idx != -1) {

					// �ؿ� post���� �� �ۼ��� ���ؼ� ���
					String hidxNmidx = "" + h_idx + "," + cuIdx;
					request.setAttribute("m_idx", cuIdx);
					request.setAttribute("hidxNmidx", hidxNmidx);

					int minihpUserIdx = memberService.getUserIdx(h_idx);// Ȩ���� idx ���ؼ� Ȩ�� ������ idx �� ��������

					// ���Ͽ��� �����ִ� �����͵�
					request.setAttribute("username", cySession.getAttribute("username"));
					request.setAttribute("miniData", miniHpService.minihpData_minihp(h_idx));// jsp ���Ͽ� ������ ������
					request.setAttribute("visitLog", miniHpService.getVisitLog(cuIdx, h_idx));// ���� ���� ����
					request.setAttribute("following", miniHpService.getFollow(minihpUserIdx, userID));// ���� ������(ArrayList)
//					request.setAttribute("f", miniHpService.followData(h_idx, userID));// ���� ������(��)
					request.setAttribute("isfollow", miniHpService.isFollow(cuIdx, minihpUserIdx));// ���� ���� �ľ�

					RequestDispatcher dispatcher = request.getRequestDispatcher("cy_visitor.jsp");
					dispatcher.forward(request, response);

				}
			}
			// �Խ�Ʈ�� ���� ������ ��
			else {
				// �ؿ� post���� �� �ۼ��� ���ؼ� ���
//				String hidxNmidx = "" + h_idx + "," + m_idx;
//				request.setAttribute("hidxNmidx", hidxNmidx);
//				System.out.println("visitbook :"+hidxNmidx);
				request.setAttribute("m_idx", cuIdx);
				request.setAttribute("miniData", miniHpService.minihpData_minihp(h_idx));// jsp ���Ͽ� ������ ������
				request.setAttribute("visitLog", miniHpService.getVisitLog(cuIdx, h_idx));// ���� ���� ����
				request.setAttribute("following", miniHpService.getFollow(h_idx, userID));// ���� ������(ArrayList)

				RequestDispatcher dispatcher = request.getRequestDispatcher("cy_visitor.jsp");
				dispatcher.forward(request, response);
			}
		} else {

			response.sendRedirect("cy_main.jsp");
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");

		// ���� �ۼ��� ���� �Ķ���� �޾ƿ��� �����ϱ�
		String[] idxArr = String.valueOf(request.getParameter("hidxNmidx")).split(",");
		int h_idx = Integer.parseInt(idxArr[0]);
		int m_idx = Integer.parseInt(idxArr[1]);

		String visitcontent = request.getParameter("visitcontent");
//		int stat = Integer.parseInt(String.valueOf(request.getParameter("stat")));

		int status = request.getParameter("status") == null ? 0
				: Integer.parseInt(String.valueOf(request.getParameter("status")));// status üũ �ȵǾ� ���� ��� ���

//		System.out.println(h_idx+","+m_idx+","+visitcontent+","+status);
		miniHpService.writeVisitlog(m_idx, h_idx, visitcontent, status);
		response.sendRedirect("cy_visitbook?idx=" + h_idx);

	}
}
