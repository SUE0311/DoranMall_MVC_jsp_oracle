package com.admin.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.admin.dao.AdminBean;
import com.admin.dao.AdminDAO;
import com.mtory.action.Action;
import com.mtory.action.ActionForward;

public class AdminEditOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		//출력 스크림 객체를 생성한다. 
		PrintWriter out = response.getWriter();
		
		AdminDAO md = new AdminDAO();
		
		HttpSession session = request.getSession();
	
		String id = (String)session.getAttribute("id");
		
		if(id!=null){
			
			String edit_pass = request.getParameter("edit_pass").trim();
			
			AdminBean adbean = md.getMember(id);
			
			if(!adbean.getAdmin_pass().equals(edit_pass)){
				out.println("<script>");
	    	    out.println("alert('비밀번호가 다릅니다.')");
	            out.println("history.go(-1)");
	            out.println("</script>");
			}else{
				String admin_empNo = request.getParameter("admin_empNo").trim();
				String admin_name = request.getParameter("admin_name").trim();
				String admin_id = request.getParameter("admin_id").trim();
				String admin_dept = request.getParameter("admin_dept").trim();
				String admin_pos = request.getParameter("admin_pos").trim();
				String admin_tel = request.getParameter("admin_tel").trim();
				
				System.out.println("====== admin_empNo : " + admin_empNo);
				AdminBean m = new AdminBean();
				
				m.setAdmin_empNo(admin_empNo);
				m.setAdmin_name(admin_name);
				m.setAdmin_id(admin_id);
				m.setAdmin_pass(edit_pass);
				m.setAdmin_dept(admin_dept);
				m.setAdmin_pos(admin_pos);
				m.setAdmin_tel(admin_tel);
				
				//DAO에서 수정 메소드 호출
				int re = md.updateMember(m);
				
				if(re==1){
					out.println("<script>");
					out.println("alert('수정에 성공하였습니다')");
					out.println("location='./admin_cont_ok.do'");
					out.println("</script>");
				}else{
					out.println("<script>");
					out.println("alert('수정에 실패하였습니다')");
					out.println("history.back()");
					out.println("</script>");
				}
			}
		}else{ //id 세션 객체가 만료된 경우
			out.println("<script>");
			out.println("alert('다시 로그인해주십시오')");
			out.println("location='adminIndex.do'");
			out.println("</script>");
		}
		return null;
	}
}
