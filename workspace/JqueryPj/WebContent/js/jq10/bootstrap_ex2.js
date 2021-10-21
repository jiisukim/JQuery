/**
 * 회원가입화면 JS
 */
$(document).ready(function() {
   $("#addrTb").on('dblclick', 'tbody tr', function() {
      var zipcode = $($(this).children()[0]).text();
      var addr = $($(this).children()[1]).text();
      $("#postCode").val(zipcode);
      $("#addr").val(addr);
      $("#zipModal").modal("hide");
   });
});



/*
 * [중복검사] 버튼 클릭 시 - ID 중복 검사
 * */
var num=0;		 // id제외 다른 정규표현식을 위한 카운트
var idCheck=0;	 // 중복검사 안했을 경우를 위한 카운트
function duplicateId(){
	// ajax 처리
	// 1.입력된 값이 있는지 확인
	// 2.입력된 값이 형식에 맞는지
	// -영문 소문자는 반드시 포함하고, 영문 소문자와 숫자의 조합으로 4~12 글자
	
	// 3.DB에서 중복된게 있는지
	var param = {
			userId : $("#userId").val()
			,flag : "CHKID"
	};
	var userId =$("#userId").val();	//정규식
	var userIdExp = /^(?=.*[a-z]).[0-9a-zA-Z]{3,12}$/;
	var msg = "\n빈 칸 없이 4~12글자. \n영문 소문자, 숫자 사용가능";
	
	if(check(userId,userIdExp,"userId","아이디",msg)){	//return
		return; //
	}
	
	console.log(param);
	$.ajax({
		url: "/JqueryPj/MemberServlet"
		,type: "post"
		,data: param
		,dataType: "json"
		,success: function(data){
			console.log(data);
			// data ==> {resultCnt: 0}
			if(data.resultCnt == 0){
				//alert("이미 사용중인 ID입니다.");
				$("#userIdSpan").removeClass("text-warning");
				$("#userIdSpan").text("사용 가능한 ID입니다.");
				$("#userIdSpan").addClass("text-success");
				idCheck++;
			}else{
				alert("이미 사용중인 ID입니다.");
				$("#userId").focus();
				$("#userIdSpan").text("이미 사용중인 ID입니다.");
				$("#userIdSpan").addClass("text-warning");
			}
		}
		,error: function(xhr){
			console.log(xhr);
			alert("오류입니다. 관리자에게 문의하세요.");
		}
	});
	
	
}


/*
 * [저장] 버튼 클릭 시 - 회원정보 저장
 * */
function save(){
	// ajax
	// id 중복검사 안했을 경우
	if(idCheck == 0){
		alert("아이디 중복검사를 해주세요.");
		return;
	}
	
	var userName =$("#name").val();	//이름 체크
	var nameExp = /^[가-힣]{2,5}$/;
	msg = "빈 칸 없이 한글 2~5글자";
	if(check(userName,nameExp,"name","이름",msg,"#nameSpan")){
		return;
	}
	
	
	var userbirth =$("#birth").val();//생년월일체크
	//10살이상만 가입가능
	var now = new Date();
    var nowYear = now.getFullYear();
    var userAge = userbirth.substr(0,4);
    userAge = (nowYear - userAge) + 1; 
    console.log(userAge);
	if(userAge <10 || userbirth == ""){
		document.getElementById("birth").focus();
		$("#birthSpan").text("10세 이상부터 가입 가능합니다.");
		$("#birthSpan").addClass("text-warning");
		num++;
	}else{
		$("#birthSpan").text("");
		$("#birthSpan").removeClass("text-warning");
	}
    
    
	var userPwd =$("#password").val();//비밀번호체크
	var pwExp =/^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\d~!@#$%^&*()+|=]{8,12}$/;
	msg = "빈 칸 없이 8~12글자. 영문 소문자, 대문자, 숫자, 특수문자 \n반드시 1글자씩 포함";
	if(check(userPwd,pwExp,"password","비밀번호",msg,"#pwdSpan")){
		return;
	}
	
	
	var pNum =$("#pNum").val();//휴대폰번호체크
	pNum = pNum.replace(/\-/g,"");
	var regExp = /^\d{3}\d{4}\d{4}$/;
	msg = "핸드폰 번호 형식을 준수해주세요.ex) 010-1234-5678 ";
	if(check(pNum,regExp,"pNum","휴대폰",msg,"#pNumSpan")){
		return;
	}
	
	var email =$("#email").val();//이메일 체크
	var emailExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/;
	msg = "이메일 형식을 준수해주세요";
	if(check(email,emailExp,"email","이메일",msg,"#emailSpan")){
		return;
	}
	
	
	var postCd = $("#postCode").val(); //우편번호
	var detailedAddress = $("#detailedAddress").val(); 	// 주소
	if(postCd == "" || detailedAddress == ""){
		num++;
		postSpan
		$("#postSpan").text("우편번호 및 주소를 입력해주세요.");
		$("#postSpan").addClass("text-warning");
	}else{
		$("#postSpan").text("");
		$("#postSpan").removeClass("text-warning");
	}
	
	
	
	console.log(num);
	if(num > 0){
		alert("형식을 체크해주세요");
		num=0;
		return;
	}else{
		alert("저장되었습니다.");
	}
}

//정규식 체크
function check(info,regExp,id,messageNM,messageInfo,spanId) {
	if(info.match(regExp)){
		$(spanId).text("");
		$(spanId).removeClass("text-warning");
		return;
	} else{
		document.getElementById(id).focus();
		$(spanId).text(messageNM+"은(는) 잘못된 형식입니다.\n" +messageInfo);
		$(spanId).addClass("text-warning");
		num++;
		return;
	}	
}



/*
 * [취소] 버튼 클릭 시 - 회원목록(이전)화면으로 돌아가기
 * */
function cancel(){
	// submit
}

/*
 * 우편번호 모달 창 [검색] 버튼 클릭 시 - 우편번호 목록 조회
 * */
function srchAddrList(){
	   // ajax
	   var param = {
	         dong : $("#dong").val()
	      
	   };
	   console.log(dong);
	   console.log(param);
	   $.ajax({
	      url: "/JqueryPj/ZipServlet"
	      ,type: "post"
	      ,data: param
	      ,dataType: "json"
	      ,success: function(data){
	         console.log(data);
	         makeTable(data);
	      }
	      ,error: function(xhr){
	         console.log(xhr);
	         alert("오류입니다. 관리자에게 문의하세요.");
	      }
	   });
	   
	   function makeTable(data){
	      $("#addrTb tbody").empty();
	      var strHtml = "";
	      
	      for(var i=0 ; i<data.length ; i++){
	         
	         var zipcode = data[i].zipcode;
	         var sido = data[i].sido;
	         var gugun = data[i].gugun;
	         var dong = data[i].dong;
	         var bunji = data[i].bunji;
	         console.log(bunji);
	         if(bunji == "null") {
	            bunji = "";
	         }
	         
	         strHtml += "<tr>"
	               + "<td>" + zipcode + "</td>"
	               + "<td>" + sido + gugun + dong + bunji + "</td>"
	               + "</tr>";
	      }
	      
	      if(data.length == 0){
	         strHtml = "<tr>"
	                + '<td colspan="2">검색 결과가 없습니다.</td>'
	                + "</tr>";
	      }
	      
	      $("#addrTb tbody").html(strHtml);
	   }
}

