drop table admin;

-- admin ���̺� ����
select * from admin;

create table admin(
	admin_num int,				/*����*/
	admin_empNo varchar2(50) not null primary key,		/*��� ��ȣ*/
	admin_name varchar2(30) not null,		/*�̸�*/
	admin_id varchar2(30) not null,			/*���̵�*/
	admin_pass varchar2(50) not null,		/*��й�ȣ*/
	admin_dept varchar2(50) not null,		/*�μ���*/
	admin_pos varchar2(50) not null,		/*������*/
	admin_tel varchar2(80) default null,	/*����ó*/
	admin_auth varchar2(10) default '1',	/*���� 1: �б⸸ , 2: ����,��������, 3: �������� ����*/
	admin_state int,						/*���� ���� 1:����, 2: Ż��*/
	admin_regdate date,			 			/*��� ��¥*/
	admin_deldate date						/*Ż��(����) ��¥*/
);

create sequence admin_seq
	increment by 1 start with 1 nocache;
	
delete from admin where admin_id = 'admin';
delete from admin where admin_id = 'aaaaa';
delete from admin where admin_id = 'bbbbb';
delete from admin where admin_id = 'ccccc';
delete from admin where admin_id = 'ddddd';
	
/*��� ����*/
insert into admin(admin_num,admin_empNo,admin_name,admin_id,admin_pass,admin_dept,admin_pos,admin_tel,admin_auth,admin_state,admin_regdate)
	values(admin_seq.nextval,'DR20130015','����ǥ','admin03','8520','������ȹ��','��ǥ','010-2112-2233','3',1,sysdate);
insert into admin(admin_num,admin_empNo,admin_name,admin_id,admin_pass,admin_dept,admin_pos,admin_tel,admin_auth,admin_state,admin_regdate)
	values(admin_seq.nextval,'DR20130020','�̺���','qqqqq','0000','ȫ����','����','010-5122-5443','2',1,sysdate);	
insert into admin(admin_num,admin_empNo,admin_name,admin_id,admin_pass,admin_dept,admin_pos,admin_tel,admin_auth,admin_state,admin_regdate)
	values(admin_seq.nextval,'DR20130021','���̻�','wwwww','0000','������','�̻�','010-6325-4852','2',1,sysdate);	
insert into admin(admin_num,admin_empNo,admin_name,admin_id,admin_pass,admin_dept,admin_pos,admin_tel,admin_auth,admin_state,admin_regdate)
	values(admin_seq.nextval,'DR20130023','���缮','eeeee','0000','������','�븮','010-2211-3551',default,1,sysdate);	
insert into admin(admin_num,admin_empNo,admin_name,admin_id,admin_pass,admin_dept,admin_pos,admin_tel,admin_auth,admin_state,admin_regdate)
	values(admin_seq.nextval,'DR20130024','���߾�','rrrrr','0000','��ȹ��','���','010-6244-2185',default,1,sysdate);	