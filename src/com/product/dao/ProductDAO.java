package com.product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.company.dao.CompanyBean;

public class ProductDAO {
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	DataSource ds = null;
	String sql = null;

	public ProductDAO() {
		try{
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/OracleDB");
			con = ds.getConnection();		
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/*�� ���ڵ� ��(��ǰ ��) ���ϱ�*/
	public int getListCount() {
		int count = 0;
		
		try{
			//count(*)�� ��ü ���ڵ� ���� ����� �ִ� sql �Լ��̴�.
			con=ds.getConnection();
			sql = "select count(*) from product";
			
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
	
	/*���� ��ǰ �Խ��� ��Ϻ���*/
	public List<ProductBean> getproductList(int page, int limit){
		//java.util ��Ű���� �ִ� List �÷����� ����Ʈ
		
		List<ProductBean> productList = new ArrayList<ProductBean>();
		
		int startrow = (page-1)*10+1;		//������ ���۹�ȣ
		int endrow = page*limit; 			//������ ����ȣ
		
		try{
			con=ds.getConnection();
			sql = "select * from " +
					" (select * from product order by product_num desc) " +
					" where product_num >=? and product_num <=?";
			
//			sql = "select * from product order by product_num desc";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			
			rs = pstmt.executeQuery(); //select �� ����
			
			while(rs.next()){ //���ڵ尡 �����ϸ� �ݺ� ����
				ProductBean bean = new ProductBean();
				
				//7���� �ʵ带 db�� ������ �� java bean���� ���ڵ带 �����Ͽ���
				bean.setProduct_num(rs.getInt("product_num"));
				bean.setProduct_code(rs.getString("product_code"));
				bean.setProduct_name(rs.getString("product_name"));
				bean.setProduct_price(rs.getInt("product_price"));
				bean.setProduct_number(rs.getInt("product_number"));
				bean.setProduct_company(rs.getString("product_company"));
				bean.setProduct_mainImg(rs.getString("product_mainImg"));
				bean.setProduct_post(rs.getInt("product_post"));
				bean.setProduct_state(rs.getInt("product_state"));
				bean.setProduct_shop(rs.getInt("product_shop"));
				
				bean.setProduct_date(rs.getString("product_date").substring(0,10));
				
				productList.add(bean); //ù��° ���ڵ� �߰�����
			}
			rs.close(); pstmt.close(); con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return productList;
	}
	
	/*���� ��ǰ �����ϱ�*/
	public int insertproduct(ProductBean bean){
		int re = 0;
		
		try{			
			sql = "insert into product" + 
			  			" values(product_seq.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,sysdate)";
			
			pstmt = con.prepareStatement(sql); //sql�� ���� ó��
			
			pstmt.setString(1, bean.getProduct_code());   //ù��° ����ǥ�� ��ǰ �ڵ�
			pstmt.setString(2, bean.getProduct_name());   //�ι�° ����ǥ�� ��ǰ �̸�
			pstmt.setInt(3, bean.getProduct_price());    	  //����° ����ǥ�� ��ǰ ����
			pstmt.setInt(4, bean.getProduct_number());    	  //�׹�° ����ǥ�� ��ǰ ����
			pstmt.setString(5, bean.getProduct_company());  //�ټ���° ����ǥ�� ��ǰ ���� ȸ��
			pstmt.setString(6, bean.getProduct_summary());       //����° ����ǥ�� ��ǰ ��� ����
			pstmt.setString(7, bean.getProduct_description());    //�ϰ���° ����ǥ�� ���� ����
			pstmt.setString(8, bean.getProduct_mainImg());    //������° ����ǥ��  ���� ����
			pstmt.setString(9, bean.getProduct_thum01());    //��ȩ����° ����ǥ�� ���� ����1
			pstmt.setString(10, bean.getProduct_thum02());       //����° ����ǥ�� ���� ����2
			pstmt.setString(11, bean.getProduct_thum03());    //���ѹ�° ����ǥ�� ���� ����3
			pstmt.setString(12, bean.getProduct_video());    //����° ����ǥ�� ���� ������
			pstmt.setString(13, bean.getProduct_opt01());    //����° ����ǥ�� �ɼ�1
			pstmt.setString(14, bean.getProduct_opt01_1());       //����° ����ǥ�� �ɼ�1-1
			pstmt.setString(15, bean.getProduct_opt01_2());    //���ټ�° ����ǥ�� �ɼ�1-2
			pstmt.setString(16, bean.getProduct_opt01_3());    //��������° ����ǥ�� �ɼ�1-3
			pstmt.setString(17, bean.getProduct_opt01_4());    //���ϰ���° ����ǥ�� �ɼ�1-4
			pstmt.setString(18, bean.getProduct_opt01_5());       //��������° ����ǥ�� �ɼ�1-5
			pstmt.setString(19, bean.getProduct_opt02());    //����ȩ��° ����ǥ�� �ɼ�2
			pstmt.setString(20, bean.getProduct_opt02_1());    //��������° ����ǥ�� �ɼ�2-1
			pstmt.setString(21, bean.getProduct_opt02_2());    //�������ѹ�° ����ǥ�� �ɼ�2-2
			pstmt.setString(22, bean.getProduct_opt02_3());    //�������ι�° ����ǥ�� �ɼ�2-3
			pstmt.setString(23, bean.getProduct_opt02_4());    //����������° ����ǥ�� �ɼ�2-4
			pstmt.setString(24, bean.getProduct_opt02_5());    //�������׹�° ����ǥ�� �ɼ�2-5
			pstmt.setString(25, bean.getProduct_startDate());    //�������ټ���° ����ǥ�� ���۳�¥
			pstmt.setString(26, bean.getProduct_endDate());    //����������°�� ����ǥ�� ������¥
			pstmt.setInt(27, bean.getProduct_post());    //�������ϰ���° ����ǥ�� �Խ� ȸ��
			pstmt.setInt(28, bean.getProduct_state());    //������������° ����ǥ�� �Խû���
			pstmt.setInt(29, bean.getProduct_shop());    //��������ȩ��° ����ǥ�� �� ��ġ
			
			re = pstmt.executeUpdate(); //insert���� �����ϰ� �������� re�� ����
	
			//���� ��ü�� �ݴ´�.
			con.close();
			pstmt.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return re;
	}
	
	/*��ǰ ���뺸��*/
	public ProductBean getCont(int num) {		
		ProductBean productbean = null;
		
		try{
			con = ds.getConnection();
			
			sql = "select * from product where product_num=?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, num);  //����ǥ�� num�� �����Ѵ�. 
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				productbean = new ProductBean();				
				
				//��񿡼� �ش� �ʵ带 ������ �� �����Ѵ�. 
				productbean.setProduct_num(rs.getInt("product_num"));
				productbean.setProduct_code(rs.getString("product_code"));
				productbean.setProduct_name(rs.getString("product_name"));
				productbean.setProduct_price(rs.getInt("product_price"));
				productbean.setProduct_number(rs.getInt("product_number"));
				productbean.setProduct_company(rs.getString("product_company"));
				productbean.setProduct_summary(rs.getString("product_summary"));
				productbean.setProduct_description(rs.getString("product_description"));
				productbean.setProduct_mainImg(rs.getString("product_mainImg"));
				productbean.setProduct_thum01(rs.getString("product_thum01"));
				productbean.setProduct_thum02(rs.getString("product_thum02"));
				productbean.setProduct_thum03(rs.getString("product_thum03"));
				productbean.setProduct_video(rs.getString("product_video"));
				productbean.setProduct_opt01(rs.getString("product_opt01"));
				productbean.setProduct_opt01_1(rs.getString("product_opt01_1"));
				productbean.setProduct_opt01_2(rs.getString("product_opt01_2"));
				productbean.setProduct_opt01_3(rs.getString("product_opt01_3"));
				productbean.setProduct_opt01_4(rs.getString("product_opt01_4"));
				productbean.setProduct_opt01_5(rs.getString("product_opt01_5"));
				productbean.setProduct_opt02(rs.getString("product_opt02"));
				productbean.setProduct_opt02_1(rs.getString("product_opt02_1"));
				productbean.setProduct_opt02_2(rs.getString("product_opt02_2"));
				productbean.setProduct_opt02_3(rs.getString("product_opt02_3"));
				productbean.setProduct_opt02_4(rs.getString("product_opt02_4"));
				productbean.setProduct_opt02_5(rs.getString("product_opt02_5"));
				productbean.setProduct_startDate(rs.getString("product_startDate"));
				productbean.setProduct_endDate(rs.getString("product_endDate"));
				productbean.setProduct_post(rs.getInt("product_post"));
				productbean.setProduct_state(rs.getInt("product_state"));
				productbean.setProduct_shop(rs.getInt("product_shop"));			
				productbean.setProduct_date(rs.getString("product_date"));
			}
			rs.close(); pstmt.close(); con.close();	
		}catch(Exception e){
			e.printStackTrace();
		}
		return productbean; //�ڹٺ� ���� ����
	}
	
	/*��ǰ ���� ����*/
	public void productEdit(ProductBean bean) {
		try{
			con = ds.getConnection();
			
			sql = "update product set product_code=?, product_name=?, product_price=?, " +
					" product_number=?, product_company=?, product_summary=?, product_description=?, " +
					" product_mainImg=?, product_thum01=?, product_thum02=?, product_thum03=?, product_video=?, " +
					" product_opt01=?, product_opt01_1=?, product_opt01_2=?, product_opt01_3=?, product_opt01_4=?, product_opt01_5=?, " +
					" product_opt02=?, product_opt02_1=?, product_opt02_2=?, product_opt02_3=?, product_opt02_4=?, product_opt02_5=?, " +
					" product_startDate=?, product_endDate=?, " +
					" product_post=?, product_state=?, product_shop=? " +
					" where product_num = ?";
			
			pstmt = con.prepareStatement(sql);
			
			//�� ����ǥ�� ������� �ش��ϴ� �Ӽ��� �����Ѵ�. 
			pstmt.setString(1, bean.getProduct_code());  
			pstmt.setString(2, bean.getProduct_name());   
			pstmt.setInt(3, bean.getProduct_price());    
			pstmt.setInt(4, bean.getProduct_number());   
			pstmt.setString(5, bean.getProduct_company());  
			pstmt.setString(6, bean.getProduct_summary());  
			pstmt.setString(7, bean.getProduct_description());    
			pstmt.setString(8, bean.getProduct_mainImg());    
			pstmt.setString(9, bean.getProduct_thum01());    
			pstmt.setString(10, bean.getProduct_thum02());      
			pstmt.setString(11, bean.getProduct_thum03());    
			pstmt.setString(12, bean.getProduct_video());    
			pstmt.setString(13, bean.getProduct_opt01());    
			pstmt.setString(14, bean.getProduct_opt01_1());  
			pstmt.setString(15, bean.getProduct_opt01_2());  
			pstmt.setString(16, bean.getProduct_opt01_3());  
			pstmt.setString(17, bean.getProduct_opt01_4());  
			pstmt.setString(18, bean.getProduct_opt01_5());  
			pstmt.setString(19, bean.getProduct_opt02());    
			pstmt.setString(20, bean.getProduct_opt02_1());  
			pstmt.setString(21, bean.getProduct_opt02_2());  
			pstmt.setString(22, bean.getProduct_opt02_3());  
			pstmt.setString(23, bean.getProduct_opt02_4());  
			pstmt.setString(24, bean.getProduct_opt02_5());  
			pstmt.setString(25, bean.getProduct_startDate());   
			pstmt.setString(26, bean.getProduct_endDate());    
			pstmt.setInt(27, bean.getProduct_post());    
			pstmt.setInt(28, bean.getProduct_state());  
			pstmt.setInt(29, bean.getProduct_shop());   

			pstmt.setInt(30, bean.getProduct_num());   
			
			pstmt.executeUpdate();
			
			pstmt.close(); con.close();		
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/*��ǰ �󼼺��� for client*/
	public ProductBean getClient(int product_shop) {		
		ProductBean productbean = null;
		
		try{
			con = ds.getConnection();
			
			if(product_shop == 1){
				System.out.println("============================== ���Ͷ�!");
				sql = "select * from product " +
						" where product_state=0 " +
						" and product_shop=1 ";
			}else if(product_shop == 2){
				sql = "select * from product " +
						" where product_state=0 " +
						" and product_shop=2 ";
			}else if(product_shop == 3){
				sql = "select * from product " +
						" where product_state=0 " +
						" and product_shop=3 ";
			}
			
			pstmt = con.prepareStatement(sql);
						
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				productbean = new ProductBean();
				
				//��񿡼� �ش� �ʵ带 ������ �� �����Ѵ�. 
				productbean.setProduct_num(rs.getInt("product_num"));
				productbean.setProduct_code(rs.getString("product_code"));
				productbean.setProduct_name(rs.getString("product_name"));
				productbean.setProduct_price(rs.getInt("product_price"));
				productbean.setProduct_number(rs.getInt("product_number"));
				productbean.setProduct_company(rs.getString("product_company"));
				productbean.setProduct_summary(rs.getString("product_summary"));
				productbean.setProduct_description(rs.getString("product_description"));
				productbean.setProduct_mainImg(rs.getString("product_mainImg"));
				productbean.setProduct_thum01(rs.getString("product_thum01"));
				productbean.setProduct_thum02(rs.getString("product_thum02"));
				productbean.setProduct_thum03(rs.getString("product_thum03"));
				productbean.setProduct_video(rs.getString("product_video"));
				productbean.setProduct_opt01(rs.getString("product_opt01"));
				productbean.setProduct_opt01_1(rs.getString("product_opt01_1"));
				productbean.setProduct_opt01_2(rs.getString("product_opt01_2"));
				productbean.setProduct_opt01_3(rs.getString("product_opt01_3"));
				productbean.setProduct_opt01_4(rs.getString("product_opt01_4"));
				productbean.setProduct_opt01_5(rs.getString("product_opt01_5"));
				productbean.setProduct_opt02(rs.getString("product_opt02"));
				productbean.setProduct_opt02_1(rs.getString("product_opt02_1"));
				productbean.setProduct_opt02_2(rs.getString("product_opt02_2"));
				productbean.setProduct_opt02_3(rs.getString("product_opt02_3"));
				productbean.setProduct_opt02_4(rs.getString("product_opt02_4"));
				productbean.setProduct_opt02_5(rs.getString("product_opt02_5"));
				productbean.setProduct_startDate(rs.getString("product_startDate"));
				productbean.setProduct_endDate(rs.getString("product_endDate"));
				productbean.setProduct_post(rs.getInt("product_post"));
				productbean.setProduct_state(rs.getInt("product_state"));
				productbean.setProduct_shop(rs.getInt("product_shop"));			
				productbean.setProduct_date(rs.getString("product_date"));
			}
			rs.close(); pstmt.close(); con.close();	
		}catch(Exception e){
			e.printStackTrace();
		}
		return productbean; //�ڹٺ� ���� ����
	}
	
	/*��ǰ ��� ����*/
	public void productDelete(int product_num) {
		try{
			con = ds.getConnection();
			
			sql = "delete from product where product_num=?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, product_num);
			
			pstmt.executeUpdate();
			
			pstmt.close(); con.close();		
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/*��ǰ �˻� ���*/
	public List<ProductBean> getproductSearchList(int page, int limit, String searchSelect, String searchCom) {
		List<ProductBean> productList = new ArrayList<ProductBean>();
		
		int startrow = (page-1)*10+1;		//������ ���۹�ȣ
		int endrow = page*limit; 			//������ ����ȣ
		
		try{
			
			System.out.println("DAO =========== searchSelect : " + searchSelect);
			System.out.println("DAO =========== searchCom : " + searchCom);
			
			con=ds.getConnection();
			
			if(searchSelect.equals("product_name")){
				System.out.println("============================== ���Ͷ�!");
				sql = "select * from " +
						" (select * from product order by product_num desc) " +
						" where product_num >=? and product_num <=? " +
						" and product_name like ?";
			}else if(searchSelect.equals("product_code")){
				sql = "select * from " +
						" (select * from product order by product_num desc) " +
						" where product_num >=? and product_num <=? " +
						" and product_code like ?";
			}else if(searchSelect.equals("product_company")){
				sql = "select * from " +
						" (select * from product order by product_num desc) " +
						" where product_num >=? and product_num <=? " +
						" and product_company like ?";
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
				ProductBean bean = new ProductBean();
				
				bean.setProduct_num(rs.getInt("product_num"));
				bean.setProduct_code(rs.getString("product_code"));
				bean.setProduct_name(rs.getString("product_name"));
				bean.setProduct_price(rs.getInt("product_price"));
				bean.setProduct_number(rs.getInt("product_number"));
				bean.setProduct_company(rs.getString("product_company"));
				bean.setProduct_mainImg(rs.getString("product_mainImg"));
				bean.setProduct_post(rs.getInt("product_post"));
				bean.setProduct_state(rs.getInt("product_state"));
				bean.setProduct_shop(rs.getInt("product_shop"));
				
				bean.setProduct_date(rs.getString("product_date").substring(0,10));
				
				productList.add(bean);	
			}
			rs.close(); pstmt.close(); con.close();	
		}catch(Exception e){
			e.printStackTrace();
		}
		return productList;
	}
	
	/*��ǰ ���뺸��*/
	public ProductBean getClientForShop(String shop) {		
		ProductBean productbean = null;
		
		try{
			con = ds.getConnection();
			
			if(shop.equals("1")){
				System.out.println("============================== ���Ͷ�!");
				sql = "select * from product " +
						" where product_state=0 " +
						" and product_shop=1";
			}else if(shop.equals("2")){
				sql = "select * from product " +
						" where product_state=0 " +
						" and product_shop=2";
			}else if(shop.equals("3")){
				sql = "select * from product " +
						" where product_state=0 " +
						" and product_shop=3";
			}
			
			/*sql = "select * from product " +
					" where product_state=0 " +
					" and product_shop=1";*/
			
			pstmt = con.prepareStatement(sql);
						
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				productbean = new ProductBean();				
				
				//��񿡼� �ش� �ʵ带 ������ �� �����Ѵ�. 
				productbean.setProduct_num(rs.getInt("product_num"));
				productbean.setProduct_code(rs.getString("product_code"));
				productbean.setProduct_name(rs.getString("product_name"));
				productbean.setProduct_price(rs.getInt("product_price"));
				productbean.setProduct_number(rs.getInt("product_number"));
				productbean.setProduct_company(rs.getString("product_company"));
				productbean.setProduct_summary(rs.getString("product_summary"));
				productbean.setProduct_description(rs.getString("product_description"));
				productbean.setProduct_mainImg(rs.getString("product_mainImg"));
				productbean.setProduct_thum01(rs.getString("product_thum01"));
				productbean.setProduct_thum02(rs.getString("product_thum02"));
				productbean.setProduct_thum03(rs.getString("product_thum03"));
				productbean.setProduct_video(rs.getString("product_video"));
				productbean.setProduct_opt01(rs.getString("product_opt01"));
				productbean.setProduct_opt01_1(rs.getString("product_opt01_1"));
				productbean.setProduct_opt01_2(rs.getString("product_opt01_2"));
				productbean.setProduct_opt01_3(rs.getString("product_opt01_3"));
				productbean.setProduct_opt01_4(rs.getString("product_opt01_4"));
				productbean.setProduct_opt01_5(rs.getString("product_opt01_5"));
				productbean.setProduct_opt02(rs.getString("product_opt02"));
				productbean.setProduct_opt02_1(rs.getString("product_opt02_1"));
				productbean.setProduct_opt02_2(rs.getString("product_opt02_2"));
				productbean.setProduct_opt02_3(rs.getString("product_opt02_3"));
				productbean.setProduct_opt02_4(rs.getString("product_opt02_4"));
				productbean.setProduct_opt02_5(rs.getString("product_opt02_5"));
				productbean.setProduct_startDate(rs.getString("product_startDate"));
				productbean.setProduct_endDate(rs.getString("product_endDate"));
				productbean.setProduct_post(rs.getInt("product_post"));
				productbean.setProduct_state(rs.getInt("product_state"));
				productbean.setProduct_shop(rs.getInt("product_shop"));			
				productbean.setProduct_date(rs.getString("product_date"));
			}
			rs.close(); pstmt.close(); con.close();	
		}catch(Exception e){
			e.printStackTrace();
		}
		return productbean; //�ڹٺ� ���� ����
	}
}
