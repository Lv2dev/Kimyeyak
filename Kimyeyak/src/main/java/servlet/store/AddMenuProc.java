package servlet.store;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.kimyeyak.member.MemberDTO;
import com.kimyeyak.store.MenuDAO;
import com.kimyeyak.store.MenuDTO;
import com.kimyeyak.store.StoreDTO;
import com.kimyeyak.utils.UploadUtil;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * 메뉴를 추가하는 서블릿
 */
@WebServlet("/store/AddMenuProc")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 50, // 50메가
		maxRequestSize = 1024 * 1024 * 50 * 5 // 50메가 5개까지
)
public class AddMenuProc extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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

		// 세션에 storeDTO가 없으면 main으로 리턴
		if (null == session.getAttribute("storeDTO")) {
			response.sendRedirect("../store/Main");
			return;
		}

		StoreDTO storeDTO = (StoreDTO) session.getAttribute("storeDTO");

		// 세션의 가게정보와 유저정보가 일치하지 않으면 리턴
		if (!storeDTO.getMemberId().equals(memberDTO.getId())) {
			response.sendRedirect("../store/Main");
			return;
		}
		

		// 메뉴 추가에 사용할 DTO
		MenuDTO menuDTO = new MenuDTO();

		// 가게 아이디 추가
		menuDTO.setStoreId(storeDTO.getStoreId());
		
		// 폼 데이터를 가져오기
		String realFolder = ""; // 실제 경로
		String saveFolder = "store/menuPic"; // menuPic 폴더 안에 저장
		String encoding = "utf-8";
		int maxFileSize = 10 * 1024 * 1024; // 최대 업로드 10mb

		String uploadFileName = null; // 업로드된 경로+파일명

		ServletContext context = request.getServletContext();
		realFolder = context.getRealPath(saveFolder);
		MultipartRequest mr = new MultipartRequest(request, realFolder, maxFileSize, encoding,
				new DefaultFileRenamePolicy());

		// 메뉴명
		if (mr.getParameter("menuName") == null || mr.getParameter("menuName") == "") {
			response.sendRedirect("../store/Main");
			System.out.println("메뉴명");
			return;
		}
		menuDTO.setMenuName(mr.getParameter("menuName"));

		// 가격
		if (mr.getParameter("price") == null || mr.getParameter("price") == "") {
			response.sendRedirect("../store/Main");
			System.out.println("가격");
			return;
		}
		menuDTO.setPrice(Integer.parseInt(mr.getParameter("price")));

		// 메뉴설명
		if (mr.getParameter("notice") == null || mr.getParameter("notice") == "") {
			response.sendRedirect("../store/Main");
			System.out.println("메뉴설명");
			return;
		}
		menuDTO.setNotice(mr.getParameter("notice"));

		// 메뉴사진

		// 이 시점에서 업로드 된다
		try {
			
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
						uploadFileName = "../store/menuPic/" + fileTempName; //저장된 경로(프로젝트 상대경로) + 파일명
					}

				}
			}
		} catch (Exception e2) {
			// TODO: handle exception
			e2.printStackTrace();
			System.out.println("메뉴사진업로드 오류" + e2.getMessage());
		}

		//새로운 파일이 들어왔을 때
		if(uploadFileName != null) {
			menuDTO.setPic(uploadFileName); // 파일경로 + 파일명 저장
		}
		

		try {
			MenuDAO menuDAO = MenuDAO.getInstance();
			// 1. 메뉴 추가 수행
			menuDAO.addMenu(menuDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 2. 가게 화면으로 이동
		response.sendRedirect("../store/MyStore?storeId=" + storeDTO.getStoreId());
		return;
	}
}
