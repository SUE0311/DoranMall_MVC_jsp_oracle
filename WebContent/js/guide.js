function test(){
	alert("나와랏!!");
}

function guide_check(){
	
	var mall01 = 0;
	var mall02 = 0;
	var mall03 = 0;
	
	var opt1_1 = document.getElementById("opt1_1");
	var opt1_2 = document.getElementById("opt1_2");
	var opt1_3 = document.getElementById("opt1_3");
	var opt2_1 = document.getElementById("opt2_1");
	var opt2_2 = document.getElementById("opt2_2");
	var opt2_3 = document.getElementById("opt2_3");
	var opt3_1 = document.getElementById("opt3_1");
	var opt3_2 = document.getElementById("opt3_2");
	var opt3_3 = document.getElementById("opt3_3");
	
	if(opt1_1.checked == true){
		mall01 = mall01 + 1;	
	}else if(opt1_2.checked == true){
		mall02 = mall02 + 1;	
	}else if(opt1_3.checked == true){
		mall03 = mall03 + 1;	
	}
	
	if(opt2_1.checked == true){
		mall01 = mall01 + 1;	
	}else if(opt2_2.checked == true){
		mall02 = mall02 + 1;	
	}else if(opt2_3.checked == true){
		mall03 = mall03 + 1;	
	}
	
	if(opt3_1.checked == true){
		mall01 = mall01 + 1;	
	}else if(opt3_2.checked == true){
		mall02 = mall02 + 1;	
	}else if(opt3_3.checked == true){
		mall03 = mall03 + 1;	
	}
	
//	alert(mall01);
//	alert(mall02);
//	alert(mall03);
	alert("mall01 = " + mall01 + ", mall02 = " + mall02 + ", mall03 = " + mall03);

	if(mall01 > mall02 && mall01 > mall03){
		alert("당신은 1번 제품이 어울리시네요. 첫번째 샵으로 바로 이동!");
		window.open('product01.do?product_shop=1');		
		/*history.go(-1);*/
	}else if(mall02 > mall01 && mall02 > mall01){
		alert("당신은 2번 제품이 어울리시네요.  첫번째 샵으로 바로 이동!");
		window.open('product02.do?product_shop=2');
	}else if(mall03 > mall01 && mall03 > mall02){
		alert("당신은 3번 제품이 어울리시네요");
		window.open('product03.do?product_shop=3');
	}
	
	if(mall01 == true && mall02 == true){
		alert("당신을 위해 특별히 준비한 추가 문제가 있어요. 추가 버튼을 눌러주세요");
//		document.getElementById("spareBtn").disabled=false;
//		$("#spareBtn").css("visibility","visible");
	}
};

/*function guide_check(){
	
	var $mall01 = 0;
	var $mall02 = 0;
	var $mall03 = 0;

	if($("#opt1_1").val() == true){
		$mall01 = $mall01 + 1;	
	}else if($("#opt1_2").val() == true){
		$mall02 = $mall02 + 1;	
	}else if($("#opt1_3").val() == true){
		$mall03 = $mall03 + 1;	
	}
	
	if($("#opt2_1").val() == true){
		$mall01 = $mall01 + 1;	
	}else if($("#opt2_2").val() == true){
		$mall02 = $mall02 + 1;	
	}else if($("#opt2_3").val() == true){
		$mall03 = $mall03 + 1;	
	}
	
	if($("#opt3_1").val() == true){
		$mall01 = $mall01 + 1;	
	}else if($("#opt3_2").val() == true){
		$mall02 = $mall02 + 1;	
	}else if($("#opt3_3").val() == true){
		$mall03 = $mall03 + 1;	
	}
	
	alert("mall01 = " + $mall01 + ", mall02 = " + $mall02 + ", mall03 = " + $mall03);

	if($mall01 > $mall02 && $mall01 > $mall03){
		alert("당신은 1번 제품이 어울리시네요. 첫번째 샵으로 바로 이동!");
		window.open('product01.do?product_shop=1');		
		history.go(-1);
	}else if($mall02 > $mall01 && $mall02 > $mall01){
		alert("당신은 2번 제품이 어울리시네요.  첫번째 샵으로 바로 이동!");
		window.open('product02.do?product_shop=2');
	}else if($mall03 > $mall01 && $mall03 > $mall02){
		alert("당신은 3번 제품이 어울리시네요");
		window.open('product03.do?product_shop=3');
	}
	
	if($mall01 == true && $mall02 == true){
		alert("당신을 위해 특별히 준비한 추가 문제가 있어요. 추가 버튼을 눌러주세요");
//		document.getElementById("spareBtn").disabled=false;
//		$("#spareBtn").css("visibility","visible");
	}
};*/

function spareQuiz(){
	$("#guideF_table").css("visibility","hidden");
	$("#spare_table").css("visibility","visible");
}

function spare_check(){
	if(opt4_1.checked == true){
		alert("당신은 1번 mall이 어울리시네요");
		window.open('product01.do?product_shop=1');	
	}else if(opt4_2.checked == true){
		alert("당신은 2번 mall이 어울리시네요");
		window.open('product02.do?product_shop=2');	
	}else if(opt4_3.checked == true){
		alert("당신은 3번 mall이 어울리시네요");	
		window.open('product03.do?product_shop=3');	
	}
}
