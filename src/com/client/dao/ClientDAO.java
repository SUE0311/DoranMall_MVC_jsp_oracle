package com.client.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ClientDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	DataSource ds = null;
	String sql = null;
	
	//��� ������ ���� �������� ����
	public ClientDAO(){
		try{
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/OracleDB");	
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/*���̵� �ߺ� üũ*/
	public int checkClientId(String id){
		int re = -1;
		
		try{
			con = ds.getConnection();
			
			sql = "select client_id from client" +
					" where client_id=?";
			
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

	/*�����ȣ ã��(������ �˻�)*/
	public List searchZipcode(String dong) {
		//List �÷����� �������̽��̱� ������ ���� ��ü�� ������ �� 
		//���� ���� Ŭ������ ArrayList Ŭ������ �̿��Ͽ� ��ü�� ������ �� �ִ�. 
		
		List zipcodeList = new ArrayList();
		
		try{
			con = ds.getConnection();	
			
			sql = "select * from zipcode where dong like ?";
			//like�� ���� ���ڿ��� �˻��ϴ� Ű�����̴�. 
			
			System.out.println("=============" + dong);
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, "%"+dong+"%");
			
			rs = pstmt.executeQuery();
			
			//%��% ���·� ����ڰ� �Է��� ���� ������ ���ڵ带 �˻��Ѵ�.
			while(rs.next()){
				String zipcode = rs.getString("zipcode"); //�����ȣ
				String sido = rs.getString("sido"); //�õ�
				String gugun = rs.getString("gugun"); //����
				String dong2 = rs.getString("dong"); //���鵿
				String bunji = rs.getString("bunji"); //����
				//������ �� �ּ� ����
				String addr = sido + " " + gugun + " " + dong2;
				//������ ������ �ּ� ����
				String addr2 = sido + " " + gugun + " " + dong2 + " " + bunji; 
 			
				//List �÷��ǿ� ��Ҹ� ���ڵ� ���·� �����Ѵ�. 
				//����Ʈ �÷����� Ÿ�� ���������� ���ʸ����� �ϴ� ���� ����..
				zipcodeList.add(zipcode + "," + addr + "," + addr2);
			}
			rs.close(); pstmt.close(); con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return zipcodeList;
	}

	/*��й�ȣ ã��(���̵�, �̸����� �˻�)*/
	public ClientBean findpwd(String pwd_id, String pwd_name) {
		
		ClientBean client = null;
		
		try{
			con = ds.getConnection();	
			
			sql = "select client_pass from client " +
					" where client_id=? and client_name=?";
			
			//���̵�� ȸ���̸��� �⺻���� ����� �˻��Ѵ�.
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pwd_id); //ù��° ����ǥ�� pwd_id ����
			pstmt.setString(2, pwd_name); //ù��° ����ǥ�� pwd_name ����
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){}
				client = new ClientBean();
				//db���� ����� ������ Setter()�� �� �����Ѵ�.
				client.setClient_pass(rs.getString("client_pass"));
		    rs.close(); pstmt.close(); con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return client;
	}
	
	/*���̵� ã��(�̸�, �̸��Ϸ� �˻�)*/
	public ClientBean findId(String find_name, String find_email) {
		ClientBean client = null;
		
		try{
			con = ds.getConnection();	
			
			sql = "select client_id from client " +
					" where client_name=? and client_email=?";
			
			//���̵�� ȸ���̸��� �⺻���� ����� �˻��Ѵ�.
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, find_name); //ù��° ����ǥ�� pwd_id ����
			pstmt.setString(2, find_email); //ù��° ����ǥ�� pwd_name ����
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){}
				client = new ClientBean();
				//db���� ����� ������ Setter()�� �� �����Ѵ�.
				client.setClient_id(rs.getString("client_id"));
		    rs.close(); pstmt.close(); con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return client;
	}

	/*ȸ�� ����*/
	public void insertClient(ClientBean m) {
		try{
			con = ds.getConnection();	

			sql = "insert into client(client_num, client_id, " + 
					"client_pass, client_name, " +
					"client_zip1, client_zip2, client_addr1, " +
					"client_addr2, client_tel, client_email, " +
					"client_regdate, client_rank, client_mileage, client_state) " +
					"values(client_seq.nextval,?,?,?,?,?,?,?,?,?,sysdate,default,default,1)";
			
			pstmt = con.prepareStatement(sql);
			
			//�ڹٺ��� ���ڵ�(m)�� �ʵ� ������ �� ����ǥ�� �����Ѵ�
			pstmt.setString(1, m.getClient_id()); 
			pstmt.setString(2, m.getClient_pass());
			pstmt.setString(3, m.getClient_name());
			pstmt.setString(4, m.getClient_zip1());
			pstmt.setString(5, m.getClient_zip2());
			pstmt.setString(6, m.getClient_addr1());
			pstmt.setString(7, m.getClient_addr2());
			pstmt.setString(8, m.getClient_tel());
			pstmt.setString(9, m.getClient_email());
			
			pstmt.executeUpdate();
			
			pstmt.close(); con.close();	
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/*�α���(���̵�� ��й�ȣ üũ)*/
	public int userCheck(String id, String pass) {
		// ����ڰ� �Է��� id�� pass�� ȸ���� üũ�Ѵ�.
		int re = -1; //��ϵ� ȸ���� ���°� Ż���� ��� ���ϵǴ� ��
		
		try{
			con = ds.getConnection();	
			
			sql = "select client_pass from client " +
					"where client_id=? and client_state=1";
			//����ȸ�� ���°� 1�� ��쿡�� �α��� �ǰ��Ѵ�.
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				if(rs.getString("client_pass").equals(pass)){
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

	/*ȸ�� ���� üũ*/
	public ClientBean getClient(String id) {
		ClientBean m = null;
		
		try{
			con = ds.getConnection();
			
			sql = "select * from client where client_id=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()){//�˻� ���ڵ� ���� �ִٸ�
				m = new ClientBean();
				
				//��񿡼� �� �ʵ带 �����ͼ� �� ���ڵ�� �����Ѵ�
				m.setClient_num(rs.getInt("client_num"));
				m.setClient_id(rs.getString("client_id"));
				m.setClient_pass(rs.getString("client_pass"));
				m.setClient_name(rs.getString("client_name"));
				m.setClient_zip1(rs.getString("client_zip1"));
				m.setClient_zip2(rs.getString("client_zip2"));
				m.setClient_addr1(rs.getString("client_addr1"));
				m.setClient_addr2(rs.getString("client_addr2"));
				m.setClient_tel(rs.getString("client_tel"));
				m.setClient_email(rs.getString("client_email"));
				m.setClient_regdate(rs.getString("client_regdate"));
				m.setClient_rank(rs.getString("client_rank"));
				m.setClient_mileage(rs.getInt("client_mileage"));
				m.setClient_state(rs.getInt("client_state"));
				m.setClient_deldate(rs.getString("client_deldate"));
				m.setClient_delcont(rs.getString("client_delcont"));
			}
			rs.close(); pstmt.close(); con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return m;
	}

	/*ȸ�� ���� ����*/
	public int updateClient(ClientBean m) {
		int re = 0;
		
		try{
			con = ds.getConnection();	
			
			sql = "update client " +
					" set client_pass=?, client_name=?, " +
					" client_zip1=?, client_zip2=?," +
					" client_addr1=?, client_addr2=?" +
					" client_tel=?, client_email=? " +
					" where client_id=?";
			
			pstmt = con.prepareStatement(sql);
			
			//�ڹٺ��� ���ڵ�(m)�� �ʵ� ������ �� ����ǥ�� �����Ѵ�
			pstmt.setString(1, m.getClient_pass());
			pstmt.setString(2, m.getClient_name());
			pstmt.setString(3, m.getClient_zip1());
			pstmt.setString(4, m.getClient_zip2());
			pstmt.setString(5, m.getClient_addr1());
			pstmt.setString(6, m.getClient_addr2());
			pstmt.setString(7, m.getClient_tel());
			pstmt.setString(8, m.getClient_email());
			
			pstmt.setString(9, m.getClient_id()); 
			
			re = pstmt.executeUpdate();
			
			pstmt.close(); con.close();	
		}catch(Exception e){
			e.printStackTrace();
		}	
		return re;
	}

	/*ȸ�� Ż��*/
	public int deleteClient(String id, String pass, String del_cont) {
		int re = -1; //ȸ�� Ż�� �����Ͽ��� �� ���ϵǴ� ��
		
		try{
			con = ds.getConnection();	
			
			sql = "select client_pass from client " +
					" where client_id=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){//�˻��� ����� �ִ� ���
				//db�� ����� ����ڰ� �Է��� ����� ���Ѵ�. 
				if(rs.getString("client_pass").equals(pass)){
					//����� ���� ���
					sql = "update client " +
							" set client_delcont=?, " +
							" client_state=2, client_deldate=sysdate " +
							" where client_id=?";
					//Ż�� ���� ����(client_delcont)
					//ȸ���� ���¸� 2�� �����ϰ� Ż�� ��¥�� ���ó�¥�� �����Ѵ�
					
					pstmt = con.prepareStatement(sql);
					
					pstmt.setString(1, del_cont);
					pstmt.setString(2, id);
					
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
	
	/*for ������ view*/
	
	/*�� ���ڵ� ��(ȸ�� ���� ����) ���ϱ�*/
	public int getListCount() {
		int count = 0;
		
		try{
			//count(*)�� ��ü ���ڵ� ���� ����� �ִ� sql �Լ��̴�.
			con=ds.getConnection();
			sql = "select count(*) from client";
			
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
	
	/*������ �� ��� ����*/
	public List<ClientBean> getClientList(int page, int limit) {
		//List�� ���� Ŭ������ ArrayList�� �̿��Ͽ� ��ü�� �����Ѵ�. 
		List<ClientBean> clientList = new ArrayList<ClientBean>();
		
		int startrow = (page-1)*10+1;		//������ ���۹�ȣ
		int endrow = page*limit; 			//������ ����ȣ
		
		try{
			con=ds.getConnection();
			sql = "select * from " +
					" (select * from client order by client_num desc) " +
					" where client_num >=? and client_num <=?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				ClientBean clientbean = new ClientBean();
				
				clientbean.setClient_num(rs.getInt("client_num"));				//�۹�ȣ
				clientbean.setClient_name(rs.getString("client_name"));			//�� �̸�
				clientbean.setClient_id(rs.getString("client_id"));				//�� ���̵�
				clientbean.setClient_rank(rs.getString("client_rank"));			//�� ���
				clientbean.setClient_mileage(rs.getInt("client_mileage"));		//�� ���ϸ���
				clientbean.setClient_regdate(rs.getString("client_regdate").substring(0,10));	//���� �����
				
				clientList.add(clientbean);	
			}
			rs.close(); pstmt.close(); con.close();	
		}catch(Exception e){
			e.printStackTrace();
		}
		return clientList;
	}

	/*�� ����Ʈ �˻�*/
	public List<ClientBean> getClientSearchList(int page, int limit,
			String searchClientSelect, String searchClient) {
		
		List<ClientBean> clientList = new ArrayList<ClientBean>();
		
		int startrow = (page-1)*10+1;		//������ ���۹�ȣ
		int endrow = page*limit; 			//������ ����ȣ
		
		try{
			con=ds.getConnection();
			
			if(searchClientSelect.equals("client_name")){
				sql = "select * from " +
						" (select * from client order by client_num desc) " +
						" where client_num >=? and client_num <=? " +
						" and client_name like ?";
			}else if(searchClientSelect.equals("client_id")){
				sql = "select * from " +
						" (select * from client order by client_num desc) " +
						" where client_num >=? and client_num <=? " +
						" and client_id like ?";
			}
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			pstmt.setString(3, "%" + searchClient + "%");
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				ClientBean clientbean = new ClientBean();
				
				clientbean.setClient_num(rs.getInt("client_num"));				//�۹�ȣ
				clientbean.setClient_name(rs.getString("client_name"));			//�� �̸�
				clientbean.setClient_id(rs.getString("client_id"));				//�� ���̵�
				clientbean.setClient_rank(rs.getString("client_rank"));			//�� ���
				clientbean.setClient_mileage(rs.getInt("client_mileage"));		//�� ���ϸ���
				clientbean.setClient_regdate(rs.getString("client_regdate").substring(0,10));	//���� �����
				
				clientList.add(clientbean);	
			}
			rs.close(); pstmt.close(); con.close();	
		}catch(Exception e){
			e.printStackTrace();
		}
		return clientList;
	}

}
