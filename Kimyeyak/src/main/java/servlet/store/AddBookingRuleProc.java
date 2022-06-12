package servlet.store;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kimyeyak.booking.BookingDAO;
import com.kimyeyak.booking.BookingRuleDTO;
import com.kimyeyak.member.MemberDTO;
import com.kimyeyak.store.StoreDTO;

/**
 * 예약 규칙을 추가하는 서블릿
 */
@WebServlet("/store/AddBookingRuleProc")
public class AddBookingRuleProc extends HttpServlet {
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
			System.out.println("세션에 storeId");
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
			System.out.println("가게아이디1");
			return;
		}

		if (storeDTO.getStoreId() != Integer.parseInt(request.getParameter("storeId"))) {
			response.sendRedirect("../store/Main");
			System.out.println("가게아이디1");
			return;
		}

		// 매개변수로 사용할 DTO
		BookingRuleDTO dto = new BookingRuleDTO();
		
		//가게아이디 추가
		dto.setStoreId(storeDTO.getStoreId());

		// 넘어온 값들 체크하고 저장
		// 최소인원
		if (request.getParameter("minPeople") == null || request.getParameter("minPeople") == "") {
			response.sendRedirect("../store/Main");
			System.out.println("최소인원");
			return;
		}
		dto.setMinPeople(Integer.parseInt(request.getParameter("minPeople")));

		// 최대인원
		if (request.getParameter("maxPeople") == null || request.getParameter("maxPeople") == "") {
			response.sendRedirect("../store/Main");
			System.out.println("최대인원");
			return;
		}
		dto.setMaxPeople(Integer.parseInt(request.getParameter("maxPeople")));

		// 받을 팀 수
		if (request.getParameter("count") == null || request.getParameter("count") == "") {
			response.sendRedirect("../store/Main");
			System.out.println("받을팀수");
			return;
		}
		dto.setCount(Integer.parseInt(request.getParameter("count")));
		
		// 시간
		if (request.getParameter("time") == null || request.getParameter("time") == "") {
			response.sendRedirect("../store/Main");
			System.out.println("시간");
			return;
		}
		Timestamp time = Timestamp.valueOf("1999-12-31 " + request.getParameter("time") + ":00");
		dto.setTime(time);
		
		// 규칙명
		if (request.getParameter("notice") == null || request.getParameter("notice") == "") {
			response.sendRedirect("../store/Main");
			System.out.println("규칙명");
			return;
		}
		dto.setNotice(request.getParameter("notice"));
		
		try {
			// 규칙 추가 수행
			BookingDAO.getInstance().addStoreBookingRule(dto);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		//이동
		response.sendRedirect("../store/MyStoreBookingRule?storeId=" + storeDTO.getStoreId());
		return;
	}
}
