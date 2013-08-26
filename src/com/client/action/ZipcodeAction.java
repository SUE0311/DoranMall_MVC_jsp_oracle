package com.client.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.client.dao.ClientDAO;
import com.mtory.action.Action;
import com.mtory.action.ActionForward;

public class ZipcodeAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//�����ȣ �˻� ����� �����ϴ� �׼� Ŭ����
		request.setCharacterEncoding("UTF-8");
		
		String dong = request.getParameter("dong").trim();
		
		ClientDAO md = new ClientDAO();
		
		//dong�� �Ű������� searchZipcode()�޼ҵ带 ȣ���Ѵ�.
		List zipcodeList = md.searchZipcode(dong);
		
		//�˻��� ���� ���� zipcodeŰ�� �����Ѵ�.
		request.setAttribute("zipcodeList", zipcodeList);
		
		//dong Ű�� ���鵿�� �����Ѵ�.
		request.setAttribute("dong", dong);
		
		//����� ����ڿ��� ������ view �������� �����ϰ� �������Ѵ�. 
		ActionForward forward = new ActionForward();
		
		//setRedirect(false)�� ��� ��û�� �������� �״��
		//�������Ѵٴ� �ǹ��̴�. 
		forward.setRedirect(false);
		
		//member_zipcode.jsp������ �����ȣ�� �˻��ϰ� �˻���
		//����� ���ÿ� �����ִ� view �������̴�.
		forward.setPath("/client/doranClient/zipcode_find.jsp");
		
		return forward;
	}
}
