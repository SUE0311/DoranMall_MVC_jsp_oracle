<!-- 
# 작성자 : 이지수
# 작성일 : 2013.08.05 
-->

<%@ page contentType="text/html; charset=UTF-8"%>

<%@ page import="java.util.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.guide.dao.*" %>

<%
	List<GuideBean> guideList = (List<GuideBean>)request.getAttribute("guideList");

    int listcount=((Integer)request.getAttribute("listcount")).intValue();
	int nowpage=((Integer)request.getAttribute("page")).intValue();
	int maxpage=((Integer)request.getAttribute("maxpage")).intValue();
	int startpage=((Integer)request.getAttribute("startpage")).intValue();
	int endpage=((Integer)request.getAttribute("endpage")).intValue();
%>

<%@ include file="../include/header.jsp" %>

<link rel="stylesheet" type="text/css" href="./css/admin_guide.css" />
<script src="./js/admin_guide.js"></script>

<div class="clear"></div>

<div id="content_wrapper">

<%@ include file="../include/left_menu.jsp" %>

	<div id="content_c">
	
	<p id="title">쇼핑 가이드 목록보기</p>
	
	<table id="guideTable">
	
		<tr>
			<td colspan="7" id="tableTop">
				글 개수 : ${listcount } 개
			</td>
		</tr>
	
		<tr>
			<th width="7%">
				<div>번호</div>
			</th>
			<th width="30%">
				<div>가이드 상품</div>
			</th>
			<th width="15%">
				<div>게시 시작 날짜</div>
			</th>
			<th width="15%">
				<div>게시 마감 날짜</div>
			</th>
			<th width="10%">
				<div>게시 회차</div>
			</th>
			<th width="10%">
				<div>게시 상태</div>
			</th>
			<th width="30%">
				<div>등록일</div>
			</th>
		</tr>
	
	<%
		for(int i=0;i<guideList.size();i++){
			GuideBean guide = guideList.get(i);
	%>
		<tr align="center" valign="middle" bordercolor="#333333"
			onmouseover="this.style.backgroundColor='F8F8F8'"
			onmouseout="this.style.backgroundColor=''">
		<td>
			<div align="center"><%=guide.getGuide_num() %></div>
		</td>
		<td>
			<div>
			 <a href="./admin_guide_cont.do?num=<%=guide.getGuide_num()%>&page=<%=nowpage%>&state=cont">
			 <%=guide.getGuide_product() %>
			 </a>
			</div>
		</td>
		<td>
			<div><%=guide.getGuide_s_date() %></div>
		</td>
		<td>
			<div><%=guide.getGuide_e_date() %></div>
		</td>	
		<td>
			<div><%=guide.getGuide_post() %>&nbsp;회</div>
		</td>
		<td>
			<%if(guide.getGuide_state() == -1){ %>
				<div style="color:#FF9999">비노출</div>
			<%}else if(guide.getGuide_state() == 0){ %>	
				<div style="color:rgb(169,3,41)">노출</div>
			<%}%>
		</td>
		<td>
			<div><%=guide.getGuide_reg_date() %></div>
		</td>
	</tr>
	<%} %>
	<tr>
		<td colspan=7 id="tableBottom">
			<%if(nowpage<=1){ %>
			[이전]&nbsp;
			<%}else{ %>
			<a href="admin_company_list.do?page=<%=nowpage-1 %>">[이전]</a>&nbsp;
			<%} %>
			
			<%for(int a=startpage;a<=endpage;a++){
				if(a==nowpage){%>
				[<%=a %>]
				<%}else{ %>
				<a href="admin_company_list.do?page=<%=a %>">[<%=a %>]</a>&nbsp;
				<%} %>
			<%} %>
			
			<%if(nowpage>=maxpage){ %>
			[다음]
			<%}else{ %>
			<a href="admin_company_list.do?page=<%=nowpage+1 %>">[다음]</a>
			<%} %>
		</td>
	</tr>
</table>
	</div><!-- end content_c -->
	</div><!-- end content_wrapper -->

<div class="clear"></div>

<%@ include file="../include/footer.jsp" %>