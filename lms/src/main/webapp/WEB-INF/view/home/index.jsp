<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>index</title>
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
                <c:choose>
                	<c:when test="${sessionMemberLv == 3}">
                	<h5>가입요청 목록</h5> 
                 <table class="table">
					<thead>
						<tr>
							<th>아이디</th>
							<th>신청일</th>
							<th>등급</th>
							<th>상태</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="w"  items="${waitingList}">
							<tr>
								<td>${w.memberId}</td>		
								<td>${w.createDate}</td>
								<td>${w.memberLevel}</td>
								<td>${w.meberState}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
							
                <h5>강의계획서 승인요청</h5>
                 <table class="table">
					<thead>
						<tr>
							<th>강의계획서</th>		
							<th>강사ID</th>
							<th>상태</th>												
						</tr>
					</thead>
					<tbody>
					<c:forEach var="l" items="${lecPlanList}">
						<tr>
							<td>${l.lectureName}</td>
							<td>${l.teacherId}</td>
							<td>${l.lecState}</td>
						</tr>
					</c:forEach>					
					</tbody>
				</table>
				
				<h5>문의사항리스트</h5>
                 <table class="table">
                  <thead>
                     <tr>
                        <th>글번호</th>
                        <th>글쓴이</th>
                        <th>내용</th>
                        <th>작성일</th>
                     </tr>
                  </thead>
                  <tbody>
                  <c:forEach var="e" items="${enquiryBoardList}">
                     <tr>
                        <td>${e.enquiryBoardNo}</td>
                        <td>${e.memberId}</td>
                        <td>${e.content}</td>               
                        <td>${e.createDate}</td>
                     </tr>
                  </c:forEach>
                  </tbody>
               </table>
               
               <h5>공지사항 목록</h5>
                 <table class="table">
				<thead>
					<tr>
						<th>번호</th>
						<th>대상</th>
						<th>제목</th>
						<th>작성자</th>
						<th>작성일</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="n" items="${noticeList}">
						<tr>
							<td>${n.managerNoticeNo}</td>
							<td>${n.category}</td>
							<td>${n.managerNoticeTitle}</td>
							<td>${n.managerId}</td>
							<td>${n.createDate}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
         		</c:when>
                	<c:when test="${sessionMemberLv == 2}">
                		
					</c:when>         		
                </c:choose>
                
               
               
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