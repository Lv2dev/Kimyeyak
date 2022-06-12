package servlet.store;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kimyeyak.member.MemberDTO;
import com.kimyeyak.store.StoreDAO;
import com.kimyeyak.store.StoreDTO;

/**
 * 가게 주소를 수정하는 서블릿
 */
@WebServlet("/store/UpdateStoreAddress")
public class UpdateStoreAddress extends HttpServlet {
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

		// 넘어온 가게 아이디 체크
		if (request.getParameter("storeId") == null || request.getParameter("storeId") == "") {
			response.sendRedirect("../store/Main");
			return;
		}

		if (storeDTO.getStoreId() != Integer.parseInt(request.getParameter("storeId"))) {
			response.sendRedirect("../store/Main");
			return;
		}
		
		//넘어온 주소정보 가져오기
		if (request.getParameter("address") == null || request.getParameter("address") == "") {
			response.sendRedirect("../store/Main");
			return;
		}
		String address = request.getParameter("address");
		if (request.getParameter("x") == null || request.getParameter("x") == "") {
			response.sendRedirect("../store/Main");
			return;
		}
		Double x = Double.parseDouble(request.getParameter("x"));
		if (request.getParameter("y") == null || request.getParameter("y") == "") {
			response.sendRedirect("../store/Main");
			return;
		}
		Double y = Double.parseDouble(request.getParameter("y"));
		
		try {
			//주소수정
			StoreDAO.getInstance().updateAddress(storeDTO.getStoreId(), address, x, y);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		//이동
		response.sendRedirect("../store/MyStore?storeId=" + storeDTO.getStoreId());
		return;
	}
}
