//빈칸 작성 체크
function apply_check(){
	//회사이름 입력 여부
	if($.trim($("#com_co").val())==""){
		alert("회사 이름을 입력하세요");
		$("#com_co").val("").focus();
		return false;
	}
	//담당자이름 입력 여부
	else if($.trim($("#com_name").val())==""){
		alert("담당자 이름을 입력하세요");
		$("#com_name").val("").focus();
		return false;
	}//연락처 입력 여부
	else if($.trim($("#com_tel").val())==""){
		alert("연락처를 입력하세요");
		$("#com_tel").val("").focus();
		return false;
	}//이메일 입력 여부
	else if($.trim($("#com_mail").val())==""){
		alert("이메일을 입력하세요");
		$("#com_mail").val("").focus();
		return false;
	}//상품명 입력 여부
	else if($.trim($("#com_product").val())==""){
		alert("상품 명을 입력하세요");
		$("#com_product").val("").focus();
		return false;
	}//가격 입력 여부
	else if($.trim($("#com_price").val())==""){
		alert("가격을 입력하세요");
		$("#com_price").val("").focus();
		return false;
	}//상품설명
	else if($.trim($("#com_cont").val())==""){
		alert("상품 설명을 입력하세요");
		$("#com_cont").val("").focus();
		return false;
	}else{
		var message = confirm("귀 사의 입점 신청서를 제출하시겠습니까?");
		if(message == true){
			$("#applyForm").submit(); //신청서 제출
			window.open('client/contact/applyPopup.jsp','','width=720,height=650,top=100,left=200,location=yes,scrollbars=no');
		}else{
			$('#com_co').focus(); //회사이름에 포커스
		}
	}
}


/*function getEmail()
{
	window.open('client/contact/applyPopup.jsp','','width=700,height=600,top=100,left=200,location=yes');
}*/
