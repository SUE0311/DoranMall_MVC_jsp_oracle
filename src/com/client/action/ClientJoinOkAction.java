package com.client.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.client.dao.ClientBean;
import com.client.dao.ClientDAO;
import com.mtory.action.Action;
import com.mtory.action.ActionForward;

public class ClientJoinOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		ClientDAO md = new ClientDAO();
		ClientBean m = new ClientBean();
		
		ActionForward forward = new ActionForward();
		
		String client_id = request.getParameter("client_id").trim();
		String client_pass = request.getParameter("client_pass").trim();
		String client_name = request.getParameter("client_name").trim();
		String client_zip1 = request.getParameter("client_zip1").trim();
		String client_zip2 = request.getParameter("client_zip2").trim();
		String client_addr1 = request.getParameter("client_addr1").trim();
		String client_addr2 = request.getParameter("client_addr2").trim();
		String client_tel = request.getParameter("client_tel").trim();
		String client_email = request.getParameter("client_email").trim();
			
		//���ε��� ���ϸ��� �������� ���� ��쿡�� ������ �׸� db�� �����Ѵ�.
		m.setClient_id(client_id);
		m.setClient_pass(client_pass);
		m.setClient_name(client_name);
		m.setClient_zip1(client_zip1);
		m.setClient_zip2(client_zip2);
		m.setClient_addr1(client_addr1);
		m.setClient_addr2(client_addr2);
		m.setClient_tel(client_tel);
		m.setClient_email(client_email);
		
		//DAO Ŭ������ insertMember() �޼ҵ带 ȣ���Ͽ� ���ڵ带 �����Ѵ�.
		//�������ڴ� ���� ��ü = ����� ���ڵ�
		System.out.println(m.toString());
		md.insertClient(m);
		
		//�α��� �������� �̵��Ѵ�.
		response.sendRedirect("./index.do");
		
		return null;
	}
}
