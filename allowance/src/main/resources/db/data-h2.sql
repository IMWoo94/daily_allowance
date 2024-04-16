-- Sample data
INSERT INTO Sample (description)
values ('베베숲 물티슈');
INSERT INTO Sample (description)
values ('여름 토퍼');
INSERT INTO Sample (description)
values ('페이크 삭스');
INSERT INTO Sample (description)
values ('우산');

-- Users data
INSERT INTO Member (member_name)
values ('라이언');
INSERT INTO Member (member_name)
values ('춘식이');
INSERT INTO Member (member_name)
values ('어피치');
INSERT INTO Member (member_name)
values ('프로도');
INSERT INTO Member (member_name)
values ('제이지');
INSERT INTO Member (member_name)
values ('무지');
INSERT INTO Member (member_name)
values ('튜브');
INSERT INTO Member (member_name)
values ('네오');
INSERT INTO Member (member_name)
values ('콘');

-- Mission data
INSERT INTO Mission (mission_name, mission_amount, start_date, end_date)
values ('혜택 좋은 신용카드', 2, '2024-04-01', '2024-06-01');
INSERT INTO Mission (mission_name, mission_amount, start_date, end_date)
values ('Color Blocks 3D(레벨 151 클리어)', 10, '2024-04-01', '2024-06-01');
INSERT INTO Mission (mission_name, mission_amount, start_date, end_date)
values ('무신사', 2, '2024-03-01', '2024-04-01');
INSERT INTO Mission (mission_name, mission_amount, start_date, end_date)
values ('올리브영', 2, '2024-03-01', '2024-04-01');

-- Payment data
-- 미션
INSERT INTO Payment (payment_amount, payment_date, status, member_id, mission_id)
values (2, '2024-04-01', 'ok', 1, 1);
INSERT INTO Payment (payment_amount, payment_date, status, member_id, mission_id)
values (2, '2024-04-02', 'ok', 1, 1);
INSERT INTO Payment (payment_amount, payment_date, status, member_id, mission_id)
values (2, '2024-04-03', 'ok', 1, 1);
INSERT INTO Payment (payment_amount, payment_date, status, member_id, mission_id)
values (10, '2024-04-03', 'ok', 1, 2);
-- 데일리 용돈
INSERT INTO Payment (payment_amount, payment_date, status, member_id)
values (3, '2024-04-03', 'ok', 1);