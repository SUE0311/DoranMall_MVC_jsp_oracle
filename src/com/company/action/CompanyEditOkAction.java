package com.company.action;

import java.io.File;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.dao.CompanyBean;
import com.company.dao.CompanyDAO;
import com.mtory.action.Action;
import com.mtory.action.ActionForward;
import com.oreilly.servlet.MultipartRequest;

public class CompanyEditOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		CompanyDAO cd = new CompanyDAO();
		CompanyBean combean = new CompanyBean();
		
	    ActionForward forward=new ActionForward();
	    
	    response.setContentType("text/html;charset=UTF-8");
	    request.setCharacterEncoding("UTF-8");
	    
		String saveFolder = "C:/WebProject/DoranMall/WebContent/upload";

		int fileSize = 5*1024*1024;
		
		MultipartRequest multi = null;
		
		multi = new MultipartRequest(request, saveFolder, fileSize, "UTF-8");
		
	    int com_num = Integer.parseInt(multi.getParameter("num")); //글번호
	    int page=Integer.parseInt(multi.getParameter("page"));  //쪽번호 저장
	    
	    System.out.println("++++++++++++" + com_num);
	    
	    String com_co = multi.getParameter("com_co").trim();
		String com_name = multi.getParameter("com_name").trim();
		String com_tel = multi.getParameter("com_tel").trim();
		String com_mail = multi.getParameter("com_mail").trim();
		String com_website = multi.getParameter("com_website").trim();
		String com_product = multi.getParameter("com_product").trim();
		String com_price = multi.getParameter("com_price");
		String com_cont = multi.getParameter("com_cont").trim();
		
		System.out.println("++++++++++++" + com_cont);
		
		CompanyBean db_com = cd.getCont(com_num);  //글번호에 해당하는 디비 내용을 가져옴
    
		File UpFile = multi.getFile("com_file1");
		
		if(UpFile != null){//첨부한 이진파일이 있다면
			
		    String fileName=UpFile.getName(); //이진파일명 저장
		    File DelFile=new File(saveFolder+db_com.getCom_file1()); //파일객체 생성
				
		    	/*if(DelFile.exists()){//기존 이진파일이 존재한다면
					DelFile.delete();//기존파일을 삭제
				}*/
		    
			Calendar c=Calendar.getInstance();

			int year=c.get(Calendar.YEAR);
			int month=c.get(Calendar.MONTH)+1;

			int date=c.get(Calendar.DATE);
			
			String homedir=saveFolder+"/"+year+"-"+month+"-"+date;
			
			File path1=new File(homedir);
			if(!(path1.exists())){
				path1.mkdir();//새로운 폴더를 생성
			}
			Random r=new Random();
			int random=r.nextInt(100000000);

	        int index = fileName.lastIndexOf(".");

	        String fileExtension=fileName.substring(index + 1);

	        String refileName="company"+year+month+date+random+"."+
	                fileExtension;//새로운 파일명을 저장
	        
	        String fileDBName="/"+year+"-"+month+"-"+date+"/"+refileName;
	        //데이터베이스에 저장될 레코드 값
	        UpFile.renameTo(new File(homedir+"/"+refileName));
	        
	        //바뀐 이진파일로 서버에 업로드
	        combean.setCom_file1(fileDBName);
		}
		
		// 수정 내용 빈에 담기
		combean.setCom_co(com_co);
		combean.setCom_name(com_name);
		combean.setCom_tel(com_tel);
		combean.setCom_mail(com_mail);
		combean.setCom_website(com_website);
		combean.setCom_product(com_product);
		combean.setCom_price(com_price);
		combean.setCom_cont(com_cont);
		
		combean.setCom_num(com_num);
	
		cd.companyEdit(combean);//수정메서드 호출
          
		response.sendRedirect("admin_company_cont.do?num="+com_num+"&page="+page+"&state=cont");
        //내용보기로 이동(글번호, 페이지번호, 내용)
		
		return null;
	}

}
