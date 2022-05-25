<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.kimyeyak.member.*" %>
<%request.setCharacterEncoding("UTF-8");%>
<!DOCTYPE html>
<html>
<head>
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

String notice = "로그인";
if(session.getAttribute("loginMessage") != null){ //loginMessage 세션이 null이 아니면
	String message = (String)session.getAttribute("loginMessage");
	if(message.equals("false")){ //로그인 실패 메시지가 넘어온 경우
		notice = "정보가 일치하지 않아요";
		session.removeAttribute("loginMessage"); //해당 세션 삭제
	}else if(message.equals("join")){
		notice = "회원가입 성공! 로그인 해주세요";
		session.removeAttribute("loginMessage"); //해당 세션 삭제
	}
}
%>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="generator" content="Hugo 0.84.0">
<title>로그인</title>

<link rel="canonical"
	href="https://getbootstrap.kr/docs/5.0/examples/sign-in/">



<!-- Bootstrap core CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
	crossorigin="anonymous"></script>

<!-- Favicons -->
<link rel="apple-touch-icon"
	href="/docs/5.0/assets/img/favicons/apple-touch-icon.png"
	sizes="180x180">
<link rel="icon" href="/docs/5.0/assets/img/favicons/favicon-32x32.png"
	sizes="32x32" type="image/png">
<link rel="icon" href="/docs/5.0/assets/img/favicons/favicon-16x16.png"
	sizes="16x16" type="image/png">
<link rel="manifest" href="/docs/5.0/assets/img/favicons/manifest.json">
<link rel="mask-icon"
	href="/docs/5.0/assets/img/favicons/safari-pinned-tab.svg"
	color="#7952b3">
<link rel="icon" href="/docs/5.0/assets/img/favicons/favicon.ico">
<meta name="theme-color" content="#7952b3">
<meta name="viewport" content="width=device-width, initial-scale=1">


<style>
.bd-placeholder-img {
	font-size: 1.125rem;
	text-anchor: middle;
	-webkit-user-select: none;
	-moz-user-select: none;
	user-select: none;
}

@media ( min-width : 768px) {
	.bd-placeholder-img-lg {
		font-size: 3.5rem;
	}
}
</style>


<!-- Custom styles for this template -->
<link href="login.css" rel="stylesheet">
</head>
<body class="text-center">

	<main class="form-signin">
		<form action="loginProc.jsp" method="post">
			<img class="mb-4" src="../img/logo.png" alt="" width="72" height="57">
			<h1 class="h3 mb-3 fw-normal"><%= notice %></h1>

			<div class="form-floating mb-2">
				<input type="text" class="form-control" id="floatingInput"
					placeholder="ID" name="id"> <label for="floatingInput">아이디</label>
			</div>
			<div class="form-floating mb-3">
				<input type="password" class="form-control" id="floatingPassword"
					placeholder="Password" name="password"> <label
					for="floatingPassword">비밀번호</label>
			</div>

			<div class="button mb-3">
				<button class="w-100 btn btn-lg btn-primary" type="submit">로그인</button>
			</div>
			<div class="button mb-3">
				<button class="w-100 btn btn-lg btn-primary" type="button"
					onclick="location.href='join.jsp';">회원가입</button>
			</div>

			<p class="mt-5 mb-3 text-muted">© 17831050 이지훈</p>
		</form>
	</main>

</body>
</html>