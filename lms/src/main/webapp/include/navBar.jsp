<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
    <link href="css/styles.css" rel="stylesheet" />
    <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
</head>
 <div id="layoutSidenav_nav">
<nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
    <div class="sb-sidenav-menu">
        <div class="nav">
        	<!-- 공통 index -->
            <div class="sb-sidenav-menu-heading">
                My Page</div>
            <c:choose>
            	<c:when test="${sessionMemberLv == 1}">
            		<a class="nav-link" href="${pageContext.request.contextPath}/student/getStudentOne">
            	</c:when>
            	<c:when test="${sessionMemberLv == 2}">
            		<a class="nav-link" href="${pageContext.request.contextPath}/getTeacherOne">
            	</c:when>
             	<c:when test="${sessionMemberLv == 3}">
            		<a class="nav-link" href="${pageContext.request.contextPath}/getManagerOne">
            	</c:when>           	
            </c:choose>
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
                    <a class="nav-link" href="${pageContext.request.contextPath}/authorizeMember">회원승인</a>
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
                            <a class="nav-link" href="${pageContext.request.contextPath}/questionnaire/getQuestionnaireList">강의 평가하기</a>
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
            <a class="nav-link" href="${pageContext.request.contextPath}/notice/noticeList">
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
</html> 