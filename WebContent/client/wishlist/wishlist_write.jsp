<!-- 
# 작성자 : 이지수
# 작성일 : 2013.08.01 
-->

<%@ page contentType="text/html; charset=UTF-8"%>

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
	
	<p id="title">희망 리스트 작성</p>

	<div id="contentArea">
	
	<div id="descPage">
		<p>고객 여러분! 저희 "도란도란 공동구매 샵"은 소비자가 직접 만들어가는 인터넷 쇼핑몰을 지향합니다.<br>
		   본 페이지는 고객님께서 저희 몰을 통해 구매하시고 싶은 희망 상품을 올려주시는 공간입니다. <br>
		   고객 한분 한분의 의견을 듣고 반영하여 모든 고객이 행복할 수 있는 도란도란 공동구매 샵이 되겠습니다.    	
		</p>
	</div>
	
	<div id="wishlistF">
	
	<form method="post" name="wishlistForm" id="wishlistForm" action="wishlist_write_ok.do" onsubmit="return write_check()" 
			enctype="multipart/form-data">
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
				<th>희망 상품</th>
				<td>
					<input type="text" name="wishlist_title" id="wishlist_title" placeholder="필수 입력입니다." size="70"/>
				</td>
			</tr>
			<tr>
				<th>신청 이유</th>
				<td>
					<textarea name="wishlist_content" id="input" cols="90"></textarea>
				</td>
			</tr>
			<tr>
				<th>첨부 파일</th>
				<td><input type="file" name="wishlist_file" id="wishlist_file" /></td>
			</tr>
			<tr>
				<td colspan="2">
					<div class="applyF_btnArea">
						<input type="submit" value="작성" />
						<!-- <input type="button" value="내 이메일로 보내기" onclick="getEmail();"/> -->
						<!-- <input type="reset" value="입력취소" onclick="getValue();"/> --><!-- onclick="$('#com_co').focus();" -->
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