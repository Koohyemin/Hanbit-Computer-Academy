<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<meta property="og:title" content="한빛컴퓨터아카데미LMS">
<meta property="og:url" content="lms/login">
<meta property="og:image"
	content="${pageContext.request.contextPath}/img/previewer.png">
<title>getStatsQuestionnaireList</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link
	href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css"
	rel="stylesheet" />
<link href="../css/styles.css" rel="stylesheet" />
<script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js"
	crossorigin="anonymous"></script>
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
						<i class="fas fa-chart-area me-1"></i> 학생 점수 리스트
					</div>
					<div class="card-body">
						<table class="table">
							<tr>
								<td>강좌이름</td>
								<td>이름</td>
								<td>총 평균</td>
							</tr>
							<c:forEach items="${list}" var="s">
								<tr>
									<td>${s.lectureName}</td>
									<td>${s.studentName}</td>
									<td>${s.avg}점</td>

								</tr>
							</c:forEach>

						</table>

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
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	crossorigin="anonymous"></script>
<script src="../js/scripts.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js"
	crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest"
	crossorigin="anonymous"></script>
</html>