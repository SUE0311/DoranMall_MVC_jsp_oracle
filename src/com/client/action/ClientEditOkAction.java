package com.client.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.client.dao.ClientBean;
import com.client.dao.ClientDAO;
import com.mtory.action.Action;
import com.mtory.action.ActionForward;

public class ClientEditOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		ClientDAO md = new ClientDAO();
		
		HttpSession session = request.getSession();

		String id=(String)session.getAttribute("id");
		
		if(id==null){//id ������ ����� ���
			out.println("<script>");
			out.println("alert('�ٽ� �α��� �ֽʽÿ�!')");
			out.println("location='index.do'");//�α��� �������� �̵�
			out.println("</script>");
		}else{
			String edit_pass = request.getParameter("edit_pass").trim();
			
			//DAO���� getMemner()�޼ҵ带 ȣ���Ͽ� ���ڵ带 �����´�. 
			ClientBean clientbean = md.getClient(id);
			
			if(!clientbean.getClient_pass().equals(edit_pass)){
				out.println("<script>");
	    	    out.println("alert('��й�ȣ�� �ٸ��ϴ�.')");
	            out.println("history.go(-1)");
	            out.println("</script>");
			}else{
				String client_id = request.getParameter("client_id").trim();
				String client_pass = request.getParameter("client_pass").trim();
				String client_name = request.getParameter("client_name").trim();
				String client_zip1 = request.getParameter("client_zip1").trim();
				String client_zip2 = request.getParameter("client_zip2").trim();
				String client_addr1 = request.getParameter("client_addr1").trim();
				String client_addr2 = request.getParameter("client_addr2").trim();
				String client_tel = request.getParameter("client_tel").trim();
				String client_email = request.getParameter("client_email").trim();
					
				ClientBean m = new ClientBean();
				
				m.setClient_id(client_id);
				m.setClient_pass(client_pass);
				m.setClient_name(client_name);
				m.setClient_zip1(client_zip1);
				m.setClient_zip2(client_zip2);
				m.setClient_addr1(client_addr1);
				m.setClient_addr2(client_addr2);
				m.setClient_tel(client_tel);
				m.setClient_email(client_email);
				
				//DAO���� ���� �޼ҵ� ȣ��
				int re = md.updateClient(m);
				
				if(re==1){
					out.println("<script>");
					out.println("alert('������ �����Ͽ����ϴ�')");
					out.println("location='./client_cont_ok.do'");
					out.println("</script>");
				}else{
					out.println("<script>");
					out.println("alert('������ �����Ͽ����ϴ�')");
					out.println("history.back()");
					out.println("</script>");
				}
			}
		}	
		return null;
	}
}
