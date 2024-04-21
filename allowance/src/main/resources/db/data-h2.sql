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
INSERT INTO Member (member_name, role)
values ('라이언', 'USER');
INSERT INTO Member (member_name, role)
values ('춘식이', 'USER');
INSERT INTO Member (member_name, role)
values ('어피치', 'USER');

-- Mission data
INSERT INTO Mission (mission_name, mission_amount, start_date, end_date)
values ('4월의혜택', 100, '2024-01-01', '2024-12-31');
INSERT INTO Mission (mission_name, mission_amount, start_date, end_date)
values ('혜택 좋은 신용카드', 2, '2024-04-01', '2024-06-01');
INSERT INTO Mission (mission_name, mission_amount, start_date, end_date)
values ('Color Blocks 3D(레벨 151 클리어)', 10, '2024-04-01', '2024-06-01');
INSERT INTO Mission (mission_name, mission_amount, start_date, end_date)
values ('무신사', 2, '2024-03-01', '2024-04-01');
INSERT INTO Mission (mission_name, mission_amount, start_date, end_date)
values ('올리브영', 2, '2024-03-01', '2024-04-01');

-- Payment data
INSERT INTO Payment (member_id, mission_id, payment_code, payment_amount, status, payment_date)
values (1, 3, 'MISSION', 10, 'SUCCESS', '2024-04-18');
INSERT INTO Payment (member_id, mission_id, payment_code, payment_amount, status, payment_date)
values (1, 2, 'MISSION', 2, 'SUCCESS', '2024-04-18');
INSERT INTO Payment (member_id, mission_id, payment_code, payment_amount, status, payment_date)
values (1, 1, 'MISSION', 100, 'SUCCESS', '2024-04-18');
INSERT INTO Payment (member_id, mission_id, payment_code, payment_amount, status, payment_date)
values (1, null, 'DAILY', 1, 'SUCCESS', '2024-04-18');
INSERT INTO Payment (member_id, mission_id, payment_code, payment_amount, status, payment_date)
values (1, 4, 'MISSION', 2, 'FAIL', '2024-04-19'); -- 미운영 기간 실패
INSERT INTO Payment (member_id, mission_id, payment_code, payment_amount, status, payment_date)
values (1, 5, 'MISSION', 2, 'FAIL', '2024-04-19');
-- 미운영 기간 실패

-- Payment_History data
INSERT INTO Payment_History (payment_id, member_id, mission_id, payment_code, payment_amount, payment_date, reason,
                             create_date)
values (1, 1, 3, 'MISSION', 10, '2024-04-18', null, '2024-04-18');
INSERT INTO Payment_History (payment_id, member_id, mission_id, payment_code, payment_amount, payment_date, reason,
                             create_date)
values (2, 1, 2, 'MISSION', 2, '2024-04-18', null, '2024-04-18');
INSERT INTO Payment_History (payment_id, member_id, mission_id, payment_code, payment_amount, payment_date, reason,
                             create_date)
values (3, 1, 1, 'MISSION', 100, '2024-04-18', null, '2024-04-18');
INSERT INTO Payment_History (payment_id, member_id, mission_id, payment_code, payment_amount, payment_date, reason,
                             create_date)
values (4, 1, null, 'DAILY', 1, '2024-04-18', null, '2024-04-18');

INSERT INTO Payment_History (payment_id, member_id, mission_id, payment_code, payment_amount, payment_date, reason,
                             create_date)
values (5, 1, 4, 'MISSION', 2, '2024-04-19', null, '2024-04-19');
INSERT INTO Payment_History (payment_id, member_id, mission_id, payment_code, payment_amount, payment_date, reason,
                             create_date)
values (5, 1, 4, 'MISSION', 2, '2024-04-19', 'NON_OPERATING_PERIOD', '2024-04-19');
INSERT INTO Payment_History (payment_id, member_id, mission_id, payment_code, payment_amount, payment_date, reason,
                             create_date)
values (6, 1, 4, 'MISSION', 2, '2024-04-19', null, '2024-04-19');
INSERT INTO Payment_History (payment_id, member_id, mission_id, payment_code, payment_amount, payment_date, reason,
                             create_date)
values (6, 1, 4, 'MISSION', 2, '2024-04-19', 'NON_OPERATING_PERIOD', '2024-04-19');

