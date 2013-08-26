<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.admin.dao.*" %>

<%
	request.setCharacterEncoding("UTF-8");

	String id = request.getParameter("check_need_id"); //제이쿼리로 받는 유저가 입력한 id
	//String id = request.getParameter("memid");
	
	//out.println(id);
	
	AdminDAO md = new AdminDAO();
	
	//중복 아이디 체크 메소드 호출
	int re = md.checkMemberId(id);
	//re=1이면 중복, -1이면 사용 가능
	
	//ajax 로 값이 넘어감(출력문의 기능이 아님)
	//member.js
	
	out.println(re);
%>