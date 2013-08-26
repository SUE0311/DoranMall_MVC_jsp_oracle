<!-- 
# 작성자 : 이지수
# 작성일 : 2013.08.01 
-->

<%@ page contentType="text/html; charset=UTF-8"%>

<%@ page import="com.wishlist.dao.*" %>

<%@ include file="../include/header.jsp" %>

<link rel="stylesheet" type="text/css" href="./css/wishlistwrite.css" />
<script src="./js/wishlist.js"></script>

<!-- 게시판 에디터 플러그인 -->
<link rel="stylesheet" type="text/css" href="./css/jquery.cleditor.css" />
<script type="text/javascript" src="./js/jquery.cleditor.min.js"></script>
<script type="text/javascript">
    $(document).ready(function () { $("#input").cleditor(); });
</script>

<div class="clear"></div>

<div id="content_wrapper">

<%@ include file="../include/left_menu.jsp" %>

<%@ include file="../include/submenu_02.jsp" %>

	<div id="content_c">
	
	<div id="contentArea">
	
	<div id="wishlistF">
	
	<p id="title">게시물 답변</p>
	
	<form method="post" name="wishlistreplyForm" id="wishlistreplyForm" action="wishlist_reply_ok.do" onsubmit="return write_check()">
		
		<!-- 글번호와 관련 번호를 히든으로 넘긴다. -->			
		<input type="hidden" name="wishlist_num" value="${wishlistdata.wishlist_num }" />
		<input type="hidden" name="wishlist_re_ref" value="${wishlistdata.wishlist_re_ref }" />
		<input type="hidden" name="wishlist_re_lev" value="${wishlistdata.wishlist_re_lev }" />
		<input type="hidden" name="wishlist_re_seq" value="${wishlistdata.wishlist_re_seq }" />
		
		<input type="hidden" name="page" value="${page }" />
		
		
		<table id="wishlistF_table">
			<tr>
				<th>고객 아이디</th>
				<td>
					<input type="text" name="wishlist_id" id="wishlist_id" autofocus="autofocus" placeholder="필수 입력입니다." size="40"/>
				</td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td>
					<input type="password" name="wishlist_pass" id="wishlist_pass" placeholder="필수 입력입니다." size="40"/>
				</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>
					<input type="text" name="wishlist_title" id="wishlist_title" placeholder="필수 입력입니다." size="70"
					value="re:${wishlistdata.wishlist_title }" />
				</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>
					<textarea name="wishlist_content" id="input" cols="90"></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div class="applyF_btnArea">
						<input type="submit" value="답변" />
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