package com.client.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.client.dao.ClientBean;
import com.client.dao.ClientDAO;
import com.mtory.action.Action;
import com.mtory.action.ActionForward;

public class ClientLoginOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		//출력 스크림 객체를 생성한다. 
		PrintWriter out = response.getWriter();
		
		ClientDAO md = new ClientDAO();
		
		String id = request.getParameter("client_id").trim();
		String pass = request.getParameter("client_pass").trim();
		
		//DAO의 userCheck() 메소드를 호출하여 회원 계정을 체크한다. 
		int result = md.userCheck(id, pass);
		
		if(result==1){ //비번이 같은 경우
			ClientBean m = md.getClient(id);
			
			//id에 해당하는 디비 회원정보를 가져온다.
			int client_num = m.getClient_num();
			String client_id = m.getClient_id();
			String client_pass = m.getClient_pass();
			String client_name = m.getClient_name();
			String client_zip1 = m.getClient_zip1();
			String client_zip2 = m.getClient_zip2();
			String client_addr1 = m.getClient_addr1();
			String client_addr2 = m.getClient_addr2();
			String client_tel = m.getClient_tel();
			String client_email = m.getClient_email();
			String client_regdate = m.getClient_regdate();
			String client_rank = m.getClient_rank();
			int client_mileage = m.getClient_mileage();
			int client_state = m.getClient_state();
			String client_deldate = m.getClient_deldate();
			String client_delcont = m.getClient_delcont();
			
			//로그인에 필요한 세션 객체를 생성한다.
			HttpSession session = request.getSession();
			
			session.setAttribute("client_id", client_id);
			session.setAttribute("client_pass", client_pass);
			session.setAttribute("client_name", client_name);
			session.setAttribute("client_zip1", client_zip1);
			session.setAttribute("client_zip2", client_zip2);
			session.setAttribute("client_zip2", client_zip2);
			session.setAttribute("client_addr1", client_addr1);
			session.setAttribute("client_addr2", client_addr2);
			session.setAttribute("client_tel", client_tel);
			session.setAttribute("client_email", client_email);
			session.setAttribute("client_regdate", client_regdate);
			session.setAttribute("client_rank", client_rank);
			session.setAttribute("client_mileage", client_mileage);
			session.setAttribute("client_state", client_state);
			session.setAttribute("client_deldate", client_deldate);
			session.setAttribute("client_delcont", client_delcont);
			
			//포워딩 페이지 설정
			ActionForward forward = new ActionForward();
			forward.setRedirect(true);
			//요청한 페이지와 포워딩 페이지가 다른 경우 true 로 지정한다.
			
			forward.setPath("index.do");
			
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
