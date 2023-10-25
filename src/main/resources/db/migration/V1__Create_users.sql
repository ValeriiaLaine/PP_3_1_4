drop table if exists roles;
drop table if exists users;
drop table if exists users_roles;
create table roles (id bigint not null auto_increment, role varchar(255), primary key (id)) engine=InnoDB;
create table users (id bigint not null auto_increment, age tinyint, email varchar(255), lastname varchar(255), password varchar(255), username varchar(255), primary key (id)) engine=InnoDB;
create table users_roles (user_id bigint not null, role_id bigint not null) engine=InnoDB;
alter table users_roles add constraint FKj6m8fwv7oqv74fcehir1a9ffy foreign key (role_id) references roles (id);
alter table users_roles add constraint FK2o0jvgh89lemvvo17cbqvdxaa foreign key (user_id) references users (id);

insert into roles (role) values ('ROLE_ADMIN');
insert into roles (role) values ('ROLE_USER');
insert into users (age, email, lastname, password, username) values (50, 'admin@gmail.com', 'user', '$2a$12$EDZLIzaLH8FX.Sh9VH8wVO66l8tOJB/tkpODcTBfUCweWO1s2u4pi', 'admin');
insert into users_roles (user_id, role_id) values (1, 1);
insert into users (age, email, lastname, password, username) values (50, 'user@gmail.com', 'user', '$2a$12$zAS2iEpQmkBl7P/7037BxeYjKNEYDdD/sUQ.aqvyQR3XotfFg8YOG', 'user');
insert into users_roles (user_id, role_id) values (2, 2);