package com.client.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.client.dao.ClientBean;
import com.client.dao.ClientDAO;
import com.mtory.action.Action;
import com.mtory.action.ActionForward;

public class PwdFindOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		//����ڰ� �Է��� id�� name�� �Ķ���ͷ� �޴´�.
		String pwd_id = request.getParameter("pwd_id").trim();
		String pwd_name = request.getParameter("pwd_name").trim();
		
		System.out.println(pwd_id);
		System.out.println(pwd_name);
		
		//DAO�� �����Ͽ� �޼ҵ带 ȣ���Ѵ�.
		ClientDAO md = new ClientDAO();
		
		//id�� name �Ű������� findpwd() �޼ҵ� ȣ��
		ClientBean member = md.findpwd(pwd_id, pwd_name);
		
		if(member == null){//���ϰ��� null �ΰ��
			System.out.println("-----------");
			out.println("<script>");
			out.println("alert('�Է��� ������ ��ġ���� �ʽ��ϴ�.')");
			out.println("history.go(-1)");
			out.println("<script>");
		}else{//���ϰ��� �ִ� ���
			request.setAttribute("passwd", member.getClient_pass());
			
			//passwdŰ�� ����� �����Ѵ�.
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./client/doranClient/client_id_pwd_find.jsp");
			return forward;
		}
		return null;
	}
}
