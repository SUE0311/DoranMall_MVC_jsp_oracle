package com.client.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.admin.dao.AdminDAO;
import com.client.dao.ClientBean;
import com.client.dao.ClientDAO;
import com.mtory.action.Action;
import com.mtory.action.ActionForward;

public class ClientDelOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		//��� ��ũ�� ��ü�� �����Ѵ�. 
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		
		String id = (String)session.getAttribute("id");
		
		if(id==null){//id ���� ��ü�� null �� ��� 
			out.println("<script>");
			out.println("alert('�ٽ� �α��� ���ּ���')");
			out.println("location='index.do'");
			out.println("</script>");
		}else{//id ���� ��ü�� �ִ� ���
			//����ڰ� �Է��� �����͸� �ĸ����ͷ� �����´�. 
			String pass = request.getParameter("pwd").trim();
			String del_cont = request.getParameter("del_cont").trim();
		
			ClientDAO md = new ClientDAO();
			
			int re = md.deleteClient(id, pass, del_cont);
			
			if(re==1){//ȸ�� Ż�� ������ ���
				session.invalidate(); //Ż��� ���� ���� ��Ŵ
				out.println("<script>");
				out.println("alert('��� Ż�� �Ǿ����ϴ�.')");
				out.println("location='./index.do'");
				out.println("</script>");
			}else if(re==0){
				out.println("<script>");
				out.println("alert('��й�ȣ�� Ʋ�Ƚ��ϴ�..')");
				out.println("history.back()");
				out.println("</script>");
			}else if(re==-1){
				out.println("<script>");
				out.println("alert('��� Ż�� �����Ͽ����ϴ�.')");
				out.println("history.go(-1)");
				out.println("</script>");
			}
		}
		return null;
	}
}
