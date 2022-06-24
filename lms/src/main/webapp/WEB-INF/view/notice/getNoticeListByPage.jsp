<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<meta property="og:title" content="한빛컴퓨터아카데미LMS">
<meta property="og:url" content="lms/login">
<meta property="og:image" content="${pageContext.request.contextPath}/img/previewer.png">
<title>getNoticeListByPage</title>
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
	            </div>
            </div>
			<!-- 상단 전체, 강사, 학생별 공지사항 보기 nav바 -->
			<ul class="nav nav-tabs">
				<li class="nav-item">
					<!-- 운영자, 강사, 학생 모두 확인 가능 -->
					<c:choose>
						<c:when test="${category eq '전체'}">
							<a class="nav-link active" data-toggle="tab" href="${pageContext.request.contextPath}/notice/noticeList?category=전체">전체</a> <!-- 기본값이 전체로 잡혀있기 때문에 현제 페이지로 이동하도록 조건을 잡아놓음 -->
						</c:when>
						<c:otherwise>
						    <a class="nav-link" data-toggle="tab" href="${pageContext.request.contextPath}/notice/noticeList?category=전체">전체</a>
						</c:otherwise>
					</c:choose>
				</li>
				
				<!-- 운영자, 강사만 확인 가능 -->
				<c:if test="${sessionMemberLv == 2 || sessionMemberLv == 3}">
					<li class="nav-item">
						<c:choose>
							<c:when test="${category eq '강사'}">
							    <a class="nav-link active" data-toggle="tab" href="${pageContext.request.contextPath}/notice/noticeList?category=강사">강사</a>
							</c:when>
							<c:otherwise>
								<a class="nav-link" data-toggle="tab" href="${pageContext.request.contextPath}/notice/noticeList?category=강사">강사</a>
							</c:otherwise>
						</c:choose>
					</li>
				</c:if>
				
				<!-- 학생, 운영자 확인 가능 -->
				<c:if test="${sessionMemberLv == 1 || sessionMemberLv == 3}">
					<li class="nav-item">
						<c:choose>
							<c:when test="${category eq '학생'}">
							    <a class="nav-link active" data-toggle="tab" href="${pageContext.request.contextPath}/notice/noticeList?category=학생">학생</a>
							</c:when>
							<c:otherwise>
								<a class="nav-link" data-toggle="tab" href="${pageContext.request.contextPath}/notice/noticeList?category=학생">학생</a>
							</c:otherwise>
						</c:choose>
					</li>
				</c:if>
			</ul>
			<c:if test="${sessionMemberLv == 3}">
				<!-- 공지사항 작성 버튼은 운영진에게만 보임 -->
				<span class="float-end">
					<a class="float-right btn btn-dark" href="${pageContext.request.contextPath}/notice/addNotice">글 작성</a>
				</span>
			</c:if>
			<table class="table table-hover">
				<thead>
					<tr>
						<th class="text-center">번호</th>
						<th class="text-center">대상</th>
						<th class="text-center">제목</th>
						<th class="text-center">작성자</th>
						<th class="text-center">작성일</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="notice" items="${list}">
						<tr>
							<td class="text-center">${notice.managerNoticeNo}</td>
							<td class="text-center">${notice.category}</td>
							<td class="col-md-4"><a href="${pageContext.request.contextPath}/notice/noticeOne?managerNoticeNo=${notice.managerNoticeNo}" style="text-decoration: none;">${notice.managerNoticeTitle}</a></td>
							<td class="text-center">${notice.managerId}</td>
							<td class="text-center">${notice.createDate}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="text-center">
				<c:if test="${currentPage>1}">
					<a href="${pageContext.request.contextPath}/notice/noticeList?currentPage=${currentPage-1}&&category=${category}" class="btn btn-dark">이전</a>
				</c:if>
				<c:if test="${lastPage>currentPage}">
					<a href="${pageContext.request.contextPath}/notice/noticeList?currentPage=${currentPage+1}&&category=${category}" class="btn btn-dark">다음</a>
				</c:if>
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