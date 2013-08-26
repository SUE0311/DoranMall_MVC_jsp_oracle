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
		//우편번호 검색 결과를 제공하는 액션 클래스
		request.setCharacterEncoding("UTF-8");
		
		String dong = request.getParameter("dong").trim();
		
		ClientDAO md = new ClientDAO();
		
		//dong을 매개변수로 searchZipcode()메소드를 호출한다.
		List zipcodeList = md.searchZipcode(dong);
		
		//검색된 리턴 값을 zipcode키에 저장한다.
		request.setAttribute("zipcodeList", zipcodeList);
		
		//dong 키에 읍면동을 저장한다.
		request.setAttribute("dong", dong);
		
		//결과를 사용자에게 보여줄 view 페이지를 저장하고 포워딩한다. 
		ActionForward forward = new ActionForward();
		
		//setRedirect(false)인 경우 요청한 페이지로 그대로
		//포워딩한다는 의미이다. 
		forward.setRedirect(false);
		
		//member_zipcode.jsp파일은 우편번호도 검색하고 검색된
		//결과도 동시에 보여주는 view 페이지이다.
		forward.setPath("/client/doranClient/zipcode_find.jsp");
		
		return forward;
	}
}
