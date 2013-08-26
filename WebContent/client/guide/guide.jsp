<!-- 
# 작성자 : 이지수
# 작성일 : 2013.08.05 
-->

<%@ page contentType="text/html; charset=UTF-8"%>

<%@ page import="com.guide.dao.*" %>

<%@ include file="../include/header.jsp" %>

<link rel="stylesheet" type="text/css" href="./css/guide.css" />
<script src="./js/guide.js"></script>

<!-- <script>

function test(){
	alert("테스트");
};

</script> -->

<div class="clear"></div>

<div id="content_wrapper">

<%@ include file="../include/left_menu.jsp" %>

	<div id="content_c">
	
	<p id="title">당신만을 위한 쇼핑 가이드</p>
	
	<div id="descPage">
		<p>고객 여러분을 위한 "도란도란 샵"의 특별한 공간!!<br>
		   경쟁력 있는 중소기업들이 내 놓은 3개의 제품 중 나에게 어울리는 제품이 무엇인지 궁금하시죠?<br>
		   그래서 준비했습니다~! 똑똑한 고객님들의 현명한 선택을 도울 수 있는 "쇼핑 가이드"를 이용해보세요.<br> 
		   아래에 제시된 몇 가지 질문에 체크만 해주시면 고객님이 찾고 싶은 제품으로 바로 이동할 수 있습니다.   	
		</p>
	</div>
	
	<div id="contentArea">

	<div id="guideF">

	<form method="post" name="guideClientF" id="guideClientF" onsubmit="guide_check()">
	
		<table id="guideF_table">
			<tr>
				<th>시작 날짜</th>
				<td style="text-align:left">
					${guidedata.guide_s_date}
				</td>
				<th>마감 날짜</th>
				<td style="text-align:left">
					${guidedata.guide_e_date}			
				</td>
			</tr>
			<tr>
				<th>금주의 제품</th>
				<td style="text-align:left" colspan=3>
					${guidedata.guide_product}
				</td>
			</tr>
			<tr>
				<th colspan=4 >
					문제1> ${guidedata.guide_q1}
				</th>
			</tr>
			<tr>
				<th>
					보기
				</th>
				<td style="text-align:left"  colspan=3>
					<input type="radio" value="1" name="opt1_1" id="opt1_1" />1)&nbsp;${guidedata.guide_q1_a1}
					<input type="radio" value="1" name="opt1_2" id="opt1_2" />2)&nbsp;${guidedata.guide_q1_a2}
					<input type="radio" value="1" name="opt1_3" id="opt1_3" />3)&nbsp;${guidedata.guide_q1_a3}
				</td>
			</tr>
			<tr>
				<th colspan=4 >
					문제2> ${guidedata.guide_q2}
				</th>
			</tr>
			<tr>
				<th>
					보기
				</th>
				<td style="text-align:left" colspan=3>
					<input type="radio" value="1" name="opt2_1" id="opt2_1" />1)&nbsp;${guidedata.guide_q2_a1}
					<input type="radio" value="1" name="opt2_2" id="opt2_2" />2)&nbsp;${guidedata.guide_q2_a2}
					<input type="radio" value="1" name="opt2_3" id="opt2_3" />3)&nbsp;${guidedata.guide_q2_a3}
				</td>
			</tr>
			<tr>
				<th colspan=4 >
					문제3> ${guidedata.guide_q3}
				</th>
			</tr>
			<tr>
				<th>
					보기
				</th>
				<td style="text-align:left" colspan=3>
					<input type="radio" value="1" name="opt3_1" id="opt3_1" />1)&nbsp;${guidedata.guide_q3_a1}
					<input type="radio" value="1" name="opt3_2" id="opt3_2" />2)&nbsp;${guidedata.guide_q3_a2}
					<input type="radio" value="1" name="opt3_4" id="opt3_4" />3)&nbsp;${guidedata.guide_q3_a3}
				</td>
			</tr>
			<tr>
				<td colspan=4 class="tableBottom">
					<input type="submit" value="제출" />
					<input type="button" value="추가 문제" id="spareBtn" onclick="spareQuiz()"/>
				</td>
			</tr>
		</table>
	</form>
	
	<form method="post" name="spareClientF" id="spareClientF" onsubmit="return spare_check()">
		<table id="spare_table" >
			<tr>
				<th colspan=2>
					문제4> ${guidedata.guide_q4}
				</th>
			</tr>
			<tr>
				<th>
					보기
				</th>
				<td style="text-align:left">
					<input type="radio" value="1" name="opt4_1" id="opt4_1" />1)&nbsp;${guidedata.guide_q4_a1}
					<input type="radio" value="1" name="opt4_2" id="opt4_2" />2)&nbsp;${guidedata.guide_q4_a2}
					<input type="radio" value="1" name="opt4_3" id="opt4_3" />3)&nbsp;${guidedata.guide_q4_a3}
				</td>
			</tr>
			<tr>
				<td colspan=2 class="tableBottom">
					<input type="submit" value="제출" />
				</td>
			</tr>
		</table>
	</form>
	
	
	</div><!-- end guideF -->
	

	</div><!-- end guideArea -->
	
	</div><!-- end content_c -->
</div><!-- end content_wrapper -->

<div class="clear"></div>

<%@ include file="../include/footer.jsp" %>