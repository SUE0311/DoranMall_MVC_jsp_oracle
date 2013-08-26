package com.wishlist.action;

import java.io.File;
import java.util.Calendar;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wishlist.dao.WishlistBean;
import com.wishlist.dao.WishlistDAO;

import com.mtory.action.Action;
import com.mtory.action.ActionForward;
import com.oreilly.servlet.MultipartRequest;


public class WishlistWriteOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		WishlistDAO wd = new WishlistDAO();
		WishlistBean wishlistbean = new WishlistBean();
		
		ActionForward forward = new ActionForward();
		
		//업로드한 파일을 저장하는 폴더 경로 지정(현재 프로젝트 경로)
		String saveFolder = "C:/WebProject/DoranMall/WebContent/upload"; 
		//폴더 구분자는 역슬래시 2개 또는 슬래시 1개로 지정한다
		
		//이진 파일 업로드 최대 파일 크기 지정
		int fileSize = 5*1024*1024; //최대 크기 5MB 지정
		
		MultipartRequest multi = null;
		
		multi = new MultipartRequest(request, saveFolder, fileSize, "UTF-8");
		//MultipartRequest는 이진 파일 업로드를 위한 객체를 생성하는 클래스이다.
		//사용되는 매개변수
		//첫번째 : 사용자 폼에서 입력한 정보를 서버로 가져오는 것
		//두번째 : 이진파일 업로드 서버 경로명
		//세번째 : 이진파일 업로드 최대 파일 크기
		//네번째 : 언어 인코딩 파일
		String wishlist_id = multi.getParameter("wishlist_id").trim();
		String wishlist_pass = multi.getParameter("wishlist_pass").trim();
		String wishlist_title = multi.getParameter("wishlist_title").trim();
		String wishlist_content = multi.getParameter("wishlist_content").trim();
		
		//첨부한 이진 파일을 가져온다. 
		File UpFile = multi.getFile("wishlist_file");
		
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
				= "wishlist" + year + month + date + random + "." + fileExtension;
			//db에 저장될 파일 이름 저장
			String fileDBName
				= "/" + year + "-" + month + "-" + date + "/" + refileName;
			
			UpFile.renameTo(new File(homedir + "/" + refileName));
			//바뀐 이진파일명으로 업로드
			
			wishlistbean.setWishlist_file(fileDBName);	
		}	
		//업로드할 파일명을 지정하지 않은 경우에는 나머지 항목만 db에 저장한다.
		wishlistbean.setWishlist_id(wishlist_id);
		wishlistbean.setWishlist_pass(wishlist_pass);
		wishlistbean.setWishlist_title(wishlist_title);
		wishlistbean.setWishlist_content(wishlist_content);
		
		//DAO 클래스의 insertMember() 메소드를 호출하여 레코드를 저장한다.
		//전달인자는 빈의 객체 = 저장될 레코드
		//System.out.println(wishlistbean.toString());
		wd.insertwishlist(wishlistbean);
		
		//목록 보기 페이지로 이동한다.
		response.sendRedirect("./wishlist_list.do");

		return null;
	}

}
