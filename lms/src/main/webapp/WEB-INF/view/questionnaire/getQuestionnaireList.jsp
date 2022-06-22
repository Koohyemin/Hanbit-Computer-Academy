<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>questionnaire</title>
</head>
<body>
	<h2>설문조사</h2>
		<table border="1">
		<thead>
			<tr>
				<th>구분번호</th>
				<th>강의이름</th>
				<th>구분</th>
				<th>질문내용</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="q" items="${list}">
			<tr>
				<td>${q.evaluationNo}</td>	
				<td>${q.lectureName}</td>
				<td>${q.category}</td>
				<td>${q.questionnaireContent}</td>
				<td>
					<input type="radio" name="radio${q.evaluationNo}" value="1">전혀아니다<br>
					<input type="radio" name="radio${q.evaluationNo}" value="2">아니다<br>
					<input type="radio" name="radio${q.evaluationNo}" value="3">보통이다<br>
					<input type="radio" name="radio${q.evaluationNo}" value="4">그렇다<br>
					<input type="radio" name="radio${q.evaluationNo}" value="5">매우그렇다
				</td>
			</tr>
			</c:forEach>
		</tbody>	
		</table>
</body>
</html>