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
                   <a href="${pageContext.request.contextPath}/enquiryBoard/getEnquiryBoardListByPage" class="btn btn-dark btn-sm float-end">뒤로가기</a>  
               </div>
               <div class="card-body">
	               <form action="${pageContext.request.contextPath}/enquiryBoard/deleteEnquiryBoard" method="post">
	               <table class="table">
	                  <tr>   
	                     <th>글쓴이</th>
	                     <td>${eb.memberId}
	                     <input type="hidden" name="enquiryBoardNo" value="${eb.enquiryBoardNo}" >
	                     </td>          
	                  </tr>      
	                  <tr>
	                     <th>구분</th>
	                     <td>${eb.category}</td> 
	                  </tr>
	                                    <tr>   
	                     <th>작성일</th>
	                     <td>${eb.createDate}</td>                      
	                  </tr>
	                  <tr>   
	                     <th>수정일</th>
	                     <td>${eb.updateDate}</td>                         
	                  </tr>
	                  <tr>                           
	                     <th>내용</th>
	                     <td>${eb.content}</td>
	                  </tr>   
	               </table>
	               <br>
	             
	               <div class="btn-group float-end">
	               <c:if test="${sessionMemberId eq eb.memberId}">
		              	<a href="${pageContext.request.contextPath}/enquiryBoard/updateEnquiryBoard?enquiryBoardNo=${enquiryBoardNo}" class="btn btn-secondary">수정</a>
		               	<button type="submit" class="btn btn-dark">삭제</button>
	           		</c:if>
	               </div>
	            </form>    
	   			<!-- 구분이 전체라면 학생을 제외한 답변 -->	
	       		<c:if test="${eb.category eq '전체' }">
		       		 <c:if test="${sessionMemberLv != 1}">
			       		 <form method="post" action="${pageContext.request.contextPath}/enquiryBoard/addEnquityAnswer">
				       		 <div class="form-group btn-group" style="width:100%;" >
				       		  <input type="hidden" name="enquiryBoardNo" value="${eb.enquiryBoardNo}" >
								  <textarea rows="5" style="width:100%;" class="form-control" placeholder="-> 답변을 해주세요" name="enquiryAnswerContent"></textarea>
								  <button type="submit" style="width:10%;max-width:200px;" class="btn btn-dark">답변</button> 
							</div> 
			       		 </form>
		       		 </c:if>
	       		</c:if>   
	       		<!-- 구분이 강사라면 학생을 제외한 답변 -->	
	       		<c:if test="${eb.category eq '강사' }">
		       		 <c:if test="${sessionMemberLv == 2 }">
			       		 <form method="post" action="${pageContext.request.contextPath}/enquiryBoard/addEnquityAnswer">
				       		 <div class="form-group btn-group" style="width:100%;" >
				       		  <input type="hidden" name="enquiryBoardNo" value="${eb.enquiryBoardNo}" >
								  <textarea rows="5" style="width:100%;" class="form-control" placeholder="-> 답변을 해주세요" name="enquiryAnswerContent"></textarea>
								  <button type="submit" style="width:10%;max-width:200px;" class="btn btn-dark">답변</button> 
							</div> 
			       		 </form>
		       		 </c:if>
	       		</c:if>   
	       		<!-- 구분이 강사라면 학생을 제외한 답변 -->	
	       		<c:if test="${eb.category eq '운영자' }">
		       		 <c:if test="${sessionMemberLv == 3 }">
			       		 <form method="post" action="${pageContext.request.contextPath}/enquiryBoard/addEnquityAnswer">
				       		 <div class="form-group btn-group" style="width:100%;" >
				       		  <input type="hidden" name="enquiryBoardNo" value="${eb.enquiryBoardNo}" >
								  <textarea rows="5" style="width:100%;" class="form-control" placeholder="-> 답변을 해주세요" name="enquiryAnswerContent"></textarea>
								  <button type="submit" style="width:10%;max-width:200px;" class="btn btn-dark">답변</button> 
							</div> 
			       		 </form>
		       		 </c:if>
	       		</c:if>   
	       		<br>
	       		  
	       		 <!-- 댓글 최신순으로  -->
	       		<c:forEach var="EnquiryAnswer" items="${answerList}">
		       		 <div class="card mb-4">
		               <div class="card-header">
		                   <b>${EnquiryAnswer.memberId}님</b>
		                   <span class="float-end">
			       		 		${EnquiryAnswer.createDate}
			       		 		<c:if test="${EnquiryAnswer.memberId eq sessionMemberId }">
			       		 			&nbsp;<a href="${pageContext.request.contextPath}/enquiryBoard/enquityDeleteAnswer?enquiryBoardNo=${eb.enquiryBoardNo}&enquiryBoardAnswerNo=${EnquiryAnswer.enquiryBoardAnswerNo}" class="btn btn-secondary btn-sm">삭제하기</a>
			       		 		</c:if>
			       		 	</span>
		               </div>
		               <div class="card-body">
		               ${EnquiryAnswer.enquiryAnswerContent}
		               </div>
	              	 </div>
	       		</c:forEach>
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