package servlet.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kimyeyak.member.MemberDAO;

/**
 * 로그아웃을 수행하는 servlet
 */
@WebServlet("/member/logoutProc")
public class LogoutProc extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		MemberDAO memberDAO = MemberDAO.getInstance();
		
		if(session.getAttribute("memberDTO") != null) { //memberDTO가 null이 아닌 경우
			session.removeAttribute("memberDTO"); //memberDTO session 삭제
		}
		
		resp.sendRedirect("../member/main"); //메인화면으로 이동
		return;
	}
}
