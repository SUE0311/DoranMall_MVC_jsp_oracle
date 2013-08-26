package com.admin.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.dao.AdminBean;
import com.admin.dao.AdminDAO;
import com.mtory.action.Action;
import com.mtory.action.ActionForward;

public class AdminJoinOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		AdminDAO md = new AdminDAO();
		AdminBean m = new AdminBean();
		
		ActionForward forward = new ActionForward();
		
		String admin_empNo = request.getParameter("admin_empNo").trim();
		String admin_name = request.getParameter("admin_name").trim();
		String admin_id = request.getParameter("admin_id").trim();
		String admin_pass = request.getParameter("admin_pass").trim();
		String admin_dept = request.getParameter("admin_dept").trim();
		String admin_pos = request.getParameter("admin_pos").trim();
		String admin_tel = request.getParameter("admin_tel").trim();
		
		//업로드할 파일명을 지정하지 않은 경우에는 나머지 항목만 db에 저장한다.
		m.setAdmin_empNo(admin_empNo);
		m.setAdmin_name(admin_name);
		m.setAdmin_id(admin_id);
		m.setAdmin_pass(admin_pass);
		m.setAdmin_dept(admin_dept);
		m.setAdmin_pos(admin_pos);
		m.setAdmin_tel(admin_tel);
		
		//DAO 클래스의 insertMember() 메소드를 호출하여 레코드를 저장한다.
		//전달인자는 빈의 객체 = 저장될 레코드
		System.out.println(m.toString());
		md.insertMember(m);
		
		//로그인 페이지로 이동한다.
		response.sendRedirect("./adminIndex.do");
		
		return null;
	}
}
