<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>addr</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
  <link rel="stylesheet" href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
  <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
  <script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
</head>

<body>
    </head>
    <body>
        <div id="wrapper">
        <div class="container">
            <h2>주소 검색</h2>
            <div id="target">
                <label for="Keyword">주소 : 
                </label>
                <div>
	                <input type="text" id="Keyword" name="Keyword" class="selectpicker form-control" placeholder="예) 서울특별시 금천구 가산동 가산디지털2로 115">
                </div>
                
                <div>
					<select id="addr" name="addr1" class="selectpicker form-control"></select>
                </div>
                <input type="button" id="clickme"  value="확인">
            </div>
        </div>
        </div>
	</body>
	<script>
	//주소검색 api사용 후 ajax처리
$(function(){
	$("#Keyword").autocomplete({        
		source : function( request, response ) {
			$.ajax({
				type:"get"
				,url:'/lms/searchAddr' //인터넷망
				,data:{'Keyword':$('#Keyword').val() } //키워드 받는 데이터
				,success:function(jsonStr){
					var jsonStr2 = JSON.parse(jsonStr);
					var arr = jsonStr2.results.juso; //주소배열
					console.log(arr);
					$('#addr').empty();	// select 초기화 부분
					//var obj = $.getElementById('gb');
					for(var i =0; i<arr.length; i++){
						//obj.options.add(new Option(arr[i].jibunAddr));
						$('#addr').append('<option>'+arr[i].jibunAddr +'</option>');
					}
				}
			});
		},
		minLength: 2,
		select: function( event, ui ) {
		// 만약 검색리스트에서 선택하였을때 선택한 데이터에 의한 이벤트발생
		}    
	});
})
	 window.onload = function(event){
	       document.getElementById("clickme").onclick = function(){
	           var addr = document.getElementById("addr").value;
	           window.opener.document.getElementById("Keyword").value = addr;
	           window.close();
	       }
	   }
	</script>
</html>