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
	               강의 납부 내역
	            </div>
            </div>
			
			
			 <select id="lectureName" name="lectureName" class="form-control">
			  <option selected="selected" value="">전체</option>
				 <c:forEach var="before" items="${beforeLectureList}"> 
					<option value="${before}">${before}</option>
				</c:forEach> 
			</select>
			<br>

			<table class="table table-hover">
				<thead>
					<tr>
						<th class="text-center">등록번호</th>
						<th class="text-center">학생명</th>
						<th class="text-center">강의명</th>
						<th class="text-center">납부금액</th>
						<th class="text-center">신청일</th>
						<th></th>
					</tr>
				</thead>
				<tbody id="tbodyid">
					<c:forEach var="reg" items="${paymentList}" varStatus="status">
						<tr>
							<td id="field1" class="text-center text-success">${reg.registrationNo}</td>
							<td id="field2" class="text-center">${reg.studentName}</td>
							<td id="field3" class="text-center col-md-4" >${reg.lectureName} </td>
							<td id="field4" class="text-center">${reg.payment}</td>
							<td id="field5" class="text-center">${reg.createDate}</td>
							<td id="field6" class="text-center" hidden="hidden">${reg.studentId}</td>
								<td><a href="${pageContext.request.contextPath}/registration/removeRegistRation?registrationNo	=${reg.registrationNo}" class="btn btn-warning btn-sm text-active">환불<i class="fa-solid fa-heart-circle-plus"></i></a></td>
							
						</tr>
							<!-- 강의가 없다면 개설된 강의가 없습니다. -->
							<c:if test="${totalCount == 0}">
								<div class="text-primary">개설된 강의가 없습니다.</div>
							</c:if>
					</c:forEach>
				</tbody>
			</table>
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

             $('#lectureName').change(function(){
            	 alert('이벤트발동');
            	 $("#field1").empty();
            	 $("#field2").empty();
            	 $("#field3").empty();
            	 $("#field4").empty();
            	 $("#field5").empty();
            	 $("#field6").empty();
            	 
            	 $.ajax({
          			type:"get" // get방식
          			,url:'/lms/RegipaymentList'											
          			,data:{'lectureName':$('#lectureName option:selected').val()}							
          			,success:function(arr){
          				console.log(arr);
          			  $(arr).each(function(index, item) {
	          				
	          				$('#field1').append('<td class="text-center text-success col-md-4	">'+item.registrationNo +'</td>');	
	          				$('#field2').append('<td class="text-center col-md-4">'+item.studentName +'</td>');	
	          				$('#field3').append('<td  class="text-center col-md-4">'+item.lectureName +'</td>');
	          				$('#field4').append('<td class="text-right col-md-4">'+item.payment +'</td>');
	          				$('#field5').append('<td class="text-center col-md-4">'+item.createDate.substring(0, 10) +'</td>');
	          				$('#field6').append('<td class="text-center" hidden="hidden">'+item.studentId +'</td>');
	          				
          	            });
          			  
          			}
              });
            	
         });
    </script>
</html>