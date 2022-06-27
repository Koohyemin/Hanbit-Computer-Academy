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
<style>
	#sp{
		color:white;
		text-align:center;
	}
</style>
</head>
<body class="bg-dark">
   <div class="layoutAuthentication">
      <div class="layoutAuthentication_content">
         <div class="container">
            <c:if test="${error eq null}">
               ${error}
            </c:if>
         <br><br><br>
         <h1 class="text-center text-white">Hanbit Computer Academy LMS</h1>
         <br>
         <div id="sp">휴면 계정입니다. 비밀번호를 바꿔주세요.</div>
         <div class="row justify-content-center">
            <div class="col-lg-4">
               <br>
 
                <form  id="findPw" method="post" action="/lms/activeMember">
                   <div class="form-group">
                     <input type="password" id="pw" class="form-control"  placeholder="비밀번호 입력" name="memberPw">
                   </div>
                   <div class="form-group">
                     <input type="password" id="pwCk" class="form-control"  placeholder="비밀번호 확인" name="pwCheck">

                   </div>
                    <button id="btn" class="btn btn-primary btn-block" type="submit" >비밀번호 바꾸기</button>
                </form>

                 <br>
             
                  <a class="btn btn-light btn-block" role="button" href="${pageContext.request.contextPath}/login">Login Page</a>
            </div>
         </div>
      </div>
   </div>
</div>
<div class="waveSvg">
                 <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" style="z-index:1;position:relative" width="3000" height="100" viewBox="300 0 500 50">
                <g transform="translate(100, 50) scale(1, 1) translate(-100, -50)">
                                <defs>
               <linearGradient id="linear" x1="200%" y1="0%" x2="100%" y2="0%">
                  <stop offset="0%" stop-color="#a1d0ff"/><stop offset="100%" stop-color="#398afa"/>
               </linearGradient>
            </defs>
                                <path d="" fill="url(#linear)" opacity="0.4" >
                <animate attributeName="d" dur="20s" repeatCount="indefinite" keyTimes="0;0.333;0.667;1" calcmod="spline" keySplines="0.2 0 0.2 1;0.2 0 0.2 1;0.2 0 0.2 1" begin="0s" values="M0 0L 0 20Q 213.5 60 427 30T 1700 55L 1700 0 Z;M0 0L 0 45Q 213.5 60 427 40T 1700 30L 1700 0 Z;M0 0L 0 65Q 213.5 35 427 65T 1700 30L 1700 0 Z;M0 0L 0 20Q 213.5 60 427 30T 1700 55L 1700 0 Z"></animate>
            </path>
            <path d="" fill="url(#linear)" opacity="0.4" >
                <animate attributeName="d" dur="20s" repeatCount="indefinite" keyTimes="0;0.333;0.667;1" calcmod="spline" keySplines="0.2 0 0.2 1;0.2 0 0.2 1;0.2 0 0.2 1" begin="-10s" values="M0 0L 0 35Q 213.5 80 427 50T 1700 60L 1700 0 Z;M0 0L 0 50Q 213.5 20 427 20T 1700 40L 1700 0 Z;M0 0L 0 45Q 213.5 25 427 50T 1700 65L 1700 0 Z;M0 0L 0 35Q 213.5 80 427 50T 1700 60L 1700 0 Z"></animate>
            </path>
                </g>
            </svg>
   </div>
<script>
   //학생
  /*  $('#btnstudent').click(function(){
   if($('#studentId').val() == '') {
	      $('.helpId').text('ID를 입력하세요');
	   } else {
	      $('.helpId').text('');
	   }
   if($('#studentName').val() == '') {
      $('.helpName').text('이름을 입력하세요');
   } else {
      $('.helpName').text('');
   }
   if($('#studentPhone').val() == '') {
      $('.helpPhone').text('연락처를 입력하세요');
      } else {
         $('.helpPhone').text('');
      }
      if($('#studentId').val() != '' && $('#studentName').val() != '' && $('#studentPhone').val() != '') {
         $("#findPw").submit();
      }
   });
   //강사
   $('#btnteacher').click(function(){
   if($('#teacherId').val() == '') {
	   $('.helpId').text("ID를 입력하세요");
    	} else {
    		$('.helpId').text('');
    	} 	 
   if($('#teacherName').val() == '') {
      $('.helpName').text('이름을 입력하세요');
   } else {
      $('.helpName').text('');
   }
   if($('#teacherPhone').val() == '') {
      $('.helpPhone').text('연락처를 입력하세요');
      } else {
         $('.helpPhone').text('');
      }
      if($('#teacherId').val() != '' && $('#teacherName').val() != '' && $('#teacherPhone').val() != '') {
         $("#findPw").submit();
      }
   });
   //운영자   
   $('#btnmanager').click(function(){
   if($('#managerId').val() == '') {
	   $('.helpId').text("ID를 입력하세요");
   		} else {
	  		$('.helpId').text('');
   		}	 
   if($('#managerName').val() == '') {
      $('.helpName').text('이름을 입력하세요');
   } else {
      $('.helpName').text('');
   }
   if($('#managerPhone').val() == '') {
      $('.helpPhone').text('연락처를 입력하세요');
      } else {
         $('.helpPhone').text('');
      }
      if($('#managerId').val() != '' && $('#managerName').val() != '' && $('#managerPhone').val() != '') {
         $("#findPw").submit();
      }
   }); */
</script>
</body>
</html>