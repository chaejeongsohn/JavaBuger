drop table USERMEMBER;
--회원 테이블
CREATE TABLE USERMEMBER (
	USER_ID	VARCHAR2(10)	primary key, --사용자 ID
	USER_PW	VARCHAR2(15)	not null, --비밀번호
	USER_NAME	VARCHAR2(15), --이름
	USER_PHONE	number(11) not null, --핸드폰번호
	USER_BIRTHDAY	number(8), --생년월일
  USER_JOINDATE date --가입날짜
);

insert into usermember values('cat01','catcat11','김고양',01011112222,19910101,SYSDATE);
insert into usermember values('dog02','dogdog02','박아지',01022223333,19920202,SYSDATE);
insert into usermember values('cow03','cowcow33','한소소',01033334444,19930303,SYSDATE);

select * from usermember;

drop table COUPON;
--쿠폰 테이블
CREATE TABLE COUPON (
	COUPON_NO	NUMBER	primary key,
	COUPON_DETAIL	VARCHAR2(100),
	COUPON_DC_RATE	NUMBER(2),
	COUPON_EXP	NUMBER(8)
);

insert into coupon values(1010,'10% 할인',10,20220401);
insert into coupon values(2020,'20% 할인',20,20220501);

select * from coupon;



drop table USERCOUPON;


CREATE TABLE USERCOUPON (
	USER_COUPON_NO	NUMBER	primary key,
	USER_ID	VARCHAR2(10)	NOT NULL references usermember(user_id),
	COUPON_NO	NUMBER	NOT NULL references coupon(coupon_no),
	COUPON_AMOUNT	NUMBER
);

-- USER_COUPON_NO 시퀀스 생성
drop sequence USER_COUPON_NO_SEQ;
create sequence USER_COUPON_NO_SEQ NOCACHE;

insert into USERCOUPON(USER_COUPON_NO, USER_ID, COUPON_NO, COUPON_AMOUNT)
values (USER_COUPON_NO_SEQ.NEXTVAL,'cat01',1010,1);

insert into USERCOUPON(USER_COUPON_NO, USER_ID, COUPON_NO, COUPON_AMOUNT)
values (USER_COUPON_NO_SEQ.NEXTVAL,'dog02',2020,3);

insert into USERCOUPON(USER_COUPON_NO, USER_ID, COUPON_NO, COUPON_AMOUNT)
values (USER_COUPON_NO_SEQ.NEXTVAL,'dog02',1010,1);

select * from usercoupon;

--USER_COUPON_NO_SEQ 의 현재값 확인
select USER_COUPON_NO_SEQ.currval from dual;



drop table productcategory;
-- 제품카테고리 테이블
create table productcategory(
  category_no varchar2(1) primary key,
  category_name varchar2(10) not null
);

insert into productcategory values('A','Set');
insert into productcategory values ('B','Burger');
insert into productcategory values ('C','Side');
insert into productcategory values ('D','Drink');

select * from productcategory;


drop table product;
-- 제품 테이블
CREATE TABLE Product (
	PRD_NO	NUMBER	primary key,
	CATEGORY_NO	varchar2(1)	NOT NULL references productcategory(category_no),
	PRD_NAME	VARCHAR2(30),
	PRD_PRICE	NUMBER not null,
	PRD_DETAIL	VARCHAR2(100)
);

-- PRD_NO 시퀀스 생성
drop sequence PRD_NO_SEQ;
create sequence PRD_NO_SEQ NOCACHE;


insert into product
values(PRD_NO_SEQ.NEXTVAL,'A','불고기버거세트M',5000,'불고기버거M, 콜라M, 감자튀김M');
insert into product
values(PRD_NO_SEQ.NEXTVAL,'A','새우버거세트M',6000,'새우버거M, 콜라M, 감자튀김M');
insert into product
values(PRD_NO_SEQ.NEXTVAL,'B','불고기버거M',3500,'달달함, M사이즈');
insert into product
values(PRD_NO_SEQ.NEXTVAL,'B','불고기버거L',4000,'달달함, L사이즈');
insert into product
values(PRD_NO_SEQ.NEXTVAL,'B','새우버거M',4500,'탱글함, M사이즈');
insert into product
values(PRD_NO_SEQ.NEXTVAL,'C','감자튀김M',1000,'바삭함,M사이즈');
insert into product
values(PRD_NO_SEQ.NEXTVAL,'C','감자튀김L',1500,'바삭함,L사이즈');
insert into product
values(PRD_NO_SEQ.NEXTVAL,'D','콜라M',1000,'코카콜라');
insert into product
values(PRD_NO_SEQ.NEXTVAL,'D','콜라L',1000,'칠성사이다');

