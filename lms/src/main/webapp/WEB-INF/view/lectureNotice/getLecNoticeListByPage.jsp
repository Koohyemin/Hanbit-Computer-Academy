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
<title>getFaqListByPage</title>
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
	                    <div class="card-body">
							<!-- LecQuestion 목록 리스트  -->
							
						<form method="post" action="${pageContext.request.contextPath}/lectureNotice/getLecNoticeListByPage">
						   
						   <div class="form-group btn-group" >
						    <select id="lectureName" name="lectureName" class="form-control">
						 	   <option selected="selected" >-----------------------------강좌선택--------------------------</option>
								<c:forEach var="l" items="${lectureNameList}">
								<option value="${l.lectureName}">${l.lectureName}</option>
								</c:forEach>
							</select>	
							<button type="submit" class="btn btn-dark">공지사항 조회</button>					   
							</div>					             	
				        </form>
				        <c:if test="${fn:length(list) == 0}">
				        <br>
				        	<b>강좌를 선택해주세요</b><br>
				        	<c:if test="${sessionMemberLv == 2 }">
       							<a class="btn btn-dark" href="${pageContext.request.contextPath}/lectureNotice/getInsertLectureNotice">공지사항입력</a>
       							</c:if> 
       						</c:if>
				         <c:if test="${fn:length(list) != 0}">
			               <table class="table">
			                   <thead>
			                       <tr>
			                           	<th>번호</th>
						                <th>제목	</th>
						                <th>강의</th>
										<th>작성자</th>
						                <th>작성일</th>
			                       </tr>
			                   </thead>
				                  <tbody>
						            <c:forEach var="lectureNotice" items="${list}">
						                <tr>
						                   <td>${lectureNotice.lecNoticeNo}</td>
						                   <td><a class="text-decoration-none text-dark" href="${pageContext.request.contextPath}/lectureNotice/getLecNoticeOne?lecNoticeNo=${lectureNotice.lecNoticeNo}">${lectureNotice.title}</a></td>
						                   <td>${lectureNotice.lectureName}</td>
						                   <td>${lectureNotice.memberId}</td>
						                   <td>${lectureNotice.createDate}</td>
						                </tr>
						            </c:forEach>
						        </tbody>
		              		 </table>
				        
		              		 <div class="text-center">
								<c:if test="${currentPage>1}">
									<a href="${pageContext.request.contextPath}/lectureNotice/getLecNoticeListByPage?currentPage=${currentPage-1}" class="btn btn-dark">이전</a>
								</c:if>
								<c:if test="${lastPage>currentPage}">
									<a href="${pageContext.request.contextPath}/lectureNotice/getLecNoticeListByPage?currentPage=${currentPage+1}" class="btn btn-dark">다음</a>
								</c:if>
							</div>
		              		 <c:if test="${sessionMemberLv == 2 }">
       							<a class="btn btn-dark" href="${pageContext.request.contextPath}/lectureNotice/getInsertLectureNotice">공지사항입력</a>
       							</c:if> 
       						</c:if>
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
