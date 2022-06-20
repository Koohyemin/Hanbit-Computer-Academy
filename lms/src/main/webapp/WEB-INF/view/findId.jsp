<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>findId</title>
</head>
<body>
	<h2>id찾기</h2>
	<form action="${pageContext.request.contextPath}/findId" method="post">
		<div>학생이름
		<input type="text" name="studentName">
		</div>
		<div>연락처
		<input type="text" name="studentPhone">
		</div>
		<button type="submit">ID찾기</button>
	</form>
</body>
</html>