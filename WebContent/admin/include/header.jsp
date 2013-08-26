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
String adminId = (String)session.getAttribute("id");
String adminName = (String)session.getAttribute("admin_name");
String adminAuth = (String)session.getAttribute("admin_auth");
%>
<div id="header">
	
	<div id="header_logo">
		<a href="./adminIndex.do"><img src="./images/logo-doran.png" width="266" height="60" /></a>
	</div>

	<!-- <ul id="top_menu">
		<li><a href="./adminIndex.do">홈</a></li>
		<li><a href="#">관리자 페이지</a></li>
		<li><a href="#">로그아웃</a></li>
	</ul> -->
	
	<ul id="top_menu">
	<%
		if(adminId != null){
	%>	
		<li id="logName"><%=adminName %> 님 [ ID : <%=adminId %> ]</li>
		<li><a href="./adminIndex.do">홈</a></li>
		<li><a href="./admin_cont_ok.do">마이 페이지</a></li>
		<li><a href="./admin_logout_ok.do">로그아웃</a></li>
	<%}else{ %>
		<li><a href="./adminIndex.do">홈</a></li>
		<li><a href="#">관리자 등록 페이지</a></li>
	<%} %>	
	</ul>
	
	<%-- <ul id="top_menu">
		<li id="logName"><%=adminName %> 님 [ ID : <%=adminId %> ]</li>
		<li><a href="./adminIndex.do">홈</a></li>
		<li><a href="./admin_cont_ok.do">마이 페이지</a></li>
		<li><a href="./admin_logout_ok.do">로그아웃</a></li>
	</ul> --%>
</div>