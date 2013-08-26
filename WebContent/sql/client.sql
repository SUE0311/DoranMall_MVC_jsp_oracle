drop table client;

-- admin ���̺� ����
select * from client;

create table client(
	client_num int not null,					/*����*/
	client_id varchar2(20) primary key,			/*�� ���̵�*/
	client_pass varchar2(20) not null,			/*�� ��й�ȣ*/
	client_name varchar2(30) not null,			/*�̸�*/
	client_zip1 varchar2(3) not null,			/*�����ȣ1*/
	client_zip2 varchar2(3) not null,			/*�����ȣ2*/
	client_addr1 varchar2(100) not null,		/*�ּ�1*/
	client_addr2 varchar2(80) not null,			/*�ּ�2*/
	client_tel varchar2(80) not null,			/*����ó*/
	client_email varchar2(100) not null,		/*�̸���*/
	client_regdate date,						/*�����*/
	client_rank varchar2(3) default '1',		/*��� 1: �Ϲ�ȸ��, 2: Ư��ȸ��, 3:���ȸ��*/
	client_mileage int default 0,				/*���ϸ���*/
	client_state int,    			 			/*����ȸ�� : 1, Ż��ȸ�� : 2*/
	client_delcont varchar2(1000),   			/*Ż�����*/
	client_deldate date							/*Ż����*/
);

create sequence client_seq
	increment by 1 start with 1 nocache;

	
/*��� ����*/
insert into client(client_num,client_id,client_pass,client_name,client_zip1,client_zip2,client_addr1,client_addr2,client_tel,client_email,client_regdate,client_rank,client_mileage,client_state)
	values(client_seq.nextval,'fisrt01','1234','����','135','806','���� ������ ����1�� �泲����Ʈ','1002�� 805ȣ','010-3265-6555','rhror01@gmail.com',sysdate,default,default,1);
insert into client(client_num,client_id,client_pass,client_name,client_zip1,client_zip2,client_addr1,client_addr2,client_tel,client_email,client_regdate,client_rank,client_mileage,client_state)
	values(client_seq.nextval,'second02','1234','����','135','806','���� ������ ����1�� �泲����Ʈ','1002�� 805ȣ','010-2222-5555','rhror02@gmail.com',sysdate,default,default,1);
insert into client(client_num,client_id,client_pass,client_name,client_zip1,client_zip2,client_addr1,client_addr2,client_tel,client_email,client_regdate,client_rank,client_mileage,client_state)
	values(client_seq.nextval,'third03','1234','����','135','806','���� ������ ����1�� �泲����Ʈ','1002�� 805ȣ','010-5555-4455','rhror03@gmail.com',sysdate,'2',1000,1);	
insert into client(client_num,client_id,client_pass,client_name,client_zip1,client_zip2,client_addr1,client_addr2,client_tel,client_email,client_regdate,client_rank,client_mileage,client_state)
	values(client_seq.nextval,'fourth04','1234','����','135','806','���� ������ ����1�� �泲����Ʈ','1002�� 805ȣ','010-3265-6555','rhror01@gmail.com',sysdate,default,default,1);
insert into client(client_num,client_id,client_pass,client_name,client_zip1,client_zip2,client_addr1,client_addr2,client_tel,client_email,client_regdate,client_rank,client_mileage,client_state)
	values(client_seq.nextval,'fifth05','1234','����','135','806','���� ������ ����1�� �泲����Ʈ','1002�� 805ȣ','010-2222-5555','rhror02@gmail.com',sysdate,'2',1500,1);
insert into client(client_num,client_id,client_pass,client_name,client_zip1,client_zip2,client_addr1,client_addr2,client_tel,client_email,client_regdate,client_rank,client_mileage,client_state)
	values(client_seq.nextval,'sixth06','1234','����','135','806','���� ������ ����1�� �泲����Ʈ','1002�� 805ȣ','010-5555-4455','rhror03@gmail.com',sysdate,'2',900,1);	
