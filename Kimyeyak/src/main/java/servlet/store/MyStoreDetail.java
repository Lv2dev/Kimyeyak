package servlet.store;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Formatter;

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
 * 가게정보 페이지 서블릿
 */
@WebServlet("/store/MyStoreDetail")
public class MyStoreDetail extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
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

		// 1. storeDTO가 세션에 저장되어 있지 않으면 Main페이지로 이동
		if (session.getAttribute("storeDTO") == null) {
			response.sendRedirect("../member/Main");
			return;
		}

		// 2. 세션에 저장된 storeDTO를 가져옴
		StoreDTO storeDTO = (StoreDTO) session.getAttribute("storeDTO");

		// 3. request에 저장
		request.setAttribute("storeDTO", storeDTO);

		int openTimeHour = Math.floorDiv(storeDTO.getOpenTime(), 60); // 오픈시간 - 시
		int openTimeMin = Math.floorMod(storeDTO.getOpenTime(), 60) / 1; // 오픈시간 - 분

		int closeTimeHour = Math.floorDiv(storeDTO.getCloseTime(), 60); // 폐점시간 - 시
		int closeTimeMin = Math.floorMod(storeDTO.getCloseTime(), 60); // 폐점시간 - 분
		
		if (openTimeHour == closeTimeHour && openTimeMin == closeTimeMin) { // 오픈시간과 폐점시간이 같으면
			request.setAttribute("openTimeChk", 1); // 1이면 24시간 영업 - 오픈시간 폐점시간 대신 24시간 영업으로 표시
		} else { // 같지않으면
			request.setAttribute("openTimeChk", 0);
			request.setAttribute("openTime", String.format("%02d", openTimeHour) + ":" + String.format("%02d", openTimeMin) + ":00"); // 오픈시간
			request.setAttribute("closeTime", String.format("%02d", closeTimeHour) + ":" + String.format("%02d", closeTimeMin) + ":00"); // 폐점시간
		}

		int breakTimeStartHour = Math.floorDiv(storeDTO.getBraketimeStart(), 60); // 쉬는시간 시작 - 시
		int breakTimeStartMin = Math.floorMod(storeDTO.getBraketimeStart(), 60); // 쉬는시간 시작 - 분

		int breakTimeEndHour = Math.floorDiv(storeDTO.getBraketimeEnd(), 60); // 쉬는시간 끝 - 시
		int breakTimeEndMin = Math.floorMod(storeDTO.getBraketimeEnd(), 60); // 쉬는시간 끝 - 분

		if (breakTimeStartHour == breakTimeEndHour && breakTimeStartMin == breakTimeEndMin) { // 휴식시간 시작과 끝이 같으면
			request.setAttribute("brakeTimeChk", 1); // 1이면 휴식시간 없음
		} else { // 같지않으면
			request.setAttribute("brakeTimeChk", 0);
			request.setAttribute("brakeTimeStart", String.format("%02d", breakTimeStartHour) + ":" + String.format("%02d", breakTimeStartMin) + ":00"); // 쉬는시간 시작
			request.setAttribute("brakeTimeEnd", String.format("%02d", breakTimeEndHour) + ":" + String.format("%02d", breakTimeEndMin) + ":00"); // 쉬는시간 끝
		}

		// 비트연산해서 쉬는요일 가져오기
		int restDay[] = new int[7] ;
		int restDayNum = storeDTO.getRestDay();
		if (1 == ((restDayNum) & 1))
			restDay[0] = 1;
		if (1 == ((restDayNum >> 1) & 1))
			restDay[1] = 1;
		if (1 == ((restDayNum >> 2) & 1))
			restDay[2] = 1;
		if (1 == ((restDayNum >> 3) & 1))
			restDay[3] = 1;
		if (1 == ((restDayNum >> 4) & 1))
			restDay[4] = 1;
		if (1 == ((restDayNum >> 5) & 1))
			restDay[5] = 1;
		if (1 == ((restDayNum >> 6) & 1))
			restDay[6] = 1;
		
		for(int i = 0; i < restDay.length; i++) {
			request.setAttribute("restDay" + (i + 1), restDay[i]);
		}

		// 주소정보 가져오기
		StoreDAO storeDAO = StoreDAO.getInstance();
		StoreDTO addressDTO = null;
		try {
			addressDTO = storeDAO.getAddressInfo(storeDTO.getStoreId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (addressDTO == null) { // 주소 정보가 없는 경우
			request.setAttribute("addressChk", 0);
		} else { // 주소정보가 있는 경우
			request.setAttribute("addressChk", 1);
			request.setAttribute("addressDTO", addressDTO);
		}

		// 가게의 세부정보를 request에 저장
		request.setAttribute("storeDTO", storeDTO);

		// 페이지 이동
		request.getRequestDispatcher("../store/MyStoreDetail.jsp").forward(request, response);
		return;
	}
}
