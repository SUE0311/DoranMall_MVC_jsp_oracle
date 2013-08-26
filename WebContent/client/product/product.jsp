<!-- 
# 작성자 : 이지수
# 작성일 : 2013.08.07 
-->

<%@ page contentType="text/html; charset=UTF-8"%>

<%@ page import="com.product.dao.*" %>

<%@ include file="../include/header.jsp" %>

<link rel="stylesheet" type="text/css" href="./css/product.css" />
<script src="./js/product.js"></script>

<div class="clear"></div>

<div id="content_wrapper">

<%@ include file="../include/left_menu.jsp" %>

	<div id="content_c">

	<p id="title">도란도란 공동구매 샵 - 금주의 상품 [텀블러]</p>
	
	<div id="descPage">
		<p>도란도란을 사랑해주시는 고객 여러분!<br>
		   무더운 여름 건강하게 잘 지내시고 계시죠? 아무쪼록 무탈하게 이번 여름도 나시길 도란도란이 손 꼽아 기도해요!<br>
		  이번 주 도란도란 공동구매 샵에서는 무려 "130여분"이 희망 신청과 추천을 해주신 텀블러를 준비했습니다. <br> 
		   자랑스런 대한민국 중소기업을 사랑하는 도란도란답게 이번에도 열정적으로 일하는 기업 3곳을 선정했습니다.<br>
		  자! 이제 눈 크게 뜨시고 도란도란이 고객님들을 위해 준비한 즐거운 쇼핑~ 지금부터 고고싱하세요!!     	
		</p>
	</div>
	
	<div id="contentArea">
	
	<div class="products" id="product01">
		<a href="./product01.do?product_shop=1">
			<img src="./images/product01_main.png" alt="상품1" />
		</a>
		<a href="./product01.do?product_shop=1" class="description" id="desc01">
			얼스테얼스 "Windy Blossom"<br>
			판매가 28,000원
		</a>
	</div>
	
	<div class="products" id="product02">
		<a href="./product02.do?product_shop=2">
			<img src="./images/product02_main.png" alt="상품2" />
		</a>
		<a href="./product02.do?product_shop=2" class="description" id="desc02">
			카네이션 "헤라"<br>
			판매가 9,700원
		</a>
	</div>
	
	<div class="products" id="product03">
		<a href="./product03.do?product_shop=3">
			<img src="./images/product03_main.png" alt="상품3" />
		</a>
		<a href="./product03.do?product_shop=3" class="description" id="desc03">
			쓰임 "스마일 모던"<br>
			판매가 18,900원
		</a>
	</div>
	
	</div><!-- end contentArea -->

	</div><!-- end content_c -->
</div><!-- end content_wrapper -->

<div class="clear"></div>

<%@ include file="../include/footer.jsp" %>