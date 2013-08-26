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
		
		//���ε��� ������ �����ϴ� ���� ��� ����(���� ������Ʈ ���)
		String saveFolder = "C:/WebProject/DoranMall/WebContent/upload";
		
		//���� ���� ���ε� �ִ� ���� ũ�� ����
		int fileSize = 5*1024*1024; //�ִ� ũ�� 5MB ����
				
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

		//÷���� ���� ������ �����´�. 
		File UpFile = multi.getFile("com_file1");
		
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
				= "company" + year + month + date + random + "." + fileExtension;
			//db�� ����� ���� �̸� ����
			String fileDBName
				= "/" + year + "-" + month + "-" + date + "/" + refileName;
			
			UpFile.renameTo(new File(homedir + "/" + refileName));
			//�ٲ� �������ϸ����� ���ε�
			
			combean.setCom_file1(fileDBName);	
		}
		
		//���ε��� ���ϸ��� �������� ���� ��쿡�� ������ �׸� db�� �����Ѵ�.
		combean.setCom_co(com_co);
		combean.setCom_name(com_name);
		combean.setCom_tel(com_tel);
		combean.setCom_mail(com_mail);
		combean.setCom_website(com_website);
		combean.setCom_product(com_product);
		combean.setCom_price(com_price);
		combean.setCom_cont(com_cont);
		
		cd.insertCompany(combean);
		
		//���� �������� �̵��Ѵ�. 
		response.sendRedirect("./index.do");
		
		return null;
	}

}
