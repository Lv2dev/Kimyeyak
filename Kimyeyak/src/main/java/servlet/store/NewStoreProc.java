package servlet.store;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;

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
 * 가게 추가를 처리하는 servlet
 */
@WebServlet("/store/newStoreProc")
public class NewStoreProc extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		StoreDAO storeDAO = StoreDAO.getInstance();
		StoreDTO storeDTO = new StoreDTO();
		
		//로그인이 되어있지 않은 상태인 경우
		if(session.getAttribute("memberDTO") == null) {
			resp.sendRedirect("../member/login"); //로그인 페이지로 이동시키기
			return;
		}
		
		//memberDTO에 값 넣기
		MemberDTO memberDTO = (MemberDTO)session.getAttribute("memberDTO");
		
		//storeDTO에 값 넣기
		storeDTO.setMemberId(memberDTO.getId()); //member_id
		
		String[] temp = req.getParameterValues("category");
		int category = 0;
		for(int i = 0; i < temp.length; i++) {
			category += Integer.parseInt(temp[i]);
		}
		
		storeDTO.setCategory(category); //category
		storeDTO.setNotice(req.getParameter("notice"));
		storeDTO.setTel(req.getParameter("tel"));
		storeDTO.setThumb("");
		
		Timestamp tempDate = Timestamp.valueOf(req.getParameter("openTime"));
		int openTime = (tempDate.getHours() * 60) + tempDate.getMinutes();
		
		storeDTO.setOpenTime(openTime); //open_time
		
		tempDate = Date.valueOf(req.getParameter("closeTime"));
		int closeTime = (tempDate.getHours() * 60) + tempDate.getMinutes();
		
		storeDTO.setCloseTime(closeTime); //close_time
		
		temp = req.getParameterValues("restDay");
		int restDay = 0;
		for(int i = 0; i < temp.length; i++) {
			restDay += Integer.parseInt(temp[i]);
		}
		
		storeDTO.setRestDay(restDay); //rest_day
		
		tempDate = Date.valueOf(req.getParameter("braketimeStart"));
		int braketimeStart = (tempDate.getHours() * 60) + tempDate.getMinutes();
		
		storeDTO.setBraketimeStart(braketimeStart); //braketime_start
		
		tempDate = Date.valueOf(req.getParameter("braketimeEnd"));
		int braketimeEnd = (tempDate.getHours() * 60) + tempDate.getMinutes();
		
		storeDTO.setBraketimeEnd(braketimeEnd); //braketime_end
		
		storeDTO.setStoreName(req.getParameter("storeName"));
		
		//가게 추가 시도
		
		try {
			if(storeDAO.addStore(storeDTO)) { //성공한 경우
				resp.sendRedirect("../store/main");
				return;
			}else { //실패한 경우
				session.setAttribute("newStoreMessage", "false");
				resp.sendRedirect("../store/newStore");
				return;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
