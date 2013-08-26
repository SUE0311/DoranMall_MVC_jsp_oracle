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
		
	    int com_num = Integer.parseInt(multi.getParameter("num")); //�۹�ȣ
	    int page=Integer.parseInt(multi.getParameter("page"));  //�ʹ�ȣ ����
	    
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
		
		CompanyBean db_com = cd.getCont(com_num);  //�۹�ȣ�� �ش��ϴ� ��� ������ ������
    
		File UpFile = multi.getFile("com_file1");
		
		if(UpFile != null){//÷���� ���������� �ִٸ�
			
		    String fileName=UpFile.getName(); //�������ϸ� ����
		    File DelFile=new File(saveFolder+db_com.getCom_file1()); //���ϰ�ü ����
				
		    	/*if(DelFile.exists()){//���� ���������� �����Ѵٸ�
					DelFile.delete();//���������� ����
				}*/
		    
			Calendar c=Calendar.getInstance();

			int year=c.get(Calendar.YEAR);
			int month=c.get(Calendar.MONTH)+1;

			int date=c.get(Calendar.DATE);
			
			String homedir=saveFolder+"/"+year+"-"+month+"-"+date;
			
			File path1=new File(homedir);
			if(!(path1.exists())){
				path1.mkdir();//���ο� ������ ����
			}
			Random r=new Random();
			int random=r.nextInt(100000000);

	        int index = fileName.lastIndexOf(".");

	        String fileExtension=fileName.substring(index + 1);

	        String refileName="company"+year+month+date+random+"."+
	                fileExtension;//���ο� ���ϸ��� ����
	        
	        String fileDBName="/"+year+"-"+month+"-"+date+"/"+refileName;
	        //�����ͺ��̽��� ����� ���ڵ� ��
	        UpFile.renameTo(new File(homedir+"/"+refileName));
	        
	        //�ٲ� �������Ϸ� ������ ���ε�
	        combean.setCom_file1(fileDBName);
		}
		
		// ���� ���� �� ���
		combean.setCom_co(com_co);
		combean.setCom_name(com_name);
		combean.setCom_tel(com_tel);
		combean.setCom_mail(com_mail);
		combean.setCom_website(com_website);
		combean.setCom_product(com_product);
		combean.setCom_price(com_price);
		combean.setCom_cont(com_cont);
		
		combean.setCom_num(com_num);
	
		cd.companyEdit(combean);//�����޼��� ȣ��
          
		response.sendRedirect("admin_company_cont.do?num="+com_num+"&page="+page+"&state=cont");
        //���뺸��� �̵�(�۹�ȣ, ��������ȣ, ����)
		
		return null;
	}

}
