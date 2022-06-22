<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body class="bg-dark">
	<div class="layoutAuthentication">
		<div class="layoutAuthentication_content">
			<div class="container">
				<c:if test="${error eq null}">
					${error}
				</c:if>
			<br><br><br>
			<h1 class="text-center text-white">Hanbit Computer Academy LMS</h1>
			<br>
			<div class="row justify-content-center">
				<div class="col-lg-4">
					<form name="testForm" id="login" method="post" action="${pageContext.request.contextPath}/login">
					<ul class="nav nav-tabs">
						<li class="nav-item">
							<c:choose>
								<c:when test="${role eq 'student'}">
									<a class="nav-link active" data-toggle="tab" href="${pageContext.request.contextPath}/login?role=student">🧑 Student </a>
								</c:when>
								<c:otherwise>
								    <a class="nav-link" data-toggle="tab" href="${pageContext.request.contextPath}/login?role=student">🧑 Student </a>
								</c:otherwise>
							</c:choose>
						</li>
						<li class="nav-item">
							<c:choose>
								<c:when test="${role == 'teacher'}">
								    <a class="nav-link active" data-toggle="tab" href="${pageContext.request.contextPath}/login?role=teacher">🧓 Teacher </a>
								</c:when>
								<c:otherwise>
									<a class="nav-link" data-toggle="tab" href="${pageContext.request.contextPath}/login?role=teacher">🧓 Teacher </a>
								</c:otherwise>
							</c:choose>
						</li>
						<li class="nav-item">
							<c:choose>
								<c:when test="${role eq 'manager'}">
								    <a class="nav-link active" data-toggle="tab" href="${pageContext.request.contextPath}/login?role=manager">👨‍🦱 Manager </a>
								</c:when>
								<c:otherwise>
									<a class="nav-link" data-toggle="tab" href="${pageContext.request.contextPath}/login?role=manager">👨‍🦱 Manager </a>
								</c:otherwise>
							</c:choose>
						</li>
					</ul>
					<br>
					<!--  학생을 선택했을때 테스트 계정입력 -->
					 <c:if test="${role =='student'}">
				    <div class="form-group">
						<input type="text" id="uname" class="form-control"  placeholder="Enter username" name="memberId" value="student1">
				    	<span id="helpId"></span>
				    </div>
				    <div class="form-group">
						<input type="password" id="pw" class="form-control" placeholder="Enter password" name="memberPw" value="a12345678">
						<span  id="helpPw"></span>
				    </div>
				     </c:if>
				     
				     	<!--  학생을 선택했을때 테스트 계정입력 -->
					 <c:if test="${role =='teacher'}">
				    <div class="form-group">
						<input type="text" id="uname" class="form-control"  placeholder="Enter username" name="memberId" value="teacher1">
				    	<span id="helpId"></span>
				    </div>
				    <div class="form-group">
						<input type="password" id="pw" class="form-control" placeholder="Enter password" name="memberPw" value="a12345678">
						<span  id="helpPw"></span>
				    </div>
				     </c:if>
				     
				     	<!--  학생을 선택했을때 테스트 계정입력 -->
					 <c:if test="${role =='manager'}">
				    <div class="form-group">
						<input type="text" id="uname" class="form-control"  placeholder="Enter username" name="memberId" value="admin">
				    	<span id="helpId"></span>
				    </div>
				    <div class="form-group">
						<input type="password" id="pw" class="form-control" placeholder="Enter password" name="memberPw" value="a12345678">
						<span  id="helpPw"></span>
				    </div>
				     </c:if>
				     
					<div>
						<input type="hidden" name="role" value="${role}">
					</div>
					 <button id="btn" class="btn btn-primary btn-block" type="button" >Login</button>
					<hr class="bg-white">
					<div>
						<a class="btn btn-light btn-block" role="button" href="${pageContext.request.contextPath}/addMember">Sign Up</a>
						<div class="text-center text-white">
						<hr class="bg-dark">
						<a class="text-white text-decoration-none" href="${pageContext.request.contextPath}/user/findId">ID 찾기</a> &nbsp;&nbsp;&nbsp;&nbsp; | &nbsp;&nbsp;&nbsp;&nbsp;
						<a class="text-white text-decoration-none"href="#">PW 찾기</a>
						</div>
					<div class="text-danger">
						<c:if test="${error != null}">
							${error}
						</c:if>
					</div>
					</div>
				 	</form>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="waveSvg">
                 <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" style="z-index:1;position:relative" width="3000" height="100" viewBox="300 0 500 50">
                <g transform="translate(100, 50) scale(1, 1) translate(-100, -50)">
                                <defs>
					<linearGradient id="linear" x1="200%" y1="0%" x2="100%" y2="0%">
						<stop offset="0%" stop-color="#a1d0ff"/><stop offset="100%" stop-color="#398afa"/>
					</linearGradient>
				</defs>
                                <path d="" fill="url(#linear)" opacity="0.4" >
                <animate attributeName="d" dur="20s" repeatCount="indefinite" keyTimes="0;0.333;0.667;1" calcmod="spline" keySplines="0.2 0 0.2 1;0.2 0 0.2 1;0.2 0 0.2 1" begin="0s" values="M0 0L 0 20Q 213.5 60 427 30T 1700 55L 1700 0 Z;M0 0L 0 45Q 213.5 60 427 40T 1700 30L 1700 0 Z;M0 0L 0 65Q 213.5 35 427 65T 1700 30L 1700 0 Z;M0 0L 0 20Q 213.5 60 427 30T 1700 55L 1700 0 Z"></animate>
            </path>
            <path d="" fill="url(#linear)" opacity="0.4" >
                <animate attributeName="d" dur="20s" repeatCount="indefinite" keyTimes="0;0.333;0.667;1" calcmod="spline" keySplines="0.2 0 0.2 1;0.2 0 0.2 1;0.2 0 0.2 1" begin="-10s" values="M0 0L 0 35Q 213.5 80 427 50T 1700 60L 1700 0 Z;M0 0L 0 50Q 213.5 20 427 20T 1700 40L 1700 0 Z;M0 0L 0 45Q 213.5 25 427 50T 1700 65L 1700 0 Z;M0 0L 0 35Q 213.5 80 427 50T 1700 60L 1700 0 Z"></animate>
            </path>
                </g>
            </svg>
	</div>
<script>
$('#btn').click(function(){
	if($('#uname').val() == ''){
		$('#helpId').text('아이디를 입력해주세요');
	} else {
		$('#helpId').text('');
	}
	if($('#pw').val() == '') {
		$('#helpPw').text('비밀번호를 입력해주세요');
	} else {
		$('#helpPw').text('');
	}
	if($('#uname').val() != '' && $('#pw').val() != '') {
		$("#login").submit();
	}
});
</script>
</body>
</html>
