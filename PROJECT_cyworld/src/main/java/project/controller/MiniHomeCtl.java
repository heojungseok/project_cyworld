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

	// 메인에서 로그인
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		String userID = request.getParameter("userID");
		String userPW = request.getParameter("userPW");

		// id, pw 통해서 idx, username 가져오기
		String[] userInfo = memberService.memberChk(userID, userPW).split(",");
		System.out.println("처음 로그인: " + memberService.memberChk(userID, userPW));
		if (memberService.memberChk(userID, userPW) != "not user") {
			int idx = Integer.parseInt(userInfo[0]);
			String username = userInfo[1];
			System.out.println("cy :" + idx + username);
			// session 저장
			HttpSession cySession = request.getSession();
			cySession.setAttribute("idx", idx);
			cySession.setAttribute("userID", userID);
			cySession.setAttribute("username", username);

			// 로그인 확인
			if (cySession.getAttribute("idx") != null) {
//				System.out.println("정상작동"+cySession.getAttribute("idx"));
				int curIdx = Integer.parseInt(String.valueOf(cySession.getAttribute("idx")));

				int h_idx = miniHpService.getMiniHpIdx(curIdx);// 로그인한 사람의 홈피로 넘어가기 위해서 설정
				cySession.setAttribute("MyHidx", h_idx);
				response.sendRedirect("cy_mini?idx=" + h_idx);

			} else {
				response.sendRedirect("cy_main.jsp");
			}

		} else {

			response.sendRedirect("cy_main.jsp");

		}

	}

	// idx 값 파라미터
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 세션 종료 됐을 경우
		HttpSession cySession = request.getSession();
		if (cySession.getAttribute("idx") != null) {
			// idx 값을 받아온 경우
			if (request.getParameter("idx") != null) {

				int curIdx = Integer.parseInt(String.valueOf(cySession.getAttribute("idx")));// 현재 접속중 m_idx
				int h_idx = Integer.parseInt(String.valueOf(request.getParameter("idx")));// h_idx = miniHP 의 것 로 넘어오는
																							// 경우
				int hpOwner_idx = miniHpService.getUserIdx(h_idx);// 홈피 주인 m_idx
//				System.out.println(h_idx);
//				System.out.println("curIdx:"+cySession.getAttribute("idx")); 
				String userID = String.valueOf(cySession.getAttribute("userID"));
				String username = String.valueOf(cySession.getAttribute("username"));
				
				// 투데이 처리하는 곳
				int curCountToday = miniHpService.countToday(hpOwner_idx);// 현재 투데이 받아오기
					if (curIdx != hpOwner_idx) {
						
						curCountToday += 1;
//					System.out.println("다른 사람 들어옴 today: " + curCountToday);
						miniHpService.updateToday(curCountToday, hpOwner_idx);// update today
						
					} else {
//					System.out.println("본인이 들어옴 :" + curCountToday);
					
				}

				request.setAttribute("miniData", miniHpService.minihpData_minihp(h_idx));// 홈피 데이터
				request.setAttribute("following", miniHpService.getFollow(hpOwner_idx, userID));// 일촌 데이터(ArrayList)
//				request.setAttribute("f", miniHpService.followData(h_idx, userID));// 일촌 데이터(모델)
				request.setAttribute("isfollow", miniHpService.isFollow(curIdx, hpOwner_idx));// 일촌 유무 파악
				request.setAttribute("MyHidx", cySession.getAttribute("MyHidx"));// 본인 홈피로 돌아가기 위함

				RequestDispatcher dispatcher = request.getRequestDispatcher("cy_mini.jsp");
				dispatcher.forward(request, response);
				// 방명록,m_idx 값 받아온 경우
			}

		} else {

			response.sendRedirect("cy_main.jsp");
		}
	}

}
