<%@ page contentType="text/html; charset=UTF-8"%>

<%@ page import="com.admin.dao.*" %>

<%@ include file="../include/header.jsp" %>

<link rel="stylesheet" type="text/css" href="./css/admin_emp.css" />
<script src="./js/admin_emp.js"></script>

<div class="clear"></div>

<div id="content_wrapper">

<%@ include file="../include/left_menuFormypage.jsp" %>

	<div id="content_c">
	
	<p id="title">[${admin_name }] 님의 정보 삭제(탈퇴)요청</p>
	
	<p id="subtitle">도란도란 공동구매 샵의 관리자 탈퇴를 원하십니까?</p>
	
	
	<form method="post" action="admin_emp_delete_ok.do" id="admin_delete_form"
		onsubmit="return delete_check()">
		
	<table id="empTable">
	
		<tr>
			<th width="20%">
				사원번호
			</th>
			<td>
				${admin_empNo }
			</td>
		</tr>
		<tr>
			<th>
				 이름
			</th>
			<td>
				${admin_name }
			</td>
		</tr>
		<tr>
			<th>
				비밀번호
			</th>
			<td>
			    <input type="password" name="pwd" id="pwd" size="40" autofocus="autofocus" placeholder="필수 입력"/>
			</td>
		</tr>
		<tr>
				<td colspan=2 id="tableBottom" style="text-align:center;">
					<input type="submit" value="탈퇴" />
					<input type="button" value="뒤로" onclick="history.go(-1)"/>
				</td>
			</tr>
	 </table>
	 
	 </form>
	
	</div><!-- end content_c -->
	</div><!-- end content_wrapper -->

<div class="clear"></div>

<%@ include file="../include/footer.jsp" %>