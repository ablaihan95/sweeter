create sequence message_seq start with 1 increment by 50;
create sequence usr_seq start with 1 increment by 50;
create table message
(
    id       bigint not null,
    filename varchar(255),
    tag      varchar(255),
    text     varchar(255) not null,
    user_id  bigint,
    primary key (id)
);
create table user_role
(
    user_id bigint not null,
    roles   varchar(255)
);
create table usr
(
    id              bigint  not null,
    activation_code varchar(255),
    email           varchar(255),
    firstname       varchar(255),
    is_active       boolean not null,
    password        varchar(255) not null,
    username        varchar(255) not null,
    primary key (id)
);
alter table if exists message add constraint FK70bv6o4exfe3fbrho7nuotopf foreign key (user_id) references usr;
alter table if exists user_role add constraint FKfpm8swft53ulq2hl11yplpr5 foreign key (user_id) references usr
