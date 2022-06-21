<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>findPw</title>
</head>
<body>
	<h2>pw찾기</h2>
	<form action="${pageContext.request.contextPath}/User/findPw" method="post">
		<div>
			학생ID<input type="text" name="studentId" placeholder="${studentId}">
		</div>
		<div>
			학생이름<input type="text" name="studentName">
		</div>
		<div>
			연락처<input type="text" name="studentPhone">
		</div>
		<button type="submit">PW찾기</button>
	</form>
	<c:if test="${check == 1}">
		일치하는 정보가 없습니다.
	</c:if>
	<c:if test="${check == 0}">
		찾으시는 PW는 '${studentPw}'입니다.
	</c:if>
	<a href="${pageContext.request.contextPath}/login">로그인</a>
</body>
</html>