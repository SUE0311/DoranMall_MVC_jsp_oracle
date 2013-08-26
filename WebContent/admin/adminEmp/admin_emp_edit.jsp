<%@ page contentType="text/html; charset=UTF-8"%>

<%@ page import="com.admin.dao.*" %>

<%
	AdminBean bean=(AdminBean)request.getAttribute("member");  
%>  

<%@ include file="../include/header.jsp" %>

<link rel="stylesheet" type="text/css" href="./css/admin_emp.css" />
<script src="./js/admin_emp.js"></script>

<div class="clear"></div>

<div id="content_wrapper">

<%@ include file="../include/left_menuFormypage.jsp" %>

	<div id="content_c">
	
	<p id="title">[<%= bean.getAdmin_name()%>] 님의 정보 수정하기</p>
	
	
	<form method="post" action="admin_emp_edit_ok.do" id="admin_edit_form"
		onsubmit="return edit_check()">
		
	<table id="empTable">
	
		<tr>
			<th width="20%">
				사원번호
			</th>
			<td>
				<%-- <input type="text" name="admin_empNo" id="admin_empNo" value="<%= bean.getAdmin_empNo()%>" disabled size="40"/> --%>
				<%= bean.getAdmin_empNo()%>
				<input type="hidden" name="admin_empNo" id="admin_empNo" value="<%= bean.getAdmin_empNo()%>" />
			</td>
		</tr>
		<tr>
			<th>
				 이름
			</th>
			<td>
				<input type="text" name="admin_name" id="admin_name" value="<%= bean.getAdmin_name()%>" size="40" placeholder="필수입력" />
			</td>
		</tr>
		<tr>
			<th>
				아이디
			</th>
			<td>
				<%= bean.getAdmin_id()%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style="color:red">(* id는 수정 불가)</span>
				<input type="hidden" name="admin_id" id="admin_id" value="<%= bean.getAdmin_id()%>" />
				<%-- <input type="text" name="admin_id" id="admin_id" value="<%= bean.getAdmin_id()%>" size="40" /> --%>
			</td>
		</tr>
		<tr>
			<th>
				비밀번호
			</th>
			<td>
			    <input type="password" name="edit_pass" id="edit_pass" size="40" autofocus="autofocus" placeholder="필수입력" />
			</td>
		</tr>
		<tr>
			<th>
				부서명
			</th>
			<td>
			    <input type="text" name="admin_dept" id="admin_dept" value="<%= bean.getAdmin_dept()%>" size="40" placeholder="필수입력" />
			</td>
		</tr>
		<tr>
			<th>
				직위
			</th>
			<td>
				<input type="text" name="admin_pos" id="admin_pos" value="<%= bean.getAdmin_pos()%>" size="40" placeholder="필수입력" />	
			</td>
		</tr>
		<tr>
			<th>
				연락처
			</th>
			<td>
				<input type="text" name="admin_tel" id="admin_tel" value="<%= bean.getAdmin_tel()%>" size="40" placeholder="필수입력" />
			</td>
		</tr>
		<tr>
				<td colspan=2 id="tableBottom" style="text-align:center;">
					<input type="submit" value="수정" />
					<input type="button" value="뒤로" onclick="history.go(-1)"/>
				</td>
			</tr>
	 </table>
	 
	 </form>
	
	</div><!-- end content_c -->
	</div><!-- end content_wrapper -->

<div class="clear"></div>

<%@ include file="../include/footer.jsp" %>