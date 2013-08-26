package com.product.action;

import java.io.File;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.product.dao.ProductBean;
import com.product.dao.ProductDAO;
import com.mtory.action.Action;
import com.mtory.action.ActionForward;
import com.oreilly.servlet.MultipartRequest;

public class ProductEditOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		ProductDAO dao = new ProductDAO();
		ProductBean bean = new ProductBean();
		
	    ActionForward forward=new ActionForward();
	    
	    response.setContentType("text/html;charset=UTF-8");
	    request.setCharacterEncoding("UTF-8");
	    
		String saveFolder = "C:/WebProject/DoranMall/WebContent/upload";

		int fileSize = 20*1024*1024;
		
		MultipartRequest multi = null;
		
		multi = new MultipartRequest(request, saveFolder, fileSize, "UTF-8");
		
	    int pro_num = Integer.parseInt(multi.getParameter("num")); //글번호
	    int page=Integer.parseInt(multi.getParameter("page"));  //쪽번호 저장
	    
	    System.out.println("++++++++++++" + pro_num);
	    
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
		
		ProductBean db_pro = dao.getCont(pro_num);  //글번호에 해당하는 디비 내용을 가져옴

		if(product_description != null){//첨부한 이진파일이 있다면
			String fileName01 = product_description.getName(); //이진 파일명을 저장한다.
			String fileName02 = product_mainImg.getName();
			String fileName03 = product_thum01.getName();
			String fileName04 = product_thum02.getName();
			String fileName05 = product_thum03.getName();
			String fileName06 = product_video.getName();
			
			File DelFile=new File(saveFolder+db_pro.getProduct_description()); //파일객체 생성
			
	    	/*if(DelFile.exists()){//기존 이진파일이 존재한다면
				DelFile.delete();//기존파일을 삭제
			}*/
			
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
			
			bean.setProduct_description(fileDBName01);	
			bean.setProduct_mainImg(fileDBName02);
			bean.setProduct_thum01(fileDBName03);
			bean.setProduct_thum02(fileDBName04);
			bean.setProduct_thum03(fileDBName05);
			bean.setProduct_video(fileDBName06);
		}
		
		// 수정 내용 빈에 담기
		bean.setProduct_code(product_code);
		bean.setProduct_name(product_name);
		bean.setProduct_price(product_price);
		bean.setProduct_number(product_number);
		bean.setProduct_company(product_company);
		bean.setProduct_summary(product_summary);

		bean.setProduct_opt01(product_opt01);
		bean.setProduct_opt01_1(product_opt01_1);
		bean.setProduct_opt01_2(product_opt01_2);
		bean.setProduct_opt01_3(product_opt01_3);
		bean.setProduct_opt01_4(product_opt01_4);
		bean.setProduct_opt01_5(product_opt01_5);
		bean.setProduct_opt02(product_opt02);
		bean.setProduct_opt02_1(product_opt02_1);
		bean.setProduct_opt02_2(product_opt02_2);
		bean.setProduct_opt02_3(product_opt02_3);
		bean.setProduct_opt02_4(product_opt02_4);
		bean.setProduct_opt02_5(product_opt02_5);
		bean.setProduct_startDate(product_startDate);
		bean.setProduct_endDate(product_endDate);
		bean.setProduct_post(product_post);
		bean.setProduct_state(product_state);
		bean.setProduct_shop(product_shop);	
		
		bean.setProduct_num(pro_num);
	
		dao.productEdit(bean);//수정메서드 호출
          
		response.sendRedirect("admin_product_cont.do?num="+pro_num+"&page="+page+"&state=cont");
        //내용보기로 이동(글번호, 페이지번호, 내용)
		
		return null;
	}

}
