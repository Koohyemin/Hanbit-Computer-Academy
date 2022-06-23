<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>updatePw</title>
</head>
<body>
	<h2>PW변경</h2>
	<form action="${pageContext.request.contextPath}/user/updatePw" method="post">
		<table>
			<tr>
				<td>비밀번호 입력</td>
				<td>
				<input type="password" placeholder="비밀번호를 입력하세요">
				</td>
			</tr>
		</table>
		<button type="submit">변경</button>
	</form>
</body>
</html>