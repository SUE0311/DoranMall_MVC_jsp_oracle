drop table wishlist;

select * from wishlist order by wishlist_num desc;

create table wishlist(
	wishlist_num int primary key,				/*번호*/
	wishlist_id varchar2(50) not null,			/*글쓴이(id)*/
	wishlist_pass varchar2(50) not null,		/*글 비밀번호*/	
	wishlist_title varchar2(100) not null,		/*글 제목*/
	wishlist_content varchar2(1000) not null,	/*글 내용*/
	wishlist_file varchar2(100),				/*첨부파일*/
	wishlist_re_ref int,			 			/*글 그룹 번호*/
	wishlist_re_lev int, 						 /*답변 글 화면 위치 번호*/
	wishlist_re_seq int, 						 /*답변 글 레벨 순서*/
	wishlist_readcont int, 						 /*조회수*/
	wishlist_good int default null,	 			 /*추천수*/
	wishlist_date date				 		     /*등록 날짜*/
);