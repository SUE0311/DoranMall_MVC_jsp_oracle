<!-- 
# 작성자 : 이지수
# 작성일 : 2013.08.16 
-->
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*" %>

<%@ page import="com.client.dao.*" %>

<%@ include file="../include/header.jsp" %>

<link rel="stylesheet" type="text/css" href="./css/client.css" />

<div class="clear"></div>

<div id="content_wrapper">

<%@ include file="../include/left_menu.jsp" %>

<%@ include file="../include/submenu_04.jsp" %>

	<div id="content_c">
	
	<p id="title">[${client_name}] 님의 마이페이지</p>
	
	<div id="contentArea">
	
		<form method="post" action="client_mypage_edit_ok.do" name="client_edit_form" id="client_edit_form"
			onsubmit="return join_check()">
			
		<table id="clientTable">
			<tr id="tableTop">
				<td colspan="3">
					* 모든 빈칸은 필수 입력입니다. 
				</td>
			</tr>
			<tr>
				<th width="20%">
					아이디
				</th>
				<td>
					<%-- <%=bean.getClient_id() %> --%>	
					${client_id}
				</td>
			</tr>
			<tr>
				<th>
					 비밀번호
				</th>
				<td>
					<input type="password" name="client_pass" id="client_pass" size="55" placeholder="필수입력"/>
				</td>
			</tr>
			<tr>
				<th>
					이름
				</th>
				<td>
					<input type="text" name="client_name" id="client_name" value="${client_name}" size="55" />
				</td>
			</tr> 
			<tr>
				<th>우편번호</th>
				<td>
					<input type="text" name="client_zip1" id="client_zip1" size="3" value="${client_zip1}"
						readonly onclick="post_search()"/> - 
					<input type="text" name="client_zip2" id="client_zip2" size="3" value="${client_zip2}"
						readonly onclick="post_search()"/>	
						<!-- readonly 속성은 커서가 입력상자에 들어가지 못하게 하는 기능으로
							읽기 전용 속성을 지정해준다. 만약 입력상자를 클릭하면 post_search()함수를 
							호출하도록 지정해있다. -->
				<input type="button" value="우편번호 찾기" onclick="post_check()"/> 
				</td>
			</tr>
			<tr>
				<th>주소</th>
				<td><input type="text" name="client_addr1" id="client_addr1" size="55" value="${client_addr1}" readonly onclick="post_search()"/></td>
			</tr>
			<tr>
				<th>나머지 주소</th>
				<td><input type="text" name="client_addr2" id="client_addr2" size="55" value="${client_addr2}" /></td>
			</tr>
			<tr>
				<th>
					연락처
				</th>
				<td>
					<input type="text" name="client_tel" id="client_tel" value="${client_tel}" size="55" />
				</td>
			</tr>
			<tr>
				<th>
					이메일
				</th>
				<td>
					<input type="text" name="client_email" id="client_email" value="${client_email}" size="55" />
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
	
	</div><!-- end contentArea -->
	

	</div><!-- end content_c -->
	
</div><!-- end content_wrapper -->

<div class="clear"></div>

<%@ include file="../include/footer.jsp" %>