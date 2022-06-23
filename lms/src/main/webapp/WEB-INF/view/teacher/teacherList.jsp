<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>addFaq</title>
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
                Teacher
            </div>
            
            <!-- trigger 사용해서 $( "#btn" ).trigger( "click" ); -->
            <input type="text" name="searchValue" class="form-control" value="" placeholder="강사 이름을 입력해주세요">
            
            <!-- 강사소개 -->
            <c:forEach var="map" items="teacherList">
	            <div class="col-lg-6 col-md-6 col-12">
	            
					<div class="card" data-wow-delay=".2s" style="visibility: visible; animation-delay: 0.2s; animation-name: fadeInUp;">
					<div class="row">
					
					<div class="col-lg-5 col-12">
					<!-- 사진 -->
					<div class="image">
					<img src="" alt="">
					</div>
					
					</div>
					<div class="col-lg-7 col-12">
					<div class="info-head">
					
					<div>
					<!-- 이름 -->
					<h4>강사 이름</h4>
					<!-- 강사 소개 / 우리는 현재 맡은 수업 이름 -->
					<p>행복을 선언하고 행복을 호출하면 행복이 실행됩니다^^</p>
					</div>
					<!-- 담당 과목 -->
					<span class="designation">JAVA/SPRING</span>
					
					<!-- 아이콘 리스트 -->
					<ul class="social">
						<li><a href="#"><i class="lni lni-facebook-filled"></i></a></li>
						<li><a href="#"><i class="lni lni-twitter-original"></i></a></li>
						<li><a href="#"><i class="lni lni-linkedin-original"></i></a></li>
						<li><a href="#"><i class="lni lni-behance-original"></i></a></li>
					</ul>
					
					</div>
					</div>
					</div>
					</div>
				</div>
            </c:forEach>
            
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