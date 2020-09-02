CREATE DATABASE IF NOT EXISTS trainingDB;
USE trainingDB;

-- Table Creation --
create table if not exists account (
       id bigint not null auto_increment,
        age integer not null,
        created_date datetime,
        email varchar(255),
        gender integer,
        height integer not null,
        modified_date datetime,
        name varchar(255),
        weight integer not null,
        primary key (id)
    );

create table if not exists training (
       id bigint not null auto_increment,
       calories integer not null,
       created_date datetime,
       device_firmware varchar(255),
       device_name varchar(255),
       time integer not null,
       type varchar(255),
       account_id bigint,
       primary key (id)
    );

create table if not exists consumer (
      id bigint not null auto_increment,
      created_date datetime,
      email varchar(255),
      modified_date datetime,
      name varchar(255),
      primary key (id)
    );

alter table training
       add constraint FK1oqx8w36x6888h6gvxcy0qf28
       foreign key (account_id)
       references account (id);

-- Data Creation --
INSERT INTO trainingDB.account (id, age, created_date, email, gender, height, modified_date, name, weight) VALUES (1, 20, '2020-09-03 01:06:36', 'user2@gmail.com', 0, 165, null, 'username1', 55);
INSERT INTO trainingDB.account (id, age, created_date, email, gender, height, modified_date, name, weight) VALUES (2, 21, '2020-09-03 01:06:37', 'user2@gmail.com', 1, 155, null, 'username2', 65);
INSERT INTO trainingDB.account (id, age, created_date, email, gender, height, modified_date, name, weight) VALUES (3, 22, '2020-09-03 01:06:38', 'user2@gmail.com', 0, 175, null, 'username3', 75);
INSERT INTO trainingDB.account (id, age, created_date, email, gender, height, modified_date, name, weight) VALUES (4, 23, '2020-09-03 01:06:39', 'user2@gmail.com', 1, 185, null, 'username4', 85);
INSERT INTO trainingDB.account (id, age, created_date, email, gender, height, modified_date, name, weight) VALUES (5, 24, '2020-09-03 01:06:40', 'user2@gmail.com', 0, 195, null, 'username5', 95);

INSERT INTO trainingDB.consumer (id, created_date, email, modified_date, name) VALUES (1, '2020-09-03 01:10:21', 'consumer1@gmail.com', null, 'consumer_name_1');
INSERT INTO trainingDB.consumer (id, created_date, email, modified_date, name) VALUES (2, '2020-09-03 01:10:22', 'consumer2@gmail.com', null, 'consumer_name_2');
INSERT INTO trainingDB.consumer (id, created_date, email, modified_date, name) VALUES (3, '2020-09-03 01:10:23', 'consumer3@gmail.com', null, 'consumer_name_3');
INSERT INTO trainingDB.consumer (id, created_date, email, modified_date, name) VALUES (4, '2020-09-03 01:10:24', 'consumer4@gmail.com', null, 'consumer_name_4');
INSERT INTO trainingDB.consumer (id, created_date, email, modified_date, name) VALUES (5, '2020-09-03 01:10:25', 'consumer5@gmail.com', null, 'consumer_name_5');

INSERT INTO trainingDB.training (id, calories, created_date, device_firmware, device_name, time, type, account_id) VALUES (1, 100, '2020-09-03 01:13:31', '1_0_1', 'Xiaomi MiBand 3', 100, 'jogging', 1);
INSERT INTO trainingDB.training (id, calories, created_date, device_firmware, device_name, time, type, account_id) VALUES (2, 200, '2020-09-03 01:13:32', '1_0_2', 'Xiaomi MiBand 4', 200, 'cycling', 2);
INSERT INTO trainingDB.training (id, calories, created_date, device_firmware, device_name, time, type, account_id) VALUES (3, 300, '2020-09-03 01:13:33', '1_0_3', 'Xiaomi MiBand 5', 300, 'workout', 3);
INSERT INTO trainingDB.training (id, calories, created_date, device_firmware, device_name, time, type, account_id) VALUES (4, 400, '2020-09-03 01:13:34', '1_0_4', 'Xiaomi MiBand 3', 400, 'swimming', 4);
INSERT INTO trainingDB.training (id, calories, created_date, device_firmware, device_name, time, type, account_id) VALUES (5, 500, '2020-09-03 01:13:35', '1_0_5', 'Xiaomi MiBand 4', 500, 'skating', 5);















