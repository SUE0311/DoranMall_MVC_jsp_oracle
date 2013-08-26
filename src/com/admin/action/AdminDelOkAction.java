package com.admin.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.admin.dao.AdminBean;
import com.admin.dao.AdminDAO;
import com.mtory.action.Action;
import com.mtory.action.ActionForward;

public class AdminDelOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//회원 탈퇴는 db에서 delete 시키지 않고, update를 사용한다.
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		//출력 스크림 객체를 생성한다. 
		PrintWriter out = response.getWriter();
		
		//세션 객체를 생성
		HttpSession session = request.getSession();
		
		String id = (String)session.getAttribute("id");
		
		if(id==null){//id 세션 객체가 null 인 경우 
			out.println("<script>");
			out.println("alert('다시 로그인 해주세요')");
			out.println("location='adminIndex.do'");
			out.println("</script>");
		}else{//id 세션 객체가 있는 경우
			//사용자가 입력한 데이터를 파리미터로 가져온다. 
			String pass = request.getParameter("pwd").trim();
		
			AdminDAO md = new AdminDAO();
			
			int re = md.deleteMember(id, pass);
			
			if(re==1){//회원 탈퇴가 성공한 경우
				session.invalidate(); //탈퇴시 세션 만료 시킴
				out.println("<script>");
				out.println("alert('멤버 탈퇴가 되었습니다.')");
				out.println("location='./adminIndex.do'");
				out.println("</script>");
			}else if(re==0){
				out.println("<script>");
				out.println("alert('비밀번호가 틀렸습니다..')");
				out.println("history.back()");
				out.println("</script>");
			}else if(re==-1){
				out.println("<script>");
				out.println("alert('멤버 탈퇴가 실패하였습니다.')");
				out.println("history.go(-1)");
				out.println("</script>");
			}
		}
		return null;
	}
}
