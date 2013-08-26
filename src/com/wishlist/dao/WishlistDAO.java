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
	
	//디비 연동을 위한 참조변수 선언
	public WishlistDAO(){
		try{
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/OracleDB");	
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/*총 레코드 수 구하기*/
	public int getListCount() {
		int count = 0;
		
		try{
			//count(*)는 전체 레코드 수를 계산해 주는 sql 함수이다.
			con=ds.getConnection();
			sql = "select count(*) from wishlist";
			
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
	
	/*자료실 목록보기*/
	public List<WishlistBean> getwishlistList(int page, int limit) {
		//List의 하위 클래스인 ArrayList를 이용하여 객체를 생성한다. 
		List<WishlistBean> wishlistList = new ArrayList<WishlistBean>();
		
		int startrow = (page-1)*10+1;		//페이지 시작번호
		int endrow = page*limit; 			//페이지 끝번호
		
		try{
			con=ds.getConnection();
			
			sql ="select * from (select wishlist.*, rownum as rnum "
					 + " from (select * from wishlist order by wishlist_re_ref desc, wishlist_re_seq asc) wishlist)" 
					 + " where rnum >=? and rnum<=?"; 
			//자료실 그룹번호(wishlist_re_ref)는 내림차순으로 정렬(최근에 작성 내용 위로)
			//답변글(wishlist_re_seq)은 그룹번호의 아래쪽으로 정렬되게 한다. 
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				WishlistBean wishlistbean = new WishlistBean();
				
				wishlistbean.setWishlist_num(rs.getInt("wishlist_num"));				//글번호
				wishlistbean.setWishlist_id(rs.getString("wishlist_id"));				//글쓴이(아이디)
				wishlistbean.setWishlist_title(rs.getString("wishlist_title"));			//글제목
				wishlistbean.setWishlist_content(rs.getString("wishlist_content"));		//글내용
				wishlistbean.setWishlist_file(rs.getString("wishlist_file"));			//첨부파일
				
				wishlistbean.setWishlist_re_ref(rs.getInt("wishlist_re_ref"));			//글 그룹 번호
				wishlistbean.setWishlist_re_lev(rs.getInt("wishlist_re_lev"));		
				wishlistbean.setWishlist_re_seq(rs.getInt("wishlist_re_seq"));		
				wishlistbean.setWishlist_readcont(rs.getInt("wishlist_readcont"));		//조회수
				
				wishlistbean.setWishlist_good(rs.getInt("wishlist_good"));		//추천수
				
				wishlistbean.setWishlist_date(rs.getString("wishlist_date").substring(0,10));	//등록일
				
				wishlistList.add(wishlistbean);	
			}
			rs.close(); pstmt.close(); con.close();	
		}catch(Exception e){
			e.printStackTrace();
		}
		return wishlistList;
	}

	/*자료실 글 저장*/
	public void insertwishlist(WishlistBean wishlistbean) {
		try{
			//max() 함수는 최대값을 구하는 함수이다.
			con=ds.getConnection();
			sql = "select max(wishlist_num) from wishlist";
			
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			int num = 0;
			
			if(rs.next()){
				num = rs.getInt(1)+1; //최대 글 번호 +1
			}else{
				num=1;
			}
			//wishlist 테이블 전체 속성을 대상으로 값을 물음표에 배정한다.			
			sql = "insert into wishlist values(?,?,?,?,?,?,?,?,?,?,?,sysdate)";
			
			pstmt = con.prepareStatement(sql);
			
			//자바빈의 레코드(m)를 필드 단위로 각 물음표에 배정한다
			pstmt.setInt(1, num);  	//번호
			pstmt.setString(2, wishlistbean.getWishlist_id()); 	    //아이디
			pstmt.setString(3, wishlistbean.getWishlist_pass());	//비번
			pstmt.setString(4, wishlistbean.getWishlist_title());	//제목
			pstmt.setString(5, wishlistbean.getWishlist_content()); //내용
			pstmt.setString(6, wishlistbean.getWishlist_file()); //첨부파일
			pstmt.setInt(7, num); //그룹번호
			pstmt.setInt(8, 0);   //이하 속성은 0으로 초기화
			pstmt.setInt(9, 0);
			pstmt.setInt(10, 0);
			pstmt.setInt(11, 0);
			
			pstmt.executeUpdate();
			
			rs.close(); pstmt.close(); con.close();	
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/*자료실 조회수 증가*/
	public void wishlistHit(int num) {
		try{
			con = ds.getConnection();
			
			sql = "update wishlist set wishlist_readcont = wishlist_readcont+1 " +
					" where wishlist_num = ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, num);  //물음표에 num을 배정한다. 
			
			pstmt.executeUpdate();
			
			pstmt.close(); con.close();	
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	/*게시물 내용 가져오기*/
	public WishlistBean getCont(int num) {
		// TODO Auto-generated method stub
		
		WishlistBean wishlist = null;
		
		try{
			con = ds.getConnection();
			
			sql = "select * from wishlist where wishlist_num=?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, num);  //물음표에 num을 배정한다. 
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				wishlist = new WishlistBean();
				
				//디비에서 해당 필드를 가져와 빈에 저장한다. 
				wishlist.setWishlist_num(rs.getInt("wishlist_num")); 	//연번
				wishlist.setWishlist_id(rs.getString("wishlist_id")); 	//아이디
				wishlist.setWishlist_pass(rs.getString("wishlist_pass")); 	//비밀번호
				wishlist.setWishlist_title(rs.getString("wishlist_title")); 	//제목
				wishlist.setWishlist_content(rs.getString("wishlist_content")); 	//내용
				wishlist.setWishlist_file(rs.getString("wishlist_file")); 	//첨부파일
				wishlist.setWishlist_re_ref(rs.getInt("wishlist_re_ref")); 	//글 그룹 번호
				wishlist.setWishlist_re_lev(rs.getInt("wishlist_re_lev")); 	//답변 글 화면 위치 번호
				wishlist.setWishlist_re_seq(rs.getInt("wishlist_re_seq")); 	//답변 글 레벨 순서
				wishlist.setWishlist_good(rs.getInt("wishlist_good")); 		//추천수
				wishlist.setWishlist_readcont(rs.getInt("wishlist_readcont")); 	//조회수
				wishlist.setWishlist_date
					(rs.getString("wishlist_date").substring(0,10)); 	//등록날짜
			}
			rs.close(); pstmt.close(); con.close();	
		}catch(Exception e){
			e.printStackTrace();
		}
		return wishlist; //자바빈 참조 변수
	}

	/*자료실 글 수정*/
	public void wishlistEdit(WishlistBean wishlistbean) {
		try{
			con = ds.getConnection();
			
			sql = "update wishlist set wishlist_id=?, wishlist_title=?, " +
					" wishlist_content=?, wishlist_file=? " +
					" where wishlist_num = ?";
			
			pstmt = con.prepareStatement(sql);
			
			//각 물음표에 순서대로 해당하는 속성을 배정한다. 
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

	/*자료실 게시물 삭제*/
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

	/*자료실 답변 글 저장*/
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
				num = rs.getInt(1)+1; //최근 글번호에 +1
			}else{
				num = 1; //없으면 1로 초기화
			}
			
			sql = "update wishlist set wishlist_re_seq=wishlist_re_seq+1 " +
					" where wishlist_re_ref=? and wishlist_re_seq > ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, re_ref); //글 그룹번호 배정
			pstmt.setInt(2, re_seq); //답변글 레벨값 배정
			
			pstmt.executeUpdate();
			
			re_seq = re_seq+1; //답변글 레벨을 1증가
			re_lev = re_lev+1; //답변글 위치번호 1증가
		
			System.out.println(num);
			System.out.println(re_seq);
			System.out.println(re_lev);
			
			sql = "insert into wishlist values(?,?,?,?,?,?,?,?,?,?,?,sysdate)";
			
			pstmt=con.prepareStatement(sql);
			
			pstmt.setInt(1, num);	//글번호
			pstmt.setString(2, wishlistbean.getWishlist_id());//글쓴이
			pstmt.setString(3, wishlistbean.getWishlist_pass());//비번
			
//			System.out.println("========== 비밀번호 넣고");
			
			pstmt.setString(4, wishlistbean.getWishlist_title());//제목
			pstmt.setString(5, wishlistbean.getWishlist_content());//내용
			pstmt.setString(6, "");//답변글은 첨부파일 없음
			
			pstmt.setInt(7, re_ref);	//글 그룹번호
			pstmt.setInt(8, re_lev);	//답변글 위치번호
			pstmt.setInt(9, re_seq);	//답변글 레벨
			pstmt.setInt(10, 0);		//추천수
			pstmt.setInt(11, 0);		//조회수
			
			
			pstmt.executeUpdate();
			
			rs.close(); pstmt.close(); con.close();	
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/*추천수 증가(Ajax)*/
	public int addWishlistGood(int num){
		int re = 0;

		System.out.println("num ==== " + num);
		
		try{
			con = ds.getConnection();
			
			sql = "update wishlist set wishlist_good = wishlist_good+1 " +
					" where wishlist_num = ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, num);  //물음표에 num을 배정한다. 
			
			re = pstmt.executeUpdate();
			
			System.out.println("re ==== " + re);
			
			pstmt.close(); con.close();	
		}catch(Exception e){
			e.printStackTrace();
		}
		return re; //자바빈 참조 변수
	}

	/*희망 신청 검색 리스트*/
	public List<WishlistBean> getwishlistSearchList(int page, int limit, String searchWish) {
		
		List<WishlistBean> wishlistList = new ArrayList<WishlistBean>();
		
		int startrow = (page-1)*10+1;		//페이지 시작번호
		int endrow = page*limit; 			//페이지 끝번호
		
		System.out.println("=========== " + searchWish);
		
		try{
			con=ds.getConnection();
			
			sql ="select * from (select wishlist.*, rownum as rnum "
					 + " from (select * from wishlist order by wishlist_re_ref desc, wishlist_re_seq asc) wishlist)" 
					 + " where rnum >=? and rnum<=? " 
					 + " and wishlist_title like ?"; 
			//자료실 그룹번호(wishlist_re_ref)는 내림차순으로 정렬(최근에 작성 내용 위로)
			//답변글(wishlist_re_seq)은 그룹번호의 아래쪽으로 정렬되게 한다. 
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			pstmt.setString(3, "%" + searchWish + "%");
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				WishlistBean wishlistbean = new WishlistBean();
				
				wishlistbean.setWishlist_num(rs.getInt("wishlist_num"));				//글번호
				wishlistbean.setWishlist_id(rs.getString("wishlist_id"));				//글쓴이(아이디)
				wishlistbean.setWishlist_title(rs.getString("wishlist_title"));			//글제목
				wishlistbean.setWishlist_content(rs.getString("wishlist_content"));		//글내용
				wishlistbean.setWishlist_file(rs.getString("wishlist_file"));			//첨부파일
				
				wishlistbean.setWishlist_re_ref(rs.getInt("wishlist_re_ref"));			//글 그룹 번호
				wishlistbean.setWishlist_re_lev(rs.getInt("wishlist_re_lev"));		
				wishlistbean.setWishlist_re_seq(rs.getInt("wishlist_re_seq"));		
				wishlistbean.setWishlist_readcont(rs.getInt("wishlist_readcont"));		//조회수
				
				wishlistbean.setWishlist_good(rs.getInt("wishlist_good"));		//추천수
				
				wishlistbean.setWishlist_date(rs.getString("wishlist_date").substring(0,10));	//등록일
				
				wishlistList.add(wishlistbean);	
			}
			rs.close(); pstmt.close(); con.close();	
		}catch(Exception e){
			e.printStackTrace();
		}
		return wishlistList;
	}
	

}
