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
		
		//클라이언트에서 히든(hidden)으로 넘어온 값을 파라미터로 받는다.
		//글번호(num)는 해당 삭제할 레코드를 찾기 위해서
		//비밀번호(del_pwd)는 삭제가 입력한 비밀번호와 일치하는 지 비교하기 위해서
		int wishlist_num = Integer.parseInt(request.getParameter("num"));
		int page = Integer.parseInt(request.getParameter("page"));
		
		//사용자가 입력한 비밀번호를 파라미터로 받는다. 
		String pwd = request.getParameter("pwd").trim();
		
		//이진 업로드 파일 경로
		String up = "C:/WebProject/DoranMall/WebContent/upload"; 
		
		//비밀번호에 해당하는 디비 내용을 가져온다. 
		WishlistBean wishlist = wd.getCont(wishlist_num);
		
		String fname = wishlist.getWishlist_file();
		
		if(!wishlist.getWishlist_pass().equals(pwd)){ //비밀번호가 다른 경우
			out.println("<script>");
			out.println("alert('비밀번호가 일치하지 않습니다.')");
			out.println("history.go(-1)"); //바로 이전 페이지로 이동
			out.println("</script>");
		}else{//비번이 같은 경우
			wd.wishlistDelete(wishlist_num); //레코드를 디비로부터 삭제하는 메소드
			
			if(fname != null){//이진 파일이 존재한다면
				File file = new File(up+fname);
				file.delete(); //기존 이진파일을 폴더로부터 삭제한다.
			}
			
			//게시물 목록으로 페이지 번호를 넘기면서 해당 페이지 목록으로 이동
			response.sendRedirect("wishlist_list.do?page=" + page);
		}
		return null;
		
	}

}
