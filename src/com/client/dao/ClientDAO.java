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
	
	//디비 연동을 위한 참조변수 선언
	public ClientDAO(){
		try{
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/OracleDB");	
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/*아이디 중복 체크*/
	public int checkClientId(String id){
		int re = -1;
		
		try{
			con = ds.getConnection();
			
			sql = "select client_id from client" +
					" where client_id=?";
			
			pstmt = con.prepareStatement(sql);
					
			pstmt.setString(1,id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){ //검색결과가 있을 경우
				re=1;  	   //중복 아이디를 의미한다. 아니면 -1(사용가능한 아이디)
			}
			rs.close(); pstmt.close(); con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return re;
	}

	/*우편번호 찾기(동으로 검색)*/
	public List searchZipcode(String dong) {
		//List 컬렉션은 인터페이스이기 때문에 직접 객체를 생성할 수 
		//없고 하위 클래스인 ArrayList 클래스를 이용하여 객체를 생성할 수 있다. 
		
		List zipcodeList = new ArrayList();
		
		try{
			con = ds.getConnection();	
			
			sql = "select * from zipcode where dong like ?";
			//like는 포함 문자열을 검색하는 키워드이다. 
			
			System.out.println("=============" + dong);
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, "%"+dong+"%");
			
			rs = pstmt.executeQuery();
			
			//%동% 형태로 사용자가 입력한 동을 포함한 레코드를 검색한다.
			while(rs.next()){
				String zipcode = rs.getString("zipcode"); //우편번호
				String sido = rs.getString("sido"); //시도
				String gugun = rs.getString("gugun"); //구군
				String dong2 = rs.getString("dong"); //읍면동
				String bunji = rs.getString("bunji"); //번지
				//번지를 뺀 주소 저장
				String addr = sido + " " + gugun + " " + dong2;
				//번지를 포함한 주소 저장
				String addr2 = sido + " " + gugun + " " + dong2 + " " + bunji; 
 			
				//List 컬렉션에 요소를 레코드 형태로 저장한다. 
				//리스트 컬렉션은 타입 안정성으로 제너릭으로 하는 것을 권장..
				zipcodeList.add(zipcode + "," + addr + "," + addr2);
			}
			rs.close(); pstmt.close(); con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return zipcodeList;
	}

	/*비밀번호 찾기(아이디, 이름으로 검색)*/
	public ClientBean findpwd(String pwd_id, String pwd_name) {
		
		ClientBean client = null;
		
		try{
			con = ds.getConnection();	
			
			sql = "select client_pass from client " +
					" where client_id=? and client_name=?";
			
			//아이디와 회원이름을 기본으로 비번을 검색한다.
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pwd_id); //첫번째 물음표에 pwd_id 배정
			pstmt.setString(2, pwd_name); //첫번째 물음표에 pwd_name 배정
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){}
				client = new ClientBean();
				//db에서 비번을 가져와 Setter()로 빈에 저장한다.
				client.setClient_pass(rs.getString("client_pass"));
		    rs.close(); pstmt.close(); con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return client;
	}
	
	/*아이디 찾기(이름, 이메일로 검색)*/
	public ClientBean findId(String find_name, String find_email) {
		ClientBean client = null;
		
		try{
			con = ds.getConnection();	
			
			sql = "select client_id from client " +
					" where client_name=? and client_email=?";
			
			//아이디와 회원이름을 기본으로 비번을 검색한다.
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, find_name); //첫번째 물음표에 pwd_id 배정
			pstmt.setString(2, find_email); //첫번째 물음표에 pwd_name 배정
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){}
				client = new ClientBean();
				//db에서 비번을 가져와 Setter()로 빈에 저장한다.
				client.setClient_id(rs.getString("client_id"));
		    rs.close(); pstmt.close(); con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return client;
	}

	/*회원 가입*/
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
			
			//자바빈의 레코드(m)를 필드 단위로 각 물음표에 배정한다
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

	/*로그인(아이디와 비밀번호 체크)*/
	public int userCheck(String id, String pass) {
		// 사용자가 입력한 id와 pass로 회원을 체크한다.
		int re = -1; //등록된 회원의 상태가 탈퇴일 경우 리턴되는 값
		
		try{
			con = ds.getConnection();	
			
			sql = "select client_pass from client " +
					"where client_id=? and client_state=1";
			//가입회원 상태가 1인 경우에만 로그인 되게한다.
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				if(rs.getString("client_pass").equals(pass)){
					re=1; //pass가 같은 경우
				}else{
					re=0; //pass가 다른 경우
				}
			}
			rs.close(); pstmt.close(); con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return re; //호출한 곳으로 리턴
	}

	/*회원 계정 체크*/
	public ClientBean getClient(String id) {
		ClientBean m = null;
		
		try{
			con = ds.getConnection();
			
			sql = "select * from client where client_id=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()){//검색 레코드 값이 있다면
				m = new ClientBean();
				
				//디비에서 각 필드를 가져와서 빈에 레코드로 저장한다
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

	/*회원 정보 수정*/
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
			
			//자바빈의 레코드(m)를 필드 단위로 각 물음표에 배정한다
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

	/*회원 탈퇴*/
	public int deleteClient(String id, String pass, String del_cont) {
		int re = -1; //회원 탈퇴가 실패하였을 때 리턴되는 값
		
		try{
			con = ds.getConnection();	
			
			sql = "select client_pass from client " +
					" where client_id=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){//검색된 결과가 있는 경우
				//db에 비번과 사용자가 입력한 비번을 비교한다. 
				if(rs.getString("client_pass").equals(pass)){
					//비번이 같은 경우
					sql = "update client " +
							" set client_delcont=?, " +
							" client_state=2, client_deldate=sysdate " +
							" where client_id=?";
					//탈퇴 사유 지정(client_delcont)
					//회원의 상태를 2로 저장하고 탈퇴 날짜를 오늘날짜로 지정한다
					
					pstmt = con.prepareStatement(sql);
					
					pstmt.setString(1, del_cont);
					pstmt.setString(2, id);
					
					re = pstmt.executeUpdate();
					//성공한 경우 re=1
				}else{//비번이 다른 경우
					re=0;
				}
			}
			rs.close(); pstmt.close(); con.close();	
		}catch(Exception e){
			e.printStackTrace();
		}	
		return re; //호출한 곳으로 re 값 리턴
	}
	
	/*for 관리자 view*/
	
	/*총 레코드 수(회원 가입 고객수) 구하기*/
	public int getListCount() {
		int count = 0;
		
		try{
			//count(*)는 전체 레코드 수를 계산해 주는 sql 함수이다.
			con=ds.getConnection();
			sql = "select count(*) from client";
			
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()){ //레코드가 있는 경우
				count=rs.getInt(1); //총 레코드 수 지정
			}
			rs.close(); pstmt.close(); con.close();	
		}catch(Exception e){
			e.printStackTrace();
		}
		return count;
	}
	
	/*가입한 고객 명단 보기*/
	public List<ClientBean> getClientList(int page, int limit) {
		//List의 하위 클래스인 ArrayList를 이용하여 객체를 생성한다. 
		List<ClientBean> clientList = new ArrayList<ClientBean>();
		
		int startrow = (page-1)*10+1;		//페이지 시작번호
		int endrow = page*limit; 			//페이지 끝번호
		
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
				
				clientbean.setClient_num(rs.getInt("client_num"));				//글번호
				clientbean.setClient_name(rs.getString("client_name"));			//고객 이름
				clientbean.setClient_id(rs.getString("client_id"));				//고객 아이디
				clientbean.setClient_rank(rs.getString("client_rank"));			//고객 등급
				clientbean.setClient_mileage(rs.getInt("client_mileage"));		//고객 마일리지
				clientbean.setClient_regdate(rs.getString("client_regdate").substring(0,10));	//가입 등록일
				
				clientList.add(clientbean);	
			}
			rs.close(); pstmt.close(); con.close();	
		}catch(Exception e){
			e.printStackTrace();
		}
		return clientList;
	}

	/*고객 리스트 검색*/
	public List<ClientBean> getClientSearchList(int page, int limit,
			String searchClientSelect, String searchClient) {
		
		List<ClientBean> clientList = new ArrayList<ClientBean>();
		
		int startrow = (page-1)*10+1;		//페이지 시작번호
		int endrow = page*limit; 			//페이지 끝번호
		
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
				
				clientbean.setClient_num(rs.getInt("client_num"));				//글번호
				clientbean.setClient_name(rs.getString("client_name"));			//고객 이름
				clientbean.setClient_id(rs.getString("client_id"));				//고객 아이디
				clientbean.setClient_rank(rs.getString("client_rank"));			//고객 등급
				clientbean.setClient_mileage(rs.getInt("client_mileage"));		//고객 마일리지
				clientbean.setClient_regdate(rs.getString("client_regdate").substring(0,10));	//가입 등록일
				
				clientList.add(clientbean);	
			}
			rs.close(); pstmt.close(); con.close();	
		}catch(Exception e){
			e.printStackTrace();
		}
		return clientList;
	}

}
