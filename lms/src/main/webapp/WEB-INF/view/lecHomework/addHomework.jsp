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
<title>addHomework</title>
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
	                        AddHomework
	                    </div>
						<form method="post" action="${pageContext.request.contextPath}/lecHomework/addHomework">
							<table class="table table">
								<tr>
									<th>아이디</th>
									<td>
										${sessionMemberId}
									</td>
								</tr>
								<tr>
									<th>강좌명</th>
									<td>
										<select id="lectureName" name="lectureName" class="form-control">
											<option selected="selected" >-----------------------------강좌선택--------------------------</option>
												<c:forEach var="l" items="${lectureNameList}">
													<option value="${l.lectureName}">${l.lectureName}</option>
												</c:forEach>
										</select>
									</td>
								</tr>
								<tr>
									<th>과제 제목</th>
									<td>
										<input class="form-control" type="text" name="homeworkMakeTitle">
									</td>
								</tr>
								<tr>
									<th>과제 내용</th>
									<td>
										<textarea name="homeworkMakeContent" id="summernote"></textarea>
										<span class="text-danger" id="contentError"></span>
									</td>
								</tr>
								<tr>
									<th>마감일</th>
									<td>
										<input type="date" name="homeworkDeadline" value="${deadline}">
									</td>
								</tr>
							</table>
						</form>		
					</div>
					<button class="btn btn-dark" type="submit">등록</button>
        		</div>
			<div id="footer"></div>
        </div>
	</div>
</body>
<script>
$(document).ready(function(){ // html페이지를 다 로드시키고 매개변수함수를 실행
	init();
	summernoteHide();
});
	// 기본설정으로 summernote라는 id사용하는 태그를 summernote로 설정
	function init(){
		$('#summernote').summernote({
			  tabsize: 2,
			  height: 400
		});
	}
	// height 높이 조절, hide는 사진이나 사용하고싶은 버튼이있다면 지우면 됨.
	function summernoteHide(){
		$(".note-editor button[aria-label='Picture']").hide();
		$(".note-editor button[aria-label='Video']").hide();
		$(".note-editor .note-view").hide();
	}
 </script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
    <script src="../js/scripts.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
    <script src="assets/demo/chart-area-demo.js"></script>
    <script src="assets/demo/chart-bar-demo.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
    <script src="js/datatables-simple-demo.js"></script>
</html>