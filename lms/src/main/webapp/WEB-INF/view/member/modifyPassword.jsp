<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta property="og:title" content="한빛컴퓨터아카데미LMS">
<meta property="og:url" content="lms/login">
<meta property="og:image"content="${pageContext.request.contextPath}/img/previewer.png">
<title>findPw</title>
<link rel="stylesheet"href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<link href="${pageContext.request.contextPath}/css/styles.css"rel="stylesheet" />
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<style>
#sp {
	color: white;
	text-align: center;
}
</style>
</head>
<body class="bg-dark">
	<div class="layoutAuthentication">
		<div class="layoutAuthentication_content">
			<div class="container">
				<c:if test="${error != null}">
               ${error}
           		 </c:if>
				<br>
				<br>
				<br>
				<h1 class="text-center text-white">Hanbit Computer Academy LMS</h1>
				<br>
				<div id="sp">
					<p>90일동안 비밀번호를 바꾸지 않았습니다.
					<p>보안을 위해 바꿔주세요.
				</div>
				<div class="row justify-content-center">
					<div class="col-lg-4">
						<br>

						<form id="changPw" method="post" action="${pageContext.request.contextPath}/member/updatePw">
							<div class="form-group">
								<input type="password" id="currentPw" class="form-control" placeholder="현재 비밀번호 입력" name="memberPw">
								<span id="helpCurrentPw"></span>
							</div>
							<div class="form-group">
								<input type="password" id="pw" class="form-control" placeholder="새로운 비밀번호 입력" name="updatePw">
								<span id="helpPw"></span>
							</div>
							<div class="form-group">
								<input type="password" id="pwCk" class="form-control" placeholder="비밀번호 확인" name="checkPw">
								<span id="helpPwCk"></span>
							</div>
							<button id="btn" class="btn btn-primary btn-block" type="button">비밀번호	바꾸기</button>
						</form>
						<br> 
						<div>
							<a class="btn btn-light btn-block" role="button" href="${pageContext.request.contextPath}/member/prolongPw">90일 연장하기</a>
						</div>
						<br>
						<div>
							<a class="btn btn-danger btn-block" role="button" href="${pageContext.request.contextPath}/logout">로그아웃</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="waveSvg">
		<svg xmlns="http://www.w3.org/2000/svg"
			xmlns:xlink="http://www.w3.org/1999/xlink"
			style="z-index: 1; position: relative" width="3000" height="100"
			viewBox="300 0 500 50">
                <g
				transform="translate(100, 50) scale(1, 1) translate(-100, -50)">
                                <defs>
               <linearGradient id="linear" x1="200%" y1="0%" x2="100%"
				y2="0%">
                  <stop offset="0%" stop-color="#a1d0ff" />
			<stop offset="100%" stop-color="#398afa" />
               </linearGradient>
            </defs>
                                <path d="" fill="url(#linear)"
				opacity="0.4">
                <animate attributeName="d" dur="20s"
				repeatCount="indefinite" keyTimes="0;0.333;0.667;1" calcmod="spline"
				keySplines="0.2 0 0.2 1;0.2 0 0.2 1;0.2 0 0.2 1" begin="0s"
				values="M0 0L 0 20Q 213.5 60 427 30T 1700 55L 1700 0 Z;M0 0L 0 45Q 213.5 60 427 40T 1700 30L 1700 0 Z;M0 0L 0 65Q 213.5 35 427 65T 1700 30L 1700 0 Z;M0 0L 0 20Q 213.5 60 427 30T 1700 55L 1700 0 Z"></animate>
            </path>
            <path d="" fill="url(#linear)" opacity="0.4">
                <animate attributeName="d" dur="20s"
				repeatCount="indefinite" keyTimes="0;0.333;0.667;1" calcmod="spline"
				keySplines="0.2 0 0.2 1;0.2 0 0.2 1;0.2 0 0.2 1" begin="-10s"
				values="M0 0L 0 35Q 213.5 80 427 50T 1700 60L 1700 0 Z;M0 0L 0 50Q 213.5 20 427 20T 1700 40L 1700 0 Z;M0 0L 0 45Q 213.5 25 427 50T 1700 65L 1700 0 Z;M0 0L 0 35Q 213.5 80 427 50T 1700 60L 1700 0 Z"></animate>
            </path>
                </g>
            </svg>
	</div>
	<script>
	
	$('#btn').click(function() {
		// 현재 비밀번호 유효성 검사
		if($('#currentPw').val() == '') {
			$('#helpCurrentPw').text('현재 비밀번호를 입력해주세요');
			console.log('1111111');
		} else {
			$('#helpCurrentPw').text('');
		}
		
		// 새로운 비밀번호 유효성 검사
		if($('#pw').val() == '') {
			$('#helpPw').text('새로운 비밀번호를 입력해주세요');
		} else if(!/^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d@$!%*#?&]{8,}$/.test($('#pw').val())){
			$('#helpPw').text('영문, 숫자 혼용하여 8자리 이상 입력하세요.');
		} else {
			$('#helpPw').text('');
		}
		
		// 비밀번호 체크 유효성 검사
		if($('#pwCk').val() == '') {
			$('#helpPwCk').text('확인 비밀번호를 입력해주세요');
		} else if($('#pw').val() != $('#pwCk').val()){
			$('#helpPwCk').text('입력하신 비밀번호가 다릅니다.');
		} else {
			$('#helpPwCk').text('');
		}
		
		if($('#currentPw').val() == '' && $('#pw').val() == '' && $('#pwCk').val() == ''){
			console.log('22222');
		} else if(!/^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d@$!%*#?&]{8,}$/.test($('#pw').val())) {
			console.log('3');
		} else if($('#pw').val() != $('#pwCk').val()){
			console.log('44444');
		} else {
			$('#changPw').submit();
		}
	});
	</script>
</body>
</html>