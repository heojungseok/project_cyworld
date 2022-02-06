package project.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project.service.CyMiniHpService;

@WebServlet("/cy_delete")
public class DeleteCtl extends HttpServlet {
	 
	CyMiniHpService miniHpService = new CyMiniHpService();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession cySession = request.getSession();
		
		if(request.getParameter("hidxNmidxNvidx") != null) {//방명록 글 삭제
			
			String hidxNmidxNvidx = request.getParameter("hidxNmidxNvidx");
			String[] idxArr = String.valueOf(request.getParameter("hidxNmidxNvidx")).split(",");
			
			int h_idx = Integer.parseInt(idxArr[0]);
			int m_idx = Integer.parseInt(idxArr[1]);
			int v_idx = Integer.parseInt(idxArr[2]);
			
			miniHpService.deleteVisitLog(v_idx);
			
			response.sendRedirect("cy_visitbook?idx="+h_idx);
			
		}else if(request.getParameter("m_idx") != null) {//일촌 삭제
			
			int m_idx = Integer.parseInt(String.valueOf(request.getParameter("m_idx")));//홈피 주인 m_idx
			int curIdx = Integer.parseInt(String.valueOf(cySession.getAttribute("idx")));//현재 접속
			
			int f_idx = Integer.parseInt(String.valueOf(miniHpService.getFollowIdx(curIdx, m_idx)));//언팔하기 위한 idx
			
//			System.out.println("f_idx :"+f_idx);
			
			miniHpService.unfollowUser(f_idx);//언팔
			
			response.sendRedirect("cy_mini?m_idx="+m_idx);//언팔하고 본래 페이지 이동
			
		}
	}
}
