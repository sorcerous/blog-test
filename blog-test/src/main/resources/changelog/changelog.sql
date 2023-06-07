drop table if exists blog CASCADE;
drop table if exists blog_user CASCADE;

create table blog (
blog_id varchar(255) not null,
content varchar(255),
created_ts timestamp,
modified_by varchar(255),
modified_ts timestamp,
parent_id varchar(255),
created_by_user_id varchar(255),
primary key (blog_id)
);

create table blog_user (
user_id varchar(255) not null,
age integer not null,
gender varchar(255),
username varchar(255),
primary key (user_id)
);

alter table blog
add constraint fk
foreign key (created_by_user_id)
references blog_user;