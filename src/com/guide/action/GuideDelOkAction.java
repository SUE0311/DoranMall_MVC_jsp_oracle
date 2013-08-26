package com.guide.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.guide.dao.GuideBean;
import com.guide.dao.GuideDAO;
import com.mtory.action.Action;
import com.mtory.action.ActionForward;

public class GuideDelOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		GuideDAO gd = new GuideDAO();
		
		int guide_num = Integer.parseInt(request.getParameter("num"));
		int page = Integer.parseInt(request.getParameter("page"));
		
		String pwd = request.getParameter("admin_pwd").trim();
				
		gd.guideDelete(guide_num);
		
		response.sendRedirect("admin_guide_list.do?page=" + page);
		
		return null;
	}

}
