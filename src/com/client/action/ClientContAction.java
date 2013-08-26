package com.client.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.client.dao.ClientBean;
import com.client.dao.ClientDAO;
import com.mtory.action.Action;
import com.mtory.action.ActionForward;

public class ClientContAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();

		String id=(String)session.getAttribute("id");
		
		if(id==null){//id 세션이 만료된 경우
			out.println("<script>");
			out.println("alert('다시 로그인 주십시오!')");
			out.println("location='index.do'");//로그인 페이지로 이동
			out.println("</script>");
		}else{
			ClientDAO md = new ClientDAO();
			
			//DAO에서 getMemner()메소드를 호출하여 레코드를 가져온다. 
			ClientBean m = md.getClient(id);
			
			//member 키에 레코드(m)를 저장한다.
			request.setAttribute("client", m);
			
			ActionForward forward = new ActionForward();
			
			forward.setRedirect(false);
			
			forward.setPath("./client/doranClient/client_cont.jsp");
			
			return forward;
		}
		return null;
	}
}
