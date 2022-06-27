<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>addEnquiryBoard</title>
</head>
<body>
	<h2>문의사항 입력</h2>
	<form action="${pageContext.request.contextPath}/enquiryBoard/addEnquiryBoard" method="post">
		<table border="1">
			
			<tr>	
				<th>글쓴이</th>
				<td><input type="text" name="memberId" value="${sessionMemberId}"></td> 			
			</tr>		
			<tr>
				<th>구분</th>
				<td>
					<select name="category">
						<option value="전체">전체</option>
						<option value="강사">강사</option>
						<option value="운영자">운영자</option>
					</select>
				</td>	
			</tr>
			<tr>									
				<th>내용</th>
				<td>
					<textarea name="content" rows="5" cols="30" placeholder="내용을 입력하세요"></textarea>
				</td> 					
			</tr>
	</table>
		<button type="submit">입력</button>
	</form>
</body>
</html>