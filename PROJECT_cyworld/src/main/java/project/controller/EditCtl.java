package project.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import project.service.CyMiniHpService;

@WebServlet("/cy_edit_minihp")
//파일 받아오고 , 전송을 위한 어노테이션 (맵핑)
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
		// 한 파일의 최대 용량
		maxFileSize = 1024 * 1024 * 20,
		// 전체 파일의 갯수 용량 제한
		maxRequestSize = 1024 * 1024 * 10 * 9)
public class EditCtl extends HttpServlet {

	CyMiniHpService miniHpService = new CyMiniHpService();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		HttpSession cySession = request.getSession();

		if (cySession.getAttribute("idx") != null) {
			int curIdx = Integer.parseInt(String.valueOf(cySession.getAttribute("idx")));// 현재 접속중 m_idx

			String intro1 = request.getParameter("intro1");
			String intro2 = request.getParameter("intro2");
			String domain = request.getParameter("domain");
			String imgs = "";

//			Collection<Part> parts = request.getParts();
//			for (Part p : parts) {
				Part p = request.getPart("imgs");
				if (p.getName().equals("imgs") && p.getSize() > 0) {

					String path = request.getServletContext().getRealPath("edit_imgs");
					imgs = p.getSubmittedFileName(); // 파일 이름 중복 해결해야 함
//					imgs += imgs  + ",";
//					System.out.println("fileName :" + imgs);
//					System.out.println("path :" + path);
					// 파일을 담는 것
					InputStream inputStream = p.getInputStream();
					File tempFile = new File(path);
					// 폴더의 존재 유무 체크 , 존재 하지 않는다면 디렉토리 까지 만들기
					if (!tempFile.exists()) {
						tempFile.mkdirs();
					}
					// 파일을 가져올 때 운영체제 마다 파일 구분자가 다르기에 File.separator 를 씀
					FileOutputStream fileOutputStream = new FileOutputStream(path + File.separator + imgs);


					int size = 0;
					byte[] buffer = new byte[1024];
					// -1 은 파일을 다 가져왔거나 파일이 없는 경우
					while ((size = inputStream.read(buffer)) != -1) {
						// 파일을 가져올 때 용량 처음부터 끝까지 유실되지 않고 가져오기 위해서
						fileOutputStream.write(buffer, 0, size);

					}
//					System.out.println("size :" + size);

//					int d = inputStream.read(); // while문 앞에 두면 1byte 잡아먹음
//					System.out.println("input d :" + d);
					
					inputStream.close();
					fileOutputStream.close();
				}
//			}
//			System.out.println("intro1 :" + intro1 + ", intro2 :" + intro2 + ", domain :" + domain + ", imgs :" + imgs);
			// 수정 서비스 추가
			int h_idx = miniHpService.getMiniHpIdx(curIdx);
			miniHpService.editMiniHp(intro1, intro2, imgs, domain, h_idx);

			response.sendRedirect("cy_mini?idx=" + h_idx);

		} else {

			response.sendRedirect("cy_main.jsp");
		}

	}
}
