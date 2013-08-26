drop table client;

-- admin 테이블 생성
select * from client;

create table client(
	client_num int not null,					/*연번*/
	client_id varchar2(20) primary key,			/*고객 아이디*/
	client_pass varchar2(20) not null,			/*고객 비밀번호*/
	client_name varchar2(30) not null,			/*이름*/
	client_zip1 varchar2(3) not null,			/*우편번호1*/
	client_zip2 varchar2(3) not null,			/*우편번호2*/
	client_addr1 varchar2(100) not null,		/*주소1*/
	client_addr2 varchar2(80) not null,			/*주소2*/
	client_tel varchar2(80) not null,			/*연락처*/
	client_email varchar2(100) not null,		/*이메일*/
	client_regdate date,						/*등록일*/
	client_rank varchar2(3) default '1',		/*등급 1: 일반회원, 2: 특별회원, 3:우수회원*/
	client_mileage int default 0,				/*마일리지*/
	client_state int,    			 			/*가입회원 : 1, 탈퇴회원 : 2*/
	client_delcont varchar2(1000),   			/*탈퇴사유*/
	client_deldate date							/*탈퇴일*/
);

create sequence client_seq
	increment by 1 start with 1 nocache;

	
/*멤버 삽입*/
insert into client(client_num,client_id,client_pass,client_name,client_zip1,client_zip2,client_addr1,client_addr2,client_tel,client_email,client_regdate,client_rank,client_mileage,client_state)
	values(client_seq.nextval,'fisrt01','1234','고객일','135','806','서울 강남구 개포1동 경남아파트','1002동 805호','010-3265-6555','rhror01@gmail.com',sysdate,default,default,1);
insert into client(client_num,client_id,client_pass,client_name,client_zip1,client_zip2,client_addr1,client_addr2,client_tel,client_email,client_regdate,client_rank,client_mileage,client_state)
	values(client_seq.nextval,'second02','1234','고객이','135','806','서울 강남구 개포1동 경남아파트','1002동 805호','010-2222-5555','rhror02@gmail.com',sysdate,default,default,1);
insert into client(client_num,client_id,client_pass,client_name,client_zip1,client_zip2,client_addr1,client_addr2,client_tel,client_email,client_regdate,client_rank,client_mileage,client_state)
	values(client_seq.nextval,'third03','1234','고객삼','135','806','서울 강남구 개포1동 경남아파트','1002동 805호','010-5555-4455','rhror03@gmail.com',sysdate,'2',1000,1);	
insert into client(client_num,client_id,client_pass,client_name,client_zip1,client_zip2,client_addr1,client_addr2,client_tel,client_email,client_regdate,client_rank,client_mileage,client_state)
	values(client_seq.nextval,'fourth04','1234','고객사','135','806','서울 강남구 개포1동 경남아파트','1002동 805호','010-3265-6555','rhror01@gmail.com',sysdate,default,default,1);
insert into client(client_num,client_id,client_pass,client_name,client_zip1,client_zip2,client_addr1,client_addr2,client_tel,client_email,client_regdate,client_rank,client_mileage,client_state)
	values(client_seq.nextval,'fifth05','1234','고객오','135','806','서울 강남구 개포1동 경남아파트','1002동 805호','010-2222-5555','rhror02@gmail.com',sysdate,'2',1500,1);
insert into client(client_num,client_id,client_pass,client_name,client_zip1,client_zip2,client_addr1,client_addr2,client_tel,client_email,client_regdate,client_rank,client_mileage,client_state)
	values(client_seq.nextval,'sixth06','1234','고객육','135','806','서울 강남구 개포1동 경남아파트','1002동 805호','010-5555-4455','rhror03@gmail.com',sysdate,'2',900,1);	
