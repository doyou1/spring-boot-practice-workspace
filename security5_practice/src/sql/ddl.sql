DROP TABLE auth ;

DROP TABLE user ;


 

CREATE TABLE user(

     uname varchar(50) not null primary key,

     pwd varchar(100) not null,

     enabled boolean not null


);


 

CREATE TABLE auth (

     uname varchar(50) not null,

     role  varchar(50) not null,

     constraint fk_auth_user foreign key(uname)

           references user(uname)

);


 

-- user1


-- pwd : 1234


insert into user(uname,pwd,enabled)

values('user1','$2a$10$fZL/N/Xotw.zH2n8A/JbUugjC4SegtDKzh2t.GTAauv5k8WRljApa', true);

insert into auth(uname,role) values('user1','ROLE_USER');


 

-- user2


-- USER or ADMIN Role은 없다.


-- pwd : 1234


insert into user(uname,pwd,enabled)

values('user2','$2a$10$fZL/N/Xotw.zH2n8A/JbUugjC4SegtDKzh2t.GTAauv5k8WRljApa', true);


 

-- admin


-- pwd : "admin@123"


insert into user(uname,pwd,enabled)

values('admin','$2a$10$hbxecwitQQ.dDT4JOFzQAulNySFwEpaFLw38jda6Td.Y/cOiRzDFu',true);

insert into auth(uname,role) values('admin','ROLE_ADMIN');