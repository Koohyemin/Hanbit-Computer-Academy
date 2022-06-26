<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
      <script>
         window.onload = function(){
             document.getElementById("newwin").onclick = function(){
                 window.open("addr","","width=600px,height=400px,top=200px;");
             }

         };
     </script>
    </head>
    <body>
    <h1>업데이트</h1>
    <form method="post" action="${pageContext.request.contextPath}/member/modifyMember">
		<table border="1">
			<tr>
				<th>학생 이름</th>
				<td>
					<input type="text" name="memberName" id="memberName" placeholder="아이디를 입력하세요" value="${student.studentName}">
				 </td>
			</tr>
			<tr>
				<th>학생 아이디</th>
				<td>${student.studentId}</td>
			</tr>
			<tr>
				<th>주소</th>
				<td>
					<input tpye="text" id="Keyword" name="addr" value="${student.studentAddr1}" readonly="readonly">
					<input id="newwin" type="button" name="" value="새창열기">
				</td>
			</tr>
			<tr>
				<th>상세주소</th>
				<td>
					<input tpye="text" name="addr2" value="${student.studentAddr2}">
				</td>
			</tr>
			<tr>
				<th>핸드폰</th>
				<td>
					<input type="text" name="phone" placeholder="연락처를 입력하세요" value="${student.studentPhone}">
				</td>
			</tr>
			<tr>
				<th>생일</th>
				<td>${student.studentBirth}</td>
			</tr>
			<tr>
				<th>성별</th>
				<td>${student.studentGender}</td>
			</tr>
			<tr>
				<th>최종학력</th>
				<td>
					<select	name="finalEdu">
						<option value="고졸">고등학교 졸업</option>
						<option value="초대졸">전문대 졸업</option>
						<option value="학사">대학교 졸업</option>
						<option value="석사">대학원 졸업</option>
						<option value="박사">박사</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>이메일</th>
				<td>
					<input type="text" name="email" placeholder="연락처를 입력하세요" value="${student.studentEmail}">
				</td>
			</tr>
		</table>
		<button type="submit">수정</button>
	 </form>
	</body>
</html>