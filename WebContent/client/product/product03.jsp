<!-- 
# 작성자 : 이지수
# 작성일 : 2013.08.12 
-->

<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="com.product.dao.*" %>

<%@ include file="../include/header.jsp" %>

<link rel="stylesheet" type="text/css" href="./css/product.css" />

<script src="./js/product.js"></script>

<div class="clear"></div>

<div id="content_wrapper">

<%@ include file="../include/left_menu.jsp" %>

<%@ include file="../include/submenu_03.jsp" %>

	<div id="content_c">

	<p id="title" class="shop03">[${prodata.product_company }] 의  ${prodata.product_name } </p>
	
	<div id="contentArea">
	
	<form method="post" name="payForm" id="payForm" action="product_pay_ok.do" onsubmit="return pay_check()">
	
	<table id="proTableTop">
		<tr>
			<td rowspan="10" style="width:270px;background:#fff;padding:10px 0 10px 20px">
				<div class="gallery">

				<div><img src="./upload${prodata.product_mainImg }" alt="큰사진" id="poster" width="260" height="200" /></div>	
						
				<ul id="thum">
					<li><a href="./upload${prodata.product_mainImg }"><img src="./upload${prodata.product_mainImg }" alt="작은사진1" width="55" height="55" /></a></li>
					<li><a href="./upload${prodata.product_thum01 }" ><img src="./upload${prodata.product_thum01 }" alt="작은사진2" width="55" height="55" /></a></li>
					<li><a href="./upload${prodata.product_thum02 }" ><img src="./upload${prodata.product_thum02 }" alt="작은사진3" width="55" height="55" /></a></li>
					<li><a href="./upload${prodata.product_thum03 }" ><img src="./upload${prodata.product_thum03 }" alt="작은사진4" width="55" height="55"  /></a></li>
				</ul>
				
				</div><!-- gallery end -->
			</td>
		</tr>
		<tr>
			<td colspan="2" id="saleDate">
				판매 기간 : ${prodata.product_startDate } ~ ${prodata.product_endDate }
			</td>
		</tr>
		<tr>
			<td colspan="2" id="summary">
				${prodata.product_summary }
			</td>
		</tr>
		<tr>
			<th width="20%">
				상품명
			</th>
			<td>
				${prodata.product_name }
			</td>
		</tr>
		<tr>
			<th>
				판매가
			</th>
			<td>
				${prodata.product_price }&nbsp;원
			</td>
		</tr>
		<tr>
			<th>
				남은 수량
			</th>
			<td>
				${prodata.product_number }&nbsp;개
			</td>
		</tr>
		<tr>
			<th>
				배송비
			</th>
			<td>
				<select name="deliverPay" id="deliverPay" class="selectStyle">
					<option name="deliverPayNow">배송비를 선택해주세요</option>
					<option name="deliverPayNow" value="2500">주문시 결재(+2500원)</option>
					<option name="deliverPayAfter" value="0">수령후 지불</option>		
				</select>
			</td>
		</tr>
		<tr>
			<th rowspan="2">
				주문옵션
			</th>
			<td>
				<c:if test = "${empty prodata.product_opt01 }" >
					<div>옵션 사항 없음</div>
				</c:if>	
				
				<c:if test = "${!empty prodata.product_opt01 }" >

					<select name="product_opt01" id="product_opt01" class="selectStyle">
							<option name="product_opt01">${prodata.product_opt01 }&nbsp;선택해주세요</option>
						
							<option name="product_opt01_1" value="1">${prodata.product_opt01_1 }</option>
						
						<c:if test = "${empty prodata.product_opt01_2 }" >
							<div></div>
						</c:if>	
						<c:if test = "${!empty prodata.product_opt01_2 }" >
							<option name="product_opt01_2" value="2">${prodata.product_opt01_2 }</option>
						</c:if>	
						
						<c:if test = "${empty prodata.product_opt01_3 }" >
							<div></div>
						</c:if>	
						<c:if test = "${!empty prodata.product_opt01_3 }" >
							<option name="product_opt01_3" value="3">${prodata.product_opt01_3 }</option>
						</c:if>	
						
						<c:if test = "${empty prodata.product_opt01_4 }" >
							<div></div>
						</c:if>	
						<c:if test = "${!empty prodata.product_opt01_4 }" >
							<option name="product_opt01_4" value="4">${prodata.product_opt01_4 }</option>
						</c:if>	
						
						<c:if test = "${empty prodata.product_opt01_5 }" >
							<div></div>
						</c:if>	
						<c:if test = "${!empty prodata.product_opt01_5 }" >
							<option name="product_opt01_5" value="5">${prodata.product_opt01_5 }</option>
						</c:if>	
	
					</select>
					
				</c:if>	
			</td>
		</tr>
		<tr>
			<td>
				<c:if test = "${!empty prodata.product_opt02 }" >

					<select name="product_opt01" id="product_opt01" class="selectStyle">
							<option name="product_opt02">${prodata.product_opt02 }&nbsp;선택해주세요</option>
						
							<option name="product_opt02_1" value="1">${prodata.product_opt02_1 }</option>
						
						<c:if test = "${empty prodata.product_opt02_2 }" >
							<div></div>
						</c:if>	
						<c:if test = "${!empty prodata.product_opt02_2 }" >
							<option name="product_opt02_2" value="2">${prodata.product_opt02_2 }</option>
						</c:if>	
						
						<c:if test = "${empty prodata.product_opt02_3 }" >
							<div></div>
						</c:if>	
						<c:if test = "${!empty prodata.product_opt02_3 }" >
							<option name="product_opt02_3" value="3">${prodata.product_opt02_3 }</option>
						</c:if>	
						
						<c:if test = "${empty prodata.product_opt02_4 }" >
							<div></div>
						</c:if>	
						<c:if test = "${!empty prodata.product_opt02_4 }" >
							<option name="product_opt02_4" value="4">${prodata.product_opt02_4 }</option>
						</c:if>	
						
						<c:if test = "${empty prodata.product_opt02_5 }" >
							<div></div>
						</c:if>	
						<c:if test = "${!empty prodata.product_opt02_5 }" >
							<option name="product_opt02_5" value="5">${prodata.product_opt02_5 }</option>
						</c:if>	
	
					</select>
					
				</c:if>	
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" id="payOk" value="구매하기" />
			</td>
		</tr>	
	</table>
	
	</form>
	
	<table id="proTableCenter">
		<tr>
			<th>상품 상세 보기</th>
		</tr>
		<tr>
			<td>
				<img src="./upload${prodata.product_description }" alt="상세보기" />
			</td>
		</tr>
	</table>
	
	
	</div><!-- end contentArea -->

	</div><!-- end content_c -->
</div><!-- end content_wrapper -->

<div class="clear"></div>

<%@ include file="../include/footer.jsp" %>