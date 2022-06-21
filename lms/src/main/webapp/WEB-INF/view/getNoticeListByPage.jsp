<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Notice</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
	<div class="container">
		<br>
		<h1>공지사항</h1>
		<br>
		<a class="float-right btn btn-dark">글 작성</a>
		<br><br>
		<table class="table">
			<tr>
				<th class="text-center">번호</th>
				<th class="text-center">대상</th>
				<th class="text-center">제목</th>
				<th class="text-center">작성자</th>
				<th class="text-center">작성일</th>
			</tr>
			<c:forEach var="notice" items="${list}">
				<tr>
					<td class="text-center">${notice.managerNoticeNo}</td>
					<td class="text-center">${notice.category}</td>
					<td class="col-md-4"><a href="${pageContext.request.contextPath}/getNoticeOne?managerNoticeNo=${notice.managerNoticeNo}">${notice.managerNoticeTitle}</a></td>
					<td class="text-center">${notice.managerId}</td>
					<td class="text-center">${notice.createDate}</td>
				</tr>
			</c:forEach>
		</table>
		<div class="text-center">
			<c:if test="${currentPage>1}">
				<a href="${pageContext.request.contextPath}/getNoticeListByPage?currentPage=${currentPage-1}" class="btn btn-dark">이전</a>
			</c:if>
			<c:if test="${lastPage>currentPage}">
				<a href="${pageContext.request.contextPath}/getNoticeListByPage?currentPage=${currentPage+1}" class="btn btn-dark">다음</a>
			</c:if>
		</div>
	</div>
</body>
</html>