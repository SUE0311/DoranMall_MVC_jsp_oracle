<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>아이디  | 비밀번호 찾기</title>

<link rel="stylesheet" type="text/css" href="./css/client.css" />

<script src="./js/jquery.js"></script>
<script src="./js/client.js"></script>

<style>
body{
	width:470px;
	padding-top:20px;
}
</style>

</head>

<body>

<!-- passwd가 공백인 경우 -->
<c:if test = "${empty afterfindId}" >

	<p class="pwdtitle">아이디 찾기</p>
	<p class="idsubtitle">이름과 이메일을 입력해주세요.</p>
	
	<form action="client_id_find_ok.do" method="post" onsubmit="return id_find()">
		<table class="findTable">	
			<tr>
				<th>이름</th>
				<td><input name="find_name" id="find_name" size="25" autofocus="autofocus"/></td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><input type="text" name="find_email" id="find_email" size="25" /></td>
			</tr>
			<tr>
				<td colspan="2" class="findBottom">
					<input type="submit" value="찾기" />
	
					<input type="reset" value="취소" onclick="$('#find_name').focus();"/>
				</td>
			</tr>
		</table>
	</form>
</c:if>

	<!-- jstl를 이용하여 결과값을 리턴받은 경우 -->
	<c:if test = "${!empty afterfindId }" >
		<h2 class="pwd_title2" 
			style="width:180px;margin:30px auto 0;font-size:20px;
					color:#9966FF;text-align:center;">아이디 찾기 결과</h2>
		<table class="pwd_t2" 
			   style="width:250px;margin:30px auto 0;border-collapse:collapse;">
			<tr>
				<th style="border: 1px solid black;padding:2px;background:#FFCCCC;">
					검색된 아이디 : </th>
				<td style="border: 1px solid black;padding:2px;padding:5px;text-align:center;">
					${afterfindId }</td>
			</tr>
			<tr>
				<td colspan="2" class="findBottom">
					<input type="button" value="닫기" onclick="pwd_close()"/>
	
				</td>
			</tr>
		</table>
	</c:if>



<!-- passwd가 공백인 경우 -->
<c:if test = "${empty passwd}" >

	<p class="pwdtitle">비밀번호 찾기</p>
	<p class="pwdsubtitle">아이디와 이름을 입력해주세요.</p>
	
	<form action="client_pwd_find_ok.do" method="post" onsubmit="return pwd_check()">
		<table class="findTable">	
			<tr>
				<th>아이디</th>
				<td><input name="pwd_id" id="pwd_id" size="25" /></td>
			</tr>
			<tr>
				<th>이름</th>
				<td><input type="text" name="pwd_name" id="pwd_name" size="25" /></td>
			</tr>
			<tr>
				<td colspan="2" class="findBottom">
					<input type="submit" value="찾기" />
	
					<input type="reset" value="취소" onclick="$('#pwd_id').focus();"/>
				</td>
			</tr>
		</table>
	</form>
</c:if>

	<!-- jstl를 이용하여 결과값을 리턴받은 경우 -->
	<c:if test = "${!empty passwd }" >
		<h2 class="pwd_title2" 
			style="width:180px;margin:30px auto 0;font-size:20px;
					color:#9966FF;text-align:center;">비번찾기 결과</h2>
		<table class="pwd_t2" 
			   style="width:250px;margin:30px auto 0;border-collapse:collapse;">
			<tr>
				<th style="border: 1px solid black;padding:2px;background:#FFCCCC;">
					검색된 비번 : </th>
				<td style="border: 1px solid black;padding:2px;padding:5px;text-align:center;">
					${passwd }</td>
			</tr>
			<tr>
				<td colspan="2" class="findBottom">
					<input type="button" value="닫기" onclick="pwd_close()"/>
	
				</td>
			</tr>
		</table>
	</c:if>

</body>
</html>