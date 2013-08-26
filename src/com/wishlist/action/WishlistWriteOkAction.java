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
		
		//���ε��� ������ �����ϴ� ���� ��� ����(���� ������Ʈ ���)
		String saveFolder = "C:/WebProject/DoranMall/WebContent/upload"; 
		//���� �����ڴ� �������� 2�� �Ǵ� ������ 1���� �����Ѵ�
		
		//���� ���� ���ε� �ִ� ���� ũ�� ����
		int fileSize = 5*1024*1024; //�ִ� ũ�� 5MB ����
		
		MultipartRequest multi = null;
		
		multi = new MultipartRequest(request, saveFolder, fileSize, "UTF-8");
		//MultipartRequest�� ���� ���� ���ε带 ���� ��ü�� �����ϴ� Ŭ�����̴�.
		//���Ǵ� �Ű�����
		//ù��° : ����� ������ �Է��� ������ ������ �������� ��
		//�ι�° : �������� ���ε� ���� ��θ�
		//����° : �������� ���ε� �ִ� ���� ũ��
		//�׹�° : ��� ���ڵ� ����
		String wishlist_id = multi.getParameter("wishlist_id").trim();
		String wishlist_pass = multi.getParameter("wishlist_pass").trim();
		String wishlist_title = multi.getParameter("wishlist_title").trim();
		String wishlist_content = multi.getParameter("wishlist_content").trim();
		
		//÷���� ���� ������ �����´�. 
		File UpFile = multi.getFile("wishlist_file");
		
		if(UpFile != null){//÷���� ���� ���� �ִ� ���
			String fileName = UpFile.getName(); //���� ���ϸ��� �����Ѵ�.
			Calendar c = Calendar.getInstance(); //�޷� �ν��Ͻ��� �����Ѵ�.
			//Calendar�� �߻�Ŭ������ ����� �ú��ʸ� ��ȯ���ش�.
			//���� ��¥�� �̿��Ͽ� ���ϸ��� �����ϱ� ���Ͽ�
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH)+1; //����+1(1���� 0�̹Ƿ�)
			int date = c.get(Calendar.DATE); //�ϰ�
			
			String homedir=saveFolder+"/"+year+"-"+month+"-"+date;
			//���ο� ���� ��� ���� ����(������ ������ ��-��-�� ���·� �����ȴ�.)
			
			File path1 = new File(homedir);
			
			//���ο� ���� ��ΰ� ������ ����
			if(!(path1.exists())){path1.mkdir();}
			
			Random r = new Random();
			int random = r.nextInt(100000000); //0�� 8��
			//1�� ������ ������ ������ �߻���Ų��. 
			
			/*���� Ȯ���� ���ϱ�*/
			int index = UpFile.getName().lastIndexOf(".");
			//File Ŭ������ getName() �޼ҵ�� �������ϸ��� �޾ƿ´�.
			//lastIndexOf(���ڿ�) �޼ҵ�� String Ŭ������ �޼ҵ��
			//�ش� ���ڿ��� ��ġ���� ��ȯ���ش�.
			String fileExtension = UpFile.getName().substring(index+1);
			/*���� Ȯ���� ���ϱ� ��*/
			
			//Ȯ���ڸ� �����Ͽ� ���ο� ���ϸ��� �����Ѵ�.
			String refileName
				= "wishlist" + year + month + date + random + "." + fileExtension;
			//db�� ����� ���� �̸� ����
			String fileDBName
				= "/" + year + "-" + month + "-" + date + "/" + refileName;
			
			UpFile.renameTo(new File(homedir + "/" + refileName));
			//�ٲ� �������ϸ����� ���ε�
			
			wishlistbean.setWishlist_file(fileDBName);	
		}	
		//���ε��� ���ϸ��� �������� ���� ��쿡�� ������ �׸� db�� �����Ѵ�.
		wishlistbean.setWishlist_id(wishlist_id);
		wishlistbean.setWishlist_pass(wishlist_pass);
		wishlistbean.setWishlist_title(wishlist_title);
		wishlistbean.setWishlist_content(wishlist_content);
		
		//DAO Ŭ������ insertMember() �޼ҵ带 ȣ���Ͽ� ���ڵ带 �����Ѵ�.
		//�������ڴ� ���� ��ü = ����� ���ڵ�
		//System.out.println(wishlistbean.toString());
		wd.insertwishlist(wishlistbean);
		
		//��� ���� �������� �̵��Ѵ�.
		response.sendRedirect("./wishlist_list.do");

		return null;
	}

}
