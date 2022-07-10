<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
<title>getLecOne</title>
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
	               	Lecture Information
	               	<a href="${pageContext.request.contextPath}/lec/lecList" class="btn btn-dark" style="float:right">이전으로</a>
	            </div>
	            <div class="card-body">
	            <button class="btn btn-dark btn-sm" style="float:right" id="btnPrint">🖨 출력하기</button>
	            <div class ="printView">
	            <h1 style="text-align:center;"> ${lec.lectureName} </h1>
					<table class="table">
					
						<tr>
							<th class="text-center">과목</th>
							<td>${lec.subjectName}</td>
						</tr>
						<tr>
							<th class="text-center">난이도</th>
							<td>${lec.difficulty}</td>
						</tr>
						<tr>
							<th class="text-center">담당 강사</th>
							<td>${lec.teacherName}</td>
						</tr>
						<tr>
							<th class="text-center">교육 기간</th>
							<td>${lec.beginClass} - ${lec.endClass}</td>
						</tr>
						<tr>
							<th class="text-center">교육 시간</th>
							<td>${lec.startTime} - ${lec.endTime} (점심시간 : ${lec.lunchTime})</td>
						</tr>
						<tr>
							<th class="text-center">수강인원</th>
							<td>${lec.registrationNumber}명</td>
						</tr>
						<tr>
							<th class="text-center">수강료</th>
							<td><fmt:formatNumber value="${lec.lecCost}" pattern="#,###" />원</td>
						</tr>
						<tr>
							<th class="text-center">수료 점수</th>
							<td>${lec.registrationPassScore}점</td>
						</tr>
						<tr>
							<th class="text-center">강의실</th>
							<td>${lec.lectureRoomName}</td>
						</tr>
						<tr>
							<th class="text-center">문의 연락처</th>
							<td>${fn:substring(lec.lecPhone,0,3)} - ${fn:substring(lec.lecPhone,3,7)} - ${fn:substring(lec.lecPhone,7,13)}</td>
						</tr>
					</table>
					 </div>
					<div>
					<!-- 운영자만 수정, 삭제 버튼을 볼 수 있음 -->
					<c:if test="${sessionMemberLv == 1}">
						<div class="d-grid gap-3">
							<a href="${pageContext.request.contextPath}/registration/addregistration?lectureName=${lec.lectureName}" id="btn" class="btn btn-dark btn-block">수강신청</a>
						</div>
					</c:if>
						<c:if test="${sessionMemberLv == 3}">
							<div class="btn-group" style="float:right">
								<!-- 수정버튼 -->
									<a href="${pageContext.request.contextPath}/lec/updateLec?lectureName=${lec.lectureName}" class="btn btn-dark">수정</a>
							</div>
						</c:if>
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
    	 $("#delBtn").click(function(){
             if (confirm('해당 강의를 삭제 하시겠습니까?')) {
                 $('#del').submit();
             } else {
             	return false;
             }
         });
    	 
   var lectureName='${lec.lectureName}';
	var list1 = new Array();

	<c:forEach var="i" items="${regiList}">
		 list1.push("${i.lectureName}");
	 </c:forEach> 
	console.log(list1);
	var count = 0;
	$("#btn").click(function(){
			for(var i=0; i<list1.length; i++){
				console.log(lectureName);
				if(lectureName == list1[i]){
					
				count++;
				console.log(count);
				}
			}
			
			if(count>0){
				console.log(count);
				alert('이미 수강 등록한 강좌입니다.');
				return false;
			}
		});
		
    /** 프린트 버튼 클릭 시 이벤트 */
    $("#btnPrint").click(function () {
        let $container = $(".printView").clone()    // 프린트 할 특정 영역 복사
        /** 팝업 */
        let innerHtml = $container[0].innerHTML
        let popupWindow = window.open("", "_paymentStudent", "width=700,height=800")
        popupWindow.document.write("<!DOCTYPE html>"
       		+"<html>"
          	+"<head>"
            +"<style>"
            +"table,td,th{border:1px solid #000000;  border-collapse : collapse;}"
            +"table{width:100%;}"
            +"</style>"
            +"</head>"
            +"<body>"
            +innerHtml
            + "<h6 style='text-align:center'; position:fixed; bottom: 0;>"
     		+ "Hanbit Computer Academy<br>"
			+ "서울특별시 금천구 가산동 가산디지털2로 115 대륭테크노타운3차 1109-1호<br>"
			+ "Development1Team@Hanbit.co.kr</h6>"
            +"</body>"+
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
    </script>
</html>