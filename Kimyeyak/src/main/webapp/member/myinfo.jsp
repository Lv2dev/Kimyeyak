<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.kimyeyak.member.*"%>
<%
request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
	crossorigin="anonymous"></script>
<meta name="theme-color" content="#7952b3">
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
.content {
	position: absolute;
	top: 0;
	right: 0;
	bottom: 0;
	left: 0;
}

.bi {
	vertical-align: -.125em;
	fill: currentColor;
}

.text-small {
	font-size: 85%;
}

.dropdown-toggle {
	outline: 0;
}

@media ( min-width : 768px) {
	.header {
		font-size: 3.5rem;
	}
}
</style>
<%
//로그인 된 상태가 아니라면 로그인 페이지로 보내기
if (session.getAttribute("memberDTO") == null) {
	response.sendRedirect("../member/login.jsp");
}
%>
</head>
<!-- 가입 정보 표시 -->
<body>
	<!-- nav -->
	<header>
		<!-- nav -->
		<nav class="navbar navbar-expand-lg navbar-light"
			style="background-color: #e3f2fd;">
			<div class="container-fluid">
				<a class="navbar-brand" href="#">김예약</a>
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
					aria-controls="navbarSupportedContent" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse " id="navbarSupportedContent">
					<ul class="navbar-nav ms-auto mb-2 mb-lg-0 ">
						<li class="nav-item"><a class="nav-link active"
							aria-current="page" href="#">내주변</a></li>
						<li class="nav-item"><a class="nav-link active"
							aria-current="page" href="#">예약</a></li>
						<!-- 내 정보 -->
						<li class="nav-item"><a class="nav-link active"
							aria-current="page" href="myinfo.jsp">내정보</a></li>
						<li class="nav-item"><a class="nav-link active"
							aria-current="page" href="#">즐겨찾기</a></li>
						<li class="nav-item"><a class="nav-link active"
							aria-current="page" href="#">리뷰</a></li>
						<%
						//로그인 되어 있는 상태에서는 로그아웃 버튼 출력

						if (session.getAttribute("memberDTO") != null) {
						%>
						<li class="nav-item mx-lg-3 mx-0"><button type="button"
								class="btn btn-primary"
								onclick="location.href='logoutProc.jsp';">로그아웃</button></li>

						<%
						//로그인 되어 있지 않은 상태라면 로그인 버튼 출력
						} else {
						%>
						<li class="nav-item mx-lg-3 mx-0 mt-1 mt-lg-0"><button
								type="button" class="btn btn-primary"
								onclick="location.href='login.jsp';">로그인</button></li>
						<%
						}
						%>
						</li>
					</ul>
				</div>
			</div>
		</nav>
	</header>
	<!-- 수정버튼 -->
</body>
</html>