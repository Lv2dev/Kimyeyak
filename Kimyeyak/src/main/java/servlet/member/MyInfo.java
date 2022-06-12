package servlet.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kimyeyak.member.MemberDTO;

/**
 * 내 정보를 가져오는 서블릿
 */
@WebServlet("/member/MyInfo")
public class MyInfo extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		MemberDTO memberDTO = null;
		if (session.getAttribute("memberDTO") != null) {
			memberDTO = (MemberDTO) session.getAttribute("memberDTO");
			if (memberDTO.getType() == 0) { // 일반회원인 경우

			} else if (memberDTO.getType() == 1) {// 사업자회원인 경우

			}
		} else { // 로그인된 상태가 아니면
			response.sendRedirect("../member/Main");
			return;
		}
		
		request.setAttribute("memberDTO", memberDTO);
		
		request.getRequestDispatcher("../member/MyInfo.jsp").forward(request, response);
		return;
	}
}
