package servlet.store;

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
 * 메뉴 수정 페이지로 가는 서블릿
 */
@WebServlet("/store/EditMenu")
public class EditMenu extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		}else {
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
			System.out.println("가게정보 유저정보 일치하지않아요");
			return;
		}
		
		request.setAttribute("storeDTO", storeDTO); //storeDTO를 request에 추가
		
		// menuId 가져오기
		if (null == request.getParameter("menuId") || "" == request.getParameter("menuId")) {
			response.sendRedirect("../store/Main");
			System.out.println("메뉴아이디");
			return;
		}
		int menuId = Integer.parseInt(request.getParameter("menuId"));
		
		
		try {
			//menuDTO를 가져오기
			MenuDTO menuDTO = MenuDAO.getInstance().getMenuDTO(menuId);
			
			//가게정보와 메뉴정보가 다르면 main으로 리턴
			if(storeDTO.getStoreId() != menuDTO.getStoreId()) {
				response.sendRedirect("../store/Main");
				System.out.println("가게정보 메뉴정보");
				return;
			}
			
			//menuDTO 저장
			request.setAttribute("menuDTO", menuDTO);
			session.setAttribute("menuDTO", menuDTO); //세션에도 저장
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//페이지 이동
		request.getRequestDispatcher("../store/EditMenu.jsp").forward(request, response);
		return;
	}
}
