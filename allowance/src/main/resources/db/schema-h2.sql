-- CASCADE 옵션의 경우 전체 테이블의 완전 초기화를 목적으로 사용
-- FK 제약 조건 등이 테이블 삭제에 영향을 끼치므로 적용하였으나, 실무에서는 사용 X
-- Sample Table
DROP TABLE IF EXISTS Sample;
create table Sample
(
    id          INTEGER auto_increment,
    description varchar2 not null
);

-- User Table
DROP TABLE IF EXISTS Member CASCADE;
create table Member
(
    member_id          LONG auto_increment,
    member_name        varchar2 not null,
    role               varchar2 not null,
    constraint Member_pk
        primary key (member_id)
);

-- Mission Table
DROP TABLE IF EXISTS Mission CASCADE;
create table Mission
(
    mission_id     LONG auto_increment,
    mission_name   VARCHAR2 not null,
    mission_amount INT      not null,
    start_date     DATE     not null,
    end_date       DATE     not null,
    active         BOOL     not null default true,
    constraint Mission_pk
        primary key (mission_id)
);

-- Payment Table
DROP TABLE IF EXISTS Payment CASCADE;
create table Payment
(
    payment_id      LONG auto_increment,
    member_id       LONG not null,
    mission_id      LONG,
    payment_code    VARCHAR2 not null,
    payment_amount  INT      not null,
    status          VARCHAR2    not null,
    payment_date    DATE     not null,
    constraint Payment_pk
        primary key (payment_id),
    constraint Payment_Member_Member_id_fk
        foreign key (member_id) references Member,
    constraint Payment_Mission_Mission_id_fk
        foreign key (mission_id) references Mission
);

-- Payment History Table
DROP TABLE IF EXISTS Payment_History CASCADE;
create table Payment_History
(
    payment_history_id LONG auto_increment,
    payment_id      LONG not null,
    member_id       LONG not null,
    mission_id      LONG,
    payment_code    VARCHAR2 not null,
    payment_amount  INT      not null,
    payment_date    DATE     not null,
    reason          VARCHAR2,
    create_date     DATE     not null default CURRENT_DATE,
    constraint Payment_History_pk
        primary key (payment_history_id),
    constraint Payment_History_Member_Member_id_fk
        foreign key (member_id) references Member,
    constraint Payment_History_Mission_Mission_id_fk
        foreign key (mission_id) references Mission
);