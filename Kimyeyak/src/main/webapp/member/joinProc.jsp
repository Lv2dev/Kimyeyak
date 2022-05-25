<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.kimyeyak.member.*"%>
<%@page import="java.sql.Date"%>
<%@page import="java.text.ParseException"%>
<%@page import="java.text.SimpleDateFormat"%>
<%
request.setCharacterEncoding("UTF-8");
%>
<%
	//이미 로그인 된 상태면
	//일반회원: <member/main.jsp>
	//사업자회원: <store/main.jsp>
	MemberDTO memberDTO;
	if (session.getAttribute("memberDTO") != null) {
		memberDTO = (MemberDTO) session.getAttribute("memberDTO");
		if (memberDTO.getType() == 0) { //일반회원이면
			response.sendRedirect("../member/main.jsp");
		} else if (memberDTO.getType() == 1) {//사업자 회원이면
			response.sendRedirect("../store/main.jsp");
		}
	}
	
	
	
	MemberDAO memberDAO = MemberDAO.getInstance();
	memberDTO = new MemberDTO();
	
	String dateStr = request.getParameter("bDay");
	Date date = Date.valueOf(dateStr);

	//memberDTO에 값 저장
	memberDTO.setName(request.getParameter("name"));
	memberDTO.setNickname(nickname);
	memberDTO.setId(id);
	memberDTO.setPw(pw);
	memberDTO.setEmail(email);
	memberDTO.setbDay(date);
	
	
	if(memberDAO.joinMember(memberDTO2)){ //회원가입이 성공한 경우
		session.setAttribute("loginMessage", "join"); //회원가입 되었다는 메시지와 함께 로그인 페이지로 넘기기
		response.sendRedirect("../member/login.jsp"); //로그인 페이지로 리다이렉트
	}else{ //회원가입이 실패한 경우
		session.setAttribute("joinMessage", "false"); 
		response.sendRedirect("../member/join.jsp");
	}
%>