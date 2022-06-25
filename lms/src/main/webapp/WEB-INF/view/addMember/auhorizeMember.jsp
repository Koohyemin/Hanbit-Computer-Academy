<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<meta property="og:title" content="한빛컴퓨터아카데미LMS">
<meta property="og:url" content="lms/login">
<meta property="og:image" content="${pageContext.request.contextPath}/img/previewer.png">
<title>paymentStudent</title>
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
                        회원 승인
                    </div>
                    <div class="card-body">
						<form  method="post" action="/lms/authorizeMember">
							 <table class="table">
								<thead>
									<tr>
										<th>아이디</th>
										<th>신청일</th>
										<th>등급</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="c"  items="${waitingList}">
										<tr>
											<td>${c.memberId}</td>		
											<td>${c.createDate}</td>
											<td>${c.memberLevel}</td>
											<td>${c.meberState}</td>
											<td>
												<input type="checkbox" name="approvalCk" value="${c.memberId}" id="ck">
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<button id="btn" class="btn btn-dark" type="submit">승인</button>
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

</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
<script src="../js/scripts.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
<script src="assets/demo/chart-area-demo.js"></script>
<script src="assets/demo/chart-bar-demo.js"></script>
<script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
<script src="js/datatables-simple-demo.js"></script>
<script>
    	$('#btn').click(function(){													//버튼 클릭 유효성
    		if($(':checkbox[name="approvalCk"]:checked').length ==0){
    			console.log($(':checkbox[name="approvalCk"]:checked').length);	
    			alert("승인 멤버를 선택해주세요");
    			return false;
    			
    	} else{
    		console.log($(':checkbox[name="approvalCk"]:checked').length);	
    		$('#btn').subtmit();
    	}
    	
    	});
</script>	
</html>