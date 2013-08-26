<!-- 
# 작성자 : 이지수
# 작성일 : 2013.08.06 
-->
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="java.util.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.wishlist.dao.*" %>

<%
	List<WishlistBean> wishlistList=(List<WishlistBean>)request.getAttribute("wishlistList");

	int nowpage=((Integer)request.getAttribute("page")).intValue();
	int maxpage=((Integer)request.getAttribute("maxpage")).intValue();
	int startpage=((Integer)request.getAttribute("startpage")).intValue();
	int endpage=((Integer)request.getAttribute("endpage")).intValue();
%>

<%@ include file="../include/header.jsp" %>

<link rel="stylesheet" type="text/css" href="./css/wishlist.css" />

<div class="clear"></div>

<div id="content_wrapper">

<%@ include file="../include/left_menu.jsp" %>

<%@ include file="../include/submenu_02.jsp" %>

	<div id="content_c">
	
	<p id="title">희망 상품 목록</p>
	
	<table id="wishlistTable">
	
		<tr>
			<td id="tableLeftTop"  colspan="3">
				<form method="post" name="searchWF" id="searchWF" action="wishlist_search.do">
				<input type="text" name="searchWish" id="searchWish" placeholder="희망상품을 검색해보세요" autofocus="autofocus" size="25"/>
				<input type="submit" value="검색" />
				</form>
			</td>
			<td colspan="3" id="tableTop">
				글 개수 : ${listcount } 개
			</td>
		</tr>
	
		<tr>
			<th width="7%">번호</th>
			<th width="45%">희망상품</th>
			<th width="15%">작성자</th>
			<th width="15%">날짜</th>
			<th width="7%">추천수</th>
			<th width="7%">조회수</th>
		</tr>
	<%
		if(wishlistList.size() == 0){
	%>	 
		<p id="searchNo">고객님! 찾으시는 희망 상품 관련 내용이 없습니다<br>
		지금이 찬스~ 고객님께서 직접 공동구매 희망상품을 남겨주세요.<br>
		도란도란 공동구매 샵은 언제나 고객님의 이야기를 소중히 듣겠습니다!<br><br><br>
		<img src="./images/wishApply.jpg" width="350">
		</p>
	<%}%>		
	
	<%
		for(int i=0;i<wishlistList.size();i++){
			WishlistBean wb=wishlistList.get(i);
	%>
		<tr>
			<td>
				 <%if(wb.getWishlist_re_lev() == 0){
				  //답변글이 아닐때는 글그룹번호가 출력%>
	             <%=wb.getWishlist_re_ref()%>
	             <%}else{
	              //답변글일때는 글그룹번호를 뺀다.%>
	              &nbsp;
	             <%}%>
			</td>
			
			<td>
				<%if(wb.getWishlist_re_lev()!=0){
					//답변글 일때 답변글 순서만큼 레벨값 번호가
					//1씩증가한다. 즉 아래 답변글일수록 
					//들여쓰기한다.
	   			for(int a=1;a<=wb.getWishlist_re_lev();a++){ %>
		         &nbsp;
	           <%} %>
	            <img src="./images/AnswerLine.gif">
	           <%} %>
	            <a href="wishlist_cont.do?num=<%=wb.getWishlist_num()%>&page=<%=nowpage%>&state=cont">
	           <%=wb.getWishlist_title()%></a>
			</td>
			<td>
				<a href="member_info.do?id=<%=wb.getWishlist_id()%>"><!--  member 테이블에서 id 회원 정보  -->
				<%=wb.getWishlist_id() %>
				</a>
			</td>
			<td>
				<%=wb.getWishlist_date() %>
			</td>
			<td>
				<%=wb.getWishlist_good() %>
			</td>		
			<td>
				<%=wb.getWishlist_readcont() %>
			</td>
		</tr>
		<%} %>
		<tr>
			<td colspan=7 id="tableBottom">
				<%if(nowpage<=1){ %>
				[이전]&nbsp;
				<%}else{ %>
				<a href="wishlist_list.do?page=<%=nowpage-1 %>">[이전]</a>&nbsp;
				<%} %>
				
				<%for(int a=startpage;a<=endpage;a++){
					if(a==nowpage){%>
					[<%=a %>]
					<%}else{ %>
					<a href="wishlist_list.do?page=<%=a %>">[<%=a %>]</a>&nbsp;
					<%} %>
				<%} %>
				
				<%if(nowpage>=maxpage){ %>
				[다음]
				<%}else{ %>
				<a href="wishlist_list.do?page=<%=nowpage+1 %>">[다음]</a>
				<%} %>
				<input type="button" id="wishWriteBtn" value="글쓰기" onclick="location='wishlist_write.do?page=<%=nowpage%>'"> 
			</td>
		</tr>
	</table>

	</div><!-- end content_c -->
	
</div><!-- end content_wrapper -->

<div class="clear"></div>

<%@ include file="../include/footer.jsp" %>