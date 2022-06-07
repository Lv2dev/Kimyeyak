<!-- 회원가입 페이지 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.kimyeyak.member.*"%>
<%
request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="generator" content="Hugo 0.84.0">
<title>새 가게 추가</title>

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
				<h2>회원가입</h2>
				<p class="lead">가게 추가하고 광명 찾자</p>
			</div>

			<div class="row justify-content-center">
				<div class="col-md-7 col-lg-8">
					<h4 class="mb-3"><%= request.getAttribute("notice") %></h4>
					<form class="needs-validation" novalidate="" action="../store/newStoreProc" method="post">
						<div class="row g-3">

							<!-- 가게이름 -->
							<div class="col-sm-6">
								<label for="name" class="form-label">가게이름</label> <input
									type="text" class="form-control" id="store" placeholder="가게이름"
									value="" required="" name="storeName">
								<div class="invalid-feedback">가게이름을 입력해 주세요</div>
							</div>

							<!-- 카테고리 설정 -->
							<div class="col-12">
								<label for="type" class="form-label">카테고리</label>
								<div class="my-3">
									<div class="form-check">
										<input id="credit" name="category" type="radio"
											class="form-check-input" checked="" required="" value="0"> <label
											class="form-check-label" for="type0">한식</label>
									</div>
									<div class="form-check">
										<input id="debit" name="category" type="radio"
											class="form-check-input" required="" value="1"> <label
											class="form-check-label" for="type1">중식</label>
									</div>
									<div class="form-check">
										<input id="debit" name="category" type="radio"
											class="form-check-input" required="" value="2"> <label
											class="form-check-label" for="type1">일식</label>
									</div>
									<div class="form-check">
										<input id="debit" name="category" type="radio"
											class="form-check-input" required="" value="3"> <label
											class="form-check-label" for="type1">양식</label>
									</div>
									<div class="form-check">
										<input id="debit" name="category" type="radio"
											class="form-check-input" required="" value="4"> <label
											class="form-check-label" for="type1">아시안</label>
									</div>
									<div class="form-check">
										<input id="debit" name="category" type="radio"
											class="form-check-input" required="" value="5"> <label
											class="form-check-label" for="type1">패밀리레스토랑</label>
									</div>
									<div class="form-check">
										<input id="debit" name="category" type="radio"
											class="form-check-input" required="" value="6"> <label
											class="form-check-label" for="type1">뷔페</label>
									</div>
									<div class="form-check">
										<input id="debit" name="category" type="radio"
											class="form-check-input" required="" value="7"> <label
											class="form-check-label" for="type1">파인다이닝</label>
									</div>
									<div class="form-check">
										<input id="debit" name="category" type="radio"
											class="form-check-input" required="" value="8"> <label
											class="form-check-label" for="type1">오마카세</label>
									</div>
									<div class="form-check">
										<input id="debit" name="category" type="radio"
											class="form-check-input" required="" value="9"> <label
											class="form-check-label" for="type1">바베큐</label>
									</div>
								</div>
							</div>

							<!-- 가게 설명 -->
							<div class="col-12">
								<label for="id" class="form-label">가게 설명</label> <input
									type="text" class="form-control" id="id" placeholder="ID"
									name="notice" required="">
								<div class="invalid-feedback">가게 설명을 입력해 주세요</div>
							</div>
							
							<!-- 전화번호 -->
							<div class="col-12">
								<label for="tel" class="form-label">전화번호</label> <input
									type="tel" class="form-control" id="tel"
									placeholder="0200000000" name="tel" required="">
								<div class="invalid-feedback">전화번호를 입력해 주세요</div>
							</div>
							
							<!-- 여는시간 -->
							<div class="col-12">
								<label for="tel" class="form-label">여는시간</label> <input
									type="time" class="form-control" id="tel"
									placeholder="0200000000" name="openTime" required="">
								<div class="invalid-feedback">오픈 시간을 입력해 주세요</div>
							</div>
							
							<!-- 닫는시간 -->
							<div class="col-12">
								<label for="tel" class="form-label">닫는시간</label> <input
									type="time" class="form-control" id="tel"
									placeholder="0200000000" name="closeTime" required="">
								<div class="invalid-feedback">닫는 시간을 입력해 주세요</div>
							</div>
							
							<!-- 쉬는 날 -->
							<div class="col-12">
								<label for="type" class="form-label">카테고리</label>
								<div class="my-3">
									<div class="form-check">
										<input id="credit" name="restDay" type="checkbox"
											class="form-check-input" checked="" required="" value="1"> <label
											class="form-check-label" for="type0">월</label>
									</div>
									<div class="form-check">
										<input id="debit" name="restDay" type="checkbox"
											class="form-check-input" required="" value="2"> <label
											class="form-check-label" for="type1">화</label>
									</div>
									<div class="form-check">
										<input id="debit" name="restDay" type="checkbox"
											class="form-check-input" required="" value="4"> <label
											class="form-check-label" for="type1">수</label>
									</div>
									<div class="form-check">
										<input id="debit" name="restDay" type="checkbox"
											class="form-check-input" required="" value="8"> <label
											class="form-check-label" for="type1">목</label>
									</div>
									<div class="form-check">
										<input id="debit" name="restDay" type="checkbox"
											class="form-check-input" required="" value="16"> <label
											class="form-check-label" for="type1">금</label>
									</div>
									<div class="form-check">
										<input id="debit" name="restDay" type="checkbox"
											class="form-check-input" required="" value="32"> <label
											class="form-check-label" for="type1">토</label>
									</div>
									<div class="form-check">
										<input id="debit" name="restDay" type="checkbox"
											class="form-check-input" required="" value="64"> <label
											class="form-check-label" for="type1">일</label>
									</div>
								</div>
							</div>
							
							<!-- 쉬는시간 시작 -->
							<div class="col-12">
								<label for="tel" class="form-label">쉬는시간 시작</label> <input
									type="time" class="form-control" id="tel"
									placeholder="0200000000" name="braketimeStart" required="">
								<div class="invalid-feedback">쉬는시간을 입력해 주세요</div>
							</div>
							
							<!-- 쉬는시간 끝 -->
							<div class="col-12">
								<label for="tel" class="form-label">쉬는시간 시작</label> <input
									type="time" class="form-control" id="tel"
									placeholder="0200000000" name="braketimeEnd" required="">
								<div class="invalid-feedback">쉬는시간을 입력해 주세요</div>
							</div>
						</div>

						<hr class="my-4">

						<input class="w-100 btn btn-primary btn-lg" type="submit" value="가게추가"/>
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