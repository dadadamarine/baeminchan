INSERT INTO account (id, user_id, password, name, email, phone_number, type ,create_at) values (1l, 'test@google.com', '$2a$10$75iOIZVohML12YADpMEqre39yvfFQWn8PkoCf0VMct6ItcVVb77B.', '자바지기', 'javajigi@slipp.net', '010-1111-1111', 'MEMBER', CURRENT_TIMESTAMP());
INSERT INTO account (id, user_id, password, name, email, phone_number, type ,create_at) values (2l, 'admin@admin.com', '$2a$10$75iOIZVohML12YADpMEqre39yvfFQWn8PkoCf0VMct6ItcVVb77B.', '관리자', 'admin@admin.com', '010-1111-1111', 'MANAGER', CURRENT_TIMESTAMP());

INSERT INTO menu_category (name, parent_id, id) values ('categories', null , 19l)
INSERT INTO menu_category (name, parent_id, id) values ('밑반찬', 19l, 1l);
INSERT INTO menu_category (name, parent_id, id) values ('무침', 1l, 2l);
INSERT INTO menu_category (name, parent_id, id) values ('나물무침', 1l, 3l);
INSERT INTO menu_category (name, parent_id, id) values ('볶음', 1l, 4l);
INSERT INTO menu_category (name, parent_id, id) values ('조림', 1l, 5l);

INSERT INTO menu_category (name, parent_id, id) values ('국·찌개', 19l, 6l);
INSERT INTO menu_category (name, parent_id, id) values ('담백한국', 6l, 7l);
INSERT INTO menu_category (name, parent_id, id) values ('찌개', 6l, 8l);

INSERT INTO menu_category (name, parent_id, id) values ('메인반찬', 19l, 9l);
INSERT INTO menu_category (name, parent_id, id) values ('고기반찬', 9l, 10l);

INSERT INTO menu_category (name, parent_id, id) values ('아이반찬', 19l, 11l);
INSERT INTO menu_category (name, parent_id, id) values ('이유식 초기/중기', 11l, 12l);

INSERT INTO menu_category (name, parent_id, id) values ('정기식단', 19l, 13l);
INSERT INTO menu_category (name, parent_id, id) values ('1~2인', 13l, 14l);

INSERT INTO menu_category (name, parent_id, id) values ('신선·가공', 19l, 15l);
INSERT INTO menu_category (name, parent_id, id) values ('가공반찬', 15l, 16l);

INSERT INTO menu_category (name, parent_id, id) values ('간식', 19l, 17l);
INSERT INTO menu_category (name, parent_id, id) values ('식사빵', 17l, 18l);