<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.kimyeyak.member.*"%>
<%
request.setCharacterEncoding("UTF-8");
%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="generator" content="Hugo 0.84.0">
<title>회원가입</title>

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

</head>
<body class="bg-light">

	<div class="container">
		<main>
			<div class="py-5 text-center">
				<img class="d-block mx-auto mb-4" src="../img/logo.png" alt=""
					width="72" height="57">
				<h2>내 정보</h2>
				<p class="lead">내 정보를 확인하고 수정할 수 있습니다.</p>
			</div>

			<div class="row justify-content-center">
				<div class="col-md-7 col-lg-8">
					<h4 class="mb-3"><%= request.getAttribute("notice") %></h4>
					<form class="needs-validation" novalidate="" action="../member/UpdateProc" method="post">
						<div class="row g-3">

							<!-- 이름 -->
							<div class="col-sm-6">
								<label for="name" class="form-label">이름</label> <input readonly
									type="text" class="form-control" id="name" placeholder="이름"
									value="" required="" name="name" value="${memberDTO.name }">
								<div class="invalid-feedback">이름을 입력해 주세요</div>
							</div>

							<!-- 닉네임 -->
							<div class="col-12">
								<label for="nickname" class="form-label">닉네임</label>
								<div class="input-group has-validation">
									<span class="input-group-text">@</span> <input type="text" value="${ memberDTO.nickname }"
										class="form-control" id="nickname" placeholder="닉네임"
										required="" name="nickname">
									<div class="invalid-feedback">닉네임을 입력해 주세요</div>
								</div>
							</div>

							<!-- 아이디 -->
							<div class="col-12">
								<label for="id" class="form-label">아이디</label> <input value="${memberDTO.id }" readonly
									type="text" class="form-control" id="id" placeholder="ID"
									name="id" required="">
								<div class="invalid-feedback">아이디를 입력해 주세요</div>
							</div>

							<!-- 전화번호 -->
							<div class="col-12">
								<label for="tel" class="form-label">전화번호</label> <input
									type="tel" class="form-control" id="tel" value="${ memberDTO.tel }"
									placeholder="01000000000" name="tel" required="">
								<div class="invalid-feedback">전화번호를 입력해 주세요</div>
							</div>

							<!-- 이메일 -->
							<div class="col-12">
								<label for="email" class="form-label">이메일</label> <input value="${ memberDTO.email }"
									type="email" class="form-control" id="email"
									placeholder="email@kimyeyak.com" name="email" required="">
								<div class="invalid-feedback">이메일을 입력해 주세요</div>
							</div>
							
						</div>

						<hr class="my-4">

						<button class="w-100 btn btn-primary btn-lg" type="submit">내정보 수정</button>
					</form>
				</div>
			</div>
		</main>

		<footer class="my-5 pt-5 text-muted text-center text-small">
			<p class="mb-1">© 17831050 이지훈</p>
			<ul class="list-inline">
				<li class="list-inline-item"><a href="#">연락 안받음</a></li>
				<li class="list-inline-item"><a href="#">내 정보는 비밀임</a></li>
				<li class="list-inline-item"><a href="#">찾아오는길 없음</a></li>
			</ul>
		</footer>
	</div>


	<script src="/docs/5.0/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>

	<script src="join.js"></script>
</body>
</html>