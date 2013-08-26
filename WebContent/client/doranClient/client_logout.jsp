<!-- 
# 작성자 : 이지수
# 작성일 : 2013.07.31 
-->

<!-- 
# 작성자 : 이지수
# 작성일 : 2013.08.07 
-->
	
<%@ page contentType="text/html; charset=UTF-8"%>

<%
	//세션 객체 만료 시킴
	session.invalidate();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>도란도란몰</title>

<link rel="stylesheet" type="text/css" href="./css/main.css" />
<link rel="stylesheet" type="text/css" href="./css/subpage.css" />
<script src="./js/jquery.js"></script>
<script src="./js/client.js"></script>

<link rel="stylesheet" type="text/css" href="./css/client.css" />

</head>
<body>

<div id="main_wrap">

<!-- header 영역 -->
<div id="header">
	<a href="./index.do"><img id="subLogo" src="./images/logo-doran.png" width="200" height="50" /></a>

	<div id="loginArea">
		<table id="login_beforeforheader">
    		<td>
        		<div><a href="index.do">홈</a></div>
        	</td>
         	<td>
          		<a href="./client_login.do">로그인으로 이동</a>
         	</td>
    	 </table>
	</div><!-- end loginArea  -->
		
</div><!-- end header  -->

<div class="clear"></div>

<div id="content_wrapper">

<%@ include file="../include/left_menu.jsp" %>

	<div id="content_c">

	<div id="contentArea">
	
		<div id="byeArea">
	
		<p id="title">로그아웃 하셨습니다. <br> 고객님! 오늘도 좋은 하루 보내세요!</p>

			<img src="./images/Bye.gif" ><br><br><br>
			<a href="./index.do">메인 페이지로 이동을 원하시면 클릭해주세요</a>
		</div>
	
	</div><!-- end contentArea -->

	</div><!-- end content_c -->
</div><!-- end content_wrapper -->

<div class="clear"></div>

<%@ include file="../include/footer.jsp" %>