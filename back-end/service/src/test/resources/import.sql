insert into CLIENT (name) values ('me');

insert into ACCOUNT (code, balance, owner_id) values ('00000', 100, (select id from CLIENT where name = 'me'));