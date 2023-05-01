create table file
(
    id            int auto_increment
        primary key,
    type          varchar(30) charset utf8mb3  null,
    name          varchar(200) charset utf8mb3 null,
    uid           int                          null,
    local_name    varchar(100) charset utf8mb3 null,
    size          bigint                       null,
    observed_size varchar(30) charset utf8mb3  null,
    create_date   datetime                     null
);

INSERT INTO mybatis.file (id, type, name, uid, local_name, size, observed_size, create_date) VALUES (4, 'png', 'illust_98731764_20230104_055800.png', 1, '565107b3-0890-4899-abc6-f280c0fe39fc.png', 1504375, '1MB', null);
INSERT INTO mybatis.file (id, type, name, uid, local_name, size, observed_size, create_date) VALUES (5, 'docx', '调研报告.docx', 1, '8db41038-5b9b-408b-9d76-66347c206b39.docx', 20520, '20KB', '2023-04-23 23:09:37');
INSERT INTO mybatis.file (id, type, name, uid, local_name, size, observed_size, create_date) VALUES (6, 'txt', '132.txt', 1, '6b4be180-23ae-4695-8b9a-6cc9382cf0ba.txt', 3, '3B', '2023-04-24 17:19:17');
INSERT INTO mybatis.file (id, type, name, uid, local_name, size, observed_size, create_date) VALUES (8, 'txt', '132.txt', 5, '6b4be180-23ae-4695-8b9a-6cc9382cf0ba.txt', 3, '3B', '2023-04-24 17:19:17');
INSERT INTO mybatis.file (id, type, name, uid, local_name, size, observed_size, create_date) VALUES (11, 'wav', '4.wav', 5, 'cca18862-f174-4274-b248-297875fc3474.wav', 126764, '123KB', '2023-05-01 01:52:16');
INSERT INTO mybatis.file (id, type, name, uid, local_name, size, observed_size, create_date) VALUES (12, 'wav', '4.wav', 5, '3a25129b-425c-4cb0-bacd-de71f6795ded.wav', 126764, '123KB', '2023-05-01 01:52:39');
INSERT INTO mybatis.file (id, type, name, uid, local_name, size, observed_size, create_date) VALUES (13, 'wav', '3.wav', 5, '89ed1003-50f5-409e-834a-3b05402ac3ba.wav', 129596, '126KB', '2023-05-01 01:52:51');
INSERT INTO mybatis.file (id, type, name, uid, local_name, size, observed_size, create_date) VALUES (14, 'wav', '2.wav', 5, 'ec99231e-e44a-488a-a33c-bb5f1730cf41.wav', 386844, '377KB', '2023-05-01 01:53:00');
INSERT INTO mybatis.file (id, type, name, uid, local_name, size, observed_size, create_date) VALUES (15, 'wav', '1.wav', 5, '6cc228fe-e559-4fd8-9163-d9df2a4b5233.wav', 39336, '38KB', '2023-05-01 01:53:08');
INSERT INTO mybatis.file (id, type, name, uid, local_name, size, observed_size, create_date) VALUES (16, 'wav', '2.wav', 1, 'f9f0e951-4e48-462c-8618-cabf9fae9fbc.wav', 386844, '377KB', '2023-05-01 12:19:32');
INSERT INTO mybatis.file (id, type, name, uid, local_name, size, observed_size, create_date) VALUES (17, 'wav', '1.wav', 1, '77a76e55-48c1-4e56-815c-5ed7ef135c7d.wav', 39336, '38KB', '2023-05-01 13:25:23');
INSERT INTO mybatis.file (id, type, name, uid, local_name, size, observed_size, create_date) VALUES (18, 'txt', '文明6_Sid_Meier''s_Civilization_VI_v1.0.12.31_中文语音全DLC中文版.txt', 1, '32cca1a1-f788-4e17-82f7-1564cf718b45.txt', 283, '283B', '2023-05-01 13:25:45');
INSERT INTO mybatis.file (id, type, name, uid, local_name, size, observed_size, create_date) VALUES (19, 'pdf', 'MySQL 实战教程.pdf', 1, '55c2d6e0-4bfa-4807-afd1-d4ea417cb3e9.pdf', 441389, '431KB', '2023-05-01 13:26:05');
INSERT INTO mybatis.file (id, type, name, uid, local_name, size, observed_size, create_date) VALUES (20, 'zip', 'VBlog-master.zip', 1, '9f72ff70-6786-48db-b8d7-7250d41a2687.zip', 3349141, '3MB', '2023-05-01 13:26:37');