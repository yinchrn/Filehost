create table user
(
    id       int auto_increment
        primary key,
    username varchar(30) charset utf8mb3 null,
    password varchar(30) charset utf8mb3 null,
    role     varchar(30) charset utf8mb3 null
);

INSERT INTO mybatis.user (id, username, password, role) VALUES (1, '10', '123456', 'user');
INSERT INTO mybatis.user (id, username, password, role) VALUES (5, '20', '123456', null);
INSERT INTO mybatis.user (id, username, password, role) VALUES (6, '30', '123456', null);