<!-- 
# 작성자 : 이지수
# 작성일 : 2013.07.31 

# 작성일 : 2013.08.18
# 내용 : 스크립트릿 제거 -> EL, JSTL로 변경
-->


<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>관리자 페이지</title>

<link rel="stylesheet" type="text/css" href="./css/admin_main.css" />
<script src="./js/jquery.js"></script>
<script src="./js/admin_emp.js"></script>

</head>
<body>

<div id="main_wrap">

<!-- header 영역 -->

<div id="header">
	
	<div id="header_logo">
		<a href="./adminIndex.do"><img src="./images/logo-doran.png" width="266" height="60" /></a>
	</div>

	<ul id="top_menu">
		<c:if test="${!empty id }">
			<li id="logName">${admin_name} 님 [ ID : ${id} ] </li>
			<li><a href="./adminIndex.do">홈</a></li>
			<li><a href="./admin_cont_ok.do">마이 페이지</a></li>
			<li><a href="./admin_logout_ok.do">로그아웃</a></li>
		</c:if>
		
		<c:if test="${empty id }">
			<li><a href="./adminIndex.do">홈</a></li>
			<li><a href="./admin_join.do">관리자 등록 페이지</a></li>
		</c:if>
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
					<a href="./admin_client_list.do"><li>- 고객 정보</li></a>
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
		
			<c:if test="${!empty id }">
				<p id="title">관리자 서비스 권한 별 이용 안내</p>
				
				<table id="login_table">
					<tr>
						<th width="15%">구분</th>
						<td colspan="6" id="desc">1. 열람 가능 : ◇  <br>2. 열람, 쓰기, 수정 가능 : ◎ <br>3. 열람, 쓰기, 수정, 삭제 가능 : ★ </td>
					</tr>
					<tr>
						<th width="15%">직급</th>
						<td width="15%">대표</td>
						<td>★</td>
						<td width="20%">부장 이상</td>
						<td>◎</td>
						<td width="20%">과장이하</td>
						<td>◇</td>
					</tr>
				</table>
				<p style="text-align:right;padding:5px 20px 0 0">※ 관리자 권한 문의 - 내선 : 1201(인사과)</p>
			</c:if>
			
			<c:if test="${empty id }">
				<p id="title">도란도란 샵 관리자 서비스 로그인</p>
				
				<form action="admin_login_ok.do" method="post" onsubmit="return admin_login_ok()">
					<table id="admin_table">
						<tr>
							<th>아이디</th>
							<td><input name="id" id="id" autofocus="autofocus"/></td>
						</tr>
						<tr>
							<th>비밀번호</th>
							<td><input type="password" name="pass" id="pass"/></td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<div id="btnArea">
									<input type="submit" value="로그인" />
									<input type="button" value="비번찾기" onclick="pwd_find()" />
								</div>	
							</td>
						</tr>
					</table>
				</form>
			</c:if>
			
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