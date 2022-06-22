<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>studentOne</title>
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
								나의 정보
	                    </div>
	                    <div class="card-body">
							<div class="row"> 
							
								<div class="col-lg-1"></div>
								
								<!-- 사진 -->
					        	<div class="col-lg-3">
					        	<br>
					           		<img src="${pageContext.request.contextPath}/assets/img/${photoFile.photoName}.${photoFile.photoType}" class="img-fluid" alt="" width="150" height="200">  <!-- 사진추가 -->
					        	</div>
					        	<!-- 사진 끝 -->
					        	
					        	<!-- 개인정보 -->
					        	<div class="col-lg-3">
						        	<br>
					        		<div>
			        					<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-caret-right-fill" viewBox="0 0 16 16"> <path d="m12.14 8.753-5.482 4.796c-.646.566-1.658.106-1.658-.753V3.204a1 1 0 0 1 1.659-.753l5.48 4.796a1 1 0 0 1 0 1.506z"/></svg>
			        					이름 : ${student.studentName}
					        		</div>
		        					<br>
			        				<div>
		        						<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-caret-right-fill" viewBox="0 0 16 16"> <path d="m12.14 8.753-5.482 4.796c-.646.566-1.658.106-1.658-.753V3.204a1 1 0 0 1 1.659-.753l5.48 4.796a1 1 0 0 1 0 1.506z"/></svg>
		        						아이디 : ${student.studentId}</li>
						        	</div>
		        					<br>
		        					<div>
				        				<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-caret-right-fill" viewBox="0 0 16 16"> <path d="m12.14 8.753-5.482 4.796c-.646.566-1.658.106-1.658-.753V3.204a1 1 0 0 1 1.659-.753l5.48 4.796a1 1 0 0 1 0 1.506z"/></svg>
										성별 : ${student.studentGender}
					        		</div>
				        			<br>
					        		<div>
				        				<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-caret-right-fill" viewBox="0 0 16 16"> <path d="m12.14 8.753-5.482 4.796c-.646.566-1.658.106-1.658-.753V3.204a1 1 0 0 1 1.659-.753l5.48 4.796a1 1 0 0 1 0 1.506z"/></svg>
										출생년도 : ${student.studentBirth}
					        		</div>
		        					<br>
			        				<div>
		        						<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-caret-right-fill" viewBox="0 0 16 16"> <path d="m12.14 8.753-5.482 4.796c-.646.566-1.658.106-1.658-.753V3.204a1 1 0 0 1 1.659-.753l5.48 4.796a1 1 0 0 1 0 1.506z"/></svg>
										연락처 : ${student.studentPhone}
					        		</div>
			        			</div>
			        			<div class="col-lg-4">
						        	<br>
					        		<div>
			        					<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-caret-right-fill" viewBox="0 0 16 16"> <path d="m12.14 8.753-5.482 4.796c-.646.566-1.658.106-1.658-.753V3.204a1 1 0 0 1 1.659-.753l5.48 4.796a1 1 0 0 1 0 1.506z"/></svg>
			        					주소 : ${student.studentAddr1}
					        		</div>
		        					<br>
			        				<div>
		        						<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-caret-right-fill" viewBox="0 0 16 16"> <path d="m12.14 8.753-5.482 4.796c-.646.566-1.658.106-1.658-.753V3.204a1 1 0 0 1 1.659-.753l5.48 4.796a1 1 0 0 1 0 1.506z"/></svg>
		        						상세주소 : ${student.studentAddr2}
						        	</div>
		        					<br>
		        					<div>
				        				<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-caret-right-fill" viewBox="0 0 16 16"> <path d="m12.14 8.753-5.482 4.796c-.646.566-1.658.106-1.658-.753V3.204a1 1 0 0 1 1.659-.753l5.48 4.796a1 1 0 0 1 0 1.506z"/></svg>
										최종학력 : ${student.finalEducation}
					        		</div>
				        			<br>
					        		<div>
				        				<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-caret-right-fill" viewBox="0 0 16 16"> <path d="m12.14 8.753-5.482 4.796c-.646.566-1.658.106-1.658-.753V3.204a1 1 0 0 1 1.659-.753l5.48 4.796a1 1 0 0 1 0 1.506z"/></svg>
										이메일 : ${student.studentEmail}
					        		</div>
			        			<!-- 개인정보 끝 -->
			        		</div>
		        		</div>
			        	</div>
			        	<br>
			        	<div class="row">
			        		<!-- 자격증 -->
			        		<div class="col-lg-6">
			                <div class="card mb-4">
			                    <div class="card-header">
			                        <i class="fas fa-chart-area me-1"></i>
										보유 자격증
			                    </div>
				        			<table class="table table-hover">
			        				<thead>
			        					<tr>
			        						<th class="text-center">자격증명</th>
			        						<th class="text-center">주관처</th>
			        						<th class="text-center">취득일</th>
			        					</tr>
			        				</thead>
			        				<tbody>
			        				<c:forEach var="c" items="${certificationList}">
			        					<tr>
			        						<td class="text-center">${c.certificationName}</td>
			        						<td class="text-center">${c.certificationIssued}</td>
			        						<td class="text-center">${c.getDate}</td>
			        					</tr>
			        				</c:forEach>
			        				</tbody>
			        			</table>
							</div>
				        	</div>
			        		<!-- 자격증 끝 -->
			        		<!-- 수강내역 -->
		        			<div class="col-lg-6">
		        			<div class="card mb-4">
			                    <div class="card-header">
			                        <i class="fas fa-chart-area me-1"></i>
										수강 목록
			                    </div>
			        			<table class="table table-hover">
			        				<thead>
			        					<tr>
			        						<th class="text-center">강좌</th>
			        						<th class="text-center">과목</th>
			        						<th class="text-center">개설날짜</th>
			        					</tr>
			        				</thead>
			        				<tbody>
			        				<c:forEach var="l" items="${lecList}">
			        					<tr>
			        						<td class="text-center"><a target='_blank' href="${pageContext.request.contextPath}/lec">${l.lectureName}</a></td><!-- 수강 상세보기로 이동 -->    						
			        						<td class="text-center">${l.subjectName}</td>
			        						<td class="text-center">${l.createDate}</td>
			        					</tr>
			        				</c:forEach>
			        				</tbody>
			        			</table>
			        		</div>
		        			</div>
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
</html>