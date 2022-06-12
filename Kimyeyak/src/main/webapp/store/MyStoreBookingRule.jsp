<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>예약은 김예약</title>
<!-- bootstrap -->
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
<link rel="stylesheet" type="text/css"
	href="https://cdn.jsdelivr.net/gh/moonspam/NanumSquare@1.0/nanumsquare.css">
<style>
body {
	font-family: 나눔스퀘어, 'NanumSquare', sans-serif;
}
</style>
<style>
.box {
	position: relative;
}

.box:before {
	content: "";
	display: block;
	padding-top: 100%; /* 1:1 비율 */
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
</head>
<body class="row justify-content-center">
	<!--  <div class="container">
		<header
			class="d-flex flex-wrap justify-content-center py-3 mb-4 border-bottom">
			<a href="main.jsp"
				class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-dark text-decoration-none">
				<span class="fs-4">김예약</span>
			</a>
			<ul class="nav nav-pills">
				<li class="nav-item"><a href="#" class="nav-link">내주변</a></li>
				<li class="nav-item"><a href="#" class="nav-link">예약</a></li>
				<li class="nav-item"><a href="#" class="nav-link">내정보</a></li>
				<li class="nav-item"><a href="#" class="nav-link">즐겨찾기</a></li>
				<li class="nav-item"><a href="#" class="nav-link">리뷰</a></li>
				<button type="button" class="btn btn-primary">로그인</button>
		}				%>
			</ul>
		</header>-->
	<header>
		<!-- nav -->
		<nav class="navbar navbar-expand-lg navbar-light"
			style="background-color: #e3f2fd;">
			<div class="container-fluid">
				<a class="navbar-brand" href="../member/Main">김예약</a>
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
					aria-controls="navbarSupportedContent" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse " id="navbarSupportedContent">
					<ul class="navbar-nav ms-auto mb-2 mb-lg-0 ">
						<li class="nav-item"><a class="nav-link active"
							aria-current="page" href="#">내정보</a></li>
						<c:if test="${login == 0 }">
							<li class="nav-item mx-lg-3 mx-0 mt-1 mt-lg-0"><button
									type="button" class="btn btn-primary"
									onclick="location.href='../member/Login';">로그인</button></li>
							</li>
						</c:if>
						<c:if test="${login == 1 }">
							<li class="nav-item mx-lg-3 mx-0 mt-1 mt-lg-0"><button
									type="button" class="btn btn-primary"
									onclick="location.href='../member/Logout';">로그아웃</button></li>
							</li>
						</c:if>
					</ul>
				</div>
			</div>
		</nav>
	</header>
	</div>
	<main class="row justify-content-center container">
		<section class="py-5 px-0 mx-0 text-center container">
			<div class="pt-lg-5 pb-lg-3 px-0 mx-0 text-center">
				<div class="col-lg-6 col-md-8 mx-auto my-5">
					<h1 class="m-0 mb-3" style="font-weight: 800;">${ storeDTO.storeName }</h1>
					<p class="lead text-muted" style="font-weight: 400;">예약 규칙을
						관리합니다</p>
				</div>
			</div>
		</section>
		<div
			class="px-md-0 px-lg-5 p-0 mx-0 mb-3  row justify-content-center container col-11 col-md-7">

			<div
				class="col-12 p-0 px-1 m-0 container row justify-content-center mt-5">
				<c:if test="${ empty bookingRuleList }">
				<div
					class="col-12 col-md-12 mb-4 container row justify-content-left align-items-center m-0 pt-0 pl-auto text-center "
					style="font-weight: 400">
					<h4>규칙이 없어요</h4>
				</div>
				</c:if>
				<!-- 예약목록 -->
				<c:forEach items="${ bookingRuleList }" var="item">
					<div
						class="col-12 col-md-12 mb-3 mx-2 container row justify-content-center shadow-lg rounded-lg bg-body align-items-center"
						style="clear: both;">
						<div class="col-6 col-md-7  text-center">
							<h3>
								규칙명 : ${ item.notice }
							</h3>
							<br>
							<h5>인원 : ${ item.minPeople } ~ ${ item.maxPeople }</h5>
							<h5>예약받을 팀 수 : ${ item.count }</h5>
							<h5>시간 : <fmt:formatDate value="${ item.time }" pattern="hh시 mm분" /></h5>
						</div>
						<div class="col-4 text-center p-2 mt-0">
							<button type="button" class="btn btn-primary"
								onclick="location.href='../store/DelStoreRuleProc?ruleId=${ item.id}&storeId=${ storeDTO.storeId }'">규칙
								삭제</button>
						</div>
					</div>
				</c:forEach>
			</div>
			<div
				class="col-12 col-md-12 mb-2 container row justify-content-left align-items-center m-0 mt-5 pl-auto"
				style="font-weight: 400">
				<h4>규칙 추가하기</h4>
				<h4 class="mb-3"></h4>
				<form class="needs-validation" novalidate=""
					action="../store/AddBookingRuleProc?storeId=${ storeDTO.storeId }"
					method="post">
					<div class="row g-3">
						<!-- 최대인원 -->
						<div class="col-12">
							<label for="nickname" class="form-label">최소인원 </label> <input
								type="number" class="form-control" id="tel" placeholder=""
								name="minPeople" required="" title="숫자만 입력해 주세요"
								oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');">
							<div class="invalid-feedback">최소인원을 입력해 주세요</div>
						</div>

						<!-- 최대인원 -->
						<div class="col-12">
							<label for="nickname" class="form-label">최대인원 </label> <input
								type="number" class="form-control" id="tel" placeholder=""
								name="maxPeople" required="" title="숫자만 입력해 주세요"
								oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');">
							<div class="invalid-feedback">최대인원을 입력해 주세요</div>
						</div>

						<!-- 받을 팀 수 -->
						<div class="col-12">
							<label for="nickname" class="form-label">받을 팀 수 </label> <input
								type="number" class="form-control" id="tel" placeholder=""
								name="count" required="" title="숫자만 입력해 주세요"
								oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');">
							<div class="invalid-feedback">팀 수를 입력해 주세요</div>
						</div>
						
						<!-- 규칙명 -->
						<div class="col-12">
							<label for="nickname" class="form-label">규칙명 </label> <input
								type="text" class="form-control" id="tel" placeholder=""
								name="notice" required="" >
							<div class="invalid-feedback">규칙명을 입력해 주세요</div>
						</div>


						<!-- 예약타임 -->
						<div class="col-12">
							<label for="pw" class="form-label">예약시간</label> <input
								type="time" class="form-control" id="braketime_start"
								placeholder="" name="time" required=""
								value="${ brakeTimeStart }">
							<div class="invalid-feedback">시간을 정해주세요</div>
						</div>

					</div>

					<hr class="my-4">

					<button class="w-100 btn btn-primary btn-lg" type="submit">예약규칙
						추가하기</button>
				</form>
			</div>
		</div>

		</div>
	</main>


</body>
</html>