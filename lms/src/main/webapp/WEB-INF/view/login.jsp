<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
	<div class="container">	<div>
		<c:if test="${error eq null}">
			${error}
		</c:if>
	</div>
	<br><br><br>
	<h2 class="text-center">Login</h2>
	<div class="row">
		<div class="col-sm-3"></div>
		<div class="col-sm-6">
			<form name="testForm" id="login" method="post" action="${pageContext.request.contextPath}/login">
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
				Username:
				<input type="text" id="uname" class="form-control"  placeholder="Enter username" name="memberId" >
		    	<span id="helpId"></span>
		    </div>
		    <div class="form-group">
				Password:
				<input type="password" id="pw" class="form-control" placeholder="Enter password" name="memberPw" >
				<span  id="helpPw"></span>
		    </div>
			<div>
				<input type="hidden" name="role" value="${role}">
			</div>
			 <button id="btn" class="btn btn-primary btn-block" type="button" >로그인</button>
			<div>
				<a class="btn btn-warning btn-block" role="button" href="${pageContext.request.contextPath}">회원가입</a>
			<div class="text-danger">
				<c:if test="${error != null}">
					${error}
				</c:if>
			</div>
			</div>
		 	</form>
		</div>
	</div>
</div>
<script>
$('#btn').click(function(){
	if($('#uname').val() == ''){
		$('#helpId').text('아이디를 입력해주세요');
	} else {
		$('#helpId').text('');
	}
	if($('#pw').val() == '') {
		$('#helpPw').text('비밀번호를 입력해주세요');
	} else {
		$('#helpPw').text('');
	}
	if($('#uname').val() != '' && $('#pw').val() != '') {
		$("#login").submit();
	}
});
</script>
</body>
</html>
