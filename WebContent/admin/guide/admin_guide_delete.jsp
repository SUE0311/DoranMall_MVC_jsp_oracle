<!-- 
# 작성자 : 이지수
# 작성일 : 2013.08.05 
-->

<%@ page contentType="text/html; charset=UTF-8"%>

<%@ page import="com.guide.dao.*" %>

<%
	GuideBean guidebean=(GuideBean)request.getAttribute("guidedata"); 

   	int nowpage=((Integer)request.getAttribute("page")).intValue();
%>  

<%@ include file="../include/header.jsp" %>

<link rel="stylesheet" type="text/css" href="./css/admin_guide.css" />
<script src="./js/admin_guide.js"></script>

<div class="clear"></div>

<div id="content_wrapper">

<%@ include file="../include/left_menu.jsp" %>

	<div id="content_c">
	
	<p id="title">쇼핑 가이드  삭제</p>
	
	 <form method="post" action="admin_guide_delete.do" 
			onsubmit="return del_check()">	
			
		<input type="hidden" name="num" value="<%=guidebean.getGuide_num() %>" />
		<input type="hidden" name="page" value="<%=nowpage %>" />
		
		<table id="guideTable">
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
				onclick="location='admin_guide_list.do'"/>
				</td>
			</tr>
		 </table>
	 </form>	
	 
	 </div><!-- end content_c -->
	</div><!-- end content_wrapper -->

<div class="clear"></div>

<%@ include file="../include/footer.jsp" %>