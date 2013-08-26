<%@ page contentType="text/html; charset=UTF-8"%>

<%@ page import="com.company.dao.*" %>

<%
	CompanyBean combean = (CompanyBean)request.getAttribute("comdata");
	int nowpage=((Integer)request.getAttribute("page")).intValue();
	
	/* out.println(combean.getCom_num()); */
%>

<%@ include file="../include/header.jsp" %>

<link rel="stylesheet" type="text/css" href="./css/admin_company.css" />
<script src="./js/admin_company.js"></script>

<div class="clear"></div>

<div id="content_wrapper">

<%@ include file="../include/left_menu.jsp" %>

	<div id="content_c">
	
	<p id="title">입점 신청 기업  정보수정</p>
	
	<form method="post" action="admin_company_edit.do" id="company_edit_form"
	onsubmit="return edit_check()" enctype="multipart/form-data">
	
		<input type="hidden" name="num" value="<%=combean.getCom_num() %>" />
		<input type="hidden" name="page" value="<%=nowpage %>" />
	
		<table id="comTable">
			<tr>
				<th>회사 이름</th>
				<td style="text-align:left">
					<input type="text" name="com_co" id="com_co" value="<%=combean.getCom_co() %>" size="40"/>
				</td>
			</tr>
			<tr>
				<th>담당자 이름</th>
				<td style="text-align:left">
					<input type="text" name="com_name" id="com_name" value="<%=combean.getCom_name() %>" size="40"/>
				</td>
			</tr>
			<tr>
				<th>담당자 연락처</th>
				<td style="text-align:left">
					<input type="text" name="com_tel" id="com_tel" value="<%=combean.getCom_tel() %>" size="40"/>
				</td>
			</tr>
			<tr>
				<th>이메일</th>
				<td style="text-align:left">
					<input type="text" name="com_mail" id="com_mail" value="<%=combean.getCom_mail() %>" size="40"/>
				</td>
			</tr>
			<tr>
				<th>사이트 주소</th>
				<td style="text-align:left">
					<%if(combean.getCom_website() == null){ %>
						<input type="text" name="com_website" id="com_website" value="내용없음" size="40"/>
					<%}else{ %>	
						<input type="text" name="com_website" id="com_website" value="<%=combean.getCom_website() %>" size="40"/>
					<%} %>
				</td>
			</tr>
			<tr>
				<th>입점 희망 제품</th>
				<td style="text-align:left">
					<input type="text" name="com_product" id="com_product" value="<%=combean.getCom_product() %>" size="80"/>
				</td>
			</tr>
			<tr>
				<th>제품 가격</th>
				<td style="text-align:left">
					<input type="text" name="com_price" id="com_price" value="<%=combean.getCom_price() %>" size="40"/>&nbsp;원
				</td>
			</tr>
			<tr>
				<th>제품 상세 정보</th>
				<td style="text-align:left">
					<textarea name="com_cont" id="com_cont" cols="90"><%=combean.getCom_cont() %></textarea>
				</td>
			</tr>
			<tr>
				<th>첨부 파일</th>
				<td style="text-align:left">
					<%-- <%if(combean.getCom_file1() == null){ %>
						<div style="color:rgb(169,3,41)">이미지 없음</div>
					<%}else{ %>	
						<img src="./upload<%=combean.getCom_file1()%>" width="300" height="200" >
					<%} %> --%>
					<input type="file" name="com_file1" value="<%=combean.getCom_file1() %>" />
				</td>	
			</tr>
			<tr>
				<td colspan=2 id="tableBottom" style="text-align:center;">
					<input type="submit" value="수정" />
					<!-- <input type="reset" value="취소" onclick="$('#bbs_name').focus();"/> -->
					<input type="button" value="목록보기" onclick="location='admin_company_list.do'"/>
				</td>
			</tr>
		</table>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	