package com.wishlist.action;

import java.io.File;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wishlist.dao.WishlistBean;
import com.wishlist.dao.WishlistDAO;

import com.mtory.action.Action;
import com.mtory.action.ActionForward;

import com.oreilly.servlet.MultipartRequest;

public class WishlistEditOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		WishlistDAO wishlistdao=new WishlistDAO();
	    WishlistBean wishlistbean=new WishlistBean();  // db연동 객체 생성
	    
	    ActionForward forward=new ActionForward();
	    response.setContentType("text/html;charset=UTF-8");//웹상의 한글 처리
	    request.setCharacterEncoding("UTF-8");
	    //method=post방식일때 한글을 안깨지게 함
	    
	    PrintWriter out=response.getWriter();//출력스트림 객체 생성
	    
	    String saveFolder = "C:/WebProject/DoranMall/WebContent/upload"; 
	    //이진파일 업로드 경로
	    int fileSize=5*1024*1024;
	    //이진파일 최대 업로드 크기
	    
	    MultipartRequest multi=null; //이진파일 업로드 객체
        multi=new MultipartRequest(request,saveFolder,fileSize,"utf-8");
        //첫번째 전달인자는 사용자폼에서 입력한 정보를 서버로 가져온다.
        //두번째 전달인자는 이진파일 서버 업로드 경로
        //세번째 전달인자는 이진파일 최대 업로드 크기
        //네번째 전달인자는 언어코딩 타입 지정
        int wishlist_num=Integer.parseInt(multi.getParameter("num")); //글번호
        int page=Integer.parseInt(multi.getParameter("page"));  //쪽번호 저장
        
        System.out.println("================ page : " + page);
        String wishlist_pass=multi.getParameter("wishlist_pass").trim();//비번
        String wishlist_title=multi.getParameter("wishlist_title").trim(); //글제목
        String wishlist_content=multi.getParameter("wishlist_content").trim(); //글내용
        String wishlist_id=multi.getParameter("wishlist_id").trim();  //글쓴이
        
        WishlistBean db_wishlist=wishlistdao.getCont(wishlist_num);  //글번호에 해당하는 디비 내용을 가져옴
    
        if(!db_wishlist.getWishlist_pass().equals(wishlist_pass)){//비번이 다른경우 경고창
        	out.println("<script>");
    	    out.println("alert('비번이 다릅니다!')");
            out.println("history.go(-1)");
            out.println("</script>");
        }else{//비번이 같은 경우
    	    File UpFile=multi.getFile("wishlist_file");
    	      //첨부한 이진파일을 가져옴
    	    if(UpFile != null){//첨부한 이진파일이 있다면
    		    String fileName=UpFile.getName(); //이진파일명 저장
    		    File DelFile=new File(saveFolder+db_wishlist.getWishlist_file()); //파일객체 생성
    		if(DelFile.exists()){//기존 이진파일이 존재한다면
    			DelFile.delete();//기존파일을 삭제
    		}
    		Calendar c=Calendar.getInstance();
    		//Calendar추상클래스는 new에 의해서 객체 생성을 못함.
    		//년월일 시분초 값을반환하는 클래스
    		int year=c.get(Calendar.YEAR);
    		int month=c.get(Calendar.MONTH)+1;//월값 반환 . +1 을 한이유는
    		//1월이 0으로 반환되기 때문이다.
    		int date=c.get(Calendar.DATE);//일값
    		
    		String homedir=saveFolder+"/"+year+"-"+month+"-"+date;
    		File path1=new File(homedir);
    		if(!(path1.exists())){
    			path1.mkdir();//새로운 폴더를 생성
    		}
    		Random r=new Random();
    		int random=r.nextInt(100000000);
    		//1억사이의 임의의 정수 난수를 발생
    		
    		/****확장자 구하기 시작 ****/
            int index = fileName.lastIndexOf(".");
            //File 클래스의 getName() 메서드는 이진파일명을 받아온다.
            //lastIndexOf("문자")는  String클래스의 메서드로 해당문자
            //를 문자열 끝 즉 우측에서 헤아려 문자의 위치번호를 반환한다.
            String fileExtension=fileName.substring(index + 1);
            //파일의 확장자를 구한다.
            /****확장자 구하기 끝 ***/
            String refileName="wishlist"+year+month+date+random+"."+
                    fileExtension;//새로운 파일명을 저장
            
            String fileDBName="/"+year+"-"+month+"-"+date+"/"+refileName;
            //데이터베이스에 저장될 레코드 값
            UpFile.renameTo(new File(homedir+"/"+refileName));
            //바뀐 이진파일로 서버에 업로드
            wishlistbean.setWishlist_file(fileDBName);
    	 }
    	  wishlistbean.setWishlist_id(wishlist_id);   // 수정
    	  wishlistbean.setWishlist_title(wishlist_title);
    	  wishlistbean.setWishlist_content(wishlist_content);
    	  wishlistbean.setWishlist_num(wishlist_num);
    	
    	  wishlistdao.wishlistEdit(wishlistbean);//수정메서드 호출
          response.sendRedirect("./wishlist_cont.do?num="+wishlist_num+"&page="+page+"&state=cont");
          //내용보기로 이동(글번호, 페이지번호, 내용)
      }    
		return null;
	}

}
