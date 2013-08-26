<!-- 
# 작성자 : 이지수
# 작성일 : 2013.08.12
-->

<%@ page contentType="text/html; charset=UTF-8"%>

<%@ include file="../include/header.jsp" %>

<link rel="stylesheet" type="text/css" href="./css/admin_productwrite.css" />
<link rel="stylesheet" type="text/css" href="./css/jquery-ui.css" />
<script src="./js/product.js"></script>

<script src="./js/jquery-1.9.1.js"></script>
<script src="./js/jquery-ui.js"></script>

<script>
  $(function() {
    
    $( "#product_startDate" ).datepicker();
    $( "#product_endDate" ).datepicker();
    
  });
</script>

<div class="clear"></div>

<div id="content_wrapper">

<%@ include file="../include/left_menu.jsp" %>

	<%
		if(adminId == null){
	%>	
		<script>
			alert("관리자 전용 메뉴입니다. 로그인해주십시오.");
			history.go(-1);
		</script>
	<%}else{ 
		if(adminAuth.equals("1")){
	%>
		<script>
			alert("접근 권한이 없습니다.");
			location='adminIndex.do';
		</script>
		<%}else {%> 	
		<div id="content_c">
		
		<p id="title">공동구매 상품 등록</p>
		
		<div id="productF">
		
		<form method="post" name="productForm" id="productForm" action="admin_product_write_ok.do" onsubmit="return write_check()" 
				enctype="multipart/form-data">
			<table id="productF_table">
				<tr>
					<th>상품 코드</th>
					<td>
						<input type="text" name="product_code" id="product_code" autofocus="autofocus" placeholder="필수 입력입니다." size="40"/>
					</td>
				</tr>
				<tr>
					<th>상품 이름</th>
					<td>
						<input type="text" name="product_name" id="product_name" placeholder="필수 입력입니다." size="40"/>
					</td>
				</tr>
				<tr>
					<th>상품 가격</th>
					<td>
						<input type="text" name="product_price" id="product_price" placeholder="필수 입력입니다." size="25"/>&nbsp;원
					</td>
				</tr>
				<tr>
					<th>상품 수량</th>
					<td>
						<input type="text" name="product_number" id="product_number" placeholder="필수 입력입니다." size="25"/>&nbsp;개
					</td>
				</tr>
				<tr>
					<th>제조 회사</th>
					<td>
						<input type="text" name="product_company" id="product_company" placeholder="필수 입력입니다." size="70"/>
					</td>
				</tr>
				<tr>
					<th>상품 요약</th>
					<td>
						<input type="text" name="product_summary" id="product_summary" placeholder="필수 입력입니다." size="70"/>
					</td>
				</tr>
				<tr>
					<th>상품 상세 정보</th>
					<td><input type="file" name="product_description" id="product_description" /></td>
				</tr>
				<tr>
					<th>상품 메인 사진</th>
					<td><input type="file" name="product_mainImg" id="product_mainImg" /></td>
				</tr>
				<tr>
					<th>상품 서브 사진(1)</th>
					<td><input type="file" name="product_thum01" id="product_thum01" /></td>
				</tr>
				<tr>
					<th>상품 서브 사진(2)</th>
					<td><input type="file" name="product_thum02" id="product_thum02" /></td>
				</tr>
				<tr>
					<th>상품 서브 사진(3)</th>
					<td><input type="file" name="product_thum03" id="product_thum03" /></td>
				</tr>
				<tr>
					<th>상품 관련 동영상</th>
					<td><input type="file" name="product_video" id="product_video" /></td>
				</tr>
				<tr>
					<th>상품 옵션(1)</th>
					<td>
						<input type="text" name="product_opt01" id="product_opt01" size="70"/>
					</td>
				</tr>
				<tr>
					<th>상품 옵션(1)-1</th>
					<td>
						<input type="text" name="product_opt01_1" id="product_opt01_1" size="70"/>
					</td>
				</tr>
				<tr>
					<th>상품 옵션(1)-2</th>
					<td>
						<input type="text" name="product_opt01_2" id="product_opt01_2" size="70"/>
					</td>
				</tr>
				<tr>
					<th>상품 옵션(1)-3</th>
					<td>
						<input type="text" name="product_opt01_3" id="product_opt01_3" size="70"/>
					</td>
				</tr>
				<tr>
					<th>상품 옵션(1)-4</th>
					<td>
						<input type="text" name="product_opt01_4" id="product_opt01_4" size="70"/>
					</td>
				</tr>
				<tr>
					<th>상품 옵션(1)-5</th>
					<td>
						<input type="text" name="product_opt01_5" id="product_opt01_5" size="70"/>
					</td>
				</tr>
				<tr>
					<th>상품 옵션(2)</th>
					<td>
						<input type="text" name="product_opt02" id="product_opt02" size="70"/>
					</td>
				</tr>
				<tr>
					<th>상품 옵션(2)-1</th>
					<td>
						<input type="text" name="product_opt02_1" id="product_opt02_1" size="70"/>
					</td>
				</tr>
				<tr>
					<th>상품 옵션(2)-2</th>
					<td>
						<input type="text" name="product_opt02_2" id="product_opt02_2" size="70"/>
					</td>
				</tr>
				<tr>
					<th>상품 옵션(2)-3</th>
					<td>
						<input type="text" name="product_opt02_3" id="product_opt02_3" size="70"/>
					</td>
				</tr>
				<tr>
					<th>상품 옵션(2)-4</th>
					<td>
						<input type="text" name="product_opt02_4" id="product_opt02_4" size="70"/>
					</td>
				</tr>
				<tr>
					<th>상품 옵션(2)-5</th>
					<td>
						<input type="text" name="product_opt02_5" id="product_opt02_5" size="70"/>
					</td>
				</tr>
				<tr>
					<th>게시 시작 날짜</th>
					<td>
						<input type="text" name="product_startDate" id="product_startDate" size="70"/>
					</td>
				</tr>
				<tr>
					<th>게시 마감 날짜</th>
					<td>
						<input type="text" name="product_endDate" id="product_endDate" size="70"/>
					</td>
				</tr>
				<tr>
					<th>게시 회차</th>
					<td>
						<input type="text" name="product_post" id="product_post" size="10"/>&nbsp;회차
					</td>
				</tr>
				<tr>
					<th>게시 상태</th>
					<td>
						<input type="radio" value="-1" name="product_state" id="product_state_hide" />비노출
						<input type="radio" value="0" name="product_state" id="product_state_show" />노출
					</td>
				</tr>
				<tr>
					<th>게시 Shop 위치</th>
					<td>
						<input type="radio" value="1" name="product_shop" id="product_shop01" />첫번째 SHOP
						<input type="radio" value="2" name="product_shop" id="product_shop02" />두번째 SHOP
						<input type="radio" value="3" name="product_shop" id="product_shop03" />세번째 SHOP
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div class="applyF_btnArea">
							<input type="submit" value="작성" />
							<input type="button" value="뒤로" onclick="history.go(-1)"/>
						</div>
					</td>
				</tr>
			</table>
		</form>
		
		</div><!-- end productF -->
		
		</div><!-- end content_c -->
		
		<%} 
	}%>	

</div><!-- end content_wrapper -->

<div class="clear"></div>

<%@ include file="../include/footer.jsp" %>