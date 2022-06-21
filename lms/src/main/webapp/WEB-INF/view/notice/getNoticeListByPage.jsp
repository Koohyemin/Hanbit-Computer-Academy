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
    <title>Notice</title>
    <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
    <link href="../css/styles.css" rel="stylesheet" />
    <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>

<body class="sb-nav-fixed">
    <nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
        <!-- Sidebar Toggle-->
        <button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" id="sidebarToggle" href="#!"><i class="fas fa-bars"></i></button>
        <!-- Navbar Brand-->
        <a class="navbar-brand ps-3" href="${pageContext.request.contextPath}/IndexStudent">Hanbit Computer Academy LMS</a>
        <!-- Navbar Search-->
        <form class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0">
        </form>
        <!-- Navbar-->
        <ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>
                <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                    <li><a class="dropdown-item" href="#">누구누구 님</a></li>
                    <li><a class="dropdown-item" href="#">Settings</a></li>
                    <li>
                        <hr class="dropdown-divider" />
                    </li>
                    <li><a class="dropdown-item" href="#">Logout</a></li>
                </ul>
            </li>
        </ul>
    </nav>
    <div id="layoutSidenav">
        <div id="layoutSidenav_nav">
            <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
                <div class="sb-sidenav-menu">
                    <div class="nav">
                        <div class="sb-sidenav-menu-heading">
                            My Page</div>
                        <a class="nav-link" href="#">
                            <div class="sb-nav-link-icon"><i class="fas fa-columns"></i></div>
                            나의 정보
                        </a>
                        <div class="sb-sidenav-menu-heading">Study</div>
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
                                        <a class="nav-link" href="#">강의계획서</a>
                                        <a class="nav-link" href="#">강의자료실</a>
                                        <a class="nav-link" href="#">공지사항</a>
                                        <a class="nav-link" href="#">과제제출</a>
                                        <a class="nav-link" href="#">Q&A</a>
                                    </nav>
                                </div>
                                <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#pagesCollapseError" aria-expanded="false" aria-controls="pagesCollapseError">
                                        성적조회
                                        <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                                    </a>
                                <div class="collapse" id="pagesCollapseError" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordionPages">
                                    <nav class="sb-sidenav-menu-nested nav">
                                        <a class="nav-link" href="#">강의 평가 조회</a>
                                        <a class="nav-link" href="#">강의 평가하기</a>
                                        <a class="nav-link" href="#">성적조회</a>
                                    </nav>
                                </div>
                            </nav>
                        </div>

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
                        <div class="sb-sidenav-menu-heading">Communication</div>
                        <a class="nav-link" href="#">
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
                    </div>
                </div>
            </nav>
        </div>
        <div id="layoutSidenav_content">
        <main>
        <div class="container-fluid px-4">
        <br>
        <!-- 컨텐츠 삽입 부분-->
			<h1>공지사항</h1>
			<a href="${pageContext.request.contextPath}/Notice/addNotice" class="btn btn-dark" style="float:right">공지 작성</a>		
			<br><br>
			<!-- 전체, 학생, 강사 구분 탭 -->
				<!-- 1학생 2강사 3관리자 -->
				<!-- 학생에게는 전체공지와 학생공지를 보여준다. -->
				<ul class="nav nav-tabs">
					<li class="nav-item">
						<c:choose>
							<c:when test="${category eq '전체'}">
								<a class="nav-link active" data-toggle="tab" href="${pageContext.request.contextPath}/Notice/noticeList?category=전체">전체</a> <!-- 기본값이 전체로 잡혀있기 때문에 현제 페이지로 이동하도록 조건을 잡아놓음 -->
							</c:when>
							<c:otherwise>
							    <a class="nav-link" data-toggle="tab" href="${pageContext.request.contextPath}/Notice/noticeList?category=전체">전체</a>
							</c:otherwise>
						</c:choose>
					</li>
					<li class="nav-item">
						<c:choose>
							<c:when test="${category == '강사'}">
							    <a class="nav-link active" data-toggle="tab" href="${pageContext.request.contextPath}/Notice/noticeList?category=강사">강사</a>
							</c:when>
							<c:otherwise>
								<a class="nav-link" data-toggle="tab" href="${pageContext.request.contextPath}/Notice/noticeList?category=강사">강사</a>
							</c:otherwise>
						</c:choose>
					</li>
					<li class="nav-item">
						<c:choose>
							<c:when test="${category eq '학생'}">
							    <a class="nav-link active" data-toggle="tab" href="${pageContext.request.contextPath}/Notice/noticeList?category=학생">학생</a>
							</c:when>
							<c:otherwise>
								<a class="nav-link" data-toggle="tab" href="${pageContext.request.contextPath}/Notice/noticeList?category=학생">학생</a>
							</c:otherwise>
						</c:choose>
					</li>
				</ul>
			<table class="table table-hover">
				<thead>
					<tr>
						<th class="text-center">번호</th>
						<th class="text-center">대상</th>
						<th class="text-center">제목</th>
						<th class="text-center">작성자</th>
						<th class="text-center">작성일</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="notice" items="${list}">
						<tr>
							<td class="text-center">${notice.managerNoticeNo}</td>
							<td class="text-center">${notice.category}</td>
							<td class="col-md-4"><a href="${pageContext.request.contextPath}/Notice/noticeOne?managerNoticeNo=${notice.managerNoticeNo}">${notice.managerNoticeTitle}</a></td>
							<td class="text-center">${notice.managerId}</td>
							<td class="text-center">${notice.createDate}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="text-center">
				<c:if test="${currentPage>1}">
					<a href="${pageContext.request.contextPath}/Notice/noticeList?currentPage=${currentPage-1}&&category=${category}" class="btn btn-dark">이전</a>
				</c:if>
				<c:if test="${lastPage>currentPage}">
					<a href="${pageContext.request.contextPath}/Notice/noticeList?currentPage=${currentPage+1}&&category=${category}" class="btn btn-dark">다음</a>
				</c:if>
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

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
    <script src="../js/scripts.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
    <script src="../assets/demo/chart-area-demo.js"></script>
    <script src="../assets/demo/chart-bar-demo.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
    <script src="../js/datatables-simple-demo.js"></script>
</body>
</html>