package com.wishlist.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wishlist.dao.WishlistBean;
import com.wishlist.dao.WishlistDAO;

import com.mtory.action.Action;
import com.mtory.action.ActionForward;

public class WishlistContOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		int num = Integer.parseInt(request.getParameter("num"));
		int page = Integer.parseInt(request.getParameter("page"));
		
		//get 방식으로 넘어 온 구분 값을 파라미터로 받는다. 
		String state = request.getParameter("state");
		//state = "cont"는 해당 자료실의 내용을 본다는 의미임
		
		WishlistDAO wd = new WishlistDAO();
		
		if(state.equals("cont")){ //내용보기 일때 조회수 증가시킨다
			wd.wishlistHit(num); //자료실 번호에 해당하는 조회수 증가 메소드 호출
		}
		//번호에 해당하는 디비 내용을 가져오는 메소드 호출
		WishlistBean wishlist = wd.getCont(num);
		
		//해당하는 디비 내용을 wishlistdate 키에 저장한다.
		request.setAttribute("wishlistdata", wishlist);	//레코드 저장
		request.setAttribute("page", page);		//쪽번호 저장
		
		//포워딩 페이지 지정
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		
		if(state.equals("cont")){ //자료실의 내용보기 일 경우
			forward.setPath("./client/wishlist/wishlist_cont.jsp"); //view 페이지 지정
		}else if(state.equals("edit")){	//게시물 수정 일 경우
			forward.setPath("./client/wishlist/wishlist_edit.jsp"); //view 페이지 지정
		}else if(state.equals("delete")){	//게시물 삭제 일 경우
			forward.setPath("./client/wishlist/wishlist_delete.jsp"); //view 페이지 지정
		}else if(state.equals("reply")){	//답변글 일때
			forward.setPath("./client/wishlist/wishlist_reply.jsp"); //view 페이지 지정
		}	
		return forward;
	}

}
