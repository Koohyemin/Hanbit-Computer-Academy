<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>lectureStudentList</title>
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
			<br>
	                <div class="card mb-4">
	                    <div class="card-header">
	                        <i class="fas fa-chart-area me-1"></i>
	                        LecturStudentList
	                    </div>
	                    <div class="card-body">
							<!-- LecNotice 목록 리스트  -->
						<form method="post" action="${pageContext.request.contextPath}/lec/lectureStudentList">
						   <div class="form-group btn-group" >
						    <select id="lectureName" name="lectureName" class="form-control">
						 	   <option selected="selected">-----------------------------강좌선택--------------------------</option>
								<c:forEach var="l" items="${lectureNameList}">
								<option value="${l.lectureName}">${l.lectureName}</option>
								</c:forEach>
							</select>	
							<button type="submit" class="btn btn-dark">강좌조회</button>					   
							</div>					             	
				        </form>
				        
				        <c:forEach var="s" items="${selectLecStudentList}">
				        	<table>
				        	<tr>
				        	<td>이름</td><td>${s.studentName}</td>
				        	</tr>
				        	<tr>
				        	<td>생일</td><td>${s.studentBrith}</td>
				        	</tr>
				        	<tr>
				        	<td>아이디</td><td>${s.studentId}</td>
				        	</tr>
				        	<tr>
				        	<td>이메일</td><td>${s.studentEmail}</td>
				        	</tr>
				        	<tr>
				        	<td>연락처</td><td>${s.studentPhone}</td>
				        	</tr>
				        	</table>
				        </c:forEach>
	                </div>
	         	</div>
	         </div>
			<div id="footer"></div>
		</div>
	</div>
</body>
	<script>
	</script>
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