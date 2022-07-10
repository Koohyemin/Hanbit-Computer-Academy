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
<title>modifyHomework</title>
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
	               Homework Modify
	            </div>
            </div>
			<a href="${pageContext.request.contextPath}/lecHomework/getLecHomeworkList" class="btn btn-dark" style="float:right">이전으로</a>
			<br><br>
			<form method="post" id="homeworkForm" action="${pageContext.request.contextPath}/lecHomework/modifyHomework">
				<table class="table">
					<tr>
						<th class="text-center">과제 번호</th>
						<td>
							<input name="homeworkMakeNo" value="${homeworkMake.homeworkMakeNo}" readonly="readonly" class="form-control">
						</td>
					</tr>
					<tr>
						<th class="text-center">등록자</th>
						<td> 
							<input name="managerId" type="text" value="${sessionMemberId}" readonly="readonly" class="form-control">
						</td>
					</tr>
					<tr>
						<th class="text-center">과제 제목</th>
						<td>
							<input name="homeworkMakeTitle" type="text" id="title" value="${homeworkMake.homeworkMakeTitle}" class="form-control">
							<span class="text-danger" id="titleError"></span>
						</td>
					</tr>
					<tr> 
						<th class="text-center" style="vertical-align: middle">내용</th>
						<td>
							<textarea name="homeworkMakeContent" id="summernote">${homeworkMake.homeworkMakeContent}</textarea>
							<span class="text-danger" id="contentError"></span>
						</td>
					</tr>
					<tr>
						<th class="text-center">마감 날짜</th>
						<td>
							<input type="date" name="homeworkDeadline" value="${homeworkMake.homeworkDeadline}">
						</td>
					</tr>
					<tr>
						<th class="text-center">작성 날짜</th>
						<td>
							<input type="text"  value="${homeworkMake.createDate}" readonly="readonly" class="form-control">
						</td>
					</tr>
				</table>
				<button type="button" id="btn" class="btn btn-dark" style="float:right">수정</button>
			</form>
		</div>
	<div id="footer"></div>
   	</div>
</div>
</body>
	<script>
	// 파일 삭제
	$(".deleteFile").click(function () {
		var fileNo = $(this).data('value');
		console.log(fileNo);
		$('#'+fileNo).css("display", "none");
		$.ajax({
			url: "/lms/lecHomework/removeFile?homeworkFileNo="+fileNo,
			method: "get",
			dataType: "text",
            success: function (data) {
                console.log(data);
            }
 		});
    });
	
	// html 태그 형성 이후 실행
	$('#nav').load('${pageContext.request.contextPath}/include/nav.jsp');
    	$('#navbar').load('${pageContext.request.contextPath}/include/navBar.jsp');
    	$('#footer').load('${pageContext.request.contextPath}/include/footer.jsp');
    
	$( document ).ready(function(){
		// id가 btn인 버튼을 클릭 했을 시 발생
	   	$('#btn').click(function(){
	   			// 제목
	   		   if($('#title').val() == '') {
	   		      $('#titleError').text('제목을 입력해주세요');
	   		   } else {
	   		      $('#titleError').text('');
	   		   }
	   		   // 내용
	   		   // 에디터는 태그를 쓰기때문에 정규식을 사용 후 공백 변환 후 유효성 검사
	   		   if( $('#summernote').summernote('code').replace(/<\/?[^>]+(>|$)/g, '') == '') {
	   			      $('#contentError').text('내용을 입력해주세요');
	   			   } else {
	   			      $('#contentError').text(''); 
	   			   }
	   		   // 전체 내용이 들어와 있다면 전송
	   		   if($('#title').val() != '' && $('#summernote').summernote('code').replace(/<\/?[^>]+(>|$)/g, '') != '') {
	   		      $('#homeworkForm').submit();
	   		   }
	   		});
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
    <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
</html>s