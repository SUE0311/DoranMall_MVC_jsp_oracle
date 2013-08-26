package com.company.action;

import java.io.File;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.dao.AdminBean;
import com.admin.dao.AdminDAO;
import com.company.dao.CompanyBean;
import com.company.dao.CompanyDAO;
import com.mtory.action.Action;
import com.mtory.action.ActionForward;

public class CompanyDelOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		CompanyDAO cd = new CompanyDAO();
		
		int com_num = Integer.parseInt(request.getParameter("num"));
		int page = Integer.parseInt(request.getParameter("page"));
		
		//사용자가 입력한 비밀번호를 파라미터로 받는다. 
		String pwd = request.getParameter("admin_pwd").trim();
		String id = request.getParameter("admin_id").trim();
		System.out.println("========== " + pwd);
		System.out.println("========== " + id);
		
		//이진 업로드 파일 경로
		String up = "C:/WebProject/DoranMall/WebContent/upload";
		
		//상품 테이블에서 상품번호에 해당하는 디비 내용을 가져온다. 
		CompanyBean combean = cd.getCont(com_num);
		String fname = combean.getCom_file1();
		
		//어드민 테이블에서 비밀번호에 해당하는 디비 내용을 가져온다. 
		AdminDAO ad = new AdminDAO();
		AdminBean adbean = ad.getMember(id);
		System.out.println("============ 관리자 비밀번호" + adbean.getAdmin_pass());
		
//		어드민 비밀번호 확인 없이 삭제할 때
//		cd.companyDelete(com_num); //레코드를 디비로부터 삭제하는 메소드
//		if(fname != null){//이진 파일이 존재한다면
//			File file = new File(up+fname);
//			file.delete(); //기존 이진파일을 폴더로부터 삭제한다.
//		}
//		게시물 목록으로 페이지 번호를 넘기면서 해당 페이지 목록으로 이동
//		response.sendRedirect("admin_company_list.do?page=" + page);
		

		if(!adbean.getAdmin_pass().equals(pwd)){ //비밀번호가 다른 경우
			out.println("<script>");
			out.println("alert('비밀번호가 일치하지 않습니다.')");
			out.println("history.go(-1)"); //바로 이전 페이지로 이동
			out.println("</script>");
		}else{//비번이 같은 경우
			cd.companyDelete(com_num);  //레코드를 디비로부터 삭제하는 메소드
			
			if(fname != null){//이진 파일이 존재한다면
				File file = new File(up+fname);
				file.delete(); //기존 이진파일을 폴더로부터 삭제한다.
			}
			
			//게시물 목록으로 페이지 번호를 넘기면서 해당 페이지 목록으로 이동
			response.sendRedirect("admin_company_list.do?page=" + page);
		}
		return null;
	}
}
