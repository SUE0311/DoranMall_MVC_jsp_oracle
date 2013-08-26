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
	    WishlistBean wishlistbean=new WishlistBean();  // db���� ��ü ����
	    
	    ActionForward forward=new ActionForward();
	    response.setContentType("text/html;charset=UTF-8");//������ �ѱ� ó��
	    request.setCharacterEncoding("UTF-8");
	    //method=post����϶� �ѱ��� �ȱ����� ��
	    
	    PrintWriter out=response.getWriter();//��½�Ʈ�� ��ü ����
	    
	    String saveFolder = "C:/WebProject/DoranMall/WebContent/upload"; 
	    //�������� ���ε� ���
	    int fileSize=5*1024*1024;
	    //�������� �ִ� ���ε� ũ��
	    
	    MultipartRequest multi=null; //�������� ���ε� ��ü
        multi=new MultipartRequest(request,saveFolder,fileSize,"utf-8");
        //ù��° �������ڴ� ����������� �Է��� ������ ������ �����´�.
        //�ι�° �������ڴ� �������� ���� ���ε� ���
        //����° �������ڴ� �������� �ִ� ���ε� ũ��
        //�׹�° �������ڴ� ����ڵ� Ÿ�� ����
        int wishlist_num=Integer.parseInt(multi.getParameter("num")); //�۹�ȣ
        int page=Integer.parseInt(multi.getParameter("page"));  //�ʹ�ȣ ����
        
        System.out.println("================ page : " + page);
        String wishlist_pass=multi.getParameter("wishlist_pass").trim();//���
        String wishlist_title=multi.getParameter("wishlist_title").trim(); //������
        String wishlist_content=multi.getParameter("wishlist_content").trim(); //�۳���
        String wishlist_id=multi.getParameter("wishlist_id").trim();  //�۾���
        
        WishlistBean db_wishlist=wishlistdao.getCont(wishlist_num);  //�۹�ȣ�� �ش��ϴ� ��� ������ ������
    
        if(!db_wishlist.getWishlist_pass().equals(wishlist_pass)){//����� �ٸ���� ���â
        	out.println("<script>");
    	    out.println("alert('����� �ٸ��ϴ�!')");
            out.println("history.go(-1)");
            out.println("</script>");
        }else{//����� ���� ���
    	    File UpFile=multi.getFile("wishlist_file");
    	      //÷���� ���������� ������
    	    if(UpFile != null){//÷���� ���������� �ִٸ�
    		    String fileName=UpFile.getName(); //�������ϸ� ����
    		    File DelFile=new File(saveFolder+db_wishlist.getWishlist_file()); //���ϰ�ü ����
    		if(DelFile.exists()){//���� ���������� �����Ѵٸ�
    			DelFile.delete();//���������� ����
    		}
    		Calendar c=Calendar.getInstance();
    		//Calendar�߻�Ŭ������ new�� ���ؼ� ��ü ������ ����.
    		//����� �ú��� ������ȯ�ϴ� Ŭ����
    		int year=c.get(Calendar.YEAR);
    		int month=c.get(Calendar.MONTH)+1;//���� ��ȯ . +1 �� ��������
    		//1���� 0���� ��ȯ�Ǳ� �����̴�.
    		int date=c.get(Calendar.DATE);//�ϰ�
    		
    		String homedir=saveFolder+"/"+year+"-"+month+"-"+date;
    		File path1=new File(homedir);
    		if(!(path1.exists())){
    			path1.mkdir();//���ο� ������ ����
    		}
    		Random r=new Random();
    		int random=r.nextInt(100000000);
    		//1������� ������ ���� ������ �߻�
    		
    		/****Ȯ���� ���ϱ� ���� ****/
            int index = fileName.lastIndexOf(".");
            //File Ŭ������ getName() �޼���� �������ϸ��� �޾ƿ´�.
            //lastIndexOf("����")��  StringŬ������ �޼���� �ش繮��
            //�� ���ڿ� �� �� �������� ��Ʒ� ������ ��ġ��ȣ�� ��ȯ�Ѵ�.
            String fileExtension=fileName.substring(index + 1);
            //������ Ȯ���ڸ� ���Ѵ�.
            /****Ȯ���� ���ϱ� �� ***/
            String refileName="wishlist"+year+month+date+random+"."+
                    fileExtension;//���ο� ���ϸ��� ����
            
            String fileDBName="/"+year+"-"+month+"-"+date+"/"+refileName;
            //�����ͺ��̽��� ����� ���ڵ� ��
            UpFile.renameTo(new File(homedir+"/"+refileName));
            //�ٲ� �������Ϸ� ������ ���ε�
            wishlistbean.setWishlist_file(fileDBName);
    	 }
    	  wishlistbean.setWishlist_id(wishlist_id);   // ����
    	  wishlistbean.setWishlist_title(wishlist_title);
    	  wishlistbean.setWishlist_content(wishlist_content);
    	  wishlistbean.setWishlist_num(wishlist_num);
    	
    	  wishlistdao.wishlistEdit(wishlistbean);//�����޼��� ȣ��
          response.sendRedirect("./wishlist_cont.do?num="+wishlist_num+"&page="+page+"&state=cont");
          //���뺸��� �̵�(�۹�ȣ, ��������ȣ, ����)
      }    
		return null;
	}

}
