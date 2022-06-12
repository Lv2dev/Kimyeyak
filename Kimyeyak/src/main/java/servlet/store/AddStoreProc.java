package servlet.store;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kimyeyak.member.MemberDTO;
import com.kimyeyak.store.StoreDAO;
import com.kimyeyak.store.StoreDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * 가게 추가하는 서블릿
 */
@WebServlet("/store/AddStoreProc")
public class AddStoreProc extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		response.setContentType("text/html;charset=UTF-8");

		request.setAttribute("login", 0); // 0 : 로그인되지 않은 상태

		// 이미 로그인 된 상태면
		// 일반회원: <member/main.jsp> 으로 보냄
		// 사업자회원: <store/main.jsp> 으로 보냄
		MemberDTO memberDTO = null;
		if (session.getAttribute("memberDTO") != null) {
			memberDTO = (MemberDTO) session.getAttribute("memberDTO");
			if (memberDTO.getType() == 2) {// 일반 회원인 경우
				response.sendRedirect("../member/Main");
				return;
			} else if (memberDTO.getType() == 1) { // 사업자 회원인 경우
				request.setAttribute("login", 1); // 1 : 로그인 된 상태
			}
		} else {
			response.sendRedirect("../member/Main");
			return;
		}

		StoreDTO storeDTO = new StoreDTO();

		// 폼 데이터를 가져오기
		String realFolder = ""; // 실제 경로
		String saveFolder = "store/storePic"; // menuPic 폴더 안에 저장
		String encoding = "utf-8";
		int maxFileSize = 10 * 1024 * 1024; // 최대 업로드 10mb

		String uploadFileName = null; // 업로드된 경로+파일명

		ServletContext context = request.getServletContext();
		realFolder = context.getRealPath(saveFolder);
		MultipartRequest mr = new MultipartRequest(request, realFolder, maxFileSize, encoding,
				new DefaultFileRenamePolicy());

		try {

			// 가게명
			if (mr.getParameter("storeName") == null || mr.getParameter("storeName") == "") {
				response.sendRedirect("../store/Main");
				return;
			}
			storeDTO.setStoreName(mr.getParameter("storeName"));
			
			// 공지사항
			if (mr.getParameter("notice") == null || mr.getParameter("notice") == "") {
				response.sendRedirect("../store/Main");
				return;
			}
			storeDTO.setNotice(mr.getParameter("notice"));
			
			//전화번호
			if (mr.getParameter("tel") == null || mr.getParameter("tel") == "") {
				response.sendRedirect("../store/Main");
				return;
			}
			storeDTO.setTel(mr.getParameter("tel"));
			

			// 카테고리
			if (mr.getParameter("category") == null || mr.getParameter("category") == "") {
				response.sendRedirect("../store/Main");
				return;
			}
			storeDTO.setCategory(Integer.parseInt(mr.getParameter("category")));

			// 가게여는시간
			if (mr.getParameter("openTime") == null || mr.getParameter("openTime") == "") {
				response.sendRedirect("../store/Main");
				return;
			}
			String openTimeTemp[] = mr.getParameter("openTime").split(":");
			storeDTO.setOpenTime(Integer.parseInt(openTimeTemp[0]) * 60
					+ Integer.parseInt(openTimeTemp[1]));

			// 가게닫는시간
			if (mr.getParameter("closeTime") == null || mr.getParameter("closeTime") == "") {
				response.sendRedirect("../store/Main");
				return;
			}
			String closeTimeTemp[] = mr.getParameter("closeTime").split(":");
			storeDTO.setOpenTime(Integer.parseInt(closeTimeTemp[0]) * 60
					+ Integer.parseInt(closeTimeTemp[1]));

			// 쉬는시간 시작
			if (mr.getParameter("brakeTimeStart") == null || mr.getParameter("brakeTimeStart") == "") {
				response.sendRedirect("../store/Main");
				return;
			}
			String brakeTimeStartTemp[] = mr.getParameter("brakeTimeStart").split(":");
			storeDTO.setOpenTime(Integer.parseInt(brakeTimeStartTemp[0]) * 60
					+ Integer.parseInt(brakeTimeStartTemp[1]));

			// 쉬는시간 끝
			if (mr.getParameter("brakeTimeEnd") == null || mr.getParameter("brakeTimeEnd") == "") {
				response.sendRedirect("../store/Main");
				return;
			}
			String brakeTimeEndTemp[] = mr.getParameter("brakeTimeEnd").split(":");
			storeDTO.setOpenTime(Integer.parseInt(brakeTimeEndTemp[0]) * 60
					+ Integer.parseInt(brakeTimeEndTemp[1]));

			// 가게 쉬는 날
			int restDay = 0;
			String[] temp = mr.getParameterValues("restDay");
			for (int i = 0; i < temp.length; i++) {
				switch (temp[i]) {
				case "월": {
					restDay += 1;
				}
				case "화": {
					restDay += 2;
				}
				case "수": {
					restDay += 4;
				}
				case "목": {
					restDay += 8;
				}
				case "금": {
					restDay += 16;
				}
				case "토": {
					restDay += 32;
				}
				case "일": {
					restDay += 64;
				}
				default:
					break;
				}
			}
			storeDTO.setRestDay(restDay);

			// 파일 업로드
			Enumeration e = mr.getFileNames(); // 폼의 이름 반환
			while (e.hasMoreElements()) {
				String eleName = (String) e.nextElement();
				String fileName = mr.getFilesystemName(eleName);
				String fileRealName = mr.getOriginalFileName(eleName);
				if (fileName != null) {
					// 이름 바꿔 업로드
					File originFile = new File(realFolder + "/" + fileName);
					String originFileName = originFile.getName();
					String ext = originFileName.substring(originFileName.lastIndexOf("."));
					String fileTempName = String.valueOf(storeDTO.getStoreId()) + System.currentTimeMillis() + ext;
					long fileSize = originFile.length();
					File tempFile = new File(realFolder + "/" + fileTempName);

					if (!originFile.renameTo(tempFile)) {
						System.out.println("파일명변경 실패");
					} else {
						uploadFileName = "../store/storePic/" + fileTempName; // 저장된 경로(프로젝트 상대경로) + 파일명
					}

				}
			}
			// 새로운 파일이 들어왔을 때
			if (uploadFileName != null) {
				storeDTO.setThumb(uploadFileName); // 파일경로 + 파일명 저장
			}
			
			//가게 추가
			StoreDAO.getInstance().addStore(storeDTO);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//이동
		response.sendRedirect("../store/Main");
		return;
	}
}
