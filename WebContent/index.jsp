<!-- 
# 작성자 : 이지수
# 작성일 : 2013.07.31 
-->

<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>도란도란몰</title>

<link rel="stylesheet" type="text/css" href="./css/main.css" />
<script src="./js/jquery.js"></script>
<script src="./js/client.js"></script>

</head>
<body>

<div id="main_wrap">

<!-- header 영역 -->

<div id="header">
	<div id="loginArea">
	
		<c:if test="${!empty client_id}">
			<table id="login_after">   
		    	<tr>
	            	<td>
	          			반가워요! &nbsp;&nbsp;<span id="logName">${client_name }</span> 님 [ ID : ${client_id } ]
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
		
		<c:if test="${empty client_id}">
			<form name="f" method="post" action="client_login_ok.do" onsubmit="return login_check()">
				<table id="login_before">
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
			     </table>
		     
		     	 <div id="login_menu">
			         <input type="submit" value="로그인" />
			         <input type="button" value="아이디  | 비밀번호 찾기" onclick="pwd_find()" />
			         <input type="button" value="회원가입" onclick="location='client_join.do'"/>
		         </div>
		      </form>
		</c:if>
		
      </div><!-- end loginArea  -->  
</div><!-- end header  -->

<div class="clear"></div>

<!-- content(본문) 영역 -->
<div id="content_wrapper">

	<div id="left_menu" style="display:hidden">
	</div><!-- end left_menu -->

	<div id="content_c">
	
		<div class="home-block">
			<div class="social-buttons">
				<a class="facebook" href="#" target="_blank"></a>
				<a class="linkedin" href="#" target="_blank"></a>
				<span class="border"></span>
				<a class="twitter" href="#" target="_blank"></a>
			</div>
			
			<h1 class="tk-source-sans-pro">도란<br />도란<br />공동구매 샵</h1>
			
			<div class="home-content">
				<img src="./images/logo-doran.png" width="266" height="60" /><br /><br /><br />
				<p>“도란도란” 이란 순수 우리말로 ‘여럿이 나직한 목소리로 서로 정답게 이야기하는 소리 또는 그 모양’을 뜻합니다. 중소기업과 소비자가 어울려져 정다운 쇼핑몰을 만들어나가겠습니다.</p>
				
				<br />
				<a href="#" target="_blank" style="color: #58585a; font-size: 11px; text-decoration: none; vertical-align: middle;">Web design by <img src="./images/yoke_logo_black.jpg" alt="Web design by Yoke" width="50" style="width: 50px !important; vertical-align: sub; margin-left: 2px" /></a>
			</div>
		</div>
		<div class="circle">
			<img src="./images/circle2.svg" style="display: block; height: 710px" alt="" />
		</div>
		<div class="circle png">
			<img src="./images/circle2.png" style="display: table-cell; height: 500px" alt="" />
		</div>
		
		<div class="colours">
			<a href="#" class="colour_link spareBar1"><span data-name="spareBar1" class="colour"><span></span></span></a>
			<a href="./product.do" class="colour_link product"><span data-name="product" class="colour"><span>금주의 상품</span></span></a>
			<a href="./guide.do" class="colour_link guide"><span data-name="guide" class="colour"><span>나에게 맞는 제품은?</span></span></a>
			<a href="./event.do" class="colour_link event"><span data-name="event" class="colour"><span>금주의 이벤트</span></span></a>
			<a href="./wishlist_list.do" class="colour_link community"><span data-name="community" class="colour"><span>고객 커뮤니티</span></span></a>
			<a href="./contact.do" class="colour_link contactUs"><span data-name="contactUs" class="colour"><span>Contact Us</span></span></a>
			<a href="#" class="colour_link spareBar2"><span data-name="spareBar2" class="colour"><span></span></span></a>
			<a href="#" class="colour_link spareBar3"><span data-name="spareBar3" class="colour"><span></span></span></a>
		</div>
	
	</div><!-- end content_c -->
</div><!-- end content_wrapper -->

<div class="clear"></div>

<!-- footer 영역 -->
<div id="footer">
	<ul id="footerLeft">
		<li><a href="./doranInfo.do">찾아오시는 길</a></li>
		<li><a href="#">고객의 소리</a></li>
	</ul>
	
	<ul id="siteMap">
		<li><a href="./product.do">금주의 상품</a></li>
		<li><a href="./guide.do">나에게 맞는 제품은?</a></li>
		<li><a href="./event.do">금주의 이벤트</a></li>
		<li><a href="./board_list.do">고객 커뮤니티</a></li>
		<li><a href="./contact.do">CONTACT US</a></li>
	</ul>
</div>

</div><!-- end main_wrap -->

</body>
</html>