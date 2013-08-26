package com.product.action;

import java.io.PrintWriter;
import java.io.File;
import java.util.Calendar;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.product.dao.ProductBean;
import com.product.dao.ProductDAO;
import com.mtory.action.Action;
import com.mtory.action.ActionForward;
import com.oreilly.servlet.MultipartRequest;

public class ProductWriteOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		ProductDAO dao = new ProductDAO();
		ProductBean probean = new ProductBean();
		
		PrintWriter out = response.getWriter();
		
		ActionForward forward = new ActionForward();

		String saveFolder = "C:/WebProject/DoranMall/WebContent/upload";

		int fileSize = 20*1024*1024; //최대 크기 20MB 지정

		MultipartRequest multi = null;
		
		multi = new MultipartRequest(request, saveFolder, fileSize, "UTF-8");

		String product_code = multi.getParameter("product_code").trim();
		String product_name = multi.getParameter("product_name").trim();
		int product_price = Integer.parseInt(multi.getParameter("product_price"));
		int product_number = Integer.parseInt(multi.getParameter("product_number"));
		String product_company = multi.getParameter("product_company").trim();
		String product_summary = multi.getParameter("product_summary").trim();
		
		File product_description = multi.getFile("product_description");
		File product_mainImg = multi.getFile("product_mainImg");
		File product_thum01 = multi.getFile("product_thum01");
		File product_thum02 = multi.getFile("product_thum02");
		File product_thum03 = multi.getFile("product_thum03");
		File product_video = multi.getFile("product_video");
		
		String product_opt01 = multi.getParameter("product_opt01").trim();
		String product_opt01_1 = multi.getParameter("product_opt01_1").trim();
		String product_opt01_2 = multi.getParameter("product_opt01_2").trim();
		String product_opt01_3 = multi.getParameter("product_opt01_3").trim();
		String product_opt01_4 = multi.getParameter("product_opt01_4").trim();
		String product_opt01_5 = multi.getParameter("product_opt01_5").trim();
		String product_opt02 = multi.getParameter("product_opt02").trim();
		String product_opt02_1 = multi.getParameter("product_opt02_1").trim();
		String product_opt02_2 = multi.getParameter("product_opt02_2").trim();
		String product_opt02_3 = multi.getParameter("product_opt02_3").trim();
		String product_opt02_4 = multi.getParameter("product_opt02_4").trim();
		String product_opt02_5 = multi.getParameter("product_opt02_5").trim();
		
		String product_startDate = multi.getParameter("product_startDate").trim();
		String product_endDate = multi.getParameter("product_endDate").trim();
		int product_post = Integer.parseInt(multi.getParameter("product_post"));
		int product_state = Integer.parseInt(multi.getParameter("product_state"));
		int product_shop = Integer.parseInt(multi.getParameter("product_shop"));

		if(product_description != null){//첨부한 이진 파일 있는 경우
			String fileName01 = product_description.getName(); //이진 파일명을 저장한다.
			String fileName02 = product_mainImg.getName();
			String fileName03 = product_thum01.getName();
			String fileName04 = product_thum02.getName();
			String fileName05 = product_thum03.getName();
			String fileName06 = product_video.getName();
			
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
			int random01 = r.nextInt(100000000); //0이 8개
			int random02 = r.nextInt(100000000);
			int random03 = r.nextInt(100000000);
			int random04 = r.nextInt(100000000);
			int random05 = r.nextInt(100000000);
			int random06 = r.nextInt(100000000);
			//1억 사이의 정수형 난수를 발생시킨다. 
			
			/*파일 확장자 구하기*/
			int index = product_description.getName().lastIndexOf(".");
			//File 클래스의 getName() 메소드는 이진파일명을 받아온다.
			//lastIndexOf(문자열) 메소드는 String 클래스의 메소드로
			//해당 문자열의 위치값을 반환해준다.
			String fileExtension = product_description.getName().substring(index+1);
			/*파일 확장자 구하기 끝*/
			
			//확장자를 포함하여 새로운 파일명을 저장한다.
			String refileName01
				= "product" + year + month + date + random01 + "." + fileExtension;
			String refileName02
				= "product" + year + month + date + random02 + "." + fileExtension;
			String refileName03
				= "product" + year + month + date + random03 + "." + fileExtension;
			String refileName04
				= "product" + year + month + date + random04 + "." + fileExtension;
			String refileName05
				= "product" + year + month + date + random05 + "." + fileExtension;
			String refileName06
				= "product" + year + month + date + random06 + "." + fileExtension;

			//db에 저장될 파일 이름 저장
			String fileDBName01
				= "/" + year + "-" + month + "-" + date + "/" + refileName01;
			String fileDBName02
				= "/" + year + "-" + month + "-" + date + "/" + refileName02;
			String fileDBName03
				= "/" + year + "-" + month + "-" + date + "/" + refileName03;
			String fileDBName04
				= "/" + year + "-" + month + "-" + date + "/" + refileName04;
			String fileDBName05
				= "/" + year + "-" + month + "-" + date + "/" + refileName05;
			String fileDBName06
				= "/" + year + "-" + month + "-" + date + "/" + refileName06;
			
			product_description.renameTo(new File(homedir + "/" + refileName01));
			product_mainImg.renameTo(new File(homedir + "/" + refileName02));
			product_thum01.renameTo(new File(homedir + "/" + refileName03));
			product_thum02.renameTo(new File(homedir + "/" + refileName04));
			product_thum03.renameTo(new File(homedir + "/" + refileName05));
			product_video.renameTo(new File(homedir + "/" + refileName06));
			//바뀐 이진파일명으로 업로드
			
			probean.setProduct_description(fileDBName01);	
			probean.setProduct_mainImg(fileDBName02);
			probean.setProduct_thum01(fileDBName03);
			probean.setProduct_thum02(fileDBName04);
			probean.setProduct_thum03(fileDBName05);
			probean.setProduct_video(fileDBName06);
		}
	
		probean.setProduct_code(product_code);
		probean.setProduct_name(product_name);
		probean.setProduct_price(product_price);
		probean.setProduct_number(product_number);
		probean.setProduct_company(product_company);
		probean.setProduct_summary(product_summary);

		probean.setProduct_opt01(product_opt01);
		probean.setProduct_opt01_1(product_opt01_1);
		probean.setProduct_opt01_2(product_opt01_2);
		probean.setProduct_opt01_3(product_opt01_3);
		probean.setProduct_opt01_4(product_opt01_4);
		probean.setProduct_opt01_5(product_opt01_5);
		probean.setProduct_opt02(product_opt02);
		probean.setProduct_opt02_1(product_opt02_1);
		probean.setProduct_opt02_2(product_opt02_2);
		probean.setProduct_opt02_3(product_opt02_3);
		probean.setProduct_opt02_4(product_opt02_4);
		probean.setProduct_opt02_5(product_opt02_5);
		probean.setProduct_startDate(product_startDate);
		probean.setProduct_endDate(product_endDate);
		probean.setProduct_post(product_post);
		probean.setProduct_state(product_state);
		probean.setProduct_shop(product_shop);	
		
		//2) 참조변수를 이용하여 저장 메소드를 호출한다. 
		int re = dao.insertproduct(probean);
		
		if(re == 1){ //insert가 성공한 경우
			response.sendRedirect("./admin_product_list.do");
		}else{ //insert가 실패한 경우
			out.println("<script>");
			out.println("alert('게시물 저장에 실패하였습니다.')");
			out.println("history.back()");
			out.println("</script>");
		}	
		return null;
	}
}
