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
                	<div class="col-lg-7 col-md-7">
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
						<c:forEach var="waitingList"  items="${waitingList}">
							<tr>
								<td>${waitingList.memberId}</td>		
								<td>${waitingList.createDate}</td>
								<td>${waitingList.memberLevel}</td>
								<td>${waitingList.meberState}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			<h5>강의계획서 목록</h5>
				<table class="table">
					  <thead>
                     <tr>
                        <th>이름</th>
                        <th>글쓴이</th>
                        <th>상태</th>
                        <th>작성일</th>
                     </tr>
                  </thead>
                  <tbody>
                  <c:forEach var="lecPlanList" items="${lecPlanList}">
                     <tr>
                        <td>${lecPlanList.lectureName}</td>
                        <td>${lecPlanList.teacherId}</td>
                        <td>${lecPlanList.lecState}</td>               
                        <td>${lecPlanList.createDate}</td>
                     </tr>
                  </c:forEach>
                  </tbody>
				</table>
			<h5>문의사항 목록</h5>
				<table class="table">
					<thead>
						<tr>
							<th>번호</th>
							<th>글쓴이</th>
							<th>내용</th>
							<th>구분</th>
							<th>작성일</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="enquiryBoardList" items="${enquiryBoardList}">
							<tr>
								<td>${enquiryBoardList.enquiryBoardNo}</td>
								<td>${enquiryBoardList.memberId}</td>
								<td>${enquiryBoardList.content}</td>
								<td>${enquiryBoardList.category}</td>
								<td>${enquiryBoardList.createDate}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			<h5>공지사항 목록</h5>
				<table>
					<thead>
						<tr>
							<th>번호</th>
							<th>글쓴이</th>
							<th>제목</th>
							<th>내용</th>
							<th>구분</th>
							<th>작성일</th>
						</tr>						
					</thead>
					<tbody>
						<c:forEach var="noticeList" items="${noticeList}">
							<tr>
								<td>${noticeList.managerNoticeNo}</td>
								<td>${noticeList.managerId}</td>
								<td>${noticeList.managerNoticeTitle}</td>
								<td>${noticeList.managerNoticeContent}</td>
								<td>${noticeList.category}</td>
								<td>${noticeList.createDate}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>	
				</div>
				</c:when>
				<c:when test="${sessionMemberLv == 2}">
					<h5>강사 공지사항 목록</h5>
						<table>
							<thead>
								<tr>
									<th>번호</th>
									<th>글쓴이</th>
									<th>제목</th>
									<th>구분</th>
									<th>작성일</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="teacherList" items="${teacherList}">
									<tr>
										<td>${teacherList.managerNoticeNo}</td>
										<td>${teacherList.managerId}</td>
										<td>${teacherList.managerNoticeTitle}</td>
										<td>${teacherList.category}</td>
										<td>${teacherList.createDate}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
				</c:when>
				<c:otherwise>
					<h5>학생이 수강중인 강좌</h5>
					<table>
						<thead>
							<tr>
								<th>번호</th>
								<th>수업이름</th>
								<th>ID</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="studentLecList" items="${studentLecList}">
								<tr>
									<td>${studentLecList.registrationNo}</td>
									<td>${studentLecList.lectureName}</td>
									<td>${studentLecList.studentId}</td>

								</tr>
							</c:forEach>
						</tbody>
					</table>
					<h5>학생 공지사항 목록</h5>
					<table>
							<thead>
								<tr>
									<th>번호</th>
									<th>글쓴이</th>
									<th>제목</th>
									<th>구분</th>
									<th>작성일</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="studentList" items="${studentList}">
									<tr>
										<td>${studentList.managerNoticeNo}</td>
										<td>${studentList.managerId}</td>
										<td>${studentList.managerNoticeTitle}</td>
										<td>${studentList.category}</td>
										<td>${studentList.createDate}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
				</c:otherwise>
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