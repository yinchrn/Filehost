create table share
(
    id          int auto_increment
        primary key,
    link        varchar(100) charset utf8mb3 null,
    uid         int                          null,
    fid         int                          null,
    create_date datetime                     null
);

INSERT INTO mybatis.share (id, link, uid, fid, create_date) VALUES (6, 'e1b5e661-defe-43c8-b1fd-279ee8e82d79', 1, 4, '2023-04-30 17:23:07');
INSERT INTO mybatis.share (id, link, uid, fid, create_date) VALUES (7, '7ffc6224-00e5-4673-ad8f-8a9996ae154e', 1, 6, '2023-04-30 17:24:18');
INSERT INTO mybatis.share (id, link, uid, fid, create_date) VALUES (8, 'd338c48e-d948-4aef-a78e-adbb457732e5', 1, 4, '2023-04-30 17:41:52');
INSERT INTO mybatis.share (id, link, uid, fid, create_date) VALUES (9, '7ca5d63d-09b4-40bc-a5a9-008f1ac30268', 1, 10, '2023-05-01 01:50:53');