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
		
		//get ������� �Ѿ� �� ���� ���� �Ķ���ͷ� �޴´�. 
		//String state = request.getParameter("state");
		//state = "cont"�� �ش� �ڷ���� ������ ���ٴ� �ǹ���
		
		response.setContentType("text/html;charset=UTF-8");
		
		GuideDAO gd = new GuideDAO();
		
		//��ȣ�� �ش��ϴ� ��� ������ �������� �޼ҵ� ȣ��
		GuideBean guidebean = gd.getClient();
		
		//�ش��ϴ� ��� ������ bbsdate Ű�� �����Ѵ�.
		request.setAttribute("guidedata", guidebean);	//���ڵ� ����
		//request.setAttribute("page", page);		//�ʹ�ȣ ����
		
		//������ ������ ����
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		
		forward.setPath("./client/guide/guide.jsp");
		
		return forward;
	}
}
