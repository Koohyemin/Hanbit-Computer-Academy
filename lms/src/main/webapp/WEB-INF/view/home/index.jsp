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
<title>Lecture List</title>
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
            <c:choose>      
            <c:when test="${sessionMemberLv == 3}">
               <div class="card mb-4">
               <div class="card-header">
                  <i class="fas fa-chart-area me-1"></i>
                 	 가입요청
                	<a class="btn btn-dark btn-sm float-end"  href="${pageContext.request.contextPath}/authorizeMember">바로가기</a>
               </div>
               	<div class="card-body">
                 <table class="table">
	               <thead>
	                  <tr>
	                     <th>아이디</th>
	                     <th>신청일</th>
	                     <th>등급</th>
	                     <th>상태</th>
	                  </tr>
	               </thead>
	               <tbody>
	                  <c:forEach var="waitingList"  items="${waitingList}">
	                     <tr>
	                        <td>${waitingList.memberId}</td>      
	                        <td>${waitingList.createDate}</td>
	                        <td>${waitingList.memberLevel}</td>
	                        <td>${waitingList.meberState}</td>
	                     </tr>
	                  </c:forEach>
	               </tbody>
          		  </table>
            </div>
            </div>
               <div class="card mb-4">
               <div class="card-header">
                   <i class="fas fa-chart-area me-1"></i>
                  강의계획서
                  <a class="btn btn-dark btn-sm float-end" href="${pageContext.request.contextPath}/lecReference/getLecReferenceListByPage">바로가기</a> 
               </div>
               	<div class="card-body">   			      
	            <table class="table">
	               <thead>
	                     <tr>
	                        <th>이름</th>
	                        <th>글쓴이</th>
	                        <th>상태</th>
	                        <th>작성일</th>
	                     </tr>
                  </thead>
                  <tbody>
                  <c:forEach var="lecPlanList" items="${lecPlanList}">
                     <tr>
                        <td>${lecPlanList.lectureName}</td>
                        <td>${lecPlanList.teacherId}</td>
                        <td>${lecPlanList.lecState}</td>               
                        <td>${lecPlanList.createDate}</td>
                     </tr>
                  </c:forEach>
                  </tbody>
            </table>
            </div>
            </div>
			<div class="card mb-4">
            <div class="card-header">
                <i class="fas fa-chart-area me-1"></i>
                 문의사항
                 <a class="btn btn-dark btn-sm float-end" href="${pageContext.request.contextPath}/enquiryBoard/getEnquiryBoardListByPage">바로가기</a>
             </div>            
               	<div class="card-body">              
            <table class="table">
               <thead>
                  <tr>
                     <th class="text-center">번호</th>
                     <th class="text-center">글쓴이</th>
                     <th class="text-center">내용</th>
                     <th class="text-center">구분</th>
                     <th class="text-center">>작성일</th>
                  </tr>
               </thead>
               <tbody>
                  <c:forEach var="enquiryBoardList" items="${enquiryBoardList}">
                     <tr>
                        <td class="text-center">${enquiryBoardList.enquiryBoardNo}</td>
                        <td class="text-center">${enquiryBoardList.memberId}</td>
                        <td class="text-center">${enquiryBoardList.content}</td>
                         <td class="text-center">${enquiryBoardList.category}</td>
                         <td class="text-center">${enquiryBoardList.createDate}</td>
                     </tr>
                  </c:forEach>
               </tbody>
            </table>
            </div>
            </div> 
        	<div class="card mb-4">
            <div class="card-header">
                <i class="fas fa-chart-area me-1"></i>
                  공지사항
                   <a class="btn btn-dark btn-sm float-end" href="${pageContext.request.contextPath}/notice/getNoticeListByPage">바로가기</a>
            </div>   
               	<div class="card-body">                             
            <table class="table">
               <thead>
                  <tr>
                     <th>번호</th>
                     <th>글쓴이</th>
                     <th>제목</th>
                     <th>내용</th>
                     <th>구분</th>
                     <th>작성일</th>
                  </tr>                  
               </thead>
               <tbody>
                  <c:forEach var="noticeList" items="${noticeList}">
                     <tr>
                        <td>${noticeList.managerNoticeNo}</td>
                        <td>${noticeList.managerId}</td>
                        <td>${noticeList.managerNoticeTitle}</td>
                        <td>${noticeList.managerNoticeContent}</td>
                        <td>${noticeList.category}</td>
                        <td>${noticeList.createDate}</td>
                     </tr>
                  </c:forEach>
               </tbody>
            </table> 
            </div>
            </div>           
            </c:when>
            <c:when test="${sessionMemberLv == 2}">
			<div class="card mb-4">
            <div class="card-header">   
            <i class="fas fa-chart-area me-1"></i>         
                담당강좌
            </div>    
                  <span><a class="btn btn-dark" href="${pageContext.request.contextPath}/member/getMemberOne">바로가기</a></span>   
				<div class="card-body">                                               
                     <table class="table">
                   <thead>
                      <tr>
                         <th>강좌이름</th>
                         <th>담당강사</th>
                      </tr>
                   </thead>
                   <tbody>
                      <c:forEach var="teacherLecList" items="${teacherLecList}">
                         <tr>
                            <td>${teacherLecList.lectureName}</td>
                            <td>${teacherLecList.teacherId}</td>
                         </tr>
                      </c:forEach>
                   </tbody>
                </table>
                </div>
                </div>
            <div class="card mb-4">
            <div class="card-header">      
            <i class="fas fa-chart-area me-1"></i>      
                강좌문의사항
                <a class="btn btn-dark btn-sm float-end" href="${pageContext.request.contextPath}/enquiryBoard/getEnquiryBoardListByPage">바로가기</a>
            </div>                                   
            <div class="card-body">
            <table class="table">
                  <thead>
                     <tr>
                        <th>번호</th>
                        <th>내용</th>
                        <th>구분</th>
                        <th>작성일</th>
                     </tr>
                  </thead>
                  <tbody>
                     <c:forEach var="teacherEqList" items="${teacherEqList}">
                        <tr>
                           <td>${teacherEqList.enquiryBoardNo}</td>
                           <td>${teacherEqList.content}</td>
                           <td>${teacherEqList.category}</td>
                           <td>${teacherEqList.createDate}</td>
                        </tr>
                     </c:forEach>
                  </tbody>
               </table> 
               </div>
               </div>              
            <div class="card mb-4">
            <div class="card-header">    
            <i class="fas fa-chart-area me-1"></i>        
                공지사항
            	<a class="btn btn-dark btn-sm float-end" href="${pageContext.request.contextPath}/notice/noticeList">바로가기</a>    
            </div>          
           		<div class="card-body">
           		 <table class="table">
                     <thead>
                        <tr>
                           <th>번호</th>
                           <th>글쓴이</th>
                           <th>제목</th>
                           <th>구분</th>
                           <th>작성일</th>
                        </tr>
                     </thead>
                     <tbody>
                        <c:forEach var="teacherList" items="${teacherList}">
                           <tr>
                              <td>${teacherList.managerNoticeNo}</td>
                              <td>${teacherList.managerId}</td>
                              <td>${teacherList.managerNoticeTitle}</td>
                              <td>${teacherList.category}</td>
                              <td>${teacherList.createDate}</td>
                           </tr>
                        </c:forEach>
                     </tbody>
                  </table>
                  </div>
                  </div>
            </c:when>
            <c:when test="${sessionMemberLv == 1}">
             <div class="card mb-4">
            <div class="card-header">     
            <i class="fas fa-chart-area me-1"></i>       
                수강강좌
               <a class="btn btn-dark btn-sm float-end" href="${pageContext.request.contextPath}/member/getMemberOne">바로가기</a> 
            </div>                        
              <div class="card-body">
               <table class="table">
                  <thead>
                     <tr>
                        <th>번호</th>
                        <th>수업이름</th>
                        <th>ID</th>
                     </tr>
                  </thead>
                  <tbody>
                     <c:forEach var="studentLecList" items="${studentLecList}">
                        <tr>
                           <td>${studentLecList.registrationNo}</td>
                           <td>${studentLecList.lectureName}</td>
                           <td>${studentLecList.studentId}</td>

                        </tr>
                     </c:forEach>
                  </tbody>
               </table>
               </div>
              </div>
             <div class="card mb-4">
             <div class="card-header"> 
             <i class="fas fa-chart-area me-1"></i>           
                공지사항
                <a class="btn btn-dark btn-sm float-end"  href="${pageContext.request.contextPath}/notice/noticeList">바로가기</a> 
            </div>                                                        
                 <div class="card-body"> 
                  <table class="table">
                     <thead>
                        <tr>
                           <th>번호</th>
                           <th>글쓴이</th>
                           <th>제목</th>
                           <th>구분</th>
                           <th>작성일</th>
                        </tr>
                     </thead>
                     <tbody>
                        <c:forEach var="studentList" items="${studentList}">
                           <tr>
                              <td>${studentList.managerNoticeNo}</td>
                              <td>${studentList.managerId}</td>
                              <td>${studentList.managerNoticeTitle}</td>
                              <td>${studentList.category}</td>
                              <td>${studentList.createDate}</td>
                           </tr>
                        </c:forEach>
                     </tbody>
                  </table>
                  </div>
                  </div>
            </c:when>
            </c:choose> 
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
    <script>

    </script>
</html>