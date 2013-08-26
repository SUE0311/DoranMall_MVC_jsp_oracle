package com.wishlist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class WishlistDAO {
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	DataSource ds = null;
	String sql = null;
	
	//��� ������ ���� �������� ����
	public WishlistDAO(){
		try{
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/OracleDB");	
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/*�� ���ڵ� �� ���ϱ�*/
	public int getListCount() {
		int count = 0;
		
		try{
			//count(*)�� ��ü ���ڵ� ���� ����� �ִ� sql �Լ��̴�.
			con=ds.getConnection();
			sql = "select count(*) from wishlist";
			
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
	
	/*�ڷ�� ��Ϻ���*/
	public List<WishlistBean> getwishlistList(int page, int limit) {
		//List�� ���� Ŭ������ ArrayList�� �̿��Ͽ� ��ü�� �����Ѵ�. 
		List<WishlistBean> wishlistList = new ArrayList<WishlistBean>();
		
		int startrow = (page-1)*10+1;		//������ ���۹�ȣ
		int endrow = page*limit; 			//������ ����ȣ
		
		try{
			con=ds.getConnection();
			
			sql ="select * from (select wishlist.*, rownum as rnum "
					 + " from (select * from wishlist order by wishlist_re_ref desc, wishlist_re_seq asc) wishlist)" 
					 + " where rnum >=? and rnum<=?"; 
			//�ڷ�� �׷��ȣ(wishlist_re_ref)�� ������������ ����(�ֱٿ� �ۼ� ���� ����)
			//�亯��(wishlist_re_seq)�� �׷��ȣ�� �Ʒ������� ���ĵǰ� �Ѵ�. 
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				WishlistBean wishlistbean = new WishlistBean();
				
				wishlistbean.setWishlist_num(rs.getInt("wishlist_num"));				//�۹�ȣ
				wishlistbean.setWishlist_id(rs.getString("wishlist_id"));				//�۾���(���̵�)
				wishlistbean.setWishlist_title(rs.getString("wishlist_title"));			//������
				wishlistbean.setWishlist_content(rs.getString("wishlist_content"));		//�۳���
				wishlistbean.setWishlist_file(rs.getString("wishlist_file"));			//÷������
				
				wishlistbean.setWishlist_re_ref(rs.getInt("wishlist_re_ref"));			//�� �׷� ��ȣ
				wishlistbean.setWishlist_re_lev(rs.getInt("wishlist_re_lev"));		
				wishlistbean.setWishlist_re_seq(rs.getInt("wishlist_re_seq"));		
				wishlistbean.setWishlist_readcont(rs.getInt("wishlist_readcont"));		//��ȸ��
				
				wishlistbean.setWishlist_good(rs.getInt("wishlist_good"));		//��õ��
				
				wishlistbean.setWishlist_date(rs.getString("wishlist_date").substring(0,10));	//�����
				
				wishlistList.add(wishlistbean);	
			}
			rs.close(); pstmt.close(); con.close();	
		}catch(Exception e){
			e.printStackTrace();
		}
		return wishlistList;
	}

	/*�ڷ�� �� ����*/
	public void insertwishlist(WishlistBean wishlistbean) {
		try{
			//max() �Լ��� �ִ밪�� ���ϴ� �Լ��̴�.
			con=ds.getConnection();
			sql = "select max(wishlist_num) from wishlist";
			
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			int num = 0;
			
			if(rs.next()){
				num = rs.getInt(1)+1; //�ִ� �� ��ȣ +1
			}else{
				num=1;
			}
			//wishlist ���̺� ��ü �Ӽ��� ������� ���� ����ǥ�� �����Ѵ�.			
			sql = "insert into wishlist values(?,?,?,?,?,?,?,?,?,?,?,sysdate)";
			
			pstmt = con.prepareStatement(sql);
			
			//�ڹٺ��� ���ڵ�(m)�� �ʵ� ������ �� ����ǥ�� �����Ѵ�
			pstmt.setInt(1, num);  	//��ȣ
			pstmt.setString(2, wishlistbean.getWishlist_id()); 	    //���̵�
			pstmt.setString(3, wishlistbean.getWishlist_pass());	//���
			pstmt.setString(4, wishlistbean.getWishlist_title());	//����
			pstmt.setString(5, wishlistbean.getWishlist_content()); //����
			pstmt.setString(6, wishlistbean.getWishlist_file()); //÷������
			pstmt.setInt(7, num); //�׷��ȣ
			pstmt.setInt(8, 0);   //���� �Ӽ��� 0���� �ʱ�ȭ
			pstmt.setInt(9, 0);
			pstmt.setInt(10, 0);
			pstmt.setInt(11, 0);
			
			pstmt.executeUpdate();
			
			rs.close(); pstmt.close(); con.close();	
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/*�ڷ�� ��ȸ�� ����*/
	public void wishlistHit(int num) {
		try{
			con = ds.getConnection();
			
			sql = "update wishlist set wishlist_readcont = wishlist_readcont+1 " +
					" where wishlist_num = ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, num);  //����ǥ�� num�� �����Ѵ�. 
			
			pstmt.executeUpdate();
			
			pstmt.close(); con.close();	
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	/*�Խù� ���� ��������*/
	public WishlistBean getCont(int num) {
		// TODO Auto-generated method stub
		
		WishlistBean wishlist = null;
		
		try{
			con = ds.getConnection();
			
			sql = "select * from wishlist where wishlist_num=?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, num);  //����ǥ�� num�� �����Ѵ�. 
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				wishlist = new WishlistBean();
				
				//��񿡼� �ش� �ʵ带 ������ �� �����Ѵ�. 
				wishlist.setWishlist_num(rs.getInt("wishlist_num")); 	//����
				wishlist.setWishlist_id(rs.getString("wishlist_id")); 	//���̵�
				wishlist.setWishlist_pass(rs.getString("wishlist_pass")); 	//��й�ȣ
				wishlist.setWishlist_title(rs.getString("wishlist_title")); 	//����
				wishlist.setWishlist_content(rs.getString("wishlist_content")); 	//����
				wishlist.setWishlist_file(rs.getString("wishlist_file")); 	//÷������
				wishlist.setWishlist_re_ref(rs.getInt("wishlist_re_ref")); 	//�� �׷� ��ȣ
				wishlist.setWishlist_re_lev(rs.getInt("wishlist_re_lev")); 	//�亯 �� ȭ�� ��ġ ��ȣ
				wishlist.setWishlist_re_seq(rs.getInt("wishlist_re_seq")); 	//�亯 �� ���� ����
				wishlist.setWishlist_good(rs.getInt("wishlist_good")); 		//��õ��
				wishlist.setWishlist_readcont(rs.getInt("wishlist_readcont")); 	//��ȸ��
				wishlist.setWishlist_date
					(rs.getString("wishlist_date").substring(0,10)); 	//��ϳ�¥
			}
			rs.close(); pstmt.close(); con.close();	
		}catch(Exception e){
			e.printStackTrace();
		}
		return wishlist; //�ڹٺ� ���� ����
	}

	/*�ڷ�� �� ����*/
	public void wishlistEdit(WishlistBean wishlistbean) {
		try{
			con = ds.getConnection();
			
			sql = "update wishlist set wishlist_id=?, wishlist_title=?, " +
					" wishlist_content=?, wishlist_file=? " +
					" where wishlist_num = ?";
			
			pstmt = con.prepareStatement(sql);
			
			//�� ����ǥ�� ������� �ش��ϴ� �Ӽ��� �����Ѵ�. 
			pstmt.setString(1, wishlistbean.getWishlist_id());
			pstmt.setString(2, wishlistbean.getWishlist_title());
			pstmt.setString(3, wishlistbean.getWishlist_content());
			pstmt.setString(4, wishlistbean.getWishlist_file());
			pstmt.setInt(5, wishlistbean.getWishlist_num());   
			
			pstmt.executeUpdate();
			
			pstmt.close(); con.close();		
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/*�ڷ�� �Խù� ����*/
	public void wishlistDelete(int wishlist_num) {
		try{
			con = ds.getConnection();
			
			sql = "delete from wishlist where wishlist_num=?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, wishlist_num);
			
			pstmt.executeUpdate();
			
			pstmt.close(); con.close();		
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/*�ڷ�� �亯 �� ����*/
	public void wishlistReply(WishlistBean wishlistbean) {
		// TODO Auto-generated method stub
		int num = 0;
		int re_ref = wishlistbean.getWishlist_re_ref();
		int re_lev = wishlistbean.getWishlist_re_lev();
		int re_seq = wishlistbean.getWishlist_re_seq();
		
//		System.out.println(re_ref);
//		System.out.println(re_lev);
//		System.out.println(re_lev);
		
		try{
			con = ds.getConnection();
			
			sql = "select max(wishlist_num) from wishlist";
			
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				num = rs.getInt(1)+1; //�ֱ� �۹�ȣ�� +1
			}else{
				num = 1; //������ 1�� �ʱ�ȭ
			}
			
			sql = "update wishlist set wishlist_re_seq=wishlist_re_seq+1 " +
					" where wishlist_re_ref=? and wishlist_re_seq > ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, re_ref); //�� �׷��ȣ ����
			pstmt.setInt(2, re_seq); //�亯�� ������ ����
			
			pstmt.executeUpdate();
			
			re_seq = re_seq+1; //�亯�� ������ 1����
			re_lev = re_lev+1; //�亯�� ��ġ��ȣ 1����
		
			System.out.println(num);
			System.out.println(re_seq);
			System.out.println(re_lev);
			
			sql = "insert into wishlist values(?,?,?,?,?,?,?,?,?,?,?,sysdate)";
			
			pstmt=con.prepareStatement(sql);
			
			pstmt.setInt(1, num);	//�۹�ȣ
			pstmt.setString(2, wishlistbean.getWishlist_id());//�۾���
			pstmt.setString(3, wishlistbean.getWishlist_pass());//���
			
//			System.out.println("========== ��й�ȣ �ְ�");
			
			pstmt.setString(4, wishlistbean.getWishlist_title());//����
			pstmt.setString(5, wishlistbean.getWishlist_content());//����
			pstmt.setString(6, "");//�亯���� ÷������ ����
			
			pstmt.setInt(7, re_ref);	//�� �׷��ȣ
			pstmt.setInt(8, re_lev);	//�亯�� ��ġ��ȣ
			pstmt.setInt(9, re_seq);	//�亯�� ����
			pstmt.setInt(10, 0);		//��õ��
			pstmt.setInt(11, 0);		//��ȸ��
			
			
			pstmt.executeUpdate();
			
			rs.close(); pstmt.close(); con.close();	
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/*��õ�� ����(Ajax)*/
	public int addWishlistGood(int num){
		int re = 0;

		System.out.println("num ==== " + num);
		
		try{
			con = ds.getConnection();
			
			sql = "update wishlist set wishlist_good = wishlist_good+1 " +
					" where wishlist_num = ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, num);  //����ǥ�� num�� �����Ѵ�. 
			
			re = pstmt.executeUpdate();
			
			System.out.println("re ==== " + re);
			
			pstmt.close(); con.close();	
		}catch(Exception e){
			e.printStackTrace();
		}
		return re; //�ڹٺ� ���� ����
	}

	/*��� ��û �˻� ����Ʈ*/
	public List<WishlistBean> getwishlistSearchList(int page, int limit, String searchWish) {
		
		List<WishlistBean> wishlistList = new ArrayList<WishlistBean>();
		
		int startrow = (page-1)*10+1;		//������ ���۹�ȣ
		int endrow = page*limit; 			//������ ����ȣ
		
		System.out.println("=========== " + searchWish);
		
		try{
			con=ds.getConnection();
			
			sql ="select * from (select wishlist.*, rownum as rnum "
					 + " from (select * from wishlist order by wishlist_re_ref desc, wishlist_re_seq asc) wishlist)" 
					 + " where rnum >=? and rnum<=? " 
					 + " and wishlist_title like ?"; 
			//�ڷ�� �׷��ȣ(wishlist_re_ref)�� ������������ ����(�ֱٿ� �ۼ� ���� ����)
			//�亯��(wishlist_re_seq)�� �׷��ȣ�� �Ʒ������� ���ĵǰ� �Ѵ�. 
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			pstmt.setString(3, "%" + searchWish + "%");
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				WishlistBean wishlistbean = new WishlistBean();
				
				wishlistbean.setWishlist_num(rs.getInt("wishlist_num"));				//�۹�ȣ
				wishlistbean.setWishlist_id(rs.getString("wishlist_id"));				//�۾���(���̵�)
				wishlistbean.setWishlist_title(rs.getString("wishlist_title"));			//������
				wishlistbean.setWishlist_content(rs.getString("wishlist_content"));		//�۳���
				wishlistbean.setWishlist_file(rs.getString("wishlist_file"));			//÷������
				
				wishlistbean.setWishlist_re_ref(rs.getInt("wishlist_re_ref"));			//�� �׷� ��ȣ
				wishlistbean.setWishlist_re_lev(rs.getInt("wishlist_re_lev"));		
				wishlistbean.setWishlist_re_seq(rs.getInt("wishlist_re_seq"));		
				wishlistbean.setWishlist_readcont(rs.getInt("wishlist_readcont"));		//��ȸ��
				
				wishlistbean.setWishlist_good(rs.getInt("wishlist_good"));		//��õ��
				
				wishlistbean.setWishlist_date(rs.getString("wishlist_date").substring(0,10));	//�����
				
				wishlistList.add(wishlistbean);	
			}
			rs.close(); pstmt.close(); con.close();	
		}catch(Exception e){
			e.printStackTrace();
		}
		return wishlistList;
	}
	

}
