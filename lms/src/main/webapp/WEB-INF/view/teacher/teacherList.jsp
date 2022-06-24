<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>addFaq</title>
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
                Teacher
            </div>         
            <br>
            <form method="get" action="${pageContext.request.contextPath}/teacher/teacherList">
	            <div class="container float-end">
		            <div class="col-lg-4 col-4 float-end btn-group">
	            		<!-- 검색어가 있다면 검색어가 유지되도록 value값 셋팅 -->
			           	<input type="text" name="searchValue" class="form-control" value="${searchValue}" placeholder="강사 이름을 입력해주세요">
		           		<div class="col-lg-2 col-2 float-end btn-group">
				      		<button id="btn" type="submit" class="btn btn-dark float-end">검색</button>
		           		</div>
		            </div>
	            </div>
            </form>
            <br>
            <!-- 강사소개 -->
            <div class="container">
             <h3>Teacher Information</h3> <br>
            <!-- 강사가 1명 이상일 시, teachList 출력 -->
         		<c:choose>
         			<c:when test="${listSize > 0}">
	         			<c:forEach var="m" items="${teacherList}">
				            <div class="col-lg-6 col-md-12 col-10">
								<div class="card">
								<div class="row">
									<div class="col-5">
									<!-- 사진 -->
									<div>
										<img src="${pageContext.request.contextPath}/assets/img/${m.photoName}" class="img-fluid">
									</div>
									
									</div>
										<div class="col-7">
											<div>
											<br>
												<div>
													<!-- 강사 이름 -->
													<h4>${m.teacherName}</h4>
													<ul>
														<!-- 강사 이메일 -->
														<li><span>이메일</span><div>✉ <span>${m.teacherEmail}</span></div></li>
														<!-- 담당 수업 -->
														<!-- 담당 수업이 없다면 없음으로 표시 -->
														<c:choose>
															<c:when test="${m.lecPlanName eq '' || m.lecPlanName == null}">
																<li><span>강의</span><div>🖥 <span>강의가 없습니다.</span></div></li>
															</c:when>
															<c:otherwise>
																<li><span>강의</span><div>🖥 <span>${m.lecPlanName}</span></div></li>
															</c:otherwise>
														</c:choose>
														<!-- 보유 자격증 -->
														<c:choose>
															<c:when test="${m.certificationName eq '' || m.certificationName == null}">
																<li><span>자격증</span> <div>📑 <span>보유 자격증이 없습니다.</span></div></li>
															</c:when>
															<c:otherwise>
																<li><span>자격증</span> <div>📑 <span>${m.certificationName}</span></div></li>
															</c:otherwise>
														</c:choose>
													</ul>
												</div>
											</div>
										</div>
									</div>
								</div>
								<br>
							</div>
			            </c:forEach>
         			</c:when>
         			<c:otherwise>
         				<br><br>
         				<!-- 일치하는 강사가 없거나, 강사목록이 존재하지 않을 경우 -->
	            		<div class="text-primary">강사가 존재하지 않습니다.</div>
	            		<br><br>
         			</c:otherwise>
         		</c:choose>
            </div>
            </div>
			</div>
        	</div>
				<div id="footer"></div>
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
    
    <script type="text/javascript">
    $( document ).ready(function(){
    	$( "#btn" ).trigger( "click" );	
    }
    </script>
</html>