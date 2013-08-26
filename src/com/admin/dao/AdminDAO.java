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
	
	//디비 연동을 위한 참조변수 선언
	public AdminDAO(){
		try{
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/OracleDB");		
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/*아이디 중복 체크*/
	public int checkMemberId(String id){
		int re = -1;
		
		try{
			con = ds.getConnection();
			
			sql = "select admin_id from admin" +
					" where admin_id=?";
			
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

	/*비밀번호 찾기(아이디, 이름으로 검색)*/
	public AdminBean findpwd(String pwd_id, String pwd_name) {
		
		AdminBean member = null;
		
		try{
			con = ds.getConnection();
			
			sql = "select admin_pass from admin " +
					" where admin_id=? and admin_name=?";
			
			//아이디와 이름을 기본으로 비번을 검색한다.
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pwd_id); //첫번째 물음표에 pwd_id 배정
			pstmt.setString(2, pwd_name); //첫번째 물음표에 pwd_name 배정
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){}
				member = new AdminBean();
				//db에서 비번을 가져와 Setter()로 빈에 저장한다.
				member.setAdmin_pass(rs.getString("admin_pass"));
		    rs.close(); pstmt.close(); con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return member;
	}

	/*멤버 가입*/
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
			
			//자바빈의 레코드(m)를 필드 단위로 각 물음표에 배정한다
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

	/*로그인(아이디와 비밀번호 체크)*/
	public int userCheck(String id, String pass) {
		// 사용자가 입력한 id와 pass로 멤버를 체크한다.
		int re = -1; //등록된 멤버원의 상태가 탈퇴일 경우 리턴되는 값
		
		try{
			con = ds.getConnection();
			
			sql = "select admin_pass from admin " +
					"where admin_id=? and admin_state=1";
			//가입회원 상태가 1인 경우에만 로그인 되게한다.
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				if(rs.getString("admin_pass").equals(pass)){
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

	/*회원 계정 체크(정보 가져오기)*/
	public AdminBean getMember(String id) {
		AdminBean m = null;
		
		try{
			con = ds.getConnection();
			
			sql = "select * from admin where admin_id=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()){//검색 레코드 값이 있다면
				m = new AdminBean();
				
				//디비에서 각 필드가 가져와서 빈에 레코드로 저장한다
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

	/*멤버 정보 수정*/
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
			
			//자바빈의 레코드(m)를 필드 단위로 각 물음표에 배정한다
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

	/*멤버 탈퇴*/
	public int deleteMember(String id, String pass) {
		int re = -1; //탈퇴가 실패하였을 때 리턴되는 값
		
		try{
			con = ds.getConnection();
			
			sql = "select admin_pass from admin " +
					" where admin_id=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){//검색된 결과가 있는 경우
				//db에 비번과 사용자가 입력한 비번을 비교한다. 
				if(rs.getString("admin_pass").equals(pass)){
					//비번이 같은 경우
					sql = "update admin " +
							" set admin_state=2, admin_deldate=sysdate " +
							" where admin_id=?";
					//회원의 상태를 2로 저장하고 탈퇴 날짜를 오늘날짜로 지정한다
					
					pstmt = con.prepareStatement(sql);

					pstmt.setString(1, id);
					
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
}
