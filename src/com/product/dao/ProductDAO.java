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
	
	/*총 레코드 수(상품 수) 구하기*/
	public int getListCount() {
		int count = 0;
		
		try{
			//count(*)는 전체 레코드 수를 계산해 주는 sql 함수이다.
			con=ds.getConnection();
			sql = "select count(*) from product";
			
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
	
	/*쇼핑 상품 게시판 목록보기*/
	public List<ProductBean> getproductList(int page, int limit){
		//java.util 패키지에 있는 List 컬렉션을 임포트
		
		List<ProductBean> productList = new ArrayList<ProductBean>();
		
		int startrow = (page-1)*10+1;		//페이지 시작번호
		int endrow = page*limit; 			//페이지 끝번호
		
		try{
			con=ds.getConnection();
			sql = "select * from " +
					" (select * from product order by product_num desc) " +
					" where product_num >=? and product_num <=?";
			
//			sql = "select * from product order by product_num desc";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			
			rs = pstmt.executeQuery(); //select 문 실행
			
			while(rs.next()){ //레코드가 존재하면 반복 수행
				ProductBean bean = new ProductBean();
				
				//7개의 필드를 db에 가져온 후 java bean으로 레코드를 구성하였음
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
				
				productList.add(bean); //첫번째 레코드 추가했음
			}
			rs.close(); pstmt.close(); con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return productList;
	}
	
	/*쇼핑 상품 삽입하기*/
	public int insertproduct(ProductBean bean){
		int re = 0;
		
		try{			
			sql = "insert into product" + 
			  			" values(product_seq.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,sysdate)";
			
			pstmt = con.prepareStatement(sql); //sql문 선행 처리
			
			pstmt.setString(1, bean.getProduct_code());   //첫번째 물음표에 상품 코드
			pstmt.setString(2, bean.getProduct_name());   //두번째 물음표에 상품 이름
			pstmt.setInt(3, bean.getProduct_price());    	  //세번째 물음표에 상품 가격
			pstmt.setInt(4, bean.getProduct_number());    	  //네번째 물음표에 상품 수량
			pstmt.setString(5, bean.getProduct_company());  //다섯번째 물음표에 상품 제조 회사
			pstmt.setString(6, bean.getProduct_summary());       //여섯째 물음표에 상품 요약 정보
			pstmt.setString(7, bean.getProduct_description());    //일곱번째 물음표에 세부 설명
			pstmt.setString(8, bean.getProduct_mainImg());    //여덟번째 물음표에  메인 사진
			pstmt.setString(9, bean.getProduct_thum01());    //아홉섯번째 물음표에 서브 사진1
			pstmt.setString(10, bean.getProduct_thum02());       //열번째 물음표에 서브 사진2
			pstmt.setString(11, bean.getProduct_thum03());    //열한번째 물음표에 서브 사진3
			pstmt.setString(12, bean.getProduct_video());    //열두째 물음표에 관련 동영상
			pstmt.setString(13, bean.getProduct_opt01());    //열세째 물음표에 옵션1
			pstmt.setString(14, bean.getProduct_opt01_1());       //열네째 물음표에 옵션1-1
			pstmt.setString(15, bean.getProduct_opt01_2());    //열다섯째 물음표에 옵션1-2
			pstmt.setString(16, bean.getProduct_opt01_3());    //열여섯번째 물음표에 옵션1-3
			pstmt.setString(17, bean.getProduct_opt01_4());    //열일곱번째 물음표에 옵션1-4
			pstmt.setString(18, bean.getProduct_opt01_5());       //열여덟번째 물음표에 옵션1-5
			pstmt.setString(19, bean.getProduct_opt02());    //열아홉번째 물음표에 옵션2
			pstmt.setString(20, bean.getProduct_opt02_1());    //열스무번째 물음표에 옵션2-1
			pstmt.setString(21, bean.getProduct_opt02_2());    //열스물한번째 물음표에 옵션2-2
			pstmt.setString(22, bean.getProduct_opt02_3());    //열스물두번째 물음표에 옵션2-3
			pstmt.setString(23, bean.getProduct_opt02_4());    //열스물세번째 물음표에 옵션2-4
			pstmt.setString(24, bean.getProduct_opt02_5());    //열스물네번째 물음표에 옵션2-5
			pstmt.setString(25, bean.getProduct_startDate());    //열스물다섯번째 물음표에 시작날짜
			pstmt.setString(26, bean.getProduct_endDate());    //열스물여섯째번 물음표에 마감날짜
			pstmt.setInt(27, bean.getProduct_post());    //열스물일곱번째 물음표에 게시 회차
			pstmt.setInt(28, bean.getProduct_state());    //열스물여덟번째 물음표에 게시상태
			pstmt.setInt(29, bean.getProduct_shop());    //열스물아홉번째 물음표에 숍 위치
			
			re = pstmt.executeUpdate(); //insert문을 실행하고 실행결과를 re에 저장
	
			//열린 객체를 닫는다.
			con.close();
			pstmt.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return re;
	}
	
	/*상품 내용보기*/
	public ProductBean getCont(int num) {		
		ProductBean productbean = null;
		
		try{
			con = ds.getConnection();
			
			sql = "select * from product where product_num=?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, num);  //물음표에 num을 배정한다. 
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				productbean = new ProductBean();				
				
				//디비에서 해당 필드를 가져와 빈에 저장한다. 
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
		return productbean; //자바빈 참조 변수
	}
	
	/*상품 정보 수정*/
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
			
			//각 물음표에 순서대로 해당하는 속성을 배정한다. 
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
	
	/*상품 상세보기 for client*/
	public ProductBean getClient(int product_shop) {		
		ProductBean productbean = null;
		
		try{
			con = ds.getConnection();
			
			if(product_shop == 1){
				System.out.println("============================== 나와랏!");
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
				
				//디비에서 해당 필드를 가져와 빈에 저장한다. 
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
		return productbean; //자바빈 참조 변수
	}
	
	/*상품 목록 삭제*/
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
	
	/*상품 검색 목록*/
	public List<ProductBean> getproductSearchList(int page, int limit, String searchSelect, String searchCom) {
		List<ProductBean> productList = new ArrayList<ProductBean>();
		
		int startrow = (page-1)*10+1;		//페이지 시작번호
		int endrow = page*limit; 			//페이지 끝번호
		
		try{
			
			System.out.println("DAO =========== searchSelect : " + searchSelect);
			System.out.println("DAO =========== searchCom : " + searchCom);
			
			con=ds.getConnection();
			
			if(searchSelect.equals("product_name")){
				System.out.println("============================== 나와랏!");
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
			//자료실 그룹번호(bbs_re_ref)는 내림차순으로 정렬(최근에 작성 내용 위로)
			//답변글(bbs_re_seq)은 그룹번호의 아래쪽으로 정렬되게 한다. 
			
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
	
	/*상품 내용보기*/
	public ProductBean getClientForShop(String shop) {		
		ProductBean productbean = null;
		
		try{
			con = ds.getConnection();
			
			if(shop.equals("1")){
				System.out.println("============================== 나와랏!");
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
				
				//디비에서 해당 필드를 가져와 빈에 저장한다. 
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
		return productbean; //자바빈 참조 변수
	}
}
