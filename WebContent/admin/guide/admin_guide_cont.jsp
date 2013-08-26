<!-- 
# 작성자 : 이지수
# 작성일 : 2013.08.05 
-->

<%@ page contentType="text/html; charset=UTF-8"%>

<%@ page import="com.guide.dao.*" %>

<%
	GuideBean guidebean = (GuideBean)request.getAttribute("guidedata");

    /* String guide_cont= guidebean.getCont().replace("\n","<br/>"); */

   	int nowpage=((Integer)request.getAttribute("page")).intValue();
%>  

<%@ include file="../include/header.jsp" %>

<link rel="stylesheet" type="text/css" href="./css/admin_guide.css" />
<script src="./js/admin_guide.js"></script>

<div class="clear"></div>

<div id="content_wrapper">

<%@ include file="../include/left_menu.jsp" %>

	<div id="content_c">
	
	<p id="title">쇼핑 가이드  상세보기</p>
	
	<table id="guideTable">
	
		<tr>
			<th width="20%">
			 	번호
			</th>
			<td>
				<%=guidebean.getGuide_num() %>
			</td>
		</tr>
		<tr>
			<th>
				가이드 제품
			</th>
			<td>
				<%=guidebean.getGuide_product()%>
			</td>
		</tr>
		<tr>
			<th>
				 게시 시작 날짜
			</th>
			<td>
				<%=guidebean.getGuide_s_date()%>
			</td>
		</tr>
		<tr>
			<th>
				게시 마감 날짜
			</th>
			<td>
				<%=guidebean.getGuide_e_date()%>
			</td>
		</tr>
		<tr>
			<th>
				게시 회차
			</th>
			<td>
				<%=guidebean.getGuide_post()%>&nbsp;회
			</td>
		</tr>
		<tr>
			<th>
				게시 상태
			</th>
			<td>
				<%if(guidebean.getGuide_state() == -1){ %>
				<div style="color:rgb(169,3,41)">비노출</div>
				<%}else if(guidebean.getGuide_state() == 0){ %>	
					<div style="color:rgb(169,3,41)">노출</div>
				<%}%>
			</td>
		</tr>
		<tr>
			<th>
				문제1
			</th>
			<td>
				<%=guidebean.getGuide_q1() %>
			</td>
		</tr>
		<tr>
			<th>
				보기 1-1
			</th>
			<td>
				<%=guidebean.getGuide_q1_a1() %>
			</td>
		</tr>
		<tr>
			<th>
				보기 1-2
			</th>
			<td>
				<%=guidebean.getGuide_q1_a2()%>
			</td>
		</tr>
		<tr>
			<th>
				보기 1-3
			</th>
			<td>
				<%=guidebean.getGuide_q1_a3()%>
			</td>
		</tr>
		<tr>
			<th>
				문제2
			</th>
			<td>
				<%=guidebean.getGuide_q2()%>
			</td>
		</tr>
		<tr>
			<th>
				보기 2-1
			</th>
			<td>
				<%=guidebean.getGuide_q2_a1()%>
			</td>
		</tr>
		<tr>
			<th>
				보기 2-2
			</th>
			<td>
				<%=guidebean.getGuide_q2_a2()%>
			</td>
		</tr>
		<tr>
			<th>
				보기 2-3
			</th>
			<td>
				<%=guidebean.getGuide_q2_a3()%>
			</td>
		</tr>
		<tr>
			<th>
				문제3
			</th>
			<td>
				<%=guidebean.getGuide_q3()%>
			</td>
		</tr>
		<tr>
			<th>
				보기 3-1
			</th>
			<td>
				<%=guidebean.getGuide_q3_a1()%>
			</td>
		</tr>
		<tr>
			<th>
				보기 3-2
			</th>
			<td>
				<%=guidebean.getGuide_q3_a2()%>
			</td>
		</tr>
		<tr>
			<th>
				보기 3-3
			</th>
			<td>
				<%=guidebean.getGuide_q3_a3()%>
			</td>
		</tr>
		<tr>
			<th>
				문제4
			</th>
			<td>
				<%=guidebean.getGuide_q4()%>
			</td>
		</tr>
		<tr>
			<th>
				보기 4-1
			</th>
			<td>
				<%=guidebean.getGuide_q4_a1() %>
			</td>
		</tr>
		<tr>
			<th>
				보기 4-2
			</th>
			<td>
				<%=guidebean.getGuide_q4_a2()%>
			</td>
		</tr>
		<tr>
			<th>
				보기 4-3
			</th>
			<td>
				<%=guidebean.getGuide_q4_a3()%>
			</td>
		</tr>
		<tr>
			<td colspan=7 id="tableBottom">
				<input type="button" value="수정"
			    	onclick="location='admin_guide_cont.do?num=<%=guidebean.getGuide_num()%>&page=<%=nowpage%>&state=edit'"/>
			 	<input type="button" value="삭제" 
			    	onclick="location='admin_guide_cont.do?num=<%=guidebean.getGuide_num()%>&page=<%=nowpage%>&state=delete'"/>
			 	<input type="button" value="목록"
			  	    onclick="location='admin_guide_list.do?page=<%=nowpage%>'" />
			</td>
		</tr>
	 </table>
	
	</div><!-- end content_c -->
	</div><!-- end content_wrapper -->

<div class="clear"></div>

<%@ include file="../include/footer.jsp" %>