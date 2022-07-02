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
<!-- 썸머노트 cdn -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>
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
            <div class="card-body">
         <form id="addEnquiryBoardForm" action="${pageContext.request.contextPath}/enquiryBoard/addEnquiryBoard" method="post">
            <table class="table">
               <tr>   
                  <th style="width:150px;">글쓴이</th>
                  <td><input type="text" name="memberId" value="${sessionMemberId}" class="form-control" readonly="readonly"></td>          
               </tr>      
               <tr>
                  <th>구분</th>
                  <c:choose> 
                     <c:when test="${sessionMemberLv == 1}">
                  <td>
                   <div class="form-group">
                     <select name="category" class="custom-select form-control"> 
                        <option value="전체" selected="selected">전체</option>
                        <option value="강사">강사</option>
                        <option value="운영자">운영자</option>
                     </select>
							<span class="text-danger" id="categoryError"></span>                     
                     </div>
                  </td>
                     </c:when> 
                     <c:when test="${sessionMemberLv == 2}">
                     <td>
                     <div class="form-group">
                     <select name="category" class="custom-select form-control">
                        <option value="전체">전체</option>
                        <option value="강사">강사</option>
                     </select>
							<span class="text-danger" id="categoryError"></span>                     
                     </div>
                  </td>   
                     </c:when>  
                  </c:choose> 
               </tr>
               <tr>                           
				<th class="text-center" style="vertical-align: middle">내용</th>
                  <td>
					<textarea name="content" id="summernote"></textarea>
						<span class="text-danger" id="contentError"></span>                
                  </td>                
               </tr>
            </table>
            <div class="float-end">            
            <button type="button" id="btn" class="btn btn-dark" style="float:right">등록</button>
			</div>         
         </form>
         </div>
        </div>
      </div>
   <div id="footer"></div>
   </div>
</div>
</body>
   <script>
       $('#nav').load('${pageContext.request.contextPath}/include/nav.jsp');
       $('#navbar').load('${pageContext.request.contextPath}/include/navBar.jsp');
       $('#footer').load('${pageContext.request.contextPath}/include/footer.jsp');

       $( document ).ready(function(){
   	   	$('#btn').click(function(){
   	   		// 카테고리
   	   		   if($('#category').val() == ''){
   	   		      $('#categoryError').text('대상을 선택해주세요');
   	   		   } else {
   	   		      $('#categoryError').text('');
   	   		   }
   	   		   // 내용
   	   		   if( $('#summernote').summernote('code').replace(/<\/?[^>]+(>|$)/g, '') == '') {
   	   			      $('#contentError').text('내용을 입력해주세요');
   	   			   } else {
   	   			      $('#contentError').text(''); 
   	   			   }

   	   		   if($('#category').val() != '' && $('#title').val() != '' && $('#summernote').summernote('code').replace(/<\/?[^>]+(>|$)/g, '') != '') {
   	   		      $('#addEnquiryBoardForm').submit();
   	   		   }
   	   		});
   	   	init();
   		summernoteHide();
   	});
   	
   	// 기본설정으로 summernote라는 id사용하는 태그를 summernote로 설정
   	function init(){
   		$('#summernote').summernote({
   			  tabsize: 2,
   			  height: 400
   		});
   	}    
   
    </script>
   <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
    <script src="../js/scripts.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
    <script src="assets/demo/chart-area-demo.js"></script>
    <script src="assets/demo/chart-bar-demo.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
    <script src="js/datatables-simple-demo.js"></script>
</html>