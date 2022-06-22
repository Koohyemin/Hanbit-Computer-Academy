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
    <title>paymentStudent</title>
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
                    	<!-- 공통 index -->
                        <div class="sb-sidenav-menu-heading">
                            My Page</div>
                        <a class="nav-link" href="#">
                            <div class="sb-nav-link-icon"><i class="fas fa-columns"></i></div>
                            나의 정보
                        </a>

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
	                        <i class="fas fa-table me-1"></i>
	                        납부 증명서
	                    </div>
	                    <div class="card-body">
							<!-- 학생의 납입 리스트  -->
			               <table class="table">
			                   <thead>
			                       <tr>
			                           <th>납부자</th>
			                           <th>강좌이름</th>
			                           <th>납부금액</th>
			                           <th>납부일자</th>
			                           <th>출력하기</th>
			                       </tr>
			                   </thead>
			                   <tbody>
									<c:forEach var="r" items="selectRegistrationList">
										<tr>
											<td>${registration.studentId}</td>
											<td>${registration.lectureName}</td>
											<td>${registration.payment}원</td>
											<td>${registration.createDate}</td>
											<td><input type="button" id="btnPrint" value="출력하기"></a></td>
										</tr>
									</c:forEach>
			                   </tbody>
		              		 </table>

		              		 <div id="printTable">
		              		 <div>
		              		 <br>
		              		 <h1  style="text-align:center;">
		              		 취업을 목적으로 하는 IT전문학원<br>
		              		 [한빛 컴퓨터 아카데미] 
		              		 </h1>
		              		 <br>
		              		 </div>
				               <table style="width:100%;border:1px solid #000000;">
				                   <thead>
				                       <tr>
				                           <th style="border:1px solid #000000;">납부자</th>
				                           <th style="border:1px solid #000000;">강좌이름</th>
				                           <th style="border:1px solid #000000;">납부금액</th>
				                           <th style="border:1px solid #000000;">납부일자</th>
				                       </tr>
				                   </thead>
				                   <tbody>
										<c:forEach var="r" items="selectRegistrationList">
											<tr>
												<td style="border:1px solid #000000;">${registration.studentId}</td>
												<td style="border:1px solid #000000;">${registration.lectureName}</td>
												<td style="border:1px solid #000000;">${registration.payment}원</td>
												<td style="border:1px solid #000000;">${registration.createDate}</td>
											</tr>
										</c:forEach>
				                   </tbody>
			              		 </table>
			              		 <br>
			              		 <h2 style="text-align:center;">
			              		 위와 같이 강좌에 대한 납부를 완료하였음을 증명합니다.
			              		 </h2>
			              		 <br>
			              		<h6 style="text-align:center"; position:fixed; bottom: 0;">
			              		Hanbit Computer Academy<br>
								서울특별시 금천구 가산동 가산디지털2로 115 대륭테크노타운3차 1109-1호<br>
								Development1Team@Hanbit.co.kr</h6>
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
    <script>
    /** 프린트 버튼 클릭 시 이벤트 */
    $("#btnPrint").click(function () {
        let $container = $("#printTable").clone()    // 프린트 할 특정 영역 복사
        let cssText = ""                            // 스타일 복사
        for (const node of $("style")) {
            cssText += node.innerHTML
        }
        /** 팝업 */
        let innerHtml = $container[0].innerHTML
        let popupWindow = window.open("", "_blank", "width=700,height=800")
        popupWindow.document.write("<!DOCTYPE html>"+
          "<html>"+
            "<head>"+
            "<style>"+cssText+"</style>"+
            "</head>"+
            "<body>"+innerHtml+"</body>"+
          "</html>")
       
        popupWindow.document.close()
        popupWindow.focus()

        /** 잠깐 지연 */
        setTimeout(() => {
            popupWindow.print()         // 팝업의 프린트 도구 시작
            popupWindow.close()         // 프린트 도구 닫혔을 경우 팝업 닫기
        }, 0)
    })
</script>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
    <script src="../js/scripts.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
    <script src="../assets/demo/chart-area-demo.js"></script>
    <script src="../assets/demo/chart-bar-demo.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
    <script src="../js/datatables-simple-demo.js"></script>
</body>
</html>