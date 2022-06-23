<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<table border="1">
		<thead>
			<tr>
				<th>아이디</th>
				<th>신청일</th>
				<th>등급</th>
			</tr>
		</thead>
		<tbody>
		
				<c:forEach var="c"  items="${waitingList}">
					<tr>
						<td>${c.memberId}</td>		
						<td>${c.createDate}</td>
						<td>${c.memberLevel}</td>
						<td>${c.meberState}</td>
						<td>
							<input type="checkbox" name="approvalCk" value="${c.memberid}">
						</td>
					</tr>
				</c:forEach>

		</tbody>
	</table>
</body>
</html>