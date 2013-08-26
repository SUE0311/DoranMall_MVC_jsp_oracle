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
		int limit = 10; //한 페이지에 보여지는 목록수(10개 지정)
		
		if(request.getParameter("page")!=null){
			//넘어온 페이지 번호(쪽번호)가 있는 경우
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		//DAO의 총 레코드 수를 구하는 메소드 호출  
		int listcount = wishlistdao.getListCount(); 

//		List<WishlistBean> wishlistList;
		
//		if(state.equals("normal")){
//			List<WishlistBean> wishlistList = wishlistdao.getwishlistList(page, limit);
//		}else if(state.equals("search")){
//			List<WishlistBean> wishlistList = wishlistdao.getwishlistSearchList(page, limit, searchWish);
//		}
		
		List<WishlistBean> wishlistList = wishlistdao.getwishlistSearchList(page, limit, searchWish);
		
		//총 페이지수
		int maxpage = (int)((double)listcount/limit+0.95);
		//시작 페이지수
		int startpage = (((int)((double)page/10+0.9))-1)*10+1;
		//끝 페이지수
		int endpage = maxpage;
		
		if(endpage > startpage+10-1){
			endpage = startpage+10-1;
		}
		
		//각 키에 페이지 관련 값을 저장한다.
		request.setAttribute("page", page);   		  	 //쪽번호
		request.setAttribute("maxpage", maxpage);        //총페이지수
		request.setAttribute("startpage", startpage);    //시작페이지
		request.setAttribute("endpage", endpage);    //시작페이지
		
		request.setAttribute("listcount", listcount);    //총레코드 수
		request.setAttribute("wishlistList", wishlistList);    	 //게시물 목록
		
		request.setAttribute("limit", limit);  			 //레코드 제한
		
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		
		forward.setPath("./client/wishlist/wishlist_list.jsp"); //뷰 페이지
		
		return forward;
	}

}
