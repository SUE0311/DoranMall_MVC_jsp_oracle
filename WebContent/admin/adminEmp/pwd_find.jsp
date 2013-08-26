<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>비밀번호 찾기</title>

<link rel="stylesheet" type="text/css" href="./css/admin_emp.css" />

<script src="./js/jquery.js"></script>
<script src="./js/admin_emp.js"></script>

</head>
<body>

<!-- passwd가 공백인 경우 -->
<c:if test = "${empty passwd}" >

	<p id="pwdtitle">비밀번호 찾기</p>
	<p id="pwdsubtitle">아이디와 이름을 입력해주세요.</p>
	
	<form action="pwd_find_ok.do" method="post" onsubmit="return pwd_check()">
		<table id="pwdTable">	
			<tr>
				<th>아이디</th>
				<td><input name="pwd_id" id="pwd_id" autofocus="autofocus"/></td>
			</tr>
			<tr>
				<th>이름</th>
				<td><input type="text" name="pwd_name" id="pwd_name"/></td>
			</tr>
			<tr>
				<td colspan="2" id="tableBottom">
					<input type="submit" value="찾기" />
	
					<input type="reset" value="취소" onclick="$('#pwd_id').focus();"/>
				</td>
			</tr>
		</table>
	</form>
</c:if>

	<!-- jstl를 이용하여 결과값을 리턴받은 경우 -->
	<c:if test = "${!empty passwd }" >
		<h2 id="pwd_title2" 
			style="width:180px;margin:30px auto 0;font-size:20px;
					color:#9966FF;text-align:center;">비번찾기 결과</h2>
		<table id="pwd_t2" 
			   style="width:250px;margin:30px auto 0;border-collapse:collapse;">
			<tr>
				<th style="border: 1px solid black;padding:2px;background:#FFCCCC;">
					검색된 비번 : </th>
				<td style="border: 1px solid black;padding:2px;padding:5px;text-align:center;">
					${passwd }</td>
			</tr>
			<tr>
				<td colspan="2" id="tableBottom">
					<input type="button" value="닫기" onclick="pwd_close()"/>
	
				</td>
			</tr>
		</table>
	</c:if>

</body>
</html>