--zipcode.sql
--우편번호 생성 쿼리문
drop table zipcode

select * from zipcode;

create table zipcode(
	no varchar2(5) primary key,  /*번호*/
	zipcode varchar2(7),		/*우편번호*/
	sido varchar2(10),			/*시도*/
	gugun varchar2(20),			/*구군*/
	dong varchar2(50),			/*동*/
	bunji varchar2(50)			/*번지*/	
);

select * from zipcode;
