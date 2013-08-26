<!-- 
# 작성자 : 이지수
# 작성일 : 2013.08.06 
-->
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="java.util.*" %>
<%@ page import="com.wishlist.dao.*" %>

<%@ include file="../include/header.jsp" %>

<link rel="stylesheet" type="text/css" href="./css/wishlist.css" />
<script src="./js/wishlist.js"></script>

<div class="clear"></div>

<div id="content_wrapper">

<%@ include file="../include/left_menu.jsp" %>

<%@ include file="../include/submenu_02.jsp" %>

	<div id="content_c">
	
	<p id="title">내용 보기</p>
	
	<table id="wishlistTable">
	
		<tr>
			<th width="20%">
				작성자
			</th>
			<td colspan="3">
				${wishlistdata.wishlist_id}
			</td>
		</tr>
		<tr>
			<th>
				희망 상품
			</th>
			<td colspan="3">
				${wishlistdata.wishlist_title}
			</td>
		</tr>
		<tr>
			<th>
				신청 이유
			</th>
			<td colspan="3" height="150px">
				<div align="left">${wishlistdata.wishlist_content}</div>
			</td>
		</tr>
		<tr>
			<th>
				추천수
			</th>
			<td width="40%">
					<span id="goodNumber">${wishlistdata.wishlist_good}</span>
					<span id="result"></span>
					<span id="message"></span>
					
					<input type="hidden" name="wishlist_num" id="wishlist_num" value="${wishlistdata.wishlist_num}" />
					<input type="hidden" name="wishlist_good" id="wishlist_good" value="${wishlistdata.wishlist_good}" />
					
					<input id="goodBtn" type="button" value="추천하기" onclick="add_good()" />
			</td>
			<th>
				조회수
			</th>
			<td width="20%">
				${wishlistdata.wishlist_readcont}
			</td>
		</tr>
		<tr>
			<th>
				첨부파일
			</th>
			<td colspan="3">
				<c:if test="${empty wishlistdata.wishlist_file}">
					<div style="color:rgb(169,3,41)">내용 없음</div>
				</c:if>
				
				<c:if test="${!empty wishlistdata.wishlist_file}">
					<a href="./upload${wishlistdata.wishlist_file}" target="_blank">{wishlistdata.wishlist_file}</a>
				</c:if>	
			</td>
		</tr>
		<tr>
			<td colspan=7 id="tableBottom">
				<input type="button" value="수정"
			    	onclick="location='wishlist_cont.do?num=${wishlistdata.wishlist_num }&page=${page}&state=edit'"/>
			 	<input type="button" value="답변"
	   				onclick="location='wishlist_cont.do?num=${wishlistdata.wishlist_num }&page=${page}&state=reply'"/>
			 	<input type="button" value="삭제" 
			    	onclick="location='wishlist_cont.do?num=${wishlistdata.wishlist_num }&page=${page}&state=delete'"/>
			 	<input type="button" value="목록"
			  	    onclick="location='wishlist_list.do?page=${page}'" />
			</td>
		</tr>
	</table>

	</div><!-- end content_c -->
	
</div><!-- end content_wrapper -->

<div class="clear"></div>

<%@ include file="../include/footer.jsp" %>