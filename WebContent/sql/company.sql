drop table company;

-- 회사(contact us) 테이블 생성
select * from company;

create table company(
	com_num int primary key,				/*연번*/
	com_co varchar2(100) not null,			/*회사 이름*/
	com_name varchar2(30) not null,			/*담당자 이름*/
	com_tel varchar2(50) not null,			/*연락처*/
	com_mail varchar2(100) not null,		/*이메일*/
	com_website varchar2(50) not null,		/*웹사이트명*/
	com_product varchar2(80) not null,		/*상품명*/
	com_price varchar2(50) not null,		/*가격*/
	com_cont varchar2(2000) not null,		/*상품설명*/
	com_file1 varchar2(100) null,			/*첨부파일*/
	com_date date			 				/*등록 날짜*/
);

-- 웹사이트 널 값으로 변경
alter table company modify (com_website varchar2(50) default 'value' null);
