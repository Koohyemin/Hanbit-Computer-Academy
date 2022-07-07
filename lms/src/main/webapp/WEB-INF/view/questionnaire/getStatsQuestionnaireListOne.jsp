<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<title>getStatsQuestionnaireListOne</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js"></script>
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
				<span id="lectureName"></span>
				<a class="btn btn-dark btn-sm float-end" href="${pageContext.request.contextPath}/questionnaire/getStatsQuestionnaireList">뒤로가기</a>
			</div>
		    <div class="card-body">
			     <div class="container-fluid px-4">
			     	<div class="row">
						<div class="col-lg-4">
							<div class="row">
			     				<canvas id="myBarChart" style="width:100%;height:600px; max-width:600px; "></canvas>
				        	</div>
						</div>
						<div class="col-lg-8">
							<div class="row">
								<div class="chartBox">
					 				<canvas id="myChart"></canvas>
								</div>
							</div>
							<br>
							<div class="text-center">
								<div class="btn-group">
								<button id="downBtn" type="button" class="btn btn-dark">◀</button>
								<button id="upBtn" type="button" class="btn btn-dark">▶</button>
								</div>
							</div>
						</div>
					</div>
					<br>
				</div>
			</div>
		</div>
	</div>
	<div id="footer"></div>
	</div>
