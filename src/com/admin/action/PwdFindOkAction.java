package com.admin.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.admin.dao.AdminBean;
import com.admin.dao.AdminDAO;
import com.mtory.action.Action;
import com.mtory.action.ActionForward;

public class PwdFindOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		//사용자가 입력한 id와 name을 파라미터로 받는다.
		String pwd_id = request.getParameter("pwd_id").trim();
		String pwd_name = request.getParameter("pwd_name").trim();
		
		System.out.println(pwd_id);
		System.out.println(pwd_name);
		
		//DAO에 접근하여 메소드를 호출한다.
		AdminDAO md = new AdminDAO();
		
		//id와 name 매개변수로 findpwd() 메소드 호출
		AdminBean member = md.findpwd(pwd_id, pwd_name);
		
		if(member == null){//리턴값이 null 인경우
			System.out.println("-----------");
			out.println("<script>");
			out.println("alert('입력한 정보가 일치하지 않습니다.')");
			out.println("history.go(-1)");
			out.println("<script>");
		}else{//리턴값이 있는 경우
			request.setAttribute("passwd", member.getAdmin_pass());
			
			//passwd키에 비번을 저장한다.
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./admin/adminEmp/pwd_find.jsp");
			return forward;
		}
		return null;
	}
}
