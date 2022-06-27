<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                        <i class="fas fa-table me-1"></i>
                        납부 증명서
                        <input type="button" id="btnPrint" value="납부증명서 출력하기" class="btn btn-dark">
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
		                       </tr>
		                   </thead>
		                   <tbody>
		                   	<c:forEach var="m" items="${selectRegistrationList}">
									<tr>
										<td>${m.studentName}</td>
										<td>${m.lectureName}</td>
										<td><fmt:formatNumber value="${m.payment}" pattern="#,###" />원</td>
										<td>${m.createDate}</td>
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
			                   <c:forEach var="m" items="${selectRegistrationList}">
									<tr>
										<td style="border:1px solid #000000;">${m.studentName}</td>
										<td style="border:1px solid #000000;">${m.lectureName}</td>
										<td style="border:1px solid #000000;">
										<fmt:formatNumber value="${m.payment}" pattern="#,###" />원 </td>
										<td style="border:1px solid #000000;">${m.createDate}</td>
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
		<div id="footer"></div>
	</div>
</div>
</body>
	<script>
    	$('#nav').load('${pageContext.request.contextPath}/include/nav.jsp');
    	$('#navbar').load('${pageContext.request.contextPath}/include/navBar.jsp');
    	$('#footer').load('${pageContext.request.contextPath}/include/footer.jsp');

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
    <script src="assets/demo/chart-area-demo.js"></script>
    <script src="assets/demo/chart-bar-demo.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
    <script src="js/datatables-simple-demo.js"></script>
</html>