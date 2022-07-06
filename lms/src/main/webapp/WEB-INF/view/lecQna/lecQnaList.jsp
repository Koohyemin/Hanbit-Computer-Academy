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
<title>Lecture List</title>
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
	                Lecture Question
	            </div>
            </div>
            
            <!-- 학생만 질문 작성을 할 수 있음 -->
            <c:if test="${sessionMemberLv == 1}">
	            <a class="float-right btn btn-dark float-end" href="${pageContext.request.contextPath}/lecQna/addLecQna">질문 작성</a> <br><br>            
            </c:if>
            
            <ul class="nav nav-tabs">
	            <c:forEach var="l" items="${lectureList}">
					<li class="nav-item">	
						<a class="nav-link active text-dark" data-toggle="tab" href="${pageContext.request.contextPath}/lecQna/lecQnaList?lectureName=${l}">${l}</a>
					</li>
	            </c:forEach>
            </ul>
            <br><br>
            
			<table class="table table-hover">
				<thead>
					<tr>
						<th class="text-center">강의명</th>
						<th class="text-center">제목</th>
						<th class="text-center">작성자</th>
						<th class="text-center">작성일</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="l" items="${list}" varStatus="status">
						<tr>
							<th class="text-center text-success">${l.lectureName}</th>
							<!-- 비밀글 표시, 답변이 0개가 아니라면 개수 표시 -->
							<c:choose>
								<c:when test="${l.revelation eq 'F'}">
								
									<!-- 학생이 본인게시글이 아니라면 게시글 상세보기로 넘어갈 수 없음 -->
									<!-- 본인게시글이거나 학생이 아니라면 열람 가능 -->
									<c:choose>
										<c:when test="${sessionMemberId ne l.memberId && sessionMemberLv == 1}">
											<td class="col-md-4">🔒 비공개 질문 입니다
												<c:if test="${l.answerCount != 0}">
													<span class="text-secondary">[${l.answerCount}]</span>
												</c:if>
											</td>
										</c:when>
										<c:otherwise>
											<td class="col-md-4"><a href="${pageContext.request.contextPath}/lecQna/lecQnaOne?lecQuestionNo=${l.lecQuestionNo}" class="none-unline">🔒 비공개 질문 입니다
												<c:if test="${l.answerCount != 0}">
													<span class="text-secondary">[${l.answerCount}]</span>
												</c:if>
											</a></td>											
										</c:otherwise>
									</c:choose>
								</c:when>
								<c:otherwise>
									<td class="col-md-4"><a href="${pageContext.request.contextPath}/lecQna/lecQnaOne?lecQuestionNo=${l.lecQuestionNo}" class="none-unline">${l.lecQuestionTitle}
										<c:if test="${l.answerCount != 0}">
											<span class="text-secondary">[${l.answerCount}]</span>
										</c:if>
									</a></td>							
								</c:otherwise>
							</c:choose>
							<td class="text-center">${l.memberId}</td>
							<td class="text-center">${l.createDate}</td>
						</tr>
					</c:forEach>
					
					<!-- 선택과목이 있지만 질문 개수가 0개라면 -->
					<c:if test="${list.size() == 0}">
						<tr>
							<td class="text-primary text-center" colspan="5">해당 강의에 등록된 질문이 없습니다</td>
						</tr>							
					</c:if>

					
				</tbody>
			</table>
			<div class="text-center">
				<c:if test="${currentPage>1}">
					<a href="${pageContext.request.contextPath}/lecQna/lecQnaList?currentPage=${currentPage-1}&&lectureName=${lectureName}" class="btn btn-dark">이전</a>
				</c:if>
				<c:if test="${lastPage>currentPage}">
					<a href="${pageContext.request.contextPath}/lecQna/lecList?currentPage=${currentPage+1}&&lectureName=${lectureName}" class="btn btn-dark">다음</a>
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