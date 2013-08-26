drop table product;

select * from product order by product_code desc;

create table product(
	product_num int not null,						/*상품 가격*/	
	product_code varchar2(50) primary key, 			/*상품 코드번호*/
	product_name varchar2(50) not null,				/*상품 이름*/
	product_price int not null,						/*상품 가격*/
	product_number int not null,					/*상품 수량*/
	product_company varchar2(50) not null,			/*상품 제조 회사*/	
	product_summary varchar2(500) not null,			/*상품 요약*/
	product_description varchar2(100) not null,		/*상품 설명*/
	product_mainImg varchar2(100) not null,			/*상품 메인 이미지*/	
	product_thum01 varchar2(100) default null,		/*상품 서브 이미지1*/
	product_thum02 varchar2(100) default null,		/*상품 서브 이미지2*/
	product_thum03 varchar2(100) default null,		/*상품 서브 이미지3*/
	product_video varchar2(100) default null,		/*상품 비디오*/
	product_opt01 varchar2(200) default null,		/*상품 옵션1*/
	product_opt01_1 varchar2(100) default null,		/*상품 옵션1-1*/
	product_opt01_2 varchar2(100) default null,		/*상품 옵션1-2*/	
	product_opt01_3 varchar2(100) default null,		/*상품 옵션1-3*/
	product_opt01_4 varchar2(100) default null,		/*상품 옵션1-4*/
	product_opt01_5 varchar2(100) default null,		/*상품 옵션1-5*/
	product_opt02 varchar2(200) default null,		/*상품 옵션2*/
	product_opt02_1 varchar2(100) default null,		/*상품 옵션2-1*/
	product_opt02_2 varchar2(100) default null,		/*상품 옵션2-2*/
	product_opt02_3 varchar2(100) default null,		/*상품 옵션2-3*/
	product_opt02_4 varchar2(100) default null,		/*상품 옵션2-4*/
	product_opt02_5 varchar2(100) default null,		/*상품 옵션2-5*/
	product_startDate varchar2(100) not null,		/*상품 시작 날짜*/
	product_endDate varchar2(100) not null,			/*상품 마감 날짜*/
	product_post int not null,						/*상품 게시 회차*/
	product_state int not null,						/*상품 게시 상태*/
	product_shop int not null,						/*상품 숍 위치*/
	product_date date				 				/*등록 날짜*/
);

create sequence product_seq
	increment by 1 start with 1 nocache;