<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		<p>role</p>
		<label><input type="radio" name="role" value="student">학생</label>
		<label><input type="radio" name="role" value="teacher">선생</label>
		<label><input type="radio" name="role" value="manager">운영자</label>
	</div>
	<div>
    	<button type="submit" class="btn btn-primary">Submit</button>
		<a class="float-right btn btn-info" role="button" href="${pageContext.request.contextPath}">회원가입</a>
	</div>
 	</form>
</div>
	
</body>
</html>