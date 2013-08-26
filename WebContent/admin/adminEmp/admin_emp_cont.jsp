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
	
	<p id="title">[<%= bean.getAdmin_name()%>] 님의 정보 상세 보기</p>
	
	<table id="empTable">
	
		<tr>
			<th width="20%">
				사원번호
			</th>
			<td>
				<%= bean.getAdmin_empNo()%>
			</td>
		</tr>
		<tr>
			<th>
				 이름
			</th>
			<td>
				<%= bean.getAdmin_name()%>
			</td>
		</tr>
		<tr>
			<th>
				아이디
			</th>
			<td>
				<%= bean.getAdmin_id()%>
			</td>
		</tr>
		<tr>
			<th>
				부서명
			</th>
			<td>
				<%= bean.getAdmin_dept()%>
			</td>
		</tr>
		<tr>
			<th>
				직위
			</th>
			<td>
				<%= bean.getAdmin_pos()%>
			</td>
		</tr>
		<tr>
			<th>
				연락처
			</th>
			<td>
				<%= bean.getAdmin_tel()%>
			</td>
		</tr>
	 </table>
	
	</div><!-- end content_c -->
	</div><!-- end content_wrapper -->

<div class="clear"></div>

<%@ include file="../include/footer.jsp" %>