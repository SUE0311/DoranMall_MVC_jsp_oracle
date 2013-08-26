package com.wishlist.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wishlist.dao.WishlistBean;
import com.wishlist.dao.WishlistDAO;

import com.mtory.action.Action;
import com.mtory.action.ActionForward;

public class WishlistReplyOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		WishlistDAO wd = new WishlistDAO();
		WishlistBean wishlistbean = new WishlistBean();
		
		//Ŭ���̾�Ʈ���� ������ �����͸� �޴´�. 
		String wishlist_id = request.getParameter("wishlist_id").trim();
		String wishlist_pass = request.getParameter("wishlist_pass").trim();
		String wishlist_title = request.getParameter("wishlist_title").trim();
		String wishlist_content = request.getParameter("wishlist_content").trim();
		
		int wishlist_num = Integer.parseInt(request.getParameter("wishlist_num"));
		
		int wishlist_re_ref = Integer.parseInt(request.getParameter("wishlist_re_ref"));
		int wishlist_re_lev = Integer.parseInt(request.getParameter("wishlist_re_lev"));
		int wishlist_re_seq = Integer.parseInt(request.getParameter("wishlist_re_seq"));
		
		int page=Integer.parseInt(request.getParameter("page"));
		
		//�ڹٺ��� setter()�� �̿��Ͽ� �Ӽ��� �����Ѵ�.
		wishlistbean.setWishlist_num(wishlist_num);
		wishlistbean.setWishlist_re_ref(wishlist_re_ref);
		wishlistbean.setWishlist_re_lev(wishlist_re_lev);
		wishlistbean.setWishlist_re_seq(wishlist_re_seq);
		
		wishlistbean.setWishlist_id(wishlist_id);
		wishlistbean.setWishlist_pass(wishlist_pass);
		wishlistbean.setWishlist_title(wishlist_title);
		wishlistbean.setWishlist_content(wishlist_content);
		
		//DAO���� �亯���� �����ϴ� �޼ҵ� ȣ��
		wd.wishlistReply(wishlistbean);
		
		//�亯�� ����� ���� ���ؼ� �ڷ�� ��� ���� �������� �̵�
		response.sendRedirect("wishlist_list.do?page=" + page);
		
		return null;
	}

}
