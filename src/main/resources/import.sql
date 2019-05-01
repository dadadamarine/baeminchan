INSERT INTO account (id, user_id, password, name, email, phone_number, type ,create_at) values (1, 'test@google.com', '$2a$10$75iOIZVohML12YADpMEqre39yvfFQWn8PkoCf0VMct6ItcVVb77B.', '자바지기', 'javajigi@slipp.net', '010-1111-1111', 'MEMBER', CURRENT_TIMESTAMP());
INSERT INTO account (id, user_id, password, name, email, phone_number, type ,create_at) values (2, 'admin@admin.com', '$2a$10$75iOIZVohML12YADpMEqre39yvfFQWn8PkoCf0VMct6ItcVVb77B.', '관리자', 'admin@admin.com', '010-1111-1111', 'ADMIN', CURRENT_TIMESTAMP());

INSERT INTO menu_category (name, parent_id, id, create_at) values ('밑반찬', NULL, 1, CURRENT_TIMESTAMP());
INSERT INTO menu_category (name, parent_id, id, create_at) values ('무침', 1, 2, CURRENT_TIMESTAMP());
INSERT INTO menu_category (name, parent_id, id, create_at) values ('나물무침', 1, 3, CURRENT_TIMESTAMP());
INSERT INTO menu_category (name, parent_id, id, create_at) values ('볶음', 1, 4, CURRENT_TIMESTAMP());
INSERT INTO menu_category (name, parent_id, id, create_at) values ('조림', 1, 5, CURRENT_TIMESTAMP());


INSERT INTO menu_category (name, parent_id, id, create_at) values ('국·찌개', NULL, 6, CURRENT_TIMESTAMP());
INSERT INTO menu_category (name, parent_id, id, create_at) values ('담백한국', 6, 7, CURRENT_TIMESTAMP());
INSERT INTO menu_category (name, parent_id, id, create_at) values ('찌개', 6, 8, CURRENT_TIMESTAMP());

INSERT INTO menu_category (name, parent_id, id, create_at) values ('메인반찬', NULL, 9, CURRENT_TIMESTAMP());
INSERT INTO menu_category (name, parent_id, id, create_at) values ('고기반찬', 9, 10, CURRENT_TIMESTAMP());

INSERT INTO menu_category (name, parent_id, id, create_at) values ('아이반찬', NULL, 11, CURRENT_TIMESTAMP());
INSERT INTO menu_category (name, parent_id, id, create_at) values ('이유식 초기/중기', 11, 12, CURRENT_TIMESTAMP());

INSERT INTO menu_category (name, parent_id, id, create_at) values ('정기식단', NULL, 13 ,CURRENT_TIMESTAMP());
INSERT INTO menu_category (name, parent_id, id, create_at) values ('1~2인', 13, 14, CURRENT_TIMESTAMP());

INSERT INTO menu_category (name, parent_id, id, create_at) values ('신선·가공', NULL, 15, CURRENT_TIMESTAMP());
INSERT INTO menu_category (name, parent_id, id, create_at) values ('가공반찬', 15, 16, CURRENT_TIMESTAMP());

INSERT INTO menu_category (name, parent_id, id, create_at) values ('간식', NULL, 17, CURRENT_TIMESTAMP());
INSERT INTO menu_category (name, parent_id, id, create_at) values ('식사빵', 17, 18, CURRENT_TIMESTAMP());