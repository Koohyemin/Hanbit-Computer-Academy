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
<title>Lecture List</title>
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
		<form method="get" action="${pageContext.request.contextPath}/lectureRoom/index">
			<div class="btn-group" >
				<select class="form-control" name="lectureName">
					<option>-----------------------------강좌선택--------------------------</option>
					<!-- 학생 수강 중인 목록 -->
		            <c:if test="${sessionMemberLv == 1}">
			            <c:forEach var="l" items="${lectureList}">
							<option <c:if test="${l eq lectureName}">selected="selected"</c:if>>${l}</option>>
			            </c:forEach>      
		            </c:if>
		            
		            <!-- 강사 수강 중인 목록 -->
					<c:if test="${sessionMemberLv == 2}">
			            <c:forEach var="l" items="${teacherLectureList}">
							<option>${l}</option>>
			            </c:forEach>  
		            </c:if>
				</select>
				<button type="submit" class="btn btn-dark">홈화면 조회</button>
			</div>
		</form>
		<br><br><br>
		<c:choose>
			<c:when test="${lectureName eq ''}">
				강의를 선택해주세요.
			</c:when>
			<c:otherwise>
				<div class="row">
					<div class="col-sm-6">
					<!-- 자료실 -->
					<h4 class="text-center">자료실 <span> <a class="btn btn-outline-dark" href="${pageContext.request.contextPath}/lecReference/getLecReferenceListByPage">바로가기</a></span></h4>
					<br>
					<table class="table">
						<thead>
							<tr class="text-center">
								<th>강의명</th>
								<th>제목</th>
								<th>작성일</th>
							</tr>
						</thead>
						<tbody>
						<c:if test="${lecReferenceSize eq 0}">
							<tr>
								<td class="text-center text-primary" colspan="3">강의의 자료가 존재하지않습니다.</td>
							</tr>
						</c:if>
						<c:forEach var="r" items="${lecReferenceList}">
							<tr>
								<td>${r.lectureName}</td>
								<td>${r.lecReferenceTitle}</td>
								<td>${r.createDate}</td>
							</tr>				
						</c:forEach>
						</tbody>
					</table>
					</div>
					<div class="col-sm-6">
					<!-- 공지사항 -->
					<h4 class="text-center">공지사항 <span> <a class="btn btn-outline-dark" href="${pageContext.request.contextPath}/lectureNotice/getLecNoticeListByPage">바로가기</a></span></h4>
					<br>
						<table class="table">
							<thead>
								<tr class="text-center">
									<th>강의명</th>
									<th>제목</th>
									<th>작성자</th>
									<th>작성일</th>
								</tr>
							</thead>
							<tbody>
							<c:if test="${lectureNoticeSize eq 0}">
								<tr>
									<td class="text-center text-primary" colspan="3">강의의 공지사항이 존재하지않습니다.</td>
								</tr>
							</c:if>
							<c:forEach var="n" items="${lectureNoticeList}">
								<tr class="text-center">
									<td>${n.lectureName}</td>
									<td>${n.title}</td>
									<td>${n.memberId}</td>
									<td>${n.createDate}</td>
								</tr>					
							</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
		
				<br><br>
				
				<div class="row">
					<div class="col-sm-6">
					<h4 class="text-center">과제<span> <a class="btn btn-outline-dark" href="${pageContext.request.contextPath}/lecHomework/getLecHomeworkList">바로가기</a></span></h4> <br>
					<table class="table">
							<thead>
								<tr class="text-center">
									<th>강의명</th>
									<th>제목</th>
									<th>마감일</th>
									<th>작성일</th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${homeworkSize eq 0}">
									<tr>
										<td class="text-center text-primary" colspan="3">강의의 과제가 존재하지않습니다.</td>
									</tr>
								</c:if>
								<c:forEach var="h" items="${homeworkList}">
									<tr class="text-center">
										<td>${h.lectureName}</td>
										<td>${h.homeworkMakeTitle}</td>
										<td>${h.homeworkDeadline}</td>
										<td>${h.createDate}</td>
									</tr>					
								</c:forEach>
							</tbody>
					</table>
					</div>
					<div class="col-sm-6">
					<h4 class="text-center">Q&A<span> <a class="btn btn-outline-dark" href="${pageContext.request.contextPath}/lecQna/lecQnaList">바로가기</a></span></h4> <br>
						<table class="table">
							<thead>
								<tr class="text-center">
									<th>강의명</th>
									<th>제목</th>
									<th>작성자</th>
									<th>작성일</th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${lecQuestionSize eq 0}">
									<tr>
										<td class="text-center text-primary" colspan="3">강의의 Q&A가 존재하지않습니다.</td>
									</tr>
								</c:if>
								<c:forEach var="q" items="${lecQuestionList}">
									<tr class="text-center">
										<td>${q.lectureName}</td>
									<c:choose>
										<c:when test="${q.revelation eq 'F'}">
										
											<!-- 학생이 본인게시글이 아니라면 게시글 상세보기로 넘어갈 수 없음 -->
											<!-- 본인게시글이거나 학생이 아니라면 열람 가능 -->
											<c:choose>
												<c:when test="${sessionMemberId ne q.memberId && sessionMemberLv == 1}">
													<td class="col-md-4">🔒 비공개 질문 입니다
														<c:if test="${q.answerCount != 0}">
															<span class="text-secondary">[${q.answerCount}]</span>
														</c:if>
													</td>
												</c:when>
												<c:otherwise>
													<td class="col-md-4"><a href="${pageContext.request.contextPath}/lecQna/lecQnaOne?lecQuestionNo=${q.lecQuestionNo}" class="none-unline">🔒 비공개 질문 입니다
														<c:if test="${q.answerCount != 0}">
															<span class="text-secondary">[${q.answerCount}]</span>
														</c:if>
													</a></td>											
												</c:otherwise>
											</c:choose>
										</c:when>
										<c:otherwise>
											<td class="col-md-4"><a href="${pageContext.request.contextPath}/lecQna/lecQnaOne?lecQuestionNo=${q.lecQuestionNo}" class="none-unline">${q.lecQuestionTitle}
												<c:if test="${q.answerCount != 0}">
													<span class="text-secondary">[${q.answerCount}]</span>
												</c:if>
											</a></td>							
										</c:otherwise>
									</c:choose>
									
										<td>${q.memberId}</td>
										<td>${q.createDate}</td>
									</tr>					
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
				
			</c:otherwise>
		</c:choose>
       
       
       </div>
             
      <div id="footer"></div>
   </div>
</div>
</body>
   <script>
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
    <script>

    </script>
</html>