package com.guide.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.guide.dao.GuideBean;
import com.guide.dao.GuideDAO;
import com.mtory.action.Action;
import com.mtory.action.ActionForward;

public class GuideClientOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		/*int num = Integer.parseInt(request.getParameter("num"));
		int page = Integer.parseInt(request.getParameter("page"));*/
		
		//get 방식으로 넘어 온 구분 값을 파라미터로 받는다. 
		//String state = request.getParameter("state");
		//state = "cont"는 해당 자료실의 내용을 본다는 의미임
		
		response.setContentType("text/html;charset=UTF-8");
		
		GuideDAO gd = new GuideDAO();
		
		//번호에 해당하는 디비 내용을 가져오는 메소드 호출
		GuideBean guidebean = gd.getClient();
		
		//해당하는 디비 내용을 bbsdate 키에 저장한다.
		request.setAttribute("guidedata", guidebean);	//레코드 저장
		//request.setAttribute("page", page);		//쪽번호 저장
		
		//포워딩 페이지 지정
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		
		forward.setPath("./client/guide/guide.jsp");
		
		return forward;
	}
}
