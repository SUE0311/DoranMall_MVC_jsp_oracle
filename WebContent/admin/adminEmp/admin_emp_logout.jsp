<!-- 
# 작성자 : 이지수
# 작성일 : 2013.07.31 
-->

<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>관리자 페이지</title>

<link rel="stylesheet" type="text/css" href="./css/admin_main.css" />
<script src="./js/jquery.js"></script>

</head>
<body>

<div id="main_wrap">

<!-- header 영역 -->
<%
	//세션 객체 만료 시킴
	session.invalidate();
%>

<div id="header">
	
	<div id="header_logo">
		<a href="./adminIndex.do"><img src="./images/logo-doran.png" width="266" height="60" /></a>
	</div>

	<ul id="top_menu">
		<li><a href="./adminIndex.do">홈</a></li>
		<li><a href="#">관리자 등록 페이지</a></li>
	</ul>
</div>

<div class="clear"></div>

<!-- content(본문) 영역 -->
<div id="content_wrapper">

	<div id="left_menu">
		<p id="menu_title">관리자 전용</p>
		<ul>
			<li>회원 관리
				<ul>
					<a href="#"><li>- 고객 정보</li></a>
				</ul>
			</li>
			<li>입점 신청 기업 관리
				<ul>
					<a href="./admin_company_list.do"><li>- 기업 정보</li></a>
				</ul>			
			</li>
			<li>상품 관리
				<ul>
					<a href="./admin_product_list.do"><li>- 상품 목록</li></a>
					<a href="./admin_product_write.do"><li>- 상품 등록</li></a>
				</ul>
			</li>
			<li>쇼핑 가이드 관리
				<ul>
					<a href="./admin_guide_list.do"><li>- 가이드 목록</li></a>
					<a href="./admin_guide_write.do"><li>- 가이드 등록</li></a>
				</ul>
			</li>
		</ul>
	</div><!-- end left_menu -->
		
	<div id="content_c">
	
		<div id="loginArea">
		
			<p id="title">로그 아웃 되었습니다.</p>
			<br>
			<p align="center"><a href="./adminIndex.do">= 관리자 페이지 메인으로 이동 =</a></p>
			
		</div>
	
	</div><!-- end content_c -->
</div><!-- end content_wrapper -->

<div class="clear"></div>

<!-- footer 영역 -->
<div id="footer">
</div>

</div><!-- end main_wrap -->

</body>
</html>