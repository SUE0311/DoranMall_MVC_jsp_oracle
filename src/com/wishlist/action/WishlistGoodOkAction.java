package com.wishlist.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wishlist.dao.WishlistBean;
import com.wishlist.dao.WishlistDAO;

import com.mtory.action.Action;
import com.mtory.action.ActionForward;

public class WishlistGoodOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		int num = Integer.parseInt(request.getParameter("num"));
		int page = Integer.parseInt(request.getParameter("page"));
		
		String state = request.getParameter("state");
		
		WishlistDAO wd = new WishlistDAO();
		
		if(state.equals("good")){ //내용보기 일때 조회수 증가시킨다
			wd.addWishlistGood(num); //자료실 번호에 해당하는 조회수 증가 메소드 호출
		}
		
		response.sendRedirect("./wishlist_list.do");
		
		return null;
	}

}
