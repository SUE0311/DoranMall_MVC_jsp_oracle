package com.client.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.client.dao.ClientBean;
import com.client.dao.ClientDAO;
import com.mtory.action.Action;
import com.mtory.action.ActionForward;

public class ClientContAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();

		String id=(String)session.getAttribute("id");
		
		if(id==null){//id ������ ����� ���
			out.println("<script>");
			out.println("alert('�ٽ� �α��� �ֽʽÿ�!')");
			out.println("location='index.do'");//�α��� �������� �̵�
			out.println("</script>");
		}else{
			ClientDAO md = new ClientDAO();
			
			//DAO���� getMemner()�޼ҵ带 ȣ���Ͽ� ���ڵ带 �����´�. 
			ClientBean m = md.getClient(id);
			
			//member Ű�� ���ڵ�(m)�� �����Ѵ�.
			request.setAttribute("client", m);
			
			ActionForward forward = new ActionForward();
			
			forward.setRedirect(false);
			
			forward.setPath("./client/doranClient/client_cont.jsp");
			
			return forward;
		}
		return null;
	}
}
