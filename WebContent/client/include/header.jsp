<!-- 
# 작성자 : 이지수
# 작성일 : 2013.07.31 
-->

<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>도란도란몰</title>

<link rel="stylesheet" type="text/css" href="./css/main.css" />
<link rel="stylesheet" type="text/css" href="./css/subpage.css" />
<script src="./js/jquery.js"></script>

</head>
<body>

<div id="main_wrap">

<!-- header 영역 -->

<div id="header">
	<a href="./index.do"><img id="subLogo" src="./images/logo-doran.png" width="200" height="50" /></a>
	
	<div id="loginArea">
	
		<c:if test = "${!empty client_id}" >
			<table id="login_after">
		    	<tr>
	            	<td>
	          			반가워요! &nbsp;&nbsp;<span id="logName">${client_name}</span> 님 [ ID : ${client_id } ]
	          		</td>
	           		<td>
	            		<div><a href="./client_mypage.do">마이페이지</a></div>
	           		</td>
	           		<td>
	            		<div><a href="./client_logout_ok.do">로그아웃</a></div>
	           		</td>
		         </tr>
		     </table>
		</c:if>   
		
		<c:if test = "${empty client_id}" >
			<table id="login_beforeforheader">
		    	<td>
          			<div><a href="index.do">홈</a></div>
          		</td>
           		<td>
            		<a href="./client_login.do">로그인으로 이동</a>
           		</td>
		     </table>
		</c:if> 
		
      </div><!-- end loginArea  -->
</div>
