package com.company.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.company.dao.CompanyBean;
import com.company.dao.CompanyDAO;
import com.mtory.action.Action;
import com.mtory.action.ActionForward;

public class CompanyListOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		//��� ��ũ�� ��ü�� �����Ѵ�. 
		PrintWriter out = response.getWriter();
		
		//���� ��ü�� ����
		HttpSession session = request.getSession();
		
		String id = (String)session.getAttribute("id");
		
		if(id==null){//id ���� ��ü�� null �� ��� 
			out.println("<script>");
			out.println("alert('������ ���� �޴��Դϴ�. �α������ֽʽÿ�.')");
			out.println("location='adminIndex.do'");
			out.println("</script>");
		}else{
			CompanyDAO comdao = new CompanyDAO();
			
			int page = 1;
			int limit = 10; //�� �������� �������� ��ϼ�(10�� ����)
			
			if(request.getParameter("page")!=null){
				//�Ѿ�� ������ ��ȣ(�ʹ�ȣ)�� �ִ� ���
				page = Integer.parseInt(request.getParameter("page"));
			}
			
			//DAO�� �� ���ڵ� ���� ���ϴ� �޼ҵ� ȣ��  
			int listcount = comdao.getListCount(); 
			
			List<CompanyBean> companyList = comdao.getCompanyList(page, limit);
			
			//�� ��������
			int maxpage = (int)((double)listcount/limit+0.95);
			//���� ��������
			int startpage = (((int)((double)page/10+0.9))-1)*10+1;
			//�� ��������
			int endpage = maxpage;
			
			if(endpage > startpage+10-1){
				endpage = startpage+10-1;
			}
			
			//�� Ű�� ������ ���� ���� �����Ѵ�.
			request.setAttribute("page", page);   		  	 //�ʹ�ȣ
			request.setAttribute("maxpage", maxpage);        //����������
			request.setAttribute("startpage", startpage);    //����������
			request.setAttribute("endpage", endpage);    //����������
			
			request.setAttribute("listcount", listcount);    //�ѷ��ڵ� ��
			request.setAttribute("companyList", companyList);    	 //�Խù� ���
			
			request.setAttribute("limit", limit);  			 //���ڵ� ����
			
			ActionForward forward = new ActionForward();
			
			forward.setRedirect(false);
			
			forward.setPath("./admin/company/admin_company_list.jsp"); //�� ������
			
			return forward;
		}
			return null;
	}
}
