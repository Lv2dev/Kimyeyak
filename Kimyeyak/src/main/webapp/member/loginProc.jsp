<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.kimyeyak.member.*" %>
<%request.setCharacterEncoding("UTF-8");%>
<%
	//이미 로그인 된 상태면
	//일반회원: <member/main.jsp>
	//사업자회원: <store/main.jsp>
	MemberDTO memberDTO = (MemberDTO)session.getAttribute("memberDTO");
	if(memberDTO != null){
		if(memberDTO.getType() == 0){ //일반회원이면
			response.sendRedirect("../member/main.jsp");
		}else if(memberDTO.getType() == 1){//사업자 회원이면
			response.sendRedirect("../store/main.jsp");
		}
	}

	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	MemberDAO memberDAO = MemberDAO.getInstance();

	//아이디와 비밀번호가 맞지 않으면 다시 login.jsp로. 보낼 때 세션으로 로그인 실패 값 전달
	//값이 맞으면 세션에 MemberDTO 저장 후 main.jsp로
	if(!memberDAO.memberLogin(id, pw)){ //값이 맞지 않으면
		session.setAttribute("loginMessage", "false");
		response.sendRedirect("login.jsp");
	}else{ //값이 맞으면 로그인 성공
		memberDTO = memberDAO.getMemberInfo(id);
		session.setAttribute("memberDTO", memberDTO);
	}
%>