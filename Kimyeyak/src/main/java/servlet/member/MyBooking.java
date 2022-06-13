package servlet.member;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kimyeyak.booking.BookingDAO;
import com.kimyeyak.booking.BookingDTO;
import com.kimyeyak.member.MemberDTO;
import com.kimyeyak.store.MenuDAO;

/**
 * 내 예약 목록을 가져오는 서블릿
 */
@WebServlet("/member/MyBooking")
public class MyBooking extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		MenuDAO menuDAO = MenuDAO.getInstance();

		// 이미 로그인 된 상태면
		// 일반회원: 탐색할 페이지로 보냄
		// 사업자회원: <store/main.jsp> 으로 보냄
		request.setAttribute("login", 0); // 0 : 로그인되지 않은 상태
		MemberDTO memberDTO = null;
		if (session.getAttribute("memberDTO") != null) { // 로그인 된 상태인 경우
			memberDTO = (MemberDTO) session.getAttribute("memberDTO"); // 세션에서 memberDTO 가져옴
			if (memberDTO.getType() == 1) {// 사업자회원인 경우
				response.sendRedirect("../store/Main");
				return;
			} else if (memberDTO.getType() == 2) { // 일반 회원인 경우
				request.setAttribute("login", 1); // 1 : 로그인 된 상태
			}
		}else { //로그인 된 상태가 아니면 로그인 화면으로 이동
			response.sendRedirect("../member/Login");
			return;
		}
		
		ArrayList<BookingDTO> list = new ArrayList<BookingDTO>();
		try {
			//page가 넘어온 경우 page저장
			int page = 1;
			if(request.getParameter("page") != null && request.getParameter("page") != "") {
				page = Integer.parseInt(request.getParameter("page"));
			}
			
			int pageCount = 10; //한 페이지에 표시되는 가게의 수
			
			BookingDAO bookingDAO = BookingDAO.getInstance();
			
			//1. 총 검색결과 갯수를 가져옴
			int resultCount = bookingDAO.getMyBookingListCount(page, pageCount, memberDTO.getId());
			
			//2. 현재 페이지에 해당하는 글 목록을 가져옴
			list = bookingDAO.getMyBookingList(page, pageCount, memberDTO.getId());
			
			//3. request에 넣어줌
			int pages = (int) Math.ceil((double) resultCount / 10);
			int end = (int) (Math.ceil((double) page/10) * 10);
			if(end > pages) {
				end = pages;
			}
			int start = 1;
			if(end > 10) {
				start = end - 9;
			}
			System.out.println(list.size());
			request.setAttribute("bookingList", list);
			request.setAttribute("page", page); //현재 페이지
			request.setAttribute("pages", pages); //전체 페이지 수
			request.setAttribute("end", end); //마지막 페이지
			request.setAttribute("start", start); //시작 페이지
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		//이동
		request.getRequestDispatcher("../member/MyBooking.jsp").forward(request, response);
		return;
	}

}
