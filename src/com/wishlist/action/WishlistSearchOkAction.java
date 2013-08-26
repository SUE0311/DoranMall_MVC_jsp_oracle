package com.wishlist.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import com.wishlist.dao.WishlistBean;
import com.wishlist.dao.WishlistDAO;

import com.mtory.action.Action;
import com.mtory.action.ActionForward;

public class WishlistSearchOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		WishlistDAO wishlistdao = new WishlistDAO();
		
//		String state = request.getParameter("state");
		String searchWish = request.getParameter("searchWish");
		
		System.out.println("=========== " + searchWish);
		
		int page = 1;
		int limit = 10; //�� �������� �������� ��ϼ�(10�� ����)
		
		if(request.getParameter("page")!=null){
			//�Ѿ�� ������ ��ȣ(�ʹ�ȣ)�� �ִ� ���
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		//DAO�� �� ���ڵ� ���� ���ϴ� �޼ҵ� ȣ��  
		int listcount = wishlistdao.getListCount(); 

//		List<WishlistBean> wishlistList;
		
//		if(state.equals("normal")){
//			List<WishlistBean> wishlistList = wishlistdao.getwishlistList(page, limit);
//		}else if(state.equals("search")){
//			List<WishlistBean> wishlistList = wishlistdao.getwishlistSearchList(page, limit, searchWish);
//		}
		
		List<WishlistBean> wishlistList = wishlistdao.getwishlistSearchList(page, limit, searchWish);
		
		//�� ��������
		int maxpage = (int)((double)listcount/limit+0.95);
		//���� ��������
		int startpage = (((int)((double)page/10+0.9))-1)*10+1;
		//�� ��������
		int endpage = maxpage;
		
		if(endpage > startpage+10-1){
			endpage = startpage+10-1;
		}
		
		//�� Ű�� ������ ���� ���� �����Ѵ�.
		request.setAttribute("page", page);   		  	 //�ʹ�ȣ
		request.setAttribute("maxpage", maxpage);        //����������
		request.setAttribute("startpage", startpage);    //����������
		request.setAttribute("endpage", endpage);    //����������
		
		request.setAttribute("listcount", listcount);    //�ѷ��ڵ� ��
		request.setAttribute("wishlistList", wishlistList);    	 //�Խù� ���
		
		request.setAttribute("limit", limit);  			 //���ڵ� ����
		
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		
		forward.setPath("./client/wishlist/wishlist_list.jsp"); //�� ������
		
		return forward;
	}

}
