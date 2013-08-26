package com.guide.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.company.dao.CompanyBean;

public class GuideDAO {
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	DataSource ds = null;
	String sql = null;

	public GuideDAO() {
		try{
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/OracleDB");
			con = ds.getConnection();		
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/*�� ���ڵ� ��(���̵� ��) ���ϱ�*/
	public int getListCount() {
		int count = 0;
		
		try{
			//count(*)�� ��ü ���ڵ� ���� ����� �ִ� sql �Լ��̴�.
			con=ds.getConnection();
			sql = "select count(*) from guide";
			
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
	
	/*���� ���̵� �Խ��� ��Ϻ���*/
	public List<GuideBean> getGuideList(){
		//java.util ��Ű���� �ִ� List �÷����� ����Ʈ
		
		List<GuideBean> guideList = new ArrayList<GuideBean>();
		
		try{
			con=ds.getConnection();
			
			sql = "select * from guide order by guide_num desc";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery(); //select �� ����
			
			while(rs.next()){ //���ڵ尡 �����ϸ� �ݺ� ����
				GuideBean bean = new GuideBean();
				
				//7���� �ʵ带 db�� ������ �� java bean���� ���ڵ带 �����Ͽ���
				bean.setGuide_num(rs.getInt("guide_num"));
				bean.setGuide_product(rs.getString("guide_product"));
				bean.setGuide_s_date(rs.getString("guide_s_date"));
				bean.setGuide_e_date(rs.getString("guide_e_date"));
				bean.setGuide_post(rs.getInt("guide_post"));
				bean.setGuide_state(rs.getInt("guide_state"));
				bean.setGuide_reg_date(rs.getString("guide_reg_date").substring(0,10));
				
				guideList.add(bean); //ù��° ���ڵ� �߰�����
			}
			rs.close(); pstmt.close(); con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return guideList;
	}
	
	/*���� ���̵� �����ϱ�*/
	public int insertGuide(GuideBean bean){
		int re = 0;
		
		try{			
			sql = "insert into guide" + 
			  			" values(guide_seq.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,sysdate)";
			
			pstmt = con.prepareStatement(sql); //sql�� ���� ó��
			
			pstmt.setString(1, bean.getGuide_s_date());   //ù��° ����ǥ�� ���� ��¥
			pstmt.setString(2, bean.getGuide_e_date());   //�ι�° ����ǥ�� ���� ��¥
			pstmt.setInt(3, bean.getGuide_post());    	  //����° ����ǥ�� �Խ� ȸ��
			pstmt.setInt(4, bean.getGuide_state());    	  //�׹�° ����ǥ�� �Խ� ����
			pstmt.setString(5, bean.getGuide_product());  //�ټ���° ����ǥ�� ��ǰ��
			pstmt.setString(6, bean.getGuide_q1());       //����° ����ǥ�� ����1
			pstmt.setString(7, bean.getGuide_q1_a1());    //�ϰ���° ����ǥ�� ����1-1
			pstmt.setString(8, bean.getGuide_q1_a2());    //������° ����ǥ�� ����1-2
			pstmt.setString(9, bean.getGuide_q1_a3());    //��ȩ����° ����ǥ�� ����1-3
			pstmt.setString(10, bean.getGuide_q2());       //����° ����ǥ�� ����2
			pstmt.setString(11, bean.getGuide_q2_a1());    //���ѹ�° ����ǥ�� ����2-1
			pstmt.setString(12, bean.getGuide_q2_a2());    //����° ����ǥ�� ����2-2
			pstmt.setString(13, bean.getGuide_q2_a3());    //����° ����ǥ�� ����2-3
			pstmt.setString(14, bean.getGuide_q3());       //����° ����ǥ�� ����3
			pstmt.setString(15, bean.getGuide_q3_a1());    //���ټ�° ����ǥ�� ����3-1
			pstmt.setString(16, bean.getGuide_q3_a2());    //��������° ����ǥ�� ����3-2
			pstmt.setString(17, bean.getGuide_q3_a3());    //���ϰ���° ����ǥ�� ����3-3
			pstmt.setString(18, bean.getGuide_q4());       //��������° ����ǥ�� ����4
			pstmt.setString(19, bean.getGuide_q4_a1());    //����ȩ��° ����ǥ�� ����4-1
			pstmt.setString(20, bean.getGuide_q4_a2());    //��������° ����ǥ�� ����4-2
			pstmt.setString(21, bean.getGuide_q4_a3());    //�������ѹ�° ����ǥ�� ����4-3
			
			re = pstmt.executeUpdate(); //insert���� �����ϰ� �������� re�� ����
	
			//���� ��ü�� �ݴ´�.
			con.close();
			pstmt.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return re;
	}
	
	/*���̵� ���뺸��*/
	public GuideBean getCont(int num) {		
		GuideBean guidebean = null;
		
		try{
			con = ds.getConnection();
			
			sql = "select * from guide where guide_num=?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, num);  //����ǥ�� num�� �����Ѵ�. 
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				guidebean = new GuideBean();
				
				//��񿡼� �ش� �ʵ带 ������ �� �����Ѵ�. 
				guidebean.setGuide_num(rs.getInt("guide_num")); 	
				guidebean.setGuide_product(rs.getString("guide_product")); 
				guidebean.setGuide_s_date(rs.getString("guide_s_date")); 
				guidebean.setGuide_e_date(rs.getString("guide_e_date")); 
				guidebean.setGuide_post(rs.getInt("guide_post")); 
				guidebean.setGuide_state(rs.getInt("guide_state")); 
				guidebean.setGuide_q1(rs.getString("guide_q1")); 
				guidebean.setGuide_q1_a1(rs.getString("guide_q1_a1")); 
				guidebean.setGuide_q1_a2(rs.getString("guide_q1_a2")); 
				guidebean.setGuide_q1_a3(rs.getString("guide_q1_a3")); 
				guidebean.setGuide_q2(rs.getString("guide_q2")); 
				guidebean.setGuide_q2_a1(rs.getString("guide_q2_a1")); 
				guidebean.setGuide_q2_a2(rs.getString("guide_q2_a2")); 
				guidebean.setGuide_q2_a3(rs.getString("guide_q2_a3")); 
				guidebean.setGuide_q3(rs.getString("guide_q3")); 
				guidebean.setGuide_q3_a1(rs.getString("guide_q3_a1")); 
				guidebean.setGuide_q3_a2(rs.getString("guide_q3_a2")); 
				guidebean.setGuide_q3_a3(rs.getString("guide_q3_a3")); 
				guidebean.setGuide_q4(rs.getString("guide_q4")); 
				guidebean.setGuide_q4_a1(rs.getString("guide_q4_a1")); 
				guidebean.setGuide_q4_a2(rs.getString("guide_q4_a2"));
				guidebean.setGuide_q4_a3(rs.getString("guide_q4_a3"));
				guidebean.setGuide_reg_date(rs.getString("guide_reg_date"));
			}
			rs.close(); pstmt.close(); con.close();	
		}catch(Exception e){
			e.printStackTrace();
		}
		return guidebean; //�ڹٺ� ���� ����
	}
	
	/*���̵� ���� ����*/
	public void guideEdit(GuideBean bean) {
		try{
			con = ds.getConnection();
			
			sql = "update guide set guide_s_date=?, guide_e_date=?, " +
					" guide_post=?, guide_state=?, guide_product=?,  " +
					" guide_q1=?, guide_q1_a1=?, guide_q1_a2=?, guide_q1_a3=?, " +
					" guide_q2=?, guide_q2_a1=?, guide_q2_a2=?, guide_q2_a3=?, " +
					" guide_q3=?, guide_q3_a1=?, guide_q3_a2=?, guide_q3_a3=?, " +
					" guide_q4=?, guide_q4_a1=?, guide_q4_a2=?, guide_q4_a3=? " +
					" where guide_num = ?";
			
			pstmt = con.prepareStatement(sql);
			
			//�� ����ǥ�� ������� �ش��ϴ� �Ӽ��� �����Ѵ�. 
			pstmt.setString(1, bean.getGuide_s_date());
			pstmt.setString(2, bean.getGuide_e_date());
			pstmt.setInt(3, bean.getGuide_post());
			pstmt.setInt(4, bean.getGuide_state());
			
			pstmt.setString(5, bean.getGuide_product());
			pstmt.setString(6, bean.getGuide_q1());
			pstmt.setString(7, bean.getGuide_q1_a1());
			pstmt.setString(8, bean.getGuide_q1_a2());
			pstmt.setString(9, bean.getGuide_q1_a3());
			pstmt.setString(10, bean.getGuide_q2());
			pstmt.setString(11, bean.getGuide_q2_a1());
			pstmt.setString(12, bean.getGuide_q2_a2());
			pstmt.setString(13, bean.getGuide_q2_a3());
			pstmt.setString(14, bean.getGuide_q3());
			pstmt.setString(15, bean.getGuide_q3_a1());
			pstmt.setString(16, bean.getGuide_q3_a2());
			pstmt.setString(17, bean.getGuide_q3_a3());
			pstmt.setString(18, bean.getGuide_q4());
			pstmt.setString(19, bean.getGuide_q4_a1());
			pstmt.setString(20, bean.getGuide_q4_a2());
			pstmt.setString(21, bean.getGuide_q4_a3());

			pstmt.setInt(22, bean.getGuide_num());   
			
			pstmt.executeUpdate();
			
			pstmt.close(); con.close();		
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public GuideBean getClient() {		
		GuideBean guidebean = null;
		
		try{
			con = ds.getConnection();
			
			sql = "select * from guide where guide_state=0";
			
			pstmt = con.prepareStatement(sql);
						
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				guidebean = new GuideBean();
				
				//��񿡼� �ش� �ʵ带 ������ �� �����Ѵ�. 
				/*guidebean.setGuide_num(rs.getInt("guide_num")); */	
				guidebean.setGuide_product(rs.getString("guide_product")); 
				guidebean.setGuide_s_date(rs.getString("guide_s_date")); 
				guidebean.setGuide_e_date(rs.getString("guide_e_date")); 
				/*guidebean.setGuide_state(rs.getInt("guide_state")); */
				guidebean.setGuide_q1(rs.getString("guide_q1")); 
				guidebean.setGuide_q1_a1(rs.getString("guide_q1_a1")); 
				guidebean.setGuide_q1_a2(rs.getString("guide_q1_a2")); 
				guidebean.setGuide_q1_a3(rs.getString("guide_q1_a3")); 
				guidebean.setGuide_q2(rs.getString("guide_q2")); 
				guidebean.setGuide_q2_a1(rs.getString("guide_q2_a1")); 
				guidebean.setGuide_q2_a2(rs.getString("guide_q2_a2")); 
				guidebean.setGuide_q2_a3(rs.getString("guide_q2_a3")); 
				guidebean.setGuide_q3(rs.getString("guide_q3")); 
				guidebean.setGuide_q3_a1(rs.getString("guide_q3_a1")); 
				guidebean.setGuide_q3_a2(rs.getString("guide_q3_a2")); 
				guidebean.setGuide_q3_a3(rs.getString("guide_q3_a3")); 
				guidebean.setGuide_q4(rs.getString("guide_q4")); 
				guidebean.setGuide_q4_a1(rs.getString("guide_q4_a1")); 
				guidebean.setGuide_q4_a2(rs.getString("guide_q4_a2"));
				guidebean.setGuide_q4_a3(rs.getString("guide_q4_a3"));
				/*guidebean.setGuide_reg_date(rs.getString("guide_reg_date"));*/
			}
			rs.close(); pstmt.close(); con.close();	
		}catch(Exception e){
			e.printStackTrace();
		}
		return guidebean; //�ڹٺ� ���� ����
	}
	
	/*���̵� ��� ����*/
	public void guideDelete(int guide_num) {
		try{
			con = ds.getConnection();
			
			sql = "delete from guide where guide_num=?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, guide_num);
			
			pstmt.executeUpdate();
			
			pstmt.close(); con.close();		
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
