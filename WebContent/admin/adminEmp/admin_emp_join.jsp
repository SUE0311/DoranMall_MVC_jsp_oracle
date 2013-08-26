<%@ page contentType="text/html; charset=UTF-8"%>

<%@ page import="com.admin.dao.*" %>

<%@ include file="../include/header.jsp" %>

<link rel="stylesheet" type="text/css" href="./css/admin_emp.css" />
<script src="./js/admin_emp.js"></script>

<div class="clear"></div>

<div id="content_wrapper">

<%@ include file="../include/left_menu.jsp" %>

	<div id="content_c">
	
	<p id="title">도란도란 샵 관리자 등록하기</p>
	
	
	<form method="post" action="admin_emp_join_ok.do" id="admin_join_form"
		onsubmit="return edit_check()">
		
	<table id="empTable">
	
		<tr>
			<th width="20%">
				사원번호
			</th>
			<td>
				<input type="text" name="admin_empNo" id="admin_empNo" size="40"/>
			</td>
		</tr>
		<tr>
			<th>
				 이름
			</th>
			<td>
				<input type="text" name="admin_name" id="admin_name" size="40" />
			</td>
		</tr>
		<tr>
			<th>
				아이디
			</th>
			<td>
				<input type="text" name="admin_id" id="admin_id" size="40" />
				<input type="button" value="아이디 중복체크" onclick="id_check()" />
				<span id="idcheck"></span>	
			</td>
		</tr>
		<tr>
			<th>
				비밀번호
			</th>
			<td>
			    <input type="password" name="admin_pass" id="admin_pass" size="40" />
			</td>
		</tr>
		<tr>
			<th>
				부서명
			</th>
			<td>
			    <input type="text" name="admin_dept" id="admin_dept" size="40" />
			</td>
		</tr>
		<tr>
			<th>
				직위
			</th>
			<td>
				<input type="text" name="admin_pos" id="admin_pos" size="40" />	
			</td>
		</tr>
		<tr>
			<th>
				연락처
			</th>
			<td>
				<input type="text" name="admin_tel" id="admin_tel" size="40" />
			</td>
		</tr>
		<tr>
				<td colspan=2 id="tableBottom" style="text-align:center;">
					<input type="submit" value="등록" />
					<input type="button" value="뒤로" onclick="history.go(-1)"/>
				</td>
			</tr>
	 </table>
	 
	 </form>
	
	</div><!-- end content_c -->
	</div><!-- end content_wrapper -->

<div class="clear"></div>

<%@ include file="../include/footer.jsp" %>