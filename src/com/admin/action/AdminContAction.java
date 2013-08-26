package com.admin.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.admin.dao.AdminBean;
import com.admin.dao.AdminDAO;
import com.mtory.action.Action;
import com.mtory.action.ActionForward;

public class AdminContAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		
		String id=(String)session.getAttribute("id");
		System.out.println("============= cont���� �ʿ��� id : " + id);
		
		if(id==null){//id ������ ����� ���
			out.println("<script>");
			out.println("alert('�ٽ� �α��� �ֽʽÿ�!')");
			out.println("location='adminIndex.do'");//�α��� �������� �̵�
			out.println("</script>");
		}else{
			AdminDAO md = new AdminDAO();
			
			//DAO���� getMemner()�޼ҵ带 ȣ���Ͽ� ���ڵ带 �����´�. 
			AdminBean m = md.getMember(id);
			
			//member Ű�� ���ڵ�(m)�� �����Ѵ�.
			request.setAttribute("member", m);
			
			ActionForward forward = new ActionForward();
			
			forward.setRedirect(false);
			
			forward.setPath("./admin/adminEmp/admin_emp_cont.jsp");
			
			return forward;
		}
		return null;
	}
}
