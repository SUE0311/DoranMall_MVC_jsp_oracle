package com.company.action;

import java.io.File;
import java.util.Calendar;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.dao.CompanyBean;
import com.company.dao.CompanyDAO;
import com.mtory.action.Action;
import com.mtory.action.ActionForward;
import com.oreilly.servlet.MultipartRequest;

public class CompanyContactOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		CompanyDAO cd = new CompanyDAO();
		CompanyBean combean = new CompanyBean();
		
		ActionForward forward = new ActionForward();
		
		//업로드한 파일을 저장하는 폴더 경로 지정(현재 프로젝트 경로)
		String saveFolder = "C:/WebProject/DoranMall/WebContent/upload";
		
		//이진 파일 업로드 최대 파일 크기 지정
		int fileSize = 5*1024*1024; //최대 크기 5MB 지정
				
		MultipartRequest multi = null;
		
		multi = new MultipartRequest(request, saveFolder, fileSize, "UTF-8");
		
		String com_co = multi.getParameter("com_co").trim();
		String com_name = multi.getParameter("com_name").trim();
		String com_tel = multi.getParameter("com_tel").trim();
		String com_mail = multi.getParameter("com_mail").trim();
		String com_website = multi.getParameter("com_website").trim();
		String com_product = multi.getParameter("com_product").trim();
		String com_price = multi.getParameter("com_price");
		String com_cont = multi.getParameter("com_cont").trim();

		//첨부한 이진 파일을 가져온다. 
		File UpFile = multi.getFile("com_file1");
		
		if(UpFile != null){//첨부한 이진 파일 있는 경우
			String fileName = UpFile.getName(); //이진 파일명을 저장한다.
			
			Calendar c = Calendar.getInstance(); //달력 인스턴스를 생성한다.
			//Calendar는 추상클래스로 년월일 시분초를 반환해준다.
			//오늘 날짜를 이용하여 파일명을 생성하기 위하여
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH)+1; //월갑+1(1월이 0이므로)
			int date = c.get(Calendar.DATE); //일값
			
			String homedir=saveFolder+"/"+year+"-"+month+"-"+date;
			//새로운 폴더 경로 설정 저장(생성된 폴더가 년-월-일 형태로 생성된다.)
			
			File path1 = new File(homedir);
			
			//새로운 폴더 경로가 없으면 만듬
			if(!(path1.exists())){path1.mkdir();}
			
			Random r = new Random();
			int random = r.nextInt(100000000); //0이 8개
			//1억 사이의 정수형 난수를 발생시킨다. 
			
			/*파일 확장자 구하기*/
			int index = UpFile.getName().lastIndexOf(".");
			//File 클래스의 getName() 메소드는 이진파일명을 받아온다.
			//lastIndexOf(문자열) 메소드는 String 클래스의 메소드로
			//해당 문자열의 위치값을 반환해준다.
			String fileExtension = UpFile.getName().substring(index+1);
			/*파일 확장자 구하기 끝*/
			
			//확장자를 포함하여 새로운 파일명을 저장한다.
			String refileName
				= "company" + year + month + date + random + "." + fileExtension;
			//db에 저장될 파일 이름 저장
			String fileDBName
				= "/" + year + "-" + month + "-" + date + "/" + refileName;
			
			UpFile.renameTo(new File(homedir + "/" + refileName));
			//바뀐 이진파일명으로 업로드
			
			combean.setCom_file1(fileDBName);	
		}
		
		//업로드할 파일명을 지정하지 않은 경우에는 나머지 항목만 db에 저장한다.
		combean.setCom_co(com_co);
		combean.setCom_name(com_name);
		combean.setCom_tel(com_tel);
		combean.setCom_mail(com_mail);
		combean.setCom_website(com_website);
		combean.setCom_product(com_product);
		combean.setCom_price(com_price);
		combean.setCom_cont(com_cont);
		
		cd.insertCompany(combean);
		
		//메인 페이지로 이동한다. 
		response.sendRedirect("./index.do");
		
		return null;
	}

}
