package com.client.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.client.dao.ClientBean;
import com.client.dao.ClientDAO;
import com.mtory.action.Action;
import com.mtory.action.ActionForward;

public class ClientSearchOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		ClientDAO clientdao = new ClientDAO();

		String searchClientSelect = request.getParameter("searchClientSelect");
		String searchClient = request.getParameter("searchClient");

		System.out.println("Action =========== searchClientSelect : " + searchClientSelect);
		System.out.println("Action =========== searchClient : " + searchClient);
		
		int page = 1;
		int limit = 10; //�� �������� �������� ��ϼ�(10�� ����)
		
		if(request.getParameter("page")!=null){
			//�Ѿ�� ������ ��ȣ(�ʹ�ȣ)�� �ִ� ���
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		//DAO�� �� ���ڵ� ���� ���ϴ� �޼ҵ� ȣ��  
		int listcount = clientdao.getListCount(); 
		
		List<ClientBean> clientList = clientdao.getClientSearchList(page, limit, searchClientSelect, searchClient);
		
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
		request.setAttribute("clientList", clientList);    	 //�Խù� ���
		
		request.setAttribute("limit", limit);  			 //���ڵ� ����
		
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		
		forward.setPath("./admin/client/admin_client_list.jsp"); //�� ������
		
		return forward;
	}
}
