create table MOVEMENT
(
    MOVE_ID   BIGINT auto_increment
        primary key,
    BALANCE  BIGINT,
    DATE     DATE,
    DEAL_SUM BIGINT
);
