<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	                LectureNotice
	            </div>
				<br><br>
				<form method="post" action="${pageContext.request.contextPath}/lectureNotice/addLectureNotice">
					<table class="table">
						<tr>
							<td> 
								<input name="memberId" type="text" value="${sessionMemberId}" readonly="readonly" class="form-control">
							</td>
						</tr>
						<tr>
							<td> 
								<select id="lectureName" name="lectureName">
									<option value="">------------------------------선택---------------------------</option>
									<c:forEach var="l" items="${lectureNameList}">
										<option value="${l.lectureName}" name="${l.lectureName}">11212</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<td>
								<input name="title" id="title" type="text" class="form-control" placeholder="제목을 입력해주세요">
								<span id="helpTitle"></span>
							</td>
						</tr>
						<tr>
							<td>
								<input name="content" id="content" class="form-control" placeholder="내용을 입력해주세요" cols="50" rows="8">
								<span id="helpContent"></span>
							</td>
						</tr>
					</table>
					<button type="submit" id="btnlecn" class="btn btn-dark">등록</button>
				</form>
			</div>
			</div>
		<div id="footer"></div>
    </div>
</div>
</body>
	<script>
	$('#btnlecn').click(function () {
		if($('#title').val() == ''){
			$('#helpTitle').text('제목을 입력해주세요');
			return false;
		} else {
			$('#helpTitle').text('');
		}
		if($('#content').val() == '') {
			$('#helpContent').text('내용을 입력해주세요');
			return false;
		} else {
			$('#helpContent').text('');
		}
		if($('#title').val() != '' && $('#content').val() != '') {
			$("#btnlecn").submit();
		}
		
	})
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