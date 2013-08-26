package com.company.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.company.dao.CompanyBean;
import com.company.dao.CompanyDAO;
import com.mtory.action.Action;
import com.mtory.action.ActionForward;

public class CompanyContOkAction implements Action {

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
		
		System.out.println("=============== " + admin_auth);
		
		int num = Integer.parseInt(request.getParameter("num"));
		int page = Integer.parseInt(request.getParameter("page"));
		
		//get ������� �Ѿ� �� ���� ���� �Ķ���ͷ� �޴´�. 
		String state = request.getParameter("state");
		//state = "cont"�� �ش� �ڷ���� ������ ���ٴ� �ǹ���
		
		CompanyDAO cd = new CompanyDAO();
		
		//��ȣ�� �ش��ϴ� ��� ������ �������� �޼ҵ� ȣ��
		CompanyBean combean = cd.getCont(num);
		
		//�ش��ϴ� ��� ������ bbsdate Ű�� �����Ѵ�.
		request.setAttribute("comdata", combean);	//���ڵ� ����
		request.setAttribute("page", page);		//�ʹ�ȣ ����
		
		//������ ������ ����
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		
		if(admin_auth.equals("3")){
			if(state.equals("cont")){ //�ڷ���� ���뺸�� �� ���
				forward.setPath("./admin/company/admin_company_cont.jsp"); //view ������ ����
			}else if(state.equals("edit")){	//�Խù� ���� �� ���
				forward.setPath("./admin/company/admin_company_edit.jsp"); //view ������ ����
			}else if(state.equals("delete")){	//�Խù� ���� �� ���
				forward.setPath("./admin/company/admin_company_delete.jsp"); //view ������ ����
			}
			return forward;
		}else if(admin_auth.equals("2")){
			if(state.equals("cont")){ //�ڷ���� ���뺸�� �� ���
				forward.setPath("./admin/company/admin_company_cont.jsp"); //view ������ ����
				return forward;
			}else if(state.equals("edit")){	//�Խù� ���� �� ���
				forward.setPath("./admin/company/admin_company_edit.jsp"); //view ������ ����
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
				forward.setPath("./admin/company/admin_company_cont.jsp"); //view ������ ����
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
