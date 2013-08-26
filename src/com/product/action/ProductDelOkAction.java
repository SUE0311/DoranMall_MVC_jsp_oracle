package com.product.action;

import java.io.File;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.product.dao.ProductBean;
import com.product.dao.ProductDAO;
import com.mtory.action.Action;
import com.mtory.action.ActionForward;

public class ProductDelOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		ProductDAO prodao = new ProductDAO();
		
		int pro_num = Integer.parseInt(request.getParameter("num"));
		int page = Integer.parseInt(request.getParameter("page"));
		
		//사용자가 입력한 비밀번호를 파라미터로 받는다. 
		String pwd = request.getParameter("admin_pwd").trim();
		
		//이진 업로드 파일 경로
		String up = "C:/WebProject/DoranMall/WebContent/upload";
		
		//비밀번호에 해당하는 디비 내용을 가져온다. 
		ProductBean probean = prodao.getCont(pro_num);
		
		String fname01 = probean.getProduct_description();
		String fname02 = probean.getProduct_mainImg();
		String fname03 = probean.getProduct_thum01();
		String fname04 = probean.getProduct_thum02();
		String fname05 = probean.getProduct_thum03();
		String fname06 = probean.getProduct_video();
		
		prodao.productDelete(pro_num); //레코드를 디비로부터 삭제하는 메소드
		
		if(fname01 != null){//이진 파일이 존재한다면
			File file01 = new File(up+fname01);
			File file02 = new File(up+fname02);
			File file03 = new File(up+fname03);
			File file04 = new File(up+fname04);
			File file05 = new File(up+fname05);
			File file06 = new File(up+fname06);
			
			file01.delete(); //기존 이진파일을 폴더로부터 삭제한다.
			file02.delete();
			file03.delete();
			file04.delete();
			file05.delete();
			file06.delete();
		}
		
		//게시물 목록으로 페이지 번호를 넘기면서 해당 페이지 목록으로 이동
		response.sendRedirect("admin_product_list.do?page=" + page);
		
		/*if(pwd == "1234"){ //비밀번호가 "1234" 인 경우
			prodao.companyDelete(pro_num); //레코드를 디비로부터 삭제하는 메소드
			
			if(fname != null){//이진 파일이 존재한다면
				File file = new File(up+fname);
				file.delete(); //기존 이진파일을 폴더로부터 삭제한다.
			}
			
			//게시물 목록으로 페이지 번호를 넘기면서 해당 페이지 목록으로 이동
			response.sendRedirect("admin_company_list.do?page=" + page);
		}else{//비번이다른 경우
			out.println("<script>");
			out.println("alert('비밀번호가 일치하지 않습니다.')");
			out.println("history.go(-1)"); //바로 이전 페이지로 이동
			out.println("</script>");
		}*/
		return null;
	}
}
