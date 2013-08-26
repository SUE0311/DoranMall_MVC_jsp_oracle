drop table admin;

-- admin 테이블 생성
select * from admin;

create table admin(
	admin_num int,				/*연번*/
	admin_empNo varchar2(50) not null primary key,		/*사원 번호*/
	admin_name varchar2(30) not null,		/*이름*/
	admin_id varchar2(30) not null,			/*아이디*/
	admin_pass varchar2(50) not null,		/*비밀번호*/
	admin_dept varchar2(50) not null,		/*부서명*/
	admin_pos varchar2(50) not null,		/*직위명*/
	admin_tel varchar2(80) default null,	/*연락처*/
	admin_auth varchar2(10) default '1',	/*권한 1: 읽기만 , 2: 쓰기,수정가능, 3: 삭제까지 가능*/
	admin_state int,						/*가입 상태 1:가입, 2: 탈퇴*/
	admin_regdate date,			 			/*등록 날짜*/
	admin_deldate date						/*탈퇴(삭제) 날짜*/
);

create sequence admin_seq
	increment by 1 start with 1 nocache;
	
delete from admin where admin_id = 'admin';
delete from admin where admin_id = 'aaaaa';
delete from admin where admin_id = 'bbbbb';
delete from admin where admin_id = 'ccccc';
delete from admin where admin_id = 'ddddd';
	
/*멤버 삽입*/
insert into admin(admin_num,admin_empNo,admin_name,admin_id,admin_pass,admin_dept,admin_pos,admin_tel,admin_auth,admin_state,admin_regdate)
	values(admin_seq.nextval,'DR20130015','나대표','admin03','8520','전략기획실','대표','010-2112-2233','3',1,sysdate);
insert into admin(admin_num,admin_empNo,admin_name,admin_id,admin_pass,admin_dept,admin_pos,admin_tel,admin_auth,admin_state,admin_regdate)
	values(admin_seq.nextval,'DR20130020','이부장','qqqqq','0000','홍보팀','부장','010-5122-5443','2',1,sysdate);	
insert into admin(admin_num,admin_empNo,admin_name,admin_id,admin_pass,admin_dept,admin_pos,admin_tel,admin_auth,admin_state,admin_regdate)
	values(admin_seq.nextval,'DR20130021','김이사','wwwww','0000','영업팀','이사','010-6325-4852','2',1,sysdate);	
insert into admin(admin_num,admin_empNo,admin_name,admin_id,admin_pass,admin_dept,admin_pos,admin_tel,admin_auth,admin_state,admin_regdate)
	values(admin_seq.nextval,'DR20130023','유재석','eeeee','0000','영업팀','대리','010-2211-3551',default,1,sysdate);	
insert into admin(admin_num,admin_empNo,admin_name,admin_id,admin_pass,admin_dept,admin_pos,admin_tel,admin_auth,admin_state,admin_regdate)
	values(admin_seq.nextval,'DR20130024','정중앙','rrrrr','0000','기획팀','사원','010-6244-2185',default,1,sysdate);	