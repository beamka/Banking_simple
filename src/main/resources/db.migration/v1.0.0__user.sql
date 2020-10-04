create table USER
(
    USER_ID BIGINT auto_increment primary key,
    EMAIL    VARCHAR(255) not null,
    PASS_HASH    VARCHAR(255) not null
);
