<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>updateEnquiryBoard</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/enquiryBoard/updateEnquiryBoard" method="post">
		<table border="1">
			<tr>
				<th>글번호</th>
				<td>${eb.enquiryBoardNo}</td>
			</tr>
			<tr>	
				<th>글쓴이</th>
				<td>${sessionMemberId}</td> 			
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
				<td><input type="text" name="content" placeholder=${eb.content} ></td> 					
			</tr>							
		</table>
		<button type="submit">수정</button>
	</form>
</body>
</html>