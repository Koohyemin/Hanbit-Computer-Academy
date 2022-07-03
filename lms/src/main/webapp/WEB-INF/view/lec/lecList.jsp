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
	                Lecture
	            </div>
            </div>
			
			<ul class="nav nav-tabs">
					<li class="nav-item">
						<a class="nav-link active" data-toggle="tab" href="${pageContext.request.contextPath}/lec/lecList">수강 신청</a>
					</li>
		            <c:if test="${sessionMemberLv == 3}">
						<!-- 전체 강의 관리 버튼은 운영진에게만 보임 -->
						<li class="nav-item">
							<a class="nav-link" data-toggle="tab" href="${pageContext.request.contextPath}/people/peopleList?level=3">전체 강의</a>
						</li>
					</c:if>
			</ul>

			<table class="table table-hover">
				<thead>
					<tr>
						<th class="text-center">과목</th>
						<th class="text-center">강의명</th>
						<th class="text-center">수용인원</th>
						<th class="text-center">개강일</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="lec" items="${list}" varStatus="status">
						<tr>
							<th class="text-center text-success">${lec.subjectName}</th>
							<td class="text-center col-md-4" ><a href="${pageContext.request.contextPath}/lec/lecOne?lectureName=${lec.lectureName}" class="none-unline">${lec.lectureName}</a></td>
							<td class="text-center">${lec.registrationNumber}</td>
							<td class="text-center">${lec.beginClass}</td>
							<td id="point">
								<input type="hidden" id="CKTxt${status.index}" class ="CKTxt" value="${lec.lectureName}">
								<td><input type="text"  value="${lec.lectureName}" id="a${status.index}" hidden="hidden">
								<div class="btn-group float-end">
									<a href="${pageContext.request.contextPath}/keeping/addKeeping?lectureName=${lec.lectureName}"  id="keepingCK${status.index}" class="btn btn-danger btn-sm ktx" > 담아두기<i class="fa-solid fa-heart-circle-plus"></i></a>
								</div>
							</td>
						</tr>
							<!-- 강의가 없다면 개설된 강의가 없습니다. -->
							<c:if test="${totalCount == 0}">
								<div class="text-primary">개설된 강의가 없습니다.</div>
							</c:if>
					</c:forEach>
				</tbody>
			</table>
			<div class="text-center">
				<c:if test="${currentPage>1}">
					<a href="${pageContext.request.contextPath}/lec/lecList?currentPage=${currentPage-1}" class="btn btn-dark">이전</a>
				</c:if>
				<c:if test="${lastPage>currentPage}">
					<a href="${pageContext.request.contextPath}/lec/lecList?currentPage=${currentPage+1}" class="btn btn-dark">다음</a>
				</c:if>
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
    <script>
    
    console.log(${totalCount}); 
	
    for(let i =0; i<${list.size()}; i++){
    	let ck= false;
		$('#keepingCK'+i).click(function(){
			
			let q = $('#a'+i).val();
			console.log(q);
			console.log($('#keepingCK'+i).attr("href"));
			$.ajax({
				type:"get"
				,url:'/lms/checkKeeping' 												//RESTController 
				,data:{'lectureName': $('#a'+i).val(), 'studentId':'${sessionMemberId}'} //키워드 받는 데이터
				,success:function(check){
					
						
					console.log(check);
					ck=check;

				}
				
			});
			if(ck == false){
				
				return false;
			}
			
			
			
		});
   	   }

    </script>
</html>