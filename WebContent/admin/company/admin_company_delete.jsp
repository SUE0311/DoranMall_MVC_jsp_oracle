<%@ page contentType="text/html; charset=UTF-8"%>

<%@ page import="com.company.dao.*" %>

<%
	CompanyBean combean=(CompanyBean)request.getAttribute("comdata"); 

   	int nowpage=((Integer)request.getAttribute("page")).intValue();
%>  

<%@ include file="../include/header.jsp" %>

<link rel="stylesheet" type="text/css" href="./css/admin_company.css" />
<script src="./js/admin_company.js"></script>

<div class="clear"></div>

<div id="content_wrapper">

<%@ include file="../include/left_menu.jsp" %>

	<div id="content_c">
	
	<p id="title">입점 신청 기업  삭제</p>
	
	 <form method="post" action="admin_company_delete.do" 
			onsubmit="return del_check()">	
			
		<input type="hidden" name="num" value="<%=combean.getCom_num() %>" />
		<input type="hidden" name="page" value="<%=nowpage %>" />
		
		<input type="hidden" name="admin_id" value="<%=adminId %>" />
		
		<table id="comTable">
		   <tr>
		    	<th>삭제<br>관리자  비밀번호</th>
		    	<td>
		    		<input type="password" name="admin_pwd" id="admin_pwd" size="14" autofocus="autofocus"/>
		    	</td>
		   </tr>
		   <tr>
				<td colspan=2 id="tableBottom">
					<input type="submit" value="삭제" />
					<input type="reset" value="취소" onclick="$('#admin_pwd').focus();"/>
					<input type="button" value="목록으로" 
				onclick="location='admin_company_list.do'"/>
				</td>
			</tr>
		 </table>
	 </form>	
	 
	 </div><!-- end content_c -->
	</div><!-- end content_wrapper -->

<div class="clear"></div>

<%@ include file="../include/footer.jsp" %>