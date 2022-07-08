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
<title>submitOneEvaluate</title>
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
	                        Evaluate
	                    </div>
	                    <table class="table table-hover">
	                    	<tr>
	                    		<th>학생이름</th>
	                    		<td>${homeworkSubmission.studentId}</td>
	                    	</tr>
	                    	<tr>
	                    		<th>제출과제 제목</th>
	                    		<td>${homeworkSubmission.homeworkSubmissionTitle}</td>
	                    	</tr>
	                    	<tr>
	                    		<th>제출과제 내용</th>
	                    		<td>${homeworkSubmission.homeworkSubmissionContent}</td>
	                    	</tr>
	                    	<tr>
	                    		<th>첨부파일</th>
	                    		<c:forEach var="h" items="${homeworkFileNameList}">
	                    			<td>
	                    				${h.homeworkFileName}
	                    			</td>
	                    		</c:forEach>
	                    	</tr>
	                    </table>
					</div>
                <form method="post" action="${pageContext.request.contextPath}/lecHomework/submitOneEvaluate">
                	<input type="hidden" name="homeworkSubmissionNo" value="${homeworkSubmission.homeworkSubmissionNo}">
                	<input type="hidden" name="homeworkMakeNo" value="${homeworkSubmission.homeworkMakeNo}">
                	<input type="number" name="homeworkScore" value="0" max="100" min="0">
                	<button type="submit">점수 입력</button>
               	</form>
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