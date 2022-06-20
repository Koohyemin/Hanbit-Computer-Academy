<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>faqOne</title>
<!-- bootstrap을 사용하기 위한 CDN주소 -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
 
</head>
<body>
<div class="container">
    <h1>FaqOne</h1>
    <table class="table table-striped">
        <form id="FaqController" method="get" name="getFaqOne" action="${pageContext.request.contextPath}/FaqController">
		
		<div>
			<tr>
				<th>번호</th>
				<td>"${faq.faqNo}"</td>
			</tr>
				<th>제목</th>
				<td>"${faq.faqTitle}"</td>
			<tr>
				<th>내용</th>
				<td>"${faq.faqContent}"</td>
			</tr>
		</div>
    </table>
   
    <div>
        <a class="btn btn-default" href="${pageContext.request.contextPath}/getFaqListPage">목록으로</a>
           <a class="btn btn-default" href="${pageContext.request.contextPath}/getFaqUpdate?noticeNo=${faq.faqNo}">수정</a>
              <a class="btn btn-default" href="${pageContext.request.contextPath}/getFaqDelete?noticeNo=${faq.faqNo}">삭제</a>
    </div>
</div>
</body>
</html>