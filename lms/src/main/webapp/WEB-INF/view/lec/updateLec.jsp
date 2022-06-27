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
<title>addLec</title>
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
	                ADD Lecture
	            </div>
            </div>
			<a href="${pageContext.request.contextPath}/lec/lecList" class="btn btn-dark" style="float:right">이전으로</a>
			<br><br>
			<form method="post" id="addLecForm" action="${pageContext.request.contextPath}/lec/updateLec">
				<table class="table">
					<tr>
						<th class="text-center">등록자</th>
						<td> <!-- pull받은 이후 value ${sessionMemberId}로 수정 -->
							<input name="managerId" type="text" value="${sessionMemberId}" readonly="readonly" class="form-control">
						</td>
					</tr>
					<tr>
						<th class="text-center">과목</th>
						<td>
							<select name="subjectName" id="subject" class="form-control">
								<option value="">과목을 선택해주세요.</option>
								<c:forEach var="s" items="${subjectList}">
									<option value="${s.subjectName}">${s.subjectName}</option>								
								</c:forEach>
							</select>
							<span class="text-danger" id="subjectError"></span>
						</td>
					</tr>
					<tr>
						<th class="text-center">강의실</th>
						<td>
							<select name="lectureRoomName" id="lecturerRoom" class="form-control">
								<option value="">강의실을 선택해주세요.</option>
								<c:forEach var="l" items="${lectureRoomList}">
									<option value="${l.lectureRoomName}">${l.lectureRoomName}</option>								
								</c:forEach>
							</select>
							<span class="text-danger" id="lectureRoomError"></span>
						</td>
					</tr>
					<tr>
						<th class="text-center">강의 계획서</th>
						<td>
							<select name="lecPlanNo" id="lecPlan" class="form-control">
								<option value="">강의계획서를 선택해주세요.</option>
								<c:forEach var="l" items="${lecPlanList}">
									<option value="${l.lecPlanNo}">${l.lecPlanName}</option>								
								</c:forEach>
							</select>
							<span class="text-danger" id="lecPlanError"></span>
						</td>
					</tr>
					<tr>
						<th class="text-center">난이도</th>
						<td>
							<select name="difficulty" id="difficulty" class="form-control">
								<option value="">난이도를 선택해주세요.</option>
								<option value="상">상</option>
								<option value="중">중</option>		
								<option value="하">하</option>										
							</select>
							<span class="text-danger" id="difficultyError"></span>
						</td>
					</tr>
					<tr>
						<th class="text-center">강의명</th>
						<td>
							<input name="lectureName" id="lectureName" value="${lectureName}" type="text" class="form-control" placeholder="강의명을 입력해주세요">
							<span class="text-danger" id="lectureNameError"></span>
						</td>
					</tr>
					<tr>
						<th class="text-center">수강인원</th>
						<td>
							<input name="registrationNumber" id="registrationNumber" value="${registrationNumber}" type="number" min="1" class="form-control" placeholder="수강인원을 지정해주세요">
							<span class="text-danger" id="registrationNumberError"></span>
						</td>
					</tr>
					<tr>
						<th class="text-center">수료 점수</th>
						<td>
							<input name="registrationPassScore" id="registrationPassScore" value="${registrationPassScore}" type="number" min="1" max="100" class="form-control" placeholder="최소 수료점수를 지정해주세요">
							<span class="text-danger" id="registrationPassScoreError"></span>
						</td>
					</tr>
					<tr>
						<th class="text-center">수강료</th>
						<td>
							<input name="lecCost" id="lecCost" value="${lecCost}" type="number" min="1" class="form-control" placeholder="수강료를 입력해주세요">
							<span class="text-danger" id="lecCostError"></span>
						</td>
					</tr>
					<tr>
						<th class="text-center">문의 연락처</th>
						<td>
							<input name="lecPhone" id="lecPhone" value="${lecPhone}" type="text" class="form-control" placeholder="문의 담당자 연락처를 입력해주세요">
							<span class="text-danger" id="lecPhoneError"></span>
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
	   			// 카테고리
	   		   if($('#subject').val() == '') { // 과목
	   		      $('#subjectError').text('과목을 선택해주세요');
	   		   } else {
	   		      $('#subjectError').text('');
	   		   }
	   		   
	   		   if($('#lectureRoom').val() == '') { // 강의실
		   		      $('#lectureRoomError').text('강의실을 선택해주세요');
		   		} else {
		   		      $('#lectureRoomError').text('');
		   		}
	   		   
	   		   if($('#lecPlan').val() == '') { // 강의계획서
		   		      $('#lecPlanError').text('강의계획서를 선택해주세요');
		   		} else {
		   		      $('#lecPlanError').text('');
		   		}
	   			
	   		   if($('#difficulty').val() == '') { // 난이도
		   		      $('#difficultyError').text('난이도를 선택해주세요');
		   		} else {
		   		      $('#difficultyError').text('');
		   		}
	   		   
	   		   // 작성 내용
				if($('#lectureName').val() == '') { // 강의명
		   		      $('#lectureNameError').text('강의명을 입력해주세요');
		   		} else {
		   		      $('#lectureNameError').text('');
		   		}
	   		   
				if($('#registrationNumber').val() == '') { // 수강인원
		   		      $('#registrationNumberError').text('수강인원을 입력해주세요');
		   		} else {
		   		      $('#registrationNumberError').text('');
		   		}
				
				if($('#registrationPassScore').val() == '') { // 수료점수
		   		      $('#registrationPassScoreError').text('수료점수를 입력해주세요');
		   		} else {
		   		      $('#registrationPassScoreError').text('');
		   		}
				
				if($('#lecCost').val() == '') { // 수강료
		   		      $('#lecCostError').text('수강료를 입력해주세요');
		   		} else {
		   		      $('#lecCostError').text('');
		   		}
				
				if($('#lecPhone').val() == '') { // 문의 연락처
		   		      $('#lecPhoneError').text('문의 연락처를 입력해주세요');
		   		} else {
		   		      $('#lecPhoneError').text('');
		   		}
	   		   
	   		   
	   		   
	   		   // 전체 내용이 들어와 있다면 전송
	   		   if($('#category').val() != '' && $('#title').val() != '' && $('#summernote').summernote('code').replace(/<\/?[^>]+(>|$)/g, '') != '') {
	   		      $('#addNoticeForm').submit();
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