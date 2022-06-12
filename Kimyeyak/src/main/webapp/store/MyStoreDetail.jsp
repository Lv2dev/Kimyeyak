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
						action="../store/UpdateStoreProc" method="post"
						enctype="multipart/form-data">
						<div class="row g-3">
							<!-- 가게 이름 -->
							<div class="col-12">
								<label for="pw" class="form-label">가게이름</label> <input
									type="text" class="form-control" id="store_name" placeholder=""
									value="${ storeDTO.storeName }" name="storeName" required="">
								<div class="invalid-feedback">가게이름을 입력해 주세요</div>
							</div>

							<!-- 카테고리 -->
							<div class="col-12">
							
								<span>카테고리</span>
								<div>
									<input type="radio" name="category" value="1" <c:if test="${ storeDTO.category == 1 }">checked</c:if> />한식 <input
										type="radio" name="category" value="2" <c:if test="${ storeDTO.category == 2 }">checked</c:if>  />중식 <input
										type="radio" name="category" value="3" <c:if test="${ storeDTO.category == 3 }">checked</c:if> />일식 <input
										type="radio" name="category" value="4" <c:if test="${ storeDTO.category == 4 }">checked</c:if> />양식 <input
										type="radio" name="category" value="5" <c:if test="${ storeDTO.category == 5 }">checked</c:if> />아시안 <input
										type="radio" name="category" value="6" <c:if test="${ storeDTO.category == 6 }">checked</c:if> />패밀리레스토랑 <input
										type="radio" name="category" value="7" <c:if test="${ storeDTO.category == 7 }">checked</c:if> />뷔페 <input
										type="radio" name="category" value="8" <c:if test="${ storeDTO.category == 8 }">checked</c:if> />파인다이닝 <input
										type="radio" name="category" value="9" <c:if test="${ storeDTO.category == 9 }">checked</c:if> />오마카세 <input
										type="radio" name="category" value="10" <c:if test="${ storeDTO.category == 10 }">checked</c:if> />바베큐

								</div>
							</div>
							<!-- 공지사항 -->
							<div class="col-12">
								<label for="nickname" class="form-label">공지사항</label>
								<input
									type="text" class="form-control" id="store_name" placeholder=""
									value="${ storeDTO.notice }" name="notice" required=""  >
								<div class="invalid-feedback">가게이름을 입력해 주세요</div>
							</div>
							
							
							<!-- 전화번호 -->
							<div class="col-12">
								<label for="nickname" class="form-label">전화번호 : </label>
								<input
									type="text" class="form-control" id="tel" placeholder=""
									value="${ storeDTO.tel }" name="tel" required="" title="숫자만 입력해 주세요" 
									oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"
									>
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
									name="openTime" required="" value="${ openTime }">
								<div class="invalid-feedback">시간을 정해주세요</div>
							</div>

							<!-- 가게 닫는 시간 -->
							<div class="col-12">
								<label for="pw" class="form-label">가게 닫는 시간</label> <input
									type="time" class="form-control" id="closetime" placeholder=""
									name="closeTime" required="" value="${ closeTime }">
								<div class="invalid-feedback">시간을 정해주세요</div>
							</div>

							<!-- 가게 쉬는 날 -->
							<div class="col-12">
								<span>가게 쉬는 날</span>
								<div>
									<input type="checkbox" name="restDay" value="월"
										<c:if test="${ restDay1 == 1 }">checked</c:if> />월요일 <input
										type="checkbox" name="restDay" value="화"
										<c:if test="${ restDay2 == 1 }">checked</c:if> />화요일 <input
										type="checkbox" name="restDay" value="수"
										<c:if test="${ restDay3 == 1 }">checked</c:if> />수요일 <input
										type="checkbox" name="restDay" value="목"
										<c:if test="${ restDay4 == 1 }">checked</c:if> />목요일 <input
										type="checkbox" name="restDay" value="금"
										<c:if test="${ restDay5 == 1 }">checked</c:if> />금요일 <input
										type="checkbox" name="restDay" value="토"
										<c:if test="${ restDay6 == 1 }">checked</c:if> />토요일 <input
										type="checkbox" name="restDay" value="일"
										<c:if test="${ restDay7 == 1 }">checked</c:if> />일요일

								</div>
							</div>

							<!-- 가게 쉬는 시간 시작-->
							<div class="col-12">
								<label for="pw" class="form-label">가게 쉬는 시간 시작</label> <input
									type="time" class="form-control" id="braketime_start"
									placeholder="" name="brakeTimeStart" required=""
									value="${ brakeTimeStart }">
								<div class="invalid-feedback">시간을 정해주세요</div>
							</div>

							<!-- 가게 쉬는 시간 끝 -->
							<div class="col-12">
								<label for="pw" class="form-label">가게 쉬는 시간 끝</label> <input
									type="time" class="form-control" id="braketime_end"
									placeholder="" name="brakeTimeEnd" required=""
									value="${ brakeTimeEnd }">
								<div class="invalid-feedback">시간을 정해주세요</div>
							</div>

						</div>

						<hr class="my-4">

						<button class="w-100 btn btn-primary btn-lg" type="submit">가게정보
							수정하기</button>
					</form>
				</div>
			</div>
			<section class="py-5 px-0 mx-0 text-center container row justify-content-center">
				<div class="pt-lg-5 pb-lg-3 px-0 mx-0 text-center">
					<div class="col-lg-6 col-md-8 mx-auto my-5">
						<h1 class="m-0 mb-3" style="font-weight: 800;">찾아오시는 길</h1>
						<p class="lead text-muted" style="font-weight: 400;">
							<!-- 담긴 주소가 없을 때 -->
							<c:if test="${ addressChk == 0 }">
						주소가 입력되어 있지 않은 가게입니다.
						</c:if>
							<!-- 담긴 주소가 있을 때 -->
							<c:if test="${ addressChk == 1 }">
						${ addressDTO.address }
						</c:if>
						</p>
					</div>
				</div>
				<div class=" pb-5 mb-5 row justify-content-center col-lg-8 col-md-12">
			<form class="row justify-content-center container col-12"
				action="../member/Search">
				<div class="col-10 mb-5 pb-5">
					<input type="text" class="form-control h-3 " id="sample5_address" style="height: 100%;"
						placeholder="주소 입력" aria-label="Search" name="keyword" disabled>
				</div>
				<div class="col-10 mb-5 pb-5">
					<input type="text" class="form-control h-3 " id="detail" style="height: 100%;"
						placeholder="세부주소 입력" aria-label="Search" name="keyword">
				</div>
				<div class="col-4">
					<button class="w-100 btn btn-lg btn-outline-primary"
						style="height: 100%;" type="button" onclick="sample5_execDaumPostcode()">주소입력</button>
				</div>
				<div class="col-4">
					<button class="w-100 btn btn-lg btn-outline-primary"
						style="height: 100%;" type="button" id="goBtn" onclick="goProc()" disabled="disabled">주소수정</button>
				</div>
			</form>
		</div>
				<!-- 지도가 표시되는 곳 -->
			<c:if test="${ addressChk == 1 }">
				<div
					class="box px-md-0 px-lg-5 p-0 mx-0 mb-3  row justify-content-center container col-10 col-md-8"
					id="map"></div>
			</c:if>
			</section>
			
	</div>
	<c:if test="${ addressChk == 1 }">
		<script
			src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
		<script
			src="//dapi.kakao.com/v2/maps/sdk.js?appkey=60fe788f0ea06f351b62582019d41e56&libraries=services"></script>
		<script>
		var x, y, address;
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div
		    mapOption = {
		        center: new daum.maps.LatLng(${storeDTO.addressY}, ${storeDTO.addressX}), // 지도의 중심좌표
		        level: 5 // 지도의 확대 레벨
		    };

		//지도를 미리 생성
		var map = new daum.maps.Map(mapContainer, mapOption);
		//주소-좌표 변환 객체를 생성
		var geocoder = new daum.maps.services.Geocoder();
		//마커를 미리 생성
		var marker = new daum.maps.Marker({
		    position: new daum.maps.LatLng(${storeDTO.addressY}, ${storeDTO.addressX}),
		    map: map
		});


		function sample5_execDaumPostcode() {
		    new daum.Postcode({
		        oncomplete: function(data) {
		            var addr = data.address; // 최종 주소 변수

		            // 주소 정보를 해당 필드에 넣는다.
		            document.getElementById("sample5_address").value = addr;
		            address=addr;
		            // 주소로 상세 정보를 검색
		            geocoder.addressSearch(data.address, function(results, status) {
		                // 정상적으로 검색이 완료됐으면
		                if (status === daum.maps.services.Status.OK) {

		                    var result = results[0]; //첫번째 결과의 값을 활용

		                    // 해당 주소에 대한 좌표를 받아서
		                    var coords = new daum.maps.LatLng(result.y, result.x);
		                    x = result.x;
		                    y = result.y;
		                    // 지도를 보여준다.
		                    mapContainer.style.display = "block";
		                    map.relayout();
		                    // 지도 중심을 변경한다.
		                    map.setCenter(coords);
		                    // 마커를 결과값으로 받은 위치로 옮긴다.
		                    marker.setPosition(coords);
		                    
		                    var goBtn = document.getElementById("goBtn");
		                    goBtn.disabled = null;
		                }
		            });
		        }
		    }).open();
		}

		function goProc(){
			var detail = document.getElementById('detail').value;
			location.replace("UpdateStoreAddress?storeId=${storeDTO.storeId}&x=" + x + "&y=" + y + "&address=" + address + " " + detail);
		}
</script>
	</c:if>
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