<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


    
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

<!-- 	<input type="text" name="resultType" value="json"/> <-- 요청 변수 설정 (검색결과형식 설정, json) --> 
<!-- 	<input type="text" name="confmKey" value="U01TX0FVVEgyMDIyMDYxNjE2MzExNTExMjY5ODQ="/>요청 변수 설정 (승인키) -->
<!-- 	<input type="button" onClick="getAddr();" value="주소검색하기"/>-->
<!--<input type="text" name="currentPage" value="1"/> <!-- 요청 변수 설정 (현재 페이지. currentPage : n > 0) -->
<!--<input type="text" name="countPerPage" value="10"/><!-- 요청 변수 설정 (페이지당 출력 개수. countPerPage 범위 : 0 < n <= 100) -->
	<h1>회원 가입</h1>

<form  method="post" action="/Hanbit/addMember">						<!-- 전체 회원 가입 폼 -->
	<table border="1">
	<tr>
	<td>아이디<input type="text" name=id> <button type="button" id="idCheck">중복검사</button></td>
	</tr>
	<tr>
	<td>비번<input type="text" name=pw id="pw"></td>
	</tr>
	<tr>
	<td>이름<input type="text" name=name>  </td>
	</tr>
	<tr>
	<td>주소 검색<input type="text" name="Keyword" id="Keyword"><!-- onkeydown="enterSearch(); 요청 변수 설정 (키워드) -->
	<!--<table id="list" border="1"></table>
		<a href="" id="list"></a>  -->
	<button id="addrBtn" type="button">전송</button></td>
	</tr>
	<tr>
	<td><span><select id="gb" name="addr1"></select><!-- 검색 결과 리스트 출력 영역 --></span></td>
	</tr>
	<tr>
	<td>상세 주소<input type="text" name=addr2></td>
	</tr>
	<tr>
	<td>전번<input type="text" name=phone></td>
	</tr>
	<tr>
	<td>이메일<input type="text" name=email></td>
	</tr>
	<tr>
	<td>생년월일<input type="text" name=birth></td>
	</tr>
	<tr>
	<td>성<input type="text" name=gender></td>
	</tr>
	<tr>
	<!-- 라디오버튼 선택 후에 최종학력받을지 결정-->
	
	<td>최종학력<input type="text" name=finalEdu> </td>
	</tr>
	</table>
	<button type="submit" id="formCheck">전송</button>
</form>
</body>
<script>

	let Num = /^[0-9]+$/;
	let Eng = /[a-zA-Z]/;
	
	console.log(Num);
	
	$('#addrBtn').click(function(){											//주소검색 api사용 후 ajax처리
		console.log($('#keyword').val());
		$.ajax({
			type:"get"
			,url:'/Hanbit/searchAddr' //인터넷망
			,data:{'Keyword':$('#Keyword').val()}					//키워드 받는 데이터
			,success:function(jsonStr){
				var jsonStr2 = JSON.parse(jsonStr);
				var arr = jsonStr2.results.juso;		//주소배열
				console.log(arr);
				console.log($('#keyword').val());
/* 				for(var i =0; i<arr.length; i++){
					$('#list').append('<div>'+arr[i].jibunAddr+'</div>');
				} */
				var obj = document.getElementById('gb');
				for(var i =0; i<arr.length; i++){
					obj.options.add(new Option(arr[i].jibunAddr));
				}
			}
		});
	});
	$('#idCheck').click(function(){
		$.ajax({
			type:"get"
			,url:'/Hanbit/compMember' 
			,success:function(memberStr){
				var memberStr2 = JSON.parse(memberStr);
				var arr = memberList
				console.log(memberList);

			}
		});
	});
	$('document').ready(function(){
		$('#formCheck').click(function(){
		/*	if($('#pw').val().length < 8) {
				alert('pw 8자이상');
				return false;
				//$('#idHelper').text('pw는 8자이상');
				$('#pw').focus();
			} else {*/
				if(!/^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d@$!%*#?&]{8,}$/.test($('#pw').val())) {						//a-z , or 숫자포함 자바스크립트 비밀번호 정규식
					alert("영문, 숫자 혼용하여 8자리 이상 입력하세요.");
					$('#pw').focus();
					return false;
				}
		});
	});
</script>
</html>