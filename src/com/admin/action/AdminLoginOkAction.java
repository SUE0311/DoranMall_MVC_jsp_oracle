package com.admin.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.admin.dao.AdminBean;
import com.admin.dao.AdminDAO;
import com.mtory.action.Action;
import com.mtory.action.ActionForward;

public class AdminLoginOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		//��� ��ũ�� ��ü�� �����Ѵ�. 
		PrintWriter out = response.getWriter();
		
		AdminDAO md = new AdminDAO();
		
		String id = request.getParameter("id").trim();
		String pass = request.getParameter("pass").trim();
		
		//DAO�� userCheck() �޼ҵ带 ȣ���Ͽ� ȸ�� ������ üũ�Ѵ�. 
		int result = md.userCheck(id, pass);
		
		if(result==1){ //����� ���� ���
			AdminBean m = md.getMember(id);
			
			//id�� �ش��ϴ� ��� ȸ�������� �����´�.
			String admin_empNo = m.getAdmin_empNo();
			String admin_name = m.getAdmin_name();
			String admin_dept = m.getAdmin_dept();
			String admin_pos = m.getAdmin_pos();
			String admin_auth = m.getAdmin_auth();
			
			System.out.println("============== " + admin_auth);
			
			//�α��ο� �ʿ��� ���� ��ü�� �����Ѵ�.
			HttpSession session = request.getSession();
			
			session.setAttribute("id", id);
			session.setAttribute("admin_empNo", admin_empNo);
			session.setAttribute("admin_name", admin_name);
			session.setAttribute("admin_dept", admin_dept);
			session.setAttribute("admin_pos", admin_pos);
			session.setAttribute("admin_auth", admin_auth);
			
			//������ ������ ����
			ActionForward forward = new ActionForward();
			forward.setRedirect(true);
			//��û�� �������� ������ �������� �ٸ� ��� true �� �����Ѵ�.
			
			forward.setPath("adminIndex.do");
			
			return forward;
		}
		else if(result==0){//����� ��ġ���� ���� ���
			out.println("<script>");
			out.println("alert('����� ��ġ���� �ʽ��ϴ�.')");
			out.println("history.back()");
			out.println("</script>");
		}
		else if(result==-1){//���̵� ��ġ���� ���� ���
			out.println("<script>");
			out.println("alert('��ϵ� ���̵� �����ϴ�.')");
			out.println("history.go(-1)");
			out.println("</script>");
		}
		
		return null;
	}
}
