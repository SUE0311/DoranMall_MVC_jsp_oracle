package com.client.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.client.dao.ClientBean;
import com.client.dao.ClientDAO;
import com.mtory.action.Action;
import com.mtory.action.ActionForward;

public class IdFindOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		//사용자가 입력한 id와 name을 파라미터로 받는다.
		String find_name = request.getParameter("find_name").trim();
		String find_email = request.getParameter("find_email").trim();
		
		System.out.println(find_name);
		System.out.println(find_email);
		
		//DAO에 접근하여 메소드를 호출한다.
		ClientDAO md = new ClientDAO();
		
		//id와 name 매개변수로 findpwd() 메소드 호출
		ClientBean clientbean = md.findId(find_name, find_email);
		
		if(clientbean == null){//리턴값이 null 인경우
			System.out.println("-----------");
			out.println("<script>");
			out.println("alert('입력한 정보가 일치하지 않습니다.')");
			out.println("history.go(-1)");
			out.println("<script>");
		}else{//리턴값이 있는 경우
			request.setAttribute("afterfindId", clientbean.getClient_id());
			
			//passwd키에 비번을 저장한다.
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./client/doranClient/client_id_pwd_find.jsp");
			return forward;
		}
		return null;
	}
}
