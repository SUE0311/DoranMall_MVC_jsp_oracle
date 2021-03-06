package com.company.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.company.dao.CompanyBean;
import com.company.dao.CompanyDAO;
import com.mtory.action.Action;
import com.mtory.action.ActionForward;

public class CompanyListOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		//출력 스크림 객체를 생성한다. 
		PrintWriter out = response.getWriter();
		
		//세션 객체를 생성
		HttpSession session = request.getSession();
		
		String id = (String)session.getAttribute("id");
		
		if(id==null){//id 세션 객체가 null 인 경우 
			out.println("<script>");
			out.println("alert('관리자 전용 메뉴입니다. 로그인해주십시오.')");
			out.println("location='adminIndex.do'");
			out.println("</script>");
		}else{
			CompanyDAO comdao = new CompanyDAO();
			
			int page = 1;
			int limit = 10; //한 페이지에 보여지는 목록수(10개 지정)
			
			if(request.getParameter("page")!=null){
				//넘어온 페이지 번호(쪽번호)가 있는 경우
				page = Integer.parseInt(request.getParameter("page"));
			}
			
			//DAO의 총 레코드 수를 구하는 메소드 호출  
			int listcount = comdao.getListCount(); 
			
			List<CompanyBean> companyList = comdao.getCompanyList(page, limit);
			
			//총 페이지수
			int maxpage = (int)((double)listcount/limit+0.95);
			//시작 페이지수
			int startpage = (((int)((double)page/10+0.9))-1)*10+1;
			//끝 페이지수
			int endpage = maxpage;
			
			if(endpage > startpage+10-1){
				endpage = startpage+10-1;
			}
			
			//각 키에 페이지 관련 값을 저장한다.
			request.setAttribute("page", page);   		  	 //쪽번호
			request.setAttribute("maxpage", maxpage);        //총페이지수
			request.setAttribute("startpage", startpage);    //시작페이지
			request.setAttribute("endpage", endpage);    //시작페이지
			
			request.setAttribute("listcount", listcount);    //총레코드 수
			request.setAttribute("companyList", companyList);    	 //게시물 목록
			
			request.setAttribute("limit", limit);  			 //레코드 제한
			
			ActionForward forward = new ActionForward();
			
			forward.setRedirect(false);
			
			forward.setPath("./admin/company/admin_company_list.jsp"); //뷰 페이지
			
			return forward;
		}
			return null;
	}
}
