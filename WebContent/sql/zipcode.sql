--zipcode.sql
--�����ȣ ���� ������
drop table zipcode

select * from zipcode;

create table zipcode(
	no varchar2(5) primary key,  /*��ȣ*/
	zipcode varchar2(7),		/*�����ȣ*/
	sido varchar2(10),			/*�õ�*/
	gugun varchar2(20),			/*����*/
	dong varchar2(50),			/*��*/
	bunji varchar2(50)			/*����*/	
);

select * from zipcode;
