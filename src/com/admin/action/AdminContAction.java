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
		System.out.println("============= cont에서 필요한 id : " + id);
		
		if(id==null){//id 세션이 만료된 경우
			out.println("<script>");
			out.println("alert('다시 로그인 주십시오!')");
			out.println("location='adminIndex.do'");//로그인 페이지로 이동
			out.println("</script>");
		}else{
			AdminDAO md = new AdminDAO();
			
			//DAO에서 getMemner()메소드를 호출하여 레코드를 가져온다. 
			AdminBean m = md.getMember(id);
			
			//member 키에 레코드(m)를 저장한다.
			request.setAttribute("member", m);
			
			ActionForward forward = new ActionForward();
			
			forward.setRedirect(false);
			
			forward.setPath("./admin/adminEmp/admin_emp_cont.jsp");
			
			return forward;
		}
		return null;
	}
}
