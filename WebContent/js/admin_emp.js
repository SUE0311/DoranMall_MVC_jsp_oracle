function id_check(){
	
	$("#idcheck").hide();//아이디 영역을 숨긴다.
	
	var memid=$("#admin_id").val();
	
	/*//1.입력글자 길이 체크
	if($.trim($("#mjjang_id").val()).length < 4){
		var newtext='<font color="red">아이디는 4자 이상이어야 합니다.</font>';
		$("#idcheck").text('');//아이디 영역을 초기화
		$("#idcheck").show();//아이디 영역을 보이게 한다.
		$("#idcheck").append(newtext);//idcheck 영역에 문자열을
		//추가
		$("#mjjang_id").val('').focus();
		return false;
	};
	if($.trim($("#mjjang_id").val()).length > 12){
		var newtext='<font color="red">아이디는 12자 이하이어야 합니다.</font>';
		$("#idcheck").text('');//아이디 영역을 초기화
		$("#idcheck").show();//아이디 영역을 보이게 한다.
		$("#idcheck").append(newtext);//idcheck 영역에 문자열을
		//추가
		$("#mjjang_id").val('').focus();
		return false;
	};
	//2.입력글자 확인
	if(!(validate_userid(memid))){//유효성검증
		var newtext='<font color="red">아이디는 영문소문자,숫자,_ 조합만 가능합니다.</font>';
		 $("#idcheck").text('');
		  $("#idcheck").show();
		  $("#idcheck").append(newtext);
		  $("#mjjang_id").val('').focus();
		  return false;
	};*/
	
	
	//아이디 중복확인
    $.ajax({//$는  jQuery. 즉 jQuery로 아작스를 실행
        data: {"check_need_id":memid},  //memid피라미터에 변수값을 저장해서넘김
        url:"./admin/adminEmp/admin_idcheck.jsp", //서버 파일 주소 와 경로 
        
        //type:"post",//자료를 보내는 법
        //datatype:"post",//서버 타입을 post 타입으로 받아옴.
        success: function (data) {//서버 결과값(html)있으면 안돼~ 오직 결과값만  data변수에 저장
      	  if(data==1){//중복 이면
      		  /*alert(data);*/
      		var newtext='<font color="red">중복 아이디입니다.</font>';
      		$("#idcheck").text('');
        		$("#idcheck").show();
        		$("#idcheck").append(newtext);
          		$("#admin_id").val('').focus();
          		return false;
	     
      	  }else{//중복이 아니면
      		/*alert(data);*/
      		var newtext='<font color="blue">사용가능한 아이디입니다.</font>';
      		$("#idcheck").text('');
      		$("#idcheck").show();
      		$("#idcheck").append(newtext);
      		$("#admin_pass").focus();
      	  }  	    	  
        },
    	  error:function(){
    		  alert("data error");
    	  }
      });//$.ajax	
};

function pwd_find(){
	window.open('pwd_find.do','','width=500,height=350,top=100,left=200,location=yes,scrollbars=no');	
};

/*비밀번호 찾기 유효성 평가*/
function pwd_check(){
	if($.trim($("#pwd_id").val())==""){
		alert("아이디를 입력하세요");
		$("#pwd_id").val("").focus();
		return false;
	}
	//비밀번호 입력 여부
	else if($.trim($("#pwd_name").val())==""){
		alert("이름을 입력하세요");
		$("#pwd_name").val("").focus();
		return false;
	}
}

function pwd_close(){
	window.close();
}