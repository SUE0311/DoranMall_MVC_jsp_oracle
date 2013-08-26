<!-- 
# 작성자 : 이지수
# 작성일 : 2013.08.14
-->

<%@ page contentType="text/html; charset=UTF-8"%>

<%@ include file="../include/header.jsp" %>

<link rel="stylesheet" type="text/css" href="./css/client.css" />
<script src="./js/jquery.js"></script>
<script src="./js/client.js"></script>

<div class="clear"></div>

<div id="content_wrapper">

<%@ include file="../include/left_menu.jsp" %>

	<div id="content_c">

	<p id="titleLogin">도란도란 공동구매 샵 회원 가입</p>
	
	<div id="descPage">
		<p>반갑습니다. 저희 사이트에 방문해주셔서 감사합니다. 언제나 고객님들과 함께 아름다운 인연을 만들어가겠습니다.     	
		</p>
	</div>
	
	<div id="contentArea">
	
		<form method="post" action="client_join_ok.do" name="client_join_form" id="client_join_form"
			onsubmit="return join_check()">
			
		<table id="clientTable">
			<tr id="tableTop">
				<td colspan="3">
					* 모든 빈칸은 필수 입력입니다. 
				</td>
			</tr>
			<tr>
				<th width="20%">
					아이디
				</th>
				<td>
					<input type="text" name="client_id" id="client_id" size="55" placeholder="4자 이상~12자 이하  | 영문소문자,숫자,_ 조합만 가능"/>
					<input type="button" value="아이디 중복체크" onclick="id_check()" />
					<br>
					<span id="idcheck"></span>	
				</td>
			</tr>
			<tr>
				<th>
					 비밀번호
				</th>
				<td>
					<input type="password" name="client_pass" id="client_pass" size="55" placeholder="필수입력"/>
				</td>
			</tr>
			<tr>
				<th>
					이름
				</th>
				<td>
					<input type="text" name="client_name" id="client_name" size="55" />
				</td>
			</tr> 
			<tr>
				<th>우편번호</th>
				<td>
					<input type="text" name="client_zip1" id="client_zip1" size="3" 
						readonly onclick="post_search()"/> - 
					<input type="text" name="client_zip2" id="client_zip2" size="3" 
						readonly onclick="post_search()"/>	
						<!-- readonly 속성은 커서가 입력상자에 들어가지 못하게 하는 기능으로
							읽기 전용 속성을 지정해준다. 만약 입력상자를 클릭하면 post_search()함수를 
							호출하도록 지정해있다. -->
				<input type="button" value="우편번호 찾기" onclick="post_check()"/> 
				</td>
			</tr>
			<tr>
				<th>주소</th>
				<td><input type="text" name="client_addr1" id="client_addr1" size="55" readonly onclick="post_search()"/></td>
			</tr>
			<tr>
				<th>나머지 주소</th>
				<td><input type="text" name="client_addr2" id="client_addr2" size="55" /></td>
			</tr>
			<tr>
				<th>
					연락처
				</th>
				<td>
					<input type="text" name="client_tel" id="client_tel" size="55" />
				</td>
			</tr>
			<tr>
				<th>
					이메일
				</th>
				<td>
					<input type="text" name="client_email" id="client_email" size="55" />
				</td>
			</tr>
			<tr>
					<td colspan=2 id="tableBottom" style="text-align:center;">
						<input type="submit" value="등록" />
						<input type="button" value="뒤로" onclick="history.go(-1)"/>
					</td>
				</tr>
		 </table>
		 
		 </form>
	
	</div><!-- end contentArea -->

	</div><!-- end content_c -->
</div><!-- end content_wrapper -->

<div class="clear"></div>

<%@ include file="../include/footer.jsp" %>