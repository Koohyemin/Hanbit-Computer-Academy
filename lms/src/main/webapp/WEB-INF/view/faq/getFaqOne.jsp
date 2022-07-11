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
<title>getFaqOne</title>
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
                FAQ
             <c:if test="${sessionMemberLv == 3 }">
	             <form method="post" action="${pageContext.request.contextPath}/faq/deleteFaq"  style="float:right">
	              		<a class="btn btn-dark btn-sm" id="delBtn" href="${pageContext.request.contextPath}/faq/updateFaq?faqNo=${faq.faqNo}">수정</a>
	              <input type="hidden" name="faqNo" value="${faq.faqNo}" readonly="readonly">
	              <button type="submit" class="btn btn-dark btn-sm">삭제</button>
	             </form>
            </c:if>
            </div>
            <div class="card-body">
	           <!-- FAQ 상세보기  -->
	              <table class="table">
	              <tr>
                      <th class="text-center" style="width:150px;">제목</th>
                       <td>${faq.title}</td>
                    <tr>
                        <th class="text-center" style="width:150px;height:500px;" >내용</th>
                       <td>${faq.content}</td>
                    </tr>
	                 </table>
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
      </script>
   <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
    <script src="../js/scripts.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
    <script src="assets/demo/chart-area-demo.js"></script>
    <script src="assets/demo/chart-bar-demo.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
    <script src="js/datatables-simple-demo.js"></script>
</html>