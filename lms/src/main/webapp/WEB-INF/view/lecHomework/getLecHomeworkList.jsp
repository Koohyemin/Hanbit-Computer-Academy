<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>lecHomework</title>
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
	                        LectureHomework
	                        <c:if test="${sessionMemberLv == 2}">
				                <div class="btn btn-group float-end">
									<a class="btn btn-dark btn-sm" role="button" href="${pageContext.request.contextPath}/lecHomework/addHomework">과제등록</a>
								</div>
	                        </c:if>
	                    </div>
	                    <div class="card-body">
	                    <form method="get" action="${pageContext.request.contextPath}/lecHomework/getLecHomeworkList">
							<div class="form-group btn-group" >
								<select id="lectureName" name="lectureName" class="form-control">
									<option selected="selected" >-----------------------------강좌선택--------------------------</option>
										<c:if test="${sessionMemberLv == 2}">
											<c:forEach var="l" items="${lectureNameList}">
												<option value="${l.lectureName}">${l.lectureName}</option>
											</c:forEach>
										</c:if>
										<c:if test="${sessionMemberLv == 1}">
											<c:forEach var="s" items="${studentLectureNameList}">
												<option value="${s.lectureName}">${s.lectureName}</option>
											</c:forEach>
										</c:if>
								</select>	
							<button type="submit" class="btn btn-dark">강의조회</button>					   
							</div>					             	
				        </form>
			        </div>
				</div>
			<!-- 강사용 -->
			<c:if test="${sessionMemberLv == 2}">
				<c:if test="${fn:length(homeworkMake) != 0}">
					<div class="row">
					<c:forEach var="h" items="${homeworkMake}">
					<div class="col-lg-6">
						<div class="card mb-4">
							<div class="card-header">
								<form method="post" action="${pageContext.request.contextPath}/lecHomework/removeHomework" id="del">
									과제
		                            <input type="hidden" value="${h.homeworkMakeNo}" name="homeworkMakeNo">
		                            <input type="hidden" value="${h.homeworkSubmissionNo}" name="homeworkSubmissionNo">
									<input class="btn btn-secondary btn-sm float-end delBtn" value="삭제" type="submit"/>
									<a class="btn btn-dark btn-sm float-end" role="button" href="${pageContext.request.contextPath}/lecHomework/modifyHomework?homeworkMakeNo=${h.homeworkMakeNo}">수정하기</a>
                                </form>
							</div>
							<table class="table table-bordered">
								<tr>
									<th class="text-center">강의</th>
									<td>${h.lectureName}</td>
								</tr>
								<tr>
									<th class="text-center">제목	</th>
									<td>${h.homeworkMakeTitle}</td>
								</tr>
								<tr>
									<th class="text-center">내용</th>
									<td>${h.homeworkMakeContent}</td>
								</tr>
								<tr>
									<th class="text-center">마감일</th>
									<td>${h.homeworkDeadline}</td>
								</tr>
								<tr>
									<th class="text-center">과제 등록일</th>
									<td>${h.createDate}</td>
								</tr>
								<tr>
									<td colspan="2" class="text-center"><a class="btn btn-outline-dark btn-sm" role="button" href="${pageContext.request.contextPath}/lecHomework/studentSubmitList?homeworkMakeNo=${h.homeworkMakeNo}">평가하기</a></td>
								</tr>
							</table>
						</div>
					</div>
					</c:forEach>
					</div>
				</c:if>
			</c:if>
			<!-- 학생용 -->
			<c:if test="${sessionMemberLv == 1}">
				<c:if test="${fn:length(studnetHomeworkMake) == 0}">
				 	<td class="text-center text-danger" colspan="5">등록된 과제가 없습니다.</td>
				</c:if>
				<c:if test="${fn:length(studnetHomeworkMake) != 0}">
					<div class="row">
					<c:forEach var="s" items="${studnetHomeworkMake}">
					<div class="col-lg-6">
						<div class="card mb-4">
							<div class="card-header">
								과제
							</div>
							
							<table class="table table-bordered">
								<tr>
									<th class="text-center">강의</th>
									<td>${s.lectureName}</td>
								</tr>
								<tr>
									<th class="text-center">제목	</th>
									<td>${s.homeworkMakeTitle}</td>
								</tr>
								<tr>
									<th class="text-center">내용</th>
									<td>${s.homeworkMakeContent}</td>
								</tr>
								<tr>
									<th class="text-center">과제 등록일</th>
									<td>${s.createDate}</td>
								</tr>
								<tr>
									<th class="text-center">마감일</th>
									<td>${s.homeworkDeadline}</td>
								</tr>
								<tr>
									<th class="text-center">점수</th>
									<td>${s.homeworkScore}</td>
								</tr>
								<tr>
									<c:choose>
										<c:when test="${s.checkDeadLine == 0}">
											<td class="text-center text-danger" colspan="2">과제 제출기간이 마감되었습니다.</td>
										</c:when>
										<c:when test="${s.checkDeadLine == 1}">
											<c:if test="${s.homeworkSubmissionNo == 0}">
												<td class="text-center" colspan="2">
													<a class="text-dark" href="${pageContext.request.contextPath}/lecHomework/addSubmit?homeworkMakeNo=${s.homeworkMakeNo}&&homeworkMakeTitle=${s.homeworkMakeTitle}"><b>과제 제출</b></a>
												</td>
											</c:if>
											<c:if test="${s.homeworkSubmissionNo != 0}">
												<td class="text-center" colspan="2">
													<a class="text-dark" href="${pageContext.request.contextPath}/lecHomework/submitOne?homeworkSubmissionNo=${s.homeworkSubmissionNo}&&homeworkMakeTitle=${s.homeworkMakeTitle}"><b>제출한 과제 상세보기</b></a>
												</td>
											</c:if>
										</c:when>
									</c:choose>
								</tr>
							</table>
						</div>
					</div>
				</c:forEach>
				</div>
				</c:if>
			</c:if>
			</div>
			<div id="footer"></div>
		</div>
	</div>
</body>
<script>
	// 과제 삭제
	$(".delBtn").click(function(){
		if (confirm('해당 자격증을 삭제 하시겠습니까?')) {
			$('#del').submit();
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
</html>
