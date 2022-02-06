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

	// 미니홈피에서 방명록의 이동과 작성에 필요한 데이터 처리하는 곳
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 방명록을 누가 어디에 작성했는지 알기 위해 m_idx, h_idx 받아놓기
		int h_idx = Integer.parseInt(String.valueOf(request.getParameter("idx")));// h_idx = miniHP 의 것

		HttpSession cySession = request.getSession();
		String userID = String.valueOf(cySession.getAttribute("userID"));
		String username = String.valueOf(cySession.getAttribute("username"));

		if (cySession.getAttribute("idx") != null) {
			int cuIdx = Integer.parseInt(String.valueOf(cySession.getAttribute("idx")));// m_idx = user 의 것
			if (cuIdx != -1) {
				if (h_idx != -1) {

					// 밑에 post에서 글 작성을 위해서 담기
					String hidxNmidx = "" + h_idx + "," + cuIdx;
					request.setAttribute("m_idx", cuIdx);
					request.setAttribute("hidxNmidx", hidxNmidx);

					int minihpUserIdx = memberService.getUserIdx(h_idx);// 홈피의 idx 통해서 홈피 주인의 idx 값 가져오기

					// 방명록에서 보여주는 데이터들
					request.setAttribute("username", cySession.getAttribute("username"));
					request.setAttribute("miniData", miniHpService.minihpData_minihp(h_idx));// jsp 파일에 보여질 데이터
					request.setAttribute("visitLog", miniHpService.getVisitLog(cuIdx, h_idx));// 방명록 내용 보기
					request.setAttribute("following", miniHpService.getFollow(minihpUserIdx, userID));// 일촌 데이터(ArrayList)
//					request.setAttribute("f", miniHpService.followData(h_idx, userID));// 일촌 데이터(모델)
					request.setAttribute("isfollow", miniHpService.isFollow(cuIdx, minihpUserIdx));// 일촌 유무 파악

					RequestDispatcher dispatcher = request.getRequestDispatcher("cy_visitor.jsp");
					dispatcher.forward(request, response);

				}
			}
			// 게스트로 방명록 입장할 때
			else {
				// 밑에 post에서 글 작성을 위해서 담기
//				String hidxNmidx = "" + h_idx + "," + m_idx;
//				request.setAttribute("hidxNmidx", hidxNmidx);
//				System.out.println("visitbook :"+hidxNmidx);
				request.setAttribute("m_idx", cuIdx);
				request.setAttribute("miniData", miniHpService.minihpData_minihp(h_idx));// jsp 파일에 보여질 데이터
				request.setAttribute("visitLog", miniHpService.getVisitLog(cuIdx, h_idx));// 방명록 내용 보기
				request.setAttribute("following", miniHpService.getFollow(h_idx, userID));// 일촌 데이터(ArrayList)

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

		// 방명록 작성을 위한 파라미터 받아오고 생성하기
		String[] idxArr = String.valueOf(request.getParameter("hidxNmidx")).split(",");
		int h_idx = Integer.parseInt(idxArr[0]);
		int m_idx = Integer.parseInt(idxArr[1]);

		String visitcontent = request.getParameter("visitcontent");
//		int stat = Integer.parseInt(String.valueOf(request.getParameter("stat")));

		int status = request.getParameter("status") == null ? 0
				: Integer.parseInt(String.valueOf(request.getParameter("status")));// status 체크 안되어 있을 경우 대비

//		System.out.println(h_idx+","+m_idx+","+visitcontent+","+status);
		miniHpService.writeVisitlog(m_idx, h_idx, visitcontent, status);
		response.sendRedirect("cy_visitbook?idx=" + h_idx);

	}
}