--PRD_NO_SEQ 의 현재값 확인
select PRD_NO_SEQ.currval from dual;

select * from product;


drop table productOption;
--옵션 테이블
create table productOption(
  OPT_NO	Number	primary key,
  CATEGORY_NO	VARCHAR2(1)	NOT NULL references productcategory(category_no),
	OPT_NAME	VARCHAR2(20),
	OPT_PRICE	NUMBER NOT NULL
);

-- OPT_NO 시퀀스 생성
drop sequence OPT_NO_SEQ;
create sequence OPT_NO_SEQ NOCACHE;

insert into productOption
values(OPT_NO_SEQ.NEXTVAL,'B','피클제거',0);

insert into productOption
values(OPT_NO_SEQ.NEXTVAL,'B','피클추가',100);

insert into productOption
values(OPT_NO_SEQ.NEXTVAL,'B','치즈제거',0);

insert into productOption
values(OPT_NO_SEQ.NEXTVAL,'B','치즈추가',100);

insert into productOption
values(OPT_NO_SEQ.NEXTVAL,'D','머스타드추가',200);

insert into productOption
values(OPT_NO_SEQ.NEXTVAL,'D','케찹추가',100);

select * from productOption;



drop table Payment;
--구매내역 테이블
create table payment(
	PAY_NO	NUMBER	primary key,
	USER_ID	VARCHAR2(10)	NOT NULL references usermember(user_id),
	PAY_DATE	DATE,
	PAY_METHOD	NUMBER,
	PAY_PRICE	NUMBER,
	USER_COUPON_NO	NUMBER references usercoupon(USER_COUPON_NO)
);

-- PAY_NO 시퀀스 생성
drop sequence PAY_NO_SEQ;
create sequence PAY_NO_SEQ NOCACHE;

insert into payment
values(PAY_NO_SEQ.NEXTVAL,'cat01','2022-03-01',1,6000,null);

insert into payment
values(PAY_NO_SEQ.NEXTVAL,'cow03','2022-03-01',2,6000,null);

insert into payment
values(PAY_NO_SEQ.NEXTVAL,'dog02','2022-01-10',1,4500,1);

insert into payment
values(PAY_NO_SEQ.NEXTVAL,'dog02','2022-02-26',1,1000,null);

insert into payment
values(PAY_NO_SEQ.NEXTVAL,'dog02','2022-03-05',1,1200,null);

insert into payment
values(PAY_NO_SEQ.NEXTVAL,'cat01','2022-03-08',1,3600,null);

insert into payment
values(PAY_NO_SEQ.NEXTVAL,'cow03','2022-03-01',1,11500,null);

insert into payment
values(PAY_NO_SEQ.NEXTVAL,'dog02','2022-03-10',1,5000,null);

insert into payment
values(PAY_NO_SEQ.NEXTVAL,'dog02','2022-03-12',1,4000,2);

select * from payment



drop table orderoption;
--옵션주문 내역 테이블
create table orderoption(
  ORDER_OPT_NO	NUMBER	primary key,
	ORDER_PRD_NO	NUMBER	NOT NULL references product(PRD_NO),
	OPT_NO	NUMBER references productOption(OPT_NO)
);

-- PRD_NO 시퀀스 생성
drop sequence ORDER_OPT_NO_SEQ;
create sequence ORDER_OPT_NO_SEQ;

insert into orderoption
values(ORDER_OPT_NO_SEQ.NEXTVAL,1,null);

insert into orderoption(ORDER_OPT_NO,ORDER_PRD_NO)
values(ORDER_OPT_NO_SEQ.NEXTVAL,2);


insert into orderoption
values(ORDER_OPT_NO_SEQ.NEXTVAL,5,5);

insert into orderoption
values(ORDER_OPT_NO_SEQ.NEXTVAL,6,1);

insert into orderoption
values(ORDER_OPT_NO_SEQ.NEXTVAL,6,4);


select * from orderoption

commit;
