<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<meta property="og:title" content="한빛컴퓨터아카데미LMS">
<meta property="og:url" content="lms/login">
<meta property="og:image" content="${pageContext.request.contextPath}/img/previewer.png">
<title>getLecOne</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
<link href="../css/styles.css" rel="stylesheet" />
<script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
</head>
<body class="sb-nav-fixed">
<div id="nav"></div>
<div id="layoutSidenav">
	<div id="layoutSidenav_nav">
			<div id="navbar"></div>
	</div>
    <div id="layoutSidenav_content">
		<div class="container-fluid px-4">
	        <!-- 컨텐츠 삽입 부분-->
			<br>
        	<div class="card mb-4">
	            <div class="card-header">
	                <i class="fas fa-chart-area me-1"></i>
	               	Lecture Information
	            </div>
	        </div>
				<a href="${pageContext.request.contextPath}/registration/getRegistration" class="btn btn-dark" style="float:right">이전으로</a>
			<br><br>
			<h1>${regimap.lectureName} </h1>
			<br><br>
			<table class="table">
				<tr>
					<th class="text-center">과목</th>
					<td>${regimap.lectureName}</td>
				</tr>
				<tr>
					<th class="text-center">난이도</th>
					<td>${regimap.difficulty}</td>
				</tr>
				<tr>
					<th class="text-center">담당 강사</th>
					<td>${regimap.teacherName}</td>
				</tr>
				<tr>
					<th class="text-center">교육 기간</th>
					<td>${regimap.beginClass} - ${regimap.endClass}</td>
				</tr>
				<tr>
					<th class="text-center">교육 시간</th>
					<td>${regimap.startTime} - ${regimap.endTime} (점심시간 : ${regimap.lunchTime})</td>
				</tr>
				<tr>
					<th class="text-center">수강인원</th>
					<td>${regimap.registrationNumber}명</td>
				</tr>
				<tr>
					<th class="text-center">수강료</th>
					<td><fmt:formatNumber value="${regimap.lecCost}" pattern="#,###" />원</td>
				</tr>
				<tr>
					<th class="text-center">수료 점수</th>
					<td>${regimap.regiPassScore}점</td>
				</tr>
				<tr>
					<th class="text-center">강의실</th>
					<td>${regimap.lectureRoomName}</td>
				</tr>
				<tr>
					<th class="text-center">강의 계획서</th>
					<td><a href="#">강의계획서 보기</a></td>
				</tr>
				<tr>
					<th class="text-center">문의 연락처</th>
					<td>${fn:substring(regimap.lecPhone,0,3)} - ${fn:substring(regimap.lecPhone,4,8)} - ${fn:substring(regimap.lecPhone,9,13)}</td>
				</tr>
			</table>
			<div>
			<!-- 운영자만 수정, 삭제 버튼을 볼 수 있음 -->
		
					<div class="d-grid gap-3">
						<form method="post" action="${pageContext.request.contextPath}/registration/pamyentMoney">
							<input type="number" name="payment" value="0">
							<input type="hidden" name="registrationNo" value="${regimap.registrationNo}">
							<button type="submit" class="btn btn-dark btn-block">제출</button>
						</form>
						<!-- <a href="${pageContext.request.contextPath}/registration/addregistration?lectureName=${regimap.lectureName}" id="btn" class="btn btn-dark btn-block">수강신청</a>  -->	
					</div>				
			</div>
		</div>
		<div id="footer"></div>
	</div>
</div>
</body>
	<script>
    	$('#nav').load('${pageContext.request.contextPath}/include/nav.jsp');
    	$('#navbar').load('${pageContext.request.contextPath}/include/navBar.jsp');
    	$('#footer').load('${pageContext.request.contextPath}/include/footer.jsp');
   	</script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
    <script src="../js/scripts.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
    <script src="assets/demo/chart-area-demo.js"></script>
    <script src="assets/demo/chart-bar-demo.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
    <script src="js/datatables-simple-demo.js"></script>

    <script>
		

    </script>
</html>