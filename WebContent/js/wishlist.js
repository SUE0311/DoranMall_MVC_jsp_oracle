 
/*# 작성자 : 이지수
# 작성일 : 2013.08.01 */

function write_check(){
	if($.trim($("#wishlist_id").val())==""){
		alert("아이디를 입력하세요");
		$("#wishlist_id").val("").focus();
		return false;
	}
	else if($.trim($("#wishlist_pass").val())==""){
		alert("비밀번호를 입력하세요");
		$("#wishlist_pass").val("").focus();
		return false;
	}
	else if($.trim($("#wishlist_title").val())==""){
		alert("희망상품을 입력하세요");
		$("#wishlist_title").val("").focus();
		return false;
	}
	/*else if($("#wishlist_content").text().length() == 0){ //textarea 유효성검사
		alert("신청 이유를 입력하세요");
		$("#wishlist_content").val("").focus();
		return false;
	}*/
	else if($("#wishlist_content").val()==""){ //textarea 유효성검사
		alert("신청 이유를 입력하세요");
		$("#wishlist_content").val("").focus();
		return false;
	}
}

/*추천하기 Ajax*/
function add_good(){
	
	var num = $("#wishlist_num").val(); //게시물 번호
	var good = $("#wishlist_good").val(); //기존 데이터베이스에 있는 추천수
	
	$.ajax({
        data: {"add_wishlist_good":good, "wishlist_num":num}, 
        url:"./client/wishlist/add_good.jsp", 
        
        //type:"post",//자료를 보내는 법
        //datatype:"post",//서버 타입을 post 타입으로 받아옴.
        success: function (data) {  	
      	  if(data==1){//수정을 성공했으면
      		var newtext = parseInt(good) + 1; //문자열 변수를 계산하기 위해 Int형으로 변환
      		var message = "추천하기는 1회만^^"
      			$("#result").text('');
      			$("#goodNumber").hide();
        		$("#result").show();
        		$("#result").append(newtext);
        		
        		$("#message").text('');
        		$("#message").show();
        		$("#message").append(message);
        		
        		$('#goodBtn').attr("disabled", true);
          		return false;
      	  }else{//수정을 실패했으면
      		var newtext='<font color="blue">실패</font>';
      		$("#goodNumber").hide();
    		$("#result").show();
    		$("#result").append(newtext);
      	  }  	    	  
        },
    	  error:function(){
    		  alert("ajax를 다시 공부하자!!!");
    	  }
      });//$.ajax
}
