<!-- 
# 작성자 : 이지수
# 작성일 : 2013.08.05 
-->

<%@ page contentType="text/html; charset=UTF-8"%>

<%@ page import="com.guide.dao.*" %>

<%
	GuideBean guidebean = (GuideBean)request.getAttribute("guidedata");
	int nowpage=((Integer)request.getAttribute("page")).intValue();
%>


<%@ include file="../include/header.jsp" %>

<link rel="stylesheet" type="text/css" href="./css/admin_guide.css" />
<link rel="stylesheet" type="text/css" href="./css/jquery-ui.css" />

<script src="./js/admin_guide.js"></script>
<script src="./js/jquery-1.9.1.js"></script>
<script src="./js/jquery-ui.js"></script>

<script>
  $(function() {
    
    $( "#guide_s_date" ).datepicker();
    $( "#guide_e_date" ).datepicker();
    
  });
</script>

<div class="clear"></div>

<div id="content_wrapper">

<%@ include file="../include/left_menu.jsp" %>

	<div id="content_c">
	
	<p id="title">금주의 쇼핑 가이드 수정</p>
	
	<form method="post" action="admin_guide_edit.do" id="guide_edit_form"
	onsubmit="return edit_check()">
	
	<input type="hidden" name="num" value="<%=guidebean.getGuide_num() %>" />
	<input type="hidden" name="page" value="<%=nowpage %>" />
	
		<table id="guideTable">
			<tr>
				<th>가이드 제품</th>
				<td style="text-align:left">
					<input type="text" name="guide_product" id="guide_product" value="<%=guidebean.getGuide_product() %>" size="50"/>
				</td>
			</tr>
			<tr>
				<th>시작 날짜</th>
				<td style="text-align:left">
					<input type="text" name="guide_s_date" id="guide_s_date" value="<%=guidebean.getGuide_s_date() %>" size="50"/>
				</td>
			</tr>
			<tr>
				<th>마감 날짜</th>
				<td style="text-align:left">
					<input type="text" name="guide_e_date" id="guide_e_date" value="<%=guidebean.getGuide_e_date() %>" size="50"/>
				</td>
			</tr>
			<tr>
				<th>게시 회차</th>
				<td style="text-align:left">
					<input type="text" name="guide_post" id="guide_post" value="<%=guidebean.getGuide_post() %>" size="10"/>&nbsp;회차
				</td>
			</tr>
			<tr>
				<th>기재 상태</th>
				<td style="text-align:left">
					<input type="radio" value="-1" name="guide_state" id="guide_state_hide" />비노출
					<input type="radio" value="0" name="guide_state" id="guide_state_show" />노출
				</td>
			</tr>
			<tr>
				<th>[문제 1]</th>
				<td style="text-align:left">
					<input type="text" name="guide_q1" id="guide_q1" value="<%=guidebean.getGuide_q1() %>" size="100"/>
				</td>
			</tr>
			<tr>
				<th>보기 1-1</th>
				<td style="text-align:left">
					<input type="text" name="guide_q1_a1" id="guide_q1_a1" value="<%=guidebean.getGuide_q1_a1() %>" size="40"/>
				</td>
			</tr>
			<tr>
				<th>보기 1-2</th>
				<td style="text-align:left">
					<input type="text" name="guide_q1_a2" id="guide_q1_a2" value="<%=guidebean.getGuide_q1_a2() %>"size="40"/>
				</td>
			</tr>
			<tr>
				<th>보기 1-3</th>
				<td style="text-align:left">
					<input type="text" name="guide_q1_a3" id="guide_q1_a3" value="<%=guidebean.getGuide_q1_a3() %>" size="40"/>
				</td>
			</tr>
			<tr>
				<th>[문제 2]</th>
				<td style="text-align:left">
					<input type="text" name="guide_q2" id="guide_q2" value="<%=guidebean.getGuide_q2() %>" size="100"/>
				</td>
			</tr>
			<tr>
				<th>보기 2-1</th>
				<td style="text-align:left">
					<input type="text" name="guide_q2_a1" id="guide_q2_a1" value="<%=guidebean.getGuide_q2_a1() %>" size="40"/>
				</td>
			</tr>
			<tr>
				<th>보기 2-2</th>
				<td style="text-align:left">
					<input type="text" name="guide_q2_a2" id="guide_q2_a2" value="<%=guidebean.getGuide_q2_a2() %>" size="40"/>
				</td>
			</tr>
			<tr>
				<th>보기 2-3</th>
				<td style="text-align:left">
					<input type="text" name="guide_q2_a3" id="guide_q2_a3" value="<%=guidebean.getGuide_q2_a3() %>" size="40"/>
				</td>
			</tr>
			<tr>
				<th>[문제 3]</th>
				<td style="text-align:left">
					<input type="text" name="guide_q3" id="guide_q3" value="<%=guidebean.getGuide_q3() %>" size="100"/>
				</td>
			</tr>
			<tr>
				<th>보기 3-1</th>
				<td style="text-align:left">
					<input type="text" name="guide_q3_a1" id="guide_q3_a1" value="<%=guidebean.getGuide_q3_a1() %>" size="40"/>
				</td>
			</tr>
			<tr>
				<th>보기 3-2</th>
				<td style="text-align:left">
					<input type="text" name="guide_q3_a2" id="guide_q3_a2" value="<%=guidebean.getGuide_q3_a2() %>" size="40"/>
				</td>
			</tr>
			<tr>
				<th>보기 3-3</th>
				<td style="text-align:left">
					<input type="text" name="guide_q3_a3" id="guide_q3_a3" value="<%=guidebean.getGuide_q3_a3() %>" size="40"/>
				</td>
			</tr>
			<tr>
				<th>[문제 4]</th>
				<td style="text-align:left">
					<input type="text" name="guide_q4" id="guide_q4" value="<%=guidebean.getGuide_q4() %>" size="100"/>
				</td>
			</tr>
			<tr>
				<th>보기 4-1</th>
				<td style="text-align:left">
					<input type="text" name="guide_q4_a1" id="guide_q4_a1" value="<%=guidebean.getGuide_q4_a1() %>" size="40"/>
				</td>
			</tr>
			<tr>
				<th>보기 4-2</th>
				<td style="text-align:left">
					<input type="text" name="guide_q4_a2" id="guide_q4_a2" value="<%=guidebean.getGuide_q4_a2() %>" size="40"/>
				</td>
			</tr>
			<tr>
				<th>보기 4-3</th>
				<td style="text-align:left">
					<input type="text" name="guide_q4_a3" id="guide_q4_a3" value="<%=guidebean.getGuide_q4_a3() %>" size="40"/>
				</td>
			</tr>
			
			<tr>
				<td colspan=2 id="tableBottom" style="text-align:center;">
					<input type="submit" value="수정" />
					<!-- <input type="reset" value="취소" onclick="$('#bbs_name').focus();"/> -->
					<input type="button" value="목록보기" onclick="location='admin_guide_list.do'"/>
				</td>
			</tr>
		</table>
	</form>	
	 
	 </div><!-- end content_c -->
	</div><!-- end content_wrapper -->

<div class="clear"></div>

<%@ include file="../include/footer.jsp" %>
	
	
	
	
	
	
	
	