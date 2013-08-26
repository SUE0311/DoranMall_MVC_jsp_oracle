package com.product.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.product.dao.ProductBean;
import com.product.dao.ProductDAO;
import com.mtory.action.Action;
import com.mtory.action.ActionForward;

public class ProductContOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		//��� ��ũ�� ��ü�� �����Ѵ�. 
		PrintWriter out = response.getWriter();
		
		//���� ��ü�� ����
		HttpSession session = request.getSession();
		
		String admin_auth = (String)session.getAttribute("admin_auth");
		
		int num = Integer.parseInt(request.getParameter("num"));
		int page = Integer.parseInt(request.getParameter("page"));
		
		//get ������� �Ѿ� �� ���� ���� �Ķ���ͷ� �޴´�. 
		String state = request.getParameter("state");
		//state = "cont"�� �ش� �ڷ���� ������ ���ٴ� �ǹ���
		
		ProductDAO prodao = new ProductDAO();
		
		//��ȣ�� �ش��ϴ� ��� ������ �������� �޼ҵ� ȣ��
		ProductBean probean = prodao.getCont(num);
		
		//�ش��ϴ� ��� ������ bbsdate Ű�� �����Ѵ�.
		request.setAttribute("prodata", probean);	//���ڵ� ����
		request.setAttribute("page", page);		//�ʹ�ȣ ����
		
		//������ ������ ����
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);

		/*if(state.equals("cont")){ //�ڷ���� ���뺸�� �� ���
			forward.setPath("./admin/product/admin_product_cont.jsp"); //view ������ ����
		}else if(state.equals("edit")){	//�Խù� ���� �� ���
			forward.setPath("./admin/product/admin_product_edit.jsp"); //view ������ ����
		}else if(state.equals("delete")){	//�Խù� ���� �� ���
			forward.setPath("./admin/product/admin_product_delete.jsp"); //view ������ ����
		}
		
		return forward;*/
		
		if(admin_auth.equals("3")){
			if(state.equals("cont")){ //�ڷ���� ���뺸�� �� ���
				forward.setPath("./admin/product/admin_product_cont.jsp"); //view ������ ����
			}else if(state.equals("edit")){	//�Խù� ���� �� ���
				forward.setPath("./admin/product/admin_product_edit.jsp"); //view ������ ����
			}else if(state.equals("delete")){	//�Խù� ���� �� ���
				forward.setPath("./admin/product/admin_product_delete.jsp"); //view ������ ����
			}
			return forward;
		}else if(admin_auth.equals("2")){
			if(state.equals("cont")){ //�ڷ���� ���뺸�� �� ���
				forward.setPath("./admin/product/admin_product_cont.jsp"); //view ������ ����
				return forward;
			}else if(state.equals("edit")){	//�Խù� ���� �� ���
				forward.setPath("./admin/product/admin_product_edit.jsp"); //view ������ ����
				return forward;
			}else if(state.equals("delete")){	//�Խù� ���� �� ���
				out.println("<script>");
				out.println("alert('���� ������ �����ϴ�.')");
				out.println("history.go(-1)");
				out.println("</script>");
				return null;
			}
		}else if(admin_auth.equals("1")){
			if(state.equals("cont")){ //�ڷ���� ���뺸�� �� ���
				forward.setPath("./admin/product/admin_product_cont.jsp"); //view ������ ����
				return forward;
			}else if(state.equals("edit")){	//�Խù� ���� �� ���
				out.println("<script>");
				out.println("alert('���� ������ �����ϴ�.')");
				out.println("history.go(-1)");
				out.println("</script>");
				return null;
			}else if(state.equals("delete")){	//�Խù� ���� �� ���
				out.println("<script>");
				out.println("alert('���� ������ �����ϴ�.')");
				out.println("history.go(-1)");
				out.println("</script>");
				return null;
			}
		}	
		
		return null;
	}
}
