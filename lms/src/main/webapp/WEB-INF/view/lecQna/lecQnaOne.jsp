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
	                Lecture Question
	            </div>
	        </div>
			<a href="${pageContext.request.contextPath}/lecQna/lecQnaList?lectureName=${lecQuestion.lectureName}" class="btn btn-dark" style="float:right">이전으로</a>
			<br><br>
			<table class="table">
				<tr>
					<th class="text-center">강의명</th>
					<td>${lecQuestion.lectureName}</td>
				</tr>
				<tr>
					<th class="text-center">작성자</th>
					<td>${lecQuestion.memberId}</td>
				</tr>
				<tr>
					<th class="text-center">제목</th>
					<td>${lecQuestion.lecQuestionTitle}</td>
				</tr>
				<tr style="height:20%;">
					<th class="text-center sm-mb-5" style="vertical-align: middle">내용</th>
					<td><div style="height: 300px;">${lecQuestion.lecQuestionContent}</div></td>
				</tr>
				<tr>
					<th class="text-center">작성일자</th>
					<td>${lecQuestion.createDate}</td>
				</tr>
				<tr>
					<th class="text-center">수정일자</th>
					<td>${lecQuestion.updateDate}</td>
				</tr>
			</table>
			<div>
			<!-- 작성자만 수정, 삭제 버튼을 볼 수 있음 -->
				<c:if test="${sessionMemberId eq lecQuestion.memberId}">
					<!-- 삭제버튼 -->
					<form method="post" action="${pageContext.request.contextPath}/lecQna/deleteLecQna" id="del" style="float:right">
						<input type="hidden" name="lecQuestionNo" value="${lecQuestion.lecQuestionNo}" > <!-- 삭제 실행, hidden타입으로 보이지 않음 -->
						<input type="submit" value="삭제" class="btn btn-danger" id="delBtn">
					</form>
					<!-- 수정버튼 -->
					<a href="${pageContext.request.contextPath}/lecQna/updateLecQna?questionNo=${lecQuestion.lecQuestionNo}" class="btn btn-info" style="float:right">수정</a>
				</c:if>
			</div>
			
			<br><br><br>
			<h2>🗨답변<span class="text-secondary">(${list.size()})</span></h2>
			<br>
			
			<!-- 답변 등록 -->
			<form method="post" action="${pageContext.request.contextPath}/lecQna/addAnswer">
				<input type="hidden" name="lecQuestionNo" value="${lecQuestion.lecQuestionNo}" >
				<input type="hidden" name="memberId" value="${sessionMemberId}" >
				<input type="hidden" name="revelation" value="${lecQuestion.revelation}" >
				<table class="table">
					<thead>
						<tr>
							<th>작성자</th>
							<td>${sessionMemberId}</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>답변</th>
							<td>
								<textarea name="lecAnswerContent" rows="3" cols="160" placeholder="답변 내용을 입력해주세요" class="form-control"></textarea>
								<span id="lecAnswerContentError"></span>
							</td>
							<td>
								<button type="submit" id="btn" class="btn btn-dark">등록</button>
							</td>
						</tr>
					</tbody>
				</table>
			</form>
			
			<!-- 답변 목록 -->
			<!-- 등록된 답변이 없을 시 -->
			<c:if test="${list.size() == 0}">
				<h5 class="text-danger">답변이 등록되지 않았습니다.</h5>
				<br><br><br>
			</c:if>
			<c:forEach var="l" items="${list}">
				<table class="table">
					<thead>
						<tr>
							<th>작성자</th>
							<td>${l.memberId}</td>
							<th>작성일자</th>
							<td>${l.createDate}</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>답변</th>
							<td colspan="3">${l.lecAnswerContent}</td>
						</tr>
					</tbody>
				</table>
				<br><br>
			</c:forEach>
			<div class="text-center">
				<c:if test="${currentPage>1}">
					<a href="${pageContext.request.contextPath}/lecQna/lecQnaOne?lecQuestionNo=${lecQuestion.lecQuestionNo}&&currentPage=${currentPage-1}" class="btn btn-dark">이전</a>
				</c:if>
				<c:if test="${lastPage>currentPage}">
					<a href="${pageContext.request.contextPath}/lecQna/lecQnaOne?lecQuestionNo=${lecQuestion.lecQuestionNo}&&currentPage=${currentPage+1}" class="btn btn-dark">다음</a>
				</c:if>
			</div>
			<br>
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
             if (confirm('해당 질문을 삭제 하시겠습니까?')) {
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