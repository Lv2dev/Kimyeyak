<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
							aria-current="page" href="../member/MyInfo">내정보</a></li>
						<li class="nav-item"><a class="nav-link active"
							aria-current="page" href="../member/MyBooking">예약관리</a></li>
						<li class="nav-item"><a class="nav-link active"
							aria-current="page" href="../member/NearStore">내주변</a></li>
						<li class="nav-item"><a class="nav-link active"
							aria-current="page" href="../member/MyAddress">주소관리</a></li>
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
					<p class="lead text-muted" style="font-weight: 400;">
						<c:if test="${ storeDTO.status == 0 }">
						지금은 예약을 받지 않습니다.
						</c:if>
						<c:if test="${ storeDTO.status > 0 }">
						예약 버튼을 눌러 일정을 선택 후 예약을 잡을 수 있습니다.
						</c:if>
					</p>
				</div>
			</div>
		</section>
		<div
			class="px-md-0 px-lg-5 p-0 mx-0 mb-3  row justify-content-center container col-11 col-md-7">

			<div
				class="col-12 p-0 px-1 m-0 container row justify-content-center mt-5">
				<!--  -->
				<div
					class="col-12 col-md-12 mb-2 py-1 mx-2 container row justify-content-center shadow-lg rounded-lg bg-body align-items-center"
					style="clear: both;">
					<div class="col-4 col-md-3 m-1 text-center">
						<img alt="가게이미지" src="${ storeDTO.thumb }" class="w-100">
					</div>
					<div
						class="col-6 col-md-7 m-1 text-left container row justify-content-left ">
						<h5 class="m-0 col-12 text-left" style="font-weight: 400;">${ storeDTO.notice }</h5>
						<div class="col-12 container row justify-content-right  pt-4 ">

							<!-- 예약 가능한 상태이면 예약하기 버튼 출력 -->
							<c:if test="${ storeDTO.status == 1 && login == 1 }">
								<div class="col-4">
									<input type="button" class="btn btn-primary "
										style="font-weight: 700;" value="예약하기"
										onclick="location.href='../member/BookingSearch?storeId=${ storeDTO.storeId }'">
								</div>
							</c:if>
							<div class="col-4">
								<input type="button" class="btn btn-primary "
									style="font-weight: 700;" value="정보보기"
									onclick="location.href='../member/StoreInfo'">
							</div>
						</div>

					</div>

				</div>
			</div>
			<div
				class="col-12 p-0 px-1 m-0 container row justify-content-center mt-5">
				<div
					class="col-12 col-md-12 mb-2 container row justify-content-left align-items-center m-0 pl-auto"
					style="font-weight: 400">
					<h4>메뉴 목록</h4>
				</div>
				<!-- 메뉴목록 -->
				<c:forEach items="${ menuList }" var="item">
					<div
						class="col-12 col-md-12 mb-2 mx-2 container row justify-content-center shadow-lg rounded-lg bg-body align-items-center"
						style="clear: both;">
						<div class="col-4 col-md-3 m-1 text-center">
							<img alt="메뉴이미지" src="${ item.pic }" class="w-100">
						</div>
						<div class="col-6 col-md-7 m-1 text-center">
							<h3>${ item.menuName }</h3>
							<br>
							<h5>${ item.price }원</h5>
						</div>
					</div>
				</c:forEach>
			</div>

		</div>
	</main>


</body>
</html>