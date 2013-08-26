<%@ page contentType="text/html; charset=UTF-8"%>

<%@ page import="com.company.dao.*" %>

<%
	CompanyBean combean=(CompanyBean)request.getAttribute("comdata"); 
    // Object클래스의 강제 다운캐스팅
    String com_cont=combean.getCom_cont().replace("\n","<br/>");
   	//글내용을 입력할때 엔터키 친부분을 다음줄로  개행한다.
   	int nowpage=((Integer)request.getAttribute("page")).intValue();
  	//getAttribute()메서드로 반환되는값은 자바 최상위 클래스 Object 형이다.
  	//이것을 정수형 변수에 저장하기 위해서 intValue()메서드로 정수형 숫자로 바꿔서 저장.
%>  

<%@ include file="../include/header.jsp" %>

<link rel="stylesheet" type="text/css" href="./css/admin_company.css" />
<script src="./js/admin_company.js"></script>

<div class="clear"></div>

<div id="content_wrapper">

<%@ include file="../include/left_menu.jsp" %>

	<div id="content_c">
	
	<p id="title">입점 신청 기업  상세보기</p>
	
	<table id="comTable">
	
		<tr>
			<th width="20%">
				기업 번호
			</th>
			<td>
				<%=combean.getCom_num()%>
			</td>
		</tr>
		<tr>
			<th>
				 신청 날짜
			</th>
			<td>
				<%=combean.getCom_date()%>
			</td>
		</tr>
		<tr>
			<th>
				기업 명
			</th>
			<td>
				<%=combean.getCom_co()%>
			</td>
		</tr>
		<tr>
			<th>
				담당자 이름
			</th>
			<td>
				<%=combean.getCom_name()%>
			</td>
		</tr>
		<tr>
			<th>
				연락처
			</th>
			<td>
				<%=combean.getCom_tel()%>
			</td>
		</tr>
		<tr>
			<th>
				이메일
			</th>
			<td>
				<%=combean.getCom_mail()%>
			</td>
		</tr>
		<tr>
			<th>
				웹사이트
			</th>
			<td>
			<%if(combean.getCom_website() == null){ %>
				<div style="color:rgb(169,3,41)">내용 없음</div>
			<%}else{ %>	
				<%=combean.getCom_website()%>
			<%} %>
			</td>
		</tr>
		<tr>
			<th>
				입점 신청 상품
			</th>
			<td>
				<%=combean.getCom_product()%>
			</td>
		</tr>
		<tr>
			<th>
				제품 가격
			</th>
			<td>
				<%=combean.getCom_price()%>
			</td>
		</tr>
		<tr>
			<th>
				제품 소개
			</th>
			<td>
				<%=combean.getCom_cont() %>
			</td>
		</tr>
		<tr>
			<th>
				제품 이미지
			</th>
			<td>
			<%if(combean.getCom_file1() == null){ %>
				<div style="color:rgb(169,3,41)">내용 없음</div>
			<%}else{ %>	
				<img src="./upload<%=combean.getCom_file1()%>" width="300" height="200" >
			<%} %>
			</td>
		</tr>
		<tr>
			<td colspan=7 id="tableBottom">
				<input type="button" value="수정"
			    	onclick="location='admin_company_cont.do?num=<%=combean.getCom_num()%>&page=<%=nowpage%>&state=edit'"/>
			 	<input type="button" value="삭제" 
			    	onclick="location='admin_company_cont.do?num=<%=combean.getCom_num()%>&page=<%=nowpage%>&state=delete'"/>
			 	<input type="button" value="목록"
			  	    onclick="location='admin_company_list.do?page=<%=nowpage%>'" />
			</td>
		</tr>
	 </table>
	
	</div><!-- end content_c -->
	</div><!-- end content_wrapper -->

<div class="clear"></div>

<%@ include file="../include/footer.jsp" %>