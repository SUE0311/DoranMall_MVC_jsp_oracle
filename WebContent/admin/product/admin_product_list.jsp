<%@ page contentType="text/html; charset=UTF-8"%>

<%@ page import="java.util.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.product.dao.*" %>

<%
	List<ProductBean> productList=(List<ProductBean>)request.getAttribute("productList");

    int listcount=((Integer)request.getAttribute("listcount")).intValue();
	int nowpage=((Integer)request.getAttribute("page")).intValue();
	int maxpage=((Integer)request.getAttribute("maxpage")).intValue();
	int startpage=((Integer)request.getAttribute("startpage")).intValue();
	int endpage=((Integer)request.getAttribute("endpage")).intValue();
%>

<%@ include file="../include/header.jsp" %>

<link rel="stylesheet" type="text/css" href="./css/admin_product.css" />
<script src="./js/admin_product.js"></script>

<div class="clear"></div>

<div id="content_wrapper">

<%@ include file="../include/left_menu.jsp" %>

	<div id="content_c">
	
	<p id="title">상품 관리 목록보기</p>
	
	<table id="proTable">
	
		<tr>
			<td colspan="6" id="tableLeftTop">
				<form method="post" name="searchCF" id="searchCF" action="admin_product_search.do">
				
				<select name="searchSelect" id="searchSelect">
					<option name="product_name" value="product_name">상품명</option>
					<option name="product_code" value="product_code">상품코드</option>
					<option name="product_company" value="product_company">회사명</option>		
				</select>
				
				<input type="text" name="searchCom" id="searchCom" placeholder="검색어 입력" autofocus="autofocus" size="25"/>
				
				<input type="submit" value="검색" />
				</form>
			</td>
			<td colspan="4" id="tableTop">
				글 개수 : ${listcount } 개
			</td>
		</tr>
	
		<tr>
			<th width="7%">
				<div>번호</div>
			</th>
			<th width="15%">
				<div>상품 코드</div>
			</th>
			<th width="10%">
				<div>사진</div>
			</th>
			<th width="20%">
				<div>상품 명</div>
			</th>
			<th width="9%">
				<div>가격(원)</div>
			</th>
			<th width="9%">
				<div>수량(개)</div>
			</th>
			<th width="15%">
				<div>회사명</div>
			</th>
			<th width="7%">
				<div>회차</div>
			</th>
			<th width="25%">
				<div>게시</div>
			</th>
			<th width="25%">
				<div>No.</div>
			</th>
		</tr>
	<%
		if(productList.size() == 0){
	%>	 
		<p id="searchNo">찾으시는 검색어 관련 내용이 없습니다</p>
	<%}%>	

	<%
		for(int i=0;i<productList.size();i++){
			ProductBean pro = productList.get(i);
	%>
		<tr align="center" valign="middle" bordercolor="#333333"
			onmouseover="this.style.backgroundColor='F8F8F8'"
			onmouseout="this.style.backgroundColor=''">
		<td>
			<div align="center"><%=pro.getProduct_num() %></div>
		</td>
		<td>
			<div align="center"><%=pro.getProduct_code() %></div>
		</td>
		<td>
			<%-- <div align="center"><%=pro.getProduct_mainImg() %></div> --%>
			<img src="./upload<%=pro.getProduct_mainImg()%>" width="70" height="50" >
		</td>
		<td>
			<div>
			 <a href="./admin_product_cont.do?num=<%=pro.getProduct_num()%>&page=<%=nowpage%>&state=cont">
			 <%=pro.getProduct_name() %>
			 </a>
			</div>
		</td>
		<td>
			<div align="center"><%=pro.getProduct_price() %></div>
		</td>
		<td>
			<div align="center"><%=pro.getProduct_number() %></div>
		</td>
		<td>
			<div align="center"><%=pro.getProduct_company() %></div>
		</td>
		<td>
			<div align="center"><%=pro.getProduct_post() %></div>
		</td>
		<td>
			<%if(pro.getProduct_state() == -1){ %>
				<div style="color:#FF9999">비노출</div>
			<%}else if(pro.getProduct_state() == 0){ %>	
				<div style="color:rgb(169,3,41)">노출</div>
			<%}%>
		</td>
		<td>
			<%if(pro.getProduct_shop() == 1){ %>
				<div style="color:#0066FF">SHOP 1</div>
			<%}else if(pro.getProduct_shop() == 2){ %>	
				<div style="color:#009933">SHOP 2</div>
			<%}else if(pro.getProduct_shop() == 3){ %>	
				<div style="color:#FF5050">SHOP 3</div>	
			<%}%>
		</td>
	</tr>
	<%} %>
	<tr>
		<td colspan=7 id="tableBottom">
			<%if(nowpage<=1){ %>
			[이전]&nbsp;
			<%}else{ %>
			<a href="admin_product_list.do?page=<%=nowpage-1 %>">[이전]</a>&nbsp;
			<%} %>
			
			<%for(int a=startpage;a<=endpage;a++){
				if(a==nowpage){%>
				[<%=a %>]
				<%}else{ %>
				<a href="admin_product_list.do?page=<%=a %>">[<%=a %>]</a>&nbsp;
				<%} %>
			<%} %>
			
			<%if(nowpage>=maxpage){ %>
			[다음]
			<%}else{ %>
			<a href="admin_product_list.do?page=<%=nowpage+1 %>">[다음]</a>
			<%} %>
		</td>
	</tr>
</table>
	</div><!-- end content_c -->
	</div><!-- end content_wrapper -->

<div class="clear"></div>

<%@ include file="../include/footer.jsp" %>