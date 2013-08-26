drop table guide;

-- ���� ���̵�(guide) ���̺� 
select * from guide;

-- ���� ���̵� ���̺� ����
CREATE TABLE guide (
  guide_num int primary key, 					/*����*/
  guide_s_date varchar2(50) DEFAULT NULL,		/*�Խ� ���� ��¥*/
  guide_e_date varchar2(50) DEFAULT NULL,		/*�Խ� �� ��¥*/
  guide_post int DEFAULT NULL,					/*�Խ� ȸ��*/
  guide_state int NOT NULL,						/*�Խ� ����*/
  guide_product varchar2(50) NOT NULL,			/*��ǰ��(����)*/
  guide_q1 varchar2(500) NOT NULL,				/*1�� ����*/
  guide_q1_a1 varchar2(100) NOT NULL,			/*1�� ���� 1�� ����*/
  guide_q1_a2 varchar2(100) NOT NULL,			/*1�� ���� 2�� ����*/
  guide_q1_a3 varchar2(100) NOT NULL,			/*1�� ���� 3�� ����*/
  guide_q2 varchar2(500) NOT NULL,				/*2�� ����*/
  guide_q2_a1 varchar2(100) NOT NULL,			/*2�� ���� 1�� ����*/
  guide_q2_a2 varchar2(100) NOT NULL,			/*2�� ���� 2�� ����*/
  guide_q2_a3 varchar2(100) NOT NULL,			/*2�� ���� 3�� ����*/
  guide_q3 varchar2(500) NOT NULL,				/*3�� ����*/
  guide_q3_a1 varchar2(100) NOT NULL,			/*3�� ���� 1�� ����*/
  guide_q3_a2 varchar2(100) NOT NULL,			/*3�� ���� 2�� ����*/
  guide_q3_a3 varchar2(100) NOT NULL,			/*3�� ���� 3�� ����*/
  guide_q4 varchar2(500) NOT NULL,			/*4�� ����*/
  guide_q4_a1 varchar2(100) NOT NULL,		/*4�� ���� 1�� ����*/
  guide_q4_a2 varchar2(100) NOT NULL,		/*4�� ���� 2�� ����*/
  guide_q4_a3 varchar2(100) NOT NULL,		/*4�� ���� 3�� ����*/
  guide_reg_date date
);

create sequence guide_seq
	increment by 1 start with 1 nocache;
	
-- ȸ��(post) �÷� �߰�	
alter table guide add(post int);	
	
	


