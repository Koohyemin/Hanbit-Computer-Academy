<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta property="og:title" content="한빛컴퓨터아카데미LMS">
<meta property="og:url" content="lms/login">
<meta property="og:image" content="${pageContext.request.contextPath}/img/previewer.png">
<title>findPw</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body class="bg-dark">
   <div class="layoutAuthentication">
      <div class="layoutAuthentication_content">
         <div class="container">
            <c:if test="${error eq null}">
               ${error}
            </c:if>
         <br><br><br>
         <h1 class="text-center text-white">JOIN Hanbit Computer Academy LMS</h1>
         <br>
			<div class="row justify-content-center">
				<div class="col-lg-6">
					<form  method="post" action="/lms/addMember">		
					<!-- 전체 회원 가입 폼 -->				
						<table class="table table-dark" >
							<tr>
								<td>
									<div class="input-group">
										<input type="text" name="id" id="memberId" class="form-control" placeholder="아이디를 입력하세요" value="">
										<button type="button" id="idCheck" class="btn btn-primary">중복검사</button>
									</div>
								</td>
							</tr>
							<tr>
								<td>
									<input type="password" name="pw" id="pw" class="form-control" placeholder="패스워드를 입력하세요">
								</td>
							</tr>
							<tr>
								<td>
									<input type="password" name="pwCk" id="pwCk" class="form-control" placeholder="패스워드를 재입력하세요">
								</td>
							</tr>
							<tr>
							<td><input type="text" name="name" id="name" class="form-control" placeholder="이름을 입력하세요"></td>
							</tr>
							<tr>
								<td>
									<div class="form-row">
										<div class="col text-white">
											<input type="text" class="onlyNumber form-control"  name="pid1" placeholder="생년월일 6자리">
										</div>
										-
										<div class="col">
											<input type="text" class="onlyNumber form-control" name="pid2" maxlength="1"  placeholder="주민등록번호 뒷자리 첫번째"> 
										</div>
									</div>
								</td>
							</tr>
							<tr>	
								<td>
									<div class="input-group">
										<input type="text" name="Keyword" id="Keyword" class="form-control" placeholder="주소를 입력하세요">
										<button id="addrBtn" type="button" class="btn btn-primary">전송</button>
									</div>
								</td>
							</tr>
							<tr>
								<td>
									<select id="addr" name="addr1" class="selectpicker form-control"></select>
								</td>				<!-- 주소 검색 결과 리스트 출력 영역 -->
							</tr>
							<tr>
								<td>
								<input type="text" name="addr2" class="form-control" placeholder="상세주소를 입력하세요">
								</td>
							</tr>
							<tr>
								<td>
									<input type="text" name="phone" id="phone" class="form-control" placeholder="연락처를 입력하세요" >
								</td>	
							</tr>
							<tr>
							<td>이메일<input type="email" name="email" id="email" class="form-control" placeholder="이메일을 입력하세요"></td>
							</tr>
							<tr>
								<td>
									<input type="radio" name="level" value="1">학생
									<input type="radio" name="level" value="2">선생
									<input type="radio" name="level" value="3">운영자
								</td>
							</tr>
							<tr>
								<!-- 라디오버튼 선택 후에 최종학력받을지 결정-->
								<td id="fEud" >최종학력
								<select name="finalEdu">
									<option value="고졸">고등학교 졸업</option>
									<option value="초대졸">전문대 졸업</option>
									<option value="학사">대학교 졸업</option>
									<option value="석사">대학원 졸업</option>
									<option value="박사">박사</option>
								</select>
								</td>
							</tr>
						</table>
						<button type="submit" id="formCheck" class="btn btn-primary btn-block">가입하기</button>
						<a class="btn btn-light btn-block" role="button" href="${pageContext.request.contextPath}/login">다음에 가입하기</a>
					</form> 
				</div>
			</div>
		</div>
	</div>
