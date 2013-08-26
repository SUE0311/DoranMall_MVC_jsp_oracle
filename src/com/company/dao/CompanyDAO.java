package com.company.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class CompanyDAO {

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	DataSource ds = null;
	String sql = null;
	
	//��� ������ ���� �������� ����
	public CompanyDAO(){
		try{
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/OracleDB");		 
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/*DB�۾��� �ʿ��� ������ �޼ҵ� ������ �ۼ�*/
	
	/*��û�� �����ϱ�*/
	public void insertCompany(CompanyBean combean) {
		try{
			//max() �Լ��� �ִ밪�� ���ϴ� �Լ��̴�.
			con=ds.getConnection();
			sql = "select max(com_num) from company";
			
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			int num = 0;
			
			if(rs.next()){
				num = rs.getInt(1)+1; //�ִ� �� ��ȣ +1
			}else{
				num=1;
			}
		
			sql = "insert into company values(?,?,?,?,?,?,?,?,?,?,sysdate)";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, num);     		//ù��° ����ǥ�� ��ȣ ����
			pstmt.setString(2, combean.getCom_co()); 		    //�ι�° ����ǥ�� ȸ���̸� ����
			pstmt.setString(3, combean.getCom_name());   		//����° ����ǥ�� ����� �̸� ����
			pstmt.setString(4, combean.getCom_tel());    		//�׹�° ����ǥ�� ����ó ����
			pstmt.setString(5, combean.getCom_mail());    		//�ټ���° ����ǥ�� �̸��� ����
			pstmt.setString(6, combean.getCom_website());    	//����° ����ǥ�� ������Ʈ ����
			pstmt.setString(7, combean.getCom_product());    	//�ϰ�° ����ǥ�� ��ǰ�� ����
			pstmt.setString(8, combean.getCom_price());    		//����° ����ǥ�� ���� ����
			pstmt.setString(9, combean.getCom_cont());    		//��ȩ° ����ǥ�� ��ǰ���� ����
			pstmt.setString(10, combean.getCom_file1());    	//����° ����ǥ�� ÷������ ����
			
			pstmt.executeUpdate();
			
			rs.close(); pstmt.close(); con.close();	
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/*�� ���ڵ� ��(���� ��û�� ȸ��) ���ϱ�*/
	public int getListCount() {
		int count = 0;
		
		try{
			//count(*)�� ��ü ���ڵ� ���� ����� �ִ� sql �Լ��̴�.
			con=ds.getConnection();
			sql = "select count(*) from company";
			
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()){ //���ڵ尡 �ִ� ���
				count=rs.getInt(1); //�� ���ڵ� �� ����
			}
			rs.close(); pstmt.close(); con.close();	
		}catch(Exception e){
			e.printStackTrace();
		}
		return count;
	}
	
	/*���� ��û�� ȸ�� ��Ϻ���*/
	public List<CompanyBean> getCompanyList(int page, int limit) {
		//List�� ���� Ŭ������ ArrayList�� �̿��Ͽ� ��ü�� �����Ѵ�. 
		List<CompanyBean> companyList = new ArrayList<CompanyBean>();
		
		int startrow = (page-1)*10+1;		//������ ���۹�ȣ
		int endrow = page*limit; 			//������ ����ȣ
		
		try{
			con=ds.getConnection();
			sql = "select * from " +
					" (select * from company order by com_num desc) " +
					" where com_num >=? and com_num <=?";
			
			/*sql ="select * from (select bbs.*, rownum as rnum "
					 + " from (select * from bbs order by bbs_re_ref desc, bbs_re_seq asc) bbs)" 
					 + " where rnum >=? and rnum<=?"; */
			//�ڷ�� �׷��ȣ(bbs_re_ref)�� ������������ ����(�ֱٿ� �ۼ� ���� ����)
			//�亯��(bbs_re_seq)�� �׷��ȣ�� �Ʒ������� ���ĵǰ� �Ѵ�. 
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				CompanyBean combean = new CompanyBean();
				
				combean.setCom_num(rs.getInt("com_num"));						//�۹�ȣ
				combean.setCom_product(rs.getString("com_product"));			//���� ��ǰ
				combean.setCom_price(rs.getString("com_price"));				//���� ��ǰ ����
				combean.setCom_co(rs.getString("com_co"));						//ȸ�� �̸�
				combean.setCom_name(rs.getString("com_name"));					//����� �̸�
				combean.setCom_date(rs.getString("com_date").substring(0,10));	//��û��
				//0���� 10 �̸� ������ ����� ����
				
				companyList.add(combean);	
			}
			rs.close(); pstmt.close(); con.close();	
		}catch(Exception e){
			e.printStackTrace();
		}
		return companyList;
	}
	
	/*��û�� ���뺸��*/
	public CompanyBean getCont(int num) {		
		CompanyBean combean = null;
		
		try{
			con = ds.getConnection();
			
			sql = "select * from company where com_num=?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, num);  //����ǥ�� num�� �����Ѵ�. 
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				combean = new CompanyBean();
				
				//��񿡼� �ش� �ʵ带 ������ �� �����Ѵ�. 
				combean.setCom_num(rs.getInt("com_num")); 	
				combean.setCom_date(rs.getString("com_date").substring(0,10)); //��û��¥
				combean.setCom_co(rs.getString("com_co")); 				
				combean.setCom_name(rs.getString("com_name")); 			
				combean.setCom_tel(rs.getString("com_tel")); 			
				combean.setCom_mail(rs.getString("com_mail"));		 	
				combean.setCom_website(rs.getString("com_website")); 	
				combean.setCom_product(rs.getString("com_product")); 	
				combean.setCom_price(rs.getString("com_price")); 		
				combean.setCom_cont(rs.getString("com_cont")); 			
				combean.setCom_file1(rs.getString("com_file1")); 	
			}
			rs.close(); pstmt.close(); con.close();	
		}catch(Exception e){
			e.printStackTrace();
		}
		return combean; //�ڹٺ� ���� ����
	}
	
	/*��û ��� ���� ����*/
	public void companyEdit(CompanyBean combean) {
		try{
			con = ds.getConnection();
			
			sql = "update company set com_co=?, com_name=?, " +
					" com_tel=?, com_mail=?, com_website=?, com_product=?, " +
					" com_price=?, com_cont=?, com_file1=? " +
					" where com_num = ?";
			
			pstmt = con.prepareStatement(sql);
			
			//�� ����ǥ�� ������� �ش��ϴ� �Ӽ��� �����Ѵ�. 
			pstmt.setString(1, combean.getCom_co());
			pstmt.setString(2, combean.getCom_name());
			pstmt.setString(3, combean.getCom_tel());
			pstmt.setString(4, combean.getCom_mail());
			pstmt.setString(5, combean.getCom_website());
			pstmt.setString(6, combean.getCom_product());
			pstmt.setString(7, combean.getCom_price());
			pstmt.setString(8, combean.getCom_cont());
			pstmt.setString(9, combean.getCom_file1());
			
			pstmt.setInt(10, combean.getCom_num());   
			
			pstmt.executeUpdate();
			System.out.println("test =========================");
			
			pstmt.close(); con.close();		
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/*��û ��� ��� ����*/
	public void companyDelete(int com_num) {
		try{
			con = ds.getConnection();
			
			sql = "delete from company where com_num=?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, com_num);
			
			pstmt.executeUpdate();
			
			pstmt.close(); con.close();		
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/*���� ��ǰ �˻� ����Ʈ ����*/
	public List<CompanyBean> getcompanySearchList(int page, int limit, String searchSelect, String searchCom) {
		List<CompanyBean> companyList = new ArrayList<CompanyBean>();
		
		int startrow = (page-1)*10+1;		//������ ���۹�ȣ
		int endrow = page*limit; 			//������ ����ȣ
		
		try{
			
			System.out.println("DAO =========== searchSelect : " + searchSelect);
			System.out.println("DAO =========== searchCom : " + searchCom);
			
			con=ds.getConnection();
			
			if(searchSelect.equals("com_co")){
				System.out.println("============================== ���Ͷ�!");
				sql = "select * from " +
						" (select * from company order by com_num desc) " +
						" where com_num >=? and com_num <=? " +
						" and com_co like ?";
			}else if(searchSelect.equals("com_product")){
				sql = "select * from " +
						" (select * from company order by com_num desc) " +
						" where com_num >=? and com_num <=? " +
						" and com_product like ?";
			}
			/*sql = "select * from " +
					" (select * from company order by com_num desc) " +
					" where com_num >=? and com_num <=? " +
					" and com_product like ?";*/
			
			/*sql ="select * from (select bbs.*, rownum as rnum "
					 + " from (select * from bbs order by bbs_re_ref desc, bbs_re_seq asc) bbs)" 
					 + " where rnum >=? and rnum<=?"; */
			//�ڷ�� �׷��ȣ(bbs_re_ref)�� ������������ ����(�ֱٿ� �ۼ� ���� ����)
			//�亯��(bbs_re_seq)�� �׷��ȣ�� �Ʒ������� ���ĵǰ� �Ѵ�. 
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			pstmt.setString(3, "%" + searchCom + "%");
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				CompanyBean combean = new CompanyBean();
				
				combean.setCom_num(rs.getInt("com_num"));						//�۹�ȣ
				combean.setCom_product(rs.getString("com_product"));			//���� ��ǰ
				combean.setCom_price(rs.getString("com_price"));				//���� ��ǰ ����
				combean.setCom_co(rs.getString("com_co"));						//ȸ�� �̸�
				combean.setCom_name(rs.getString("com_name"));					//����� �̸�
				combean.setCom_date(rs.getString("com_date").substring(0,10));	//��û��
				//0���� 10 �̸� ������ ����� ����
				
				companyList.add(combean);	
			}
			rs.close(); pstmt.close(); con.close();	
		}catch(Exception e){
			e.printStackTrace();
		}
		return companyList;
	}
	
}
