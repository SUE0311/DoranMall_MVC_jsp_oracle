package com.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class AdminDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	DataSource ds = null;
	String sql = null;
	
	//��� ������ ���� �������� ����
	public AdminDAO(){
		try{
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/OracleDB");		
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/*���̵� �ߺ� üũ*/
	public int checkMemberId(String id){
		int re = -1;
		
		try{
			con = ds.getConnection();
			
			sql = "select admin_id from admin" +
					" where admin_id=?";
			
			pstmt = con.prepareStatement(sql);
					
			pstmt.setString(1,id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){ //�˻������ ���� ���
				re=1;  	   //�ߺ� ���̵� �ǹ��Ѵ�. �ƴϸ� -1(��밡���� ���̵�)
			}
			rs.close(); pstmt.close(); con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return re;
	}

	/*��й�ȣ ã��(���̵�, �̸����� �˻�)*/
	public AdminBean findpwd(String pwd_id, String pwd_name) {
		
		AdminBean member = null;
		
		try{
			con = ds.getConnection();
			
			sql = "select admin_pass from admin " +
					" where admin_id=? and admin_name=?";
			
			//���̵�� �̸��� �⺻���� ����� �˻��Ѵ�.
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pwd_id); //ù��° ����ǥ�� pwd_id ����
			pstmt.setString(2, pwd_name); //ù��° ����ǥ�� pwd_name ����
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){}
				member = new AdminBean();
				//db���� ����� ������ Setter()�� �� �����Ѵ�.
				member.setAdmin_pass(rs.getString("admin_pass"));
		    rs.close(); pstmt.close(); con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return member;
	}

	/*��� ����*/
	public void insertMember(AdminBean m) {
		try{
			con = ds.getConnection();
			
			sql = "insert into admin " + 
					"(admin_num, admin_empNo, admin_name, admin_id, " +
					"admin_pass, admin_dept, admin_pos, " +
					"admin_tel, admin_auth, admin_state, " +
					"admin_regdate) " +
					"values(admin_seq.nextval,?,?,?,?,?,?,?,default,1,sysdate)";
			
			pstmt = con.prepareStatement(sql);
			
			//�ڹٺ��� ���ڵ�(m)�� �ʵ� ������ �� ����ǥ�� �����Ѵ�
			pstmt.setString(1, m.getAdmin_empNo()); 
			pstmt.setString(2, m.getAdmin_name());
			pstmt.setString(3, m.getAdmin_id());
			pstmt.setString(4, m.getAdmin_pass());
			pstmt.setString(5, m.getAdmin_dept());
			pstmt.setString(6, m.getAdmin_pos());
			pstmt.setString(7, m.getAdmin_tel());
			
			pstmt.executeUpdate();
			
			pstmt.close(); con.close();	
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/*�α���(���̵�� ��й�ȣ üũ)*/
	public int userCheck(String id, String pass) {
		// ����ڰ� �Է��� id�� pass�� ����� üũ�Ѵ�.
		int re = -1; //��ϵ� ������� ���°� Ż���� ��� ���ϵǴ� ��
		
		try{
			con = ds.getConnection();
			
			sql = "select admin_pass from admin " +
					"where admin_id=? and admin_state=1";
			//����ȸ�� ���°� 1�� ��쿡�� �α��� �ǰ��Ѵ�.
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				if(rs.getString("admin_pass").equals(pass)){
					re=1; //pass�� ���� ���
				}else{
					re=0; //pass�� �ٸ� ���
				}
			}
			rs.close(); pstmt.close(); con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return re; //ȣ���� ������ ����
	}

	/*ȸ�� ���� üũ(���� ��������)*/
	public AdminBean getMember(String id) {
		AdminBean m = null;
		
		try{
			con = ds.getConnection();
			
			sql = "select * from admin where admin_id=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()){//�˻� ���ڵ� ���� �ִٸ�
				m = new AdminBean();
				
				//��񿡼� �� �ʵ尡 �����ͼ� �� ���ڵ�� �����Ѵ�
				m.setAdmin_empNo(rs.getString("admin_empNo"));
				m.setAdmin_name(rs.getString("admin_name"));
				m.setAdmin_id(rs.getString("admin_id"));
				m.setAdmin_pass(rs.getString("admin_pass"));
				m.setAdmin_dept(rs.getString("admin_dept"));
				m.setAdmin_pos(rs.getString("admin_pos"));
				m.setAdmin_tel(rs.getString("admin_tel"));
				m.setAdmin_auth(rs.getString("admin_auth"));
			}
			rs.close(); pstmt.close(); con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return m;
	}

	/*��� ���� ����*/
	public int updateMember(AdminBean m) {
		int re = 0;
		
		try{
			con = ds.getConnection();
			
			sql = "update admin " +
					" set admin_name=?, " +
					" admin_pass=?, " +
					" admin_dept=?, admin_pos=?, " +
					" admin_tel=? " +
					" where admin_empNo=?";
			
			pstmt = con.prepareStatement(sql);
			
			//�ڹٺ��� ���ڵ�(m)�� �ʵ� ������ �� ����ǥ�� �����Ѵ�
//			pstmt.setString(1, m.getAdmin_empNo());
			pstmt.setString(1, m.getAdmin_name());
//			pstmt.setString(2, m.getAdmin_id());
			pstmt.setString(2, m.getAdmin_pass());
			pstmt.setString(3, m.getAdmin_dept());
			pstmt.setString(4, m.getAdmin_pos());
			pstmt.setString(5, m.getAdmin_tel());
			
			pstmt.setString(6, m.getAdmin_empNo()); 
			
			re = pstmt.executeUpdate();
			
			pstmt.close(); con.close();	
		}catch(Exception e){
			e.printStackTrace();
		}	
		return re;
	}

	/*��� Ż��*/
	public int deleteMember(String id, String pass) {
		int re = -1; //Ż�� �����Ͽ��� �� ���ϵǴ� ��
		
		try{
			con = ds.getConnection();
			
			sql = "select admin_pass from admin " +
					" where admin_id=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){//�˻��� ����� �ִ� ���
				//db�� ����� ����ڰ� �Է��� ����� ���Ѵ�. 
				if(rs.getString("admin_pass").equals(pass)){
					//����� ���� ���
					sql = "update admin " +
							" set admin_state=2, admin_deldate=sysdate " +
							" where admin_id=?";
					//ȸ���� ���¸� 2�� �����ϰ� Ż�� ��¥�� ���ó�¥�� �����Ѵ�
					
					pstmt = con.prepareStatement(sql);

					pstmt.setString(1, id);
					
					re = pstmt.executeUpdate();
					//������ ��� re=1
				}else{//����� �ٸ� ���
					re=0;
				}
			}
			rs.close(); pstmt.close(); con.close();	
		}catch(Exception e){
			e.printStackTrace();
		}	
		return re; //ȣ���� ������ re �� ����
	}	
}
