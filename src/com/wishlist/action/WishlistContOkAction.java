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
		
		//get ������� �Ѿ� �� ���� ���� �Ķ���ͷ� �޴´�. 
		String state = request.getParameter("state");
		//state = "cont"�� �ش� �ڷ���� ������ ���ٴ� �ǹ���
		
		WishlistDAO wd = new WishlistDAO();
		
		if(state.equals("cont")){ //���뺸�� �϶� ��ȸ�� ������Ų��
			wd.wishlistHit(num); //�ڷ�� ��ȣ�� �ش��ϴ� ��ȸ�� ���� �޼ҵ� ȣ��
		}
		//��ȣ�� �ش��ϴ� ��� ������ �������� �޼ҵ� ȣ��
		WishlistBean wishlist = wd.getCont(num);
		
		//�ش��ϴ� ��� ������ wishlistdate Ű�� �����Ѵ�.
		request.setAttribute("wishlistdata", wishlist);	//���ڵ� ����
		request.setAttribute("page", page);		//�ʹ�ȣ ����
		
		//������ ������ ����
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		
		if(state.equals("cont")){ //�ڷ���� ���뺸�� �� ���
			forward.setPath("./client/wishlist/wishlist_cont.jsp"); //view ������ ����
		}else if(state.equals("edit")){	//�Խù� ���� �� ���
			forward.setPath("./client/wishlist/wishlist_edit.jsp"); //view ������ ����
		}else if(state.equals("delete")){	//�Խù� ���� �� ���
			forward.setPath("./client/wishlist/wishlist_delete.jsp"); //view ������ ����
		}else if(state.equals("reply")){	//�亯�� �϶�
			forward.setPath("./client/wishlist/wishlist_reply.jsp"); //view ������ ����
		}	
		return forward;
	}

}
