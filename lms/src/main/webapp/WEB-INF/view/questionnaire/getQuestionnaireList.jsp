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
<title>getQuestionnaireList</title>
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
			강의평가
        </div>
        <div class="card-body">
        <form>
		<table class="table">
			<thead>
				<tr>
					<th>번호</th>
					<th>강의이름</th>
					<th>구분</th>
					<th>질문내용</th>
					<th class="text-center">전혀아니다</th>
					<th class="text-center">아니다</th>
					<th class="text-center">보통이다</th>
					<th class="text-center">그렇다</th>
					<th class="text-center">매우그렇다</th>
				</tr>
			</thead>
			<tbody>	
				<c:forEach var="q" items="${list}">
					<tr>
						<td>${q.evaluationNo}<input type="hidden" name="content" value="${q.evaluationNo}"></td>	
						<td>${q.lectureName}</td>
						<td>${q.category}</td>
						<td>${q.questionnaireContent}</td>
						<td class="text-center">
							<input type="radio" name="select${q.evaluationNo}" value="1">
						</td>
						<td class="text-center">
							<input type="radio" name="select${q.evaluationNo}" value="2">
						</td>
						<td class="text-center">
							<input type="radio" name="select${q.evaluationNo}" value="3">
						</td>
						<td class="text-center">
							<input type="radio" name="select${q.evaluationNo}" value="4">
						</td>
						<td class="text-center">
							<input type="radio" name="select${q.evaluationNo}" value="5">
						</td>
					</tr>
				</c:forEach>
			</tbody>	
		</table>
		<button type="button" class="btn btn-dark btn-block" id="btnQuestionnaire">제출하기</button>
		</form>
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
    	
    	$('#btnQuestionnaire').click(function() {
    		// 설문 입력 값 배열로 묶기
    		var quelist = new Array(); // 설문 입력 값
 		   $("input[name=content]").each(function(index, item){
 			   quelist.push($(item).val());
 		   }); 
    		
 		// 답변 스코어 배열로 묶기
 		var checklist = new Array();// 답변 스코어 배열
   		   $("input[type=radio]:checked").each(function(index, item){
   			checklist.push($(item).val());
				
   		   });
   		 
   		 var list = quelist.concat(checklist);
   		 console.log("list"+list);
   		 
	   		$.ajax({
				url : '${pageContext.request.contextPath}/questionnaire/getQuestionnaireList'
				,type : 'POST'
				,data : {
					quelist:quelist
					,checklist:checklist
					}
				,success : function(data) {
					console.log("완료");
			     }
				,error : function() {
					alert("error");
				}
			});	
});
		
   	</script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
    <script src="../js/scripts.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
</html>