create table ACC_MOVE
(
    ACC_ID  BIGINT not null,
    MOVE_ID BIGINT not null unique,
        foreign key (ACC_ID) references ACCOUNT (ACC_ID),
        foreign key (MOVE_ID) references MOVEMENT (MOVE_ID)
);
