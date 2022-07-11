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
	                Homework Submission
					<a href="${pageContext.request.contextPath}/lecHomework/getLecHomeworkList" class="btn btn-dark btn-sm" style="float:right">이전으로</a>
	            </div>
            </div>
			<br><br>
			<form id="addForm" method="post" action="${pageContext.request.contextPath}/lecHomework/addHomework">
				<table class="table">
					<tr>
						<th class="text-center">등록자</th>
						<td>
							${sessionMemberId}
						</td>
					</tr>
					<tr>
						<th>강좌명</th>
						<td>
							<select id="lectureName" name="lectureName" class="form-control">
								<option selected="selected" value="">-----------------------------강좌선택--------------------------</option>
									<c:forEach var="l" items="${lectureNameList}">
										<option value="${l.lectureName}">${l.lectureName}</option>
									</c:forEach>
							</select>
							<span class="text-danger" id="lectureNameError"></span>
						</td>
					</tr>
					<tr>
						<th class="text-center">과제 제목</th>
						<td>
							<input class="form-control" id="title" type="text" name="homeworkMakeTitle" placeholder="재목을 입력하세요">
							<span class="text-danger" id="titleError"></span>
						</td>
					</tr>
					<tr>
						<th class="text-center" style="vertical-align: middle">내용</th>
						<td>
							<textarea name="homeworkMakeContent" id="summernote"></textarea>
							<span class="text-danger" id="contentError"></span>
						</td>
					</tr>
					<tr>
						<th>마감일</th>
						<td>
							<input id="beginClass" type="date" name="homeworkDeadline" value="${deadline}">
							<span class="text-danger" id="beginClassError"></span>
						</td>
					</tr>
				</table>
				<button type="button" id="btn" class="btn btn-dark" style="float:right">등록</button>
			</form>
		</div>
	<div id="footer"></div>
   	</div>
</div>
</body>
	<script>
	// html 태그 형성 이후 실행
	$('#nav').load('${pageContext.request.contextPath}/include/nav.jsp');
    	$('#navbar').load('${pageContext.request.contextPath}/include/navBar.jsp');
    	$('#footer').load('${pageContext.request.contextPath}/include/footer.jsp');
    
	$( document ).ready(function(){
		// id가 btn인 버튼을 클릭 했을 시 발생
	   	$('#btn').click(function(){
			// 카테고리(강좌)
			if($('#lectureName').val() == '') {
				$('#lectureNameError').text('강좌를 선택해주세요');
			} else {
				$('#lectureNameError').text('');
			}
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
   		   
   			// 유효한 날짜인지 확인 정규식
		    var dateRegex = /^(?=\d)(?:(?:31(?!.(?:0?[2469]|11))|(?:30|29)(?!.0?2)|29(?=.0?2.(?:(?:(?:1[6-9]|[2-9]\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00)))(?:\x20|$))|(?:2[0-8]|1\d|0?[1-9]))([-.\/])(?:1[012]|0?[1-9])\1(?:1[6-9]|[2-9]\d)?\d\d(?:(?=\x20\d)\x20|$))?(((0?[1-9]|1[012])(:[0-5]\d){0,2}(\x20[AP]M))|([01]\d|2[0-3])(:[0-5]\d){1,2})?$/;
		    var result = dateRegex.test(d+'-'+m+'-'+y);
		    
			var date = $('#beginClass').val().split("-"); // ''-'를 기준으로 자름
		   	var y = parseInt(date[0], 10), // 연도
		        m = parseInt(date[1], 10), // 월
		        d = parseInt(date[2], 10); // 일
		    
		        dateRegex = /^(?=\d)(?:(?:31(?!.(?:0?[2469]|11))|(?:30|29)(?!.0?2)|29(?=.0?2.(?:(?:(?:1[6-9]|[2-9]\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00)))(?:\x20|$))|(?:2[0-8]|1\d|0?[1-9]))([-.\/])(?:1[012]|0?[1-9])\1(?:1[6-9]|[2-9]\d)?\d\d(?:(?=\x20\d)\x20|$))?(((0?[1-9]|1[012])(:[0-5]\d){0,2}(\x20[AP]M))|([01]\d|2[0-3])(:[0-5]\d){1,2})?$/;
			    result = dateRegex.test(d+'-'+m+'-'+y);
		    
		    // 개강일자가 오늘 이후날짜여야되고, 종강일자는 개강일자보다 크거나 같다
		    var beginDateSplit = $('#beginClass').val().split("-");
		    var beginDate = beginDateSplit[0] + beginDateSplit[1] + beginDateSplit[2];
		    
		    var date = new Date();
		    
		    var year = date.getFullYear();
		    
		    var month = date.getMonth();
		    month += 1;
		    if (month <= 9){
		        month = "0" + month;
		    }

		    var day = date.getDate();
		    if (day <= 9){
		        day = "0" + day;
		    }
		    
		    var today = year + month + day;
		    
		   	if($('#beginClass').val() == '') { // 개강일
	   		 	$('#beginClassError').text('개강일을 선택해주세요');
	   		} else if (!result && $('#beginClassError').text()) {
		    	$('#beginClassError').text('개강일의 날짜형식이 유효하지 않습니다.');
		    } else if (today > beginDate) {
			    $('#beginClassError').text('개강일은 오늘날짜 이후로 설정하여야 합니다.');
			} else {
			    $('#beginClassError').text('');
			}
   		    
   		   // 전체 내용이 들어와 있다면 전송
   		   if($('#lectureName').val() != '' && $('#title').val() != '' && $('#summernote').summernote('code').replace(/<\/?[^>]+(>|$)/g, '') != '' && $('#beginClassError').text() == '') {
   		      $('#addForm').submit();
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
    <script src="assets/demo/chart-area-demo.js"></script>
    <script src="assets/demo/chart-bar-demo.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
    <script src="js/datatables-simple-demo.js"></script>
</html>