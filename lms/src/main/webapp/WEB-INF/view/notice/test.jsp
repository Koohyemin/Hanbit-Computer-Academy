<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:choose>
		<!-- 1학생 2강사 3관리자 -->
		<!-- 학생에게는 전체공지와 학생공지를 보여준다. -->
		<c:when test="${sessionMemberLv==1}">
			<ul class="nav nav-tabs">
				<li class="nav-item">
					<c:choose>
						<c:when test="${category eq '전체'}">
							<a class="nav-link active" data-toggle="tab" href="${pageContext.request.contextPath}/notice/noticeList?category=전체">전체</a> <!-- 기본값이 전체로 잡혀있기 때문에 현제 페이지로 이동하도록 조건을 잡아놓음 -->
						</c:when>
						<c:otherwise>
						    <a class="nav-link" data-toggle="tab" href="${pageContext.request.contextPath}/notice/noticeList?category=전체">전체</a>
						</c:otherwise>
					</c:choose>
				</li>
				<li class="nav-item">
					<c:choose>
						<c:when test="${category eq '학생'}">
						    <a class="nav-link active" data-toggle="tab" href="${pageContext.request.contextPath}/notice/noticeList?category=학생">학생</a>
						</c:when>
						<c:otherwise>
							<a class="nav-link" data-toggle="tab" href="${pageContext.request.contextPath}/notice/noticeList?category=학생">학생</a>
						</c:otherwise>
					</c:choose>
				</li>
			</ul>
		</c:when>
		<!-- 강사에게는 전체공지와 강사공지를 보여준다. -->
		<c:when test="${sessionMemberLv==2}">
			<ul class="nav nav-tabs">
				<li class="nav-item">
					<c:choose>
						<c:when test="${category eq '전체'}">
							<a class="nav-link active" data-toggle="tab" href="${pageContext.request.contextPath}/notice/noticeList?category=전체">전체</a> <!-- 기본값이 전체로 잡혀있기 때문에 현제 페이지로 이동하도록 조건을 잡아놓음 -->
						</c:when>
						<c:otherwise>
						    <a class="nav-link" data-toggle="tab" href="${pageContext.request.contextPath}/notice/noticeList?category=전체">전체</a>
						</c:otherwise>
					</c:choose>
				</li>
				<li class="nav-item">
					<c:choose>
						<c:when test="${category == '강사'}">
						    <a class="nav-link active" data-toggle="tab" href="${pageContext.request.contextPath}/notice/noticeList?category=강사">강사</a>
						</c:when>
						<c:otherwise>
							<a class="nav-link" data-toggle="tab" href="${pageContext.request.contextPath}/notice/noticeList?category=강사">강사</a>
						</c:otherwise>
					</c:choose>
				</li>
			</ul>
		</c:when>
		<!-- 운영진은 전체공지를 카테고리별로 볼 수 있다. -->
		<c:otherwise>
			<ul class="nav nav-tabs">
				<li class="nav-item">
					<c:choose>
						<c:when test="${category eq '전체'}">
							<a class="nav-link active" data-toggle="tab" href="${pageContext.request.contextPath}/notice/noticeList?category=전체">전체</a> <!-- 기본값이 전체로 잡혀있기 때문에 현제 페이지로 이동하도록 조건을 잡아놓음 -->
						</c:when>
						<c:otherwise>
						    <a class="nav-link" data-toggle="tab" href="${pageContext.request.contextPath}/notice/noticeList?category=전체">전체</a>
						</c:otherwise>
					</c:choose>
				</li>
				<li class="nav-item">
					<c:choose>
						<c:when test="${category == '강사'}">
						    <a class="nav-link active" data-toggle="tab" href="${pageContext.request.contextPath}/notice/noticeList?category=강사">강사</a>
						</c:when>
						<c:otherwise>
							<a class="nav-link" data-toggle="tab" href="${pageContext.request.contextPath}/notice/noticeList?category=강사">강사</a>
						</c:otherwise>
					</c:choose>
				</li>
				<li class="nav-item">
					<c:choose>
						<c:when test="${category eq '학생'}">
						    <a class="nav-link active" data-toggle="tab" href="${pageContext.request.contextPath}/notice/noticeList?category=학생">학생</a>
						</c:when>
						<c:otherwise>
							<a class="nav-link" data-toggle="tab" href="${pageContext.request.contextPath}/notice/noticeList?category=학생">학생</a>
						</c:otherwise>
					</c:choose>
				</li>
			</ul>
		</c:otherwise>
		<br> <!-- 운영자만 글 작성이 가능하다. -->
			<a class="float-right btn btn-dark">글 작성</a>
		<br><br>
	</c:choose>
</body>
</html>