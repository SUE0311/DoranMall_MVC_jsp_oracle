<%@ page contentType="text/html; charset=UTF-8"%>

<%@ page import="java.util.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.client.dao.*" %>

<%
	List<ClientBean> clientList=(List<ClientBean>)request.getAttribute("clientList");

    int listcount=((Integer)request.getAttribute("listcount")).intValue();
	int nowpage=((Integer)request.getAttribute("page")).intValue();
	int maxpage=((Integer)request.getAttribute("maxpage")).intValue();
	int startpage=((Integer)request.getAttribute("startpage")).intValue();
	int endpage=((Integer)request.getAttribute("endpage")).intValue();
%>

<%@ include file="../include/header.jsp" %>

<link rel="stylesheet" type="text/css" href="./css/admin_client.css" />
<script src="./js/admin_client.js"></script>

<div class="clear"></div>

<div id="content_wrapper">

<%@ include file="../include/left_menu.jsp" %>

	<div id="content_c">
	
	<p id="title">가입 고객 명단 보기</p>
	
	<table id="clientTable">
	
		<tr>
			<td colspan="3" id="tableLeftTop">
				<form method="post" name="searchClient" id="searchClient" action="admin_client_search.do">
				
				<select name="searchClientSelect" id="searchSelect">
						<option name="client_name" value="client_name">고객명</option>
						<option name="client_id" value="client_id">아이디</option>
				</select>
				
				<input type="text" name="searchClient" id="searchCom" placeholder="검색어 입력" autofocus="autofocus" size="25"/>
				
				<input type="submit" value="검색" />
				</form>
			</td>
			<td colspan="3" id="tableTop">
				가입 고객 : ${listcount } 명
			</td>
		</tr>
	
		<tr>
			<th width="7%">
				<div>번호</div>
			</th>
			<th width="20%">
				<div>고객 명</div>
			</th>
			<th width="20%">
				<div>아이디</div>
			</th>
			<th width="15%">
				<div>등급</div>
			</th>
			<th width="15%">
				<div>마일리지(점)</div>
			</th>
			<th width="20%">
				<div>가입일</div>
			</th>
		</tr>
<%-- 	<%
		if(clientList.size() == 0){
	%>	 
		<p id="searchNo">찾으시는 검색어 관련 내용이 없습니다</p>
	<%}%>	 --%>

	<%
		for(int i=0;i<clientList.size();i++){
			ClientBean client = clientList.get(i);
	%>
		<tr align="center" valign="middle" bordercolor="#333333"
			onmouseover="this.style.backgroundColor='F8F8F8'"
			onmouseout="this.style.backgroundColor=''">
		<td>
			<div align="center"><%=client.getClient_num() %></div>
		</td>
		<td>
			<div>
			 <a href="./admin_client_cont.do?num=<%=client.getClient_num()%>&page=<%=nowpage%>&state=cont">
			 <%=client.getClient_name() %>
			 </a>
			</div>
		</td>
		<td>
			<div><%=client.getClient_id() %></div>
		</td>
		<td>
			<%if(client.getClient_rank().equals("1")){ %>
				<div style="color:#FF9999">일반회원</div>
			<%}else if(client.getClient_rank().equals("2")){ %>	
				<div style="color:rgb(169,3,41)">특별회원</div>
			<%}else if(client.getClient_rank().equals("3")){ %>	
				<div style="color:#009999">우수회원</div>
			<%}%>
		</td>	
		<td>
			<div><%=client.getClient_mileage() %></div>
		</td>
		<td>
			<div><%=client.getClient_regdate() %></div>
		</td>
	</tr>
	<%} %>
	<tr>
		<td colspan=7 id="tableBottom">
			<%if(nowpage<=1){ %>
			[이전]&nbsp;
			<%}else{ %>
			<a href="admin_client_list.do?page=<%=nowpage-1 %>">[이전]</a>&nbsp;
			<%} %>
			
			<%for(int a=startpage;a<=endpage;a++){
				if(a==nowpage){%>
				[<%=a %>]
				<%}else{ %>
				<a href="admin_client_list.do?page=<%=a %>">[<%=a %>]</a>&nbsp;
				<%} %>
			<%} %>
			
			<%if(nowpage>=maxpage){ %>
			[다음]
			<%}else{ %>
			<a href="admin_client_list.do?page=<%=nowpage+1 %>">[다음]</a>
			<%} %>
		</td>
	</tr>
</table>
	</div><!-- end content_c -->
	</div><!-- end content_wrapper -->

<div class="clear"></div>

<%@ include file="../include/footer.jsp" %>