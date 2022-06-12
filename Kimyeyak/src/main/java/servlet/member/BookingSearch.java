package servlet.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kimyeyak.member.MemberDTO;
import com.kimyeyak.store.MenuDAO;
import com.kimyeyak.store.StoreDTO;

/**
 * 예약 조건을 설정하는 페이지
 */
@WebServlet("/member/BookingSearch")
public class BookingSearch extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		MenuDAO menuDAO = MenuDAO.getInstance();

		// 이미 로그인 된 상태면
		// 일반회원: 탐색할 페이지로 보냄
		// 사업자회원: <store/main.jsp> 으로 보냄
		request.setAttribute("login", 0); // 0 : 로그인되지 않은 상태
		MemberDTO memberDTO;
		if (session.getAttribute("memberDTO") != null) { // 로그인 된 상태인 경우
			memberDTO = (MemberDTO) session.getAttribute("memberDTO"); // 세션에서 memberDTO 가져옴
			if (memberDTO.getType() == 1) {// 사업자회원인 경우
				response.sendRedirect("../store/Main");
				return;
			} else if (memberDTO.getType() == 2) { // 일반 회원인 경우
				request.setAttribute("login", 1); // 1 : 로그인 된 상태
			}
		}

		// 1. storeDTO가 세션에 저장되어 있지 않으면 Main페이지로 이동
		if (session.getAttribute("storeDTO") == null) {
			response.sendRedirect("../member/Main");
			return;
		}

		// 2. 세션에 저장된 storeDTO를 가져옴
		StoreDTO storeDTO = (StoreDTO) session.getAttribute("storeDTO");

		// 3. 넘어온 세션 체크
		if (request.getParameter("storeId") == null || request.getParameter("storeId") == "") {
			response.sendRedirect("../member/Main");
			return;
		}
		if (storeDTO.getStoreId() != Integer.parseInt(request.getParameter("storeId"))) {
			response.sendRedirect("../member/Main");
			return;
		}
		
		request.getRequestDispatcher("../member/BookingSearch.jsp").forward(request, response);
		return;
	}
}
