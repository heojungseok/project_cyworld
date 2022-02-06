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
//���� �޾ƿ��� , ������ ���� ������̼� (����)
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
		// �� ������ �ִ� �뷮
		maxFileSize = 1024 * 1024 * 20,
		// ��ü ������ ���� �뷮 ����
		maxRequestSize = 1024 * 1024 * 10 * 9)
public class EditCtl extends HttpServlet {

	CyMiniHpService miniHpService = new CyMiniHpService();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		HttpSession cySession = request.getSession();

		if (cySession.getAttribute("idx") != null) {
			int curIdx = Integer.parseInt(String.valueOf(cySession.getAttribute("idx")));// ���� ������ m_idx

			String intro1 = request.getParameter("intro1");
			String intro2 = request.getParameter("intro2");
			String domain = request.getParameter("domain");
			String imgs = "";

//			Collection<Part> parts = request.getParts();
//			for (Part p : parts) {
				Part p = request.getPart("imgs");
				if (p.getName().equals("imgs") && p.getSize() > 0) {

					String path = request.getServletContext().getRealPath("edit_imgs");
					imgs = p.getSubmittedFileName(); // ���� �̸� �ߺ� �ذ��ؾ� ��
//					imgs += imgs  + ",";
//					System.out.println("fileName :" + imgs);
//					System.out.println("path :" + path);
					// ������ ��� ��
					InputStream inputStream = p.getInputStream();
					File tempFile = new File(path);
					// ������ ���� ���� üũ , ���� ���� �ʴ´ٸ� ���丮 ���� �����
					if (!tempFile.exists()) {
						tempFile.mkdirs();
					}
					// ������ ������ �� �ü�� ���� ���� �����ڰ� �ٸ��⿡ File.separator �� ��
					FileOutputStream fileOutputStream = new FileOutputStream(path + File.separator + imgs);


					int size = 0;
					byte[] buffer = new byte[1024];
					// -1 �� ������ �� �����԰ų� ������ ���� ���
					while ((size = inputStream.read(buffer)) != -1) {
						// ������ ������ �� �뷮 ó������ ������ ���ǵ��� �ʰ� �������� ���ؼ�
						fileOutputStream.write(buffer, 0, size);

					}
//					System.out.println("size :" + size);

//					int d = inputStream.read(); // while�� �տ� �θ� 1byte ��Ƹ���
//					System.out.println("input d :" + d);
					
					inputStream.close();
					fileOutputStream.close();
				}
//			}
//			System.out.println("intro1 :" + intro1 + ", intro2 :" + intro2 + ", domain :" + domain + ", imgs :" + imgs);
			// ���� ���� �߰�
			int h_idx = miniHpService.getMiniHpIdx(curIdx);
			miniHpService.editMiniHp(intro1, intro2, imgs, domain, h_idx);

			response.sendRedirect("cy_mini?idx=" + h_idx);

		} else {

			response.sendRedirect("cy_main.jsp");
		}

	}
}
