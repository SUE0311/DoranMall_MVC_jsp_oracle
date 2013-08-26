drop table wishlist;

select * from wishlist order by wishlist_num desc;

create table wishlist(
	wishlist_num int primary key,				/*��ȣ*/
	wishlist_id varchar2(50) not null,			/*�۾���(id)*/
	wishlist_pass varchar2(50) not null,		/*�� ��й�ȣ*/	
	wishlist_title varchar2(100) not null,		/*�� ����*/
	wishlist_content varchar2(1000) not null,	/*�� ����*/
	wishlist_file varchar2(100),				/*÷������*/
	wishlist_re_ref int,			 			/*�� �׷� ��ȣ*/
	wishlist_re_lev int, 						 /*�亯 �� ȭ�� ��ġ ��ȣ*/
	wishlist_re_seq int, 						 /*�亯 �� ���� ����*/
	wishlist_readcont int, 						 /*��ȸ��*/
	wishlist_good int default null,	 			 /*��õ��*/
	wishlist_date date				 		     /*��� ��¥*/
);