<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
	
</script>
<title>Insert title here</title>
</head>
<body>
	<h1>회원 가입</h1>
<form name="form" id="form" method="get" action="/Hanbit/searchAddr">
	<input type="text" name="currentPage" value="1"/> <-- 요청 변수 설정 (현재 페이지. currentPage : n > 0) -->
	<input type="text" name="countPerPage" value="10"/><!-- 요청 변수 설정 (페이지당 출력 개수. countPerPage 범위 : 0 < n <= 100) -->
	<input type="text" name="resultType" value="json"/> <-- 요청 변수 설정 (검색결과형식 설정, json) --> 
	<input type="text" name="confmKey" value="U01TX0FVVEgyMDIyMDYxNjE2MzExNTExMjY5ODQ="/><!-- 요청 변수 설정 (승인키) -->
	<input type="text" name="keyword" value="" onkeydown="enterSearch();"/><!-- 요청 변수 설정 (키워드) -->
	<input type="button" onClick="getAddr();" value="주소검색하기"/>
	<div id="list" ></div><!-- 검색 결과 리스트 출력 영역 -->
	<button id="btn" type="button">전송</button>
</form>
</body>
<script>
$('#btn').click(function()  {
	$.ajax({
		type:"get"
		,url:'/Hanbit/searchAddr' //인터넷망
		,success:function(jsonStr){
			var jsonStr2 = JSON.parse(jsonStr);
			var arr = jsonStr2.results.juso;		//주소배열
			console.log(arr);
			for(var i =0; i<arr.length; i++){
				$('#list').append('<div>'+arr[i].jibunAddr+'</div>');
			}
		}
	});
});

</script>
</html>