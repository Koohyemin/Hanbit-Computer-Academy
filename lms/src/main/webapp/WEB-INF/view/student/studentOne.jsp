<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>index</title>
    <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
    <link href="css/styles.css" rel="stylesheet" />
    <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
<style type="text/css">
a:link {
  color : black;
  text-decoration: none;
}
a:hover {
  color : red;
  text-decoration: none;
}
a:active {
  color : green;
  text-decoration: none;
}
</style>
</head>

<body class="sb-nav-fixed">

    <nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
        <!-- Sidebar Toggle-->
        <button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" id="sidebarToggle" href="#!"><i class="fas fa-bars"></i></button>
        <!-- Navbar Brand-->
        <a class="navbar-brand ps-3" href="${pageContext.request.contextPath}/index">Hanbit Computer Academy LMS</a>
        <!-- Navbar Search-->
        <form class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0">
        </form>
        <!-- Navbar-->
        <ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>
                <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                    <li><a class="dropdown-item" href="#">${sessionMemberId}</a></li>
                    <li><a class="dropdown-item" href="#">Settings</a></li>
                    <li>
                        <hr class="dropdown-divider" />
                    </li>
                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/logout">Logout</a></li>
                </ul>
            </li>
        </ul>
    </nav>
    <div id="layoutSidenav">
        <div id="layoutSidenav_nav">
            <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
                <div class="sb-sidenav-menu">
                    <div class="nav">
                    	<!-- 상세보기 분기 -->
                        <div class="sb-sidenav-menu-heading">
                            My Page</div>
                         
                        <c:if test="${sessionMemberLv == 1}">
	                        <a class="nav-link" href="${pageContext.request.contextPath}/getStudentOne">
	                            <div class="sb-nav-link-icon"><i class="fas fa-columns"></i></div>
	                            나의 정보
	                        </a>
                        </c:if>
                        <c:if test="${sessionMemberLv == 2}">
	                        <a class="nav-link" href="${pageContext.request.contextPath}/getTeacherOne">
	                            <div class="sb-nav-link-icon"><i class="fas fa-columns"></i></div>
	                            나의 정보
	                        </a>
                        </c:if>
                        <c:if test="${sessionMemberLv == 3}">
	                        <a class="nav-link" href="${pageContext.request.contextPath}/getManagerOne">
	                            <div class="sb-nav-link-icon"><i class="fas fa-columns"></i></div>
	                            나의 정보
	                        </a>
                        </c:if>

                   		<!-- 공통 index -->
                   		<c:if test="${sessionMemberLv == 1 }">
                   		<div class="sb-sidenav-menu-heading">Work</div>
                   		</c:if>
                   		<c:if test="${sessionMemberLv != 1 }">
                   		<div class="sb-sidenav-menu-heading">Work</div>
                   		</c:if>
                        <!-- 학생이라면 이걸 보여주기 -->
                        <c:if test="${sessionMemberLv == 1 }">
                        <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseLayouts" aria-expanded="false" aria-controls="collapseLayouts">
                            <div class="sb-nav-link-icon"><i class="fas fa-book-open"></i></div>
                            수강신청
                            <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                        </a>
                        <div class="collapse" id="collapseLayouts" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
                            <nav class="sb-sidenav-menu-nested nav">
                                <a class="nav-link" href="#">찜 목록</a>
                                <a class="nav-link" href="#">수강신청하기</a>
                                <a class="nav-link" href="#">수강신청내역</a>
                            </nav>
                        </div>
                        </c:if>
   
  						 <!-- 운영진이라면 이걸 보여주기 -->
                        <c:if test="${sessionMemberLv == 3 }">
                        <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseLayouts" aria-expanded="false" aria-controls="collapseLayouts">
                            <div class="sb-nav-link-icon"><i class="fas fa-book-open"></i></div>
                            관리
                            <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                        </a>
                        <div class="collapse" id="collapseLayouts" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
                            <nav class="sb-sidenav-menu-nested nav">
                                <a class="nav-link" href="#">회원승인</a>
                                 <a class="nav-link" href="#">학생/강사 관리</a>
                           		<a class="nav-link" href="#">강의계획서 관리</a>
                           		<a class="nav-link" href="#">납부 관리</a>
                                <a class="nav-link" href="#">개설강좌 관리</a>
                                <a class="nav-link" href="#">수강신청 관리</a>   
                            </nav>
                        </div>
                        </c:if>
                        
                        <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapsePages" aria-expanded="false" aria-controls="collapsePages">
                            <div class="sb-nav-link-icon"><i class="fas fa-book-open"></i></div>
                            강좌
                            <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                        </a>
                        <div class="collapse" id="collapsePages" aria-labelledby="headingTwo" data-bs-parent="#sidenavAccordion">
                            <nav class="sb-sidenav-menu-nested nav accordion" id="sidenavAccordionPages">
                                <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#pagesCollapseAuth" aria-expanded="false" aria-controls="pagesCollapseAuth">
                                        강의실
                                        <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                                    </a>
                                <div class="collapse" id="pagesCollapseAuth" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordionPages">
                                    <nav class="sb-sidenav-menu-nested nav">
                                        <a class="nav-link" href="#">강의실 홈</a>
	                                     <!-- 강사라면 이걸 보여주기 -->
				                         <c:if test="${sessionMemberLv != 1}">
				                          <a class="nav-link" href="#">수강학생조회</a>
				                         </c:if>
				                         <!--  강사index -->
                                        <a class="nav-link" href="#">강의계획서</a>
                                        <a class="nav-link" href="#">강의자료실</a>
                                        <a class="nav-link" href="#">공지사항</a>
                                        <a class="nav-link" href="#">과제게시판</a>
                                        <a class="nav-link" href="#">Q&A</a>
                                    </nav>
                                </div>
                                
                                <!-- 학생이거나 강사라면 이걸 보여주기 -->
                                <c:if test="${sessionMemberLv != 2}">
                                <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#pagesCollapseError" aria-expanded="false" aria-controls="pagesCollapseError">
                                        성적조회
                                        <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                                    </a>
                                <div class="collapse" id="pagesCollapseError" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordionPages">
                                    <nav class="sb-sidenav-menu-nested nav">
                                        <a class="nav-link" href="#">강의 평가 조회</a>
                                         <c:if test="${sessionMemberLv == 1}">
                                        <a class="nav-link" href="#">강의 평가하기</a>
                                        </c:if>
                                        <a class="nav-link" href="#">성적조회</a>
                                    </nav>
                                </div>
                                </c:if>
                                <!--  학생 운영진 index -->
                                
                                 <!-- 강사라면 이걸 보여주기 -->
		                         <c:if test="${sessionMemberLv == 2 }">
		                          <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#pagesCollapseError" aria-expanded="false" aria-controls="pagesCollapseError">
                                        조회
                                        <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                                    </a>
                                <div class="collapse" id="pagesCollapseError" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordionPages">
                                    <nav class="sb-sidenav-menu-nested nav">
                                        <a class="nav-link" href="#">강의 평가 조회</a>
                                        <a class="nav-link" href="#">성적조회</a>
                                    </nav>
                                </div>
		                         </c:if>
		                         <!--  강사index -->
                            </nav>
                        </div>
                        <c:if test="${sessionMemberLv == 1 }">
                        <div class="sb-sidenav-menu-heading">
                            certificate</div>
                        <a class="nav-link" href="#">
                            <div class="sb-nav-link-icon"><i class="fas fa-table"></i></div>
                            수료 증명서
                        </a>
                        <a class="nav-link" href="${pageContext.request.contextPath}/certificate/paymentStudent">
                            <div class="sb-nav-link-icon"><i class="fas fa-table"></i></div>
                            납입 증명서
                        </a>
                        </c:if>
                        <!-- 학생 index -->

                        
                        <!-- 공통 index -->
                        <div class="sb-sidenav-menu-heading">Communication</div>
                        <a class="nav-link" href="${pageContext.request.contextPath}/Notice/noticeList">
                            <div class="sb-nav-link-icon"><i class="fas fa-chart-area"></i></div>
                            공지사항
                        </a>
                        <a class="nav-link" href="#">
                            <div class="sb-nav-link-icon"><i class="fas fa-chart-area"></i></div>
                            문의 게시판
                        </a>
                        <a class="nav-link" href="${pageContext.request.contextPath}/faq/getFaqListByPage">
                            <div class="sb-nav-link-icon"><i class="fas fa-chart-area"></i></div>
                            FAQ
                        </a>
                        <!-- 공통 index -->
                    </div>
                </div>
            </nav>
        </div>
        <div id="layoutSidenav_content">
            <main>
                <div class="container-fluid px-4">
                    <!-- 컨텐츠 삽입 부분-->
                    <br>
	                <div class="card mb-4">
	                    <div class="card-header">
	                        <i class="fas fa-chart-area me-1"></i>
								나의 정보
	                    </div>
	                    <div class="card-body">
							<div class="row"> 
							
								<div class="col-lg-1"></div>
								
								<!-- 사진 -->
					        	<div class="col-lg-3">
					        	<br>
					           		<img src="${pageContext.request.contextPath}/assets/img/${photoFile.photoName}.${photoFile.photoType}" class="img-fluid" alt="" width="150" height="200">  <!-- 사진추가 -->
					        	</div>
					        	<!-- 사진 끝 -->
					        	
					        	<!-- 개인정보 -->
					        	<div class="col-lg-3">
						        	<br>
					        		<div>
			        					<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-caret-right-fill" viewBox="0 0 16 16"> <path d="m12.14 8.753-5.482 4.796c-.646.566-1.658.106-1.658-.753V3.204a1 1 0 0 1 1.659-.753l5.48 4.796a1 1 0 0 1 0 1.506z"/></svg>
			        					이름 : ${student.studentName}
					        		</div>
		        					<br>
			        				<div>
		        						<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-caret-right-fill" viewBox="0 0 16 16"> <path d="m12.14 8.753-5.482 4.796c-.646.566-1.658.106-1.658-.753V3.204a1 1 0 0 1 1.659-.753l5.48 4.796a1 1 0 0 1 0 1.506z"/></svg>
		        						아이디 : ${student.studentId}</li>
						        	</div>
		        					<br>
		        					<div>
				        				<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-caret-right-fill" viewBox="0 0 16 16"> <path d="m12.14 8.753-5.482 4.796c-.646.566-1.658.106-1.658-.753V3.204a1 1 0 0 1 1.659-.753l5.48 4.796a1 1 0 0 1 0 1.506z"/></svg>
										성별 : ${student.studentGender}
					        		</div>
				        			<br>
					        		<div>
				        				<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-caret-right-fill" viewBox="0 0 16 16"> <path d="m12.14 8.753-5.482 4.796c-.646.566-1.658.106-1.658-.753V3.204a1 1 0 0 1 1.659-.753l5.48 4.796a1 1 0 0 1 0 1.506z"/></svg>
										출생년도 : ${student.studentBirth}
					        		</div>
		        					<br>
			        				<div>
		        						<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-caret-right-fill" viewBox="0 0 16 16"> <path d="m12.14 8.753-5.482 4.796c-.646.566-1.658.106-1.658-.753V3.204a1 1 0 0 1 1.659-.753l5.48 4.796a1 1 0 0 1 0 1.506z"/></svg>
										연락처 : ${student.studentPhone}
					        		</div>
			        			</div>
			        			<div class="col-lg-4">
						        	<br>
					        		<div>
			        					<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-caret-right-fill" viewBox="0 0 16 16"> <path d="m12.14 8.753-5.482 4.796c-.646.566-1.658.106-1.658-.753V3.204a1 1 0 0 1 1.659-.753l5.48 4.796a1 1 0 0 1 0 1.506z"/></svg>
			        					주소 : ${student.studentAddr1}
					        		</div>
		        					<br>
			        				<div>
		        						<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-caret-right-fill" viewBox="0 0 16 16"> <path d="m12.14 8.753-5.482 4.796c-.646.566-1.658.106-1.658-.753V3.204a1 1 0 0 1 1.659-.753l5.48 4.796a1 1 0 0 1 0 1.506z"/></svg>
		        						상세주소 : ${student.studentAddr2}
						        	</div>
		        					<br>
		        					<div>
				        				<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-caret-right-fill" viewBox="0 0 16 16"> <path d="m12.14 8.753-5.482 4.796c-.646.566-1.658.106-1.658-.753V3.204a1 1 0 0 1 1.659-.753l5.48 4.796a1 1 0 0 1 0 1.506z"/></svg>
										최종학력 : ${student.finalEducation}
					        		</div>
				        			<br>
					        		<div>
				        				<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-caret-right-fill" viewBox="0 0 16 16"> <path d="m12.14 8.753-5.482 4.796c-.646.566-1.658.106-1.658-.753V3.204a1 1 0 0 1 1.659-.753l5.48 4.796a1 1 0 0 1 0 1.506z"/></svg>
										이메일 : ${student.studentEmail}
					        		</div>
			        			<!-- 개인정보 끝 -->
			        		</div>
		        		</div>
			        	</div>
			        	<br>
			        	<div class="row">
			        		<!-- 자격증 -->
			        		<div class="col-lg-6">
			                <div class="card mb-4">
			                    <div class="card-header">
			                        <i class="fas fa-chart-area me-1"></i>
										보유 자격증
			                    </div>
				        			<table class="table table-hover">
			        				<thead>
			        					<tr>
			        						<th class="text-center">자격증명</th>
			        						<th class="text-center">주관처</th>
			        						<th class="text-center">취득일</th>
			        					</tr>
			        				</thead>
			        				<tbody>
			        				<c:forEach var="c" items="${certificationList}">
			        					<tr>
			        						<td class="text-center">${c.certificationName}</td>
			        						<td class="text-center">${c.certificationIssued}</td>
			        						<td class="text-center">${c.getDate}</td>
			        					</tr>
			        				</c:forEach>
			        				</tbody>
			        			</table>
							</div>
				        	</div>
			        		<!-- 자격증 끝 -->
			        		<!-- 수강내역 -->
		        			<div class="col-lg-6">
		        			<div class="card mb-4">
			                    <div class="card-header">
			                        <i class="fas fa-chart-area me-1"></i>
										수강 목록
			                    </div>
			        			<table class="table table-hover">
			        				<thead>
			        					<tr>
			        						<th class="text-center">강좌</th>
			        						<th class="text-center">과목</th>
			        						<th class="text-center">개설날짜</th>
			        					</tr>
			        				</thead>
			        				<tbody>
			        				<c:forEach var="l" items="${lecList}">
			        					<tr>
			        						<td class="text-center"><a target='_blank' href="${pageContext.request.contextPath}/lec">${l.lectureName}</a></td><!-- 수강 상세보기로 이동 -->    						
			        						<td class="text-center">${l.subjectName}</td>
			        						<td class="text-center">${l.createDate}</td>
			        					</tr>
			        				</c:forEach>
			        				</tbody>
			        			</table>
			        		</div>
		        			</div>
		        		</div>
					</div>
           		</main>
            <footer class="py-4 bg-light mt-auto">
                <div class="container-fluid px-4">
                    <div class="d-flex align-items-center justify-content-between small">
                        <div class="text-muted">Copyright &copy; Hanbit Computer Academy 2022</div>
                    </div>
                </div>
            </footer>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
    <script src="js/scripts.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
    <script src="assets/demo/chart-area-demo.js"></script>
    <script src="assets/demo/chart-bar-demo.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
    <script src="js/datatables-simple-demo.js"></script>
</body>
</html>