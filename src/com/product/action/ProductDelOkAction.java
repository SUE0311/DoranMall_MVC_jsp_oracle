package com.product.action;

import java.io.File;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.product.dao.ProductBean;
import com.product.dao.ProductDAO;
import com.mtory.action.Action;
import com.mtory.action.ActionForward;

public class ProductDelOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		ProductDAO prodao = new ProductDAO();
		
		int pro_num = Integer.parseInt(request.getParameter("num"));
		int page = Integer.parseInt(request.getParameter("page"));
		
		//����ڰ� �Է��� ��й�ȣ�� �Ķ���ͷ� �޴´�. 
		String pwd = request.getParameter("admin_pwd").trim();
		
		//���� ���ε� ���� ���
		String up = "C:/WebProject/DoranMall/WebContent/upload";
		
		//��й�ȣ�� �ش��ϴ� ��� ������ �����´�. 
		ProductBean probean = prodao.getCont(pro_num);
		
		String fname01 = probean.getProduct_description();
		String fname02 = probean.getProduct_mainImg();
		String fname03 = probean.getProduct_thum01();
		String fname04 = probean.getProduct_thum02();
		String fname05 = probean.getProduct_thum03();
		String fname06 = probean.getProduct_video();
		
		prodao.productDelete(pro_num); //���ڵ带 ���κ��� �����ϴ� �޼ҵ�
		
		if(fname01 != null){//���� ������ �����Ѵٸ�
			File file01 = new File(up+fname01);
			File file02 = new File(up+fname02);
			File file03 = new File(up+fname03);
			File file04 = new File(up+fname04);
			File file05 = new File(up+fname05);
			File file06 = new File(up+fname06);
			
			file01.delete(); //���� ���������� �����κ��� �����Ѵ�.
			file02.delete();
			file03.delete();
			file04.delete();
			file05.delete();
			file06.delete();
		}
		
		//�Խù� ������� ������ ��ȣ�� �ѱ�鼭 �ش� ������ ������� �̵�
		response.sendRedirect("admin_product_list.do?page=" + page);
		
		/*if(pwd == "1234"){ //��й�ȣ�� "1234" �� ���
			prodao.companyDelete(pro_num); //���ڵ带 ���κ��� �����ϴ� �޼ҵ�
			
			if(fname != null){//���� ������ �����Ѵٸ�
				File file = new File(up+fname);
				file.delete(); //���� ���������� �����κ��� �����Ѵ�.
			}
			
			//�Խù� ������� ������ ��ȣ�� �ѱ�鼭 �ش� ������ ������� �̵�
			response.sendRedirect("admin_company_list.do?page=" + page);
		}else{//����̴ٸ� ���
			out.println("<script>");
			out.println("alert('��й�ȣ�� ��ġ���� �ʽ��ϴ�.')");
			out.println("history.go(-1)"); //�ٷ� ���� �������� �̵�
			out.println("</script>");
		}*/
		return null;
	}
}
