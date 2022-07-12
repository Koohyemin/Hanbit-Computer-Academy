<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<meta property="og:title" content="한빛컴퓨터아카데미LMS">
<meta property="og:url" content="lms/login">
<meta property="og:image"
	content="${pageContext.request.contextPath}/img/previewer.png">
<title>informationMember</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link
	href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css"
	rel="stylesheet" />
<link href="../css/styles.css" rel="stylesheet" />
<script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js"
	crossorigin="anonymous"></script>
</head>
<body class="sb-nav-fixed">
	<div id="nav"></div>
	<div id="layoutSidenav">
		<div id="layoutSidenav_nav">
			<div id="navbar"></div>
		</div>
		<div id="layoutSidenav_content">
			<div class="container-fluid px-4">
				<br>
				<!-- 컨텐츠 삽입 부분-->
				<div class="row">
					<div class="col-lg-4">
						<div class="card mb-4">

							<div class="card-header">
								<i class="fas fa-chart-area me-1"></i> <span>My Photo</span>

							</div>
							<div class="card-body">
								<div class="text-center">
									<!-- 사진추가 -->
									<img
										src="${pageContext.request.contextPath}/upload/${photoFile.photoName}"
										class="img-fluid" alt="" width="270">
									<hr>
									<form id="photoSubmit" method="post"
										action="${pageContext.request.contextPath}/updatePhoto"
										enctype="multipart/form-data">
										<div class="form-group btn-group">
											<input type="file" id="photo" name="photoFile"
												multiple="multiple" class="form-control"
												style="margin: 0 auto;">
											<button id="photoBtn" type="button"
												class="btn btn-sm btn-dark float-end">변경</button>
											<a
												href="${pageContext.request.contextPath}/changeDefaultPhoto"
												class="btn btn-sm btn-secondary float-end">초기화</a>
										</div>
									</form>
									<span id="helpPhoto"></span>
								</div>
							</div>
						</div>
					</div>
					<!-- 사진 끝 -->
					<div class="col-lg-8">
						<div class="card mb-4">
							<div class="card-header">
								<i class="fas fa-chart-area me-1"></i> <span>My
									information</span>
							</div>
							<div class="card-body">
								<div class="btn btn-group float-end">
									<a class="btn btn-dark btn-sm" role="button"
										href="${pageContext.request.contextPath}/member/modifyMember">수정하기</a>
									<a class="btn btn-secondary btn-sm" role="button"
										id="removeMemberBtn"
										href="${pageContext.request.contextPath}/member/removceMember?memberId=${memberId}">회원
										탈퇴</a>
								</div>
								<!-- 학생이라면 개인정보 보여주기-->
								<c:if test="${sessionMemberLv == 1}">
									<table>
										<tr>
											<td width="450px;" height="80px;" style="padding-left: 25px;">
												<!-- 이름 --> <span class="text-muted">❣ NAME</span> <br>
												- ${student.studentName} <c:if
													test="${student.studentGender eq '남자'}">
													<svg xmlns="http://www.w3.org/2000/svg" width="16"
														height="16" fill="currentColor"
														class="bi bi-gender-female" viewBox="0 0 16 16">
										  <path fill-rule="evenodd"
															d="M8 1a4 4 0 1 0 0 8 4 4 0 0 0 0-8zM3 5a5 5 0 1 1 5.5 4.975V12h2a.5.5 0 0 1 0 1h-2v2.5a.5.5 0 0 1-1 0V13h-2a.5.5 0 0 1 0-1h2V9.975A5 5 0 0 1 3 5z" />
										</svg>
												</c:if> <c:if test="${student.studentGender eq '여자'}">
													<svg xmlns="http://www.w3.org/2000/svg" width="16"
														height="16" fill="currentColor"
														class="bi bi-gender-female" viewBox="0 0 16 16">
										  <path fill-rule="evenodd"
															d="M8 1a4 4 0 1 0 0 8 4 4 0 0 0 0-8zM3 5a5 5 0 1 1 5.5 4.975V12h2a.5.5 0 0 1 0 1h-2v2.5a.5.5 0 0 1-1 0V13h-2a.5.5 0 0 1 0-1h2V9.975A5 5 0 0 1 3 5z" />
										</svg>
												</c:if>
											</td>
											<td width="450px;" height="80px;" style="padding-left: 25px;">
												<!-- 아이디 --> <span class="text-muted">⚙ ID</span> <br>
												- ${student.studentId}
											</td>
										</tr>
										<tr>
											<td width="450px;" height="80px;" style="padding-left: 25px;">
												<!-- 생년월일 --> <span class="text-muted">🍰 BRITH</span> <br>
												- ${student.studentBirth}
											</td>
											<td width="450px;" height="80px;" style="padding-left: 25px;">
												<!-- 연락처 --> <span class="text-muted">📞 PHONE</span> <br>
												- ${student.studentPhone}
											</td>
										</tr>
										<tr>
											<td width="450px;" height="80px;" style="padding-left: 25px;">
												<!-- 이메일 --> <span class="text-muted">✉ E-mail</span> <br>
												- ${student.studentEmail}
											</td>
											<td width="450px;" height="80px;" style="padding-left: 25px;">
												<!-- 학력 --> <span class="text-muted">🏫 LEVEL</span> <br>
												- ${student.finalEducation}
											</td>
										</tr>
										<tr>
											<td colspan="2" height="80px;" style="padding-left: 25px;">
												<!-- 주소 --> <span class="text-muted">🏡 ADDR</span> <br>
												- ${student.studentAddr1} ${student.studentAddr2}
											</td>
										</tr>
									</table>
									<br>
								</c:if>
								<!-- 학생 개인정보 끝 -->
								<!-- 강사라면 개인정보 보여주기-->
								<c:if test="${sessionMemberLv == 2}">
									<table>
										<tr>
											<td width="450px;" height="80px;" style="padding-left: 25px;">
												<!-- 이름 --> <span class="text-muted">❣ NAME</span> <br>
												- ${teacher.teacherName} <c:if
													test="${teacher.teacherGender eq '남자'}">
													<svg xmlns="http://www.w3.org/2000/svg" width="16"
														height="16" fill="currentColor"
														class="bi bi-gender-female" viewBox="0 0 16 16">
										  <path fill-rule="evenodd"
															d="M8 1a4 4 0 1 0 0 8 4 4 0 0 0 0-8zM3 5a5 5 0 1 1 5.5 4.975V12h2a.5.5 0 0 1 0 1h-2v2.5a.5.5 0 0 1-1 0V13h-2a.5.5 0 0 1 0-1h2V9.975A5 5 0 0 1 3 5z" />
										</svg>
												</c:if> <c:if test="${teacher.teacherGender eq '여자'}">
													<svg xmlns="http://www.w3.org/2000/svg" width="16"
														height="16" fill="currentColor"
														class="bi bi-gender-female" viewBox="0 0 16 16">
										  <path fill-rule="evenodd"
															d="M8 1a4 4 0 1 0 0 8 4 4 0 0 0 0-8zM3 5a5 5 0 1 1 5.5 4.975V12h2a.5.5 0 0 1 0 1h-2v2.5a.5.5 0 0 1-1 0V13h-2a.5.5 0 0 1 0-1h2V9.975A5 5 0 0 1 3 5z" />
										</svg>
												</c:if>
											</td>
											<td width="450px;" height="80px;" style="padding-left: 25px;">
												<!-- 아이디 --> <span class="text-muted">⚙ ID</span> <br>
												- ${teacher.teacherId}
											</td>
										</tr>
										<tr>
											<td width="450px;" height="80px;" style="padding-left: 25px;">
												<!-- 생년월일 --> <span class="text-muted">🍰 BRITH</span> <br>
												- ${teacher.teacherBirth}
											</td>
											<td width="450px;" height="80px;" style="padding-left: 25px;">
												<!-- 연락처 --> <span class="text-muted">📞 PHONE</span> <br>
												- ${teacher.teacherPhone}
											</td>
										</tr>
										<tr>
											<td width="450px;" height="80px;" style="padding-left: 25px;">
												<!-- 이메일 --> <span class="text-muted">✉ E-mail</span> <br>
												- ${teacher.teacherEmail}
											</td>
											<td width="450px;" height="80px;" style="padding-left: 25px;">
												<!-- 학력 --> <span class="text-muted">🏫 LEVEL</span> <br>
												- ${teacher.finalEducation}
											</td>
										</tr>
										<tr>
											<td colspan="2" height="80px;" style="padding-left: 25px;">
												<!-- 주소 --> <span class="text-muted">🏡 ADDR</span> <br>
												- ${teacher.teacherAddr1} ${teacher.teacherAddr2}
											</td>
										</tr>
									</table>
								</c:if>
								<!-- 강사 개인정보 끝 -->
								<!-- 운영자라면 개인정보 보여주기-->
								<c:if test="${sessionMemberLv == 3}">
									<table>
										<tr>
											<td width="450px;" height="80px;" style="padding-left: 25px;">
												<!-- 이름 --> <span class="text-muted">❣ NAME</span> <br>
												- ${manager.managerName} <c:if
													test="${manager.managerGender eq '남자'}">
													<svg xmlns="http://www.w3.org/2000/svg" width="16"
														height="16" fill="currentColor"
														class="bi bi-gender-female" viewBox="0 0 16 16">
										  <path fill-rule="evenodd"
															d="M8 1a4 4 0 1 0 0 8 4 4 0 0 0 0-8zM3 5a5 5 0 1 1 5.5 4.975V12h2a.5.5 0 0 1 0 1h-2v2.5a.5.5 0 0 1-1 0V13h-2a.5.5 0 0 1 0-1h2V9.975A5 5 0 0 1 3 5z" />
										</svg>
												</c:if> <c:if test="${manager.managerGender eq '여자'}">
													<svg xmlns="http://www.w3.org/2000/svg" width="16"
														height="16" fill="currentColor"
														class="bi bi-gender-female" viewBox="0 0 16 16">
										  <path fill-rule="evenodd"
															d="M8 1a4 4 0 1 0 0 8 4 4 0 0 0 0-8zM3 5a5 5 0 1 1 5.5 4.975V12h2a.5.5 0 0 1 0 1h-2v2.5a.5.5 0 0 1-1 0V13h-2a.5.5 0 0 1 0-1h2V9.975A5 5 0 0 1 3 5z" />
										</svg>
												</c:if>
											</td>
											<td width="450px;" height="80px;" style="padding-left: 25px;">
												<!-- 아이디 --> <span class="text-muted">⚙ ID</span> <br>
												- ${manager.managerId}
											</td>
										</tr>
										<tr>
											<td width="450px;" height="80px;" style="padding-left: 25px;">
												<!-- 생년월일 --> <span class="text-muted">🍰 BRITH</span> <br>
												- ${manager.managerBirth}
											</td>
											<td width="450px;" height="80px;" style="padding-left: 25px;">
												<!-- 연락처 --> <span class="text-muted">📞 PHONE</span> <br>
												- ${manager.managerPhone}
											</td>
										</tr>
										<tr>
											<td width="450px;" height="80px;" style="padding-left: 25px;">
												<!-- 이메일 --> <span class="text-muted">✉ E-mail</span> <br>
												- ${manager.managerEmail}
											</td>
											<td width="450px;" height="80px;" style="padding-left: 25px;">
												<!-- 학력 --> <span class="text-muted">🏡 ADDR</span> <br>
												- ${manager.managerAddr1} ${manager.managerAddr2}
											</td>
										</tr>
									</table>
								</c:if>
								<!-- 운영자 개인정보 끝 -->
							</div>
						</div>
					</div>
				</div>
				<!-- 운영진 제외 보여질 뷰 -->
				<c:if test="${sessionMemberLv != 3}">
					<div class="row">
						<!-- 수강목록 -->
						<div class="col-lg-7">
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
								<div class="card-body">
									<table class="table table-hover">
										<thead>
											<tr>
												<th class="text-center">강좌</th>
												<th class="text-center">개강 날짜</th>
												<th class="text-center">종강 날짜</th>
												<th></th>
												<c:if test="${sessionMemberLv == 3}">
													<th class="text-center">승인 상태</th>
												</c:if>
											</tr>
										</thead>
										<tbody>
											<c:choose>
												<c:when test="${sessionMemberLv == 2}">
													<c:choose>
														<c:when test="${fn:length(lecTimeList) > 0}">
															<c:forEach var="l" items="${lecTimeList}">
																<tr>
																	<td class="text-center"><a
																		href="${pageContext.request.contextPath}/lec/lecOne?lectureName=${l.lectureName}">${l.lectureName}</a></td>
																	<!-- 수강 상세보기로 이동 -->
																	<td class="text-center">${l.beginClass}</td>
																	<td class="text-center">${l.endClass}</td>

																	<!-- 수강상태 분기 -->
																	<c:choose>
																		<c:when test="${l.checkLec == 0}">
																			<td class="text-center">수강 접수 중</td>
																		</c:when>
																		<c:when test="${l.checkLec == 1}">
																			<td class="text-center text-danger">수업 진행 중</td>
																		</c:when>
																		<c:when test="${l.checkLec == 2}">
																			<td class="text-center text-danger">종강</td>
																		</c:when>
																	</c:choose>
																	<td class="text-center">${l.lecState}</td>
																</tr>
															</c:forEach>
														</c:when>
														<c:otherwise>
															<td class="text-center text-danger" colspan="5">개설된
																강좌가 없습니다.</td>
														</c:otherwise>
													</c:choose>
												</c:when>
												<c:when test="${sessionMemberLv == 1}">
													<c:choose>
														<c:when test="${fn:length(lecList) > 0}">
															<c:forEach var="l" items="${lecList}">
																<tr>
																	<td class="text-center"><a
																		style="text-decoration: none; color: #000000;"
																		href="${pageContext.request.contextPath}/lec/lecOne?lectureName=${l.lectureName}">
																			<b>${l.lectureName}</b>
																	</a></td>
																	<!-- 수강 상세보기로 이동 -->
																	<td class="text-center">${l.subjectName}</td>
																	<td class="text-center">수강 중</td>
																	<td class="text-center">${l.createDate}</td>
																</tr>
															</c:forEach>
														</c:when>
														<c:otherwise>
															<td class="text-center text-danger" colspan="4">수강중인
																강의가 없습니다</td>
														</c:otherwise>
													</c:choose>
												</c:when>
											</c:choose>
										</tbody>
									</table>
								</div>
							</div>
						</div>
						<!-- 수강목록끝 -->

						<!-- 자격증 -->
						<div class="col-lg-5">
							<div class="card mb-4">
								<div class="card-header">
									<i class="fas fa-chart-area me-1"></i> 보유 자격증
									<div class="float-end">
										<a class="btn btn-dark btn-sm" role="button"
											href="${pageContext.request.contextPath}/certificate/addCertification">등록</a>
									</div>
								</div>
								<div class="card-body">
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
															<div class="btn-group float-end" style="margin: 0;">
																<a class="btn btn-dark btn-sm" role="button"
																	href="${pageContext.request.contextPath}/certificate/modifyCertification?certificationNo=${c.certificationNo}">수정</a>
																<!-- 삭제버튼 -->
																<form method="post"
																	action="${pageContext.request.contextPath}/certificate/deleteCertification"
																	id="del">
																	<input type="hidden" value="${c.certificationNo}"
																		name="certificationNo"> <input
																		class="btn btn-secondary btn-sm delBtn" value="삭제"
																		type="submit" />
																</form>
															</div>
														</td>
													</tr>
												</c:forEach>
											</c:if>
											<c:if test="${fn:length(certificationList) == 0}">
												<td class="text-center text-danger" colspan="3">습득한
													자격증이 없습니다.</td>
											</c:if>
										</tbody>
									</table>
								</div>
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
   $(".delBtn").click(function(){
      if (confirm('해당 자격증을 삭제 하시겠습니까?')) {
         $('#del').submit();
      } else {
         return false;
      }
   });
   
   var ckk = false;
   
   $("#removeMemberBtn").click(function(){
	   var inputString = prompt('비밀번호 입력하세요', '비밀번호입력');
	   $.ajax({
			type:"get"
			,url:'/lms/removeMemberCk' //인터넷망
			,data:{'x':inputString} //키워드 받는 데이터
			,async : false
			,async: false
			,success:function(result){
				
				//console.log(result);
				ckk=result;
				console.log(ckk);


			}
	   });
	   

	   
	   if(ckk== true){
		   
		      if (confirm('탈퇴 하시겠습니까?')) {
		    	  
		      } else {
		    	  return false;
		      }
		   }
	   else{
		   return false;
	   }
					
   });   
  
   $('#nav').load('${pageContext.request.contextPath}/include/nav.jsp');
   $('#navbar').load('${pageContext.request.contextPath}/include/navBar.jsp');
   $('#footer').load('${pageContext.request.contextPath}/include/footer.jsp');
      </script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	crossorigin="anonymous"></script>
<script src="../js/scripts.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js"
	crossorigin="anonymous"></script>
<script src="assets/demo/chart-area-demo.js"></script>
<script src="assets/demo/chart-bar-demo.js"></script>
<script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest"
	crossorigin="anonymous"></script>
<script src="js/datatables-simple-demo.js"></script>
</html>