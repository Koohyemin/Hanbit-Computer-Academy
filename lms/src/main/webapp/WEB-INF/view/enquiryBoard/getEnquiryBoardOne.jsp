<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>getEnquiryBoardOne</title>
</head>
<body>
	<h2>to.강사/운영자 문의사항 상세보기</h2>
	<table border="1">
			<tr>
				<th>글번호</th>
				<td>${eb.enquiryBoardNo}</td>
			</tr>
			<tr>	
				<th>글쓴이</th>
				<td>${eb.memberId}</td> 			
			</tr>		
			<tr>
				<th>구분</th>
				<td>${eb.category}</td> 
			</tr>
			<tr>									
				<th>내용</th>
				<td>${eb.content}</td> 					
			</tr>
			<tr>	
				<th>작성일</th>
				<td>${eb.createDate}</td> 							
			</tr>
			<tr>	
				<th>수정일</th>
				<td>${eb.updateDate}</td> 								
			</tr>
	</table>
		<a href="${pageContext.request.contextPath}/enquiryBoard/updateEnquiryBoard">수정</a>
		<a href="${pageContext.request.contextPath}/enquiryBoard/deleteEnquiryBoard">삭제</a>
	
</body>
</html>