</div>
</body>
	<script>
    	
	// 과목번호와 항목값 가져옴
	var num = ${registrationNo} ;
	var checknum= ${checknum}
	
	// 실행이 되면 기본으로 1번문항을 보여줌
    	$(document).ready(function() {
    		console.log(num); // 과목번호 체크
    		console.log(checknum); //  문항번호 체크
    		let arr; // json 데이터를 집어넣을 공간 
    		let lectureName; // 과목이름 
    		let questionnaireContent; // 항목 테스트
    		var selectedEvaluationNo = []; // 선택된 점수 항목  1~5까지 
    		var people = []; // 점수를 체크한 사람의 인원수 
		    	$.ajax({
		    		type:'get'
		    		, url:'${pageContext.request.contextPath}/questionnaire/checkQueScore'
		    		, dataType: "json"
		    		,async: false
		    		,success:function(jsonData) {
						console.log(jsonData);
						var jsonArr = JSON.stringify(jsonData);
			            console.log(jsonArr);
			            
			            for(key in jsonData){
			                lectureName = jsonData[key].lectureName;
			                questionnaireContent = jsonData[key].questionnaireContent;
			                selectedEvaluationNo.push(jsonData[key].selectedEvaluationNo +"점");
			                people.push(jsonData[key].people);
			            	}
			            console.log("고른 점수" + selectedEvaluationNo);
			            console.log("참여인원" + people);
			            
				    	$('#lectureName').text(lectureName);


				    	// 마우스를 올릴 때 이전의 차트 데이터가 남아있어서 이슈 해결을 위한 차트 초기화
				    	$('#myChart').remove(); // 캔버스 날리기   
				    	$('.chartBox').append('<canvas id="myChart"></canvas>');  //바차트 안의 캔버스 다시 만들기
				    	
				    	
				    	
				    	// 항목별 차트 생성하기
				    	var barColors = [
				    	  "#65C7C6"
				    	  ,"#FEDF6B"
				    	  ,"#F58F76"
				    	  ,"#F17086"
				    	  ,"#D5E03C"
				    	];

				    	chart = new Chart("myChart", {
				    	  type: "doughnut",
				    	  data: {
				    	    labels: selectedEvaluationNo,
				    	    datasets: [{
				    	      backgroundColor: barColors,
				    	      data: people
				    	    }]
				    	  },
				    	  options: {
				    	    title: {
				    	      display: true,
				    	      text: checknum +'번쨰 질문 : ' + questionnaireContent
				    	    }
				    	  }
				    	});
		    		}
   				});
    		
    			// 카테고리별 차트
    		
		    	var xValues = ${categoryList};
		    	var yValues = ${categoryScoreList};
		    	var barColors = ["#65C7C6", "#FEDF6B","#F58F76","#F17086","#D5E03C"];

		    	new Chart("myBarChart", {
		    	  type: "bar",
		    	  data: {
		    	    labels: xValues
		    	    ,datasets: [{
		    	      backgroundColor: barColors,
		    	      data: yValues
		    	    }]
		    	  },
		    	  options: {
		    	    legend: {display: false}
		    	    ,title: {
		    	      display: true,
		    	      text:  lectureName + "의 카테고리별 점수"
		    	    }
		    	    ,scales: {
		    	    	 yAxes: [{
		    	            display: true
		    	            ,ticks: {
		    	                suggestedMin: 0
		    	                ,beginAtZero: true  
		    	            }
		    	        }]
		    	    }
		    	  }
		    	});	
	    });	
    	
		// 문항 업 버튼을 누르면 다음 번째 문항 
    	$('#upBtn').click(function() {
    		checknum = checknum +1;
    		// 만약 뒤로가기를 눌렀는데 0번쨰 문항이 선택퇴면 20번째 문항으로 교채
    		if(checknum == 21){
    			checknum = 1;
    		}
    		console.log(num); // 과목번호 체크
    		console.log(checknum); //  문항번호 체크
    		let arr; // json 데이터를 집어넣을 공간 
    		let lectureName; // 과목이름 
    		let questionnaireContent; // 항목 테스트
    		var selectedEvaluationNo = []; // 선택된 점수 항목  1~5까지 
    		var people = []; // 점수를 체크한 사람의 인원수 
    		$.ajax({
	    		type:'get'
	    		,url:'${pageContext.request.contextPath}/questionnaire/checkQueScore'
	    		, data : {
	    			num : num
	    			,checknum :checknum
	    		}
	    		,success:function(jsonData) {
					console.log(jsonData);
					var jsonArr = JSON.stringify(jsonData);
		            console.log(jsonArr);
		            
		            for(key in jsonData){
		                lectureName = jsonData[key].lectureName;
		                questionnaireContent = jsonData[key].questionnaireContent;
		                selectedEvaluationNo.push(jsonData[key].selectedEvaluationNo +"점");
		                people.push(jsonData[key].people);
		            	}
		            console.log("고른 점수" + selectedEvaluationNo);
		            console.log("참여인원" + people);
			    	$('#lectureName').text(lectureName);

			    	// 마우스를 올릴 때 이전의 차트 데이터가 남아있어서 이슈 해결을 위한 차트 초기화
			    	$('#myChart').remove(); // 캔버스 날리기   
			    	$('.chartBox').append('<canvas id="myChart"></canvas>');  //바차트 안의 캔버스 다시 만들기
			    	
			    	var barColors = [
			    		 "#65C7C6"
				    	  ,"#FEDF6B"
				    	  ,"#F58F76"
				    	  ,"#F17086"
				    	  ,"#D5E03C"
			    	];

			    	new Chart("myChart", {
			    	  type: "doughnut",
			    	  data: {
			    	    labels: selectedEvaluationNo,
			    	    datasets: [{
			    	      backgroundColor: barColors,
			    	      data: people
			    	    }]
			    	  },
			    	  options: {
			    	    title: {
			    	      display: true,
			    	      text: checknum+'번쨰 질문 : ' + questionnaireContent
			    	    }
			    	  }
			    	});
	    		}

			});
		})
		
		
		// 문항 다운 버튼을 누르면 이전 번쨰 문항 
		$('#downBtn').click(function() {
    		checknum = checknum -1;
    		// 만약 뒤로가기를 눌렀는데 0번쨰 문항이 선택퇴면 20번째 문항으로 교채
    		if(checknum == 0){
    			checknum = 20;
    		}
    		console.log(num); // 과목번호 체크
    		console.log(checknum); //  문항번호 체크
    		let arr; // json 데이터를 집어넣을 공간 
    		let lectureName; // 과목이름 
    		let questionnaireContent; // 항목 테스트
    		var selectedEvaluationNo = []; // 선택된 점수 항목  1~5까지 
    		var people = []; // 점수를 체크한 사람의 인원수 
    		$.ajax({
	    		type:'get'
	    		,url:'${pageContext.request.contextPath}/questionnaire/checkQueScore'
	    		, data : {
	    			num : num
	    			,checknum :checknum
	    		}
	    		,success:function(jsonData) {
					console.log(jsonData);
					var jsonArr = JSON.stringify(jsonData);
		            console.log(jsonArr);
		            
		            for(key in jsonData){
		                lectureName = jsonData[key].lectureName;
		                questionnaireContent = jsonData[key].questionnaireContent;
		                selectedEvaluationNo.push(jsonData[key].selectedEvaluationNo +"점");
		                people.push(jsonData[key].people);
		            	}
		            console.log("고른 점수" + selectedEvaluationNo);
		            console.log("참여인원" + people);
			    	$('#lectureName').text(lectureName);    	
			    	
			    	// 마우스를 올릴 때 이전의 차트 데이터가 남아있어서 이슈 해결을 위한 차트 초기화
			    	$('#myChart').remove(); // 캔버스 날리기   
			    	$('.chartBox').append('<canvas id="myChart"></canvas>');  //바차트 안의 캔버스 다시 만들기
			    	
			    	var barColors = [
			    		 "#65C7C6"
				    	  ,"#FEDF6B"
				    	  ,"#F58F76"
				    	  ,"#F17086"
				    	  ,"#D5E03C"
			    	];

			    	new Chart("myChart", {
			    	  type: "doughnut",
			    	  data: {
			    	    labels: selectedEvaluationNo,
			    	    datasets: [{
			    	      backgroundColor: barColors,
			    	      data: people
			    	    }]
			    	  },
			    	  options: {
			    	    title: {
			    	      display: true,
			    	      text: checknum + '번쨰 질문 : ' + questionnaireContent
			    	    }
			    	  }
			    	});
	    		}

			});
		})


    	$('#nav').load('${pageContext.request.contextPath}/include/nav.jsp');
    	$('#navbar').load('${pageContext.request.contextPath}/include/navBar.jsp');
    	$('#footer').load('${pageContext.request.contextPath}/include/footer.jsp');
   	</script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
    <script src="../js/scripts.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
</html>