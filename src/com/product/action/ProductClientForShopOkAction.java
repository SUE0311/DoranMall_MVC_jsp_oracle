package com.product.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.product.dao.ProductBean;
import com.product.dao.ProductDAO;
import com.mtory.action.Action;
import com.mtory.action.ActionForward;

public class ProductClientForShopOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		response.setContentType("text/html;charset=UTF-8");
	    request.setCharacterEncoding("UTF-8");
	    
	    String shop = request.getParameter("product_shop");
	    System.out.println("============ 몇번 shop? " + shop);

		ProductDAO prodao = new ProductDAO();
		
		//번호에 해당하는 디비 내용을 가져오는 메소드 호출
		ProductBean probean = prodao.getClientForShop(shop);
		
		//해당하는 디비 내용을 bbsdate 키에 저장한다.
		request.setAttribute("prodata", probean);	//레코드 저장
		//request.setAttribute("page", page);		//쪽번호 저장
		
		//포워딩 페이지 지정
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		
		forward.setPath("./client/product/product01.jsp");
		
		return forward;
	}
}
