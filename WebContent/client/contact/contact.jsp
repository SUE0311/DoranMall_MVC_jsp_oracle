<!-- 
# 작성자 : 이지수
# 작성일 : 2013.08.01 
-->

<%@ page contentType="text/html; charset=UTF-8"%>

<%@ include file="../include/header.jsp" %>

<link rel="stylesheet" type="text/css" href="./css/contact.css" />
<script src="./js/contact.js"></script>

<div class="clear"></div>

<div id="content_wrapper">

<%@ include file="../include/left_menu.jsp" %>
	
	<div id="content_c">
	
	<p id="title">입점요청 신청서</p>
	
	<div id="descPage">
		<p>반갑습니다. "도란도란 공동구매 샵"에 관심을 갖고 방문해주셔서 감사합니다.<br>
		   본 페이지는 저희 샵에 소중한 제품을 입점하고자 하는 중소기업을 위한 신청서 작성 공간입니다.<br>
		   귀 사의 땀과 열정으로 만든 제품을 더 많은 소비자들에게 알려주십시오.<br> 
		   질 좋은 제품을 합당한 가격으로 판매함으로서, 생산자와 소비자 모두가 행복할 수 있도록 노력하겠습니다.    	
		</p>
	</div>
	
	<div id="contentArea">
	
	<div id="applyF">
	
	<form method="post" name="applyForm" id="applyForm" action="company_contact_ok.do" onsubmit="return apply_check()" 
			enctype="multipart/form-data">
		<table id="applyF_table">
			<tr>
				<th>회사 이름</th>
				<td>
					<input type="text" name="com_co" id="com_co" autofocus="autofocus" placeholder="필수 입력입니다." size="40"/>
				</td>
			</tr>
			<tr>
				<th>담당자 이름</th>
				<td>
					<input type="text" name="com_name" id="com_name" placeholder="필수 입력입니다." size="40"/>
				</td>
			</tr>
			<tr>
				<th>담당자 연락처</th>
				<td>
					<input type="text" name="com_tel" id="com_tel" placeholder="필수 입력입니다. (작성 예 : 010-1234-5678)" size="40"/>
				</td>
			</tr>
			<tr>
				<th>이메일</th>
				<td>
					<input type="text" name="com_mail" id="com_mail" placeholder="필수 입력입니다." size="40"/>
				</td>
			</tr>
			<tr>
				<th>사이트 주소</th>
				<td>
					<input type="text" name="com_website" id="com_website" size="40"/>
				</td>
			</tr>
			<tr>
				<th>입점 희망 제품</th>
				<td>
					<input type="text" name="com_product" id="com_product" placeholder="필수 입력입니다." size="80"/>
				</td>
			</tr>
			<tr>
				<th>제품 가격</th>
				<td>
					<input type="text" name="com_price" id="com_price" placeholder="필수 입력입니다." size="40"/>&nbsp;원
				</td>
			</tr>
			<tr>
				<th>제품 상세 정보</th>
				<td>
					<textarea name="com_cont" id="com_cont" placeholder="필수 입력입니다." cols="80"></textarea>
				</td>
			</tr>
			<tr>
				<th>첨부 파일</th>
				<td><input type="file" name="com_file1" id="com_file1" /></td>
			</tr>
			<tr>
				<td colspan="2">
					<div class="applyF_btnArea">
						<input type="submit" value="신청서 제출" />
						<!-- <input type="button" value="내 이메일로 보내기" onclick="getEmail();"/> -->
						<input type="reset" value="입력취소" onclick="getValue();"/><!-- onclick="$('#com_co').focus();" -->
						<input type="button" value="뒤로" onclick="history.go(-1)"/>
					</div>
				</td>
			</tr>
		</table>
	</form>
	
	</div><!-- end applyF -->

	</div><!-- end applyF -->
	
	</div><!-- end content_c -->
</div><!-- end content_wrapper -->

<div class="clear"></div>

<%@ include file="../include/footer.jsp" %>