<!-- 
# 작성자 : 이지수
# 작성일 : 2013.08.06 
-->
<%@ page contentType="text/html; charset=UTF-8"%>

<%@ page import="com.wishlist.dao.*" %>

<%@ include file="../include/header.jsp" %>

<link rel="stylesheet" type="text/css" href="./css/wishlist.css" />

<div class="clear"></div>

<div id="content_wrapper">

<%@ include file="../include/left_menu.jsp" %>

<%@ include file="../include/submenu_02.jsp" %>

	<div id="content_c">
	
	<p id="title">게시물 삭제</p>
	
	<form method="post" action="wishlist_delete.do" 
			onsubmit="return del_check()">	
			
		<input type="hidden" name="num" value="${wishlistdata.wishlist_num }" />
		<input type="hidden" name="page" value="${page }" />
		
		<table id="wishlistTable">
		   <tr>
		    	<th>삭제<br>비밀번호</th>
		    	<td>
		    		<input type="password" name="pwd" id="wishlist_pwd" size="14" autofocus="autofocus"/>
		    	</td>
		   </tr>
		   <tr>
				<td colspan=2 id="tableBottom">
					<input type="submit" value="삭제" />
					<input type="reset" value="취소" onclick="$('#admin_pwd').focus();"/>
					<input type="button" value="목록으로" 
				onclick="location='wishlist_list.do'"/>
				</td>
			</tr>
		 </table>
	 </form>
	 
	</div><!-- end content_c -->
	
</div><!-- end content_wrapper -->

<div class="clear"></div>

<%@ include file="../include/footer.jsp" %>