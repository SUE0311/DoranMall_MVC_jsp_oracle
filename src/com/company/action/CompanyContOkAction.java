package com.company.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.company.dao.CompanyBean;
import com.company.dao.CompanyDAO;
import com.mtory.action.Action;
import com.mtory.action.ActionForward;

public class CompanyContOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		//출력 스크림 객체를 생성한다. 
		PrintWriter out = response.getWriter();
		
		//세션 객체를 생성
		HttpSession session = request.getSession();
		
		String admin_auth = (String)session.getAttribute("admin_auth");
		
		System.out.println("=============== " + admin_auth);
		
		int num = Integer.parseInt(request.getParameter("num"));
		int page = Integer.parseInt(request.getParameter("page"));
		
		//get 방식으로 넘어 온 구분 값을 파라미터로 받는다. 
		String state = request.getParameter("state");
		//state = "cont"는 해당 자료실의 내용을 본다는 의미임
		
		CompanyDAO cd = new CompanyDAO();
		
		//번호에 해당하는 디비 내용을 가져오는 메소드 호출
		CompanyBean combean = cd.getCont(num);
		
		//해당하는 디비 내용을 bbsdate 키에 저장한다.
		request.setAttribute("comdata", combean);	//레코드 저장
		request.setAttribute("page", page);		//쪽번호 저장
		
		//포워딩 페이지 지정
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		
		if(admin_auth.equals("3")){
			if(state.equals("cont")){ //자료실의 내용보기 일 경우
				forward.setPath("./admin/company/admin_company_cont.jsp"); //view 페이지 지정
			}else if(state.equals("edit")){	//게시물 수정 일 경우
				forward.setPath("./admin/company/admin_company_edit.jsp"); //view 페이지 지정
			}else if(state.equals("delete")){	//게시물 삭제 일 경우
				forward.setPath("./admin/company/admin_company_delete.jsp"); //view 페이지 지정
			}
			return forward;
		}else if(admin_auth.equals("2")){
			if(state.equals("cont")){ //자료실의 내용보기 일 경우
				forward.setPath("./admin/company/admin_company_cont.jsp"); //view 페이지 지정
				return forward;
			}else if(state.equals("edit")){	//게시물 수정 일 경우
				forward.setPath("./admin/company/admin_company_edit.jsp"); //view 페이지 지정
				return forward;
			}else if(state.equals("delete")){	//게시물 삭제 일 경우
				out.println("<script>");
				out.println("alert('접근 권한이 없습니다.')");
				out.println("history.go(-1)");
				out.println("</script>");
				return null;
			}
		}else if(admin_auth.equals("1")){
			if(state.equals("cont")){ //자료실의 내용보기 일 경우
				forward.setPath("./admin/company/admin_company_cont.jsp"); //view 페이지 지정
				return forward;
			}else if(state.equals("edit")){	//게시물 수정 일 경우
				out.println("<script>");
				out.println("alert('접근 권한이 없습니다.')");
				out.println("history.go(-1)");
				out.println("</script>");
				return null;
			}else if(state.equals("delete")){	//게시물 삭제 일 경우
				out.println("<script>");
				out.println("alert('접근 권한이 없습니다.')");
				out.println("history.go(-1)");
				out.println("</script>");
				return null;
			}
		}	
		
		return null;
	}
}
