<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
	<div class="container">
	<br><br><br>
	<h2 class="text-center">Login</h2>
	<form method="post" action="${pageContext.request.contextPath}/login">
	<ul class="nav nav-tabs">
		<li class="nav-item">
			<c:choose>
				<c:when test="${role eq 'student'}">
					<a class="nav-link active" data-toggle="tab" href="${pageContext.request.contextPath}/login?role=student">student</a>
				</c:when>
				<c:otherwise>
				    <a class="nav-link" data-toggle="tab" href="${pageContext.request.contextPath}/login?role=student">student</a>
				</c:otherwise>
			</c:choose>
		</li>
		<li class="nav-item">
			<c:choose>
				<c:when test="${role == 'teacher'}">
				    <a class="nav-link active" data-toggle="tab" href="${pageContext.request.contextPath}/login?role=teacher">teacher</a>
				</c:when>
				<c:otherwise>
					<a class="nav-link" data-toggle="tab" href="${pageContext.request.contextPath}/login?role=teacher">teacher</a>
				</c:otherwise>
			</c:choose>
		</li>
		<li class="nav-item">
			<c:choose>
				<c:when test="${role eq 'manager'}">
				    <a class="nav-link active" data-toggle="tab" href="${pageContext.request.contextPath}/login?role=manager">manager</a>
				</c:when>
				<c:otherwise>
					<a class="nav-link" data-toggle="tab" href="${pageContext.request.contextPath}/login?role=manager">manager</a>
				</c:otherwise>
			</c:choose>
		</li>
	</ul>
    <div class="form-group">
		<label for="uname">Username:</label>
		<input type="text" class="form-control" id="uname" placeholder="Enter username" name="memberId" required value="guest">
		<div class="valid-feedback">Valid.</div>
		<div class="invalid-feedback">Please fill out this field.</div>
    </div>
    <div class="form-group">
		<label for="pwd">Password:</label>
		<input type="password" class="form-control" id="pwd" placeholder="Enter password" name="memberPw" required value="1234">
		<div class="valid-feedback">Valid.</div>
		<div class="invalid-feedback">Please fill out this field.</div>
    </div>
	<div>
		<input type="hidden" name="role" value="${role}">
	</div>
	<div>
    	<button type="submit" class="btn btn-primary">Submit</button>
		<a class="float-right btn btn-info" role="button" href="${pageContext.request.contextPath}">회원가입</a>
	</div>
 	</form>
	 	<c:if test="${member.memberId ne null} ">
		 	<div>
			 	memberId = ${member.memberId}
		 	</div>
		 	<div>
		 		memberLevel = ${member.memberLevel}
		 	</div>
	 	</c:if>
	 	<c:if test="${error ne null}">
		 	<div class="text-danger">
		 		${error}
		 	</div>
	 	</c:if>
</div>
	
</body>
</html>