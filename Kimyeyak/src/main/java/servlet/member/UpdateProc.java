package servlet.member;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kimyeyak.member.MemberDAO;
import com.kimyeyak.member.MemberDTO;

/**
 * 회원정보를 업데이트하는 서블릿
 */
@WebServlet("/member/UpdateProc")
public class UpdateProc extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
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
		}else { //로그인된 상태가 아니면
			response.sendRedirect("../member/Main");
			return;
		}

		// 회원가입 처리

		// memberDAO 객체 가져오기

		MemberDAO memberDAO = MemberDAO.getInstance();
		try {
			memberDTO = memberDAO.getMemberInfo(memberDTO.getId());//멤버정보 갱신
			// request 값 가져오기

			// memberDTO에 값 저장
			memberDTO.setNickname(request.getParameter("nickname"));
			memberDTO.setEmail(request.getParameter("email"));
			memberDTO.setTel(request.getParameter("tel"));
			
			memberDAO.editMemberInfo(memberDTO); //회원정보 수정 수행
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		//이동
		response.sendRedirect("../member/Main");
		return;

	}

}
