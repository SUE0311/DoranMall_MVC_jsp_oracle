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
		
		//출력 스크림 객체를 생성한다. 
		PrintWriter out = response.getWriter();
		
		AdminDAO md = new AdminDAO();
		
		String id = request.getParameter("id").trim();
		String pass = request.getParameter("pass").trim();
		
		//DAO의 userCheck() 메소드를 호출하여 회원 계정을 체크한다. 
		int result = md.userCheck(id, pass);
		
		if(result==1){ //비번이 같은 경우
			AdminBean m = md.getMember(id);
			
			//id에 해당하는 디비 회원정보를 가져온다.
			String admin_empNo = m.getAdmin_empNo();
			String admin_name = m.getAdmin_name();
			String admin_dept = m.getAdmin_dept();
			String admin_pos = m.getAdmin_pos();
			String admin_auth = m.getAdmin_auth();
			
			System.out.println("============== " + admin_auth);
			
			//로그인에 필요한 세션 객체를 생성한다.
			HttpSession session = request.getSession();
			
			session.setAttribute("id", id);
			session.setAttribute("admin_empNo", admin_empNo);
			session.setAttribute("admin_name", admin_name);
			session.setAttribute("admin_dept", admin_dept);
			session.setAttribute("admin_pos", admin_pos);
			session.setAttribute("admin_auth", admin_auth);
			
			//포워딩 페이지 설정
			ActionForward forward = new ActionForward();
			forward.setRedirect(true);
			//요청한 페이지와 포워딩 페이지가 다른 경우 true 로 지정한다.
			
			forward.setPath("adminIndex.do");
			
			return forward;
		}
		else if(result==0){//비번이 일치하지 않은 경우
			out.println("<script>");
			out.println("alert('비번이 일치하지 않습니다.')");
			out.println("history.back()");
			out.println("</script>");
		}
		else if(result==-1){//아이디가 일치하지 않은 경우
			out.println("<script>");
			out.println("alert('등록된 아이디가 없습니다.')");
			out.println("history.go(-1)");
			out.println("</script>");
		}
		
		return null;
	}
}
