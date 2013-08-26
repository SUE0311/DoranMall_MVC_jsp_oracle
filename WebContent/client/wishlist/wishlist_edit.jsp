<!-- 
# 작성자 : 이지수
# 작성일 : 2013.08.01 
-->

<%@ page contentType="text/html; charset=UTF-8"%>

<%@ page import="com.wishlist.dao.*" %>

<%@ include file="../include/header.jsp" %>

<link rel="stylesheet" type="text/css" href="./css/wishlistwrite.css" />
<script src="./js/wishlist.js"></script>

<div class="clear"></div>

<div id="content_wrapper">

<%@ include file="../include/left_menu.jsp" %>

<%@ include file="../include/submenu_02.jsp" %>

	<div id="content_c">
	
	<div id="contentArea">
	
	<div id="wishlistF">
	
	<p id="title">게시물 수정</p>
	
	<form method="post" name="wishlistEditForm" id="wishlistEditForm" action="wishlist_edit.do" onsubmit="return write_check()" 
			enctype="multipart/form-data">
			
			<input type="hidden" name="num" value="${wishlistdata.wishlist_num }" />
			<input type="hidden" name="page" value="${page }" />
			
		<table id="wishlistF_table">
			<tr>
				<th>고객 아이디</th>
				<td>
					<input type="text" name="wishlist_id" id="wishlist_id" value="${wishlistdata.wishlist_id }"  size="40"/>
				</td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td>
					<input type="password" name="wishlist_pass" id="wishlist_pass" placeholder="필수 입력입니다." size="40"/>
				</td>
			</tr>
			<tr>
				<th>희망 상품</th>
				<td>
					<input type="text" name="wishlist_title" id="wishlist_title" value="${wishlistdata.wishlist_title }" size="70"/>
				</td>
			</tr>
			<tr>
				<th>신청 이유</th>
				<td>
					<textarea name="wishlist_content" cols="90">
						${wishlistdata.wishlist_content }
					</textarea>
				</td>
			</tr>
			<tr>
				<th>첨부 파일</th>
				<td><input type="file" name="wishlist_file" id="wishlist_file" ${wishlistdata.wishlist_file }/></td>
			</tr>
			<tr>
				<td colspan="2">
					<div class="applyF_btnArea">
						<input type="submit" value="수정" />
						<input type="button" value="뒤로" onclick="history.go(-1)"/>
					</div>
				</td>
			</tr>
		</table>
	</form>
	
	</div><!-- end wishlistF -->

	</div><!-- end contentArea -->
	
	</div><!-- end content_c -->
</div><!-- end content_wrapper -->

<div class="clear"></div>

<%@ include file="../include/footer.jsp" %>