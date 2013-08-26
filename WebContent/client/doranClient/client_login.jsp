<!-- 
# 작성자 : 이지수
# 작성일 : 2013.07.31 
-->

<!-- 
# 작성자 : 이지수
# 작성일 : 2013.08.16 
-->
	
<%@ page contentType="text/html; charset=UTF-8"%>

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
          		<a href="#">로그인으로 이동</a>
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
	
		<p id="title">고객님! 안녕하세요~ <br> 방문해주셔서 감사합니다!</p>
		<p id="subtitle">로그인 하시면 <span class="titlecolor">메인 페이지</span>로 이동합니다.</p>
		
		<div id="helloArea">		
		<form name="f" method="post" action="client_login_ok.do" onsubmit="return login_check()">
			<table id="loginforsubpage">
		    	<tr>
		    		<th>아이디</th>
		            	<td>
		          			<input type="text" name="client_id" id="client_id" size="17" />
		          		</td>
		            <th>비밀번호</th>
		           		<td>
		            		<input type="password" name="client_pass" id="client_pass" size="17" />
		           		</td>
		         </tr>
		         <tr>
		         	
		         </tr>
		     </table><br>
	     
	     	 <div id="login_menuforsubpage">
		         <input type="submit" value="로그인" />
		         <input type="button" value="아이디  | 비밀번호 찾기" onclick="pwd_find()" />
		         <input type="button" value="회원가입" onclick="location='client_join.do'"/>
	         </div>
	      </form>
	      </div>
	      <br><br><br>
	      <p id="bottomdesc">도란도란 샵에 아이디가 없으신 분은&nbsp;&nbsp;<span class="titlecolor"><a href="./client_join.do"> [ 회원가입  ] </a></span>&nbsp;&nbsp;후 이용하실 수 있습니다.</p>
		</div>
	
	</div><!-- end contentArea -->

	</div><!-- end content_c -->
</div><!-- end content_wrapper -->

<div class="clear"></div>

<%@ include file="../include/footer.jsp" %>