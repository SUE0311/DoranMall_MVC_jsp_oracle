<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*" %>
<%
String zipcode,addr,addr2;
String dong = (String)request.getAttribute("dong"); 
List zipcodeList = (ArrayList)request.getAttribute("zipcodeList");
/* out.println(dong);
out.println(zipcodeList); */

%>
<html>
<head>
<title>우편번호 검색</title>
<SCRIPT>
<!--
function selectnow() {

	/* select 객체에서 검색된 동을 선택했을때 change 이벤트가 발생한다.
 	여기서 여러분들이 이해할것이 있다. 바로 form 객체가 두개 써였다는 것이다. 그 이유는 동을입력하지 않았을때 실행과 동을 입력하고 검색된 동을 선택할수 있도록 하기 위해서
	postform과 postform2 두개의 폼객체 네임 이름을 사용하였다. 유의 하기 바란다. zip 변수에 선택된 주소가 저장된다. 

	zip1 변수에는 우편번호 앞 세자리를 저장하고 zip2 변수에는 우편번호 뒷자리 세자리를 저장한다. 그리고
	addr1변수에는 나머지 주소를 저장시켜준다.

	여기서 length-1 을 해준 이유는 substring 메서드는 문자열 첫 시작이 0부터 시작하고 length 속성은 첫 시작이 1부터 시작하기 때문이다. 그리고 둘다 String 객체 하위의 구성요소라는 것을 꼭 알아주기 바란다.

	*/

	var zip=document.postform.post_list.value;
	var zip1=zip.substring(0,3);
	var zip2=zip.substring(4,7);
	var addr2=zip.substring(7, (zip.length));

	opener.document.client_join_form.client_zip1.value=zip1;

	/* 회원가입창에 첫번째 세자리 우편번호를 입력한다. */

	opener.document.client_join_form.client_zip2.value=zip2;

	/* 회원가입창에 두번째 세자리 우편번호를 입력한다. */

	opener.document.client_join_form.client_addr1.value=addr2;

	/* 회원가입창에 나머지 주소를  입력한다. */

	parent.window.close();

	/* 그리고 윈도우 창을 닫아준다.  */

}

function check(form){
	if(form.dong.value==""){
		alert("동을 입력해 주세요!");
		form.dong.focus();
	return false;
	}
}

//-->
</SCRIPT>
<style type="text/css">
<!--
INPUT,SELECT{font-family: Dotum;font-size:9pt;;}
.style1 {
	color: #466D1B;
	font-weight: bold;
	font-size: 12px;
}
-->
</style>
</head>
<body onload="postform.dong.focus();" bgcolor="#FFFFFF" topmargin="0" leftmargin="0">
<form method="post" action="zipcode_ok.do" name="postform" onsubmit="return check(this)">
<table width="414" height="100" border="0" align="center" cellpadding="0" cellspacing="0">
 <tr> 
  <td bgcolor="#999999" align="center"><input type="image" src="images/ZipCode_img01.gif" width="413" height="58"></td>
 </tr>
 <tr> 
  <td bgcolor="f5ffea" align="center"> 
   <strong><font color="#466d1b"><span class="style1">[거주지의 면.동을 입력하고 '찾기'버튼을 누르세요!!!]</span></font></strong>
  </td>
 </tr>
 <tr height="30"> 
  <td bgcolor="f5ffea" align="center"><input type="text" name="dong" value size="10" maxlength="10">&nbsp;<input type="image"  src="images/m-i02.gif" width="69" height="19" align="absmiddle" onfocus="this.blur()"></td>
 </tr>
 <%if(dong != null){ %>
 <%if(zipcodeList != null && zipcodeList.size() != 0){%>
 <tr> 
  <td bgcolor="f5ffea" height="30" align="center">
   <SELECT NAME="post_list" onchange="selectnow()">
    <option value="">----주소를 선택하세요----</option>
	<%for(int i = 0; i < zipcodeList.size(); i++) {	
		String data=(String)zipcodeList.get(i);
		StringTokenizer st = new StringTokenizer(data,",");
		zipcode = st.nextToken();
		addr = st.nextToken();
		addr2 = st.nextToken();
		String totaladdr = zipcode + addr;
	%>
	<option value="<%=totaladdr%>">[<%=zipcode%>]&nbsp;<%=addr%></option>
	<%}%>
   </SELECT>
  </td>
 </tr>
 <%}else{ %>
 <tr>
  <td bgcolor="f5ffea" height="30" align="center"><font color="#466d1b"><span class="style1">검색 결과가 없습니다.</span></font></td>
 </tr>
 <%}}%>
 <tr> 
  <td bgcolor="508C0F" colspan="3" height="3"></td>
 </tr>
</table>
</form>
</body>
</html>