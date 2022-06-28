<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<meta property="og:title" content="한빛컴퓨터아카데미LMS">
<meta property="og:url" content="lms/login">
<meta property="og:image" content="${pageContext.request.contextPath}/img/previewer.png">
<title>getEnquiryBoardListByPage</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
<link href="../css/styles.css" rel="stylesheet" />
<script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
</head>
<body class="sb-nav-fixed">
<div id="nav"></div>
<div id="layoutSidenav">
	<div id="layoutSidenav_nav">
		<div id="navbar"></div>
	</div>
    <div id="layoutSidenav_content">
		<div class="container-fluid px-4">
     	<!-- 컨텐츠 삽입 부분-->
     			<br>
        	<div class="card mb-4">
	            <div class="card-header">
	                <i class="fas fa-chart-area me-1"></i>
	                Enquiry Board
	            </div>
            </div>
            <a href="${pageContext.request.contextPath}/enquiryBoard/getEnquiryBoardListByPage" class="btn btn-dark">이전으로</a>
            <br><br>
		<form action="${pageContext.request.contextPath}/enquiryBoard/addEnquiryBoard" id="addEnquiryBoardForm" method="post">
		<table class="table">
			
			<tr>	
				<th class="text-center">글쓴이</th>
				<td><input type="text" name="memberId" value="${sessionMemberId}" class="form-control"></td> 			
			</tr>		
			<tr>
				<th class="text-center">구분</th>
				<c:choose> 
					<c:when test="${sessionMemberLv == 1}">
				<td>
					<select name="category" id="category" class="form-control">
						<option value="전체" id="category">전체</option>
						<option value="강사">강사</option>
						<option value="운영자">운영자</option>
					</select>
					<span id="categoryError"></span>
				</td>
					</c:when> 
					<c:when test="${sessionMemberLv == 2}">
					<td>
					<select name="category" id="category">
						<option value="전체">전체</option>
						<option value="운영진">운영진</option>
					</select>
					<span id="categoryError"></span>					
				</td>	
					</c:when>  
				</c:choose> 
			</tr>
			<tr>									
				<th class="text-center" style="vertical-align: middle">내용</th>
				<td>
					<textarea name="content"  class = "form-control" id="content" cols="50" rows="10" placeholder="내용을 입력하세요"></textarea>
					<span id="contentError"></span>
				</td> 					
			</tr>
	</table>
		<button type="submit" id="btn" class="btn btn-dark"">등록</button>
	</form>
<div id="footer"></div>
	</div>
</div>
</body>
	<script>
    $('#nav').load('${pageContext.request.contextPath}/include/nav.jsp');
    	$('#navbar').load('${pageContext.request.contextPath}/include/navBar.jsp');
    	$('#footer').load('${pageContext.request.contextPath}/include/footer.jsp');
    	
 			//유효성 검사
    	   	$('#btn').click(function(){
    	   		
    	   		//카테고리
    	   		if($('#category').val() == '전체'){
    	   		      $('#categoryError').text('구분을 선택해주세요');
    	   		      return false;
    	   		   } else {
    	   		      $('#categoryError').text('');
    	   		   }
    	   		//내용
    	   		   if( $('#content').val() == '') {
    	   			      $('#contentError').text('내용을 입력해주세요');
    	   			   return false;
    	   			   } else {
    	   			      $('#contentError').text(''); 
    	   			   }
					//둘다 값이 있다면 
    	   		   if($('#category').val() != '' && $('#content').val() != '') {
    	   		      $('#addEnquiryBoardForm').submit();
    	   		   }
    	   		})
    </script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
    <script src="../js/scripts.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
    <script src="assets/demo/chart-area-demo.js"></script>
    <script src="assets/demo/chart-bar-demo.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
    <script src="js/datatables-simple-demo.js"></script>
</html>