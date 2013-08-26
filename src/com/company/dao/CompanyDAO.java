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
	
	//디비 연동을 위한 참조변수 선언
	public CompanyDAO(){
		try{
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/OracleDB");		 
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/*DB작업에 필요한 내용을 메소드 단위로 작성*/
	
	/*신청서 제출하기*/
	public void insertCompany(CompanyBean combean) {
		try{
			//max() 함수는 최대값을 구하는 함수이다.
			con=ds.getConnection();
			sql = "select max(com_num) from company";
			
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			int num = 0;
			
			if(rs.next()){
				num = rs.getInt(1)+1; //최대 글 번호 +1
			}else{
				num=1;
			}
		
			sql = "insert into company values(?,?,?,?,?,?,?,?,?,?,sysdate)";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, num);     		//첫번째 물음표에 번호 배정
			pstmt.setString(2, combean.getCom_co()); 		    //두번째 물음표에 회사이름 배정
			pstmt.setString(3, combean.getCom_name());   		//세번째 물음표에 담당자 이름 배정
			pstmt.setString(4, combean.getCom_tel());    		//네번째 물음표에 연락처 배정
			pstmt.setString(5, combean.getCom_mail());    		//다섯번째 물음표에 이메일 배정
			pstmt.setString(6, combean.getCom_website());    	//여섯째 물음표에 웹사이트 배정
			pstmt.setString(7, combean.getCom_product());    	//일곱째 물음표에 제품명 배정
			pstmt.setString(8, combean.getCom_price());    		//여덟째 물음표에 가격 배정
			pstmt.setString(9, combean.getCom_cont());    		//아홉째 물음표에 제품설명 배정
			pstmt.setString(10, combean.getCom_file1());    	//열번째 물음표에 첨부파일 배정
			
			pstmt.executeUpdate();
			
			rs.close(); pstmt.close(); con.close();	
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/*총 레코드 수(입점 신청한 회사) 구하기*/
	public int getListCount() {
		int count = 0;
		
		try{
			//count(*)는 전체 레코드 수를 계산해 주는 sql 함수이다.
			con=ds.getConnection();
			sql = "select count(*) from company";
			
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
	
	/*입점 신청한 회사 목록보기*/
	public List<CompanyBean> getCompanyList(int page, int limit) {
		//List의 하위 클래스인 ArrayList를 이용하여 객체를 생성한다. 
		List<CompanyBean> companyList = new ArrayList<CompanyBean>();
		
		int startrow = (page-1)*10+1;		//페이지 시작번호
		int endrow = page*limit; 			//페이지 끝번호
		
		try{
			con=ds.getConnection();
			sql = "select * from " +
					" (select * from company order by com_num desc) " +
					" where com_num >=? and com_num <=?";
			
			/*sql ="select * from (select bbs.*, rownum as rnum "
					 + " from (select * from bbs order by bbs_re_ref desc, bbs_re_seq asc) bbs)" 
					 + " where rnum >=? and rnum<=?"; */
			//자료실 그룹번호(bbs_re_ref)는 내림차순으로 정렬(최근에 작성 내용 위로)
			//답변글(bbs_re_seq)은 그룹번호의 아래쪽으로 정렬되게 한다. 
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				CompanyBean combean = new CompanyBean();
				
				combean.setCom_num(rs.getInt("com_num"));						//글번호
				combean.setCom_product(rs.getString("com_product"));			//입점 상품
				combean.setCom_price(rs.getString("com_price"));				//입점 상품 가격
				combean.setCom_co(rs.getString("com_co"));						//회사 이름
				combean.setCom_name(rs.getString("com_name"));					//담당자 이름
				combean.setCom_date(rs.getString("com_date").substring(0,10));	//신청일
				//0에서 10 미만 사이의 년월일 리턴
				
				companyList.add(combean);	
			}
			rs.close(); pstmt.close(); con.close();	
		}catch(Exception e){
			e.printStackTrace();
		}
		return companyList;
	}
	
	/*신청서 내용보기*/
	public CompanyBean getCont(int num) {		
		CompanyBean combean = null;
		
		try{
			con = ds.getConnection();
			
			sql = "select * from company where com_num=?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, num);  //물음표에 num을 배정한다. 
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				combean = new CompanyBean();
				
				//디비에서 해당 필드를 가져와 빈에 저장한다. 
				combean.setCom_num(rs.getInt("com_num")); 	
				combean.setCom_date(rs.getString("com_date").substring(0,10)); //신청날짜
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
		return combean; //자바빈 참조 변수
	}
	
	/*신청 기업 정보 수정*/
	public void companyEdit(CompanyBean combean) {
		try{
			con = ds.getConnection();
			
			sql = "update company set com_co=?, com_name=?, " +
					" com_tel=?, com_mail=?, com_website=?, com_product=?, " +
					" com_price=?, com_cont=?, com_file1=? " +
					" where com_num = ?";
			
			pstmt = con.prepareStatement(sql);
			
			//각 물음표에 순서대로 해당하는 속성을 배정한다. 
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
	
	/*신청 기업 목록 삭제*/
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

	/*입점 상품 검색 리스트 보기*/
	public List<CompanyBean> getcompanySearchList(int page, int limit, String searchSelect, String searchCom) {
		List<CompanyBean> companyList = new ArrayList<CompanyBean>();
		
		int startrow = (page-1)*10+1;		//페이지 시작번호
		int endrow = page*limit; 			//페이지 끝번호
		
		try{
			
			System.out.println("DAO =========== searchSelect : " + searchSelect);
			System.out.println("DAO =========== searchCom : " + searchCom);
			
			con=ds.getConnection();
			
			if(searchSelect.equals("com_co")){
				System.out.println("============================== 나와랏!");
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
			//자료실 그룹번호(bbs_re_ref)는 내림차순으로 정렬(최근에 작성 내용 위로)
			//답변글(bbs_re_seq)은 그룹번호의 아래쪽으로 정렬되게 한다. 
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			pstmt.setString(3, "%" + searchCom + "%");
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				CompanyBean combean = new CompanyBean();
				
				combean.setCom_num(rs.getInt("com_num"));						//글번호
				combean.setCom_product(rs.getString("com_product"));			//입점 상품
				combean.setCom_price(rs.getString("com_price"));				//입점 상품 가격
				combean.setCom_co(rs.getString("com_co"));						//회사 이름
				combean.setCom_name(rs.getString("com_name"));					//담당자 이름
				combean.setCom_date(rs.getString("com_date").substring(0,10));	//신청일
				//0에서 10 미만 사이의 년월일 리턴
				
				companyList.add(combean);	
			}
			rs.close(); pstmt.close(); con.close();	
		}catch(Exception e){
			e.printStackTrace();
		}
		return companyList;
	}
	
}
