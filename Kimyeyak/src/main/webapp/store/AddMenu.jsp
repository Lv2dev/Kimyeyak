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
<title>메뉴추가</title>

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
				<h2>${ storeDTO.storeName }</h2>
				<p class="lead">에 메뉴를 추가합니다.</p>
			</div>

			<div class="row justify-content-center">
				<div class="col-md-7 col-lg-8">
					<h4 class="mb-3">메뉴추가</h4>
					<form class="needs-validation" novalidate=""
						action="../store/AddMenuProc?storeId=${ storeDTO.storeId }"
						method="post" enctype="multipart/form-data">
						<div class="row g-3">
							<!-- 메뉴명 -->
							<div class="col-12">
								<label for="pw" class="form-label">메뉴명</label> <input
									type="text" class="form-control" id="store_name" placeholder=""
									name="menuName" required="">
								<div class="invalid-feedback">가게이름을 입력해 주세요</div>
							</div>

							<!-- 가격 -->
							<div class="col-12">
								<label for="pw" class="form-label">가격</label> <input
									type="number" class="form-control" id="store_name"
									placeholder="" name="price" required="">
								<div class="invalid-feedback">가게이름을 입력해 주세요</div>
							</div>


							<!-- 메뉴설명 -->
							<div class="col-12">
								<label for="nickname" class="form-label">메뉴설명</label> <input
									type="text" class="form-control" id="notice" placeholder=""
									name="notice" required="">
								<div class="invalid-feedback">메뉴설명을 입력해 주세요</div>
							</div>

							<!-- 이미지 업로드 -->
							<script type="text/javascript">
								function fileCheck(obj) {
									pathpoint = obj.value.lastIntextOf('.');
									filepoint = obj.value.substring(
											pathpoint + 1, obj.length);
									filetype = filepoint.toLowerCase();
									if (filetype == 'jpg' || filetype == 'png'
											|| filetype == "jpeg") {
										//정상적인 이미지 확장자 파일인 경우
									} else {
										alert('이미지 파일만 선택할 수 있습니다.');
										parentObj = obj.parentNode;
										node = parentObj.replaceChild(obj
												.cloneNode(true), obj);
										return false;
									}
								}
							</script>
							<div class="col-12">
								<div class="button">
									<label for="chooseFile"> 이미지 업로드 </label>
								</div>
								<input type="file" id="chooseFile"
									onchange="fileCheck(this)" name="uploadFile"
									accept="image/jpeg, image/png, image/jpg">
							</div>


						</div>

						<hr class="my-4">

						<button class="w-100 btn btn-primary btn-lg" type="submit">메뉴
							추가하기</button>
					</form>
				</div>
			</div>
		</main>

		<footer class="my-5 pt-5 text-muted text-center text-small">
			<p class="mb-1">© 17831050 이지훈</p>
			<ul class="list-inline">
				<li class="list-inline-item"><a href="#">연락 안받음</a></li>
				<li class="list-inline-item"><a href="#">내 정보는 비밀</a></li>
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