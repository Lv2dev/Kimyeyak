package servlet.store;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 가게 추가 페이지
 */
@WebServlet("/store/newStore")
public class NewStore extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		
		//로그인이 되어있지 않은 상태인 경우
		if(session.getAttribute("memberDTO") == null) {
			resp.sendRedirect("../member/login"); //로그인 페이지로 이동시키기
			return;
		}
		
		//가게 추가 실패 메시지가 전달된 경우
		String notice = "가게추가하고 광명찾자";
		if(session.getAttribute("newStoreMessage") != null) {
			notice = "가게추가 실패";
			session.removeAttribute("newStoreMessage");
		}
		
		req.setAttribute("notice", notice);
		req.getRequestDispatcher("../store/newStore.jsp").forward(req, resp);
		return;
	}
}
