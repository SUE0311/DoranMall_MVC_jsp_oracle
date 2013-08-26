drop table guide;

-- 쇼핑 가이드(guide) 테이블 
select * from guide;

-- 쇼핑 가이드 테이블 생성
CREATE TABLE guide (
  guide_num int primary key, 					/*연번*/
  guide_s_date varchar2(50) DEFAULT NULL,		/*게시 시작 날짜*/
  guide_e_date varchar2(50) DEFAULT NULL,		/*게시 끝 날짜*/
  guide_post int DEFAULT NULL,					/*게시 회차*/
  guide_state int NOT NULL,						/*게시 상태*/
  guide_product varchar2(50) NOT NULL,			/*제품명(종류)*/
  guide_q1 varchar2(500) NOT NULL,				/*1번 문제*/
  guide_q1_a1 varchar2(100) NOT NULL,			/*1번 문제 1번 보기*/
  guide_q1_a2 varchar2(100) NOT NULL,			/*1번 문제 2번 보기*/
  guide_q1_a3 varchar2(100) NOT NULL,			/*1번 문제 3번 보기*/
  guide_q2 varchar2(500) NOT NULL,				/*2번 문제*/
  guide_q2_a1 varchar2(100) NOT NULL,			/*2번 문제 1번 보기*/
  guide_q2_a2 varchar2(100) NOT NULL,			/*2번 문제 2번 보기*/
  guide_q2_a3 varchar2(100) NOT NULL,			/*2번 문제 3번 보기*/
  guide_q3 varchar2(500) NOT NULL,				/*3번 문제*/
  guide_q3_a1 varchar2(100) NOT NULL,			/*3번 문제 1번 보기*/
  guide_q3_a2 varchar2(100) NOT NULL,			/*3번 문제 2번 보기*/
  guide_q3_a3 varchar2(100) NOT NULL,			/*3번 문제 3번 보기*/
  guide_q4 varchar2(500) NOT NULL,			/*4번 문제*/
  guide_q4_a1 varchar2(100) NOT NULL,		/*4번 문제 1번 보기*/
  guide_q4_a2 varchar2(100) NOT NULL,		/*4번 문제 2번 보기*/
  guide_q4_a3 varchar2(100) NOT NULL,		/*4번 문제 3번 보기*/
  guide_reg_date date
);

create sequence guide_seq
	increment by 1 start with 1 nocache;
	
-- 회차(post) 컬럼 추가	
alter table guide add(post int);	
	
	


