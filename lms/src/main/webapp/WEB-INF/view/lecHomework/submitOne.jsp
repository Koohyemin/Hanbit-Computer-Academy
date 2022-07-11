<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
<title>submitOne</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
<link href="../css/styles.css" rel="stylesheet" />
<script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
<!-- 썸머노트 cdn -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>
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
	                Student Homework Submission ONe
	                <c:choose>
	                	<c:when test="${sessionMemberLv == 1 }">
							<a href="${pageContext.request.contextPath}/lecHomework/getLecHomeworkList" class="btn btn-dark btn-sm" style="float:right">이전으로</a>
	                	</c:when>
	                	<c:when test="${sessionMemberLv == 2}">
	                		<a href="${pageContext.request.contextPath}/lwecHomework/studentSubmitList?homeworkMakeNo=${homeworkSubmission.homeworkMakeNo}" class="btn btn-dark btn-sm" style="float:right">이전으로</a>
	                	</c:when>
	                </c:choose>
	            </div>
			<table class="table">
				<tr>
					<th class="text-center">등록자</th>
					<td>
						<input name="homeworkMakeNo" type="hidden" value="${homeworkSubmission.homeworkMakeNo}">
						<input name="managerId" type="text" value="${homeworkSubmission.studentId}" readonly="readonly" class="form-control">
					</td>
				</tr>
				<tr>
					<th class="text-center">과제 제목</th>
					<td>
						<input name="homeworkMakeTitle" type="text" value="${homeworkMakeTitle}" readonly="readonly" class="form-control">
						<span class="text-danger" id="categoryError"></span>
					</td>
				</tr>
				<tr>
					<th class="text-center">제목</th>
					<td>
						<input name="homeworkSubmissionTitle" id="title" type="text" class="form-control"  value="${homeworkSubmission.homeworkSubmissionTitle}" readonly="readonly" placeholder="제목을 입력해주세요">
						<span class="text-danger" id="titleError"></span>
					</td>
				</tr>
				<tr>
					<th class="text-center">파일업로드</th>
					<td>
					<c:choose>
						<c:when test="${fn:length(homeworkFileList) > 0}">
							<c:forEach var="f" items="${homeworkFileList}">
								<div><a href="${pageContext.request.contextPath}/upload/${f.homeworkFileName}" download="">${f.homerworkFileOriginalName}</a></div>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<span class="text-danger">등록된 파일이 없습니다.</span>
						</c:otherwise>
					</c:choose>
					</td>
				</tr>
				<tr>
					<th class="text-center" style="vertical-align: middle">내용</th>
					<td>
						<textarea readonly="readonly" disabled name="homeworkSubmissionContent" cols="150" rows="10" >${homeworkSubmission.homeworkSubmissionContent}</textarea>
						<span class="text-danger" id="contentError"></span>
					</td>
				</tr>
			</table>
			</div>
			
			<!-- 학생이라면 수정버튼과 삭제버튼 보이게 -->
			<c:if test="${sessionMemberLv == 1}">
				<a class="btn btn-secondary" style="float:right" id="delBtn" href="${pageContext.request.contextPath}/lecHomework/removeSubmit?homeworkSubmissionNo=${homeworkSubmission.homeworkSubmissionNo}">삭제</a>
				<a class="btn btn-dark" style="float:right"  href="${pageContext.request.contextPath}/lecHomework/modifySubmit?homeworkSubmissionNo=${homeworkSubmission.homeworkSubmissionNo}&&homeworkMakeTitle=${homeworkMakeTitle}">수정</a>
			</c:if>
			
			<!-- 강사라면 점수주기 버튼이 보이게 -->
			<c:if test="${sessionMemberLv == 2}">
				 <form method="post" action="${pageContext.request.contextPath}/lecHomework/submitOneEvaluate">
                	<input type="hidden" name="homeworkSubmissionNo" value="${homeworkSubmission.homeworkSubmissionNo}">
                	<input type="hidden" name="homeworkMakeNo" value="${homeworkSubmission.homeworkMakeNo}">
                	<button class="btn btn-secondary btn-sm" style="float:right" type="submit">점수 입력</button>
                	<input style="float:right" type="number" name="homeworkScore" value="${homeworkSubmission.homeworkScore}" max="100" min="0">
               	</form>
			</c:if>
		</div>
	<div id="footer"></div>
   	</div>
</div>
</body>
	<script>
	// html 태그 형성 이후 실행
    // 삭제이벤트
    $("#delBtn").click(function(){
    	if(confirm('해당 과제를 삭제 하시겠습니까?')) {
    		
    	} else {
    		return false;
    	}
    });
	
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
</html>s