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
	    System.out.println("============ ��� shop? " + shop);

		ProductDAO prodao = new ProductDAO();
		
		//��ȣ�� �ش��ϴ� ��� ������ �������� �޼ҵ� ȣ��
		ProductBean probean = prodao.getClientForShop(shop);
		
		//�ش��ϴ� ��� ������ bbsdate Ű�� �����Ѵ�.
		request.setAttribute("prodata", probean);	//���ڵ� ����
		//request.setAttribute("page", page);		//�ʹ�ȣ ����
		
		//������ ������ ����
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		
		forward.setPath("./client/product/product01.jsp");
		
		return forward;
	}
}
