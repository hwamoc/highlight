DROP TABLE IF EXISTS `highlight` CASCADE;
DROP TABLE IF EXISTS `page` CASCADE;
DROP TABLE IF EXISTS `user` CASCADE;
DROP TABLE IF EXISTS `theme` CASCADE;

CREATE TABLE `theme`
(
    `id`            bigint      NOT NULL AUTO_INCREMENT,     --테마 PK
    `type`          varchar(30) NOT NULL DEFAULT 'Custom',   --테마 종류
    `created_by`    bigint      NOT NULL,                    --테마 생성자 PK
    `first`         varchar(30) NOT NULL,                    --첫번째 색상 hex값
    `second`        varchar(30) NOT NULL,                    --두번째 색상 hex값
    `third`         varchar(30) NOT NULL,                    --세번째 색상 hex값
    `fourth`        varchar(30) DEFAULT NULL,                --네번째 색상 hex값
    `fifth`         varchar(30) DEFAULT NULL,                --다섯번째 색상 hex값
    `sixth`         varchar(30) DEFAULT NULL,                --여섯번째 색상 hex값
    `modified_date` datetime    DEFAULT NULL,
    `created_date`  datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    PRIMARY KEY (`id`)
);

CREATE TABLE `user`
(
    `id`                bigint      NOT NULL AUTO_INCREMENT,          --사용자 PK
    `name`              varchar(10) NOT NULL,                         --사용자명
    `email`             varchar(50) NOT NULL,                         --로그인 이메일
    `password`          varchar(80) NOT NULL,                         --로그인 비밀번호
    `theme_id`          bigint      NOT NULL DEFAULT 1,               --현재 테마 PK
    `modified_date`     datetime    DEFAULT NULL,
    `created_date`      datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    PRIMARY KEY (`id`),
    CONSTRAINT unq_user_email UNIQUE (`email`),
    CONSTRAINT fk_theme_to_theme FOREIGN KEY (`theme_id`) REFERENCES `theme` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
);

CREATE TABLE `page`
(
    `id`            bigint        NOT NULL AUTO_INCREMENT, --페이지 PK
    `user_id`       bigint        NOT NULL,                --생성자 PK (user 테이블 참조)
    `page_url`      varchar(200)  NOT NULL,                --페이지 주소
    `modified_date` datetime      DEFAULT NULL,
    `created_date`  datetime      NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    PRIMARY KEY (`id`)
);

CREATE TABLE `highlight`
(
    `id`             bigint   NOT NULL AUTO_INCREMENT,               --하이라이트 PK
    `user_id`        bigint   NOT NULL,                              --생성자 PK (user 테이블 참조)
    `page_id`        bigint   NOT NULL,                              --페이지 PK (page 테이블 참조)
    `color_ordinal`  varchar(20)       DEFAULT 0 NOT NULL,           --테마 색상 서수값
    `color_hex`      varchar(20)       DEFAULT '#fdff85' NOT NULL,   --하이라이트 색상
    `text`           varchar(6000)     DEFAULT NULL,                 --내용
    `modified_date`  datetime          DEFAULT NULL,                 --수정 일자
    `created_date`   datetime NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    PRIMARY KEY (`id`),
    CONSTRAINT fk_order_to_user FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT fk_order_to_product FOREIGN KEY (`page_id`) REFERENCES `page` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
);

CREATE INDEX IDX_CREATEBY ON `theme` (`created_by`);

CREATE INDEX IDX_USER_ID ON `page` (`user_id`);
CREATE INDEX IDX_PAGE_URL ON `page` (`page_url`);

CREATE INDEX IDX_IDX_PAGE_ID ON `highlight` (`page_id`);
CREATE INDEX IDX_IDX_USER_ID ON `highlight` (`user_id`);
CREATE INDEX IDX_IDX_PAGE_ID_USER_ID ON `highlight` (`page_id`, `user_id`);