</div>
</body>
<script>

	$('#fEud').hide();
	// 첫 입력은 memberId 부터
	$('#memberId').focus();

	$( "#memberId" ).blur(function() {
		if($('#memberId').val() ==''){
			$( "#memberId" ).focus();
		}else{
			$('#idCheck').focus();
		}
	});
	
	//id 중복 검사 비동기 처리
	var idCheck='false';
	$('#idCheck').click(function(){	// <-- 최종 버튼 클릭 때도 이것으로 확인한다.
		$.ajax({
			type:"get" // get방식
			,url:'/lms/compMember'											
			,data:{'idCheck':$('#memberId').val()}							
			,success:function(member){
				console.log(member);
				console.log(idCheck);
				idCheck=member;
				console.log(idCheck);
				if(idCheck=="false"){
					alert("중복된 아이디 입니다");
				} else{
					$('#memberId').attr("readonly",true);
		 			alert("사용가능한 아이디 입니다");

				}
			}
		});
	
	// pw영문, 숫자 혼용하여 8자리
	$( "#pw" ).blur(function() {
		if(!/^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d@$!%*#?&]{8,}$/.test($('#pw').val())) {					
			alert("영문, 숫자 혼용하여 8자리 이상 입력하세요.");
			return false;
		}
	});
	
	// pw pwCK 비교
	$( "#pwCk" ).blur(function() {
		if($('#pw').val() != $('#pwCk').val() ){ 
			alert("입력하신 비밀번호가 다릅니다.");
			return false;
		}
	});
	
	
	// 생년월일 받는 폼에서 text 타입 숫자만 받게
	// == onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"
	$(".onlyNumber").keyup(function(event){						       
	    var inputVal = $(this).val();
	    $(this).val(inputVal.replace(/[^0-9]/g,''));
	});
	
	//주소검색 api사용 후 ajax처리
	$('#addrBtn').click(function(){											
		console.log($('#keyword').val());
		$.ajax({
			type:"get"
			,url:'/lms/searchAddr' //인터넷망
			,data:{'Keyword':$('#Keyword').val()} //키워드 받는 데이터
			,success:function(jsonStr){
				console.log(jsonStr);
				var jsonStr2 = JSON.parse(jsonStr);
				console.log(jsonStr2);
				var arr = jsonStr2.results.juso; //주소배열
				console.log(arr);
				//console.log($('#keyword').val());
				
				/* 				

				 for(var i =0; i<arr.length; i++){
					$('#list').append('<div>'+arr[i].jibunAddr+'</div>');				<!--<table id="list" border="1"></table>
																		<a href="" id="list"></a>  -->
					} 
				*/
				//var obj = document.getElementById('addr');
				
				$('#addr').empty();	// select 초기화 부분
				//var obj = $.getElementById('gb');
				for(var i =0; i<arr.length; i++){
					//obj.options.add(new Option(arr[i].jibunAddr));
					$('#addr').append('<option>'+arr[i].jibunAddr +'</option>');
				}
			}
		});
	});
	

	// 사용자 상태를 선택하는 폼에서 radio 값이 바뀌면 학적 숨기는 이벤트
	$("input[name='level']").change(function(){										
		var level = $("input[name='level']:checked").val();			//<--id로 수정
			alert(level);			
		if(level == 3){
			$('#fEud').hide();
		} else{
			$('#fEud').show();
		}
	});

	
	// 최종제출 전 유효성 검사		
	$('#formCheck').click(function(){ 														
			
		// idcheck 안 고친상태면 안된다.		
		if(idCheck == 'false'){		
			alert("id중복확인하세요");
			$('#pw').focus();
			return false;
		}
		//비밀번호 유효성 검사 ( a-z or 숫자포함 자바스크립트 비밀번호 정규식)
		else if(!/^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d@$!%*#?&]{8,}$/.test($('#pw').val())) {					
			alert("영문, 숫자 혼용하여 8자리 이상 입력하세요.");
			$('#pw').focus();
			return false;
		}		
		else if($('#pw').val() != $('#pwCk').val() ){ // 비밀번호 비밀번호 확인과 비교
			alert("입력하신 비밀번호가 다릅니다.");
			$('#pwCk').focus();
			return false;
		}
		else if($('#name').val() == ''){ // 이름 유효성 검사
			alert("이름 써주세요");
			$('#name').focus();
			return false;
		}
		else if($('#phone').val().length != 13){ // 전화번호 13자리 유효성 검사
			alert("전번 정확히 써주세요");
			$('#phone').focus();
			return false;
		}
		else if($('#email').val().length < 1 || $('#email').val()==''){	// 이메일 
			alert("이메일 써주세요");
			$('#email').focus();
			return false;
		}
		else if($(':radio[name="level"]:checked').length < 1){
		    alert('level 선택해주세요');                        
		    return false;
		}
			/* 				
				--> type="eamail로 대체"
				else if(/^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i.test($('email').val())){			
				alert("이메일 정확히 써주세요");
				return false;ㄷ
				} 
		    */
		else {
			$('#formCheck').submit();
		}
		});
	});

	<!-- 	<input type="text" name="resultType" value="json"/> <-- 요청 변수 설정 (검색결과형식 설정, json) --> 
	<!-- 	<input type="text" name="confmKey" value="U01TX0FVVEgyMDIyMDYxNjE2MzExNTExMjY5ODQ="/>요청 변수 설정 (승인키) -->
	<!-- 	<input type="button" onClick="getAddr();" value="주소검색하기"/>-->
	<!--<input type="text" name="currentPage" value="1"/> <!-- 요청 변수 설정 (현재 페이지. currentPage : n > 0) -->
	<!--<input type="text" name="countPerPage" value="10"/><!-- 요청 변수 설정 (페이지당 출력 개수. countPerPage 범위 : 0 < n <= 100) -->
</script>
</html>