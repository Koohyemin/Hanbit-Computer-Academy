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
<title>imformationMember</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
<link href="../css/styles.css" rel="stylesheet" />
<script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
</head>
<body class="sb-nav-fixed">
<script>
   window.onload = function(){
       document.getElementById("newwin").onclick = function(){
           window.open("addr","","width=600px,height=400px,top=200px;");
       }

   };
</script>
<div id="nav"></div>
<div id="layoutSidenav">
	<div id="layoutSidenav_nav">
		<div id="navbar"></div>
	</div>
    <div id="layoutSidenav_content">
		<div class="container-fluid px-4">
            <!-- 컨텐츠 삽입 부분-->
            <br>
	        <form method="post" action="${pageContext.request.contextPath}/member/modifyMember">
	      	<div class="card mb-4">
	     		<div class="card-header">
		     		<i class="fas fa-chart-area me-1"></i>
					회원 수정
		        </div>
		        <div class="card-body">
		        
					<table class="table table-hover">
			    	<c:if test="${sessionMemberLv == 1}">
						<tr>
							<th class="text-center">학생 이름</th>
							<td>
								<input class="form-control" type="text" name="memberName" id="memberName" placeholder="아이디를 입력하세요" value="${student.studentName}">
							 </td>
						</tr>
						<tr>
							<th class="text-center">학생 아이디</th>
							<td>${student.studentId}</td>
						</tr>
						<tr>
							<th class="text-center">주소</th>
							<td>
								<input type="text" id="Keyword" name="addr" value="${student.studentAddr1}" readonly="readonly">
								<input id="newwin" type="button" name="" value="새창열기">
							</td>
						</tr>
						<tr>
							<th class="text-center">상세주소</th>
							<td>
								<input type="text" class="form-control"name="addr2" value="${student.studentAddr2}">
							</td>
						</tr>
						<tr>
							<th class="text-center">핸드폰</th>
							<td>
								<input type="text" class="form-control" name="phone" placeholder="연락처를 입력하세요" value="${student.studentPhone}">
							</td>
						</tr>
						<tr>
							<th class="text-center">생일</th>
							<td>${student.studentBirth}</td>
						</tr>
						<tr>
							<th class="text-center">성별</th>
							<td>${student.studentGender}</td>
						</tr>
						<tr>
							<th class="text-center">최종학력</th>
							<td>
								<select	name="finalEdu">
									<option value="고졸">고등학교 졸업</option>
									<option value="초대졸">전문대 졸업</option>
									<option value="학사">대학교 졸업</option>
									<option value="석사">대학원 졸업</option>
									<option value="박사">박사</option>
								</select>
							</td>
						</tr>
						<tr>
							<th class="text-center">이메일</th>
							<td>
								<input type="text" class="form-control" name="email" placeholder="연락처를 입력하세요" value="${student.studentEmail}">
							</td>
						</tr>
			    	</c:if>
			    	<c:if test="${sessionMemberLv == 2}">
						<tr>
							<th class="text-center">강사 이름</th>
							<td>
								<input type="text" class="form-control" name="memberName" id="memberName" placeholder="아이디를 입력하세요" value="${teacher.teacherName}">
							 </td>
						</tr>
						<tr>
							<th class="text-center">강사 아이디</th>
							<td>${teacher.teacherId}</td>
						</tr>
						<tr>
							<th class="text-center">주소</th>
							<td>
								<input type="text" id="Keyword" name="addr" value="${teacher.teacherAddr1}" readonly="readonly">
								<input id="newwin" type="button" name="" value="새창열기">
							</td>
						</tr>
						<tr>
							<th class="text-center">상세주소</th>
							<td>
								<input tpye="text" class="form-control" name="addr2" value="${teacher.teacherAddr2}">
							</td>
						</tr>
						<tr>
							<th class="text-center">핸드폰</th>
							<td>
								<input type="text" class="form-control" name="phone" placeholder="연락처를 입력하세요" value="${teacher.teacherPhone}">
							</td>
						</tr>
						<tr>
							<th class="text-center">생일</th>
							<td>${teacher.teacherBirth}</td>
						</tr>
						<tr>
							<th class="text-center">성별</th>
							<td>${teacher.teacherGender}</td>
						</tr>
						<tr>
							<th class="text-center">최종학력</th>
							<td>
								<select	name="finalEdu">
									<option value="고졸">고등학교 졸업</option>
									<option value="초대졸">전문대 졸업</option>
									<option value="학사">대학교 졸업</option>
									<option value="석사">대학원 졸업</option>
									<option value="박사">박사</option>
								</select>
							</td>
						</tr>
						<tr>
							<th class="text-center">이메일</th>
							<td>
								<input type="text" class="form-control" name="email" placeholder="연락처를 입력하세요" value="${teacher.teacherEmail}">
							</td>
						</tr>
		    		</c:if>
		    		<c:if test="${sessionMemberLv == 3}">
						<tr>
							<th class="text-center">강사 이름</th>
							<td>
								<input type="text" class="form-control" name="memberName" id="memberName" placeholder="아이디를 입력하세요" value="${manager.managerName}">
							 </td>
						</tr>
						<tr>
							<th class="text-center">강사 아이디</th>
							<td>${manager.managerId}</td>
						</tr>
						<tr>
							<th class="text-center">주소</th>
							<td>
								<input type="text"  id="Keyword" name="addr" value="${manager.managerAddr1}" readonly="readonly">
								<input id="newwin" type="button" name="" value="새창열기">
							</td>
						</tr>
						<tr>
							<th class="text-center">상세주소</th>
							<td>
								<input type="text" class="form-control" name="addr2" value="${manager.managerAddr2}">
							</td>
						</tr>
						<tr>
							<th class="text-center">핸드폰</th>
							<td>
								<input type="text" class="form-control" name="phone" placeholder="연락처를 입력하세요" value="${manager.managerPhone}">
							</td>
						</tr>
						<tr>
							<th class="text-center">생일</th>
							<td>${manager.managerBirth}</td>
						</tr>
						<tr>
							<th class="text-center">성별</th>
							<td>${manager.managerGender}</td>
						</tr>
						<tr>
							<th class="text-center">이메일</th>
							<td>
								<input type="text" class="form-control" name="email" placeholder="연락처를 입력하세요" value="${manager.managerEmail}">
							</td>
						</tr>
		    		</c:if>
				</table>
				</div>
			</div>
			<button class="btn btn-outline-primary btn-block" type="submit">수정</button>
			</form>
		</div>
	<div id="footer"></div>
   	</div>
</div>
</body>
	<script>
    	$('#nav').load('${pageContext.request.contextPath}/include/nav.jsp');
    	$('#navbar').load('${pageContext.request.contextPath}/include/navBar.jsp');
    	$('#footer').load('${pageContext.request.contextPath}/include/footer.jsp');
		$(".delBtn").click(function(){
			if (confirm('해당 자격증을 삭제 하시겠습니까?')) {
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