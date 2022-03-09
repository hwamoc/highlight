-- Theme 데이터 생성
INSERT INTO theme(id, type, created_by, first, second, third)
VALUES (1, 'Default', 1, '#ffff8d', '#a5f2e9', '#ffd5c8');
INSERT INTO theme(id, type, created_by, first, second, third)
VALUES (2, 'Default', 1, '#f6f0aa', '#d3edd1', '#f9d6c1');
INSERT INTO theme(id, type, created_by, first, second, third)
VALUES (100, 'Custom', 1, '#fdff85', '#a8fff9', '#9dff9c');
INSERT INTO theme(id, type, created_by, first, second, third)
VALUES (101, 'Custom', 2, '#fffbc9', '#c9ceff', '#ffc4c4');
INSERT INTO theme(id, type, created_by, first, second, third)
VALUES (102, 'Custom', 3, '#e7edb4', '#c2e3ba', '#adc9de');

-- User 데이터 생성
INSERT INTO user(id, name, email, password, theme_id)
VALUES (1, 'tester', 'tester@gmail.com', '$2a$10$mzF7/rMylsnxxwNcTsJTEOFhh1iaHv3xVox.vpf6JQybEhE4jDZI.', 1);
INSERT INTO user(id, name, email, password, theme_id)
VALUES (2, 'tester2', 'tester2@gmail.com', '$2a$10$mzF7/rMylsnxxwNcTsJTEOFhh1iaHv3xVox.vpf6JQybEhE4jDZI.', 2);
INSERT INTO user(id, name, email, password, theme_id)
VALUES (3, 'tester3', 'tester3@gmail.com', '$2a$10$mzF7/rMylsnxxwNcTsJTEOFhh1iaHv3xVox.vpf6JQybEhE4jDZI.', 1);

-- Page 데이터 생성
INSERT INTO page(id, user_id, page_url)
VALUES (1, 1, 'www.getliner.com');
INSERT INTO page(id, user_id, page_url)
VALUES (2, 2, 'www.naver.com');
INSERT INTO page(id, user_id, page_url)
VALUES (3, 2, 'www.google.com');
INSERT INTO page(id, user_id, page_url)
VALUES (4, 3,'www.bbc.com/korean/news-60643146');
INSERT INTO page(id, user_id, page_url)
VALUES (5, 3, 'www.earticle.net/Article/A225635');

-- Highlight 데이터 생성
INSERT INTO highlight(id, user_id, page_id, color_ordinal, text, modified_date)
VALUES (1, 1, 1, 'first', '처음 긋는 하이라이트~~', '2021-01-24 12:10:30');
INSERT INTO highlight(id, user_id, page_id, color_ordinal, text, modified_date)
VALUES (2, 1, 1, 'first', '구우우웃 구우우웃', null);
INSERT INTO highlight(id, user_id, page_id, color_ordinal, text, modified_date)
VALUES (3, 1, 1, 'second', '꾸르잼잼잼 ^0^', '2021-03-28 14:20:30');

INSERT INTO highlight(id, user_id, page_id, color_ordinal, text, modified_date)
VALUES (4, 2, 2, 'first', 'AROUND', null);
INSERT INTO highlight(id, user_id, page_id, color_ordinal, text, modified_date)
VALUES (5, 2, 2, 'second', '디자인', '2021-07-30 12:30:20');
INSERT INTO highlight(id, user_id, page_id, color_ordinal, text, modified_date)
VALUES (6, 2, 3, 'third', 'SPACE', '2021-10-21 16:40:40');

INSERT INTO highlight(id, user_id, page_id, color_ordinal, text, modified_date)
VALUES (7, 3, 4, 'first', '뚜두뚜두뚜', '2022-01-25 12:10:30');
INSERT INTO highlight(id, user_id, page_id, color_ordinal, text, modified_date)
VALUES (8, 3, 4, 'second', 'blank pink in your area', '2022-01-24 10:30:10');
INSERT INTO highlight(id, user_id, page_id, color_ordinal, text, modified_date)
VALUES (9, 3, 5, 'first', 'everything i need is on the ground', '2022-03-24 18:30:00');
INSERT INTO highlight(id, user_id, page_id, color_ordinal, text, modified_date)
VALUES (10, 3, 5, 'third', 'lets kill this love', null);

