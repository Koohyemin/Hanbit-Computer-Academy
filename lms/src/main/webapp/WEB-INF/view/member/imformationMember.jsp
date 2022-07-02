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
					<span style="font-size:20px;">My information</span>
					<div class="btn btn-group float-end">
						<a class="btn btn-dark btn-sm" role="button" href="${pageContext.request.contextPath}/member/modifyMember">수정하기</a>
						<a class="btn btn-secondary btn-sm" role="button" href="${pageContext.request.contextPath}/#">회원탈퇴</a>
					</div>
		        </div>
		        <div class="card-body">
					<div class="row"> 
						<div class="col-lg-1"></div>
						
						<!-- 사진 -->
				        <div class="col-lg-3">
				        <br>
				           	<img src="${pageContext.request.contextPath}/upload/${photoFile.photoName}" class="img-fluid" alt="" width="150" height="200">  <!-- 사진추가 -->
				        	<form id="photoSubmit" method="post" action="${pageContext.request.contextPath}/updatePhoto"  enctype="multipart/form-data">
				        		<input type="file" id="photo"name="photoFile" multiple="multiple">
				        		<span id="helpPhoto"></span>
				        		<button id="photoBtn"type="button">사진수정</button>
				        	</form>
				        </div>
				        <!-- 사진 끝 -->
				        	
				        <!-- 학생이라면 개인정보 보여주기-->
						<c:if test="${sessionMemberLv == 1}">
				        	<div class="col-lg-3">
					        	<br>
				        		<div>▶ 이름 : ${student.studentName}</div>
	        					<br>
		        				<div>▶ 아이디 : ${student.studentId}</div>
	        					<br>
	        					<div>▶ 성별 : ${student.studentGender}</div>
			        			<br>
				        		<div>▶ 출생년도 : ${student.studentBirth}</div>
	        					<br>
		        				<div>▶ 연락처 : ${student.studentPhone}</div>
			        		</div>
		        			<div class="col-lg-4">
					        	<br>
				        		<div>▶ 주소 : ${student.studentAddr1}</div>
	        					<br>
		        				<div>▶ 상세주소 : ${student.studentAddr2}</div>
	        					<br>
	        					<div>▶ 최종학력 : ${student.finalEducation}</div>
			        			<br>
				        		<div>▶ 이메일 : ${student.studentEmail}</div>
		        			</div>
						</c:if>
		        		<!-- 학생 개인정보 끝 -->
		        		
		        		<!-- 강사라면 개인정보 보여주기-->
						<c:if test="${sessionMemberLv == 2}">
				        	<div class="col-lg-3">
					        	<br>
				        		<div>▶ 이름 : ${teacher.teacherName}</div>
		       					<br>
		        				<div>▶ 아이디 : ${teacher.teacherId}</div>
		       					<br>
		       					<div>▶ 성별 : ${teacher.teacherGender}</div>
			        			<br>
				        		<div>▶ 출생년도 : ${teacher.teacherBirth}</div>
		       					<br>
		        				<div>▶ 연락처 : ${teacher.teacherPhone}</div>
		        			</div>
		        			<div class="col-lg-4">
					        	<br>
				        		<div>▶ 주소 : ${teacher.teacherAddr1}</div>
		       					<br>
		        				<div>▶ 상세주소 : ${teacher.teacherAddr2}</div>
		       					<br>
		       					<div>▶ 최종학력 : ${teacher.finalEducation}</div>
			        			<br>
				        		<div>▶ 이메일 : ${teacher.teacherEmail}</div>
		       				</div>
						</c:if>	
		        		<!-- 강사 개인정보 끝 -->
		        		
				        <!-- 운영진이라면 개인정보 보여주기-->
						<c:if test="${sessionMemberLv == 3}">
				        	<div class="col-lg-3">
					        	<br>
				        		<div>▶ 이름 : ${manager.managerName}</div>
		       					<br>
		        				<div>▶ 아이디 : ${manager.managerId}</div>
		       					<br>
		       					<div>▶ 성별 : ${manager.managerGender}</div>
			        			<br>
				        		<div>▶ 출생년도 : ${manager.managerBirth}</div>
			        		</div>
			        		<div class="col-lg-4">
			        			<br>
		        				<div>▶ 연락처 : ${manager.managerPhone}</div>
					        	<br>
				        		<div>▶ 주소 : ${manager.managerAddr1}</div>
		       					<br>
		        				<div>▶ 상세주소 : ${manager.managerAddr2}</div>
		       					<br>
				        		<div>▶ 이메일 : ${manager.managerEmail}</div>
		        			</div>
						</c:if>
		        		<!-- 운영진 개인정보 끝 -->	
		        														
		       		</div>
		       	</div>
			</div>
		    <br>
	      	<!-- 운영진 제외 보여질 뷰 -->
	      	<c:if test="${sessionMemberLv != 3}">
	      	<div class="row">
	      		<!-- 수강목록 -->
     			<div class="col-lg-6">
	      			<div class="card mb-4">
	                   <div class="card-header">
	                       <i class="fas fa-chart-area me-1"></i>
	                       <c:choose>
	                       	<c:when test="${sessionMemberLv == 1}">
								수강 목록
	                       	</c:when>
	                       	<c:when test="${sessionMemberLv == 2}">
	                       		강좌 목록
	                       	</c:when>
	                       </c:choose>
	                   </div>
	       			<table class="table table-hover">
	       				<thead>
	       					<tr>
	       						<th class="text-center">강좌</th>
	       						<th class="text-center">과목</th>
	       						<th class="text-center">상태</th>
	       						<th class="text-center">개설날짜</th>
	       					</tr>
	       				</thead>
	       				<tbody>
	       				<c:choose>
	       					<c:when test="${sessionMemberLv == 2}">
	       						<c:choose>
	        						<c:when test="${fn:length(lecList) > 0}">
				        				<c:forEach var="r" items="${registrationList}">
				        					<tr>
				        						<td class="text-center"><a href="${pageContext.request.contextPath}/lec/lecOne?lectureName=${l.lectureName}">${r.lectureName}</a></td><!-- 수강 상세보기로 이동 -->    						
				        						<td class="text-center">${r.subjectName}</td>
				        						<td class="text-center text-danger">수업 진행중</td>
				        						<td class="text-center">${r.createDate}</td>
				        					</tr>		        				
				        				</c:forEach>
				        				<c:forEach var="l" items="${lecList}">
				        					<tr>
				        						<td class="text-center"><a href="${pageContext.request.contextPath}/lec/lecOne?lectureName=${l.lectureName}">${l.lectureName}</a></td><!-- 수강 상세보기로 이동 -->    						
				        						<td class="text-center">${l.subjectName}</td>
				        						<td class="text-center">수강 신청기간</td>
				        						<td class="text-center">${l.createDate}</td>
				        					</tr>
				        				</c:forEach>
	        						</c:when>
				        			<c:otherwise>
				        				<td class="text-center text-danger" colspan="4">계설된 강좌가 없습니다.</td>
				        			</c:otherwise>
	       						</c:choose>
	       					</c:when>
	       					<c:when test="${sessionMemberLv == 1}">
	        					<c:choose>
	        						<c:when test="${fn:length(lecList) > 0}">
				        				<c:forEach var="l" items="${lecList}">
				        					<tr>
				        						<td class="text-center"><a href="${pageContext.request.contextPath}/lec/lecOne?lectureName=${l.lectureName}">${l.lectureName}</a></td><!-- 수강 상세보기로 이동 -->    						
				        						<td class="text-center">${l.subjectName}</td>
				        						<td class="text-center">수강 중</td>
				        						<td class="text-center">${l.createDate}</td>
				        					</tr>
				        				</c:forEach>		        					
	        						</c:when>
	        						<c:otherwise>
	        							<td class="text-center text-danger" colspan="4">수강중인 강의가 없습니다</td>
	        						</c:otherwise>
	        					</c:choose>
	       					</c:when>
	       				</c:choose>
	       				</tbody>
	       			</table>
	       		</div>
     		</div>
	     	<!-- 수강목록끝 -->
	     			
	     	<!-- 자격증 -->
      		<div class="col-lg-6">
	               <div class="card mb-4">
	                   <div class="card-header" >
	                       <i class="fas fa-chart-area me-1"></i>
							보유 자격증
							<div class="float-end">
							<a class="btn btn-dark btn-sm"  role="button" href="${pageContext.request.contextPath}/certificate/addCertification">등록</a>
							</div>
	                   </div>
	        			<table class="table table-hover">
	       				<thead>
	       					<tr>
	       						<th class="text-center">자격증명</th>
	       						<th class="text-center">주관처</th>
	       						<th class="text-center">취득일</th>
	       					</tr>
	       				</thead>
	       				<tbody>
	       				<!-- 자격증이 존재한다면 -->
	       				<c:if test="${fn:length(certificationList) > 0}">
	        				<c:forEach var="c" items="${certificationList}">
	        					<tr>
	        						<td class="text-center">${c.certificationName}</td>
	        						<td class="text-center">${c.certificationIssued}</td>
	        						<td class="text-center">${c.getDate}</td>
	        						<td>
	        						<div class="btn btn-group float-end">
		        						<a class="btn btn-outline-primary btn-sm"  role="button" href="${pageContext.request.contextPath}/certificate/modifyCertification?certificationNo=${c.certificationNo}">수정</a>
	        						<!-- 삭제버튼 -->
	        							<form method="post" action="${pageContext.request.contextPath}/certificate/deleteCertification" id="del">
			        						<input type="hidden" value="${c.certificationNo}" name="certificationNo">
	        								<input class="btn btn-outline-danger  btn-sm delBtn" value="삭제" type="submit"/>
	        							</form>
	        							</div>
	        						</td>
	        					</tr>
	        				</c:forEach>
	       				</c:if>
	       				<c:if test="${fn:length(certificationList) == 0}">
	       					<td class="text-center text-danger" colspan="3">습득한 자격증이 없습니다.</td>
	       				</c:if>
	       				</tbody>
	       			</table>
				</div>
       		</div>
	      	<!-- 자격증 끝 -->
	     </div>
	     </c:if>
		</div>
	<div id="footer"></div>
   	</div>
</div>
</body>
	<script>
	$('#photoBtn').click(function(){
		if($('#photo').val() == ''){
			$('#helpPhoto').text('사진을 선택해주세요');
		} else {
			$('#helpPhoto').text('');
		}
		if($('#photo').val() != '') {
			$("#photoSubmit").submit();
		}
	});
	
	
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