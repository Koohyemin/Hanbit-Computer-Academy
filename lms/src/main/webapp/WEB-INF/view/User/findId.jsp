<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>findId</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
	<h2>학생id찾기</h2>
	<form id="signupForm" action="${pageContext.request.contextPath}/User/studentSearchId" method="post">
		<div>
			학생이름<input type="text" name="studentName" id="studentName">
		</div>
		<div>
			연락처<input type="text" name="studentPhone"  placeholder="하이픈(-)포함해서 입력">
		</div>
		<button type="submit">ID찾기</button>
	</form>
		<c:if test="${check == 1}">
			일치하는 정보가 없습니다.
		</c:if>
		<c:if test="${check == 0}">
			찾으시는 ID는 '${studentId}'입니다.
		</c:if>
			<a href="${pageContext.request.contextPath}/User/findPw">PW찾기로</a>
			
	<h2>강사id찾기</h2>
	<form action="${pageContext.request.contextPath}/User/teacherSearchId" method="post">
		<div>
			강사이름<input type="text" name="teacherName">
		</div>
		<div>
			강사연락처<input type="text" name="teacherPhone" placeholder="하이픈(-)포함해서 입력">
		</div>
		<button type="submit">ID찾기</button>
	</form>
	<c:if test="${check == 1}">
		일치하는 정보가 없습니다.
	</c:if>
	<c:if test="${check == 0}">
		찾으시는 ID는 '${teacherId}'입니다.
	</c:if>
	
	<h2>운영자id찾기</h2>
	<form action="${pageContext.request.contextPath}/User/managerSearchId" method="post">
		<div>
			운영자이름<input type="text" name="managerName">
		</div>
		<div>
			운영자연락처<input type="text" name="managerPhone" placeholder="하이픈(-)포함해서 입력">
		</div>
		<button type="submit">ID찾기</button>
	</form>
	<c:if test="${check == 1}">
		일치하는 정보가 없습니다.
	</c:if>
	<c:if test="${check == 0}">
		찾으시는 ID는 '${managerId}'입니다.
	</c:if>
<script>
	$('#studentName').focus();
	
	$('#studentName').blur(function(){
		if($('#studentName').val().length==0) {
			$('#studentNameHelper').text('id를 입력하세요.');
			$('#studentName').focus();
		} else {
			$('#studentNameHelper').text('');
		}
	});
	
	$('#studentPhone').blur(function(){
		if($('#studentPhone').val().length < 13) {
			$('#studentPhoneHelper').text('연락처는 13자리이상');
			$('#studentPhone').focus();
		} else if($('#studentPhone').val() != $('#studentPhone').val()) {
			$('#studentPhoneHelper').text('연락처를 입력하세요');
			$('#studentPhone').focus();
		} else {
			$('#studentPhoneHelper').text('');
		}
	});

	});
</script>
</html>