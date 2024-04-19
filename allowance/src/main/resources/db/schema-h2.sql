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
    error           TEXT,
    create_date     DATE     not null default CURRENT_DATE,
    constraint Payment_History_pk
        primary key (payment_history_id),
    constraint Payment_History_Member_Member_id_fk
        foreign key (member_id) references Member,
    constraint Payment_History_Mission_Mission_id_fk
        foreign key (mission_id) references Mission
);

-- Error Log Table
DROP TABLE IF EXISTS Error_Log CASCADE;
create table Error_Log
(
    id LONG auto_increment,
    error TEXT,
    url VARCHAR2,
    create_date     DATE     not null default CURRENT_DATE,
        constraint Error_Log_pk
            primary key (id)
);


--  Batch

DROP TABLE IF EXISTS BATCH_JOB_INSTANCE CASCADE;
CREATE TABLE BATCH_JOB_INSTANCE  (
                                     JOB_INSTANCE_ID BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY ,
                                     VERSION BIGINT ,
                                     JOB_NAME VARCHAR(100) NOT NULL,
                                     JOB_KEY VARCHAR(32) NOT NULL,
                                     constraint JOB_INST_UN unique (JOB_NAME, JOB_KEY)
) ;

DROP TABLE IF EXISTS BATCH_JOB_EXECUTION CASCADE;
CREATE TABLE BATCH_JOB_EXECUTION  (
                                      JOB_EXECUTION_ID BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY ,
                                      VERSION BIGINT  ,
                                      JOB_INSTANCE_ID BIGINT NOT NULL,
                                      CREATE_TIME TIMESTAMP(9) NOT NULL,
                                      START_TIME TIMESTAMP(9) DEFAULT NULL ,
                                      END_TIME TIMESTAMP(9) DEFAULT NULL ,
                                      STATUS VARCHAR(10) ,
                                      EXIT_CODE VARCHAR(2500) ,
                                      EXIT_MESSAGE VARCHAR(2500) ,
                                      LAST_UPDATED TIMESTAMP(9),
                                      constraint JOB_INST_EXEC_FK foreign key (JOB_INSTANCE_ID)
                                          references BATCH_JOB_INSTANCE(JOB_INSTANCE_ID)
) ;

DROP TABLE IF EXISTS BATCH_JOB_EXECUTION_PARAMS CASCADE;
CREATE TABLE BATCH_JOB_EXECUTION_PARAMS  (
                                             JOB_EXECUTION_ID BIGINT NOT NULL ,
                                             PARAMETER_NAME VARCHAR(100) NOT NULL ,
                                             PARAMETER_TYPE VARCHAR(100) NOT NULL ,
                                             PARAMETER_VALUE VARCHAR(2500) ,
                                             IDENTIFYING CHAR(1) NOT NULL ,
                                             constraint JOB_EXEC_PARAMS_FK foreign key (JOB_EXECUTION_ID)
                                                 references BATCH_JOB_EXECUTION(JOB_EXECUTION_ID)
) ;

DROP TABLE IF EXISTS BATCH_STEP_EXECUTION CASCADE;
CREATE TABLE BATCH_STEP_EXECUTION  (
                                       STEP_EXECUTION_ID BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY ,
                                       VERSION BIGINT NOT NULL,
                                       STEP_NAME VARCHAR(100) NOT NULL,
                                       JOB_EXECUTION_ID BIGINT NOT NULL,
                                       CREATE_TIME TIMESTAMP(9) NOT NULL,
                                       START_TIME TIMESTAMP(9) DEFAULT NULL ,
                                       END_TIME TIMESTAMP(9) DEFAULT NULL ,
                                       STATUS VARCHAR(10) ,
                                       COMMIT_COUNT BIGINT ,
                                       READ_COUNT BIGINT ,
                                       FILTER_COUNT BIGINT ,
                                       WRITE_COUNT BIGINT ,
                                       READ_SKIP_COUNT BIGINT ,
                                       WRITE_SKIP_COUNT BIGINT ,
                                       PROCESS_SKIP_COUNT BIGINT ,
                                       ROLLBACK_COUNT BIGINT ,
                                       EXIT_CODE VARCHAR(2500) ,
                                       EXIT_MESSAGE VARCHAR(2500) ,
                                       LAST_UPDATED TIMESTAMP(9),
                                       constraint JOB_EXEC_STEP_FK foreign key (JOB_EXECUTION_ID)
                                           references BATCH_JOB_EXECUTION(JOB_EXECUTION_ID)
) ;

DROP TABLE IF EXISTS BATCH_STEP_EXECUTION_CONTEXT CASCADE;
CREATE TABLE BATCH_STEP_EXECUTION_CONTEXT  (
                                               STEP_EXECUTION_ID BIGINT NOT NULL PRIMARY KEY,
                                               SHORT_CONTEXT VARCHAR(2500) NOT NULL,
                                               SERIALIZED_CONTEXT LONGVARCHAR ,
                                               constraint STEP_EXEC_CTX_FK foreign key (STEP_EXECUTION_ID)
                                                   references BATCH_STEP_EXECUTION(STEP_EXECUTION_ID)
) ;

DROP TABLE IF EXISTS BATCH_JOB_EXECUTION_CONTEXT CASCADE;
CREATE TABLE BATCH_JOB_EXECUTION_CONTEXT  (
                                              JOB_EXECUTION_ID BIGINT NOT NULL PRIMARY KEY,
                                              SHORT_CONTEXT VARCHAR(2500) NOT NULL,
                                              SERIALIZED_CONTEXT LONGVARCHAR ,
                                              constraint JOB_EXEC_CTX_FK foreign key (JOB_EXECUTION_ID)
                                                  references BATCH_JOB_EXECUTION(JOB_EXECUTION_ID)
) ;

DROP SEQUENCE IF EXISTS BATCH_STEP_EXECUTION_SEQ;
CREATE SEQUENCE BATCH_STEP_EXECUTION_SEQ;
DROP SEQUENCE IF EXISTS BATCH_JOB_EXECUTION_SEQ;
CREATE SEQUENCE BATCH_JOB_EXECUTION_SEQ;
DROP SEQUENCE IF EXISTS BATCH_JOB_SEQ;
CREATE SEQUENCE BATCH_JOB_SEQ;
