drop table if exists show_overlay cascade;
create table show_overlay
(
    id       bigint not null,
    switcher boolean,
    primary key (id)
);

drop table if exists font_link cascade;
create table font_link
(
    id              bigint not null,
    font_address     varchar(255),
    font_name      varchar(255),
    primary key (id)
);

drop table if exists character_picture cascade;
create table character_picture
(
    id                       bigint not null,
    address_of_big_picture   varchar(255),
    address_of_small_picture varchar(255),
    is_died          boolean,
    primary key (id)
);

drop table if exists picture_of_drop cascade;
create table picture_of_drop
(
    id              bigint not null,
    numbers_of_drop integer not null,
    address_picture varchar(255),
    primary key (id)
);

drop table if exists picture_of_rank cascade;
create table picture_of_rank
(
    id              bigint not null,
    numbers_of_rank integer not null,
    address_picture varchar(255),
    primary key (id)
);

drop table if exists users cascade;
create table users
(
    id              bigint  not null,
    username        varchar(255) not null,
    password        varchar(255) not null,
    role            varchar(50),
    rank_id         bigint,
    drop_id         bigint,
    picture_id      bigint,
    primary key (id)
);
create sequence seq_character_picture start with 1 increment by 1;
create sequence seq_picture_of_drop start with 1 increment by 1;
create sequence seq_picture_of_rank start with 1 increment by 1;
create sequence seq_users start with 1 increment by 1;
alter table users add constraint FK29iiwenwktq1iquhr8xr7ngjo foreign key (drop_id) references picture_of_drop;
alter table users add constraint FK4bgueejxm5mpxvs9sjvmxmpta foreign key (picture_id) references character_picture;
alter table users add constraint FK5voa919cx4k1ka2ak5mi3tjon foreign key (rank_id) references picture_of_rank;
