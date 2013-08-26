package com.company.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.dao.CompanyBean;
import com.company.dao.CompanyDAO;
import com.mtory.action.Action;
import com.mtory.action.ActionForward;

public class CompanySearchOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		CompanyDAO comdao = new CompanyDAO();

		String searchSelect = request.getParameter("searchSelect");
		String searchCom = request.getParameter("searchCom");

		System.out.println("Action =========== searchSelect : " + searchSelect);
		System.out.println("Action =========== searchCom : " + searchCom);
		
		int page = 1;
		int limit = 10; //�� �������� �������� ��ϼ�(10�� ����)
		
		if(request.getParameter("page")!=null){
			//�Ѿ�� ������ ��ȣ(�ʹ�ȣ)�� �ִ� ���
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		//DAO�� �� ���ڵ� ���� ���ϴ� �޼ҵ� ȣ��  
		int listcount = comdao.getListCount(); 
		
		List<CompanyBean> companyList = comdao.getcompanySearchList(page, limit, searchSelect, searchCom);
		
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
}
