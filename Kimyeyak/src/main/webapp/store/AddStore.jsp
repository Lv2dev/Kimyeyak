<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="generator" content="Hugo 0.84.0">
<title>김예약 - 가게수정</title>

<link rel="canonical"
	href="https://getbootstrap.kr/docs/5.0/examples/sign-in/">

<style>
.box {
	position: relative;
}

.box:before {
	content: "";
	display: block;
	padding-top: 80%; /* 1:1 비율 */
}

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
				<h2>내 가게 정보</h2>
				<p class="lead"></p>
			</div>

			<div class="row justify-content-center">
				<div class="col-md-7 col-lg-8">
					<h4 class="mb-3"></h4>
					<form class="needs-validation" novalidate=""
						action="../store/AddStoreProc" method="post"
						enctype="multipart/form-data">
						<div class="row g-3">
							<!-- 가게 이름 -->
							<div class="col-12">
								<label for="pw" class="form-label">가게이름</label> <input
									type="text" class="form-control" id="store_name" placeholder=""
									name="storeName" required="">
								<div class="invalid-feedback">가게이름을 입력해 주세요</div>
							</div>

							<!-- 카테고리 -->
							<div class="col-12">

								<span>카테고리</span>
								<div>
									<input type="radio" name="category" value="1"
										 checked />한식
									<input type="radio" name="category" value="2"
										 />중식
									<input type="radio" name="category" value="3"
										 />일식
									<input type="radio" name="category" value="4"
										 />양식
									<input type="radio" name="category" value="5"
										 />아시안
									<input type="radio" name="category" value="6"
										 />패밀리레스토랑
									<input type="radio" name="category" value="7"
										 />뷔페
									<input type="radio" name="category" value="8"
										 />파인다이닝
									<input type="radio" name="category" value="9"
										 />오마카세
									<input type="radio" name="category" value="10"
										 />바베큐

								</div>
							</div>
							<!-- 공지사항 -->
							<div class="col-12">
								<label for="nickname" class="form-label">공지사항</label> <input
									type="text" class="form-control" id="store_name" placeholder=""
									 name="notice" required="">
								<div class="invalid-feedback">가게이름을 입력해 주세요</div>
							</div>


							<!-- 전화번호 -->
							<div class="col-12">
								<label for="nickname" class="form-label">전화번호 : </label> <input
									type="text" class="form-control" id="tel" placeholder=""
									 name="tel" required=""
									title="숫자만 입력해 주세요"
									oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');">
								<div class="invalid-feedback">전화번호를 입력해 주세요</div>
							</div>

							<!-- 가게 이미지 업로드 -->
							<div class="col-12">
								<div class="button">
									<label for="chooseFile"> 가게 이미지 업로드 </label>
								</div>
								<input type="file" id="chooseFile" name="fileUpload"
									accept="image/*" onchange="loadFile(this)">
							</div>

							<!-- 가게 여는 시간 -->
							<div class="col-12">
								<label for="pw" class="form-label">가게 여는 시간</label> <input
									type="time" class="form-control" id="opentime" placeholder=""
									name="openTime" required="" >
								<div class="invalid-feedback">시간을 정해주세요</div>
							</div>

							<!-- 가게 닫는 시간 -->
							<div class="col-12">
								<label for="pw" class="form-label">가게 닫는 시간</label> <input
									type="time" class="form-control" id="closetime" placeholder=""
									name="closeTime" required="" >
								<div class="invalid-feedback">시간을 정해주세요</div>
							</div>

							<!-- 가게 쉬는 날 -->
							<div class="col-12">
								<span>가게 쉬는 날</span>
								<div>
									<input type="checkbox" name="restDay" value="월"
										 />월요일 <input
										type="checkbox" name="restDay" value="화"
										 />화요일 <input
										type="checkbox" name="restDay" value="수"
										 />수요일 <input
										type="checkbox" name="restDay" value="목"
										 />목요일 <input
										type="checkbox" name="restDay" value="금"
										 />금요일 <input
										type="checkbox" name="restDay" value="토"
										 />토요일 <input
										type="checkbox" name="restDay" value="일"
										 />일요일

								</div>
							</div>

							<!-- 가게 쉬는 시간 시작-->
							<div class="col-12">
								<label for="pw" class="form-label">가게 쉬는 시간 시작</label> <input
									type="time" class="form-control" id="braketime_start"
									placeholder="" name="brakeTimeStart" required=""
									>
								<div class="invalid-feedback">시간을 정해주세요</div>
							</div>

							<!-- 가게 쉬는 시간 끝 -->
							<div class="col-12">
								<label for="pw" class="form-label">가게 쉬는 시간 끝</label> <input
									type="time" class="form-control" id="braketime_end"
									placeholder="" name="brakeTimeEnd" required=""
									>
								<div class="invalid-feedback">시간을 정해주세요</div>
							</div>

						</div>

						<hr class="my-4">

						<button class="w-100 btn btn-primary btn-lg" type="submit">가게추가하기</button>
					</form>
				</div>
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