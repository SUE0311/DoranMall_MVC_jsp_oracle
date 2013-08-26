package com.company.action;

import java.io.File;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.dao.AdminBean;
import com.admin.dao.AdminDAO;
import com.company.dao.CompanyBean;
import com.company.dao.CompanyDAO;
import com.mtory.action.Action;
import com.mtory.action.ActionForward;

public class CompanyDelOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		CompanyDAO cd = new CompanyDAO();
		
		int com_num = Integer.parseInt(request.getParameter("num"));
		int page = Integer.parseInt(request.getParameter("page"));
		
		//����ڰ� �Է��� ��й�ȣ�� �Ķ���ͷ� �޴´�. 
		String pwd = request.getParameter("admin_pwd").trim();
		String id = request.getParameter("admin_id").trim();
		System.out.println("========== " + pwd);
		System.out.println("========== " + id);
		
		//���� ���ε� ���� ���
		String up = "C:/WebProject/DoranMall/WebContent/upload";
		
		//��ǰ ���̺��� ��ǰ��ȣ�� �ش��ϴ� ��� ������ �����´�. 
		CompanyBean combean = cd.getCont(com_num);
		String fname = combean.getCom_file1();
		
		//���� ���̺��� ��й�ȣ�� �ش��ϴ� ��� ������ �����´�. 
		AdminDAO ad = new AdminDAO();
		AdminBean adbean = ad.getMember(id);
		System.out.println("============ ������ ��й�ȣ" + adbean.getAdmin_pass());
		
//		���� ��й�ȣ Ȯ�� ���� ������ ��
//		cd.companyDelete(com_num); //���ڵ带 ���κ��� �����ϴ� �޼ҵ�
//		if(fname != null){//���� ������ �����Ѵٸ�
//			File file = new File(up+fname);
//			file.delete(); //���� ���������� �����κ��� �����Ѵ�.
//		}
//		�Խù� ������� ������ ��ȣ�� �ѱ�鼭 �ش� ������ ������� �̵�
//		response.sendRedirect("admin_company_list.do?page=" + page);
		

		if(!adbean.getAdmin_pass().equals(pwd)){ //��й�ȣ�� �ٸ� ���
			out.println("<script>");
			out.println("alert('��й�ȣ�� ��ġ���� �ʽ��ϴ�.')");
			out.println("history.go(-1)"); //�ٷ� ���� �������� �̵�
			out.println("</script>");
		}else{//����� ���� ���
			cd.companyDelete(com_num);  //���ڵ带 ���κ��� �����ϴ� �޼ҵ�
			
			if(fname != null){//���� ������ �����Ѵٸ�
				File file = new File(up+fname);
				file.delete(); //���� ���������� �����κ��� �����Ѵ�.
			}
			
			//�Խù� ������� ������ ��ȣ�� �ѱ�鼭 �ش� ������ ������� �̵�
			response.sendRedirect("admin_company_list.do?page=" + page);
		}
		return null;
	}
}
