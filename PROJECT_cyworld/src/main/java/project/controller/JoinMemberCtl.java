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

@WebServlet("/join_member")
public class JoinMemberCtl extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");

		String userID = request.getParameter("userID");
		String userPW = request.getParameter("userPW");
		String email = request.getParameter("email");
		String userName = request.getParameter("userName");
		String PhoneNo = request.getParameter("PhoneNo");
		String address = request.getParameter("address");
		String gender = request.getParameter("gender");

		CyMemberService memberService = new CyMemberService();
		// 멤버 정보 입력
		memberService.insertMember(userID, userPW, email, userName, PhoneNo, address, gender);
		// idx , username 가져오기
		// memberChk가 먼저 실행되는 것을 방지하기 위한 로직 세우기
		String[] userInfo = memberService.memberChk(userID, userPW).split(",");
		int idx = Integer.parseInt(userInfo[0]);
		String username = userInfo[1];
		// 세션에 담기
		HttpSession cySession = request.getSession();
		request.setAttribute("username", username);
		request.setAttribute("idx", idx);
//		System.out.println("join_member :" + request.getAttribute("username") + "," + request.getAttribute("idx"));

		RequestDispatcher dispatcher = request.getRequestDispatcher("cy_create_hp.jsp");
		dispatcher.forward(request, response);

	}
}
