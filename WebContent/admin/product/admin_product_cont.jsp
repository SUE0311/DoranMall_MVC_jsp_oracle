<%@ page contentType="text/html; charset=UTF-8"%>

<%@ page import="com.product.dao.*" %>

<%
	ProductBean probean=(ProductBean)request.getAttribute("prodata"); 
    // Object클래스의 강제 다운캐스팅
   	int nowpage=((Integer)request.getAttribute("page")).intValue();
  	//getAttribute()메서드로 반환되는값은 자바 최상위 클래스 Object 형이다.
  	//이것을 정수형 변수에 저장하기 위해서 intValue()메서드로 정수형 숫자로 바꿔서 저장.
%>  

<%@ include file="../include/header.jsp" %>

<link rel="stylesheet" type="text/css" href="./css/admin_product.css" />
<script src="./js/admin_product.js"></script>

<div class="clear"></div>

<div id="content_wrapper">

<%@ include file="../include/left_menu.jsp" %>

	<div id="content_c">
	
	<p id="title">상품 관리  상세보기</p>
	
	<table id="proTable">
		<tr>
			<td rowspan="12">
				<img src="./upload<%=probean.getProduct_mainImg()%>" width="150" height="100" ><br>
				<img src="./upload<%=probean.getProduct_thum01()%>" width="150" height="100" ><br>
				<img src="./upload<%=probean.getProduct_thum02()%>" width="150" height="100" >
			</td>
		</tr>
		<tr>
			<th width="20%">
				상품 코드
			</th>
			<td>
				<%=probean.getProduct_code()%>
			</td>
		</tr>
		<tr>
			<th>
				 상품 명
			</th>
			<td colspan=2>
				<%=probean.getProduct_name()%>
			</td>
		</tr>
		<tr>
			<th>
				가격
			</th>
			<td>
				<%=probean.getProduct_price()%>&nbsp;원
			</td>
		</tr>
		<tr>
			<th>
				수량
			</th>
			<td>
				<%=probean.getProduct_number()%>&nbsp;개
			</td>
		</tr>
		<tr>
			<th>
				회사명
			</th>
			<td>
				<%=probean.getProduct_company()%>
			</td>
		</tr>
		<tr>
			<th>
				상품 요약
			</th>
			<td>
				<%=probean.getProduct_summary()%>
			</td>
		</tr>
		<tr>
			<th>
				게시 시작
			</th>
			<td>
				<%=probean.getProduct_startDate()%>
			</td>
		</tr>
		<tr>
			<th>
				게시 마감
			</th>
			<td>
				<%=probean.getProduct_endDate()%>
			</td>
		</tr>
		<tr>
			<th>
				게시 회차
			</th>
			<td>
				<%=probean.getProduct_post() %>
			</td>
		</tr>
		<tr>
			<th>
				게시 상태
			</th>
			<td>
				<%if(probean.getProduct_state() == -1){ %>
					<div style="color:#FF9999">비노출</div>
				<%}else if(probean.getProduct_state() == 0){ %>	
					<div style="color:rgb(169,3,41)">노출</div>
				<%}%>
			</td>
		</tr>
		<tr>
			<th>
				게시 SHOP NO
			</th>
			<td>
				<%if(probean.getProduct_shop() == 1){ %>
					<div style="color:#0066FF">SHOP 1</div>
				<%}else if(probean.getProduct_shop() == 2){ %>	
					<div style="color:#009933">SHOP 2</div>
				<%}else if(probean.getProduct_shop() == 3){ %>	
					<div style="color:#FF5050">SHOP 3</div>	
				<%}%>
			</td>
		</tr>
		<tr>
			<td colspan=7 id="tableBottom">
				<input type="button" value="수정"
			    	onclick="location='admin_product_cont.do?num=<%=probean.getProduct_num()%>&page=<%=nowpage%>&state=edit'"/>
			 	<input type="button" value="삭제" 
			    	onclick="location='admin_product_cont.do?num=<%=probean.getProduct_num()%>&page=<%=nowpage%>&state=delete'"/>
			 	<input type="button" value="목록"
			  	    onclick="location='admin_product_list.do?page=<%=nowpage%>'" />
			</td>
		</tr>
	 </table>
	
	</div><!-- end content_c -->
	</div><!-- end content_wrapper -->

<div class="clear"></div>

<%@ include file="../include/footer.jsp" %>