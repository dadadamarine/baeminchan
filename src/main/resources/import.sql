INSERT INTO account (id, user_id, password, name, email, phone_number, type ,create_at) values (1, 'test@google.com', '$2a$10$75iOIZVohML12YADpMEqre39yvfFQWn8PkoCf0VMct6ItcVVb77B.', '자바지기', 'javajigi@slipp.net', '010-1111-1111', 'MEMBER', CURRENT_TIMESTAMP());
INSERT INTO account (id, user_id, password, name, email, phone_number, type ,create_at) values (2, 'admin@admin.com', '$2a$10$75iOIZVohML12YADpMEqre39yvfFQWn8PkoCf0VMct6ItcVVb77B.', '관리자', 'admin@admin.com', '010-1111-1111', 'MANAGER', CURRENT_TIMESTAMP());

INSERT INTO menu_category (name, parent_id, id) values ('categories', null , 19)
INSERT INTO menu_category (name, parent_id, id) values ('밑반찬', 19, 1);
INSERT INTO menu_category (name, parent_id, id) values ('무침', 1, 2);
INSERT INTO menu_category (name, parent_id, id) values ('나물무침', 1, 3);
INSERT INTO menu_category (name, parent_id, id) values ('볶음', 1, 4);
INSERT INTO menu_category (name, parent_id, id) values ('조림', 1, 5);


INSERT INTO menu_category (name, parent_id, id) values ('국·찌개', 19, 6);
INSERT INTO menu_category (name, parent_id, id) values ('담백한국', 6, 7);
INSERT INTO menu_category (name, parent_id, id) values ('찌개', 6, 8);

INSERT INTO menu_category (name, parent_id, id) values ('메인반찬', 19, 9);
INSERT INTO menu_category (name, parent_id, id) values ('고기반찬', 9, 10);

INSERT INTO menu_category (name, parent_id, id) values ('아이반찬', 19, 11);
INSERT INTO menu_category (name, parent_id, id) values ('이유식 초기/중기', 11, 12);

INSERT INTO menu_category (name, parent_id, id) values ('정기식단', 19, 13);
INSERT INTO menu_category (name, parent_id, id) values ('1~2인', 13, 14);

INSERT INTO menu_category (name, parent_id, id) values ('신선·가공', 19, 15);
INSERT INTO menu_category (name, parent_id, id) values ('가공반찬', 15, 16);

INSERT INTO menu_category (name, parent_id, id) values ('간식', 19, 17);
INSERT INTO menu_category (name, parent_id, id) values ('식사빵', 17, 18);