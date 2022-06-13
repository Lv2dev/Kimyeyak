package servlet.store;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kimyeyak.booking.BookingDAO;
import com.kimyeyak.booking.BookingDTO;
import com.kimyeyak.booking.BookingRuleDTO;
import com.kimyeyak.member.MemberDTO;
import com.kimyeyak.store.StoreDTO;

/**
 * 예약닫기 페이지와 연결된 서블릿
 */
@WebServlet("/store/CloseBooking")
public class CloseBooking extends HttpServlet {
	// 폼에서 날짜가 넘어오지 않은 경우(처음 들어갈 때)
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

			Timestamp ts = null;
			// 넘어온 날짜가 없으면 오늘날짜
			if (request.getParameter("date") == null || request.getParameter("date") == "") {
				// 현재시간 timestamp
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
				Calendar cal = Calendar.getInstance();
				String today = null;
				today = formatter.format(cal.getTime());
				ts = Timestamp.valueOf(today);
			}else { //넘어온 날짜가 있으면 가져오기
				
				ts = Timestamp.valueOf(request.getParameter("date") + " 00:00:00");
				System.out.println("들어온 날짜 포맷 : " + ts);
			}
			
			try {
				// 예약 규칙 리스트 가져오기 with 닫았는지 안닫았는지 정보도 같이
				ArrayList<BookingRuleDTO> bookingList = BookingDAO.getInstance().getStoreBookingRuleDTOListWithClose(storeDTO.getStoreId(), ts);
				request.setAttribute("bookingList", bookingList);
				request.setAttribute("date", ts);
				session.setAttribute("date", ts);
				request.setAttribute("storeDTO", storeDTO);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			//이동
			request.getRequestDispatcher("../store/CloseBooking.jsp").forward(request, response);
			return;
		}

		// 폼에서 날짜가 넘어온 경우 doGet 수행
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			doGet(req, resp);
			return;
		}
}
