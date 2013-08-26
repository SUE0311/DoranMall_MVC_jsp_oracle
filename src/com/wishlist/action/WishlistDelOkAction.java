package com.wishlist.action;

import java.io.File;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wishlist.dao.WishlistBean;
import com.wishlist.dao.WishlistDAO;

import com.mtory.action.Action;
import com.mtory.action.ActionForward;

public class WishlistDelOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		WishlistDAO wd = new WishlistDAO();
		
		//Ŭ���̾�Ʈ���� ����(hidden)���� �Ѿ�� ���� �Ķ���ͷ� �޴´�.
		//�۹�ȣ(num)�� �ش� ������ ���ڵ带 ã�� ���ؼ�
		//��й�ȣ(del_pwd)�� ������ �Է��� ��й�ȣ�� ��ġ�ϴ� �� ���ϱ� ���ؼ�
		int wishlist_num = Integer.parseInt(request.getParameter("num"));
		int page = Integer.parseInt(request.getParameter("page"));
		
		//����ڰ� �Է��� ��й�ȣ�� �Ķ���ͷ� �޴´�. 
		String pwd = request.getParameter("pwd").trim();
		
		//���� ���ε� ���� ���
		String up = "C:/WebProject/DoranMall/WebContent/upload"; 
		
		//��й�ȣ�� �ش��ϴ� ��� ������ �����´�. 
		WishlistBean wishlist = wd.getCont(wishlist_num);
		
		String fname = wishlist.getWishlist_file();
		
		if(!wishlist.getWishlist_pass().equals(pwd)){ //��й�ȣ�� �ٸ� ���
			out.println("<script>");
			out.println("alert('��й�ȣ�� ��ġ���� �ʽ��ϴ�.')");
			out.println("history.go(-1)"); //�ٷ� ���� �������� �̵�
			out.println("</script>");
		}else{//����� ���� ���
			wd.wishlistDelete(wishlist_num); //���ڵ带 ���κ��� �����ϴ� �޼ҵ�
			
			if(fname != null){//���� ������ �����Ѵٸ�
				File file = new File(up+fname);
				file.delete(); //���� ���������� �����κ��� �����Ѵ�.
			}
			
			//�Խù� ������� ������ ��ȣ�� �ѱ�鼭 �ش� ������ ������� �̵�
			response.sendRedirect("wishlist_list.do?page=" + page);
		}
		return null;
		
	}

}
