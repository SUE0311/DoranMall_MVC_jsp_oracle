drop table company;

-- ȸ��(contact us) ���̺� ����
select * from company;

create table company(
	com_num int primary key,				/*����*/
	com_co varchar2(100) not null,			/*ȸ�� �̸�*/
	com_name varchar2(30) not null,			/*����� �̸�*/
	com_tel varchar2(50) not null,			/*����ó*/
	com_mail varchar2(100) not null,		/*�̸���*/
	com_website varchar2(50) not null,		/*������Ʈ��*/
	com_product varchar2(80) not null,		/*��ǰ��*/
	com_price varchar2(50) not null,		/*����*/
	com_cont varchar2(2000) not null,		/*��ǰ����*/
	com_file1 varchar2(100) null,			/*÷������*/
	com_date date			 				/*��� ��¥*/
);

-- ������Ʈ �� ������ ����
alter table company modify (com_website varchar2(50) default 'value' null);
