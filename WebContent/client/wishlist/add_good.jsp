<%@ page contentType="text/html; charset=UTF-8"%>

<%@ page import="com.wishlist.dao.*" %>

<%
	request.setCharacterEncoding("UTF-8");
	
	int num = Integer.parseInt(request.getParameter("wishlist_num"));
	//int good = Integer.parseInt(request.getParameter("add_wishlist_good"));
	
	WishlistDAO wd = new WishlistDAO();
	
	//수정 성공했는지 체크하는 메소드
	int re = wd.addWishlistGood(num);
	//re=1이면 성공, 아니면 실패
	
	//ajax 로 값이 넘어감(출력문의 기능이 아님)
	//function rec_good()
	out.println(re);
%>