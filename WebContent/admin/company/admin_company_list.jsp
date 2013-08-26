<%@ page contentType="text/html; charset=UTF-8"%>

<%@ page import="java.util.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.company.dao.*" %>

<%
	List<CompanyBean> companyList=(List<CompanyBean>)request.getAttribute("companyList");

    int listcount=((Integer)request.getAttribute("listcount")).intValue();
	int nowpage=((Integer)request.getAttribute("page")).intValue();
	int maxpage=((Integer)request.getAttribute("maxpage")).intValue();
	int startpage=((Integer)request.getAttribute("startpage")).intValue();
	int endpage=((Integer)request.getAttribute("endpage")).intValue();
%>

<%@ include file="../include/header.jsp" %>

<link rel="stylesheet" type="text/css" href="./css/admin_company.css" />
<script src="./js/admin_company.js"></script>

<div class="clear"></div>

<div id="content_wrapper">

<%@ include file="../include/left_menu.jsp" %>

	<div id="content_c">
	
	<p id="title">입점 신청 기업 목록보기</p>
	
	<table id="comTable">
	
		<tr>
			<td colspan="3" id="tableLeftTop">
				<form method="post" name="searchCF" id="searchCF" action="admin_company_search.do">
				
				<select name="searchSelect" id="searchSelect">
						<option name="com_co" value="com_co">회사명</option>
						<option name="com_product" value="com_product">상품명</option>
				</select>
				
				<input type="text" name="searchCom" id="searchCom" placeholder="검색어 입력" autofocus="autofocus" size="25"/>
				
				<!-- <input type="text" name="searchCom" id="searchCom" placeholder="입점신청 상품" autofocus="autofocus" size="25"/> -->
				<input type="submit" value="검색" />
				</form>
			</td>
			<td colspan="3" id="tableTop">
				글 개수 : ${listcount } 개
			</td>
		</tr>
	
		<tr>
			<th width="7%">
				<div>번호</div>
			</th>
			<th width="30%">
				<div>입점 희망 제품</div>
			</th>
			<th width="15%">
				<div>제품 가격(원)</div>
			</th>
			<th width="20%">
				<div>회사 이름</div>
			</th>
			<th width="15%">
				<div>담당자 이름</div>
			</th>
			<th width="30%">
				<div>신청일</div>
			</th>
		</tr>
	<%
		if(companyList.size() == 0){
	%>	 
		<p id="searchNo">찾으시는 검색어 관련 내용이 없습니다</p>
	<%}%>	

	<%
		for(int i=0;i<companyList.size();i++){
			CompanyBean com = companyList.get(i);
	%>
		<tr align="center" valign="middle" bordercolor="#333333"
			onmouseover="this.style.backgroundColor='F8F8F8'"
			onmouseout="this.style.backgroundColor=''">
		<td>
			<div align="center"><%=com.getCom_num() %></div>
		</td>
		<td>
			<div>
			 <a href="./admin_company_cont.do?num=<%=com.getCom_num()%>&page=<%=nowpage%>&state=cont">
			 <%=com.getCom_product() %>
			 </a>
			</div>
		</td>
		<td>
			<div><%=com.getCom_price() %></div>
		</td>
		<td>
			<div><%=com.getCom_co() %></div>
		</td>	
		<td>
			<div><%=com.getCom_name() %></div>
		</td>
		<td>
			<div><%=com.getCom_date() %></div>
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