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
           <div class="card mb-4">
               <div class="card-header">
                   <i class="fas fa-chart-area me-1"></i>
                  Index
               </div>               
            </div>
                 <c:choose>      
                   <c:when test="${sessionMemberLv == 3}">
               <h5>가입요청</h5> 
                  <a class="float-right btn btn-dark"  href="${pageContext.request.contextPath}/authorizeMember">바로가기</a>
                 <table class="table"> 
               <thead>
                  <tr>
                     <th class="text-center">아이디</th>
                     <th class="text-center">신청일</th>
                     <th class="text-center">등급</th>
                     <th class="text-center">상태</th>
                  </tr>
               </thead>
               <tbody>
                  <c:forEach var="waitingList"  items="${waitingList}">
                     <tr>
                        <td class="text-center">${waitingList.memberId}</td>      
                        <td class="text-center">${waitingList.createDate}</td>
                        <td class="text-center">${waitingList.memberLevel}</td>
                        <td class="text-center">${waitingList.meberState}</td>
                     </tr>
                  </c:forEach>
               </tbody>
            </table>
            
         <h5>강의계획서</h5>
              <a class="float-right btn btn-dark" href="${pageContext.request.contextPath}/lecReference/getLecReferenceListByPage">바로가기</a>                     
            <table class="table">
                 <thead>
                     <tr>
                        <th class="text-center">이름</th>
                        <th class="text-center">글쓴이</th>
                        <th class="text-center">상태</th>
                        <th class="text-center">작성일</th>
                     </tr>
                  </thead>
                  <tbody>
                  <c:forEach var="lecPlanList" items="${lecPlanList}">
                     <tr>
                        <td class="text-center">${lecPlanList.lectureName}</td>
                        <td class="text-center">${lecPlanList.teacherId}</td>
                        <td class="text-center">${lecPlanList.lecState}</td>               
                        <td class="text-center">${lecPlanList.createDate}</td>
                     </tr>
                  </c:forEach>
                  </tbody>
            </table>
            
         <h5>문의사항</h5>
           <a class="float-right btn btn-dark" href="${pageContext.request.contextPath}/enquiryBoard/getEnquiryBoardListByPage">바로가기</a>                    
            <table class="table">
               <thead>
                  <tr>
                     <th class="text-center">번호</th>
                     <th class="text-center">글쓴이</th>
                     <th class="text-center">내용</th>
                     <th class="text-center">구분</th>
                     <th class="text-center">작성일</th>
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
            
         <h5>공지사항</h5>
           <a class="float-right btn btn-dark" href="${pageContext.request.contextPath}/notice/noticeList">바로가기</a>                             
            <table class="table">
               <thead>
                  <tr>
                     <th class="text-center">번호</th>
                     <th class="text-center">글쓴이</th>
                     <th class="text-center">제목</th>
                     <th class="text-center">내용</th>
                     <th class="text-center">구분</th>
                     <th class="text-center">작성일</th>
                  </tr>                  
               </thead>
               <tbody>
                  <c:forEach var="noticeList" items="${noticeList}">
                     <tr>
                        <td class="text-center">${noticeList.managerNoticeNo}</td>
                        <td class="text-center">${noticeList.managerId}</td>
                        <td class="text-center">${noticeList.managerNoticeTitle}</td>
                        <td class="text-center">${noticeList.managerNoticeContent}</td>
                        <td class="text-center">${noticeList.category}</td>
                        <td class="text-center">${noticeList.createDate}</td>
                     </tr>
                  </c:forEach>
               </tbody>
            </table>   
           	</c:when>
            <c:when test="${sessionMemberLv == 2}">
                <br><h5>담당 강좌</h5>
                     <a class="btn btn-dark" href="${pageContext.request.contextPath}/member/getMemberOne">바로가기</a>                         
                     <table class="table">
                   <thead>
                      <tr>
                         <th class="text-center">강좌이름</th>
                         <th class="text-center">담당강사</th>
                      </tr>
                   </thead>
                   <tbody>
                      <c:forEach var="teacherLecList" items="${teacherLecList}">
                         <tr>
                            <td class="text-center">${teacherLecList.lectureName}</td>
                            <td class="text-center">${teacherLecList.teacherId}</td>
                         </tr>
                      </c:forEach>
                   </tbody>
                </table>
                
               <h5>담당강좌 문의사항</h5>
              <a class="float-right btn btn-dark" href="${pageContext.request.contextPath}/enquiryBoard/getEnquiryBoardListByPage">바로가기</a>                                
            	<table class="table">
                  <thead>
                     <tr>
                         <th class="text-center">번호</th>
                         <th class="text-center">내용</th>
                         <th class="text-center">구분</th>
                         <th class="text-center">작성일</th>
                     </tr>
                  </thead>
                  <tbody>
                     <c:forEach var="teacherEqList" items="${teacherEqList}">
                        <tr>
                           <td class="text-center">${teacherEqList.enquiryBoardNo}</td>
                           <td class="text-center">${teacherEqList.content}</td>
                           <td class="text-center">${teacherEqList.category}</td>
                           <td class="text-center">${teacherEqList.createDate}</td>
                        </tr>
                     </c:forEach>
                  </tbody>
               </table>    
                           
               <h5>공지사항</h5>
              <a class="float-right btn btn-dark" href="${pageContext.request.contextPath}/notice/noticeList">바로가기</a>                                               
            	<table class="table">
                     <thead>
                        <tr>
                            <th class="text-center">번호</th>
                            <th class="text-center">글쓴이</th>
                            <th class="text-center">제목</th>
                            <th class="text-center">구분</th>
                            <th class="text-center">작성일</th>
                        </tr>
                     </thead>
                     <tbody>
                        <c:forEach var="teacherList" items="${teacherList}">
                           <tr>
                              <td class="text-center">${teacherList.managerNoticeNo}</td>
                              <td class="text-center">${teacherList.managerId}</td>
                              <td class="text-center">${teacherList.managerNoticeTitle}</td>
                              <td class="text-center">${teacherList.category}</td>
                              <td class="text-center">${teacherList.createDate}</td>
                           </tr>
                        </c:forEach>
                     </tbody>
                  </table>
                  
            </c:when>
            <c:when test="${sessionMemberLv == 1}">
               <h5>수강중 강좌</h5>
                 <a class="float-right btn btn-dark" href="${pageContext.request.contextPath}/member/getMemberOne">바로가기</a>                                               
               <table class="table">
                  <thead>
                     <tr>
                        <th class="text-center">번호</th>
                        <th class="text-center">수업이름</th>
                        <th class="text-center">ID</th>
                     </tr>
                  </thead>
                  <tbody>
                     <c:forEach var="studentLecList" items="${studentLecList}">
                        <tr>
                           <td class="text-center">${studentLecList.registrationNo}</td>
                           <td class="text-center">${studentLecList.lectureName}</td>
                           <td class="text-center">${studentLecList.studentId}</td>
                        </tr>
                     </c:forEach>
                  </tbody>
                  
               </table><br>
               <h5>공지사항</h5>
              <a class="float-right btn btn-dark"  href="${pageContext.request.contextPath}/notice/noticeList">바로가기</a>                                                              
                  <table class="table">
                     <thead>
                        <tr>
                           <th class="text-center">번호</th>
                           <th class="text-center">글쓴이</th>
                           <th class="text-center">제목</th>
                           <th class="text-center">구분</th>
                           <th class="text-center">작성일</th>
                        </tr>
                     </thead>
                     <tbody>
                        <c:forEach var="studentList" items="${studentList}">
                           <tr>
                              <td class="text-center">${studentList.managerNoticeNo}</td>
                              <td class="text-center">${studentList.managerId}</td>
                              <td class="text-center">${studentList.managerNoticeTitle}</td>
                              <td class="text-center">${studentList.category}</td>
                              <td class="text-center">${studentList.createDate}</td>
                           </tr>
                        </c:forEach>
                     </tbody>
                  </table>
                  </div>
            </c:when>
            </c:choose> 
            
            </div>
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