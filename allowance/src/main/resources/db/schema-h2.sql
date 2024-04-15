-- Sample Table
DROP TABLE IF EXISTS Sample;
create table Sample
(
    id          INTEGER auto_increment,
    description varchar2 not null
);

-- Mission Table
DROP TABLE IF EXISTS Mission;
create table Mission
(
    mission_id     LONG auto_increment,
    mission_name   VARCHAR2 not null,
    mission_amount INT      not null,
    start_date     DATE     not null,
    end_date       DATE     not null,
    active         TINYINT  not null default 1,
    constraint Mission_pk
        primary key (mission_id)
);

