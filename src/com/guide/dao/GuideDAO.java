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
	
	/*총 레코드 수(가이드 수) 구하기*/
	public int getListCount() {
		int count = 0;
		
		try{
			//count(*)는 전체 레코드 수를 계산해 주는 sql 함수이다.
			con=ds.getConnection();
			sql = "select count(*) from guide";
			
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
	
	/*쇼핑 가이드 게시판 목록보기*/
	public List<GuideBean> getGuideList(){
		//java.util 패키지에 있는 List 컬렉션을 임포트
		
		List<GuideBean> guideList = new ArrayList<GuideBean>();
		
		try{
			con=ds.getConnection();
			
			sql = "select * from guide order by guide_num desc";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery(); //select 문 실행
			
			while(rs.next()){ //레코드가 존재하면 반복 수행
				GuideBean bean = new GuideBean();
				
				//7개의 필드를 db에 가져온 후 java bean으로 레코드를 구성하였음
				bean.setGuide_num(rs.getInt("guide_num"));
				bean.setGuide_product(rs.getString("guide_product"));
				bean.setGuide_s_date(rs.getString("guide_s_date"));
				bean.setGuide_e_date(rs.getString("guide_e_date"));
				bean.setGuide_post(rs.getInt("guide_post"));
				bean.setGuide_state(rs.getInt("guide_state"));
				bean.setGuide_reg_date(rs.getString("guide_reg_date").substring(0,10));
				
				guideList.add(bean); //첫번째 레코드 추가했음
			}
			rs.close(); pstmt.close(); con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return guideList;
	}
	
	/*쇼핑 가이드 삽입하기*/
	public int insertGuide(GuideBean bean){
		int re = 0;
		
		try{			
			sql = "insert into guide" + 
			  			" values(guide_seq.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,sysdate)";
			
			pstmt = con.prepareStatement(sql); //sql문 선행 처리
			
			pstmt.setString(1, bean.getGuide_s_date());   //첫번째 물음표에 시작 날짜
			pstmt.setString(2, bean.getGuide_e_date());   //두번째 물음표에 마감 날짜
			pstmt.setInt(3, bean.getGuide_post());    	  //세번째 물음표에 게시 회차
			pstmt.setInt(4, bean.getGuide_state());    	  //네번째 물음표에 게시 상태
			pstmt.setString(5, bean.getGuide_product());  //다섯번째 물음표에 제품명
			pstmt.setString(6, bean.getGuide_q1());       //여섯째 물음표에 문제1
			pstmt.setString(7, bean.getGuide_q1_a1());    //일곱번째 물음표에 보기1-1
			pstmt.setString(8, bean.getGuide_q1_a2());    //여덟번째 물음표에 보기1-2
			pstmt.setString(9, bean.getGuide_q1_a3());    //아홉섯번째 물음표에 보기1-3
			pstmt.setString(10, bean.getGuide_q2());       //열번째 물음표에 문제2
			pstmt.setString(11, bean.getGuide_q2_a1());    //열한번째 물음표에 보기2-1
			pstmt.setString(12, bean.getGuide_q2_a2());    //열두째 물음표에 보기2-2
			pstmt.setString(13, bean.getGuide_q2_a3());    //열세째 물음표에 보기2-3
			pstmt.setString(14, bean.getGuide_q3());       //열네째 물음표에 문제3
			pstmt.setString(15, bean.getGuide_q3_a1());    //열다섯째 물음표에 보기3-1
			pstmt.setString(16, bean.getGuide_q3_a2());    //열여섯번째 물음표에 보기3-2
			pstmt.setString(17, bean.getGuide_q3_a3());    //열일곱번째 물음표에 보기3-3
			pstmt.setString(18, bean.getGuide_q4());       //열여덟번째 물음표에 문제4
			pstmt.setString(19, bean.getGuide_q4_a1());    //열아홉번째 물음표에 보기4-1
			pstmt.setString(20, bean.getGuide_q4_a2());    //열스무번째 물음표에 보기4-2
			pstmt.setString(21, bean.getGuide_q4_a3());    //열스물한번째 물음표에 보기4-3
			
			re = pstmt.executeUpdate(); //insert문을 실행하고 실행결과를 re에 저장
	
			//열린 객체를 닫는다.
			con.close();
			pstmt.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return re;
	}
	
	/*가이드 내용보기*/
	public GuideBean getCont(int num) {		
		GuideBean guidebean = null;
		
		try{
			con = ds.getConnection();
			
			sql = "select * from guide where guide_num=?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, num);  //물음표에 num을 배정한다. 
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				guidebean = new GuideBean();
				
				//디비에서 해당 필드를 가져와 빈에 저장한다. 
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
		return guidebean; //자바빈 참조 변수
	}
	
	/*가이드 정보 수정*/
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
			
			//각 물음표에 순서대로 해당하는 속성을 배정한다. 
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
				
				//디비에서 해당 필드를 가져와 빈에 저장한다. 
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
		return guidebean; //자바빈 참조 변수
	}
	
	/*가이드 목록 삭제*/
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
