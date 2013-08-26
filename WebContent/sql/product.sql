drop table product;

select * from product order by product_code desc;

create table product(
	product_num int not null,						/*��ǰ ����*/	
	product_code varchar2(50) primary key, 			/*��ǰ �ڵ��ȣ*/
	product_name varchar2(50) not null,				/*��ǰ �̸�*/
	product_price int not null,						/*��ǰ ����*/
	product_number int not null,					/*��ǰ ����*/
	product_company varchar2(50) not null,			/*��ǰ ���� ȸ��*/	
	product_summary varchar2(500) not null,			/*��ǰ ���*/
	product_description varchar2(100) not null,		/*��ǰ ����*/
	product_mainImg varchar2(100) not null,			/*��ǰ ���� �̹���*/	
	product_thum01 varchar2(100) default null,		/*��ǰ ���� �̹���1*/
	product_thum02 varchar2(100) default null,		/*��ǰ ���� �̹���2*/
	product_thum03 varchar2(100) default null,		/*��ǰ ���� �̹���3*/
	product_video varchar2(100) default null,		/*��ǰ ����*/
	product_opt01 varchar2(200) default null,		/*��ǰ �ɼ�1*/
	product_opt01_1 varchar2(100) default null,		/*��ǰ �ɼ�1-1*/
	product_opt01_2 varchar2(100) default null,		/*��ǰ �ɼ�1-2*/	
	product_opt01_3 varchar2(100) default null,		/*��ǰ �ɼ�1-3*/
	product_opt01_4 varchar2(100) default null,		/*��ǰ �ɼ�1-4*/
	product_opt01_5 varchar2(100) default null,		/*��ǰ �ɼ�1-5*/
	product_opt02 varchar2(200) default null,		/*��ǰ �ɼ�2*/
	product_opt02_1 varchar2(100) default null,		/*��ǰ �ɼ�2-1*/
	product_opt02_2 varchar2(100) default null,		/*��ǰ �ɼ�2-2*/
	product_opt02_3 varchar2(100) default null,		/*��ǰ �ɼ�2-3*/
	product_opt02_4 varchar2(100) default null,		/*��ǰ �ɼ�2-4*/
	product_opt02_5 varchar2(100) default null,		/*��ǰ �ɼ�2-5*/
	product_startDate varchar2(100) not null,		/*��ǰ ���� ��¥*/
	product_endDate varchar2(100) not null,			/*��ǰ ���� ��¥*/
	product_post int not null,						/*��ǰ �Խ� ȸ��*/
	product_state int not null,						/*��ǰ �Խ� ����*/
	product_shop int not null,						/*��ǰ �� ��ġ*/
	product_date date				 				/*��� ��¥*/
);

create sequence product_seq
	increment by 1 start with 1 nocache;