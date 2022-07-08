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
<title>getNoticeOne</title>
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
	                Notice
	                <a href="${pageContext.request.contextPath}/notice/noticeList" class="btn btn-dark btn-sm float-end" style="float:right">이전으로</a>
	            </div>
	            <div class="card-body">
		            <table class="table table-bordered">
						<tr>
							<th class="text-center" width="150px;">제목</th>
							<td colspan="3">${managerNotice.managerNoticeTitle}</td>
						</tr>
						<tr>
							<th class="text-center" width="150px;">글쓴이</th>
							<td>${managerNotice.managerId}</td>
							<th class="text-center" width="150px;">대상</th>
							<td>${managerNotice.category}</td>
						</tr>
						<tr>
							<th class="text-center" width="150px;">생성일</th>
							<td>${managerNotice.createDate}</td>
							<th class="text-center" width="150px;">수정일</th>
							<td>${managerNotice.updateDate}</td>
						</tr>
						<tr style="height:20%;">
							<th class="text-center sm-mb-5" style="vertical-align: middle" width="150px;">내용</th>
							<td colspan="3" ><div style="height: 300px;">${managerNotice.managerNoticeContent}</div></td>
						</tr>
					</table>
					<!-- 운영자만 수정, 삭제 버튼을 볼 수 있음 -->
					<c:if test="${sessionMemberLv == 3}">
						<!-- 삭제버튼 -->
						<div class="text-center">
							<form method="post" action="${pageContext.request.contextPath}/notice/deleteNotice" id="del">
								<input type="hidden" name="managerNoticeNo" value="${managerNotice.managerNoticeNo}" > <!-- 삭제 실행, hidden타입으로 보이지 않음 -->
								<div class="btn-group">
									<a href="${pageContext.request.contextPath}/notice/updateNotice?managerNoticeNo=${managerNotice.managerNoticeNo}" class="btn btn-dark">수정</a><!-- 수정버튼 -->
									<button type="submit" class="btn btn-secondary" id="delBtn">삭제</button>		
								</div>
							</form>
						</div>
					</c:if>
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
    	 $("#delBtn").click(function(){
             if (confirm('해당 공지사항을 삭제 하시겠습니까?')) {
                 $('#del').submit();
             } else {
             	return false;
             }
         });
   	</script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
    <script src="../js/scripts.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
    <script src="assets/demo/chart-area-demo.js"></script>
    <script src="assets/demo/chart-bar-demo.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
    <script src="js/datatables-simple-demo.js"></script>
</html>