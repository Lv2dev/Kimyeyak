package servlet.store;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kimyeyak.member.MemberDTO;
import com.kimyeyak.store.MenuDAO;
import com.kimyeyak.store.MenuDTO;
import com.kimyeyak.store.StoreDTO;

/**
 * 메뉴를 삭제하는 서블릿
 */
@WebServlet("/store/DelMenu")
public class DelMenu extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
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

		// 넘어온 ID 저장
		if (request.getParameter("menuId") == null || request.getParameter("menuId") == null) {
			response.sendRedirect("../store/Main");
			return;
		}
		int menuId = Integer.parseInt(request.getParameter("menuId"));

		try {
			// menuDTO 가져오기
			MenuDTO menuDTO = MenuDAO.getInstance().getMenuDTO(menuId);
			// 세션의 가게정보와 비교
			if (storeDTO.getStoreId() != menuDTO.getStoreId()) {
				response.sendRedirect("../store/Main");
				return;
			}

			// 파일이 있는경우 파일 먼저 삭제
			if (menuDTO.getPic() != null && menuDTO.getPic() != "") {
				// 기존에 있던 파일 제거하기

				// 실제 경로 가져오기
				String tempRealDir = this.getServletContext().getRealPath("../store/menuPic");
				// 파일명만 가져오기
				String[] tempFileNameSlice = menuDTO.getPic().split("/");
				String tempFileName = tempFileNameSlice[tempFileNameSlice.length - 1]; // 파일명
				// File
				File file = new File(tempRealDir + tempFileName);

				// 삭제하고자 하는 파일이 서버에 존재하면 삭제시킨다
				if (file.exists()) {
					file.delete();
				}
			}

			// 메뉴삭제 수행
			MenuDAO.getInstance().delMenu(menuId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 이동
		response.sendRedirect("../store/MyStore?storeId=" + storeDTO.getStoreId());
		return;
	}
}
