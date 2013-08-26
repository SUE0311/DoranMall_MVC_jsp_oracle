$(document).ready(function(){
	
	//상품 이미지 세부 내용 보이기
	$('.description').hide();
	
	$('#product01>a').mouseenter(function(){
		$('#desc01').show();
	});
	$('#product02>a').mouseenter(function(){
		$('#desc02').show();
	});
	$('#product03>a').mouseenter(function(){
		$('#desc03').show();
	});
	
	//상품 이미지 세부 내용 감추기
	$('#product01>a').mouseleave(function(){
		$('#desc01').hide();
	});
	$('#product02>a').mouseleave(function(){
		$('#desc02').hide();
	});
	$('#product03>a').mouseleave(function(){
		$('#desc03').hide();
	});
	
	
	var obj=document.getElementById("thum");
	var anChor=obj.getElementsByTagName("a");
	var iMg=obj.getElementsByTagName("img");
	var large=document.getElementById("poster");

	for (i=0;i<anChor.length;i++ )
	{
	anChor[i].onclick=function(){

	for (var j=0;j<iMg.length;j++)
	{
	iMg[j].className="";//"on"클래스 적용된 모든 이미지 초기화
	};//end for

	this.firstChild.className="on";
	large.src=this.href;//poster id 요소를 src를 클릭된 a의 href 속성값으로 대입.
	return false;
	};//end onclick

	}//end for
	
})//end ready