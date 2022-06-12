package servlet.member;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kimyeyak.member.MemberDAO;
import com.kimyeyak.member.MemberDTO;
import com.kimyeyak.store.StoreDAO;
import com.kimyeyak.store.StoreDTO;

/**
 * 반경 내의 가게들을 찾는 서블릿
 */
@WebServlet("/member/NearStoreProc")
public class NearStoreProc extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		MemberDTO memberDTO = new MemberDTO();
		// 로그인 된 상태가 아닌 경우 로그인 페이지로 이동
		if (null == session.getAttribute("memberDTO")) {
			response.sendRedirect("../member/Login");
			return;
		} else {
			// 로그인 된 상태인 경우 로그인 정보를 전달
			request.setAttribute("login", 0); //0 : 로그인되지 않은 상태
			memberDTO = (MemberDTO) session.getAttribute("memberDTO"); // 세션에서 memberDTO 가져오기
			if (memberDTO.getType() == 1) {// 사업자회원인 경우
				response.sendRedirect("../store/Main");
				return;
			} else if (memberDTO.getType() == 2) { // 일반 회원인 경우
				request.setAttribute("login", 1); // 1 : 로그인 된 상태
			}
		}
		
		MemberDAO memberDAO = MemberDAO.getInstance();
		try {
			if(!memberDAO.isAddressAdded(memberDTO.getId())) { //등록된 주소가 없으면 주소관리 페이지로 이동시킴
				response.sendRedirect("../member/MyAddress");
				return;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//page가 넘어온 경우 page저장
		int page = 1;
		if(request.getParameter("page") != null && request.getParameter("page") != "") {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		//넘어온 distance를 저장
		int distance = 0;
		if(request.getParameter("distance") != null) {
			distance = Integer.parseInt(request.getParameter("distance"));
		}
		
		int pageCount = 10; //한 페이지에 표시되는 가게의 수
		
				
		ArrayList<StoreDTO> list = new ArrayList<StoreDTO>();
		try {
			//회원의 주소정보 가져오기
			MemberDTO tempDTO = MemberDAO.getInstance().getAddressInfo(memberDTO.getId());
			
			//1. 총 검색결과 갯수를 가져옴
			StoreDAO storeDAO = StoreDAO.getInstance();
			int resultCount = storeDAO.searchDistanceCount("", tempDTO.getAddressX(), tempDTO.getAddressY(), distance);
			
			//2. 현재 페이지에 해당하는 글 목록을 가져옴
			list = storeDAO.searchDistanceList("", tempDTO.getAddressX(), tempDTO.getAddressY(), distance, page, pageCount);
			
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
			request.setAttribute("searchList", list);
			request.setAttribute("page", page); //현재 페이지
			request.setAttribute("pages", pages); //전체 페이지 수
			request.setAttribute("end", end); //마지막 페이지
			request.setAttribute("start", start); //시작 페이지
			request.setAttribute("distance", distance); //반경
			request.setAttribute("pageCount", pageCount); //페이지의 글 갯수
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//4. 이동
		request.getRequestDispatcher("../member/NearStoreSearch.jsp").forward(request, response);
		return;
	}
}
