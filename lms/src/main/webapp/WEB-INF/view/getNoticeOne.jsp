<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Notice</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
	<div class="container">
		<br>
		<h1>공지사항 상세보기</h1>
		<br>
		<a href="${pageContext.request.contextPath}/getNoticeListByPage" class="float-right btn btn-dark">이전으로</a>
		<br><br>
		<table class="table">
			<tr>
				<th class="text-center">번호</th>
				<td><div class="form-control">${managerNotice.managerNoticeNo}</div></td>
			</tr>
			<tr>
				<th class="text-center">대상</th>
				<td><div class="form-control">${managerNotice.category}</div></td>
			</tr>
			<tr>
				<th class="text-center">제목</th>
				<td><div class="form-control">${managerNotice.managerNoticeTitle}</div></td>
			</tr>
			<tr style="height:20%;">
				<th class="text-center sm-mb-5" style="vertical-align: middle">내용</th>
				<td><div class="form-control" style="height: 100px;">${managerNotice.managerNoticeContent}</div></td>
			</tr>
			<tr>
				<th class="text-center">작성자</th>
				<td><div class="form-control">${managerNotice.managerNoticeContent}</div></td>
			</tr>
			<tr>
				<th class="text-center">작성일자</th>
				<td><div class="form-control">${managerNotice.createDate}</div></td>
			</tr>
			<tr>
				<th class="text-center">수정일자</th>
				<td><div class="form-control">${managerNotice.updateDate}</div></td>
			</tr>
		</table>
	</div>
</body>
</html>